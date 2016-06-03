package cn.xcom.helper.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.xcom.helper.R;

/**
 * Created by zhuchongkun on 16/5/27.
 * 主页面——地图
 */
public class MapFragment extends Fragment implements View.OnClickListener{
    private  String TAG="MapFragment";
    private Context mContext;
    private RelativeLayout rl_location,rl_authentication_list;
    private TextView tv_I_help,tv_help_me,tv_city_interaction;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext=getActivity();
        initView();
    }
    private void initView(){
        rl_location= (RelativeLayout) getView().findViewById(R.id.rl_fragment_map_location);
        rl_location.setOnClickListener(this);
        rl_authentication_list= (RelativeLayout) getView().findViewById(R.id.rl_fragment_map_authentication_list);
        rl_authentication_list.setOnClickListener(this);
        tv_I_help= (TextView) getView().findViewById(R.id.tv_fragment_map_I_help);
        tv_I_help.setOnClickListener(this);
        tv_help_me= (TextView) getView().findViewById(R.id.tv_fragment_map_help_me);
        tv_help_me.setOnClickListener(this);
        tv_city_interaction= (TextView) getView().findViewById(R.id.tv_fragment_map_city_interaction);
        tv_city_interaction.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_fragment_map_location:

                break;
            case R.id.rl_fragment_map_authentication_list:

                break;
            case R.id.tv_fragment_map_I_help:

                break;
            case R.id.tv_fragment_map_help_me:

                break;
            case R.id.tv_fragment_map_city_interaction:

                break;
        }

    }
}
