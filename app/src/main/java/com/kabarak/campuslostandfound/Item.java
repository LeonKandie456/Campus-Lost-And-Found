package com.kabarak.campuslostandfound;

public class Item {
    public String name;
    public String description;
    public String location;
    public String date;
    public String contact;
    public String category;
    public boolean isLost;
    public boolean claimed;

    public Item(String name, String description, String location, String date, String contact, String category, boolean isLost) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.date = date;
        this.contact = contact;
        this.category = category;
        this.isLost = isLost;
        this.claimed = false;
    }
}
