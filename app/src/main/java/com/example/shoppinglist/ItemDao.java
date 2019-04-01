package com.example.shoppinglist;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ItemDao {
    @Insert
    void insert(Item item);

    @Query("SELECT * FROM items")
    LiveData<List<Item>> getAllItems();

    @Query("SELECT * FROM items WHERE id=:itemId")
    LiveData<Item> getItem(String itemId);

    @Update
    void update(Item item);

    @Delete
    int delete(Item item);
}
