package inspect.cegzm.com;

import android.content.Context;
import android.os.Environment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.module.GlideModule;

import java.io.File;

/**
 * Created by zhangli on 2018/12/4.
 */

public class GlidCache implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
        //设置缓存目录(都可以自定义的)
        File storageDirectory = Environment.getExternalStorageDirectory();
        String downloadDirectoryPath=storageDirectory+"/TestGlideDisk";

        //设置缓存的大小为100M
        int cacheSize = 500*1024*1024;
        builder.setDiskCache(new DiskLruCacheFactory(downloadDirectoryPath, cacheSize));

    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}
