package cn.xcom.helper.bean;

import android.content.Context;
import java.io.Serializable;
import cn.xcom.helper.sp.UserSp;

/**
 * Created by zhuchongkun on 16/5/27.
 */
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userId;// 用户ID
    private String userPassword;// 用户密码
    private String userImg;// 用户头像
    private String userPhone;// 用户手机号
    private String userCode;// 验证码
    private String userName;// 姓名
    private String userGender;// 性别
    private String userID;//身份证
    private String userAddress;//地址


    public UserInfo() {

    }

    public UserInfo(Context context) {
        readData(context);
    }

    public void readData(Context context) {
        UserSp sp = new UserSp(context);
        sp.read(this);
    }

    public void writeData(Context context) {
        UserSp sp = new UserSp(context);
        sp.write(this);
    }

    public void clearData(Context context) {
        UserSp sp = new UserSp(context);
        sp.clear();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserPhone() {

        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserImg() {

        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }
}
