package genericUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {

    // Login valid data
    public Object[][] ValidCredentials() throws EncryptedDocumentException, IOException {
        return getLoginData("ValidData");
    }

    // Login invalid data
    public Object[][] InvalidCredentials() throws EncryptedDocumentException, IOException {
        return getLoginData("InvalidData");
    }

    // Blank username and password data
    public Object[][] BlankLoginData() throws EncryptedDocumentException, IOException {
        return getLoginData("BlankData");
    }

    // Boundary login data
    public Object[][] BoundaryLoginData() throws EncryptedDocumentException, IOException {
        return getLoginData("BoundaryData");
    }

    // Product names from Excel
    public Object[] ProductNames() throws EncryptedDocumentException, IOException {
        FileInputStream fis = new FileInputStream("./src/test/resources/Project.xlsx");
        Workbook wb = WorkbookFactory.create(fis);
        Sheet sh = wb.getSheet("Sheet2");
        DataFormatter formatter = new DataFormatter();
        ArrayList<String> products = new ArrayList<>();

        for (int i = 1; i <= sh.getLastRowNum(); i++) {
            Row row = sh.getRow(i);

            if (row == null || row.getCell(0) == null) {
                continue;
            }

            String product = formatter.formatCellValue(row.getCell(0)).trim();

            if (!product.isEmpty()) {
                products.add(product);
            }
        }

        wb.close();
        fis.close();

        return products.toArray();
    }

    // Positive checkout data
    public Object[][] CheckoutPositiveData() throws EncryptedDocumentException, IOException {
        return getCheckoutData("Positive Test Cases");
    }

    // Negative checkout data
    public Object[][] CheckoutNegativeData() throws EncryptedDocumentException, IOException {
        return getCheckoutData("Negative Test Cases");
    }

    // Boundary checkout data
    public Object[][] CheckoutBoundaryData() throws EncryptedDocumentException, IOException {
        return getCheckoutData("Boundary Test Cases");
    }

    // Blank first name data
    public Object[][] CheckoutBlankFirstNameData() throws EncryptedDocumentException, IOException {
        FileInputStream fis = new FileInputStream("./src/test/resources/Checkout_YourInfo_TestData.xlsx");
        Workbook wb = WorkbookFactory.create(fis);
        Sheet sh = wb.getSheet("Negative Test Cases");
        DataFormatter formatter = new DataFormatter();
        ArrayList<Object[]> data = new ArrayList<>();

        for (int i = 1; i <= sh.getLastRowNum(); i++) {
            Row row = sh.getRow(i);

            if (row == null) {
                continue;
            }

            String firstName = getCellValue(formatter, row, 0);
            String lastName = getCellValue(formatter, row, 1);
            String postalCode = getCellValue(formatter, row, 2);

            if (firstName.isEmpty() && lastName.isEmpty() && postalCode.isEmpty()) {
                continue;
            }

            if (firstName.isEmpty() && !lastName.isEmpty() && !postalCode.isEmpty()) {
                data.add(new Object[] { firstName, lastName, postalCode });
            }
        }

        wb.close();
        fis.close();

        return data.toArray(new Object[0][3]);
    }

    // Blank last name data
    public Object[][] CheckoutBlankLastNameData() throws EncryptedDocumentException, IOException {
        FileInputStream fis = new FileInputStream("./src/test/resources/Checkout_YourInfo_TestData.xlsx");
        Workbook wb = WorkbookFactory.create(fis);
        Sheet sh = wb.getSheet("Negative Test Cases");
        DataFormatter formatter = new DataFormatter();
        ArrayList<Object[]> data = new ArrayList<>();

        for (int i = 1; i <= sh.getLastRowNum(); i++) {
            Row row = sh.getRow(i);

            if (row == null) {
                continue;
            }

            String firstName = getCellValue(formatter, row, 0);
            String lastName = getCellValue(formatter, row, 1);
            String postalCode = getCellValue(formatter, row, 2);

            if (firstName.isEmpty() && lastName.isEmpty() && postalCode.isEmpty()) {
                continue;
            }

            if (!firstName.isEmpty() && lastName.isEmpty() && !postalCode.isEmpty()) {
                data.add(new Object[] { firstName, lastName, postalCode });
            }
        }

        wb.close();
        fis.close();

        return data.toArray(new Object[0][3]);
    }

    // Invalid postal code data
    public Object[][] CheckoutInvalidPostalCodeData() throws EncryptedDocumentException, IOException {
        FileInputStream fis = new FileInputStream("./src/test/resources/Checkout_YourInfo_TestData.xlsx");
        Workbook wb = WorkbookFactory.create(fis);
        Sheet sh = wb.getSheet("Negative Test Cases");
        DataFormatter formatter = new DataFormatter();
        ArrayList<Object[]> data = new ArrayList<>();

        for (int i = 1; i <= sh.getLastRowNum(); i++) {
            Row row = sh.getRow(i);

            if (row == null) {
                continue;
            }

            String firstName = getCellValue(formatter, row, 0);
            String lastName = getCellValue(formatter, row, 1);
            String postalCode = getCellValue(formatter, row, 2);

            if (firstName.isEmpty() && lastName.isEmpty() && postalCode.isEmpty()) {
                continue;
            }

            if (!postalCode.matches("\\d{6}")) {
                data.add(new Object[] { firstName, lastName, postalCode });
            }
        }

        wb.close();
        fis.close();

        return data.toArray(new Object[0][3]);
    }

    // This method reads login data from Excel
    private Object[][] getLoginData(String sheetName) throws EncryptedDocumentException, IOException {
        FileInputStream fis = new FileInputStream("./src/test/resources/LoginValidInvalidCreditional.xlsx");
        Workbook wb = WorkbookFactory.create(fis);
        Sheet sh = wb.getSheet(sheetName);
        DataFormatter formatter = new DataFormatter();
        ArrayList<Object[]> data = new ArrayList<>();

        for (int i = 1; i <= sh.getLastRowNum(); i++) {
            Row row = sh.getRow(i);

            if (row == null) {
                continue;
            }

            String username = getCellValue(formatter, row, 0);
            String password = getCellValue(formatter, row, 1);

            if (username.isEmpty() && password.isEmpty()) {
                continue;
            }

            data.add(new Object[] { username, password });
        }

        wb.close();
        fis.close();

        return data.toArray(new Object[0][2]);
    }

    // This method reads checkout data from Excel
    private Object[][] getCheckoutData(String sheetName) throws EncryptedDocumentException, IOException {
        FileInputStream fis = new FileInputStream("./src/test/resources/Checkout_YourInfo_TestData.xlsx");
        Workbook wb = WorkbookFactory.create(fis);
        Sheet sh = wb.getSheet(sheetName);
        DataFormatter formatter = new DataFormatter();
        ArrayList<Object[]> data = new ArrayList<>();

        for (int i = 1; i <= sh.getLastRowNum(); i++) {
            Row row = sh.getRow(i);

            if (row == null) {
                continue;
            }

            String firstName = getCellValue(formatter, row, 0);
            String lastName = getCellValue(formatter, row, 1);
            String postalCode = getCellValue(formatter, row, 2);

            if (firstName.isEmpty() && lastName.isEmpty() && postalCode.isEmpty()) {
                continue;
            }

            data.add(new Object[] { firstName, lastName, postalCode });
        }

        wb.close();
        fis.close();

        return data.toArray(new Object[0][3]);
    }

    // Get cell value safely from Excel
    private String getCellValue(DataFormatter formatter, Row row, int column) {
        if (row.getCell(column) == null) {
            return "";
        }

        return formatter.formatCellValue(row.getCell(column)).trim();
    }
}