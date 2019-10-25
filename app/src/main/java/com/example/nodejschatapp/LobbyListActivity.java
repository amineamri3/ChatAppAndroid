package com.example.nodejschatapp;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class LobbyListActivity extends Activity {
    public static Context cxt;
    private static ListView listView;
    private static LobbyAdapter mAdapter;
    private static ArrayList<Lobby> LobbyList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby_list);
        cxt = this;
        listView = (ListView) findViewById(R.id.lobbyList);
        LobbyList = new ArrayList<>();

        SocketManager.updateLobby(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                if (LobbyList.get(position).isLocked()){
                    AlertDialog.Builder alert = new AlertDialog.Builder(cxt);

                    alert.setTitle("Joining :"+LobbyList.get(position).name);
                    alert.setMessage("Please enter room password.");
                    LayoutInflater inflater = getLayoutInflater();
                    final View dialogView= inflater.inflate(R.layout.confirmpassword, null);
                    alert.setView(dialogView);
                    alert.setPositiveButton("Join", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            EditText ed1 = dialogView.findViewById(R.id.editText);
                            Intent i = new Intent();
                            i.putExtra("roomname",LobbyList.get(position).name);
                            i.putExtra("password",ed1.getText().toString());
                            startActivity(i);
                        }
                    });

                    alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            // Canceled.
                        }
                    });
                    alert.show();

                }else{

                    AlertDialog.Builder alert = new AlertDialog.Builder(cxt);

                    alert.setTitle("Joining :"+LobbyList.get(position).name);
                    alert.setMessage("Are you sure ?");
                    LayoutInflater inflater = getLayoutInflater();
                    final View dialogView= inflater.inflate(R.layout.confirmpassword, null);
                    alert.setView(dialogView);
                    alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            Intent i = new Intent();
                            i.putExtra("roomname",LobbyList.get(position).name);
                            i.putExtra("password","");
                            startActivity(i);
                        }
                    });

                    alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            // Canceled.
                        }
                    });
                    alert.show();



                }
            }
        });

    }

    public static void UpdateList(ArrayList<Lobby> l){
        LobbyList = l;
        mAdapter = new LobbyAdapter(cxt,LobbyList);
        listView.setAdapter(mAdapter);

    }
}
