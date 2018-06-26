package me.inrush.common.app;

import android.support.annotation.StringRes;
import android.widget.Toast;

import java.io.File;

import me.inrush.common.thread.Run;
import me.inrush.common.thread.handler.Action;

/**
 * @author inrush
 * @date 2017/8/7.
 * @package me.inrush.common.app
 */

public class BaseApplication extends android.app.Application {

    private static BaseApplication instance;

    public static BaseApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    /**
     * 获取缓存文件夹地址
     *
     * @return 当前App的缓存文件地址
     */
    public static File getCacheDirFile() {
        return instance.getCacheDir();
    }


    /**
     * 显示一个Toast
     *
     * @param msg 字符串
     */
    public static void showToast(final String msg) {
        // Toast 只能在主线程中显示，所有需要进行线程转换，
        // 保证一定是在主线程进行的show操作
        Run.onUiAsync(new Action() {
            @Override
            public void call() {
                // 这里进行回调的时候一定就是主线程状态了
                Toast.makeText(instance, msg, Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * 显示一个Toast
     *
     * @param msgId 传递的是字符串的资源
     */
    public static void showToast(@StringRes int msgId) {
        showToast(instance.getString(msgId));
    }

    /**
     * 显示一个Toast
     *
     * @param msgId      传递的是字符串的资源
     * @param formatArgs 占位符参数
     */
    public static void showToast(@StringRes int msgId, Object... formatArgs) {
        showToast(instance.getString(msgId, formatArgs));
    }


}
