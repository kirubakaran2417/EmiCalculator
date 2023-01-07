package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelUtils {

	public static XSSFSheet worksheet;
	public static XSSFWorkbook workbook;
	public static FileOutputStream workbook_write;
	public static String file_path;
	public static XSSFRow row;
	public static FileInputStream work_file;

	public static void writeExcelAllDEtails(String sheetName, ArrayList<String> details) {

		workbook = new XSSFWorkbook();
		worksheet = workbook.createSheet(sheetName);

		row = worksheet.createRow(0);
		row.createCell(0).setCellValue("Months");
		row.createCell(1).setCellValue("Principle");
		row.createCell(2).setCellValue("Interest");
		row.createCell(3).setCellValue("Total");
		row.createCell(4).setCellValue("Balance");
		row.createCell(5).setCellValue("To Date");

		Iterator itrDetails = details.iterator();

		String[] rowDataArr;
		int r = 1;
		while (itrDetails.hasNext()) {
			String dataToInsert = (String) itrDetails.next();
			// System.out.println(dataToInsert);
			// System.out.println(dataToInsert);
			rowDataArr = dataToInsert.split("-");
			row = worksheet.createRow(r++);
			for (int i = 0; i < rowDataArr.length; i++) {
				row.createCell(i).setCellValue(rowDataArr[i]);
			}
		}

		try {
			workbook_write = new FileOutputStream(System.getProperty("user.dir")+"/excelOutputs/"+sheetName+".xlsx");
			workbook.write(workbook_write);
			workbook_write.close();
			// workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
