package genericUtility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {

    public String getDataByTestId(String sheetName, String testId, String columnName) throws IOException {
        FileInputStream file = new FileInputStream("./src/test/resources/VtigerTestData.xlsx");
        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheet(sheetName);

        if (sheet == null) {
            workbook.close();
            file.close();
            throw new IOException("Sheet not found: " + sheetName);
        }

        DataFormatter formatter = new DataFormatter();
        Row header = sheet.getRow(0);

        if (header == null) {
            workbook.close();
            file.close();
            throw new IOException("Header row not found in Excel");
        }

        int idColumn = -1;
        int dataColumn = -1;

        for (int i = 0; i < header.getLastCellNum(); i++) {

            String headerName =
                    formatter.formatCellValue(header.getCell(i)).trim();

            if (headerName.equalsIgnoreCase("TC_ID")) {
                idColumn = i;
            }

            if (headerName.equalsIgnoreCase(columnName)) {
                dataColumn = i;
            }
        }

        if (idColumn == -1) {
            workbook.close();
            file.close();
            throw new IOException("TC_ID column not found in Excel");
        }

        if (dataColumn == -1) {
            workbook.close();
            file.close();
            throw new IOException(
                    "Column not found in Excel: " + columnName
            );
        }

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {

            Row row = sheet.getRow(i);

            if (row == null) {
                continue;
            }

            String currentTestId =
                    formatter.formatCellValue(
                            row.getCell(idColumn)
                    ).trim();

            if (currentTestId.equalsIgnoreCase(testId)) {

                String value =
                        formatter.formatCellValue(
                                row.getCell(dataColumn)
                        ).trim();

                workbook.close();
                file.close();

                return value;
            }
        }

        workbook.close();
        file.close();

        throw new IOException(
                "Test ID not found in Excel: " + testId
        );
    }
}