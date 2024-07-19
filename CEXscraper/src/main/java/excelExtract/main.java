package excelExtract;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main (String[] args) throws IOException {
        ArrayList<String> stores = new ArrayList<>();
//        stores.add("WRT PINHAL NOVO");
        stores.add("WRT MOZELOS");
        String path = "CEXscraper/07. Listagem Descontinuados 1 a 31 de Julho 2024_wtalk.xlsx";

        List<Item> itemList = WRTExcelSource.getSellPrices(stores,path);
        List<Item> updatedItemList = CEXScraper.getCEXinfo(itemList);
        OutputExcel.toExcel(updatedItemList);
    }
}
