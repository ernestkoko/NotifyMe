package com.example.notifyme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button_notify, button_cancel, button_update;
    private NotificationManager mNotificationManager;

    //notification channel
    public static final String PRIMARY_NOTIFICATION_ID = "primary_notification_channel";
    private static final int NOTIFICATION_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //creates notification
        createNotificationChannel();

        //initialise the button
        button_notify = findViewById(R.id.notify);
        button_notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification();
            }
        });

        button_update = findViewById(R.id.update);
        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        button_cancel = findViewById(R.id.cancel);
        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
    //method
    public void sendNotification(){
        NotificationCompat.Builder builder = getNotificationBuilder();
        mNotificationManager.notify(NOTIFICATION_ID, builder.build() );

    }
   private NotificationCompat.Builder getNotificationBuilder(){
       Intent notificationIntent = new Intent(this, MainActivity.class);
       PendingIntent pendingIntent = PendingIntent.getActivity(this,NOTIFICATION_ID,
               notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(
                this,PRIMARY_NOTIFICATION_ID)
                .setContentTitle("You have been notified")
                .setContentText("This is your notification Text")
                .setSmallIcon(R.drawable.ic_android)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL);

        return notifyBuilder;

   }
    public void  createNotificationChannel(){
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //checking for the version of android
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            //create a notification channel
            NotificationChannel notificationChannel = new NotificationChannel(
                    PRIMARY_NOTIFICATION_ID, "Ernest Notification",
                    NotificationManager.IMPORTANCE_HIGH
            );
            notificationChannel.enableVibration(true);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setDescription("Notification from Ernest");
        }
    }

    public void updateNotification(){

    }
    public void cancelNotification(){
        mNotificationManager.cancel(NOTIFICATION_ID);
    }
}
