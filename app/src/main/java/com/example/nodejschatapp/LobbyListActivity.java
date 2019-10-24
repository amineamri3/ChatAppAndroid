package com.example.nodejschatapp;


import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class LobbyListActivity extends Activity {
    private ListView listView;
    private LobbyAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby_list);

        listView = (ListView) findViewById(R.id.lobbyList);
        ArrayList<Lobby> LobbyList = new ArrayList<>();

        LobbyList.add(new Lobby(2,"Room 1",false));
        LobbyList.add(new Lobby(0,"Room 2",true));
        LobbyList.add(new Lobby(9,"Room 3",false));



        mAdapter = new LobbyAdapter(this,LobbyList);
        listView.setAdapter(mAdapter);
    }
}
