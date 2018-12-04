package inspect.cegzm.com;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

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

//        try{
//            Field field = TextView.class.getDeclaredField("mCursorDrawableRes");
//            field.setAccessible(true);
//            field.set(editText,0xff00ff00);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        for (int i=0;i<4;i++) {
            mImageList.add("https://sys.cegzm.com/tmp_2943f200969e7855e8e894d7b183ebca.jpg");
            mTitleList.add("234");
        }

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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
