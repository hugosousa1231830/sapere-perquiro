# Project: The Epic Price Scraper Saga: Worten vs. CEX

## The Grand Plan

Welcome to my grand adventure in web scraping, where I pit the mighty Worten against the formidable CEX! The mission, 
is to create a digital wizard that reads product data from an Excel scroll, fetches the price from the CEX realm, and 
then records the results in a shiny new Excel parchment. Obviously, one lacks the necessary knowledge to perform such
feat, therefore it should grant quite a lot of experience points. And yes, there is a bit of CHATGPT magic involved, if
I am to be replaced, I will leech xp as much as I can.

Important Note: The driver is too big, so it is ignored (just download the most recent chrome driver and it should work), 
also it is very unlikely you will be able to reproduce this locally as it requires a specific excel file that I am not 
able to share. However the main code should be enough to illustrate what it is meant to do.

## The Epic Workflow

### Excel Reading Extravaganza:

- Summon the mystical powers of Apache POI to read an ancient Excel file filled with product names from the land of Worten.
- Extract a list of names like a treasure hunter finding gold.

### Browser Simulation Shenanigans:

- Wield the Selenium wand to simulate browser magic and traverse the CEX kingdom’s search page.
- Retrieve the first available price from the CEX oracle (or as close as we can get).

### Excel Writing Wizardry:

- Create a new Excel scroll to record our findings.
- Populate it with product names and their newfound prices from CEX, all neatly arranged for easy viewing.

## The Quest for Knowledge

Before embarking on this quest, I need to master several arcane arts:

- **Excel Manipulation**: Learn how to open, edit, and save Excel files like deciphering ancient scripts.
- **Browser Sorcery**: Understand how to simulate browser interactions—Chrome’s magic wand is required!
- **Web Scraping**: Figure out how to target specific elements in the DOM without causing too much havoc.
- **Asynchronous Alchemy**: Master the art of handling asynchronous responses. For now, we’re using a magical “sleep” spell, 
but there are probably more elegant ways to handle this.

## The Chrome Driver Conundrum

Ah, the Chrome driver—essential for our browser-based escapades! It’s a bit like finding the right key to open a magical 
door, but with a dash of compatibility woes. We’re using Selenium for its dynamic prowess, unlike other static libraries 
that only fetch the initial HTML (which is great for scraping titles but less so for fancy JavaScript-loaded content).

## Apache POI: Our Trusty Companion

Apache POI is like the trusty steed that helps us tame Excel files. It’s quite intuitive: you get to work with rows and 
cells as if you’re arranging an epic banquet—create new rows, add cells, and with a sprinkle of code magic, save it all 
into a new file output stream.

## The Async Riddle

For now, I am keeping it simple with a “sleep” spell to handle the asynchronous nature of web scraping. But beware, 
there are more elegant solutions out there! 

## The Name Game

Here’s the kicker: item names on Worten and CEX don’t always match up. It’s like trying to find a needle in a haystack 
of different-sized needles. The CEX search might not be the sharpest tool in the shed, and sometimes our expertly 
extracted Worten names lead to items that are about as related as cats and dogs (maybe CEX is equipped with bing?). A 
smarter search function would be a noble quest for another time!

## The Multithreading Quest

To supercharge the scraping prowess, I could dive into the realm of multithreading. By deploying multiple threads 
simultaneously, we can perform several product searches in parallel. This will drastically cut down our execution time, 
transforming our script from a single-threaded tortoise into a lightning-fast data-harvesting Dodge Viper (AOE ref).

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