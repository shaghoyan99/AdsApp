package storage;

import model.Category;
import model.Item;
import model.User;
import util.ExcelUtil;
import util.FileUtil;

import java.io.IOException;
import java.util.*;

public class DataStorage {

    private static long itemId = 1;
    private Map<String, User> map = new HashMap<>();
    private List<Item> items = new ArrayList<>();


    public void initData() {
        map = FileUtil.deserializeUser();
        items = FileUtil.deserializeItem();


        if (items != null && !items.isEmpty()) {
            Item item = items.get(items.size() - 1);
            itemId = item.getId() + 1;
        }
    }

    public void add(User user) throws IOException {
        map.put(user.getPhoneNumber(), user);
        ExcelUtil.user(map);
        FileUtil.serializeUser(map);

    }

    public void add(Item item) throws IOException {
        item.setId(itemId++);
        items.add(item);
        ExcelUtil.item(items);
        FileUtil.serializeItem(items);

    }

//    public void addUser(User user) {
//        Map<String,User> userMap = new HashMap<>();
//        userMap.put(user.getPhoneNumber(),user);
//        FileUtil.serializeUser(userMap);
//
//    }
//    public void addItem(Item item) {
//        List<Item> it = new ArrayList<>();
//        it.add(item);
//        FileUtil.serializeItem(it);
//    }

    public User getUser(String phoneNumber) {
        return map.get(phoneNumber);
    }

    public Item getItemById(long id) {
        for (Item item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public void printItems() {
        for (Item item : items) {
            System.out.println(item);
        }
    }


    public void printItemsByUser(User user) {
        for (Item item : items) {
            if (item.getUser().equals(user)) {
                System.out.println(item);
            }
        }
    }

    public void printItemsByCategory(Category category) {
        for (Item item : items) {
            if (item.getCategory() == category) {
                System.out.println(item);
            }
        }
    }

    public void printItemsOrderByTitle() {
        List<Item> orderedList = new ArrayList<>(items);
        Collections.sort(orderedList);
//        orderedList.sort(Item::compareTo);
        for (Item item : orderedList) {
            System.out.println(item);
        }
    }

    public void printItemsOrderByDate() {
        List<Item> orderedList = new ArrayList<>(items);
//        orderedList.sort(Comparator.comparing(Item::getCreatedDate));

        orderedList.sort(new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return o1.getCreatedDate().compareTo(o2.getCreatedDate());
            }
        });
        for (Item item : orderedList) {
            System.out.println(item);
        }
    }

    public void deleteItemsByUser(User user) {
        Iterator<Item> iterator = items.iterator();
        while (iterator.hasNext()) {
            Item next = iterator.next();
            if (next.getUser().equals(user)) {
                iterator.remove();
            }
        }
        FileUtil.serializeItem(items);
//        items.removeIf(item -> item.getUser().equals(user));Use
    }

    public void deleteItemsById(long id) {
        items.remove(getItemById(id));
        FileUtil.serializeItem(items);
    }


}
