package cn.wischool.ws1app.wischool_androidapp.widge.tasksearch;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RadioButton;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.wischool.ws1app.wischool_androidapp.R;
import cn.wischool.ws1app.wischool_androidapp.common.SharedPreferencesHelper;
import cn.wischool.ws1app.wischool_androidapp.common.UiHelper;
import cn.wischool.ws1app.wischool_androidapp.model.db.College;
import cn.wischool.ws1app.wischool_androidapp.model.db.DatabaseHelper;
import cn.wischool.ws1app.wischool_androidapp.model.db.Department;
import cn.wischool.ws1app.wischool_androidapp.model.db.Province;
import cn.wischool.ws1app.wischool_androidapp.ui.BaseFragmentActivity;
//import cn.wischool.ws1app.wischool_androidapp.viewpagerindicator.TabPageIndicator;

/**
 * Created by Administrator on 2015/10/28.
 */
//@ContentView(R.layout.activity_search)
public class SearchActivity extends FragmentActivity implements View.OnClickListener{

    private RadioButton mRadTaskList;
    private RadioButton mRadTaskClassify;
    private RadioButton mRadTaskSelect;
    private EditText editTextSearch;

    private Fragment taskListFrg;
    private Fragment taskClassifyFrg;
    private Fragment taskSelectFrg;




    Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_search);


        initView();
        initEvent();
        select(0);
    }



    private void initView(){
        mRadTaskList = (RadioButton)findViewById(R.id.radio_tasklist);
        mRadTaskClassify = (RadioButton)findViewById(R.id.radio_classify);
        mRadTaskSelect = (RadioButton)findViewById(R.id.radio_select);
        editTextSearch = (EditText) findViewById(R.id.search_edt);
    }

    private void initEvent(){
        mRadTaskList.setOnClickListener(this);
        mRadTaskClassify.setOnClickListener(this);
        mRadTaskSelect.setOnClickListener(this);


//        editTextSearch.setFocusableInTouchMode(true);

        HomeSearch homeSearch = (HomeSearch) findViewById(R.id.homeSearch2);
        homeSearch.setTopbarOnClickListener(new HomeSearch.topbarClickListener() {
            @Override
            public void editClick() {

                UiHelper.getInstance().toastMessage(SearchActivity.this,"SearchActivity点击" + editTextSearch.isEnabled() + editTextSearch.isFocusable() + editTextSearch.isFocusableInTouchMode());
            }
        });
    }

    private void select(int i){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragment(transaction);
        switch (i){
            case 0:
                if (taskListFrg==null){
                    taskListFrg = new TaskListFragment();
                    transaction.add(R.id.id_content,taskListFrg);
                }else{
                    transaction.show(taskListFrg);
                }
                break;
            case 1:
                if (taskClassifyFrg==null){
                    taskClassifyFrg = new TaskListClassifyFragment();
                    transaction.add(R.id.id_content,taskClassifyFrg);
                }else{
                    transaction.show(taskClassifyFrg);
                }
                break;
            case 2:
                if (taskSelectFrg==null){
                    taskSelectFrg = new TaskListSelectFragment();
                    transaction.add(R.id.id_content,taskSelectFrg);
                }else{
                    transaction.show(taskSelectFrg);
                }
                break;
        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction)
    {
        if (taskListFrg != null)
        {
            transaction.hide(taskListFrg);
        }
        if (taskClassifyFrg != null)
        {
            transaction.hide(taskClassifyFrg);
        }
        if (taskSelectFrg != null)
        {
            transaction.hide(taskSelectFrg);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.radio_tasklist:
                select(0);
                break;
            case R.id.radio_classify:
                select(1);
                break;
            case R.id.radio_select:
                select(2);
                break;
        }
    }
    public void setListener(){

//        //监听搜索框
//        searchEditText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                handler.post(runnable);
//            }
//        });

//        taskListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                TextView tv = (TextView) view.findViewById(R.id.tvname);
//                UiHelper.getInstance().toastMessage(getApplication(),"你点击了" + String.valueOf(tv.getText()) + "个选项");
//            }
//        });


    }

//    Runnable runnable = new Runnable() {
//        @Override
//        public void run() {
//            String data = String.valueOf(searchEditText.getText());  //获取搜索框的数据
//            list.clear();
//            getmDatas(list, data);//筛选数据
//
//            adapter.notifyDataSetChanged();
//        }
//    };

//    public void initData(List<TaskCellData> mDatas){
//        TaskCellData taskCellData = null;
//        for (int i=0;i<DataInput.data.length;i++){
//            mDatas.add(DataInput.data[i]);
//        }
//    }
//
//    /**
//     * 根据搜索框的关键字从数据里面搜索，筛选之后放进mDatas里面
//     * @param mDatas
//     * @param info
//     */
//    public void getmDatas(List<TaskCellData> mDatas,String info){
//        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + info);
//        for (int i=0;i<DataInput.data.length;i++){
//            if (DataInput.data[i].getName().contains(info)){
//                mDatas.add(DataInput.data[i]);
//                Log.i("更新列表",DataInput.data[i].getName());
//            }
//        }
//
//    }
}
