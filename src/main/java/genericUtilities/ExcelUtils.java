package genericUtilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils {
	
	
	/**
	 * This method is used to read single data from excel
	 * @author Divya C L
	 * @param Sheetname
	 * @param rowNum
	 * @param celNum
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public String readSingleDataFromExcel(String Sheetname, int rowNum, int celNum) throws EncryptedDocumentException, IOException {
		
		FileInputStream fis = new FileInputStream(IpathConstants.excelFilePath);
		Workbook wb = WorkbookFactory.create(fis);
		
		Sheet sh = wb.getSheet(Sheetname);
		String value = sh.getRow(rowNum).getCell(celNum).getStringCellValue();
		return value;
	}

	/**
	 * This method is used to read multiple data and store into list
	 * @param Sheetname
	 * @return 
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public ArrayList<String> readMultipleDataFromExcel(String Sheetname) throws EncryptedDocumentException, IOException {
		
		FileInputStream fis = new FileInputStream(IpathConstants.excelFilePath);
		Workbook wb = WorkbookFactory.create(fis);
		
		Sheet sh = wb.getSheet(Sheetname);
		int rowCount = sh.getLastRowNum();
		int cellCount = sh.getRow(0).getLastCellNum();
		
		ArrayList<String> list = new ArrayList<>();
		for(int i=0 ; i<=rowCount ; i++) {
			
			for(int j=0; j<cellCount ; j++) {
				
				String value = sh.getRow(i).getCell(j).getStringCellValue();
				list.add(value);
			}
		}
		
		return list;
	}
	
	/**
	 * This method is used to get number rows count
	 * @param Sheetname
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public int getRowCount(String Sheetname) throws EncryptedDocumentException, IOException {
		
		FileInputStream fis = new FileInputStream(IpathConstants.excelFilePath);
		Workbook wb = WorkbookFactory.create(fis);
		
		Sheet sh = wb.getSheet(Sheetname);
		int rowCount = sh.getLastRowNum();
		return rowCount;
		
	}
	
	/**
	 * This method is used to get number columns count
	 * @param Sheetname
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public int getCellCount(String Sheetname) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream(IpathConstants.excelFilePath);
		Workbook wb = WorkbookFactory.create(fis);
		
		Sheet sh = wb.getSheet(Sheetname);
		int cellCount = sh.getRow(0).getLastCellNum();
		return cellCount;
	}
	/**
	 * This method is used to write data into excel
	 * @param Sheetname
	 * @param celValue
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public void writeDataIntoExcel(String Sheetname, String celValue) throws EncryptedDocumentException, IOException {
		
		FileInputStream fis = new FileInputStream(IpathConstants.excelFilePath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(Sheetname);
		
		Row row = sh.createRow(0);
		Cell cell = row.createCell(0);
		cell.setCellType(CellType.STRING);
		cell.setCellValue(celValue);
		
		FileOutputStream fos = new FileOutputStream(IpathConstants.excelFilePath);
		wb.write(fos);
		wb.close();
		
	}
	
	public Object[][] readDataAndStoreToObjArray(String SheetName) throws EncryptedDocumentException, IOException {
		
		FileInputStream fis = new FileInputStream(IpathConstants.excelFilePath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(SheetName);
		int rowCount = sh.getLastRowNum()+1;
		//int rowCount = sh.getPhysicalNumberOfRows()	;
		int celCount = sh.getRow(0).getLastCellNum();
		
		Object[][] obj = new Object[rowCount][celCount];
		
		for(int i=0; i<rowCount; i++) {
			
			for(int j=0; j<celCount; j++) {
				obj[i][j] = sh.getRow(i).getCell(j).getStringCellValue();
			}
		}
		
		return obj;

		
	}
}
