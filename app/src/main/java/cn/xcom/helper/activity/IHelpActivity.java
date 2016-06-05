package cn.xcom.helper.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;

import cn.xcom.helper.R;

/**
 * Created by zhuchongkun on 16/6/4.
 * 我帮页
 */
public class IHelpActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_i_help);
    }

    @Override
    public void onClick(View v) {

    }
}
