package com.example.nodejschatapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
    public Context cxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        cxt = this;
        final Button btn1 = (Button)findViewById(R.id.joinbtn);
        final EditText et1 = (EditText)findViewById(R.id.editText1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et1.getText().toString().isEmpty() ){
                    Toast.makeText(getApplicationContext(),"you must enter a username", Toast.LENGTH_LONG);
                }else{
                    Intent i = new Intent(cxt,LobbyActivity.class);
                    i.putExtra("username",et1.getText().toString());
                    startActivity(i);
                }
            }
        });
    }
}
