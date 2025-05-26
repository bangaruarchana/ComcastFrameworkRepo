package generic.fileUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {

	public String getDataFromExcel(String sheetName , int rowNum ,int cellNum) throws Exception {
		FileInputStream fis= new FileInputStream("./testScriptData/TestScriptdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String data = wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum).getStringCellValue();
		
		return data;
	}
	
	public int getRowCount(String sheetName) throws Exception {
		FileInputStream fis= new FileInputStream("./testScriptData/TestScriptdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		int rowCount = wb.getSheet(sheetName).getLastRowNum();
		wb.close();
		return rowCount;
	}
	
	public void setDataIntoExcel(String sheetName , int rowNum , int cellNum , String Data) throws Exception {
		FileInputStream fis= new FileInputStream("./testScriptData/TestScriptdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		wb.getSheet(sheetName).getRow(rowNum).createCell(cellNum);
		
		FileOutputStream fos= new FileOutputStream("./testScriptData/TestScriptdata.xlsx");
		wb.write(fos);
		wb.close();
		
	}
}
