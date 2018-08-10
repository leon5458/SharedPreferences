package com.hly.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private EditText input;
    private Button save, get;
    private TextView show;

    private EditText helperinput;
    private Button helpersave, helperget;
    private TextView helpershow;
    private SharedPreferencesHelper sharedPreferencesHelper;


    private EditText fileinput;
    private Button filesave, fileget;
    private TextView fileshow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = findViewById(R.id.edt);
        save = findViewById(R.id.save_btn);
        get = findViewById(R.id.get_btn);
        show = findViewById(R.id.show);
        sharedPreferences = getSharedPreferences("input", Context.MODE_PRIVATE);


        helperinput = findViewById(R.id.helper_edt);
        helpersave = findViewById(R.id.helper_save_btn);
        helperget = findViewById(R.id.helper_get_btn);
        helpershow = findViewById(R.id.helper_show);


        fileinput = findViewById(R.id.file_edt);
        filesave = findViewById(R.id.file_save_btn);
        fileget = findViewById(R.id.file_get_btn);
        fileshow = findViewById(R.id.file_show);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 获取输入的对象
                 */
                SharedPreferences.Editor editor = sharedPreferences.edit();
                /**
                 * 通过edt对象写入数据
                 */
                editor.putString("Value", input.getText().toString().trim());
                /**
                 *提交数据存入到xml文件中
                 */
                editor.commit();
            }
        });
        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 取出数据,第一个参数是写入是的键，第二个参数是如果没有获取到数据就默认返回的值。
                 */
                String value = sharedPreferences.getString("Value", "Null");
                show.setText(value);
                input.setText(value);
            }
        });

        helpersave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferencesHelper = new SharedPreferencesHelper(MainActivity.this, "value");
                String helper_edt = helperinput.getText().toString().trim();
                sharedPreferencesHelper.saveData("input", helper_edt);
            }
        });

        helperget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                helpershow.setText(sharedPreferencesHelper.getData("input", "").toString().trim());
            }
        });


        /**
         * 文件的写入
         */
        filesave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String saveInfo =fileinput.getText().toString().trim();
                FileOutputStream fos;
                try {
                    fos=openFileOutput("data.txt",MODE_APPEND);//把文件输出到data中
                    fos.write(saveInfo.getBytes());//将我们写入的字符串变成字符数组）
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Toast.makeText(MainActivity.this,"数据保存成功",Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * 文件的读取
         */
        fileget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content="";
                try {
                    FileInputStream fis=openFileInput("data.txt");
                    byte [] buffer=new  byte[fis.available()];
                    fis.read(buffer);
                    content=new String(buffer);
                    fis.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Toast.makeText(MainActivity.this,"保存的数据是"+content,Toast.LENGTH_SHORT).show();

            }
        });

    }


}
