package com.com.cst2335.shar0686;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
public class ProfileActivity extends AppCompatActivity {
    public String NAME = "text";
    public String ADDRESS = "address";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        SharedPreferences sharedPreferences = getSharedPreferences("shared", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        EditText name = findViewById(R.id.Name);
        EditText address = findViewById(R.id.Address);
        TextView emailEditText = findViewById(R.id.emailAddress);

        Button saveButton = findViewById(R.id.button2);
        Button ClearButton = findViewById(R.id.button3);

        Intent intent = getIntent();
        String email = intent.getStringExtra("EMAIL");
        emailEditText.setText(email);

        name.setText(sharedPreferences.getString(NAME, ""));
        address.setText(sharedPreferences.getString(ADDRESS, ""));

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString(NAME, name.getText().toString());
                editor.putString(ADDRESS, address.getText().toString());
                editor.apply();
            }
        });

        ClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.clear();
                editor.commit();
                name.setText("");
                address.setText("");

            }
        });
    }
}