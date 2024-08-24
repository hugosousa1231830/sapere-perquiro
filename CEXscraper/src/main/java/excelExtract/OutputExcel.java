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

    public static void toExcel(List<Item> itemList) {
        try (Workbook outputBook = new XSSFWorkbook()) {
            Sheet outputSheet = outputBook.createSheet("FilteredData");

            // Create the header row
            Row headerRow = outputSheet.createRow(0);

            Cell headerWrtName = headerRow.createCell(0);
            headerWrtName.setCellValue("WrtName");

            Cell headerCEXName = headerRow.createCell(1);
            headerCEXName.setCellValue("CexName");

            Cell headerWrtPrice = headerRow.createCell(2);
            headerWrtPrice.setCellValue("WRTprice");

            Cell headerCEXPrice = headerRow.createCell(3);
            headerCEXPrice.setCellValue("CEXprice");

            Cell headerWrtLink = headerRow.createCell(4);
            headerWrtLink.setCellValue("WrtLink");

            Cell headerCexLink = headerRow.createCell(5);
            headerCexLink.setCellValue("CexLink");

            Cell headerStore = headerRow.createCell(6);
            headerStore.setCellValue("Location");

            // Start filling data from row 1
            int outputRowNumber = 1;
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
                String ean = item.getWrtEAN(); // this gives me a float point number, that's why I use replace below
                outputWrtLink.setCellFormula("HYPERLINK(\"https://www.worten.pt/search?query=" + ean.replace(".0","") + "\", \"link\")");

                Cell outputCexLink = outputRow.createCell(5);
                String cexUrl = item.getCexURL();
                outputCexLink.setCellFormula("HYPERLINK(\"" + cexUrl + "\", \"link\")");

                Cell outputStore = outputRow.createCell(6);
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
