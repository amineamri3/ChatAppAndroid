package com.example.nodejschatapp;

import android.app.Activity;
import android.content.res.ObbInfo;
import android.util.Log;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import co.intentservice.chatui.ChatView;
import co.intentservice.chatui.models.ChatMessage;

public  class SocketManager {
    public static Socket socket;
    public static ChatActivity ui;
    public static LobbyListActivity LobbyAct;

    public SocketManager(ChatActivity ui) {
        this.ui = ui;
    }

    public static boolean connect(){
        try {
             socket = IO.socket("https://zerai-node-v3-chat-app.herokuapp.com");
            //socket = IO.socket("127.0.0.1:3000");
            socket.connect();
            Log.d("TAG1", "socket : " + socket.connected());

            socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    if (args.length > 0)
                        Log.d("TAG1", args[0].toString());
                    else
                        Log.d("TAG1", "NO CALLBACK");

                }
            });


            socket.on("message", new Emitter.Listener() {
                @Override
                public void call(final Object... args) {
                    ui.runOnUiThread(new Runnable() {
                        public void run() {
                            try {
                                JSONObject data = (JSONObject) args[0];
                                Log.d("D", data.toString());
                                ui.addMessageBubble(new ChatMessage(data.getString("text"), System.currentTimeMillis(), ChatMessage.Type.RECEIVED, data.getString("username")));
                            }catch (Exception e){
                                e.printStackTrace();
                            }

                        }
                    });

                }
            });
            socket.on("response", new Emitter.Listener() {
                @Override
                public void call(final Object... args) {
                    LobbyAct.runOnUiThread(new Runnable() {
                        public void run() {
                            try {
                                ArrayList<Lobby> l = new ArrayList<>();
                                JSONArray data = (JSONArray) args[0];
                                Log.d("D", data.toString());
                                for (int i =0; i<data.length();i++) {
                                    Lobby lob;
                                    JSONObject obj = data.getJSONObject(i);
                                    int count =0;
                                    boolean lock = false;
                                    if(obj.getInt("psw") == 1) lock = true;
                                    if(obj.getInt("inroom") > 0) count = obj.getInt("inroom");
                                    lob = new Lobby(count,obj.getString("roomname"),lock);
                                    l.add(lob);
                                }
                                LobbyAct.UpdateList(l);
                            }catch (Exception e){
                                e.printStackTrace();
                            }

                        }
                    });

                }
            });
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public static void updateLobby(LobbyListActivity q){
        LobbyAct = q;
        socket.emit("requestrooms");

    }



    public static void joinRoom(String room,String name){
        socket.emit("join", name, room);
    }

    public static void emitMessage(String msg){
        socket.emit("SendMessageAndroid",msg);
    }
    public static void disconnect(){
        socket.disconnect();
    }
}

