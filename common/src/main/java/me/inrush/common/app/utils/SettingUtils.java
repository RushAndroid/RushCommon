package me.inrush.common.app.utils;

import android.arch.core.BuildConfig;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

/**
 * @author inrush
 * @date 2018/6/29 下午4:13
 */
public class SettingUtils {
    /**
     * 获取应用详情页面intent
     *
     * @return
     */
    public static Intent getAppDetailSettingIntent(Context context) {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
        }
        return localIntent;
    }

    /**
     * 跳转到miui的权限管理页面
     */
    public static void openMiuiPermissionSettingPage(Context context) {
        Intent i = new Intent("miui.intent.action.APP_PERM_EDITOR");
        ComponentName componentName = new ComponentName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
        i.setComponent(componentName);
        i.putExtra("extra_pkgname", context.getPackageName());
        try {
            context.startActivity(i);
        } catch (Exception e) {
            e.printStackTrace();
            context.startActivity(getAppDetailSettingIntent(context));
        }
    }

    /**
     * 华为的权限管理页面
     */
    public static void openHuaweiPermissionSettingPage(Context context) {
        try {
            Intent intent = new Intent();
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ComponentName comp = new ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity");
            intent.setComponent(comp);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            context.startActivity(getAppDetailSettingIntent(context));
        }

    }

    /**
     * 跳转到魅族的权限管理系统
     */
    public static void openMeizuPermissionSettingPage(Context context) {
        Intent intent = new Intent("com.meizu.safe.security.SHOW_APPSEC");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.putExtra("packageName", BuildConfig.APPLICATION_ID);
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            context.startActivity(getAppDetailSettingIntent(context));
        }
    }

    public static void openOtherPhonePermissionSettingPage(Context context) {
        String SCHEME = "package";
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts(SCHEME, context.getPackageName(), null);
        intent.setData(uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            context.startActivity(getAppDetailSettingIntent(context));
        }
    }
}
