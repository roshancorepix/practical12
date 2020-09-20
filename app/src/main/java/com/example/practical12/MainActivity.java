package com.example.practical12;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText et_content;
    TextView tv_file_content;
    Button btn_store,btn_append,btn_display;
    final String TAG = getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_content = findViewById(R.id.et_content);
        tv_file_content = findViewById(R.id.tv_file_content);
        btn_store = findViewById(R.id.btn_store);
        btn_append = findViewById(R.id.btn_append);
        btn_display = findViewById(R.id.btn_display);

        btn_store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File file = new File("data/data/"+getApplicationContext().getPackageName()+"/test.txt");
                if (!file.exists()) {
                    try {
                        file.createNewFile();
                        Log.e(TAG,"file is Created: "+file.getPath());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        btn_append.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // File externalStorageDir = Environment.getExternalStorageDirectory();
                File myFile = new File("data/data/"+getApplicationContext().getPackageName()+"/test.txt");

                if(myFile.exists())
                {
                    try
                    {
                        FileOutputStream fOut = new FileOutputStream(myFile);
                        OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
                        myOutWriter.append(et_content.getText().toString());
                        myOutWriter.close();
                        fOut.close();
                    } catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                else
                {
                    try {
                        myFile.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        btn_display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File myFile = new File("data/data/"+getApplicationContext().getPackageName()+"/test.txt");
                if (myFile.exists()) {
                    try {

                        FileInputStream fin = new FileInputStream (myFile);
                        int c;
                        String temp = "";
                        while ((c = fin.read()) != -1) {
                            temp = temp + Character.toString((char) c);
                        }
                        tv_file_content.setText(temp);
                        Toast.makeText(getBaseContext(), "file read", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Log.e(TAG, e.getLocalizedMessage());
                    }
                }else {
                    Log.e(TAG, "file not found");
                }
            }
        });
    }
}