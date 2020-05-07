package util;

import model.Item;
import model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FileUtil {

    private static final String User_PATH = "C:\\Users\\WPP\\IdeaProjects\\AdsApp\\src\\util\\file\\serializeUser.txt";
    private static final String Item_PATH = "C:\\Users\\WPP\\IdeaProjects\\AdsApp\\src\\util\\file\\serializeItem.txt";

    public static void serializeUser(Map<String, User> userMap) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(User_PATH));
        objectOutputStream.writeObject(userMap);
        objectOutputStream.close();
    }

    public static Map<String, User> deserializeUser() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(User_PATH));
        Object ob = objectInputStream.readObject();
        objectInputStream.close();
        return (Map<String ,User>) ob;
    }

    public static void serializeItem(List<Item> items) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(Item_PATH));
        objectOutputStream.writeObject(items);
        objectOutputStream.close();
    }

    public static List<Item> deserializeItem() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(Item_PATH));
        Object ob = objectInputStream.readObject();
        objectInputStream.close();
        return (List<Item>) ob;
    }
}
