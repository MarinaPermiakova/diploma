package com.example.android.com.diploma;

import android.content.Context;

import java.io.FileNotFoundException;

interface Keystore {
    boolean hasPin(Context context);
    boolean checkPin(String pin);
    void saveNew (String pin, Context context) throws FileNotFoundException;
}
