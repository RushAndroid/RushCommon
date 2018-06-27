package me.inrush.rushcommon.recycler.single;

import android.view.View;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.inrush.common.recycler.BaseRecyclerAdapter;
import me.inrush.rushcommon.R;

/**
 * @author inrush
 */
public class FunItemAdapter extends BaseRecyclerAdapter<FunItemBean> {

    public FunItemAdapter(List<FunItemBean> dataList) {
        super(dataList);
    }

    @Override
    protected int getItemLayoutId(int position, FunItemBean data) {
        return R.layout.item_fun;
    }

    @Override
    protected BaseViewHolder<FunItemBean> onCreateViewHolder(View root, int layoutId) {
        return new ViewHolder(root);
    }


    static class ViewHolder extends BaseRecyclerAdapter.BaseViewHolder<FunItemBean> {
        @BindView(R.id.title_item_fun)
        TextView mTitle;
        @BindView(R.id.desc_item_fun)
        TextView mDesc;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void onBind(FunItemBean data) {
            mTitle.setText(data.getTitle());
            mDesc.setText(data.getDesc());
        }

        @Override
        public void onClick(View v) {

        }
    }
}
