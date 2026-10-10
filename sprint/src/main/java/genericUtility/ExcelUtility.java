package genericUtility;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

    private Workbook openWorkbook() throws IOException {
        Path sourceFile = Paths.get("src/test/resources/TestData.xlsx");
        if (Files.exists(sourceFile) && Files.size(sourceFile) > 0) {
            try (InputStream inputStream = Files.newInputStream(sourceFile)) {
                return new XSSFWorkbook(inputStream);
            }
        }
        InputStream resourceStream = getClass().getClassLoader().getResourceAsStream("TestData.xlsx");
        if (resourceStream == null) {
            throw new IOException("TestData.xlsx was not found in src/test/resources or the classpath");
        }
        try (InputStream inputStream = resourceStream) {
            return new XSSFWorkbook(inputStream);
        }
    }

    public String getDataByTestId(String sheetName,String testId,String columnName) throws IOException {
        try (Workbook workbook = openWorkbook()) {
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IOException("Sheet not found: " + sheetName);
            }
            DataFormatter formatter = new DataFormatter();
            Row header = sheet.getRow(0);
            
            if (header == null) {
                throw new IOException("Header row is missing in sheet: " + sheetName);
            }
            
            int idColumn = findColumn(header, "TC_ID", formatter);
            int dataColumn = findColumn(header, columnName, formatter);

            if (idColumn < 0) {
                throw new IOException("TC_ID column not found in " + sheetName);
            }
            
            if (dataColumn < 0) {
                throw new IOException("Column '" + columnName + "' not found in " + sheetName);
            }
            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row == null) {
                    continue;
                }
                String currentTestId =formatter.formatCellValue(row.getCell(idColumn)).trim();
                if (testId.equalsIgnoreCase(currentTestId)) {
                    return formatter.formatCellValue(row.getCell(dataColumn)).trim();
                }
            }
            throw new IOException("Test ID '" + testId + "' not found in " + sheetName);
        }
    }
    private int findColumn(Row row,String columnName,DataFormatter formatter) {
        if (row == null) {
            return -1;
        }
        for (int columnIndex = 0;columnIndex < row.getLastCellNum();columnIndex++) {
            Cell cell = row.getCell(columnIndex);
            if (cell == null) {
                continue;
            }
            String header = formatter.formatCellValue(cell).trim();
            if (header.equalsIgnoreCase(columnName.trim())) {
                return columnIndex;
            }
        }
        return -1;
    }
}
