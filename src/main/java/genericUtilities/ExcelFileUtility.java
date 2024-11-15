package genericUtilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * This class consists of generic methods related to Excel File
 * @author User
 */
public class ExcelFileUtility {
	
/**
 * 	
 * @param sheetName
 * @param rowNo
 * @param cellNo
 * @return
 * @throws EncryptedDocumentException
 * @throws IOException
 */
	
	public String readDataFromExcel(String sheetName, int rowNo, int cellNo) throws EncryptedDocumentException, IOException
	{
FileInputStream fis = new FileInputStream("C:\\Users\\User\\Desktop\\TestData.xlsx");		
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		Row rw = sh.getRow(rowNo);	
		Cell cl = rw.getCell(cellNo);
		String value = cl.getStringCellValue();
		return value;
	}
}