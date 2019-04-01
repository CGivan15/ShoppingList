package com.example.shoppinglist;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.List;
import java.util.UUID;

import android.widget.Toast;
import android.view.View;


public class MainActivity extends AppCompatActivity implements ItemListAdapter.OnDeleteClickListener {

    private static final int NEW_ITEM_ACTIVITY_REQUEST_CODE = 1;
    public static final int UPDATE_ITEM_ACTIVITY_REQUEST_CODE = 2;
    private String TAG = this.getClass().getSimpleName();
    private ItemViewModel itemViewModel;
    private ItemListAdapter itemListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        itemListAdapter = new ItemListAdapter(this, this);
        recyclerView.setAdapter(itemListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Add new item
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewItemActivity.class);
                startActivityForResult(intent, NEW_ITEM_ACTIVITY_REQUEST_CODE);
            }
        });

        itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);

        itemViewModel.getAllItems().observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(@Nullable List<Item> items) {
                itemListAdapter.setItems(items);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_ITEM_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

            // Insert item
            final String item_id = UUID.randomUUID().toString();
            Item item = new Item(item_id, data.getStringExtra(NewItemActivity.ITEM_ADDED));
            itemViewModel.insert(item);

            Toast.makeText(
                    getApplicationContext(),
                    R.string.saved,
                    Toast.LENGTH_LONG).show();
        } else if (requestCode == UPDATE_ITEM_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

            // Update item
            Item item = new Item(
                    data.getStringExtra(EditItemActivity.ITEM_ID),
                    data.getStringExtra(EditItemActivity.UPDATED_ITEM));
            itemViewModel.update(item);

            Toast.makeText(
                    getApplicationContext(),
                    R.string.updated,
                    Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void OnDeleteClickListener(Item myItem) {
        // Delete Item
        itemViewModel.delete(myItem);
    }
}