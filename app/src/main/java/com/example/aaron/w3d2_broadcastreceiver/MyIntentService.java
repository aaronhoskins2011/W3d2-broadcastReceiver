package com.example.aaron.w3d2_broadcastreceiver;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;


public class MyIntentService extends IntentService {
    List<RandomObject> randomObjects = new ArrayList<RandomObject>();
    public MyIntentService() {
        super("MyIntentService");
    }



    @Override
    protected void onHandleIntent(Intent intent) {
        initRandomObjects();
        intent.putExtra("randomObjects", (Parcelable) randomObjects);


    }

    public void initRandomObjects(){
        randomObjects.add(new RandomObject("five_unread_messages", "Five Unread Massages", "Bet he answers his phone next time", "Funny"));
        randomObjects.add(new RandomObject("alert", "Basic Alert Image", "Self Explaining", "Alert"));
        randomObjects.add(new RandomObject("logo", "Coding in Progress", "Sometime it feels like this", "Funny"));
        randomObjects.add(new RandomObject("memelocation", "Where I get My Memes", "This is what makes it interesting", "Funny"));
        randomObjects.add(new RandomObject("wwfsd", "WWFSMD", "What Would he do?", "Funny"));

    }


}
