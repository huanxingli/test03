package cn.wischool.ws1app.wischool_androidapp.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.KeyEvent;

import java.lang.reflect.Field;

import cn.wischool.ws1app.wischool_androidapp.AppManager;
import cn.wischool.ws1app.wischool_androidapp.common.UiHelper;
import roboguice.activity.RoboActivity;


/**
 * Created by sunyuhong on 15/9/30.
 */

public class BaseFragmentActivity extends RoboActivity {

    // 实现再按一次退出程序
    protected static long exitTime = 0;

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("生命周期", ((Object) this).getClass().getName() + " onPause");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);


        Log.i("生命周期", ((Object) this).getClass().getName() + " -onCreate");
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("生命周期", ((Object) this).getClass().getName() + " onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("生命周期", ((Object) this).getClass().getName() + " onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("生命周期", ((Object) this).getClass().getName() + " onStop");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("生命周期", ((Object) this).getClass().getName() + " onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
        Log.i("生命周期", ((Object) this).getClass().getName() + " onDestroy");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (((Object) this).getClass().getName().equals(HomeActivity.class.getName())
                    ||((Object) this).getClass().getName().equals(Login.class.getName())) {
                if ((System.currentTimeMillis() - exitTime) > 2000) {
                    UiHelper.getInstance().toastMessage(this, "再按一次返回桌面");
                    exitTime = System.currentTimeMillis();
                } else {
//                    moveTaskToBack(true);
                    back();
                }
            } else if (((Object) this).getClass().getName().equals(BActivity.class.getName())
                    ||((Object) this).getClass().getName().equals(CActivity.class.getName())
                    ||((Object) this).getClass().getName().equals(DActivity.class.getName())
                    ||((Object) this).getClass().getName().equals(EActivity.class.getName())){
                MainActivity.changeTabByIndex(0);
            }
            else if(AppManager.getAppManager().getActivityStackSize() >= 1) {
                back();
            }
            else
            {
                super.onKeyDown(keyCode, event);
            }

        }

        return true;

    }

    // 顶部栏返回键
    private void back() {
        finishCurrentActivity();
    }

    //结束当前activity，虽然和back（）的效果一样，但是，back（）是按顶部返回键的，而finishCurrentActivity可以在任意地方使用
    public void finishCurrentActivity()
    {
        // 结束Activity&从堆栈中移除
        AppManager.getAppManager().finishActivity(this);
    }


}
