package cn.xcom.helper.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.xcom.helper.R;

/**
 * Created by zhuchongkun on 16/5/27.
 * 主页面——特卖
 *
 */
public class SaleFragment extends Fragment implements View.OnClickListener{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sale,container,false);
    }

    @Override
    public void onClick(View v) {

    }
}
