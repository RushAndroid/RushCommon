package me.inrush.rushcommon.recycler.single;

public class FunItemBean {
    /**
     * 标题
     */
    private String title;
    /**
     * 描述
     */
    private String desc;

    public FunItemBean(String title, String desc) {
        this.title = title;
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
