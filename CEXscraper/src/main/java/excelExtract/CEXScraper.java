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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CEXScraper {

    /**
     * Fetches information for a list of items from the CEX website.
     *
     * @param itemList The list of items to fetch information for.
     * @return A list of items with updated information from the CEX website.
     * @throws ExecutionException   If an error occurs while waiting for the computation to complete.
     * @throws InterruptedException If the current thread was interrupted while waiting.
     */
    public static List<Item> getCEXinfo(List<Item> itemList) throws ExecutionException, InterruptedException {

        System.setProperty("webdriver.chrome.driver", "CEXscraper/chromedriver-win64/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);

        // Fits we create an array that holds multiple future<item>, this will hold all cex query results
        List<CompletableFuture<Item>> futures = new ArrayList<>();

        for (Item item : itemList) {
            // This will create a future for each item, and add to the above List
            CompletableFuture<Item> future = CompletableFuture.supplyAsync(() -> scrapeItemInfo(driver, item));
            futures.add(future);
        }

        /* So considering we have a "futures" List with a bunch of futures waiting to be completed, we must create an
        overarching future object which will be fulfilled once all the futures within that List are finished. Once all
        the futures are done, thus making the overarching future also done, it will turn that list into a stream and
        call join() on each future to extract the resulting Item. Once it extracts all the Item objects, it turns them
        into a List using toList and uses .get() to obtain and return that list.
         */
        CompletableFuture<Void> listUnderway = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        List<Item> scraperResult = listUnderway
                .thenApply(placeholder -> futures.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList()))
                .get();

        driver.quit();
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
            System.out.println(itemName);

            WebElement priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cash-price")));
            String price = priceElement.getText().substring(10).trim();
            System.out.println(price);

            item.setCexItemName(itemName);
            item.setCexBuyPrice(price);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }

    // CEX search always substitutes spaces with %20, therefore...
    private static String composeProductURL(String product){
        return "https://pt.webuy.com/sell/search?stext=" + product.replaceAll(" ", "%20");
    }
}
