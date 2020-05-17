package util;

import model.Item;
import model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileUtil {

    private static final String USER_PATH = "src\\main\\resources\\serializeUser.obj";
    private static final String ITEM_PATH = "src\\main\\resources\\serializeItem.obj";


    public static void serializeUser(Map<String, User> userMap) {
        File serializeUser = new File(USER_PATH);
        try {
            if (!serializeUser.exists()) {
                boolean newFile = serializeUser.createNewFile();
            }
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(USER_PATH))) {
                objectOutputStream.writeObject(userMap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, User> deserializeUser() {
        Map<String, User> result = new HashMap<>();
        File serializeUser = new File(USER_PATH);
        if (serializeUser.exists()) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(USER_PATH))) {
                Object ob = objectInputStream.readObject();
                return (Map<String, User>) ob;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static void serializeItem(List<Item> items) {
        File serializeItem = new File(ITEM_PATH);
        try {
            if (!serializeItem.exists()) {
                boolean newFile = serializeItem.createNewFile();
            }
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(ITEM_PATH))) {
                objectOutputStream.writeObject(items);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static List<Item> deserializeItem() {
        List<Item> result = new ArrayList<>();
        File serializeItem = new File(ITEM_PATH);
        if (serializeItem.exists()) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(ITEM_PATH))) {
                Object ob = objectInputStream.readObject();
                return (List<Item>) ob;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
