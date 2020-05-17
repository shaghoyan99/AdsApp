
import commands.Command;
import model.Category;
import model.Gender;
import model.Item;
import model.User;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import storage.DataStorage;
import util.ExcelUtil;
import util.FileUtil;


import java.io.IOException;
import java.util.*;

import static model.Gender.valueOf;


public class AdvertisementMain implements Command {

    private static final Scanner scanner = new Scanner(System.in);
    private static final DataStorage dataStorage = new DataStorage();
    private static User currentUser = null;


    public static void main(String[] args) throws IOException, ClassNotFoundException {
       // dataStorage.initData();
        boolean isRun = true;
        while (isRun) {
            Command.printMainCommands();
            int command;
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
            }
            switch (command) {
                case LOGIN:
                    loginUser();
                    break;
                case REGISTER:
                    registerUser();
                    break;
                case IMPORT_USERS:
                    importFromUserXlsx();
                    break;
                case EXIT:
                    isRun = false;
                    break;
                default:
                    System.out.println("Wrong Command!");
            }
        }
    }

    private static void importFromItemXlsx() {
        System.out.println("Please select xlsx path");
        String xlsxPath = scanner.nextLine();

        try{
            XSSFWorkbook workbook = new XSSFWorkbook(xlsxPath);
            Sheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 1; i <= lastRowNum ; i++) {
                Row row = sheet.getRow(i);
                String title = row.getCell(0).getStringCellValue();
                String text = row.getCell(1).getStringCellValue();
                String price = row.getCell(2).getStringCellValue();
                Category category = Category.valueOf(row.getCell(3).getStringCellValue());
                Item item = new Item(title, text, price,
                        currentUser, category, new Date());
                dataStorage.add(item);
//                dataStorage.addItem(item);
            }
            System.out.println("Import was success!");
        }catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error while importing users");
        }
    }



    private static void importFromUserXlsx() {
        System.out.println("Please select xlsx path");
        String xlsxPath = scanner.nextLine();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(xlsxPath);
            Sheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 1; i <= lastRowNum; i++) {
                Row row = sheet.getRow(i);
                String name = row.getCell(0).getStringCellValue();
                String surName = row.getCell(1).getStringCellValue();
                Gender gender = Gender.valueOf(row.getCell(2).getStringCellValue());
                String age = row.getCell(3).getStringCellValue();
                Cell phoneNumber = row.getCell(4);
                String phoneNumberStr = phoneNumber.getCellType() == CellType.NUMERIC ?
                        String.valueOf(Double.valueOf(phoneNumber.getNumericCellValue()).intValue()) : phoneNumber.getStringCellValue();
                Cell password = row.getCell(5);
                String passwordStr = password.getCellType() == CellType.NUMERIC ?
                        String.valueOf(Double.valueOf(password.getNumericCellValue()).intValue()) : password.getStringCellValue();
                User user = new User();
                user.setName(name);
                user.setSurName(surName);
                user.setAge(age);
                user.setGender(gender);
                user.setPhoneNumber(phoneNumberStr);
                user.setPassword(passwordStr);
                dataStorage.add(user);
            }
            System.out.println("Import was success!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error while importing users");
        }

    }

    private static void registerUser() {
        System.out.println("Please input user data: " +
                "name,surname,gender(MALE,FEMALE),age,phoneNumber,password");
        try {
            String userDataStr = scanner.nextLine();
            String[] userDataArray = userDataStr.split(",");
            User userFromStorage = dataStorage.getUser(userDataArray[4]);
            if (userFromStorage == null) {
                User user = new User();
                user.setName(userDataArray[0]);
                user.setSurName(userDataArray[1]);
                user.setGender(valueOf(userDataArray[2].toUpperCase()));
                user.setAge(userDataArray[3]);
                user.setPhoneNumber(userDataArray[4]);
                user.setPassword(userDataArray[5]);
                dataStorage.add(user);
                System.out.println("User was successfully added");
            } else {
                System.out.println("User already exists");
            }
        } catch (ArrayIndexOutOfBoundsException | IOException e) {
            System.out.println("Wrong Data!");

        }


    }

    private static void loginUser() {
        System.out.println("Please input phoneNumber,password");
        try {
            String loginStr = scanner.nextLine();
            String[] loginArray = loginStr.split(",");
            User user = dataStorage.getUser(loginArray[0]);
            if (user != null && user.getPhoneNumber().equals(loginArray[0])) {
                currentUser = user;
                loginSuccess();
            } else {
                System.out.println("Wrong phoneNumber or password!");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Wrong data!");
        }


    }

    private static void loginSuccess() {
        System.out.println("Welcome " + currentUser.getName() + "!");
        boolean isRun = true;
        while (isRun) {
            Command.printUserCommands();
            int userCommand;
            try {
                userCommand = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                userCommand = -1;
            }
            switch (userCommand) {
                case IMPORT_ITEMS:
                    importFromItemXlsx();
                    break;
                case ADD_NEW_AD:
                    addNewItem();
                    break;
                case PRINT_MY_ADS:
                    dataStorage.printItemsByUser(currentUser);
                    break;
                case PRINT_ALL_ADS:
                    dataStorage.printItems();
                    break;
                case PRINT_ADS_BY_CATEGORY:
                    printByCategory();
                    break;
                case PRINT_ALL_ADS_SORT_BY_TITLE:
                    dataStorage.printItemsOrderByTitle();
                    break;
                case PRINT_ALL_ADS_SORT_BY_DATE:
                    dataStorage.printItemsOrderByDate();
                    break;
                case DELETE_MY_ALL_ADS:
                    dataStorage.deleteItemsByUser(currentUser);
                    break;
                case DELETE_AD_BY_ID:
                    deleteById();
                    break;
                case LOGOUT:
                    isRun = false;
                    break;
                default:
                    System.out.println("Wrong commands!");
            }
        }
    }


    private static void addNewItem() {
        System.out.println("Please input Item data: title,text,price,category");
        System.out.println("Please choose category name from list " + Arrays.toString(Category.values()));
        try {
            String itemDataStr = scanner.nextLine();
            String[] itemDataArray = itemDataStr.split(",");
            Item item = new Item(itemDataArray[0], itemDataArray[1], itemDataArray[2],
                    currentUser, Category.valueOf(itemDataArray[3].toUpperCase()), new Date());
            dataStorage.add(item);
            System.out.println("Item was successfully added");
        } catch (Exception e) {
            System.out.println("Wrong Date!");
        }


    }

    private static void printByCategory() {
        System.out.println("Please choose category name from list " + Arrays.toString(Category.values()));
        try {
            String categoryStr = scanner.nextLine();
            Category category = Category.valueOf(categoryStr);
            dataStorage.printItemsByCategory(category);
        } catch (Exception e) {
            System.out.println("Wrong Category!");
        }
    }

    private static void deleteById() {
        System.out.println("Please choose id from list");
        dataStorage.printItemsByUser(currentUser);
        long id = Long.parseLong(scanner.nextLine());
        Item itemById = dataStorage.getItemById(id);
        if (itemById != null && itemById.getUser().equals(currentUser)) {
            dataStorage.deleteItemsById(id);
        } else {
            System.out.println("Wrong Id!");
        }
    }
}
