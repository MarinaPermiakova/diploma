package com.example.android.com.diploma;

import android.content.Context;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class FileKeystore implements Keystore {
    private static final String FILE_NAME = "pin_code.txt";
    private final File root;

    FileKeystore(@NonNull Context context) {
        root = new File(context.getFilesDir(), FILE_NAME);
    }

    @Override
    public boolean hasPin(Context context) {
        return root.exists();
    }

    @Override
    public boolean checkPin(@Nullable String pin) {
        try (BufferedReader fileInputStream = new BufferedReader(new FileReader(root))) {
            return TextUtils.equals(pin, fileInputStream.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void saveNew(String pin, Context context) {
        try (FileWriter fileOutputStream = new FileWriter(root);
             BufferedWriter bw = new BufferedWriter(fileOutputStream)) {
            bw.write(pin);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

