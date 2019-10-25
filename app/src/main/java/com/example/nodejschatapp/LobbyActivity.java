package com.example.nodejschatapp;


import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LobbyActivity extends Activity {
Context cxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);
        cxt = this;

        Button btn1 = (Button)findViewById(R.id.Button1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(cxt,LobbyListActivity.class);
                startActivity(i);
            }
        });

        Button btn2 = (Button)findViewById(R.id.Button2);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(cxt);

                alert.setTitle("Create Lobby");

                LayoutInflater inflater = getLayoutInflater();
                final View dialogView= inflater.inflate(R.layout.customdialog, null);
                alert.setView(dialogView);

// Set an EditText view to get user input


                alert.setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                       HTTPHandler http = new HTTPHandler();
                        EditText ed1 = dialogView.findViewById(R.id.editText);
                        EditText ed2 = dialogView.findViewById(R.id.editText2);
                        if(ed1.getText().toString().isEmpty()){
                            Toast.makeText(cxt,"Please enter a room name",Toast.LENGTH_SHORT).show();
                        }else {
                            http.makePost(ed1.getText().toString(),ed2.getText().toString());
                        }
                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Canceled.
                    }
                });


                alert.show();
            }
        });


    }


}
