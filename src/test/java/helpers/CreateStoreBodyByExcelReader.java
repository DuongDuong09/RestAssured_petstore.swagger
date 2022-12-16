package helpers;

import model.Store_Body;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class CreateStoreBodyByExcelReader {
    //UnsynchronizedByteArrayOutputStream
    Sheet sheet;
    Workbook workbook;
    private static String excelPath = "src/test/resources/Store.xlsx";
    private static String sheetName = "Sheet1";
    private static Map<String, Integer> columns = new HashMap<>();

    public Store_Body getDataIntoObject() throws IOException {
        Store_Body store = new Store_Body();
        int i = 1;
        store.setId(Integer.parseInt(getValuesByColumn("id", i)));
        store.setPetId(Integer.parseInt(getValuesByColumn("petId", i)));
        store.setQuantity(Integer.parseInt(getValuesByColumn("quantity", i)));
        store.setShipData(getValuesByColumn("shipDate", i));
        store.setStatus(getValuesByColumn("status", i));
        store.setComplete(Boolean.parseBoolean(getValuesByColumn("complete", i)));
        return store;
    }
    public String getValuesByColumn(String columnName, int rowNum) throws IOException {
        File file = new File(excelPath);
        FileInputStream inputStream = new FileInputStream(file);
        workbook = new XSSFWorkbook(inputStream);
        sheet = workbook.getSheet(sheetName);
        Cell cell = sheet.getRow(rowNum).getCell(columns.get(columnName));
        String CellData = null;
        try {
            switch (cell.getCellType()) {
                case STRING:
                    CellData = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        CellData = String.valueOf(cell.getDateCellValue());
                    } else {
                        CellData = String.valueOf((long) cell.getNumericCellValue());
                    }
                    break;
                case BOOLEAN:
                    CellData = Boolean.toString(cell.getBooleanCellValue());
                    break;
                case BLANK:
                    CellData = "";
                    break;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return CellData;
    }
    public JSONObject adddata() throws IOException {
        Map<String, Object> store = new HashMap<String, Object>();
        store.put("id", getDataIntoObject().getId());
        store.put("petId", getDataIntoObject().getPetId());
        store.put("quantity", getDataIntoObject().getQuantity());
        store.put("shipDate",getDataIntoObject().getShipData());
        store.put("status",getDataIntoObject().getStatus());
        store.put("complete",getDataIntoObject().getComplete());
        JSONObject requestPayload = new JSONObject(store);
        return requestPayload;
    }
}