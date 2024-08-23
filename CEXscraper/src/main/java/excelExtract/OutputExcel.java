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

    public static void toExcel(List<Item> itemList) throws IOException {
        try (Workbook outputBook = new XSSFWorkbook()) {
            Sheet outputSheet = outputBook.createSheet("FilteredData");

            int outputRowNumber = 0;
            for (Item item : itemList) {

                if (Double.parseDouble(item.getCexBuyPrice()) < Double.parseDouble(item.getWrtSellPrice())) {
                    continue;
                }

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
                String ean = item.getWrtEAN(); // this gives me a float point number, thats why I use replace below
                outputWrtLink.setCellFormula("HYPERLINK(\"https://www.worten.pt/search?query=" + ean.replace(".0","") + "\", \"Click Here\")");

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
