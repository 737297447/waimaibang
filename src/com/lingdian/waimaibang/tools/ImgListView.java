package com.lingdian.waimaibang.tools;




import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.lingdian.waimaibang.R;


/**
 * listview头图片下拉放大， 使用时务必调用方法setImageId或setImageBitmap设置图片 否则报错 Created by Roger
 * on 14-4-30.
 */
public class ImgListView extends ListView {
	public static final int LOADSTATE_IDLE = 200;
	public static final int LOADSTATE_LOADING = 201;
	
	private final int DECREASE_HEADVIEW_PADDING = 100;
	private final int LOAD_DATA = 101;
	private final int DISMISS_CIRCLE = 102;
	private static final int BACK_SCALE = 0;
	private boolean isHaveHead = false;// 头部是否有图片
	private float scaleY = 0;
	private boolean isBacking = false;// 是否处在回弹状态
	private int displayWidth;
	private Context mContext;
	private Bitmap bmp;
	private View headerView;
	private ImageView imageView;
	/** 用于记录拖拉图片移动的坐标位置 */
	private Matrix matrix = new Matrix();
	/** 用于记录图片要进行拖拉时候的坐标位置 */
	private Matrix currentMatrix = new Matrix();
	private Matrix defaultMatrix = new Matrix();
	private float imgHeight, imgWidth;
	/** 记录是拖拉照片模式还是放大缩小照片模式 0:拖拉模式，1：放大 */
	private int mode = 0;// 初始状态
	/** 拖拉照片模式 */
	private final int MODE_DRAG = 1;
	/** 用于记录开始时候的坐标位置 */
	private PointF startPoint = new PointF();

	private int mImageId;

	private AttributeSet attrs;
	private ImageView circle;

	private int CircleMarginTop;
	
	private int deltaCount;
	
	private float lastDownY;
	private int currentState;

	public ImgListView(Context context) {
		super(context);
		this.mContext = context;
		initView();
	}

