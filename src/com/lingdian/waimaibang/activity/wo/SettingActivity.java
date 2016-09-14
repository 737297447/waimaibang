package com.lingdian.waimaibang.activity.wo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.http.Header;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lingdian.waimaibang.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.lingdian.waimaibang.activity.AdminImgActivity;
import com.lingdian.waimaibang.activity.BaseActivity;
import com.lingdian.waimaibang.model.GerenInfo;
import com.lingdian.waimaibang.model.GsonUtil;
import com.lingdian.waimaibang.model.Register;
import com.lingdian.waimaibang.model.UserIconModel;
import com.lingdian.waimaibang.tools.CircleImageView;
import com.lingdian.waimaibang.utils.AsyncHttpUtil;
import com.lingdian.waimaibang.utils.BaseUrlAndKey;
import com.lingdian.waimaibang.utils.CacheUtils;
import com.lingdian.waimaibang.utils.OptionsUtil;
import com.lingdian.waimaibang.utils.SharedpreferncesUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.analytics.MobclickAgent;

public class SettingActivity extends BaseActivity implements OnClickListener {

	private LinearLayout layout_touxiang, layout_jibenziliao,
			layout_xiugaimima, layout_tuichu,layout_admin;
	private ImageView man_or_woman_icon;
	private TextView user_name;
	private CircleImageView user_img;
	private GerenInfo gerenInfo;
	private Register register;
	private int user_id;
	private String access_token;
	/** 编辑资料 */
	public static final int Type_Edit_Ziliao = 10;
	public SelectTuichuPopupWindow tuichuSelectWindow;
	public SelectCmaeraPopupWindow cmaeraPopupWindow;
	public String dateTime;
	public String iconUrl;
	/** 从照相机中照 */
	public static final int Type_Camera = 11;
	/** 从相册中中选取 */
	public static final int Type_Xiangce = 12;
	/** 裁剪照片 */
	public static final int Type_Caijian = 13;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		findViewById();
		setListener();
		init();
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		title_bar_left = (ImageView) findViewById(R.id.title_bar_left);
		title_bar_text = (TextView) findViewById(R.id.title_bar_text);
		title_bar_text.setText("设置");

		layout_touxiang = (LinearLayout) findViewById(R.id.layout_touxiang);
		layout_jibenziliao = (LinearLayout) findViewById(R.id.layout_jibenziliao);
		layout_xiugaimima = (LinearLayout) findViewById(R.id.layout_xiugaimima);
		layout_tuichu = (LinearLayout) findViewById(R.id.layout_tuichu);
		man_or_woman_icon = (ImageView) findViewById(R.id.man_or_woman_icon);
		user_name = (TextView) findViewById(R.id.user_name);
		user_img = (CircleImageView) findViewById(R.id.user_img);
		
		layout_admin = (LinearLayout) findViewById(R.id.layout_admin);
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		title_bar_left.setOnClickListener(this);
		layout_touxiang.setOnClickListener(this);
		layout_jibenziliao.setOnClickListener(this);
		layout_xiugaimima.setOnClickListener(this);
		layout_tuichu.setOnClickListener(this);
		layout_admin.setOnClickListener(this);
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		Date date1 = new Date(System.currentTimeMillis());
		dateTime = date1.getTime() + "";
		register = SharedpreferncesUtil.getRegisterInfo(SettingActivity.this);
		if (register != null) {
			if (register.getUser() != null) {
				user_id = register.getUser().getId();
			}
			if(register.getAccess_token() != null){
				access_token = register.getAccess_token().getToken();
			}
		}
		http_get_gereninfo();
	
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.title_bar_left:
			finish();
			break;
		case R.id.layout_touxiang:
			cmaeraPopupWindow = new SelectCmaeraPopupWindow(
					SettingActivity.this, itemsOnClick1);
			// 显示窗口,设置layout在PopupWindow中显示的位置
			cmaeraPopupWindow
					.showAtLocation(SettingActivity.this
							.findViewById(R.id.setting_main_layout),
							Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
			break;
		case R.id.layout_jibenziliao:
			Intent i = new Intent(SettingActivity.this,
					EditZiliaoActivity.class);
			i.putExtra("sex", gerenInfo.getUser().getSex() + "");
			i.putExtra("intro", gerenInfo.getUser().getIntro());
			startActivityForResult(i, Type_Edit_Ziliao);

			break;
		case R.id.layout_xiugaimima:
			Intent i1 = new Intent(SettingActivity.this,
					EditPasswordActivity.class);
			startActivity(i1);

			break;
		case R.id.layout_tuichu:
			tuichuSelectWindow = new SelectTuichuPopupWindow(
					SettingActivity.this, itemsOnClick);
			// 显示窗口,设置layout在PopupWindow中显示的位置
			tuichuSelectWindow
					.showAtLocation(SettingActivity.this
							.findViewById(R.id.setting_main_layout),
							Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

			break;
		case R.id.layout_admin:
			Intent i2 = new Intent(SettingActivity.this,
					AdminImgActivity.class);
			startActivity(i2);

			break;
		default:
			break;
		}

	}

