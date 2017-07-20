package com.ar.movieapp.fcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.ar.movieapp.R;
import com.ar.movieapp.activity.MainActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by aderifaldi on 08/12/2016.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private int notificationId, requestCode;
    private String url, pageName, title, message;
    private Uri defaultSoundUri;
    private NotificationManager notificationManager;
    private NotificationCompat.Builder notificationBuilder;
    private Intent intent;
    private PendingIntent pendingIntent;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        title = remoteMessage.getNotification().getTitle();
        message = remoteMessage.getNotification().getBody();

//        notificationId = GlobalVariable.getNotificationId(this);
//        requestCode = GlobalVariable.getRequestCode(this);
//
//        GlobalVariable.saveNotificationId(this, notificationId + 1);
//        GlobalVariable.saveRequestCode(this, requestCode + 1);
//
//        url = remoteMessage.getData().get("url");
//        pageName = remoteMessage.getData().get("title");

        sendNotification();

    }

    private void sendNotification() {

        intent = new Intent(this, MainActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        pendingIntent = PendingIntent.getActivity(this, requestCode, intent, PendingIntent.FLAG_ONE_SHOT);

        defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(notificationId, notificationBuilder.build());
    }
}
