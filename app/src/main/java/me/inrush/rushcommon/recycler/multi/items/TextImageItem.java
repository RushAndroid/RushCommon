package me.inrush.rushcommon.recycler.multi.items;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.inrush.common.recycler.BaseRecyclerItemView;
import me.inrush.rushcommon.R;
import me.inrush.rushcommon.recycler.multi.bean.TextImageBean;

/**
 * @author inrush
 * @date 2018/6/27 下午8:24
 */
public class TextImageItem extends BaseRecyclerItemView<TextImageBean, TextImageItem.ViewHolder> {


    public TextImageItem(Context context, TextImageBean data) {
        super(context, data);
    }

    @Override
    public ViewHolder onCreateViewHolder(Context context, ViewGroup parent, int layoutId) {
        return new ViewHolder(LayoutInflater.from(context).inflate(layoutId, parent, false));
    }

    @Override
    public void onBindViewHolder(Context context, ViewHolder holder, TextImageBean data) {
        holder.text.setText(data.getText());
        holder.image.setImageBitmap(data.getImage());
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text)
        TextView text;
        @BindView(R.id.image)
        ImageView image;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
