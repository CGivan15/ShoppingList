package com.example.shoppinglist;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

public class ItemViewModel extends AndroidViewModel {
    private String TAG = this.getClass().getSimpleName();
    private ItemDao itemDao;
    private ItemRoomDatabase itemDB;
    private LiveData<List<Item>> mAllItems;

    public ItemViewModel(Application application) {
        super(application);

        itemDB = ItemRoomDatabase.getDatabase(application);
        itemDao = itemDB.itemDao();
        mAllItems = itemDao.getAllItems();
    }

    public void insert(Item item) {
        new InsertAsyncTask(itemDao).execute(item);
    }

    LiveData<List<Item>> getAllItems() {
        return mAllItems;
    }

    public void update(Item item) {
        new UpdateAsyncTask(itemDao).execute(item);
    }

    public void delete(Item item) {
        new DeleteAsyncTask(itemDao).execute(item);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.i(TAG, "ViewModel Destroyed");
    }

    private class OperationsAsyncTask extends AsyncTask<Item, Void, Void> {

        ItemDao mAsyncTaskDao;

        OperationsAsyncTask(ItemDao dao) {
            this.mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Item... items) {
            return null;
        }
    }

    private class InsertAsyncTask extends OperationsAsyncTask {

        InsertAsyncTask(ItemDao mItemDao) {
            super(mItemDao);
        }

        @Override
        protected Void doInBackground(Item... items) {
            mAsyncTaskDao.insert(items[0]);
            return null;
        }
    }

    private class UpdateAsyncTask extends OperationsAsyncTask {

        UpdateAsyncTask(ItemDao itemDao) {
            super(itemDao);
        }

        @Override
        protected Void doInBackground(Item... items) {
            mAsyncTaskDao.update(items[0]);
            return null;
        }
    }

    private class DeleteAsyncTask extends OperationsAsyncTask {

        public DeleteAsyncTask(ItemDao itemDao) {
            super(itemDao);
        }

        @Override
        protected Void doInBackground(Item... items) {
            mAsyncTaskDao.delete(items[0]);
            return null;
        }
    }
}

