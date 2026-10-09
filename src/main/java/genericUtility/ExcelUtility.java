package genericUtility;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.DataFormatter;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	public Object[][] ValidCredentials() throws EncryptedDocumentException, IOException{
		FileInputStream fis = new FileInputStream("./src/test/resources/LoginValidInvalidCreditional.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("ValidData");
		Object[][] ob = new Object[sh.getLastRowNum()][2];
		for(int i=0;i<sh.getLastRowNum();i++) {
			for(int j=0;j<2;j++) {
				ob[i][j]=sh.getRow(i+1).getCell(j).getStringCellValue();
			}
		}
		return ob;
	}
	public Object[][] InvalidCredentials()
	        throws EncryptedDocumentException, IOException {

	    FileInputStream fis =
	            new FileInputStream(
	                    "./src/test/resources/LoginValidInvalidCreditional.xlsx");

	    Workbook wb = WorkbookFactory.create(fis);

	    Sheet sh = wb.getSheet("InvalidData");

	    DataFormatter formatter = new DataFormatter();

	    ArrayList<Object[]> data = new ArrayList<>();

	    for (int i = 1; i <= sh.getLastRowNum(); i++) {

	        Row row = sh.getRow(i);

	        if (row == null) {
	            continue;
	        }

	        String username =
	                formatter.formatCellValue(row.getCell(0)).trim();

	        String password =
	                formatter.formatCellValue(row.getCell(1)).trim();

	        // Skip completely empty rows
	        if (username.isEmpty() && password.isEmpty()) {
	            continue;
	        }

	        data.add(new Object[] {
	                username,
	                password
	        });
	    }

	    wb.close();
	    fis.close();

	    return data.toArray(new Object[0][2]);
	}
	public Object[][] BlankLoginData()
	        throws EncryptedDocumentException, IOException {

	    FileInputStream fis =
	            new FileInputStream(
	                    "./src/test/resources/LoginValidInvalidCreditional.xlsx");

	    Workbook wb = WorkbookFactory.create(fis);

	    Sheet sh = wb.getSheet("BlankData");

	    DataFormatter formatter = new DataFormatter();

	    ArrayList<Object[]> data = new ArrayList<>();

	    for (int i = 1; i <= sh.getLastRowNum(); i++) {

	        Row row = sh.getRow(i);

	        if (row == null) {
	            continue;
	        }

	        String username =
	                formatter.formatCellValue(row.getCell(0)).trim();

	        String password =
	                formatter.formatCellValue(row.getCell(1)).trim();

	        // Skip completely empty rows
	        if (username.isEmpty() && password.isEmpty()) {
	            continue;
	        }

	        data.add(new Object[] {
	                username,
	                password
	        });
	    }

	    wb.close();
	    fis.close();

	    return data.toArray(new Object[0][2]);
	}

	public Object[][] BoundaryLoginData()
	        throws EncryptedDocumentException, IOException {

	    FileInputStream fis =
	            new FileInputStream(
	                    "./src/test/resources/LoginValidInvalidCreditional.xlsx");

	    Workbook wb = WorkbookFactory.create(fis);

	    Sheet sh = wb.getSheet("BoundaryData");

	    DataFormatter formatter = new DataFormatter();

	    Object[][] ob =
	            new Object[sh.getLastRowNum()][2];

	    for (int i = 0; i < sh.getLastRowNum(); i++) {

	        for (int j = 0; j < 2; j++) {

	            if (sh.getRow(i + 1) == null ||
	                sh.getRow(i + 1).getCell(j) == null) {

	                ob[i][j] = "";

	            } else {

	                ob[i][j] =
	                        formatter.formatCellValue(
	                                sh.getRow(i + 1).getCell(j));
	            }
	        }
	    }

	    wb.close();
	    fis.close();

	    return ob;
	}
	
	public Object[] ProductNames() throws EncryptedDocumentException, IOException {

	    FileInputStream fis = new FileInputStream("./src/test/resources/Project.xlsx");

	    Workbook wb = WorkbookFactory.create(fis);

	    Sheet sh = wb.getSheet("Sheet2");

	    ArrayList<String> products = new ArrayList<>();

	    for (int i = 1; i <= sh.getLastRowNum(); i++) {

	        Row row = sh.getRow(i);

	        if (row != null && row.getCell(0) != null) {
	            products.add(row.getCell(0).getStringCellValue());
	        }
	    }

	    return products.toArray();
	}
	
	public Object[][] CheckoutPositiveData()
	        throws EncryptedDocumentException, IOException {

	    FileInputStream fis =
	            new FileInputStream(
	                    "./src/test/resources/Checkout_YourInfo_TestData.xlsx");

	    Workbook wb = WorkbookFactory.create(fis);

	    Sheet sh = wb.getSheet("Positive Test Cases");

	    DataFormatter formatter = new DataFormatter();

	    ArrayList<Object[]> data = new ArrayList<>();

	    for (int i = 1; i <= sh.getLastRowNum(); i++) {

	        Row row = sh.getRow(i);

	        if (row == null) {
	            continue;
	        }

	        String firstName =
	                formatter.formatCellValue(row.getCell(0));

	        String lastName =
	                formatter.formatCellValue(row.getCell(1));

	        String postalCode =
	                formatter.formatCellValue(row.getCell(2));

	        // Skip completely empty rows
	        if (firstName.trim().isEmpty()
	                && lastName.trim().isEmpty()
	                && postalCode.trim().isEmpty()) {
	            continue;
	        }

	        data.add(new Object[] {
	                firstName,
	                lastName,
	                postalCode
	        });
	    }

	    wb.close();
	    fis.close();

	    return data.toArray(new Object[0][3]);
	}
	public Object[][] CheckoutNegativeData()
	        throws EncryptedDocumentException, IOException {

	    FileInputStream fis =
	            new FileInputStream(
	                    "./src/test/resources/Checkout_YourInfo_TestData.xlsx");

	    Workbook wb = WorkbookFactory.create(fis);

	    Sheet sh = wb.getSheet("Negative Test Cases");

	    DataFormatter formatter = new DataFormatter();

	    Object[][] ob =
	            new Object[sh.getLastRowNum()][3];

	    for (int i = 0; i < sh.getLastRowNum(); i++) {

	        for (int j = 0; j < 3; j++) {

	            if (sh.getRow(i + 1) == null ||
	                sh.getRow(i + 1).getCell(j) == null) {

	                ob[i][j] = "";

	            } else {

	                ob[i][j] =
	                        formatter.formatCellValue(
	                                sh.getRow(i + 1).getCell(j));
	            }
	        }
	    }

	    wb.close();
	    fis.close();

	    return ob;
	}
	public Object[][] CheckoutBlankFirstNameData()
	        throws EncryptedDocumentException, IOException {

	    FileInputStream fis =
	            new FileInputStream(
	                    "./src/test/resources/Checkout_YourInfo_TestData.xlsx");

	    Workbook wb = WorkbookFactory.create(fis);

	    Sheet sh = wb.getSheet("Negative Test Cases");

	    DataFormatter formatter = new DataFormatter();

	    ArrayList<Object[]> data = new ArrayList<>();

	    for (int i = 1; i <= sh.getLastRowNum(); i++) {

	        Row row = sh.getRow(i);

	        if (row == null) {
	            continue;
	        }

	        String firstName =
	                formatter.formatCellValue(row.getCell(0));

	        String lastName =
	                formatter.formatCellValue(row.getCell(1));

	        String postalCode =
	                formatter.formatCellValue(row.getCell(2));

	        if (firstName.trim().isEmpty()) {
	            data.add(new Object[] {
	                    firstName,
	                    lastName,
	                    postalCode
	            });
	        }
	    }

	    wb.close();
	    fis.close();

	    return data.toArray(new Object[0][3]);
	}


	public Object[][] CheckoutBlankLastNameData()
	        throws EncryptedDocumentException, IOException {

	    FileInputStream fis =
	            new FileInputStream(
	                    "./src/test/resources/Checkout_YourInfo_TestData.xlsx");

	    Workbook wb = WorkbookFactory.create(fis);

	    Sheet sh = wb.getSheet("Negative Test Cases");

	    DataFormatter formatter = new DataFormatter();

	    ArrayList<Object[]> data = new ArrayList<>();

	    for (int i = 1; i <= sh.getLastRowNum(); i++) {

	        Row row = sh.getRow(i);

	        if (row == null) {
	            continue;
	        }

	        String firstName =
	                formatter.formatCellValue(row.getCell(0));

	        String lastName =
	                formatter.formatCellValue(row.getCell(1));

	        String postalCode =
	                formatter.formatCellValue(row.getCell(2));

	        if (lastName.trim().isEmpty()) {
	            data.add(new Object[] {
	                    firstName,
	                    lastName,
	                    postalCode
	            });
	        }
	    }

	    wb.close();
	    fis.close();

	    return data.toArray(new Object[0][3]);
	}


	public Object[][] CheckoutInvalidPostalCodeData()
	        throws EncryptedDocumentException, IOException {

	    FileInputStream fis =
	            new FileInputStream(
	                    "./src/test/resources/Checkout_YourInfo_TestData.xlsx");

	    Workbook wb = WorkbookFactory.create(fis);

	    Sheet sh = wb.getSheet("Negative Test Cases");

	    DataFormatter formatter = new DataFormatter();

	    ArrayList<Object[]> data = new ArrayList<>();

	    for (int i = 1; i <= sh.getLastRowNum(); i++) {

	        Row row = sh.getRow(i);

	        if (row == null) {
	            continue;
	        }

	        String firstName =
	                formatter.formatCellValue(row.getCell(0));

	        String lastName =
	                formatter.formatCellValue(row.getCell(1));

	        String postalCode =
	                formatter.formatCellValue(row.getCell(2));

	        if (!postalCode.matches("\\d{6}")) {
	            data.add(new Object[] {
	                    firstName,
	                    lastName,
	                    postalCode
	            });
	        }
	    }

	    wb.close();
	    fis.close();

	    return data.toArray(new Object[0][3]);
	}
	public Object[][] CheckoutBoundaryData()
	        throws EncryptedDocumentException, IOException {

	    FileInputStream fis =
	            new FileInputStream(
	                    "./src/test/resources/Checkout_YourInfo_TestData.xlsx");

	    Workbook wb = WorkbookFactory.create(fis);

	    Sheet sh = wb.getSheet("Boundary Test Cases");

	    DataFormatter formatter = new DataFormatter();

	    Object[][] ob =
	            new Object[sh.getLastRowNum()][3];

	    for (int i = 0; i < sh.getLastRowNum(); i++) {

	        for (int j = 0; j < 3; j++) {

	            if (sh.getRow(i + 1) == null ||
	                sh.getRow(i + 1).getCell(j) == null) {

	                ob[i][j] = "";

	            } else {

	                ob[i][j] =
	                        formatter.formatCellValue(
	                                sh.getRow(i + 1).getCell(j));
	            }
	        }
	    }

	    wb.close();
	    fis.close();

	    return ob;
	}
	
}
