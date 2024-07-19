package excelExtract;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;

public class CEXScraper {


    public static List<Item> getCEXinfo(List<Item> itemList) {

        /* This opens up the browser, using the driver specified on the property bit. Had to disable CORS as I am
        interacting with resources that are on a different origin. Ideally this should be done within its own class.
         */
        System.setProperty("webdriver.chrome.driver", "CEXscraper/chromedriver-win64/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);

        // Creates an empty list to be returned after scraping
        ArrayList<Item> scraperResult = new ArrayList<>();


        for (Item item : itemList) {
            try {
                // Utilizes the private function to construct the URL, specific to CEX, that searches by name
                String searchURL = composeProductURL(item.getWrtItemName());
                driver.get(searchURL);

                // This will can be solved more elegantly with proper use of async strategies (later...)
                Thread.sleep(2000);

                /*
                Gets the item name (exactly as it is on the site) and its price. Creates CEXitem object and adds to the
                return list
                 */
                String itemName = driver.findElement(By.className("line-clamp")).getText();
                System.out.println(itemName);

                /*
                The following class always has "DINHEIRO XX". getText gets me the full thing so I have to discard
                anything before index 10 to only extract the numbers (this may be helpful to cast to double later). I
                could scrape using Xpath but it seems too complicated and requires lots of extra steps, this is cleaner.
                */
                String price = driver.findElement(By.className("cash-price")).getText().substring(10).trim();
                System.out.println(price);

                item.setCexItemName(itemName);
                item.setCexBuyPrice(price);
                scraperResult.add(item);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        driver.quit();
        return scraperResult;
    }

    // CEX search always substitutes spaces with %20, therefore...
    private static String composeProductURL(String product){
        return "https://pt.webuy.com/sell/search?stext=" + product.replaceAll(" ", "%20");
    }
}
