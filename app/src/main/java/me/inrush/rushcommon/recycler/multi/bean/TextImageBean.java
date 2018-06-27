package me.inrush.rushcommon.recycler.multi.bean;

import android.graphics.Bitmap;

/**
 * @author inrush
 * @date 2018/6/27 下午8:24
 */
public class TextImageBean {
    private String text;
    private Bitmap image;

    public TextImageBean(String text, Bitmap image) {
        this.text = text;
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
