package in.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadfromFile {
	public static void main(String[] args) {
		int i;
		Sentiment ob = new Sentiment();
		PreProcessing obj = new PreProcessing();
		try {
			FileInputStream inputStream = new FileInputStream(new File("D:\\Finance.xlsx"));
			Workbook workbook = WorkbookFactory.create(inputStream);
			Sheet sheet = workbook.getSheetAt(0); // creating a Sheet object to retrieve object
			int rowStart= sheet.getFirstRowNum();
			int rowEnd=sheet.getLastRowNum();
			for(i=rowStart+1;i<=rowEnd;i++) {
				Row row = sheet.getRow(i);
				Cell cell = row.getCell(0);
				String st = obj.whenRemoveStopwordsUsingRemoveAll_thenSuccess(cell.getStringCellValue());
				row.getCell(2).setCellValue(ob.senti(st));	
			}
			inputStream.close();
			FileOutputStream outputStream = new FileOutputStream(new File("D:\\Finance.xlsx"));
			workbook.write(outputStream);
			outputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Succesful");
	}
}
