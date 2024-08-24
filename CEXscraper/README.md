# Project: The CEX Scraper

This project is something I wanted to do for a long time. I wanted to have an automated way of obtaining prices for a 
large number of items from the CEX website. The item list may have hundreds of items, and it would be very time-consuming
to search for each one manually. The idea is to have an Excel file with the items I want to search for, and the script
will search for them on CEX and write the prices back to a new Excel file.

# Learning opportunities
1. **Web scraping**: This project will allow me to learn how to scrape data from websites. I will use Selenium to do this.
2. **Excel manipulation**: I will learn how to read and write Excel files using Apache POI.
3. **Architecture**: I will have to think about the best way to structure the project. I will have to think about how to
   handle exceptions, how to make the code more readable, and how to make it more efficient.

# Architecture
One could easily create a monolith that deals with everything, from obtaining the items from the Excel file to writing
the prices back to a new Excel file. However, I wanted to think a bit about the flow of data and how to make the code a 
bit more resilient to change. I knew I needed to have a vessel for the information to be transfered between the different
stages of the process. I decided to create a class called `Item` that would hold the information of the items. Now this
class would, thinking about it clearly, have two states: the initial state, where the item only has the details of the 
source file, and the final state, where the item has all the prices obtained from the CEX website. Thinking about it this
way was an easy way to think about the "Item" class as almost a DTO. Obviously this is not the best way to do it, but it
was a good way to start thinking about the problem.

Separating the process into different stages, I decided to create three classes: `WRTExcelSource`, `CEXScraper`, and
`OutputExcel`. The first class would be responsible for reading the items from the Excel file, the second class would be
responsible for scraping the prices from the CEX website, and the third class would be responsible for writing the prices
back to a new Excel file. This way, each class would have a single responsibility, making the code easier to read and
maintain. The main class would be responsible for orchestrating the process, calling the other classes in the correct
order.

There is some room for improvement in this architecture. For example, the `Item` class could be split into two classes:
one for the initial state and one for the final state. This would make the code more readable and maintainable. Also, the
`WRTExcelSource` class could be split into two classes: one for reading the items from the Excel file and one for
filtering the items based on the store and item type. This would make the code more modular and easier to test.

In terms of coding practices, I tried to follow the SOLID principles. I tried to make the classes small and reasonably
focused. In terms of inversion of control, there is something to be said about perhaps having interfaces for the "sources",
for example, if instead of relying on the Excel file, we could have a database or a REST API or something else, as long as
it followed the same contract. I may pursue this at a later stage, if I find that I need to expand the project.

# Theoretical concepts
1. **Scraping**: When thinking of scraping one could argue that Java is probably not the best language for this. Python
has a lot of libraries that make scraping easier, such as BeautifulSoup and Scrapy. However, I wanted to use Java
as I am more familiar with the language, and am more into learning new concepts than learning a new language at the moment. 
The first hurdle I had to overcome was the fact that CEX uses JavaScript to render the page, which means that I cannot simply use 
a library like Jsoup to scrape the data, as the dynamic nature of the page would not be captured. I had to use a library 
that could render the page and Selenium popped up. Selenium was relatively easy to use, and I was able to get the data I
needed without too much trouble. I learned that Selenium is not thread-safe, which means that I cannot use it in a
multi-threaded environment. This was a bit of a setback, as I wanted to make the process more efficient by running
multiple threads. 

## V1
The first iteration of the script is a simple proof of concept. It reads a list of product names from an Excel file,
searches for them on CEX, and writes the first price found back to a new Excel file. There are several issue with this
version, such as the fact that the search is not very accurate, and the script is not very efficient. I learned that
WebDriver is not thread safe, which leads the program to have loads of issues when trying to run multiple threads, often
even having memory leaks.

## V2   
After the webdriver disappointment, I decided to maintain a single thread, but I added a lot of improvements to the script.
A possible alternative would be to create several instances of the webdriver, but that would be a lot more complicated and
I am not sure if it would be worth it.
Changelog:
1. Ensured only the items that have significant price differences are written to the output file.
2. Added feedback via console which signals how many items were obtained from the excel file and the current % of completion
during CEX scraping.
3. Added an extra filter for item types. This is because CEX will never have fridges and other large items;
4. Added a convenient place to add stores and types on the main function. This could be done in a config file, but this
should be enough for now.

## V3
Looking at the spanish cex site, it seems the page composition is the same, which means the only things that needs to be
changed when looking at the portuguese or spanish site is the URL. Modified the scraper to receive a URL as argument. This
inverts the dependency, as the caller is now responsible for providing the URL, making it easier to swap between different
sites. This also makes it easier to add more sites in the future, as the scraper is now more generic.

# Next steps

1. Adapt source to be from a website database (vinted/wallapop)
3. Add a config file for the stores and types
