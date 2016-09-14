package com.lingdian.waimaibang.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

import com.lingdian.waimaibang.model.Register;
import com.lingdian.waimaibang.model.ShoucangModel;
import com.lingdian.waimaibang.model.User;
import com.lingdian.waimaibang.model.XiaoxiModel;

/**
 * 缓存工具类
 * 
 * @author zhou.ni 2015年5月13日
 */
public class SharedpreferncesUtil {

	public static final String PREFERNCE_FILE_NAME = "obj"; // 缓存文件名
	public static final String USER_GUIDE_FILE_NAME = "guide"; // 引导界面文件名

	// ----collection----
	public static final String KEY_COLLECTION_INFO = "collection"; // 收藏文件名
	// -----城市名-----
	public static final String CITY_NAME = "city_name";
	// -----保存注册登陆信息-----
	public static final String LOGIN_INFO = "login_info";

	// -----保存注册时的时间信息-----
	public static final String FIRST_LOGIN_TIME = "FIRST_LOGIN_TIME";

	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String KEY_XIAOXI = "xiaoxi"; // 收藏文件名

	public static void saveUser(Context context, String username) {
		saveObj(context, username, USERNAME);
	}

	public static void savePassword(Context context, String password) {
		saveObj(context, password, PASSWORD);
	}

	public static String getUsername(Context context) {
		return (String) readObj(context, USERNAME);
	}

	public static String getPassword(Context context) {
		return (String) readObj(context, PASSWORD);
	}

	public static void saveFirstTime(Context context, String firstTime) {
		saveObj(context, firstTime, FIRST_LOGIN_TIME);
	}

	public static String getFirstTime(Context context) {
		return (String) readObj(context, FIRST_LOGIN_TIME);
	}

	public static final String GEOCODE = "geocode";

	public static void saveGeoceode(Context context, String geocode) {
		saveObj(context, geocode, GEOCODE);
	}

	public static String getGeoceode(Context context) {
		return (String) readObj(context, GEOCODE);
	}

	public static final String LAT = "latitude";

	public static void saveLAT(Context context, String geocode) {
		saveObj(context, geocode, LAT);
	}

	public static String getLAT(Context context) {
		return (String) readObj(context, LAT);
	}

	public static final String LONG = "longitude";

	public static void saveLONG(Context context, String geocode) {
		saveObj(context, geocode, LONG);
	}

	public static String getLONG(Context context) {
		return (String) readObj(context, LONG);
	}

	public static final String DINGWEI_NAME = "dingwei_name";

	public static void saveDingweiName(Context context, String geocode) {
		saveObj(context, geocode, DINGWEI_NAME);
	}

	public static String getDingweiName(Context context) {
		return (String) readObj(context, DINGWEI_NAME);
	}

	/**
	 * 保存注册信息
	 * 
	 * @param context
	 * @param user
	 */
	public static void saveRegisterInfo(Context context, Register register) {
		saveObj(context, register, LOGIN_INFO);
	}

	/**
	 * 取出注册信息
	 * 
	 * @param context
	 * @return
	 */
	public static Register getRegisterInfo(Context context) {
		return (Register) readObj(context, LOGIN_INFO);
	}

	/**
	 * 保存用户信息
	 * 
	 * @param context
	 * @param user
	 */
	// public static void saveUserInfo(Context context, User user){
	// saveObj(context, user, KEY_USER_INFO);
	// }

	/**
	 * 取出用户信息
	 * 
	 * @param context
	 * @return
	 */
	// public static User getUserInfo(Context context){
	// return (User) readObj(context, KEY_USER_INFO);
	// }

	/**
	 * 取出access_token
	 * 
	 * @param context
	 * @return
	 */
	// public static String getAccessTokenInfo(Context context){
	// return (String) readObj(context, ACCESS_TOKEN);
	// }

	/**
	 * 保存access_token
	 * 
	 * @param context
	 * @param user
	 */
	// public static void saveAccessToken(Context context, String access_token){
	// saveObj(context, access_token, ACCESS_TOKEN);
	// }
	/**
	 * 取出城市名
	 * 
	 * @param context
	 * @return
	 */
	public static String getCityInfo(Context context) {
		return (String) readObj(context, CITY_NAME);
	}

	/**
	 * 保存城市名
	 * 
	 * @param context
	 * @param user
	 */
	public static void saveCityInfo(Context context, String access_token) {
		saveObj(context, access_token, CITY_NAME);
	}

	/**
	 * 保存收藏信息
	 * 
	 * @param context
	 * @param user
	 */
	public static void saveCollectionInfo(Context context, ShoucangModel obj) {
		List<ShoucangModel> shoucangList = getCollectionInfo(context);
		if (shoucangList == null) {
			shoucangList = new ArrayList<ShoucangModel>();
		}
		shoucangList.add(obj);
		saveObj(context, shoucangList, KEY_COLLECTION_INFO);
	}

