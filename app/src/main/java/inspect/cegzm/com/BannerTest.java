package inspect.cegzm.com;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;
import cz.msebera.android.httpclient.Header;
import retrifitRequest.SdCardUtils;
import utils.CheckExitService;
import utils.FileSizeUtil;

/**
 * Created by zhangli on 2018/11/28.
 */

public class BannerTest extends Activity {

    private BGABanner mBanner;
    private EditText editText;
    private List<String> mImageList = new ArrayList<>();
    private List<String> mTitleList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        this.mBanner = findViewById(R.id.banner_test);
        this.editText = findViewById(R.id.et);

        savaInfo1();
//        getInfo();
//        try{
//            Field field = TextView.class.getDeclaredField("mCursorDrawableRes");
//            field.setAccessible(true);
//            field.set(editText,0xff00ff00);
//        }catch (Exception e){
//            e.printStackTrace();
//        }

//        Log.e("TGA",FileSizeUtil.getFileOrFilesSize(Environment.getExternalStorageDirectory()+"/CegzGlideDisk",3)+"MB");

        for (int i=0;i<4;i++) {
            mImageList.add("https://sys.cegzm.com/tmp_2943f200969e7855e8e894d7b183ebca.jpg");
            mTitleList.add("234");
        }

        getHello();
        mBanner.setData(mImageList,mTitleList);
        mBanner.setAutoPlayAble(true);
        mBanner.setAdapter(new BGABanner.Adapter<ImageView,String>() {

            @Override
            public void fillBannerItem(BGABanner banner, ImageView itemView, @Nullable String model, int position) {
                Glide.with(BannerTest.this)
                        .load(model)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_background)
                        .centerCrop()
                        .dontAnimate()
                        .into(itemView);
            }
        });
        mBanner.setDelegate(new BGABanner.Delegate() {
            @Override
            public void onBannerItemClick(BGABanner banner, View itemView, @Nullable Object model, int position) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent=new Intent(this, CheckExitService.class);
        startService(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Glide.get(BannerTest.this).clearDiskCache();
            }
        }).start();

    }

    private boolean isForeground(Context context) {
        if (context != null) {
            ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
            String currentPackageName = cn.getPackageName();
            if (!TextUtils.isEmpty(currentPackageName) && currentPackageName.equals(context.getPackageName())) {
                return true;
            }
            return false;
        }
        return false;
    }

    private void getHello() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(this, "http://192.168.0.79:9999/hello", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.e("TGA","==success===="+new String(responseBody));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("TGA","==fail");

            }
        });
    }

    //避免桌面进入重新实例化入口activity
    private void judge() {
        if (!this.isTaskRoot()) {//判断是否为任务栈根部
            Intent intent = getIntent();
            if (intent != null){
                String action = intent.getAction();
                if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && Intent.ACTION_MAIN.equals(action)){
                    finish();
                    return;
                }
            }
        }
    }

    private void saveInfo() {
       String file =  Environment.getExternalStorageDirectory()+"/CegzLog.txt";
       String Content  = "hello world";
       long Firsttime = System.currentTimeMillis();
        SdCardUtils.saveInfo(file,Content);
    }

    private void savaInfo1() {
        String file =  Environment.getExternalStorageDirectory()+"/CegzLog.txt";
        String Content  = "hello world";
        long Firsttime = System.currentTimeMillis();
        SdCardUtils.bufferSave(Content,file);
    }
    private void getInfo() {
        String file =  Environment.getExternalStorageDirectory()+"/CegzLog.txt";
        Log.e("TGA",SdCardUtils.redSavaInfo());

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        finish();
        return super.onKeyDown(keyCode, event);

    }
}
