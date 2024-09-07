package excelExtract;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

public class OutputExcel {

    public static void toExcel(List<Item> itemList) {
        try (Workbook outputBook = new XSSFWorkbook()) {
            Sheet outputSheet = outputBook.createSheet("FilteredData");

            // Create the header row
            Row headerRow = outputSheet.createRow(0);
            String[] headers = {"WrtName", "CexName", "WRTprice", "CEXprice", "WrtLink", "CexLink", "Location"};
            for (int i = 0; i < headers.length; i++) {
                Cell headerCell = headerRow.createCell(i);
                headerCell.setCellValue(headers[i]);
            }

            // Start filling data from row 1
            int outputRowNumber = 1;
            for (Item item : itemList) {
                try {
                    double cexPrice = parsePrice(item.getCexBuyPrice());
                    double wrtPrice = parsePrice(item.getWrtSellPrice());

                    if (cexPrice < wrtPrice) {
                        continue;
                    }

                    Row outputRow = outputSheet.createRow(outputRowNumber++);

                    outputRow.createCell(0).setCellValue(item.getWrtItemName());
                    outputRow.createCell(1).setCellValue(item.getCexItemName());
                    outputRow.createCell(2).setCellValue(wrtPrice);
                    outputRow.createCell(3).setCellValue(cexPrice);

                    String ean = item.getWrtEAN().replace(".0", "");
                    outputRow.createCell(4).setCellFormula(createHyperlinkFormula("https://www.worten.pt/search?query=" + ean));

                    String cexUrl = item.getCexURL();
                    outputRow.createCell(5).setCellFormula(createHyperlinkFormula(cexUrl));

                    outputRow.createCell(6).setCellValue(item.getWrtStore());
                } catch (ParseException e) {
                    // Log or handle the exception
                    e.printStackTrace();
                }
            }

            try (FileOutputStream fileOut = new FileOutputStream("FilteredData.xlsx")) {
                outputBook.write(fileOut);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static double parsePrice(String price) throws ParseException {
        NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
        Number number = format.parse(price);
        return number.doubleValue();
    }

    private static String createHyperlinkFormula(String url) {
        String escapedUrl = url.replace("\"", "\"\"");
        return "HYPERLINK(\"" + escapedUrl + "\", \"" + "link" + "\")";
    }
}
