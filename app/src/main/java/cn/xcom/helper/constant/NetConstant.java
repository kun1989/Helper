package cn.xcom.helper.constant;

/**
 * Created by zhuchongkun on 16/5/29.
 */
public interface NetConstant extends HelperConstant {
    /**
     *注册
     */
    public final static String NET_REGISTER=NET_HOST_PREFIX+"a=AppRegister&";
    /**
     * 登录
     */
    public final static String NET_LOGIN=NET_HOST_PREFIX+"a=applogin&";
    /**
     * 发送验证码
     */
    public final static String NET_GET_CODE="a=SendMobileCode&";
    /**
     * 修改密码
     */
    public final static String NET_RESET_PASSWORD="a=forgetpwd&";
    /**
     * 获取个人资料
     */
    public final static String NET_GET_USER_INFO="a=getuserinfo&";
}
