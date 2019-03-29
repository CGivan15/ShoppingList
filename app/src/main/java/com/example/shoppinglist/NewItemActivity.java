package com.example.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewItemActivity extends AppCompatActivity {

    public static final String ITEM_ADDED = "new_item";

    private EditText etNewItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);
        etNewItem = findViewById(R.id.etNewItem);

        Button button = findViewById(R.id.bAdd);
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                Intent resultIntent = new Intent();

                if (TextUtils.isEmpty(etNewItem.getText())) {
                    setResult(RESULT_CANCELED, resultIntent);
                } else {
                    String item = etNewItem.getText().toString();
                    resultIntent.putExtra(ITEM_ADDED, item);
                    setResult(RESULT_OK, resultIntent);
                }

                finish();
            }
        });
    }

}
