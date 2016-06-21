package cn.xcom.helper;

import android.app.Application;
import android.content.Context;

import com.baidu.mapapi.SDKInitializer;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by zhuchongkun on 16/5/27.
 */
public class HelperApplication  extends Application{
    private Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext=getApplicationContext();
        //初始化Fresco
        Fresco.initialize(mContext);
        SDKInitializer.initialize(mContext);
        //初始化ImageLoader
        initImageLoader(mContext);
    }
    private void initImageLoader(Context mContext){
//        File cacheDir = StorageUtils.getOwnCacheDirectory(mContext,"caterin/Cache");
//        // LruCache通过构造函数传入缓存值，以KB为单位。
//        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
//        // 使用最大可用内存值的1/8作为缓存的大小。
//        int cacheSize = maxMemory / 8;
        // 创建配置ImageLoader(所有的选项都是可选的,只使用那些你真的想定制)，这个可以设定在APPLACATION里面，设置为全局的配置参数
//        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(mContext)
//                .memoryCacheExtraOptions(480, 800)
//                .threadPoolSize(3)// 线程池内加载的数量
//                .threadPriority(Thread.NORM_PRIORITY - 1).tasksProcessingOrder(QueueProcessingType.FIFO) // default
//                .denyCacheImageMultipleSizesInMemory().memoryCache(new UsingFreqLimitedMemoryCache(cacheSize))
//                // You can pass your own memory cache
//                .memoryCache(new LruMemoryCache(cacheSize)).memoryCache(new WeakMemoryCache())
//                // implementation你可以通过自己的内存缓存实现
//                .memoryCacheSize(cacheSize).memoryCacheSizePercentage(13) // default
//                .discCacheSize(cacheSize)
//                // .discCacheFileNameGenerator(new Md5FileNameGenerator())
//                // 将保存的时候的URI名称用MD5加密
//                // .discCacheFileNameGenerator(new
//                // HashCodeFileNameGenerator())// 将保存的时候的URI名称用HASHCODE加密
//                .tasksProcessingOrder(QueueProcessingType.LIFO).discCacheFileCount(1000) // 缓存的File数量
//                .defaultDisplayImageOptions(DisplayImageOptions.createSimple()).imageDownloader(new BaseImageDownloader(mContext, 5 * 1000, 30 * 1000))
//                // connectTimeout (5s), readTimeout(30s)超时时间
//                .writeDebugLogs() // Remove for release app
//                .build();
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration
                .createDefault(mContext);
        ImageLoader.getInstance().init(configuration);// 全局初始化此配置
    }
}
