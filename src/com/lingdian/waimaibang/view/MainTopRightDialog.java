package com.lingdian.waimaibang.view;


import com.lingdian.waimaibang.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainTopRightDialog extends Activity {
	//private MyDialog dialog;
		private LinearLayout layout;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main_top_right_dialog);
			//dialog=new MyDialog(this);
			layout=(LinearLayout)findViewById(R.id.main_dialog_layout);
			layout.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Toast.makeText(getApplicationContext(), "提示：点击窗口外部关闭窗口！", 
							Toast.LENGTH_SHORT).show();	
				}
			});
		}

		@Override
		public boolean onTouchEvent(MotionEvent event){
			finish();
			return true;
		}
	

	

}
