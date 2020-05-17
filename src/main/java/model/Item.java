package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Item implements Comparable<Item>, Serializable {

    private long id;
    private String title;
    private String text;
    private String price;
    private User user;
    private Category category;
    private Date createdDate;


    public Item( String title, String text, String price, User user, Category category,Date createdDate) {
        this.title = title;
        this.text = text;
        this.price = price;
        this.user = user;
        this.category = category;
        this.createdDate = createdDate;
    }



    @Override
    public int compareTo(Item o) {
        return title.compareTo(o.getTitle());
    }
}
