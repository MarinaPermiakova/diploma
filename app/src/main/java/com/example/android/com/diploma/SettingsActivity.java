package com.example.android.com.diploma;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.textfield.TextInputEditText;

import java.io.FileNotFoundException;
import java.util.Objects;


public class SettingsActivity extends AppCompatActivity implements KeystoreHolder {

    private TextInputEditText editTextForPincode;
    private Keystore keystore;

    @Override
    public void setKeystore(@NonNull Keystore keystore) {
        this.keystore = keystore;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button btnSave = findViewById(R.id.buttonSave);
        btnSave.setOnClickListener(btnSaveClickListener);

        editTextForPincode = findViewById(R.id.editTextNumberPassword);
        editTextForPincode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 5) btnSave.setVisibility(View.VISIBLE);
                if (s.length() < 5) btnSave.setVisibility(View.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        RadioButton lightRadioButton = (RadioButton) findViewById(R.id.radio_light);
        lightRadioButton.setOnClickListener(radioButtonClickListener);
        RadioButton darkRadioButton = (RadioButton) findViewById(R.id.radio_dark);
        darkRadioButton.setOnClickListener(radioButtonClickListener);
        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if (nightMode == AppCompatDelegate.MODE_NIGHT_NO) {
            lightRadioButton.setChecked(true);
        } else darkRadioButton.setChecked(true);
    }

    View.OnClickListener btnSaveClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String pincode = Objects.requireNonNull(editTextForPincode.getText()).toString();
            try {
                savePinCode(pincode);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    };
    View.OnClickListener radioButtonClickListener = view -> {
        RadioButton rb = (RadioButton) view;
        int id = rb.getId();
        if (id == R.id.radio_light) {
            AppCompatDelegate.setDefaultNightMode
                    (AppCompatDelegate.MODE_NIGHT_NO);
        } else if (id == R.id.radio_dark) {
            AppCompatDelegate.setDefaultNightMode
                    (AppCompatDelegate.MODE_NIGHT_YES);
        }
        recreate();
    };

    private void savePinCode(String pincode) throws FileNotFoundException {
        keystore.saveNew(pincode, SettingsActivity.this);
        Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
