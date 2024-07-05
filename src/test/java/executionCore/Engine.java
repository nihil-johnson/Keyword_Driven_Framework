package executionCore;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.openqa.selenium.By;

import actionKeywords.ActionKeywords;
import constants.Constants;
import utilities.ExcelUtilities;
import utilities.Locators;

public class Engine {
	ActionKeywords actionkeywords;
	Method[] methods;
	public static String keyword;
	public static By locator;
	public void getKeywordsFromAction() {
		actionkeywords=new ActionKeywords();	//We are using Reflection API to get all the method name in
		methods=actionkeywords.getClass().getMethods(); //class
	}
	public void executeKeyword() throws IllegalAccessException, InvocationTargetException {
		for(int i=0;i<methods.length;i++) {
			if(methods[i].getName().equalsIgnoreCase(ExcelUtilities.keywordColumnValue)) {
				methods[i].invoke(actionkeywords);
			}
		}
	}
	public void findWebElementsToBeUsed() {
		switch(ExcelUtilities.locatorName) {
		case "name":
			locator=Locators.getName(ExcelUtilities.locatorValue);
			break;
		case "id":
			locator=Locators.getId(ExcelUtilities.locatorValue);
			break;
		case "xpath":
			locator=Locators.getXpath(ExcelUtilities.locatorValue);
			break;
		case "linktext":
			locator=Locators.getLinktext(ExcelUtilities.locatorValue);
			break;
		}
	}
	public static void main(String[] args) throws IOException, InterruptedException, IllegalAccessException, InvocationTargetException {
	ExcelUtilities excel=new ExcelUtilities();
	excel.readExcelFile(Constants.EXCEL_LOCATION);
	Engine engine=new Engine();
	engine.getKeywordsFromAction();
	for(int i=1;i<=ExcelUtilities.totalRows;i++) {
		excel.getLocatorsKeywordAndDataFromExcel(i,Constants.LOCATOR_COLUMN,Constants.KEYWORD_COLUMN,Constants.DATA_COLUMN);
		engine.findWebElementsToBeUsed();
		engine.executeKeyword();
	}
	}

}
