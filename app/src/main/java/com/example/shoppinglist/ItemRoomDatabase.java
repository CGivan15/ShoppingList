package com.example.shoppinglist;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = Item.class, version = 1)
public abstract class ItemRoomDatabase extends RoomDatabase {

    public abstract ItemDao itemDao();

    private static volatile ItemRoomDatabase itemRoomInstance;

    static ItemRoomDatabase getDatabase(final Context context) {
        if (itemRoomInstance == null) {
            synchronized (ItemRoomDatabase.class) {
                if (itemRoomInstance == null) {
                    itemRoomInstance = Room.databaseBuilder(context.getApplicationContext(),
                            ItemRoomDatabase.class, "item_database")
                            .build();
                }
            }
        }
        return itemRoomInstance;
    }
}
