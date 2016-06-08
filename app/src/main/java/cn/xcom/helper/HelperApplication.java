package cn.xcom.helper;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by zhuchongkun on 16/5/27.
 */
public class HelperApplication  extends Application{
    private Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext=getApplicationContext();
        Fresco.initialize(mContext);
    }
}
