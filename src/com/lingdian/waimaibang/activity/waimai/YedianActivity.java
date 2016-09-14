package com.lingdian.waimaibang.activity.waimai;

import java.io.File;
import java.net.URISyntaxException;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lingdian.waimaibang.R;
import com.lingdian.waimaibang.activity.BaseActivity;
import com.lingdian.waimaibang.utils.OptionsUtil;
import com.lingdian.waimaibang.utils.PhoneUtils;
import com.lingdian.waimaibang.view.ChooseRemindDialog;
import com.lingdian.waimaibang.waimaimodel.Stores;
import com.nostra13.universalimageloader.core.ImageLoader;

public class YedianActivity extends BaseActivity implements OnClickListener {

	public RelativeLayout title_bar_left_layout;
	public TextView title_bar_text;
	public Stores stores;

	public ImageView yeshi_img;
	public TextView yeshi_jianjie, yeshi_tuijian;
	public TextView yeshi_tel, yeshi_daohang;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ye_dianpu_detail);
		findViewById();
		setListener();
		init();
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		title_bar_left_layout = (RelativeLayout) findViewById(R.id.title_bar_left_layout);
		title_bar_text = (TextView) findViewById(R.id.title_bar_text);

		yeshi_img = (ImageView) findViewById(R.id.yeshi_img);
		yeshi_jianjie = (TextView) findViewById(R.id.yeshi_jianjie);
		yeshi_tuijian = (TextView) findViewById(R.id.yeshi_tuijian);
		yeshi_tel = (TextView) findViewById(R.id.yeshi_tel);
		yeshi_daohang = (TextView) findViewById(R.id.yeshi_daohang);

	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		title_bar_left_layout.setOnClickListener(this);
		yeshi_tel.setOnClickListener(this);
		yeshi_daohang.setOnClickListener(this);
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		stores = (Stores) getIntent().getSerializableExtra("stores");
		title_bar_text.setText(stores.getName()+"");
		yeshi_jianjie.setText(stores.getJianjie() + "");
		yeshi_tuijian.setText(stores.getTuijian() + "");
		if (stores.getImg() != null) {
			ImageLoader
					.getInstance()
					.displayImage(
							stores.getImg().getFileUrl(YedianActivity.this) == null ? ""
									: stores.getImg().getFileUrl(
											YedianActivity.this), yeshi_img,
							OptionsUtil.PicCamera());
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.title_bar_left_layout:
			finish();
			break;
		case R.id.yeshi_tel:
			callDialog("是否拨打该商家的电话进行订餐？电话号码：" + stores.getTel(),
					stores.getTel());
			break;
		case R.id.yeshi_daohang:
			daohangDialog("是否复制该店铺在百度地图中进行路线搜索？");
			break;
		}
	}

	/** *打电话弹出的dialog */
	public void callDialog(String msg, final String mobilePh) {
		ChooseRemindDialog rmdDlg = new ChooseRemindDialog(YedianActivity.this,
				R.style.meizhouliu_MyDialogStyleTop, "请确认", msg, "确定", "取消") {
			@Override
			public void doBtn1Click() {
				PhoneUtils.callPhone(mobilePh, YedianActivity.this);
				this.dismiss();
			}

			@Override
			public void doBtn2Click() {
				this.dismiss();
			}
		};
		rmdDlg.show();
	}

	/** *导航的dialog */
	public void daohangDialog(String msg) {
		ChooseRemindDialog rmdDlg = new ChooseRemindDialog(YedianActivity.this,
				R.style.meizhouliu_MyDialogStyleTop, "请确认", msg, "确定", "取消") {
			@Override
			public void doBtn1Click() {
				ClipboardManager copy = (ClipboardManager) YedianActivity.this
						.getSystemService(Context.CLIPBOARD_SERVICE);
				copy.setText(stores.getName());

				Intent intent = new Intent(Intent.ACTION_VIEW);
				Uri uri = Uri.parse("geo:"+stores.getLatitude()+","+stores.getLongitude()+","+stores.getDiming2()+"");
				intent.setData(uri);
				if (isInstallByread("com.baidu.BaiduMap")) {
					intent.setPackage("com.baidu.BaiduMap");
					startActivity(intent);
				} else {
					showToast("您当前手机没有安装百度地图，没法进行导航！");
				}

				this.dismiss();
			}

			@Override
			public void doBtn2Click() {
				this.dismiss();
			}
		};
		rmdDlg.show();
	}

	private boolean isInstallByread(String packageName) {
		return new File("/data/data/" + packageName).exists();
	}
}