	// 退出为弹出窗口实现监听类
	private OnClickListener itemsOnClick = new OnClickListener() {

		public void onClick(View v) {
			tuichuSelectWindow.dismiss();
			switch (v.getId()) {
			case R.id.btn_tuichu:
				http_tuichu_login();
				break;
			case R.id.btn_quxiao:

				break;
			}
		}
	};

	// 选取照片
		private OnClickListener itemsOnClick1 = new OnClickListener() {

			public void onClick(View v) {
				cmaeraPopupWindow.dismiss();
				switch (v.getId()) {
				case R.id.btn_camera:
					getAvataFromCamera();
					break;
				case R.id.btn_xiangce:
					getAvataFromAlbum();
					break;
				case R.id.btn_camera_quxiao:
					break;
				}
			}
		};

	
	/** 从相册中选取*/
	private void getAvataFromAlbum() {
		Intent intent2 = new Intent(Intent.ACTION_GET_CONTENT);
		intent2.setType("image/*");
		startActivityForResult(intent2, Type_Xiangce);
	}
	/** 从照相机中照*/
	private void getAvataFromCamera() {
		File f = new File(CacheUtils.getCacheDirectory(SettingActivity.this, true, "icon")
				+ dateTime);
		if (f.exists()) {
			f.delete();
		}
		try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Uri uri = Uri.fromFile(f);
		Log.e("uri", uri + "");
		Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		camera.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		startActivityForResult(camera, Type_Camera);
	}
	
