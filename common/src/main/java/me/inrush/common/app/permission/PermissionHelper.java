package me.inrush.common.app.permission;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.support.annotation.NonNull;

import me.inrush.common.app.permission.annotation.Permissions;
import me.inrush.common.app.permission.annotation.RequestCode;
import me.inrush.common.app.utils.SettingUtils;
import me.inrush.common.app.utils.SystemUtils;

/**
 * @author inrush
 * @date 2018/6/28 下午8:58
 */
public class PermissionHelper {
    private Activity mActivity;
    private PermissionInterface mPermissionInterface;
    private String[] mPermissions;
    private int mRequestCode;

//    public PermissionHelper(@NonNull Activity activity, @NonNull PermissionInterface permissionInterface) {
//        mActivity = activity;
//        mPermissionInterface = permissionInterface;
//    }

    /**
     * activity must impl {@link PermissionInterface}
     *
     * @param activity Activity
     */
    public PermissionHelper(@NonNull Activity activity) {
        if (activity instanceof PermissionInterface) {
            mActivity = activity;
            mPermissionInterface = (PermissionInterface) activity;
            mPermissions = getPermissionsAnnotation(mActivity);
            mRequestCode = getRequestCode(mActivity);
        } else {
            throw new IllegalArgumentException("this activity is not impl PermissionInterface");
        }
    }

    public static void openPermissionSettingPage(Context context) {
        SystemUtils.ROM_TYPE romType = SystemUtils.GetRomType();
        switch (romType) {
            case EMUI:
                SettingUtils.openHuaweiPermissionSettingPage(context);
                break;
            case MIUI:
                SettingUtils.openMiuiPermissionSettingPage(context);
                break;
            case FLYME:
                SettingUtils.openMeizuPermissionSettingPage(context);
                break;
            case OTHER:
                SettingUtils.openOtherPhonePermissionSettingPage(context);
                break;
            default:
                break;
        }
    }

    @SuppressLint("PrivateApi")
    private static String[] getPermissionsAnnotation(Activity activity) {
        boolean hasAnnotation = activity.getClass().isAnnotationPresent(Permissions.class);
        if (hasAnnotation) {
            Permissions permissions =
                    activity.getClass().getAnnotation(Permissions.class);
            return permissions.value();
        } else {
            throw new Resources.NotFoundException("permission is request, but not found,please add @Permission() above the Activity!");
        }
    }

    @SuppressLint("PrivateApi")
    private static int getRequestCode(Activity activity) {
        boolean hasAnnotation = activity.getClass().isAnnotationPresent(RequestCode.class);
        if (hasAnnotation) {
            RequestCode requestCode =
                    activity.getClass().getAnnotation(RequestCode.class);
            return requestCode.value();
        } else {
            throw new Resources.NotFoundException("requestCode is request, but not found,please add @RequestCode() above the Activity!");
        }
    }

    /**
     * 检查所有的权限是否都通过了
     *
     * @return {True | False}
     */
    public boolean checkPermissions() {
        for (String permission : mPermissions) {
            if (!PermissionUtil.hasPermission(mActivity, permission)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 开始请求权限。
     * 方法内部已经对Android M 或以上版本进行了判断，外部使用不再需要重复判断。
     * 如果设备还不是M或以上版本，则也会回调到requestPermissionsSuccess方法。
     */
    public void requestPermissions() {
        mPermissions = getPermissionsAnnotation(mActivity);
        mRequestCode = getRequestCode(mActivity);
        if (mPermissions != null && mPermissions.length > 0) {
            PermissionUtil.requestPermissions(mActivity, mPermissions, mRequestCode);
        } else {
            mPermissionInterface.requestPermissionsSuccess();
        }
    }

    /**
     * 在Activity中的onRequestPermissionsResult中调用
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     * @return true 代表对该requestCode感兴趣，并已经处理掉了。false 对该requestCode不感兴趣，不处理。
     */
    public boolean requestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == mRequestCode) {
            //是否全部权限已授权
            boolean isAllGranted = true;
            for (int result : grantResults) {
                if (result == PackageManager.PERMISSION_DENIED) {
                    isAllGranted = false;
                    break;
                }
            }
            if (isAllGranted) {
                //已全部授权
                mPermissionInterface.requestPermissionsSuccess();
            } else {
                //权限有缺失
                mPermissionInterface.requestPermissionsFail();
            }
            return true;
        }
        return false;
    }

}
