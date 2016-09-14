package com.lingdian.waimaibang.utils;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.muzhi.mtools.utils.T;

public class PhoneUtils {
	public static void callPhone(String mobilePh,Context context) {
		try {
			Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mobilePh));
			context.startActivity(intent);
		} catch (Exception e) {
			T.showShort(context,"您的设备无法使用电话功能");
		}
	}

	public static void goSystemSMS(String mobilePh,Context context) {
		try {
			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.putExtra("address", mobilePh);
			intent.putExtra("sms_body", "");
			intent.setType("vnd.android-dir/mms-sms");
			context.startActivity(intent);
		} catch (Exception e) {
			T.showShort(context,"您的设备无法使用短信功能");
		}
	}
}
