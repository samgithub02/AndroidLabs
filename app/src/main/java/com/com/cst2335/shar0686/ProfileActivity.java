package com.com.cst2335.shar0686;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;



public class ProfileActivity extends AppCompatActivity {
    ;
    private ActivityResultLauncher<Intent> myPictureTakerLauncher;

    private static final String TAG = "ProfileActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_activity);
        EditText emailEditText = findViewById(R.id.editTextTextEmailAddress2);

        Intent intent = getIntent();
        String email = intent.getStringExtra("EMAIL");
        emailEditText.setText(email);

        Button takePictureButton = findViewById(R.id.button2);



        takePictureButton.setOnClickListener(v -> dispatchTakePictureIntent());

        myPictureTakerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        ImageView imgView = findViewById(R.id.imageView);
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            assert data != null;
                            Bitmap imgBitmap = (Bitmap) data.getExtras().get("data");
                            imgView.setImageBitmap(imgBitmap);
                        } else if (result.getResultCode() == Activity.RESULT_CANCELED) {
                            Log.i(TAG, "User refused to capture a picture.");
                        }
                    }
                }
        );
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            myPictureTakerLauncher.launch(takePictureIntent);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart method");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume method");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause method");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop method");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy method");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e(TAG, "onActivityResult method");
    }

}