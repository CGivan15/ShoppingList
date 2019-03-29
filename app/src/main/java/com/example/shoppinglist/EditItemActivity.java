package com.example.shoppinglist;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {
    public static final String ITEM_ID = "item_id";
    static final String UPDATED_ITEM = "item_text";
    private EditText etItem;
    private Bundle bundle;
    private String itemId;
    private LiveData<Item> item;

    EditItemViewModel itemModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        etItem = findViewById(R.id.etItem);

        bundle = getIntent().getExtras();

        if (bundle != null) {
            itemId = bundle.getString("item_id");
        }

        itemModel = ViewModelProviders.of(this).get(EditItemViewModel.class);
        item = itemModel.getItem(itemId);
        item.observe(this, new Observer<Item>() {
            @Override
            public void onChanged(@Nullable Item item) {
                etItem.setText(item.getItem());
            }
        });
    }

    public void updateItem(View view) {
        String updatedItem = etItem.getText().toString();
        Intent resultIntent = new Intent();
        resultIntent.putExtra(ITEM_ID, itemId);
        resultIntent.putExtra(UPDATED_ITEM, updatedItem);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    public void cancelUpdate(View view) {
        finish();
    }
}

