package cn.wischool.ws1app.wischool_androidapp.widge.homeviewpage;

import android.content.Context;
import android.support.v4.view.ViewPager;

import cn.wischool.ws1app.wischool_androidapp.R;
import cn.wischool.ws1app.wischool_androidapp.ui.HomeActivity;

/**
 * Created by Administrator on 2015/11/12.
 */
public class ViewPagerListener implements ViewPager.OnPageChangeListener {
    private Context context;
    private int num;
    private ViewPager viewPager;

    public ViewPagerListener(Context context,int num,ViewPager viewPager){
        this.context = context;
        this.num = num;
        this.viewPager = viewPager;
    }

    public int getNum() {
        return num;
    }

    public Context getContext(){
        return this.context;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
//        System.out.println(">>>>>>>>>>>>>>>>>" + position);
        if (position==0){
            HomeActivity.atomicInteger.set(position);
            viewPager.setCurrentItem(num-2,false);
            HomeActivity.strips[getNum()-2].setImageResource(R.drawable.home_strip_focus);
            HomeActivity.strips[1].setImageResource(R.drawable.home_strip_unfocus);
            return;
        }
        if (position==getNum()-1){
            HomeActivity.atomicInteger.set(position);
            viewPager.setCurrentItem(1,false);
            HomeActivity.strips[position-1].setImageResource(R.drawable.home_strip_unfocus);
            HomeActivity.strips[1].setImageResource(R.drawable.home_strip_focus);
        }
        else{
            for (int i=1;i<getNum()-1;i++){
                if (position==i){
                    HomeActivity.atomicInteger.set(position);
                    HomeActivity.strips[i].setImageResource(R.drawable.home_strip_focus);
                }else{
                    HomeActivity.strips[i].setImageResource(R.drawable.home_strip_unfocus);
                }
            }
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}