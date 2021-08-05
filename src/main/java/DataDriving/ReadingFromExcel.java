package DataDriving;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadingFromExcel {

	static XSSFSheet sheet;
	static XSSFWorkbook workbook;

	public ArrayList<String> getData(String testCaseName) throws IOException {

		FileInputStream fis = new FileInputStream("/home/mehuljain/Documents/TestData.xlsx");
		ArrayList<String> listOfData = new ArrayList<String>();
		workbook = new XSSFWorkbook(fis);
		workbook.getNumberOfSheets();
		int sheetsCount = workbook.getNumberOfSheets();
		System.out.println("Total Sheets are => " + sheetsCount);
		for (int i = 0; i < sheetsCount; i++) {
			if (workbook.getSheetAt(i).getSheetName().equalsIgnoreCase("TestingData")) {
				sheet = workbook.getSheetAt(i);
			}
		}
		Iterator<Row> rowItr = sheet.iterator();
		Iterator<Cell> cell = rowItr.next().cellIterator();
		Cell firstcell = cell.next();
		System.out.println(firstcell.getStringCellValue());
		int k = 0;
		int column = 0;
		while (cell.hasNext()) {
			if (cell.next().getStringCellValue().equalsIgnoreCase("Test Case Name")) {
				column = k;
			}
			k++;
		}
		System.out.println(column);

		while (rowItr.hasNext()) {
			Row row = rowItr.next();
			if (row.getCell(column).getStringCellValue().equalsIgnoreCase(testCaseName)) {
				Iterator<Cell> cellVal = row.cellIterator();
				while (cellVal.hasNext()) {
					Cell c = cellVal.next();
					if (c.getCellTypeEnum() == CellType.STRING) {
						listOfData.add(c.getStringCellValue());
					} else {
						listOfData.add(NumberToTextConverter.toText(c.getNumericCellValue()));
					}
				}
			}
		}
		return listOfData;
	}
}