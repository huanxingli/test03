package cn.wischool.ws1app.wischool_androidapp.widge.tasksearch;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import cn.wischool.ws1app.wischool_androidapp.R;

/**
 * Created by Administrator on 2015/12/3.
 */
public class HomeSearch extends RelativeLayout {
    private RelativeLayout homeSearch;
    private EditText editTextSearch;
    private ImageView imageInfo;
    private ImageView imagePublish;
    private Context context;

    private topbarClickListener listener;

    public interface topbarClickListener{
        public void editClick();
    }

    public void setTopbarOnClickListener(topbarClickListener listener){
        this.listener = listener;
    }

    public HomeSearch(Context context){
        super(context);
        initHomeSearch(context);
    }
    public HomeSearch(Context context,AttributeSet attrs){
        super(context,attrs);
        initHomeSearch(context);
    }

    private void initHomeSearch(Context context){
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.fragment_home_search,this);
        homeSearch = (RelativeLayout) findViewById(R.id.homeSearch);
        editTextSearch = (EditText) findViewById(R.id.search_edt);
        imageInfo = (ImageView)findViewById(R.id.home_info_image);
        imagePublish = (ImageView)findViewById(R.id.imageView2);

        editTextSearch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.editClick();
            }
        });
    }

//    public void setListener(){
//        editTextSearch.setOnTouchListener(new View.OnTouchListener() {
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
//				if (event.getAction() == MotionEvent.ACTION_DOWN) {
//					Intent intent = new Intent(context, SearchActivity.class);
//                    Activity activity = (Activity)context;
//					activity.startActivity(intent);
//				}
//				return false;
//			}
//		});
//    }


}
