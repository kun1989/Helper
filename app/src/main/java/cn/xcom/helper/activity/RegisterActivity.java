package cn.xcom.helper.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;

import cn.xcom.helper.R;
import cn.xcom.helper.constant.HelperConstant;
import cn.xcom.helper.constant.NetConstant;
import cn.xcom.helper.net.HelperAsyncHttpClient;
import cn.xcom.helper.utils.LogUtils;
import cn.xcom.helper.utils.RegexUtil;
import cz.msebera.android.httpclient.Header;

/**
 * Created by zhuchongkun on 16/5/27.
 * 注册页
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener,RadioGroup.OnCheckedChangeListener{
    private String TAG="RegisterActivity";
    private RelativeLayout rl_back;
    private SimpleDraweeView iv_head;
    private String head=null;
    private EditText et_name,et_ID,et_address,et_phone,et_verification,et_password;
    private TextView tv_getVerification;
    private ImageView iv_password;
    private Button bt_submit;
    private RadioGroup rg_gender;
    private RadioButton rb_male,rb_female;
    private Context mContext;
    // 保存的文件的路径
    private String filepath = "/sdcard/myheader";
    private String picname = "newpic";
    private static final int PHOTO_REQUEST_CAMERA=1;//拍照
    private static final int PHOTO_REQUEST_ALBUM=2;//相册
    private static final int PHOTO_REQUEST_CUT=3;//剪裁
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS=4;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);
        mContext=this;
        initView();
    }
    private void initView(){
        rl_back= (RelativeLayout) findViewById(R.id.rl_register_back);
        rl_back.setOnClickListener(this);
        iv_head= (SimpleDraweeView) findViewById(R.id.iv_register_head);
        iv_head.setOnClickListener(this);
        rg_gender= (RadioGroup) findViewById(R.id.radioGroup_register_gender);
        rb_male= (RadioButton) findViewById(R.id.radioButton_register_man);
        rb_female= (RadioButton) findViewById(R.id.radioButton_register_woman);
        rg_gender.setOnCheckedChangeListener(this);
        et_name= (EditText) findViewById(R.id.et_register_name);
        et_ID= (EditText) findViewById(R.id.et_register_ID);
        et_address= (EditText) findViewById(R.id.et_register_address);
        et_phone= (EditText) findViewById(R.id.et_register_phone);
        et_verification= (EditText) findViewById(R.id.et_register_verification);
        et_password= (EditText) findViewById(R.id.et_register_password);
        tv_getVerification= (TextView) findViewById(R.id.tv_register_verification_get);
        tv_getVerification.setOnClickListener(this);
        bt_submit= (Button) findViewById(R.id.bt_register_submit);
        bt_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_register_back:
                finish();
                break;
            case R.id.iv_register_head:
                showPickDialog();
                break;
            case R.id.tv_register_verification_get:
                getVerification();
                break;
            case R.id.bt_register_submit:
                toRegister();
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (group.getCheckedRadioButtonId()){
            case R.id.radioButton_register_man:
                break;
            case R.id.radioButton_register_woman:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode!=RESULT_CANCELED){
                switch (requestCode) {
                    case PHOTO_REQUEST_CAMERA:// 相册
                        // 判断存储卡是否可以用，可用进行存储
                        String state = Environment.getExternalStorageState();
                        if (state.equals(Environment.MEDIA_MOUNTED)) {
                            File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
                            File tempFile = new File(path, "newpic.jpg");
                            startPhotoZoom(Uri.fromFile(tempFile));
                        } else {
                            Toast.makeText(getApplicationContext(), "未找到存储卡，无法存储照片！", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case PHOTO_REQUEST_ALBUM:// 图库
                        startPhotoZoom(data.getData());
                        break;

                    case PHOTO_REQUEST_CUT: // 图片缩放完成后
                        if (data != null) {
                            getImageToView(data);
                        }
                        break;
                }
            }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void showPickDialog(){
        new AlertDialog.Builder(this,AlertDialog.THEME_HOLO_LIGHT)
                .setNegativeButton("相册", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent albumIntent=new Intent();
                        albumIntent.setType("image/*");
                        albumIntent.setAction(Intent.ACTION_PICK);
                        startActivityForResult(albumIntent,PHOTO_REQUEST_ALBUM);
                    }
                }).setPositiveButton("拍照", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        int permissionCheck = ContextCompat.checkSelfPermission(mContext,
                                Manifest.permission.CAMERA);
                        if(permissionCheck== PackageManager.PERMISSION_GRANTED){
                            Intent cameraIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            String state = Environment.getExternalStorageState();
                            if (state.equals("")){
                                File path=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
                                File file=new File(path,"helperhead.jpg");
                                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                            }
                            startActivityForResult(cameraIntent,PHOTO_REQUEST_CAMERA);
                        }else if (permissionCheck== PackageManager.PERMISSION_DENIED){
                            // Should we show an explanation?
                            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) mContext,
                                    Manifest.permission.CAMERA)) {

                                // Show an expanation to the user *asynchronously* -- don't block
                                // this thread waiting for the user's response! After the user
                                // sees the explanation, try again to request the permission.

                            } else {

                                // No explanation needed, we can request the permission.

                                ActivityCompat.requestPermissions((Activity) mContext,
                                        new String[]{Manifest.permission.CAMERA},
                                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                                // app-defined int constant. The callback method gets the
                                // result of the request.
                            }
                        }

            }
        }).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 340);
        intent.putExtra("outputY", 340);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    /**
     * 保存裁剪之后的图片数据
     *
     * @param data
     */
    private void getImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            Drawable drawable = new BitmapDrawable(this.getResources(), photo);
            iv_head.setImageDrawable(drawable);
            storeImageToSDCARD(photo, picname, filepath);
        }
    }

    /**
     * storeImageToSDCARD 将bitmap存放到sdcard中
     * */
    public void storeImageToSDCARD(Bitmap colorImage, String ImageName, String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        File imagefile = new File(file, ImageName + ".jpg");
        try {
            imagefile.createNewFile();
            FileOutputStream fos = new FileOutputStream(imagefile);
            colorImage.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            head = imagefile.getPath();
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getVerification(){
//      http://bang.xiaocool.net/index.php?g=apps&m=index&a=SendMobileCode&phone=18653503680
        String phone=et_phone.getText().toString().trim();
        if (!RegexUtil.checkMobile(phone)) {
            Toast.makeText(mContext,"请正确输入手机号！",Toast.LENGTH_LONG).show();
            return;
        }
        RequestParams params=new RequestParams();
        params.put("phone",phone);
        HelperAsyncHttpClient.get(NetConstant.NET_GET_CODE,params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                LogUtils.e(TAG,"--statusCode->"+statusCode+"==>"+response.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                LogUtils.e(TAG,"--statusCode->"+statusCode+"==>"+responseString);
            }
        });

    }
    private void toRegister(){
//      http://bang.xiaocool.net/index.php?g=apps&m=index&a=AppRegister&name=我的昵称&avatar=1234.jpg&phone=18653503680&password=123456&code=2345&devicestate=1
        String phone=et_phone.getText().toString().trim();
        String name=et_name.getText().toString().trim();
        String ID=et_ID.getText().toString().trim();
        String address=et_address.getText().toString().trim();
        String password=et_password.getText().toString().trim();
        String verification=et_verification.getText().toString().trim();
        if (!RegexUtil.IsChineseOrEnglish(name)){
            Toast.makeText(mContext,"请正确填写姓名！",Toast.LENGTH_LONG).show();
            return;
        }
        if (!RegexUtil.checkIdCard(ID)){
            return;
        }
        if (RegexUtil.checkMobile(phone)){
            return;
        }
        RequestParams params=new RequestParams();
        params.put("name",name);
//      params.put("avatar","AppRegister");
        params.put("phone",phone);
        params.put("ID",ID);
        params.put("address",address);
        params.put("password",password);
        params.put("code",verification);
        params.put("devicestate", HelperConstant.DEVICE_STATE);
        HelperAsyncHttpClient.get(NetConstant.NET_REGISTER,params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                LogUtils.e(TAG,"--statusCode->"+statusCode+"==>"+response.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                LogUtils.e(TAG,"--statusCode->"+statusCode+"==>"+responseString);
            }
        });
    }
}