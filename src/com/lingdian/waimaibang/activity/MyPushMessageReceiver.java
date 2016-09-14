package com.lingdian.waimaibang.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import cn.bmob.push.PushConstants;

public class MyPushMessageReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        if(intent.getAction().equals(PushConstants.ACTION_MESSAGE)){
            Log.d("bmob", "客户端收到推送内容："+intent.getStringExtra(PushConstants.EXTRA_PUSH_MESSAGE_STRING));
    	    /* create Intent，调用AlarmAlert.class */
    	    Intent i = new Intent(context, NotificationService.class);
    	        
    	    Bundle bundleRet = new Bundle();    
    	    bundleRet.putString("STR_CALLER", intent.getStringExtra(PushConstants.EXTRA_PUSH_MESSAGE_STRING));
    	    i.putExtras(bundleRet);
    	    context.startService(i);
        }
    }

}
