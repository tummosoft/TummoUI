package com.tummosoft.Utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import anywheresoftware.b4a.BA;

@BA.ShortName("NotificationHelper")
public class NotificationHelper {

    public static final int NOTIFICATION_ID = 20111;

    public static void notifyShort(@NonNull Context context, @NonNull String title, @NonNull String msg, @DrawableRes int iconId) {
        notifyShort(context, title, msg, iconId, NOTIFICATION_ID, null);
    }

    public static void notifyShort(@NonNull Context context, @NonNull String title, @NonNull String msg, @DrawableRes int iconId,
                                   @NonNull PendingIntent pendingIntent) {
        notifyShort(context, title, msg, iconId, NOTIFICATION_ID, pendingIntent);
    }

    public static void notifyShort(@NonNull Context context, @NonNull String title, @NonNull String msg, @DrawableRes int iconId, int nId) {
        notifyShort(context, title, msg, iconId, nId, null);
    }

    public static void notifyShort(@NonNull Context context, @NonNull String title, @NonNull String msg, @DrawableRes int iconId, int nId,
                                   @Nullable PendingIntent pendingIntent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(context)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setContentTitle(title)
                .setContentText(msg)
                .setSmallIcon(iconId)
                .setContentIntent(pendingIntent)
                .build();
        notificationManager.notify(nId, notification);
    }
    
    public static void cancelNotification(@NonNull Context context, int id) {
        int finalId = id == 0 ? NOTIFICATION_ID : id;
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(finalId);
    }

    public static void cancelAllNotifications(@NonNull Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
    }
}