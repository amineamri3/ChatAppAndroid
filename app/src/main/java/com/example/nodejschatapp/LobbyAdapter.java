package com.example.nodejschatapp;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class LobbyAdapter extends ArrayAdapter<Lobby> {

    private Context mContext;
    private List<Lobby> LobbyList = new ArrayList<>();

    public LobbyAdapter(@NonNull Context context, @LayoutRes ArrayList<Lobby> list) {
        super(context, 0 , list);
        mContext = context;
        LobbyList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.listview_item,parent,false);

        Lobby currentLobby = LobbyList.get(position);

        ImageView lock = (ImageView)listItem.findViewById(R.id.lockedImage);
        if(currentLobby.isLocked())
        lock.setImageResource(R.drawable.lock);

        TextView name = (TextView) listItem.findViewById(R.id.RoomName);
        name.setText(currentLobby.getName());

        TextView num = (TextView) listItem.findViewById(R.id.num);
        num.setText(Integer.toString(currentLobby.getMembersNum()));

        ImageView release = (ImageView) listItem.findViewById(R.id.per);
        release.setImageResource(R.drawable.user);

        return listItem;
    }
}
