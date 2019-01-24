package utils;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.List;


/**
 * Created by zhangli on 2017/7/17.
 */

public class CheckExitService extends Service{

    private String packageName = "inspect.cegzm.com";


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);

        Log.e("TGA","===appExit()");
    }

    //service异常停止的回调
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            ActivityManager activtyManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activtyManager.getRunningAppProcesses();
            for (int i = 0; i < runningAppProcesses.size(); i++) {
                if (packageName.equals(runningAppProcesses.get(i).processName)) {
                    Log.e("TGA","===appRun()");                }
            }
        }catch (Exception e){}

        return START_NOT_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("TGA","===appBegin ()");    }
}
