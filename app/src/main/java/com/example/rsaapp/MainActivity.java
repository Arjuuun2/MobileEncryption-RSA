package com.example.rsaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigInteger;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText etInput,etOutput;
    private String publicKey="";
    private String privateKey="";
    private byte[] encodeData=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        etInput=(EditText) findViewById(R.id.txt1);
        etOutput=(EditText) findViewById(R.id.output);
        try {
            Map<String,Object>keyMap=rsa.initKey();
            publicKey=rsa.getPublicKey(keyMap);
            privateKey=rsa.getPrivateKey(keyMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void encrypt(View view){

        byte[]rsaData=etInput.getText().toString().getBytes();
        try {
            encodeData = rsa.encryptByPublicKey(rsaData, publicKey);
            String encodeStr=new BigInteger(1,encodeData).toString();
            etOutput.setText(encodeStr);
        }catch(Exception e){}
    }
    public void decrypt(View view){
//        encodeData = txt;
//        String str=txt.toString();
        try {
            byte[]decodeData= rsa.decryptByPrivateKey(encodeData, getPrivatekey());
//            str = decodeData.toString();
            String decodeStr=new String(decodeData);
            Intent obj= new Intent(getApplicationContext(),Qrgenerator.class);
            obj.putExtra("decryptedmsg",decodeStr);
            startActivity(obj);
//            etOutput.setText(decodeStr);
        }catch(Exception e){}
//        return str;
    }

    public String getPublickey() {
        return publicKey;
    }


    public String getPrivatekey() {
        return privateKey;
    }


}