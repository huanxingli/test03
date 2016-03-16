package cn.wischool.ws1app.wischool_androidapp.ui;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TabHost;

import java.util.ArrayList;
import java.util.List;

import cn.wischool.ws1app.wischool_androidapp.R;
import cn.wischool.ws1app.wischool_androidapp.common.UiHelper;
import cn.wischool.ws1app.wischool_androidapp.ui.BActivity;
import cn.wischool.ws1app.wischool_androidapp.ui.CActivity;
import cn.wischool.ws1app.wischool_androidapp.ui.DActivity;
import cn.wischool.ws1app.wischool_androidapp.ui.EActivity;
import cn.wischool.ws1app.wischool_androidapp.ui.HomeActivity;

public class MainActivity extends TabActivity implements CompoundButton.OnCheckedChangeListener {

    private static TabHost mTabHost;
    private static List<RadioButton> radioButtonList;
    private Intent homeIntent;
    private Intent mBIntent;
    private Intent mCIntent;
    private Intent mDIntent;
    private Intent mEIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.homeIntent = new Intent(this,HomeActivity.class);
        this.mBIntent = new Intent(this,BActivity.class);
        this.mCIntent = new Intent(this,CActivity.class);
        this.mDIntent = new Intent(this,DActivity.class);
        this.mEIntent = new Intent(this,EActivity.class);

        radioButtonList = new ArrayList<>();
        radioButtonList.add((RadioButton) findViewById(R.id.rb_home));
        radioButtonList.add((RadioButton) findViewById(R.id.radio_button1));
        radioButtonList.add((RadioButton) findViewById(R.id.radio_button2));
        radioButtonList.add((RadioButton) findViewById(R.id.radio_button3));
        radioButtonList.add((RadioButton) findViewById(R.id.radio_button4));
        for (RadioButton radioButton : radioButtonList){
            radioButton.setOnCheckedChangeListener(this);
        }

        setupIntent();

        this.mTabHost.setCurrentTabByTag("Home_TAB");
        radioButtonList.get(0).setChecked(true);
    }

    private void setupIntent() {
        this.mTabHost = getTabHost();
        TabHost localTabHost = this.mTabHost;


        localTabHost.addTab(buildTabSpec("Home_TAB", R.string.main_home,
                R.drawable.icon_1_n, this.homeIntent));
        localTabHost.addTab(buildTabSpec("B_TAB", R.string.main_news,
                R.drawable.icon_2_n, this.mBIntent));
        localTabHost.addTab(buildTabSpec("C_TAB", R.string.main_manage_date,
                R.drawable.icon_3_n, this.mCIntent));
        localTabHost.addTab(buildTabSpec("D_TAB", R.string.main_friends,
                R.drawable.icon_4_n, this.mDIntent));
        localTabHost.addTab(buildTabSpec("MORE_TAB", R.string.more,
                R.drawable.icon_5_n, this.mEIntent));
    }

    /**
     * 自定义创建标签项
     * @param tag  标签标识
     * @param resLabel 标签名字
     * @param resIcon  标签图标
     * @param content  标签对应的内容
     * @return
     */
    private TabHost.TabSpec buildTabSpec(String tag, int resLabel, int resIcon, final Intent content) {
        return this.mTabHost.newTabSpec(tag).setIndicator(getString(resLabel),
                getResources().getDrawable(resIcon)).setContent(content);
    }

    public static void changeTabByIndex(int index){
        mTabHost.setCurrentTab(index);
        radioButtonList.get(index).setChecked(true);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if(isChecked){
            switch (buttonView.getId()) {
                case R.id.rb_home:
                    this.mTabHost.setCurrentTabByTag("Home_TAB");
                    break;
                case R.id.radio_button1:
                    this.mTabHost.setCurrentTabByTag("B_TAB");
                    break;
                case R.id.radio_button2:
                    this.mTabHost.setCurrentTabByTag("C_TAB");
                    break;
                case R.id.radio_button3:
                    this.mTabHost.setCurrentTabByTag("D_TAB");
                    break;
                case R.id.radio_button4:
                    changeTabByIndex(4);
//                    this.mTabHost.setCurrentTabByTag("MORE_TAB");
                    break;
            }
        }

    }

}