	/** 选取玩照片进行裁剪*/
	public void startPhotoZoom(Uri uri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", 120);
		intent.putExtra("outputY", 120);
		intent.putExtra("crop", "true");
		intent.putExtra("scale", true);
		intent.putExtra("scaleUpIfNeeded", true);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, Type_Caijian);

	}
	
	/** 获取个人信息 */
	public void http_get_gereninfo() {
		String url = BaseUrlAndKey.BAES_URL + "/v1/users/" + user_id + ".json";
		RequestParams params = new RequestParams();
		params.put("id", user_id);
		AsyncHttpUtil.get(url, params, new TextHttpResponseHandler() {

			@Override
			public void onSuccess(int arg0, Header[] arg1, String arg2) {
				// TODO Auto-generated method stub
				gerenInfo = GsonUtil.getGerenInfo(arg2);

				user_name.setText(gerenInfo.getUser().getNickname());
				if (gerenInfo.getUser().getPhoto() != null) {
					if (gerenInfo.getUser().getPhoto().getFile_url() != null) {
						ImageLoader.getInstance().displayImage(
								gerenInfo.getUser().getPhoto().getFile_url(),
								user_img, OptionsUtil.PicNormal());
					}
				}

				if (gerenInfo.getUser().getSex() == 0) {
					man_or_woman_icon
							.setImageResource(R.drawable.android_common_icon_male);
				} else if (gerenInfo.getUser().getSex() == 1) {
					man_or_woman_icon
							.setImageResource(R.drawable.android_common_icon_female);
				}
				
				if(gerenInfo.getUser().getLevel() == 100){
					layout_admin.setVisibility(View.VISIBLE);
				}else{
					layout_admin.setVisibility(View.GONE);
				}
				
			}

			@Override
			public void onFailure(int arg0, Header[] arg1, String arg2,
					Throwable arg3) {
				// TODO Auto-generated method stub
				errorDialog("获取失败，请检查网络！");
			}
		});

	}
	
	
	/** 保存个人头像 */
	public void http_save_usericon(File file) {
		String url = BaseUrlAndKey.BAES_URL + "/v1/users/" + user_id + "/photo.json";
//		AsyncHttpClient client = AsyncHttpUtil.getClient();
//		client.addHeader("Content-Type",
//				"application/x-www-form-urlencoded; charset=utf-8");
		
		RequestParams params = new RequestParams();
		try {
			params.put("id", user_id);
			params.put("access_token", access_token);
			
			params.put("file", file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AsyncHttpClient client = new AsyncHttpClient(); 

		
		client.put(url, params, new TextHttpResponseHandler() {

			@Override
			public void onSuccess(int arg0, Header[] arg1, String arg2) {
				// TODO Auto-generated method stub
				UserIconModel userIconModel = GsonUtil.getUserIconModel(arg2);
				if(userIconModel.getError() == 0){
					showToast("图像上传成功！");
					if(userIconModel.getPhoto() !=null){
						ImageLoader.getInstance().displayImage(userIconModel.getPhoto().getFile_url(), user_img);
					}
					
				}else{
					errorDialog("上传失败，请重新上传！");
				}
			}

			@Override
			public void onFailure(int arg0, Header[] arg1, String arg2,
					Throwable arg3) {
				// TODO Auto-generated method stub
				errorDialog("获取失败，请检查网络！");
				System.out.println("*****************"+arg2);
			}
		});

	}
	
	
	
	/** 退出登录 */
	public void http_tuichu_login() {
		String url = BaseUrlAndKey.BAES_URL + "/v1/logout.json";
		RequestParams params = new RequestParams();
		params.put("access_token", access_token);
		AsyncHttpUtil.delete(url, params, new TextHttpResponseHandler() {

			@Override
			public void onSuccess(int arg0, Header[] arg1, String arg2) {
				// TODO Auto-generated method stub
				showToast("退出成功");
				SharedpreferncesUtil.saveRegisterInfo(SettingActivity.this,
						null);
				Intent intent1 = new Intent();
				intent1.putExtra("type", "退出成功");
				setResult(RESULT_OK, intent1);
				finish();
			}

			@Override
			public void onFailure(int arg0, Header[] arg1, String arg2,
					Throwable arg3) {
				// TODO Auto-generated method stub
				
			}
		});

	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		switch (requestCode) {
		case Type_Edit_Ziliao:
			if (resultCode == RESULT_OK) {
				http_get_gereninfo();
			}
		break;
		
		case Type_Camera:
			String files = CacheUtils.getCacheDirectory(SettingActivity.this, true,
					"icon") + dateTime;
			File file = new File(files);
			if (file.exists() && file.length() > 0) {
				Uri uri = Uri.fromFile(file);
				startPhotoZoom(uri);
			} else {

			}
			break;
		case Type_Xiangce:
			if (data == null) {
				return;
			}
			startPhotoZoom(data.getData());
			break;
		case Type_Caijian:
			if (data != null) {
				Bundle extras = data.getExtras();
				if (extras != null) {
					Bitmap bitmap = extras.getParcelable("data");
					iconUrl = saveToSdCard(bitmap);
					http_save_usericon(changeFile(bitmap));
				}
			}
			break;
			
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	
	/** bitmip转换成file*/
	public File changeFile(Bitmap bitmap) {
		String files = CacheUtils.getCacheDirectory(SettingActivity.this, true, "icon")
				+ dateTime + "_icon.jpg";
		File file = new File(files);
		
		return file;
	}
	
	
	public String saveToSdCard(Bitmap bitmap) {
		String files = CacheUtils.getCacheDirectory(SettingActivity.this, true, "icon")
				+ dateTime + "_icon.jpg";
		File file = new File(files);
		try {
			FileOutputStream out = new FileOutputStream(file);
			if (bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)) {
				out.flush();
				out.close();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return file.getAbsolutePath();
	}
	public void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}

	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}
}
