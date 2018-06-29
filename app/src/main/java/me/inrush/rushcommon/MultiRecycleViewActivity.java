package me.inrush.rushcommon;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.inrush.common.app.BaseActivity;
import me.inrush.common.widget.recycler.BaseRecyclerItemView;
import me.inrush.common.widget.recycler.RecycleViewDivider;
import me.inrush.rushcommon.recycler.multi.MultiRecycleAdapter;
import me.inrush.rushcommon.recycler.multi.bean.ImageBean;
import me.inrush.rushcommon.recycler.multi.bean.TextBean;
import me.inrush.rushcommon.recycler.multi.bean.TextImageBean;
import me.inrush.rushcommon.recycler.multi.items.ImageItem;
import me.inrush.rushcommon.recycler.multi.items.TextImageItem;
import me.inrush.rushcommon.recycler.multi.items.TextItem;

/**
 * @author inrush
 * @date 2018/6/27 下午4:26
 */
public class MultiRecycleViewActivity extends BaseActivity {
    @BindView(R.id.topbar)
    QMUITopBar mTopBar;
    @BindView(R.id.recycler_multi_recycle_view)
    RecyclerView mRecycleView;

    private List<BaseRecyclerItemView> mItemViews;
    private MultiRecycleAdapter mAdapter;

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, MultiRecycleViewActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_multi_recycle_view;
    }

    @Override
    protected void initData() {
        super.initData();
        mItemViews = new ArrayList<>();
        mItemViews.add(new ImageItem(this, new ImageBean(BitmapFactory.decodeResource(getResources(), R.drawable.image))));
        mItemViews.add(new TextItem(this, new TextBean("我是文本")));
        mItemViews.add(new TextImageItem(this, new TextImageBean("我是图片文本", BitmapFactory.decodeResource(getResources(), R.drawable.avatar))));
        mItemViews.add(new TextItem(this, new TextBean("我是文本")));
        mItemViews.add(new ImageItem(this, new ImageBean(BitmapFactory.decodeResource(getResources(), R.drawable.image))));
        mItemViews.add(new ImageItem(this, new ImageBean(BitmapFactory.decodeResource(getResources(), R.drawable.image))));
        mItemViews.add(new ImageItem(this, new ImageBean(BitmapFactory.decodeResource(getResources(), R.drawable.image))));
        mItemViews.add(new TextItem(this, new TextBean("我是文本")));
        mItemViews.add(new TextItem(this, new TextBean("我是文本")));
        mItemViews.add(new TextItem(this, new TextBean("我是文本")));
        mItemViews.add(new TextItem(this, new TextBean("我是文本")));
        mItemViews.add(new TextImageItem(this, new TextImageBean("我是图片文本", BitmapFactory.decodeResource(getResources(), R.drawable.avatar))));
        mItemViews.add(new TextImageItem(this, new TextImageBean("我是图片文本", BitmapFactory.decodeResource(getResources(), R.drawable.avatar))));
        mItemViews.add(new TextImageItem(this, new TextImageBean("我是图片文本", BitmapFactory.decodeResource(getResources(), R.drawable.avatar))));
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        ButterKnife.bind(this);
        mTopBar.setTitle("Multi RecycleView");
        mTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mAdapter = new MultiRecycleAdapter(mItemViews);
        mRecycleView.setAdapter(mAdapter);
        mRecycleView.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL));
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
    }
}
