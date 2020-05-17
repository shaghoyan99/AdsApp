package model;

import lombok.*;

import java.io.Serializable;

@Data

public class User implements Serializable {

    private String name;
    private String surName;
    private Gender gender;
    private String age;
    private String phoneNumber;
    private String password;


}
