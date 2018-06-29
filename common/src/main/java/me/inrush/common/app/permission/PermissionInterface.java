package me.inrush.common.app.permission;

/**
 * @author inrush
 * @date 2018/6/28 下午8:57
 */
public interface PermissionInterface {
    /**
     * 请求权限成功回调
     */
    void requestPermissionsSuccess();

    /**
     * 请求权限失败回调
     */
    void requestPermissionsFail();
}
