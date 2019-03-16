package edu.hzuapps.shiyan2;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



import java.io.FileInputStream;
import java.io.FileOutputStream;


public class MainActivity extends Activity {

    private EditText et_info;
    private Button btn_save;
    private Button btn_read;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_info = (EditText) findViewById(R.id.et_info);
        btn_save = (Button) findViewById(R.id.btn_save);
        btn_read = (Button) findViewById(R.id.btn_read);
        btn_save.setOnClickListener(new ButtonListener());
        btn_read.setOnClickListener(new ButtonListener());
    }

    private class ButtonListener implements View.OnClickListener {
        @SuppressLint("WrongConstant")
        public void onClick(View v){
            switch(v.getId()){
                case R.id.btn_save:
                    String saveinfo=et_info.getText().toString().trim();
                    FileOutputStream fos;
                    try{
                        fos=openFileOutput("data.txt", Context.MODE_APPEND);
                        fos.write(saveinfo.getBytes());
                        fos.close();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    Toast.makeText(MainActivity.this,"数据保存成功",0).show();
                    break;
                case R.id.btn_read:
                    String content="";
                    try{
                        FileInputStream fis=openFileInput("data.text");
                        byte[] buffer=new byte[fis.available()];
                        fis.read(buffer);
                        content=new String(buffer);
                        fis.close(); }catch(Exception e){
                        e.printStackTrace();
                    }
                    Toast.makeText(MainActivity.this,"保存的数据是："+content,0).show();
                    break;
                default:
                    break;
            }
        }
    }
}

