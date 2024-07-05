package utilities;

import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {
	XSSFWorkbook wbook;
	XSSFSheet sheet;
	XSSFCell cell;
	public static String locatorColumnValue;
	public static String locatorName;
	public static String locatorValue;
	public static String keywordColumnValue;
	public static String dataColumnValue;
	public static int totalRows;
	
	public void readExcelFile(String location) throws IOException {
		wbook=new XSSFWorkbook(location);
		sheet=wbook.getSheetAt(0);
		totalRows=sheet.getLastRowNum();
	}
	public void getLocatorsKeywordAndDataFromExcel(int row,int locatorColumn,int keywordColumn,int dataColumn) {
		locatorColumnValue=sheet.getRow(row).getCell(locatorColumn).toString().trim();  //trim is used to avoid the spaces in the Excel cell if wrongly we given
		if(!locatorColumnValue.contains("NA")) {
			locatorName=locatorColumnValue.split("==")[0].toString().trim();  //This will split the string into 2 string by keeping "=" as middle ,split will return string array which contain 2 string value
			locatorValue=locatorColumnValue.split("==")[1].toString().trim();
		}
		else {
			locatorName="NA";
			locatorValue="NA";
		}
		keywordColumnValue=sheet.getRow(row).getCell(keywordColumn).toString().trim();
		dataColumnValue=sheet.getRow(row).getCell(dataColumn).toString().trim();
		System.out.println(locatorName+" "+locatorValue+" "+keywordColumnValue+" "+dataColumnValue);
	}
}