	public ImgListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.mContext = context;
		this.attrs = attrs;
		initView();
	}

	public ImgListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.mContext = context;
		this.attrs = attrs;
		initView();
	}

	public void setAdapter(ListAdapter adapter) {
		super.setAdapter(adapter);
	}

	public void addHeaderView(View v) {
		super.addHeaderView(v);
	}

	public void setImageId(int id) {
		this.mImageId = id;
		bmp = BitmapFactory.decodeResource(getResources(), mImageId);
		if (isHaveHead)
			this.removeHeaderView(headerView);
		initHead();
	}

	public void setImageBitmap(Bitmap bit) {
		this.bmp = bit;
		if (isHaveHead)
			this.removeHeaderView(headerView);
		initHead();
	}

	/**
	 * 初始化图片
	 */
	private void initView() {
		/* 取得屏幕分辨率大小 */
		DisplayMetrics dm = new DisplayMetrics();
		WindowManager mWm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
		mWm.getDefaultDisplay().getMetrics(dm);
		displayWidth = dm.widthPixels;

		TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.ImgListView);
		mImageId = a.getResourceId(R.styleable.ImgListView_headimage, 0);
		a.recycle();
		if (null == bmp && mImageId != 0) {
			bmp = BitmapFactory.decodeResource(getResources(), mImageId);
			initHead();
		}
	}

	private void initHead() {
		LayoutInflater inflater = LayoutInflater.from(mContext);
		headerView = inflater.inflate(R.layout.top_img, null);
		imageView = (ImageView) headerView.findViewById(R.id.imageView);
		circle = (ImageView) headerView.findViewById(R.id.circleprogress);
		float scale = (float) displayWidth / (float) bmp.getWidth();// 1080/1800
		matrix.postScale(scale, scale, 0, 0);
		imageView.setImageMatrix(matrix);
		defaultMatrix.set(matrix);
		imgHeight = scale * bmp.getHeight();
		imgWidth = scale * bmp.getWidth();
		RelativeLayout.LayoutParams relativeLayout = new RelativeLayout.LayoutParams((int) imgWidth, (int) imgHeight);
		imageView.setLayoutParams(relativeLayout);
		imageView.setImageBitmap(bmp);
		this.addHeaderView(headerView);
		isHaveHead = true;
		CircleMarginTop = 76;
		currentState = LOADSTATE_IDLE;
	}

	protected void setCircleMargin() {
		// TODO Auto-generated method stub
		MarginLayoutParams lp = (MarginLayoutParams) circle.getLayoutParams();
		lp.topMargin = CircleMarginTop - headerView.getPaddingTop();
		circle.setLayoutParams(lp);
	}
	protected void setCircleMargin(int margin) {
		MarginLayoutParams lp = (MarginLayoutParams) circle.getLayoutParams();
		lp.topMargin = margin;
		circle.setLayoutParams(lp);
	}


	private void setCircleViewStay(int margin) {
		// TODO Auto-generated method stub
			MarginLayoutParams lp = (MarginLayoutParams) circle
					.getLayoutParams();
			lp.topMargin = CircleMarginTop - margin;
			circle.setLayoutParams(lp);
	}
	
	private void startCircleAnimation() {
		// TODO Auto-generated method stub
		CircleAnimation.startRotateAnimation(circle);

	}
	
	private void loadDataForThreeSecond() {
		currentState = LOADSTATE_LOADING;
		Message msg = Message.obtain();
		msg.what = LOAD_DATA;
		mHandler.sendMessageDelayed(msg, 3000);
	}
	
	private void setHeadViewPaddingTop(int deltaY) {
		headerView.setPadding(0, deltaY, 0, 0);
	}
	private void decreasePadding(int count) {
		Thread thread = new Thread(new DecreaseThread(count));
		thread.start();
	}
	
	private class DecreaseThread implements Runnable {
		private final static int TIME = 25;
		private int decrease_length;

		public DecreaseThread(int count) {
			decrease_length = count / TIME;
		}

		@Override
		public void run() {
			for (int i = 0; i < TIME; i++) {
				if (i == 24) {
					deltaCount = 0;
				} else {
					deltaCount = deltaCount - decrease_length;
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
					}
				}
				Message msg = Message.obtain();
				msg.what = DECREASE_HEADVIEW_PADDING;
				mHandler.sendMessage(msg);
			}
		}
	}
	
	private class DismissCircleThread implements Runnable {
		private final int COUNT = 10;
		private final int deltaMargin;

		public DismissCircleThread() {
			deltaMargin = CircleMarginTop / COUNT;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			int temp = 0;
			for (int i = 0; i <= COUNT; i++) {
				if (i == 10) {
					temp = 0;
				} else {
					temp = CircleMarginTop - deltaMargin * i;
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
					}
				}
				Message msg = Message.obtain();
				msg.what = DISMISS_CIRCLE;
				msg.arg1 = temp;
				mHandler.sendMessage(msg);
			}

		}
	}
	/**
	 * 向下滑动让图片变大
	 * 
	 * @param event
	 * @return
	 */
	public boolean onTouchEvent(MotionEvent event) {

		if (!isHaveHead) {// 无头部图片
			return super.onTouchEvent(event);
		}
		float downY = event.getY();
		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		// 手指压下屏幕
		case MotionEvent.ACTION_DOWN:
			lastDownY = downY;
			if (isBacking) {
				return super.onTouchEvent(event);
			}
			int[] location = new int[2];
			imageView.getLocationInWindow(location);
			if (location[1] >= 0) {
				mode = MODE_DRAG;
				// 记录ImageView当前的移动位置
				currentMatrix.set(imageView.getImageMatrix());
				startPoint.set(event.getX(), event.getY());
			}
			break;
		// 手指在屏幕上移动，改事件会被不断触发
		case MotionEvent.ACTION_MOVE:
			// 拖拉图片
			if (mode == MODE_DRAG) {
				float dx = event.getX() - startPoint.x; // 得到x轴的移动距离
				float dy = event.getY() - startPoint.y; // 得到y轴的移动距离
				// 在没有移动之前的位置上进行移动
				if (dy / 2 + imgHeight <= 1.5 * imgHeight) {
					matrix.set(currentMatrix);
					float scale = (dy / 2 + imgHeight) / (imgHeight);// 得到缩放倍数
					if (dy > 0) {
						scaleY = dy;
						RelativeLayout.LayoutParams relativeLayout = new RelativeLayout.LayoutParams((int) (scale * imgWidth), (int) (scale * imgHeight));
						imageView.setLayoutParams(relativeLayout);
						matrix.postScale(scale, scale, imgWidth / 2, 0);
						imageView.setImageMatrix(matrix);
					}
				}
				int nowDeltaCount = (int) ((downY - lastDownY) / 3.0);
				int grepDegree = nowDeltaCount - deltaCount;
				deltaCount = nowDeltaCount;
				if (deltaCount > 100 && currentState != LOADSTATE_LOADING) {
					setCircleViewStay(grepDegree-deltaCount+76);
					CircleAnimation.startCWAnimation(circle,
							5 * (deltaCount - grepDegree), 5 * deltaCount);
				}
			}
			
			
			break;
		// 手指离开屏幕
		case MotionEvent.ACTION_UP:
			// 当触点离开屏幕，图片还原
			if (deltaCount >100 && currentState != LOADSTATE_LOADING) {
				decreasePadding(deltaCount);
				loadDataForThreeSecond();
				startCircleAnimation();
			}
			mHandler.sendEmptyMessage(BACK_SCALE);
		case MotionEvent.ACTION_POINTER_UP:
			mode = 0;
			break;
		}

		return super.onTouchEvent(event);
	}

	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case DECREASE_HEADVIEW_PADDING:
//				setHeadViewPaddingTop(deltaCount);
				setCircleMargin();
				break;
			case LOAD_DATA:
				Thread thread = new Thread(new DismissCircleThread());
				currentState = LOADSTATE_IDLE;
				thread.start();
				break;
			case DISMISS_CIRCLE:
				int margin = msg.arg1;
				setCircleMargin(margin);
				if (margin == 0) {
					CircleAnimation.stopRotateAnmiation(circle);
				}
				break;
			case BACK_SCALE:
				float scale = (scaleY / 2 + imgHeight) / (imgHeight);// 得到缩放倍数
				if (scaleY > 0) {
					isBacking = true;
					matrix.set(currentMatrix);
					RelativeLayout.LayoutParams relativeLayout = new RelativeLayout.LayoutParams((int) (scale * imgWidth), (int) (scale * imgHeight));
					imageView.setLayoutParams(relativeLayout);
					matrix.postScale(scale, scale, imgWidth / 2, 0);
					imageView.setImageMatrix(matrix);
					scaleY = (float) (scaleY / 2 - 1);
					mHandler.sendEmptyMessage(BACK_SCALE);
				} else {
					scaleY = 0;
					RelativeLayout.LayoutParams relativeLayout = new RelativeLayout.LayoutParams((int) imgWidth, (int) imgHeight);
					imageView.setLayoutParams(relativeLayout);
					matrix.set(defaultMatrix);
					imageView.setImageMatrix(matrix);
					isBacking = false;
				}
				break;
			default:
				break;
			}
			super.handleMessage(msg);
		}
	};
}
