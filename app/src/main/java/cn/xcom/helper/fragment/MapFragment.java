package cn.xcom.helper.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

//import com.baidu.mapapi.map.MapView;

import com.baidu.mapapi.map.MapView;

import cn.xcom.helper.R;
import cn.xcom.helper.activity.HelpMeActivity;
import cn.xcom.helper.activity.IHelpActivity;

/**
 * Created by zhuchongkun on 16/5/27.
 * 主页面——地图
 */
public class MapFragment extends Fragment implements View.OnClickListener{
    private  String TAG="MapFragment";
    private Context mContext;
    private RelativeLayout rl_location,rl_authentication_list;
    private TextView tv_I_help,tv_help_me,tv_city_interaction;
    private MapView mMapView = null;

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
        mMapView = (MapView) getView().findViewById(R.id.mapView_fragment_map);

    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_fragment_map_location:
                Toast.makeText(mContext,"未开发",Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_fragment_map_authentication_list:
                Toast.makeText(mContext,"未开发",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_fragment_map_I_help:
                startActivity(new Intent(mContext, IHelpActivity.class));
                break;
            case R.id.tv_fragment_map_help_me:
                startActivity(new Intent(mContext, HelpMeActivity.class));
                break;
            case R.id.tv_fragment_map_city_interaction:
                Toast.makeText(mContext,"未开发",Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
