package cn.xcom.helper.net;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by zhuchongkun on 16/5/27.
 */
public class HelperAsyncHttpClient {
    private static AsyncHttpClient client=new AsyncHttpClient();
    static {
        client.setTimeout(5000);//如果不设置，默认10s
    }

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler){
        client.get(url, params, responseHandler);
    }
    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler){
        client.post(url, params, responseHandler);
    }
}
