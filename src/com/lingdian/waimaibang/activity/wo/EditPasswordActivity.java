package com.lingdian.waimaibang.activity.wo;

import org.apache.http.Header;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.lingdian.waimaibang.model.GsonUtil;
import com.lingdian.waimaibang.model.PsdErrorModel;
import com.lingdian.waimaibang.model.Register;
import com.lingdian.waimaibang.utils.AsyncHttpUtil;
import com.lingdian.waimaibang.utils.BaseUrlAndKey;
import com.lingdian.waimaibang.utils.SharedpreferncesUtil;
import com.umeng.analytics.MobclickAgent;

public class EditPasswordActivity extends BaseActivity implements
		OnClickListener {

	private EditText password_jiu, password_xin, password_queren;

	private int user_id;
	private String accsee_token;
	private Register register;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_password);
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
		title_bar_text.setText("修改密码");
		title_right_text.setVisibility(View.VISIBLE);

		password_jiu = (EditText) findViewById(R.id.password_jiu);
		password_xin = (EditText) findViewById(R.id.password_xin);
		password_queren = (EditText) findViewById(R.id.password_queren);

	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		title_bar_left.setOnClickListener(this);
		title_right_text.setOnClickListener(this);
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		register = SharedpreferncesUtil
				.getRegisterInfo(EditPasswordActivity.this);
		if (register != null) {
			user_id = register.getUser().getId();
			accsee_token = register.getAccess_token().getToken();
		}

	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.title_bar_left:
			hideSoftInput(this, password_jiu);
			hideSoftInput(this, password_xin);
			hideSoftInput(this, password_queren);
			finish();
			break;
		case R.id.title_right_text:
			if (TextUtils.isEmpty(password_jiu.getText().toString())) {
				errorDialog("请填写密码！");
				return;
			}
			if (TextUtils.isEmpty(password_xin.getText().toString())) {
				errorDialog("请填写新密码！");
				return;
			}
			if (TextUtils.isEmpty(password_queren.getText().toString())) {
				errorDialog("请填写确认密码！");
				return;
			}
			if (password_jiu.getText().toString().length() < 6
					|| password_xin.getText().toString().length() < 6
					|| password_queren.getText().toString().length() < 6) {
				errorDialog("密码长度最少6个字符哦！");
				return;
			}

			if (!password_xin.getText().toString()
					.equals(password_queren.getText().toString())) {
				errorDialog("新密码与确认密码不一致！");
				return;
			}
			http_edit_password();
			break;
		default:
			break;
		}

	}

	/** 编辑密码 */
	public void http_edit_password() {

		showLoadingDlg("努力设置中...", true);
		String url = BaseUrlAndKey.BAES_URL + "/v1/users/" + user_id
				+ "/password.json";

		RequestParams params = new RequestParams();
		params.put("id", user_id);
		params.put("access_token", accsee_token);
		params.put("user[old_password]", password_jiu.getText().toString());
		params.put("user[new_password]", password_xin.getText().toString());

		AsyncHttpUtil.put(url, params, new TextHttpResponseHandler() {

			@Override
			public void onSuccess(int arg0, Header[] arg1, String arg2) {
				// TODO Auto-generated method stub
				hideTextLoadingDlg();
				PsdErrorModel psdErrorModel = GsonUtil.getPsdErrorModel(arg2);
				if (psdErrorModel.getError() == 0) {
					showToast("编辑成功");
					Intent intent1 = new Intent();
					intent1.putExtra("type", "success");
					setResult(RESULT_OK, intent1);
					finish();
				} else {
					errorDialog("密码错误");
				}

			}

			@Override
			public void onFailure(int arg0, Header[] arg1, String arg2,
					Throwable arg3) {
				// TODO Auto-generated method stub
				hideTextLoadingDlg();
				errorDialog("编辑失败，请检查网络！");
			}
		});

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			hideSoftInput(this, password_jiu);
			hideSoftInput(this, password_xin);
			hideSoftInput(this, password_queren);
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
