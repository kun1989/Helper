package cn.xcom.helper.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cn.xcom.helper.R;
import cn.xcom.helper.bean.SkillTagInfo;
import cn.xcom.helper.constant.NetConstant;
import cn.xcom.helper.net.HelperAsyncHttpClient;
import cn.xcom.helper.utils.LogUtils;
import cn.xcom.helper.view.NoScrollGridView;
import cz.msebera.android.httpclient.Header;

/**
 * Created by zhuchongkun on 16/6/4.
 * 帮我页
 */
public class HelpMeActivity extends BaseActivity implements View.OnClickListener {
    private String TAG="HelpMeActivity";
    private Context mContext;
    private RelativeLayout rl_back;
    private NoScrollGridView gv_skill;
    private ArrayList<SkillTagInfo> skillTagInfos;
    private HelpMeSkillAdapter mHelpMeSkillAdapter;
    private EditText et_content,et_phone,et_site_location,et_service_location,et_wages,et_validity_period;
    private TextView tv_time_unit,tv_service_charge;
    private ImageView iv_site_location,iv_service_location,iv_validity_period;
    private Button bt_submit;
    private SkillTagInfo selectTag;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_help_me);
        mContext=this;
        initView();
    }

    private void initView(){
        rl_back= (RelativeLayout) findViewById(R.id.rl_help_me_back);
        rl_back.setOnClickListener(this);
        et_content= (EditText) findViewById(R.id.et_help_me_content);
        et_phone= (EditText) findViewById(R.id.et_help_me_phone);
        et_site_location= (EditText) findViewById(R.id.et_help_me_site_location);
        et_service_location= (EditText) findViewById(R.id.et_help_me_service_location);
        et_wages= (EditText) findViewById(R.id.et_help_me_wages);
        et_validity_period= (EditText) findViewById(R.id.et_help_me_validity_period);
        tv_time_unit= (TextView) findViewById(R.id.tv_help_me_time_unit);
        tv_time_unit.setOnClickListener(this);
        tv_service_charge= (TextView) findViewById(R.id.tv_help_me_service_charge);
        iv_site_location= (ImageView) findViewById(R.id.iv_help_me_site_location);
        iv_site_location.setOnClickListener(this);
        iv_service_location= (ImageView) findViewById(R.id.iv_help_me_service_location);
        iv_service_location.setOnClickListener(this);
        iv_validity_period= (ImageView) findViewById(R.id.iv_help_me_validity_period);
        iv_validity_period.setOnClickListener(this);
        bt_submit= (Button) findViewById(R.id.bt_help_me_submit);
        bt_submit.setOnClickListener(this);
        selectTag=new SkillTagInfo();
        skillTagInfos=new ArrayList<SkillTagInfo>();
        gv_skill= (NoScrollGridView) findViewById(R.id.gridView_help_me);
        mHelpMeSkillAdapter=new HelpMeSkillAdapter(mContext,skillTagInfos);
        if (skillTagInfos.size()>0&&selectTag.getSkill_id()!=null){
            selectTag.setSkill_id(skillTagInfos.get(0).getSkill_id());
            selectTag.setSkill_name(skillTagInfos.get(0).getSkill_name());
        }
        gv_skill.setAdapter(mHelpMeSkillAdapter);
        gv_skill.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectTag= (SkillTagInfo) parent.getAdapter().getItem(position);
                mHelpMeSkillAdapter.notifyDataSetChanged();
            }
        });

        getSkill();
    }
    private void getSkill(){
        RequestParams params=new RequestParams();
        params.put("id",0);
        HelperAsyncHttpClient.get(NetConstant.NET_GET_TASKLIST,params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                LogUtils.e(TAG,"--statusCode->"+statusCode+"==>"+response.toString());
                if (response!=null){
                    try {
                        String state=response.getString("status");
                        if (state.equals("success")){
                            JSONArray data=response.getJSONArray("data");
                            skillTagInfos.clear();
                            for (int i=1;i<data.length();i++){
                                SkillTagInfo info=new SkillTagInfo();
                                JSONObject jsonObject=data.getJSONObject(i);
                                info.setSkill_id(jsonObject.getString("id"));
                                info.setSkill_name(jsonObject.getString("name"));
                                skillTagInfos.add(info);
                            }
                            if (skillTagInfos.size()>0&&selectTag.getSkill_id()!=null){
                                selectTag.setSkill_id(skillTagInfos.get(0).getSkill_id());
                                selectTag.setSkill_name(skillTagInfos.get(0).getSkill_name());
                            }
                            mHelpMeSkillAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                LogUtils.e(TAG,"--statusCode->"+statusCode+"==>"+responseString);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_help_me_back:
                finish();
                break;
            case R.id.iv_help_me_site_location:
                break;
            case R.id.iv_help_me_service_location:
                break;
            case R.id.tv_help_me_time_unit:
                break;
            case R.id.iv_help_me_validity_period:
                break;
            case R.id.bt_help_me_submit:
                startActivity(new Intent(mContext,UploadContractActivity.class));
                break;
        }

    }
    public class HelpMeSkillAdapter extends BaseAdapter {
        private Context mContext;
        private ArrayList<SkillTagInfo> mSkillTagInfos;
        public HelpMeSkillAdapter(Context context, ArrayList<SkillTagInfo> skillTagInfos) {
            this.mContext=context;
            if (skillTagInfos==null)
                skillTagInfos=new ArrayList<SkillTagInfo>();
            this.mSkillTagInfos=skillTagInfos;
        }

        @Override
        public int getCount() {
            return mSkillTagInfos.size();
        }

        @Override
        public SkillTagInfo getItem(int position) {
            return mSkillTagInfos.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView==null){
                viewHolder=new ViewHolder();
                convertView= LayoutInflater.from(mContext).inflate(R.layout.item_help_me_skill_tag,null);
                viewHolder.tv_tag= (TextView) convertView.findViewById(R.id.tv_item_help_me_skill_tag);
                convertView.setTag(viewHolder);
            }else{
                viewHolder= (ViewHolder) convertView.getTag();
            }
            viewHolder.tv_tag.setText(mSkillTagInfos.get(position).getSkill_name());
            if (mSkillTagInfos.get(position).getSkill_name().equals(selectTag.getSkill_name())){
                viewHolder.tv_tag.setTextColor(mContext.getResources().getColor(R.color.colorTextWhite));
                viewHolder.tv_tag.setBackgroundResource(R.drawable.tv_tag_select);
            }else{
                viewHolder.tv_tag.setTextColor(mContext.getResources().getColor(R.color.colorTheme));
                viewHolder.tv_tag.setBackgroundResource(R.drawable.tv_tag);
            }
            return convertView;
        }
        private  class ViewHolder{
            TextView tv_tag;
        }
    }

}
