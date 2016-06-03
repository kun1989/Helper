package cn.xcom.helper.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;

import cn.xcom.helper.R;

/**
 * Created by zhuchongkun on 16/6/3.
 */
public class AuthorizedActivity extends BaseActivity implements View.OnClickListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_authorized);
    }

    @Override
    public void onClick(View v) {

    }
}
