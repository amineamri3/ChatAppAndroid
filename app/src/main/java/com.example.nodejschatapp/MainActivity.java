package com.example.nodejschatapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    Button btn;
    EditText et1,et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button)findViewById(R.id.joinbtn);
        et1 = (EditText)findViewById(R.id.editText1);
        et2 = (EditText)findViewById(R.id.editText2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et1.getText().toString().equals("") || et2.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Both fields cannot be empty",Toast.LENGTH_SHORT).show();
                }else{

                  /* Intent i = new Intent(MainActivity.this,ChatActivity.class);
                   i.putExtra("name",et1.getText().toString());
                   i.putExtra("room",et2.getText().toString());
                   startActivity(i);*/
                }
            }
        });

    }



}
