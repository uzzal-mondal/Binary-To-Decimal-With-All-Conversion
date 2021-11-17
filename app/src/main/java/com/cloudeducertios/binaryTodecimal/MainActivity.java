package com.cloudeducertios.binaryTodecimal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    TextInputEditText etBinary;
    TextInputEditText etDecimal;
    Button btnConvert, btnClear, btnPaste, btnCopy;
    ClipboardManager clipboardManager;
    String stringBinary, stringDecimal;
    int intDecimal;
    ImageView imageCopy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etBinary = findViewById(R.id.editTextBinaryNum);
        etDecimal = findViewById(R.id.editTextDecimalNum);
        btnConvert = findViewById(R.id.button_convert);
        btnClear = findViewById(R.id.button_clear);
        btnPaste = findViewById(R.id.button_paste);
        btnCopy = findViewById(R.id.btnCopy);
        imageCopy = findViewById(R.id.image_copy);
        clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

        showImageCopy();

    }

    public void convertBinary(View view) {
        stringBinary = etBinary.getText().toString();

        try {
            if (!stringBinary.isEmpty()) {
                intDecimal = Integer.parseInt(stringBinary, 2);
                etDecimal.setText(String.valueOf(intDecimal));
            }
        } catch (Exception e) {
            Toast.makeText(this, "please input binary number , Error!!",
                    Toast.LENGTH_SHORT).show();
        }
        /**
         * another way to ...
         */

        /* int decimal = Integer.parseInt(sb, 2);
        etDecimal.setText(String.valueOf(decimal));*/
       /* int dec = Integer.parseInt(etBinary.getText().toString());
        String bin = Integer.toBinaryString(dec);
        String oct = Integer.toOctalString(dec);
        String hex = Integer.toHexString(dec);
        etDecimal.setText(bin);*/
    }

    public void showClear(View view) {
        etBinary.setText("");
        etDecimal.setText("");
    }

    public void showCopyResult(View view) {
        stringDecimal = etDecimal.getText().toString();
        if (!stringDecimal.isEmpty()) {
            ClipData clipData = ClipData.newPlainText("Decimal", stringDecimal);
            clipboardManager.setPrimaryClip(clipData);
            Toast.makeText(this, "Number Copied to Clipboard.", Toast.LENGTH_SHORT).show();
            etDecimal.setText("");

        }

    }

    public void showPasteNumber(View view) {
        String pasteData;
        if (clipboardManager.hasPrimaryClip()) {
            if (clipboardManager.getPrimaryClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                ClipData.Item item = clipboardManager.getPrimaryClip().getItemAt(0);
                pasteData = item.getText().toString();
                if (!pasteData.isEmpty()) {
                    etBinary.setText(pasteData);
                }
            }
        }
    }

    public void showImageCopy() {
        imageCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stringBinary = etBinary.getText().toString();
                if (!stringBinary.isEmpty()) {
                    ClipData clipData = ClipData.newPlainText("Binary", stringBinary);
                    clipboardManager.setPrimaryClip(clipData);
                    Toast.makeText(getApplicationContext(), "Number Copied to Clipboard.",
                            Toast.LENGTH_SHORT).show();
                    etBinary.setText("");
                }
            }
        });
    }
}