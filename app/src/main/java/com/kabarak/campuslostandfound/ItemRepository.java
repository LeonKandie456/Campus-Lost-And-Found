package com.kabarak.campuslostandfound;

import java.util.ArrayList;
import java.util.List;

public class ItemRepository {
    private static final List<Item> items = new ArrayList<>();

    public static void add(Item item) {
        items.add(item);
    }

    public static List<Item> getAll() {
        return items;
    }
}
