package com.lingdian.waimaibang.activity.wo;

import org.apache.http.Header;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lingdian.waimaibang.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.lingdian.waimaibang.activity.BaseActivity;
import com.lingdian.waimaibang.model.Register;
import com.lingdian.waimaibang.utils.AsyncHttpUtil;
import com.lingdian.waimaibang.utils.BaseUrlAndKey;
import com.lingdian.waimaibang.utils.SharedpreferncesUtil;
import com.umeng.analytics.MobclickAgent;

public class EditZiliaoActivity extends BaseActivity implements OnClickListener {

	private LinearLayout layout_man_or_woman;
	private TextView xingbie_text;
	private EditText edit_jianjie;
	private SelectXingbiePopupWindow xPopupWindow;
	private int user_id;
	private String accsee_token;
	private Register register;
	
	private String intro;
	private String sex;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_ziliao);
		init();
		findViewById();
		setListener();
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		title_bar_left = (ImageView) findViewById(R.id.title_bar_left);
		title_bar_text = (TextView) findViewById(R.id.title_bar_text);
		title_right_text = (TextView) findViewById(R.id.title_right_text);
		title_bar_text.setText("基本资料");
		title_right_text.setVisibility(View.VISIBLE);
		layout_man_or_woman = (LinearLayout) findViewById(R.id.layout_man_or_woman);
		xingbie_text = (TextView) findViewById(R.id.xingbie_text);
		edit_jianjie = (EditText) findViewById(R.id.edit_jianjie);
		
		if(sex.equals("0")){
			xingbie_text.setText("男");
		}else if(sex.equals("1")){
			xingbie_text.setText("女");
		}
		edit_jianjie.setText(intro);
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		title_bar_left.setOnClickListener(this);
		layout_man_or_woman.setOnClickListener(this);
		title_right_text.setOnClickListener(this);
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		register = SharedpreferncesUtil
				.getRegisterInfo(EditZiliaoActivity.this);
		if (register != null) {
			user_id = register.getUser().getId();
			accsee_token = register.getAccess_token().getToken();
		}
		
		intro = getIntent().getStringExtra("intro");
		sex = getIntent().getStringExtra("sex");
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.title_bar_left:
			hideSoftInput(this, edit_jianjie);
			finish();
			break;
		case R.id.layout_man_or_woman:
			hideSoftInput(this, edit_jianjie);
			xPopupWindow = new SelectXingbiePopupWindow(
					EditZiliaoActivity.this, itemsOnClick);
			// 显示窗口,设置layout在PopupWindow中显示的位置
			xPopupWindow.showAtLocation(EditZiliaoActivity.this
					.findViewById(R.id.edit_ziliao_main_layout), Gravity.BOTTOM
					| Gravity.CENTER_HORIZONTAL, 0, 0);
			break;
		case R.id.title_right_text:
			http_edit_gereninfo();
			break;
		default:
			break;
		}

	}

	/** 编辑个人信息 */
	public void http_edit_gereninfo() {

		String url = BaseUrlAndKey.BAES_URL + "/v1/users/" + user_id + ".json";

		RequestParams params = new RequestParams();
		params.put("id", user_id);
		params.put("access_token", accsee_token);
		params.put("user[sex]",
				xingbie_text.getText().toString().equals("男") ? 0
						: xingbie_text.getText().toString().equals("女") ? 1 : 0);
		params.put("user[intro]", edit_jianjie.getText().toString());

		AsyncHttpUtil.put(url, params, new TextHttpResponseHandler() {

			@Override
			public void onSuccess(int arg0, Header[] arg1, String arg2) {
				// TODO Auto-generated method stub
				showToast("编辑成功");
				Intent intent1 = new Intent();
				intent1.putExtra("type", "success");
				setResult(RESULT_OK, intent1);
				finish();
			}

			@Override
			public void onFailure(int arg0, Header[] arg1, String arg2,
					Throwable arg3) {
				// TODO Auto-generated method stub
				errorDialog("编辑失败，请检查网络！");
			}
		});

	}

	// 为弹出窗口实现监听类
	private OnClickListener itemsOnClick = new OnClickListener() {

		public void onClick(View v) {
			xPopupWindow.dismiss();
			switch (v.getId()) {
			case R.id.btn_nan:
				xingbie_text.setText("男");
				break;
			case R.id.btn_nv:
				xingbie_text.setText("女");
				break;

			default:
				break;
			}
		}
	};

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			hideSoftInput(this, edit_jianjie);
			finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
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
