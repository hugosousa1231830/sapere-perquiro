package excelExtract;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WRTExcelSource {
    /**
     * Retrieves a list of product names from an Excel file based on a list of store names.
     *
     * @param stores    A list of store names to filter the products.
     * @param itemTypes A list of item types to filter the products.
     * @param filepath  The path to the Excel file.
     * @return A list of items corresponding to the given stores and item types.
     * @throws RuntimeException If an I/O error occurs during file operations.
     */
    public static List<Item> getSellPrices(List<String> stores, List<String> itemTypes, String filepath) {
        try {
            File file = new File(filepath);
            FileInputStream excelFile = new FileInputStream(file);
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet sheet = workbook.getSheetAt(0);

            ArrayList<Item> productList = new ArrayList<>();

            int itemCount = 0;

            for (Row row : sheet) {
                // Retrieve cells
                Cell storeCell = row.getCell(2);
                Cell itemTypeCell = row.getCell(4);
                Cell itemNameCell = row.getCell(6);
                Cell sellPriceCell = row.getCell(10);
                Cell eanCell = row.getCell(5);

                // Check if any cell is null
                if (storeCell == null || itemTypeCell == null || itemNameCell == null || sellPriceCell == null || eanCell == null) {
                    continue; // Skip this row if any of the required cells are null
                }

                // Get cell values with appropriate type handling
                String store = getCellStringValue(storeCell);
                String itemType = getCellStringValue(itemTypeCell);

                if (stores.contains(store) && itemTypes.contains(itemType)) {
                    Item item = new Item();
                    item.setWrtStore(store);
                    item.setWrtItemName(getCellStringValue(itemNameCell));
                    item.setWrtSellPrice(getCellNumericValue(sellPriceCell));
                    item.setWrtEAN(getCellNumericValue(eanCell));
                    productList.add(item);
                    itemCount++;
                }
            }

            workbook.close();
            excelFile.close();
            System.out.println("Found " + itemCount + " items.");
            return productList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves the string value from a cell, handling different cell types.
     *
     * @param cell The cell to read.
     * @return The string value of the cell.
     */
    private static String getCellStringValue(Cell cell) {
        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case FORMULA:
                // Evaluate the formula to get the string value
                FormulaEvaluator evaluator = cell.getSheet().getWorkbook().getCreationHelper().createFormulaEvaluator();
                CellValue cellValue = evaluator.evaluate(cell);
                if (cellValue.getCellType() == CellType.STRING) {
                    return cellValue.getStringValue();
                } else {
                    return String.valueOf(cellValue.getNumberValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case ERROR:
                return "Error";
            default:
                return "";
        }
    }

    /**
     * Retrieves the numeric value from a cell, handling different cell types.
     *
     * @param cell The cell to read.
     * @return The numeric value of the cell as a string.
     */
    private static String getCellNumericValue(Cell cell) {
        if (cell == null) {
            return "0";
        }

        switch (cell.getCellType()) {
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case STRING:
                try {
                    return String.valueOf(Double.parseDouble(cell.getStringCellValue()));
                } catch (NumberFormatException e) {
                    return "0"; // Handle non-numeric strings
                }
            case FORMULA:
                // Evaluate the formula to get the numeric value
                FormulaEvaluator evaluator = cell.getSheet().getWorkbook().getCreationHelper().createFormulaEvaluator();
                CellValue cellValue = evaluator.evaluate(cell);
                if (cellValue.getCellType() == CellType.NUMERIC) {
                    return String.valueOf(cellValue.getNumberValue());
                } else {
                    return "0";
                }
            case BOOLEAN:
                return "0";
            case ERROR:
                return "0";
            default:
                return "0";
        }
    }
}
