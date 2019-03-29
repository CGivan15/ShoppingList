package com.example.shoppinglist;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "items")
public class Item {

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getItem() {
        return this.mItem;
    }

    @PrimaryKey
    @NonNull
    private String id;

    @NonNull
    @ColumnInfo(name = "item")
    private String mItem;

    public Item(String id, String item) {
        this.id = id;
        this.mItem = item;
    }
}
