package me.inrush.rushcommon.recycler.multi;

import me.inrush.common.recycler.BaseRecyclerItemView;
import me.inrush.common.recycler.TypeMapPolicy;
import me.inrush.rushcommon.R;
import me.inrush.rushcommon.recycler.multi.items.ImageItem;
import me.inrush.rushcommon.recycler.multi.items.TextImageItem;
import me.inrush.rushcommon.recycler.multi.items.TextItem;

/**
 * @author inrush
 * @date 2018/6/27 下午4:47
 */
public class RecycleTypeMapPolicy implements TypeMapPolicy {

    @Override
    public int toType(BaseRecyclerItemView viewFactory) {
        int type = 0;
        if (viewFactory instanceof ImageItem) {
            type = R.layout.item_image;
        } else if (viewFactory instanceof TextItem) {
            type = R.layout.item_text;
        } else if (viewFactory instanceof TextImageItem) {
            type = R.layout.item_text_image;
        }
        if (type == 0) {
            throw new IllegalArgumentException("this type " + viewFactory.getClass() + " is not found!");
        }
        TYPES_MAPPING.put(type, viewFactory);
        return type;
    }

    @Override
    public BaseRecyclerItemView toItemView(int type) {
        return TYPES_MAPPING.get(type);
    }
}
