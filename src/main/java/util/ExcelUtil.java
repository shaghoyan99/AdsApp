package util;


import model.Item;
import model.User;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class ExcelUtil {

    private static final String USER_PATH = "src\\main\\resources\\userDate.xlsx";
    private static final String ITEM_PATH = "src\\main\\resources\\itemDate.xlsx";
    private static int ROW_INDEX = 0;
    private static int ROW_IND = 0;


    public static void item(List<Item> items) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        int lasRowNum = sheet.getLastRowNum();
        if (lasRowNum < 0) {
            lasRowNum = 1;
        }

        for (Item item : items) {
            for (int i = 0; i <= lasRowNum ; i++) {
                Row row = sheet.createRow(ROW_IND++);
                row.createCell(0).setCellValue(item.getTitle());
                row.createCell(1).setCellValue(item.getText());
                row.createCell(2).setCellValue(item.getPrice());
                row.createCell(3).setCellValue(item.getCategory().name());
            }
        }

//        File serializeItem = new File(ITEM_PATH);
//        try {
//            if (!serializeItem.exists()) {
//                serializeItem.createNewFile();
//            }
            try (FileOutputStream fileOutputStream = new FileOutputStream(ITEM_PATH)) {
                workbook.write(fileOutputStream);
            }
         catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void user(Map<String, User> users) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        int lasRowNum = sheet.getLastRowNum();
        if (lasRowNum < 0) {
            lasRowNum = 1;
        }
        for (User value : users.values()) {
            for (int i = 0; i <= lasRowNum; i++) {
                Row row = sheet.createRow(ROW_INDEX++);
                row.createCell(0).setCellValue(value.getName());
                row.createCell(1).setCellValue(value.getSurName());
                row.createCell(2).setCellValue(value.getGender().name());
                row.createCell(3).setCellValue(value.getAge());
                row.createCell(4).setCellValue(value.getPhoneNumber());
                row.createCell(5).setCellValue(value.getPassword());
            }
        }
//        File serializeItem = new File(USER_PATH);
//        try {
//            if (!serializeItem.exists()) {
//                serializeItem.createNewFile();
//            }
            try (FileOutputStream fileOutputStream = new FileOutputStream(USER_PATH)) {
                workbook.write(fileOutputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
