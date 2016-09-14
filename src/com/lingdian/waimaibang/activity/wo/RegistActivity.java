package com.lingdian.waimaibang.activity.wo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.Header;

import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lingdian.waimaibang.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.lingdian.waimaibang.activity.BaseActivity;
import com.lingdian.waimaibang.model.GsonUtil;
import com.lingdian.waimaibang.model.Register;
import com.lingdian.waimaibang.utils.AsyncHttpUtil;
import com.lingdian.waimaibang.utils.BaseUrlAndKey;
import com.lingdian.waimaibang.utils.SharedpreferncesUtil;
import com.lingdian.waimaibang.view.ChooseOneBtDialog;
import com.umeng.analytics.MobclickAgent;

public class RegistActivity extends BaseActivity implements OnClickListener {

	private ImageView title_bar_left;
	private TextView title_bar_text;
	private TextView tv_email;
	private TextView tv_nickname;
	private TextView tv_password;
	private Button btn_submit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regester);
		findViewById();
		setListener();
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		title_bar_left = (ImageView) findViewById(R.id.title_bar_left);
		title_bar_text = (TextView) findViewById(R.id.title_bar_text);
		title_bar_text.setText("注册");

		tv_email = (TextView) findViewById(R.id.tv_email);
		tv_nickname = (TextView) findViewById(R.id.tv_nickname);
		tv_password = (TextView) findViewById(R.id.tv_password);
		btn_submit = (Button) findViewById(R.id.btn_submit);
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		title_bar_left.setOnClickListener(this);
		btn_submit.setOnClickListener(this);
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.title_bar_left:
			finish();
			break;
		case R.id.btn_submit:
			
			String email = tv_email.getText().toString();
			if(TextUtils.isEmpty(email)){
				errorDialog("请填写邮箱！");
				return;
			}
			if(!isEmail(email)){
				errorDialog("邮箱格式不正确，请重新输入！");
				return;
			}
			String nickname = tv_nickname.getText().toString();
			if(TextUtils.isEmpty(nickname)){
				errorDialog("请填写昵称！");
				return;
			}
			if(getWordCount(nickname) < 4){
				errorDialog("昵称长度为4-30个字符（2-15个汉字）哦！");
				return;
			}
			
			String password = tv_password.getText().toString();
			if(TextUtils.isEmpty(password)){
				errorDialog("请填写密码！");
				return;
			}
			if(checkfilename(password)){
				errorDialog("请输入英文或者阿拉伯数据哦！");
				return;
			}
			if(getWordCount(password) < 6){
				errorDialog("密码长度最少6个字符哦！");
				return;
			}
			submit();
			break;
		}

	}

	private void submit() {
		showLoadingDlg("正在注册中...", true);
		String url = BaseUrlAndKey.BAES_URL + "/v1/users/register.json";
//		AsyncHttpClient client = AsyncHttpUtil.getClient();
//		client.addHeader("Content-Type",
//				"application/x-www-form-urlencoded; charset=utf-8");

		RequestParams params = new RequestParams();
		params.put("machine_id", "2015080901201335991");
		params.put("user[email]", tv_email.getText().toString());
		params.put("user[nickname]", tv_nickname.getText().toString());
		params.put("user[password]", tv_password.getText().toString());

		AsyncHttpUtil.post(url, params, new TextHttpResponseHandler() {

			@Override
			public void onSuccess(int arg0, Header[] arg1, String arg2) {
				// TODO Auto-generated method stub
				hideTextLoadingDlg();
				Register register = GsonUtil.getRegisterInfo(arg2);
//				SharedpreferncesUtil.saveRegisterInfo(RegistActivity.this, register);
				
				if(register.getError() == -1){
					if(register.getMessages() !=null){
						if(register.getMessages().size() == 1){
							if(register.getMessages().get(0).getField().equals("nickname")){
								errorDialog("昵称已被占用，请重新选择一个昵称");
							}else if(register.getMessages().get(0).getField().equals("email")){
								errorDialog("邮箱已被占用，请重新选择一个邮箱");
							}
						}else if(register.getMessages().size() == 2){
							errorDialog("邮箱和昵称都被占用，请重新填写");
						}
					}
					
				}else if(register.getError() == 0){
					Intent intent1 = new Intent();
					intent1.putExtra("zhuce_emall", tv_email.getText().toString());
					intent1.putExtra("zhuce_password", tv_password.getText().toString());
					setResult(RESULT_OK, intent1);
					finish();
				}
			
			}

			@Override
			public void onFailure(int arg0, Header[] arg1, String arg2,
					Throwable arg3) {
				// TODO Auto-generated method stub
				hideTextLoadingDlg();
				errorDialog("注册失败，请检查网络！");
			}
		});
	}


	public boolean isEmail(String email) {
		String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);
		return m.matches();
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
