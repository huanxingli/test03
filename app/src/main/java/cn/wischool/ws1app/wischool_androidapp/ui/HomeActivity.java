package cn.wischool.ws1app.wischool_androidapp.ui;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import cn.wischool.ws1app.wischool_androidapp.R;
import cn.wischool.ws1app.wischool_androidapp.common.ImageUtil;
import cn.wischool.ws1app.wischool_androidapp.common.SharedPreferencesHelper;
import cn.wischool.ws1app.wischool_androidapp.common.SharedPreferencesLifecycle;
import cn.wischool.ws1app.wischool_androidapp.common.UiHelper;
import cn.wischool.ws1app.wischool_androidapp.model.db.Province;
import cn.wischool.ws1app.wischool_androidapp.model.db.business.ProvinceService;
import cn.wischool.ws1app.wischool_androidapp.widge.departmentSelect.ProvinceSlectActivity;
import cn.wischool.ws1app.wischool_androidapp.widge.homeviewpage.ViewPagerAdapter;
import cn.wischool.ws1app.wischool_androidapp.widge.homeviewpage.ViewPagerListener;
import cn.wischool.ws1app.wischool_androidapp.widge.tasksearch.HomeSearch;
import cn.wischool.ws1app.wischool_androidapp.widge.tasksearch.SearchActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_home)
public class HomeActivity extends BaseFragmentActivity{

	@InjectView(R.id.viewpager)
	private ViewPager viewPager;

	@InjectView(R.id.btn)
	private Button button;

	@InjectView(R.id.image_honour)
	private ImageView imageHonour;

	@InjectView(R.id.image_money)
	private ImageView imageMoney;

	@InjectView(R.id.image_emergency)
	private ImageView imageEmergency;

	@InjectView(R.id.image_heart)
	private ImageView iamgeHeart;

	@InjectView(R.id.search_edt)
	private EditText editText;

	@InjectView(R.id.home_btn_test)
	private Button btnTest;

	@Inject
	private ProvinceService provinceService;

	public static AtomicInteger atomicInteger = new AtomicInteger(0); //类似于计时器
	private int[] ids = {R.id.iv1,R.id.iv2,R.id.iv3,R.id.iv4};
	public static List<View> views;
	private ViewPagerAdapter viewPagerAdapter;

	public static ImageView[] strips;//strips[]是从1到4

	private ViewPagerListener viewPagerListener;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//初始化滑动的视图并随时改变
		initViews();
		//初始化提示器
		initDots();


		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				SharedPreferencesHelper.putBoolean(getApplicationContext(), SharedPreferencesLifecycle.forever, "hasLogin", false);
				Province p = provinceService.getProvinceById("12");
				if (p != null) {
					UiHelper.getInstance().toastMessage(HomeActivity.this, p.getProId() + ":" + p.getProName());
				} else {
					UiHelper.getInstance().toastMessage(HomeActivity.this, "没有该数据");
				}
			}
		});

		btnTest.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(HomeActivity.this, ProvinceSlectActivity.class);
				startActivity(intent);
			}
		});

		HomeSearch homeSearch = (HomeSearch) findViewById(R.id.homeSearch);
		homeSearch.setTopbarOnClickListener(new HomeSearch.topbarClickListener() {
			@Override
			public void editClick() {
				Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
				startActivity(intent);
			}
		});

	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	public void initViews(){
		editText.setFocusable(false);
		views = new ArrayList<View>();

		ImageView img0 = new ImageView(this);
		Drawable drawable =new BitmapDrawable(ImageUtil.getBitmap(this, R.drawable.i4));
		img0.setBackground(drawable);
		views.add(img0);

		ImageView img1 = new ImageView(this);
		drawable =new BitmapDrawable(ImageUtil.getBitmap(this, R.drawable.i1));
		img1.setBackground(drawable);
		views.add(img1);

		ImageView img2 = new ImageView(this);
		drawable =new BitmapDrawable(ImageUtil.getBitmap(this, R.drawable.i2));
		img2.setBackground(drawable);
		views.add(img2);

		ImageView img3 = new ImageView(this);
		drawable =new BitmapDrawable(ImageUtil.getBitmap(this, R.drawable.i3));
		img3.setBackground(drawable);
		views.add(img3);

		ImageView img4 = new ImageView(this);
		drawable =new BitmapDrawable(ImageUtil.getBitmap(this, R.drawable.i4));
		img4.setBackground(drawable);
		views.add(img4);


		ImageView img5 = new ImageView(this);
		drawable =new BitmapDrawable(ImageUtil.getBitmap(this, R.drawable.i1));
		img5.setBackground(drawable);
		views.add(img5);

		//设置荣誉榜、赏金榜、紧急任务的图案
		imageHonour.setImageBitmap(ImageUtil.getBitmap(this, R.drawable.home_star));
		imageMoney.setImageBitmap(ImageUtil.getBitmap(this, R.drawable.home_money));
        imageEmergency.setImageBitmap(ImageUtil.getBitmap(this,R.drawable.home_clock));

		viewPagerAdapter = new ViewPagerAdapter(views,this);
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		viewPager.setAdapter(viewPagerAdapter);
		viewPagerListener = new ViewPagerListener(this,views.size(),viewPager);
		viewPager.setOnPageChangeListener(viewPagerListener);

		//随时改变图片和提示器
		new Thread(){
			@Override
			public void run() {
				while (true){
					handler.sendEmptyMessage(atomicInteger.get());
					autoImage();
				}
			}
		}.start();

	}

	//strip[]是从1到4
	public void initDots(){
		strips = new ImageView[views.size()];
		for (int i=1;i<views.size()-1;i++){
			strips[i] = (ImageView) findViewById(ids[i-1]);
		}
	}


	public Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			viewPager.setCurrentItem((msg.what) % views.size());

		}
	};


	//每隔2秒切换视图
	public void autoImage(){
		atomicInteger.incrementAndGet();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
