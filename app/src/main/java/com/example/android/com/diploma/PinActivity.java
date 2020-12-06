package com.example.android.com.diploma;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class PinActivity extends AppCompatActivity implements KeystoreHolder {

    private Button btn0;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button btnDelete;

    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;

    String pin = "";
    private Keystore keystore;

    @Override
    public void setKeystore(@NonNull Keystore keystore) {
        this.keystore = keystore;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);

        if (keystore.hasPin(this)) {
            Log.d("pinActivity", "hasPin");
            initViews();
            btn0.setOnClickListener(click0);
            btn1.setOnClickListener(click1);
            btn2.setOnClickListener(click2);
            btn3.setOnClickListener(click3);
            btn4.setOnClickListener(click4);
            btn5.setOnClickListener(click5);
            btn6.setOnClickListener(click6);
            btn7.setOnClickListener(click7);
            btn8.setOnClickListener(click8);
            btn9.setOnClickListener(click9);
            btnDelete.setOnClickListener(delete);
        } else {
            Intent intent = new Intent(PinActivity.this, SettingsActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void initViews() {
        btn0 = findViewById(R.id.button0);
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        btn5 = findViewById(R.id.button5);
        btn6 = findViewById(R.id.button6);
        btn7 = findViewById(R.id.button7);
        btn8 = findViewById(R.id.button8);
        btn9 = findViewById(R.id.button9);
        btnDelete = findViewById(R.id.button_delete);

        textView1 = findViewById(R.id.placeholder1);
        textView2 = findViewById(R.id.placeholder2);
        textView3 = findViewById(R.id.placeholder3);
        textView4 = findViewById(R.id.placeholder4);
        textView5 = findViewById(R.id.placeholder5);
    }

    View.OnClickListener click0 = view -> {
        pin += "0";
        placeholderChangeColor(pin.length());
        if (pin.length() == 5) startIntentIfPinIsOK(pin);
    };

    View.OnClickListener click1 = view -> {
        pin += "1";
        placeholderChangeColor(pin.length());
        if (pin.length() == 5) startIntentIfPinIsOK(pin);
    };

    View.OnClickListener click2 = view -> {
        pin += "2";
        placeholderChangeColor(pin.length());
        if (pin.length() == 5) startIntentIfPinIsOK(pin);
    };

    View.OnClickListener click3 = view -> {
        pin += "3";
        placeholderChangeColor(pin.length());
        if (pin.length() == 5) startIntentIfPinIsOK(pin);
    };

    View.OnClickListener click4 = view -> {
        pin += "4";
        placeholderChangeColor(pin.length());
        if (pin.length() == 5) startIntentIfPinIsOK(pin);
    };

    View.OnClickListener click5 = view -> {
        pin += "5";
        placeholderChangeColor(pin.length());
        if (pin.length() == 5) startIntentIfPinIsOK(pin);
    };

    View.OnClickListener click6 = view -> {
        pin += "6";
        placeholderChangeColor(pin.length());
        if (pin.length() == 5) startIntentIfPinIsOK(pin);
    };

    View.OnClickListener click7 = view -> {
        pin += "7";
        placeholderChangeColor(pin.length());
        if (pin.length() == 5) startIntentIfPinIsOK(pin);
    };

    View.OnClickListener click8 = view -> {
        pin += "8";
        placeholderChangeColor(pin.length());
        if (pin.length() == 5) startIntentIfPinIsOK(pin);
    };

    View.OnClickListener click9 = view -> {
        pin += "9";
        placeholderChangeColor(pin.length());
        if (pin.length() == 5) startIntentIfPinIsOK(pin);
    };


    View.OnClickListener delete = view -> {
        if (pin != null && pin.length() > 0) {
            pin = pin.substring(0, pin.length() - 1);
            placeholderChangeColor(pin.length());
        }
    };

    void startIntentIfPinIsOK(String pin) {
        if (keystore.checkPin(pin)) {
            Intent intent = new Intent(PinActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(getApplicationContext(), R.string.wrong_pin, Toast.LENGTH_SHORT).show();
        }
    }

    private void placeholderChangeColor(int length) {
        switch (length) {
            case 5:
                textView1.setBackgroundResource(R.drawable.shape2);
                textView2.setBackgroundResource(R.drawable.shape2);
                textView3.setBackgroundResource(R.drawable.shape2);
                textView4.setBackgroundResource(R.drawable.shape2);
                textView5.setBackgroundResource(R.drawable.shape2);
                break;
            case 4:
                textView1.setBackgroundResource(R.drawable.shape2);
                textView2.setBackgroundResource(R.drawable.shape2);
                textView3.setBackgroundResource(R.drawable.shape2);
                textView4.setBackgroundResource(R.drawable.shape2);
                textView5.setBackgroundResource(R.drawable.shape);
                break;
            case 3:
                textView1.setBackgroundResource(R.drawable.shape2);
                textView2.setBackgroundResource(R.drawable.shape2);
                textView3.setBackgroundResource(R.drawable.shape2);
                textView4.setBackgroundResource(R.drawable.shape);
                textView5.setBackgroundResource(R.drawable.shape);
                break;
            case 2:
                textView1.setBackgroundResource(R.drawable.shape2);
                textView2.setBackgroundResource(R.drawable.shape2);
                textView3.setBackgroundResource(R.drawable.shape);
                textView4.setBackgroundResource(R.drawable.shape);
                textView5.setBackgroundResource(R.drawable.shape);
                break;
            case 1:
                textView1.setBackgroundResource(R.drawable.shape2);
                textView2.setBackgroundResource(R.drawable.shape);
                textView3.setBackgroundResource(R.drawable.shape);
                textView4.setBackgroundResource(R.drawable.shape);
                textView5.setBackgroundResource(R.drawable.shape);
                break;
            case 0:
                textView1.setBackgroundResource(R.drawable.shape);
                textView2.setBackgroundResource(R.drawable.shape);
                textView3.setBackgroundResource(R.drawable.shape);
                textView4.setBackgroundResource(R.drawable.shape);
                textView5.setBackgroundResource(R.drawable.shape);
                break;
            default:
                break;
        }
    }
}