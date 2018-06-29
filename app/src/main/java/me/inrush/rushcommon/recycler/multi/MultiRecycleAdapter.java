package me.inrush.rushcommon.recycler.multi;

import java.util.List;

import me.inrush.common.widget.recycler.BaseMultiRecyclerItemAdapter;
import me.inrush.common.widget.recycler.BaseRecyclerItemView;
import me.inrush.common.widget.recycler.TypeMapPolicy;

/**
 * @author inrush
 * @date 2018/6/27 下午5:04
 */
public class MultiRecycleAdapter extends BaseMultiRecyclerItemAdapter {
    public MultiRecycleAdapter(List<BaseRecyclerItemView> dataList) {
        super(dataList);
    }

    @Override
    protected TypeMapPolicy getTypeMapPolicy() {
        return new RecycleTypeMapPolicy();
    }
}
