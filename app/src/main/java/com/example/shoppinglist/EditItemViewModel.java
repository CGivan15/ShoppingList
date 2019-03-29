package com.example.shoppinglist;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

public class EditItemViewModel  extends AndroidViewModel {

    private String TAG = this.getClass().getSimpleName();
    private ItemDao itemDao;
    private ItemRoomDatabase db;

    public EditItemViewModel(@NonNull Application application) {
        super(application);
        Log.i(TAG, "Edit ViewModel");
        db = ItemRoomDatabase.getDatabase(application);
        itemDao = db.itemDao();
    }

    public LiveData<Item> getItem(String itemId) {
        return itemDao.getItem(itemId);
    }
}
