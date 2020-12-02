package com.example.android.com.diploma;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class PinActivity extends AppCompatActivity {

    Button btn0;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btn7;
    Button btn8;
    Button btn9;
    Button btnDelete;

    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;

    String pin = "";
    String savedPin;
    private static final String FILE_NAME = "pin_code2.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);

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

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = openFileInput(FILE_NAME);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Intent intent = new Intent(PinActivity.this, settingsActivity.class);
            startActivity(intent);
            finish();
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
        try {
            savedPin = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
    }


    View.OnClickListener click0 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            pin += "0";
            placeholderChangeColor(pin.length());
            if (pin.equals(savedPin)) {
                Intent intent = new Intent(PinActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };


    View.OnClickListener click1 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            pin += "1";
            placeholderChangeColor(pin.length());
            if (pin.equals(savedPin)) {
                Intent intent = new Intent(PinActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };


    View.OnClickListener click2 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            pin += "2";
            placeholderChangeColor(pin.length());
            if (pin.equals(savedPin)) {
                Intent intent = new Intent(PinActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };

    View.OnClickListener click3 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            pin += "3";
            placeholderChangeColor(pin.length());
            if (pin.equals(savedPin)) {
                Intent intent = new Intent(PinActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
            Toast.makeText(getApplicationContext(), pin, Toast.LENGTH_SHORT).show();

        }
    };

    View.OnClickListener click4 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            pin += "4";
            placeholderChangeColor(pin.length());
            if (pin.equals(savedPin)) {
                Intent intent = new Intent(PinActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
            Toast.makeText(getApplicationContext(), pin, Toast.LENGTH_SHORT).show();

        }
    };

    View.OnClickListener click5 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            pin += "5";
            placeholderChangeColor(pin.length());
            if (pin.equals(savedPin)) {
                Intent intent = new Intent(PinActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
            Toast.makeText(getApplicationContext(), pin, Toast.LENGTH_SHORT).show();

        }
    };

    View.OnClickListener click6 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            pin += "6";
            placeholderChangeColor(pin.length());
            if (pin.equals(savedPin)) {
                Intent intent = new Intent(PinActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
            Toast.makeText(getApplicationContext(), pin, Toast.LENGTH_SHORT).show();

        }
    };

    View.OnClickListener click7 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            pin += "7";
            placeholderChangeColor(pin.length());
            if (pin.equals(savedPin)) {
                Intent intent = new Intent(PinActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
            Toast.makeText(getApplicationContext(), pin, Toast.LENGTH_SHORT).show();

        }
    };

    View.OnClickListener click8 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            pin += "8";
            placeholderChangeColor(pin.length());
            if (pin.equals(savedPin)) {
                Intent intent = new Intent(PinActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
            Toast.makeText(getApplicationContext(), pin, Toast.LENGTH_SHORT).show();

        }
    };

    View.OnClickListener click9 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            pin += "9";
            placeholderChangeColor(pin.length());
            if (pin.equals(savedPin)) {
                Intent intent = new Intent(PinActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
            Toast.makeText(getApplicationContext(), pin, Toast.LENGTH_SHORT).show();

        }
    };

    View.OnClickListener delete = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (pin != null && pin.length() > 0) {
                pin = pin.substring(0, pin.length() - 1);
            }
            placeholderChangeColor(pin.length());
            Toast.makeText(getApplicationContext(), pin, Toast.LENGTH_SHORT).show();
        }
    };

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