	/**
	 * 取出收藏信息
	 * 
	 * @param context
	 * @return
	 */
	public static List<ShoucangModel> getCollectionInfo(Context context) {
		return (List<ShoucangModel>) readObj(context, KEY_COLLECTION_INFO);
	}

	/**
	 * 删除某一条收藏信息
	 * 
	 * @param context
	 * @param user
	 */
	public static void deleteCollectionInfo(Context context, int position) {
		List<ShoucangModel> shoucangList = getCollectionInfo(context);
		if (shoucangList != null) {
			shoucangList.remove(position);
		}
		saveObj(context, shoucangList, KEY_COLLECTION_INFO);
	}

	/**
	 * 保存消息
	 * 
	 * @param context
	 * @param user
	 */
	public static void saveXiaoxiInfo(Context context, String objList,
			String key) {

		saveObj(context, objList, key);
	}

	/**
	 * 取出消息
	 * 
	 * @param context
	 * @return
	 */
	public static String getXiaoxi(Context context, String key) {
		return (String) readObj(context, key);
	}

	public static boolean isHaveNoRead(Context context, String key, String text) {
		boolean isHave = false;
		String shoucangList = getXiaoxi(context, key);
		if (shoucangList == null) {
			isHave = false;
		} else {
			if (text.equals(shoucangList)) {
				isHave = true;
				return isHave;
			}
		}
		return isHave;
	}


	/**
	 * 清除某个key对应的缓存
	 * 
	 * @param key
	 * @param context
	 */
	public static void clearByKey(String key, Context context) {
		SharedPreferences prefe = context.getSharedPreferences(
				PREFERNCE_FILE_NAME, 0);
		Editor editor = prefe.edit();
		editor.putString(key, "");
		editor.commit();
	}

	/**
	 * 读取一个对象
	 * 
	 * @param context
	 * @return
	 */
	public static Object readObj(Context context, String key) {
		Object obj = null;
		SharedPreferences prefe = context.getSharedPreferences(
				PREFERNCE_FILE_NAME, 0);
		String replysBase64 = prefe.getString(key, "");
		if (TextUtils.isEmpty(replysBase64)) {
			return obj;
		}
		// 读取字节
		byte[] base64 = Base64.decodeBase64(replysBase64.getBytes());
		// 封装到字节读取流
		ByteArrayInputStream bais = new ByteArrayInputStream(base64);
		try {
			// 封装到对象读取流
			ObjectInputStream ois = new ObjectInputStream(bais);
			try {
				// 读取对象
				obj = ois.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (StreamCorruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return obj;
	}

	/**
	 * 存储一个对象
	 * 
	 * @param context
	 * @param old
	 * @param key
	 */
	public static <T> void saveObj(Context context, T obj, String key) {
		T _obj = obj;

		SharedPreferences prefe = context.getSharedPreferences(
				PREFERNCE_FILE_NAME, 0);
		// 创建字节输出流
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			// 创建对象输出流,封装字节流
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			// 将对象写入字节流
			oos.writeObject(_obj);
			// 将字节流编码成base64的字符串
			String list_base64 = new String(Base64.encodeBase64(baos
					.toByteArray()));
			Editor editor = prefe.edit();
			editor.putString(key, list_base64);
			editor.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设置进入过引导界面
	 * 
	 * @param context
	 */
	public static void setGuided(Context context) {
		SharedPreferences prefe = context.getSharedPreferences(
				USER_GUIDE_FILE_NAME, 0);
		Editor editor = prefe.edit();
		editor.putBoolean("isguide", true);
		editor.commit();
	}

	/**
	 * 判断是否进入引导界面
	 * 
	 * @param context
	 * @return
	 */
	public static boolean getGuided(Context context) {
		SharedPreferences prefe = context.getSharedPreferences(
				USER_GUIDE_FILE_NAME, 0);
		boolean b = prefe.getBoolean("isguide", false);
		return b;
	}

	public static boolean getReadMode(Context context, String key,
			boolean defValue) {
		SharedPreferences prefe = context.getSharedPreferences(
				PREFERNCE_FILE_NAME, Context.MODE_PRIVATE);
		return prefe.getBoolean(key, defValue);
	}

	public static void putReadMode(Context context, String key, boolean state) {
		SharedPreferences prefe = context.getSharedPreferences(
				PREFERNCE_FILE_NAME, Context.MODE_PRIVATE);
		Editor editor = prefe.edit();
		editor.putBoolean(key, state);
		editor.commit();
	}

	public static int getFontSize(Context context, String key, int defValue) {
		SharedPreferences prefe = context.getSharedPreferences(
				PREFERNCE_FILE_NAME, Context.MODE_PRIVATE);
		return prefe.getInt(key, defValue);
	}

	public static void putFontSize(Context context, String key, int state) {
		SharedPreferences prefe = context.getSharedPreferences(
				PREFERNCE_FILE_NAME, Context.MODE_PRIVATE);
		Editor editor = prefe.edit();
		editor.putInt(key, state);
		editor.commit();
	}

}
