package cn.wischool.ws1app.wischool_androidapp.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

public class CActivity extends BaseFragmentActivity{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		TextView tv = new TextView(this);
		tv.setText("This is C Activity!");
		tv.setGravity(Gravity.CENTER);
		setContentView(tv);
	}
	
}