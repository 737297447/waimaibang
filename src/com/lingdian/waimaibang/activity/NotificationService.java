package com.lingdian.waimaibang.activity;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.PowerManager;

import com.lingdian.waimaibang.R;

public class NotificationService extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		System.out.println("进入开机启动通知栏项目");

	}

	private PowerManager pm;
	private PowerManager.WakeLock wl;

	@SuppressLint("NewApi")
	@SuppressWarnings("deprecation")
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		
		String pushMessage = intent.getStringExtra("STR_CALLER");
		
		pm = (PowerManager) getSystemService(this.POWER_SERVICE);
		wl = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP
				| PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "bright");
		wl.acquire();
		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		Notification notification = new Notification(R.drawable.ic_launcher,
				"外卖帮有新的消息！", System.currentTimeMillis());

		Intent i = new Intent(this, MainActivity.class);
		PendingIntent pi = PendingIntent.getActivity(this, 0, i,
				PendingIntent.FLAG_CANCEL_CURRENT);

		notification.setLatestEventInfo(this, "外卖帮提醒您",pushMessage.substring(10,pushMessage.length()-2)
				, pi);
		notification.defaults = notification.DEFAULT_SOUND
				| notification.DEFAULT_VIBRATE;

		manager.notify(1, notification);
        stopSelf();
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}
