package com.lingdian.waimaibang.fragment;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.widget.ImageView;
import android.widget.TextView;

import com.lingdian.waimaibang.R;
import com.lingdian.waimaibang.utils.LocationBase;
import com.lingdian.waimaibang.view.ChooseOneBtDialog;
import com.lingdian.waimaibang.view.ErrorHintView;
import com.lingdian.waimaibang.view.LoadingTextDialog;

public abstract class BaseFragment extends SuperFragment {

	protected ImageView title_bar_left;
	protected ImageView title_bar_right;
	protected ImageView local_img;
	protected TextView title_bar_text;
	protected LocationBase mLocationBase = null;
	protected String destination_name;
	protected String dingwei_name;

	protected ErrorHintView mErrorHintView;
	/** 加载完成 */
	protected static int VIEW_LIST = 1;
	/** 显示断网 **/
	protected static int VIEW_WIFIFAILUER = 2;
	/** 显示加载数据失败 **/
	protected static int VIEW_LOADFAILURE = 3;
	/** 正在加载 */
	protected static int VIEW_LOADING = 4;
	/** 加载无数据 */
	protected static int VIEW_NO_DATA = 5;

	/** 绑定界面UI **/
	protected abstract void findViewById();

	/** 界面UI事件监听 **/
	protected abstract void setListener();

	/** 界面数据初始化 **/
	protected abstract void init();

	protected static LoadingTextDialog lTextDialog;

	/**
	 * 可以设置是否点击的时候会取消
	 * 
	 * @param remark
	 * @param cancelable
	 *            =false点击不可取消
	 */
	public void showLoadingDlg(String remark, boolean cancelable) {
		lTextDialog = new LoadingTextDialog(getActivity(),
				R.style.meizhouliu_MyDialogStyleTop, remark);
		lTextDialog.setCancelable(cancelable);
		lTextDialog.show();
	}

	public void setLoadingText(String text) {
		if (lTextDialog != null) {
			lTextDialog.setRemarkText(text);
		}
	}

	public void setLoadingJinduText(String text) {
		if (lTextDialog != null) {
			lTextDialog.setJinduText(text);
		}
	}

	/** 取消菊花转dialog */
	public static void hideTextLoadingDlg() {
		if (lTextDialog != null) {
			lTextDialog.dismiss();
		}
	}

	/** 验证错误的dialog */
	public void errorDialog(String msg) {
		ChooseOneBtDialog rmdDlg = new ChooseOneBtDialog(getActivity(),
				R.style.meizhouliu_MyDialogStyleTop, msg) {
			@Override
			public void doBtn1Click() {
				this.dismiss();
			}

			@Override
			public void doBtn2Click() {
				this.dismiss();
			}
		};
		rmdDlg.show();
	}

	/** 得到字符串的长度，字符一个，中文两个 */
	public int getWordCount(String s) {
		s = s.replaceAll("[^\\x00-\\xff]", "**");
		int length = s.length();
		return length;
	}

	/** 检查密码中是否有中文 */
	public boolean checkfilename(String s) {
		boolean isChina = false;
		s = new String(s.getBytes());// 用GBK编码
		String pattern = "[\u4e00-\u9fa5]+";
		Pattern p = Pattern.compile(pattern);
		Matcher result = p.matcher(s);
		while (result.find()) {
			isChina = true;
		}
		return isChina;
	}

	public String listToString(List<String> a) {
		String result = "";
		for (int i = 0; i < a.size(); i++) {
			if (i + 1 == a.size()) {
				result += a.get(i).toString();
			} else {
				result += a.get(i).toString() + ",";
			}
		}
		return result;
	}

}
