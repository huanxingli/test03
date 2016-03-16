package cn.wischool.ws1app.wischool_androidapp.common;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;

/**
 * Created by Administrator on 2015/11/12.
 */
public class ImageUtil {
    //获取图片
    @SuppressWarnings("ResourceType")
    public static Bitmap getBitmap(Context context,int id){
        //获取图片资源
        InputStream is = null;
        try {
            is = context.getResources().openRawResource(id);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        return BitmapFactory.decodeStream(is, null, opt);
    }
}
