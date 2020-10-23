package club.hackslash.habita;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class MyBattery extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if ((intent.getAction()).equals(Intent.ACTION_POWER_CONNECTED)) {
            Toast.makeText(context, "Power is connected", Toast.LENGTH_SHORT).show();
            not(context,  R.drawable.ic_stat_name1, "Power connected", "Phone is Charging",0);
        } else if ((intent.getAction()).equals(Intent.ACTION_POWER_DISCONNECTED)) {
            Toast.makeText(context, "Power is disconnected", Toast.LENGTH_SHORT).show();

            not(context, R.drawable.ic_stat_name2, "Power disconnected", "Phone is disconnected from the charger", 1);

        } else if (intent.getAction().equals(Intent.ACTION_BATTERY_LOW)) {
            Toast.makeText(context, "Battery is low", Toast.LENGTH_SHORT).show();
            not(context, R.drawable.ic_stat_name3, "Battery low", "Please connect phone to the charger", 2);


        }

    }
    //function for sending notifications

    private void not(Context context, int pic, String title, String body, int notifiid ){
        Intent i= new Intent(context, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(context, 0, i, 0);

        NotificationManager notificationManager=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel("1", "My Notifications", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription(body);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            notificationChannel.setVibrationPattern(new long[] {0,1000,500,1000});
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);


        }
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context,  "1");
        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setDefaults(Notification.DEFAULT_ALL);
        notificationBuilder.setWhen(System.currentTimeMillis());
        notificationBuilder.setSmallIcon(pic);
        notificationBuilder.setTicker("Hearty 365");
        notificationBuilder.setPriority(Notification.PRIORITY_MAX);

        notificationBuilder.setContentTitle(title);
        notificationBuilder.setContentText(body);
        notificationBuilder.setColor(context.getResources().getColor(R.color.colorRed));
        notificationBuilder.setContentInfo("Info");
        notificationBuilder.setContentIntent(pi);
        notificationManager.notify(notifiid, notificationBuilder.build());


    }



}
