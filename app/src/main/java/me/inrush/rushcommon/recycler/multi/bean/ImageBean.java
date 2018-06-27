package me.inrush.rushcommon.recycler.multi.bean;

import android.graphics.Bitmap;

/**
 * @author inrush
 * @date 2018/6/27 下午4:54
 */
public class ImageBean {
    private Bitmap image;

    public ImageBean(Bitmap image) {
        this.image = image;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
