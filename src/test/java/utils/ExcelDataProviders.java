package utils;

import java.io.IOException;

public class ExcelDataProviders {
    public static String[][] getExcelData(String sheetName) throws IOException {
        String path = System.getProperty("user.dir")
                + "/testData/authorTestTabTestData/authorTestCaseTab_testData.xlsx";
        XLUtility utility = new XLUtility(path);

        int totalRows = utility.getRowCount(sheetName);
        int totalCols = utility.getCellCount(sheetName, 1);

        String[][] data = new String[totalRows][totalCols];

        for (int i = 1; i <= totalRows; i++) {
            for (int j = 0; j < totalCols; j++) {
                data[i - 1][j] = utility.getCellData(sheetName, i, j);
            }
        }
        return data;
    }

    public static String[][] getExcelDataForRequirement(String sheetName) throws IOException {
        String path = System.getProperty("user.dir") + "/testData/requirementTabTestData/requirementTab_testData.xlsx";
        XLUtility utility = new XLUtility(path);

        int totalRows = utility.getRowCount(sheetName);
        int totalCols = utility.getCellCount(sheetName, 1);

        String[][] data = new String[totalRows][totalCols];

        for (int i = 1; i <= totalRows; i++) {
            for (int j = 0; j < totalCols; j++) {
                data[i - 1][j] = utility.getCellData(sheetName, i, j);
            }
        }
        return data;
    }

    public static String[][] getExcelDataForTestPlan(String sheetName) throws IOException {
        String path = System.getProperty("user.dir") + "/testData/testPlanTabTestData/testPlanTab_TestData.xlsx";
        XLUtility utility = new XLUtility(path);

        int totalRows = utility.getRowCount(sheetName);
        int totalCols = utility.getCellCount(sheetName, 1);

        String[][] data = new String[totalRows][totalCols];

        for (int i = 1; i <= totalRows; i++) {
            for (int j = 0; j < totalCols; j++) {
                data[i - 1][j] = utility.getCellData(sheetName, i, j);
            }
        }
        return data;
    }

    public static String[][] getExcelDataForExecuteTestCase(String sheetName) throws IOException {
        String path = System.getProperty("user.dir")
                + "/testData/executeTestCaseTabTestData/executeTestCaseTab_testData.xlsx";
        XLUtility utility = new XLUtility(path);

        int totalRows = utility.getRowCount(sheetName);
        int totalCols = utility.getCellCount(sheetName, 1);

        String[][] data = new String[totalRows][totalCols];

        for (int i = 1; i <= totalRows; i++) {
            for (int j = 0; j < totalCols; j++) {
                data[i - 1][j] = utility.getCellData(sheetName, i, j);
            }
        }
        return data;
    }

    public static String[][] getExcelDataForSettingTestCase(String sheetName) throws IOException {
        String path = System.getProperty("user.dir") + "/testData/SettingTestCaseTestData/settingTab_testData.xlsx";
        XLUtility utility = new XLUtility(path);

        int totalRows = utility.getRowCount(sheetName);
        int totalCols = utility.getCellCount(sheetName, 1);

        String[][] data = new String[totalRows][totalCols];

        for (int i = 1; i <= totalRows; i++) {
            for (int j = 0; j < totalCols; j++) {
                data[i - 1][j] = utility.getCellData(sheetName, i, j);
            }
        }
        return data;
    }
}
