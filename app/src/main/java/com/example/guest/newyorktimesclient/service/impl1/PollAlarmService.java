package com.example.guest.newyorktimesclient.service.impl1;

/**
 * Created by l1maginaire on 3/9/18.
 */

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

public class PollAlarmService extends IntentService {
    private static final String TAG = "PollAlarmService";

    public static Intent newIntent(Context context) {
        return new Intent(context, PollAlarmService.class);
    }

    public static void setServiceAlarm(Context context, boolean isOn) {
        Intent i = PollAlarmService.newIntent(context);
        PendingIntent pi = PendingIntent.getService(context, 0, i, 0);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        if (isOn) {
            alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME,
                    SystemClock.elapsedRealtime(), PollService.POLL_INTERVAL, pi);
        } else {
            alarmManager.cancel(pi);
            pi.cancel();
        }
    }

    public static boolean isServiceAlarmOn(Context context) {
        Intent i = PollAlarmService.newIntent(context);
        PendingIntent pi = PendingIntent.getService(context, 0, i, PendingIntent.FLAG_NO_CREATE);
        return (pi != null);
    }

    public PollAlarmService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        PollService.doWork(this);
    }
}