package me.inrush.common.widget.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

public abstract class BaseMultiRecyclerItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<BaseRecyclerItemView> mDataList;

    public BaseMultiRecyclerItemAdapter(List<BaseRecyclerItemView> dataList) {
        mDataList = dataList;
        for (BaseRecyclerItemView item : dataList) {
            item.attachAdapter(this);
        }
    }

    @Override
    public int getItemViewType(int position) {
        BaseRecyclerItemView data = mDataList.get(position);
        return getTypeMapPolicy().toType(data);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getTypeMapPolicy().toItemView(viewType).innerCreateVH(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BaseRecyclerItemView item = mDataList.get(position);
        item.innerBindVH(holder, position);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    /**
     * 获取布局映射
     *
     * @return 布局映射 {@link TypeMapPolicy}
     */
    protected abstract TypeMapPolicy getTypeMapPolicy();
}
