package excelExtract;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WRTExcelSource {
    /**
     * Retrieves a list of product names from an Excel file based on a list of store names. This method assumes the
     * workable sheet on the Excel file is the first and that the cells with "magic numbers" are correct (they are...)
     * <p>
     * This method reads an Excel file specified by the instance's file path, iterates over the rows
     * of the first sheet, and collects product names from column index 6 for the rows where the store
     * name in column index 2 matches one of the provided store names.
     * </p>
     *
     * @param stores A list of store names to filter the products.
     * @return A list of product names corresponding to the given stores.
     * @throws RuntimeException If an I/O error occurs during file operations.
     */
    public static List<Item> getSellPrices(List<String> stores, String filepath) {
        try {
            File file = new File(filepath);
            FileInputStream excelFile = new FileInputStream(file);
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet sheet = workbook.getSheetAt(0);

            ArrayList<Item> productList = new ArrayList<>();

            for (Row row : sheet) {
                String store = row.getCell(2).getStringCellValue();
                if (stores.contains(store)) {
                    Item item = new Item();
                    item.setWrtStore(store);
                    item.setWrtItemName(row.getCell(6).getStringCellValue());
                    item.setWrtSellPrice(String.valueOf(row.getCell(10).getNumericCellValue()));
                    item.setWrtEAN(String.valueOf((int)row.getCell(5).getNumericCellValue()));
                    productList.add(item);
                }
            }
            workbook.close();
            excelFile.close();
            return productList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}