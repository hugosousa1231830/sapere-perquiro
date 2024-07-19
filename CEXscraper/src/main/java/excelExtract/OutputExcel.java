package excelExtract;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class OutputExcel {

    /**
     * Exports a list of items to an Excel file named "FilteredData.xlsx".
     * Each item is written to a new row in the sheet "FilteredData".
     *
     * <p>The following columns are created in the Excel sheet:</p>
     * <ul>
     *   <li>Column 0: WRT Item Name</li>
     *   <li>Column 1: CEX Item Name</li>
     *   <li>Column 2: WRT Sell Price</li>
     *   <li>Column 3: CEX Buy Price</li>
     *   <li>Column 4: Hyperlink to WRT item using EAN</li>
     *   <li>Column 5: WRT Store</li>
     * </ul>
     *
     * @param itemList the list of items to be written to the Excel file
     * @throws IOException if an I/O error occurs during file operations
     */
    public static void toExcel(List<Item> itemList) throws IOException {
        try (Workbook outputBook = new XSSFWorkbook()) {
            Sheet outputSheet = outputBook.createSheet("FilteredData");

            int outputRowNumber = 0;
            for (Item item : itemList) {
                Row outputRow = outputSheet.createRow(outputRowNumber++);

                Cell outputWrtName = outputRow.createCell(0);
                outputWrtName.setCellValue(item.getWrtItemName());

                Cell outputCEXName = outputRow.createCell(1);
                outputCEXName.setCellValue(item.getCexItemName());

                Cell outputWrtPrice = outputRow.createCell(2);
                outputWrtPrice.setCellValue(item.getWrtSellPrice());

                Cell outputCEXPrice = outputRow.createCell(3);
                outputCEXPrice.setCellValue(item.getCexBuyPrice());

                Cell outputWrtLink = outputRow.createCell(4);
                String ean = item.getWrtEAN();
                outputWrtLink.setCellFormula("HYPERLINK(\"https://www.worten.pt/search?query=" + ean + "\", \"Click Here\")");

                Cell outputStore = outputRow.createCell(5);
                outputStore.setCellValue(item.getWrtStore());
            }

            try (FileOutputStream fileOut = new FileOutputStream("FilteredData.xlsx")) {
                outputBook.write(fileOut);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
