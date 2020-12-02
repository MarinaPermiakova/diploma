package com.example.android.com.diploma;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.textfield.TextInputLayout;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class settingsActivity extends AppCompatActivity {

    TextInputLayout editTextForPincode;
    Button btnSave;
    private static final String FILE_NAME = "pin_code2.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        editTextForPincode = findViewById(R.id.editTextNumberPassword);
        btnSave = findViewById(R.id.buttonSave);
        btnSave.setOnClickListener(btnSaveClickListener);

        RadioButton lightRadioButton = (RadioButton)findViewById(R.id.radio_light);
        lightRadioButton.setOnClickListener(radioButtonClickListener);

        RadioButton darkRadioButton = (RadioButton)findViewById(R.id.radio_dark);
        darkRadioButton.setOnClickListener(radioButtonClickListener);

        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if (nightMode == AppCompatDelegate.MODE_NIGHT_NO){
            lightRadioButton.setChecked(true);
        } else darkRadioButton.setChecked(true);
    }

    View.OnClickListener btnSaveClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String pincode = editTextForPincode.getEditText().getText().toString();
            try {
                savePinCode(pincode);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    };

    View.OnClickListener radioButtonClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            RadioButton rb = (RadioButton) view;
            switch (rb.getId()) {
                case R.id.radio_light: AppCompatDelegate.setDefaultNightMode
                        (AppCompatDelegate.MODE_NIGHT_NO);
                    break;
                case R.id.radio_dark: AppCompatDelegate.setDefaultNightMode
                        (AppCompatDelegate.MODE_NIGHT_YES);
                    break;
                default:
                    break;
            }
            recreate();
        }
    };

    private void savePinCode(String pincode) throws FileNotFoundException {
        FileOutputStream fileOutputStream = openFileOutput(FILE_NAME, MODE_PRIVATE);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
        try {
            bw.write(pincode);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(settingsActivity.this, MainActivity.class);

        startActivity(intent);
    }
}