package me.inrush.rushcommon.recycler.multi.items;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.inrush.common.widget.recycler.BaseRecyclerItemView;
import me.inrush.rushcommon.R;
import me.inrush.rushcommon.recycler.multi.bean.ImageBean;

/**
 * @author inrush
 * @date 2018/6/27 下午4:52
 */
public class ImageItem extends BaseRecyclerItemView<ImageBean, ImageItem.ViewHolder> {

    public ImageItem(Context context, ImageBean data) {
        super(context, data);
    }

    @Override
    public ViewHolder onCreateViewHolder(Context context, ViewGroup parent, int layoutId) {
        return new ViewHolder(LayoutInflater.from(context).inflate(layoutId, parent, false));
    }

    @Override
    public void onBindViewHolder(Context context, ViewHolder holder, ImageBean data) {
        holder.image.setImageBitmap(data.getImage());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView image;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
