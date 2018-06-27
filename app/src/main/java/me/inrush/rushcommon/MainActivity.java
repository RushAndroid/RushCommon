package me.inrush.rushcommon;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.inrush.common.app.BaseActivity;
import me.inrush.common.recycler.BaseRecyclerAdapter;
import me.inrush.common.recycler.RecycleViewDivider;
import me.inrush.rushcommon.recycler.single.FunItemAdapter;
import me.inrush.rushcommon.recycler.single.FunItemBean;

/**
 * @author inrush
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.topbar)
    QMUITopBar mTopBar;
    @BindView(R.id.recycler_main)
    RecyclerView mRecyclerView;


    private List<FunItemBean> mFunItemList;
    private FunItemAdapter mFunItemAdapter;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        ButterKnife.bind(this);
        mTopBar.setTitle("Common Library");
        mFunItemAdapter = new FunItemAdapter(mFunItemList);
        mRecyclerView.setAdapter(mFunItemAdapter);
        mRecyclerView.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL, 1, Color.parseColor("#eeeeee")));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mFunItemAdapter.setListener(new BaseRecyclerAdapter.AdapterListenerImpl<FunItemBean>() {
            @Override
            public void onItemClick(BaseRecyclerAdapter.BaseViewHolder holder, FunItemBean data) {
                super.onItemClick(holder, data);
                if (data.getTitle().equals("多布局")) {
                    MultiRecycleViewActivity.start(MainActivity.this);
                }
            }

            @Override
            public void onItemLongClick(BaseRecyclerAdapter.BaseViewHolder holder, FunItemBean data) {
                super.onItemLongClick(holder, data);
                App.showToast("长按了 " + data.getTitle());
            }
        });


    }

    @Override
    protected void initData() {
        super.initData();
        mFunItemList = new ArrayList<>();
        mFunItemList.add(new FunItemBean("多布局", "多布局列表功能"));
        mFunItemList.add(new FunItemBean("RecycleView 分割线", "多类型RecycleView分割线"));
    }
}
