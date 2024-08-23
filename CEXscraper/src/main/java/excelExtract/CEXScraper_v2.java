package excelExtract;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CEXScraper_v2 {

    /**
     * Fetches information for a list of items from the CEX website.
     *
     * @param itemList The list of items to fetch information for.
     * @return A list of items with updated information from the CEX website.
     */
    public static List<Item> getCEXinfo(List<Item> itemList) {
        System.setProperty("webdriver.chrome.driver", "CEXscraper/chromedriver-win64/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);

        List<Item> scraperResult = new ArrayList<>();

        int totalItems = itemList.size();
        int progressInterval = Math.max(totalItems / 10, 1);

        int processedItems = 0;

        try {
            for (Item item : itemList) {
                try {
                    // Sequentially scrape information for each item
                    Item result = scrapeItemInfo(driver, item);
                    scraperResult.add(result);
                } catch (Exception e) {
                    System.err.println("Error processing item: " + item.getWrtItemName());
                    e.printStackTrace();
                }

                processedItems++;
                if (processedItems % progressInterval == 0 || processedItems == totalItems) {
                    System.out.println("Progress: " + (processedItems * 100 / totalItems) + "%");
                }
            }
        } finally {
            driver.quit();
        }

        return scraperResult;
    }

    /**
     * Scrapes information for a single item from the CEX website.
     *
     * @param driver The WebDriver used to navigate and scrape the website.
     * @param item   The item to scrape information for.
     * @return The item with updated information from the CEX website.
     */
    private static Item scrapeItemInfo(WebDriver driver, Item item) {
        try {
            String searchURL = composeProductURL(item.getWrtItemName());
            driver.get(searchURL);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement itemNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("line-clamp")));
            String itemName = itemNameElement.getText();

            WebElement priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cash-price")));
            String price = priceElement.getText().substring(10).trim();

            item.setCexItemName(itemName);
            item.setCexBuyPrice(price);
            item.setCexURL(searchURL);
        } catch (Exception e) {
            System.err.println("Error scraping item: " + item.getWrtItemName());
            e.printStackTrace();
            item.setCexItemName("Error");
            item.setCexBuyPrice("N/A");
        }

        return item;
    }

    /**
     * Composes the URL for searching a product on the CEX website.
     *
     * @param product The product name to search for.
     * @return The composed URL.
     */
    private static String composeProductURL(String product) {
        return "https://pt.webuy.com/sell/search?stext=" + product.replaceAll(" ", "%20");
    }
}
