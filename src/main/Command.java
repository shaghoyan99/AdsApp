package main;

public interface Command {

    int LOGIN = 1;
    int REGISTER = 2;
    int EXIT = 0;

    int ADD_NEW_AD = 1;
    int PRINT_MY_ADS = 2;
    int PRINT_ALL_ADS = 3;
    int PRINT_ADS_BY_CATEGORY = 4;
    int PRINT_ALL_ADS_SORT_BY_TITLE = 5;
    int PRINT_ALL_ADS_SORT_BY_DATE = 6;
    int DELETE_MY_ALL_ADS = 7;
    int DELETE_AD_BY_ID = 8;
    int LOGOUT = 0;


    static void printMainCommands() {
        System.out.println("Please input " + LOGIN + " for LOGIN");
        System.out.println("Please input " + REGISTER + " for REGISTER");
        System.out.println("Please input " + EXIT + " for EXIT");
    }

    static void printUserCommands() {
        System.out.println("Please input " + ADD_NEW_AD + " for ADD_NEW_AD");
        System.out.println("Please input " + PRINT_MY_ADS + " for PRINT_MY_ADS");
        System.out.println("Please input " + PRINT_ALL_ADS + " for PRINT_ALL_ADS");
        System.out.println("Please input " + PRINT_ADS_BY_CATEGORY + " for PRINT_AD_BY_CATEGORY");
        System.out.println("Please input " + PRINT_ALL_ADS_SORT_BY_TITLE + " for PRINT_ALL_AD_BY_TITLE_SORT");
        System.out.println("Please input " + PRINT_ALL_ADS_SORT_BY_DATE + " for PRINT_ALL_AD_BY_DATE_SORT");
        System.out.println("Please input " + DELETE_MY_ALL_ADS + " for DELETE_MY_ALL_ADS");
        System.out.println("Please input " + DELETE_AD_BY_ID + " for DELETE_AD_BY_ID");
        System.out.println("Please input " + LOGOUT + " for LOGOUT");
    }
}
