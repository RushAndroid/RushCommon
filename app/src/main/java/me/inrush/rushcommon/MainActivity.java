package me.inrush.rushcommon;

import android.Manifest;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.inrush.common.app.BaseActivity;
import me.inrush.common.app.permission.PermissionHelper;
import me.inrush.common.app.permission.PermissionInterface;
import me.inrush.common.app.permission.annotation.Permissions;
import me.inrush.common.app.permission.annotation.RequestCode;
import me.inrush.common.widget.recycler.BaseRecyclerAdapter;
import me.inrush.common.widget.recycler.RecycleViewDivider;
import me.inrush.rushcommon.recycler.single.FunItemAdapter;
import me.inrush.rushcommon.recycler.single.FunItemBean;

/**
 * @author inrush
 */
@RequestCode(0x111)
@Permissions({
        Manifest.permission.CAMERA,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
})
public class MainActivity extends BaseActivity implements PermissionInterface {
    @BindView(R.id.topbar)
    QMUITopBar mTopBar;
    @BindView(R.id.recycler_main)
    RecyclerView mRecyclerView;

    private List<FunItemBean> mFunItemList;
    private FunItemAdapter mFunItemAdapter;
    private PermissionHelper mPermissionHelper;

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
                if ("多布局".equals(data.getTitle())) {
                    MultiRecycleViewActivity.start(MainActivity.this);
                } else if ("获取动态权限".equals(data.getTitle())) {
                    if (!mPermissionHelper.checkPermissions()) {
                        mPermissionHelper.requestPermissions();
                    } else {
                        App.showToast("全部权限已经获取,无需再次获取.");
                    }
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
        mFunItemList.add(new FunItemBean("获取动态权限", "Android 6.0 动态获取权限"));

        mPermissionHelper = new PermissionHelper(this);
    }


//    @Override
//    protected void onStart() {
//        super.onStart();
//        if (!mPermissionHelper.checkPermissions()) {
//            mPermissionHelper.requestPermissions();
//        }
//    }


    @Override
    public void requestPermissionsSuccess() {
        App.showToast("获取动态权限成功");
    }

    @Override
    public void requestPermissionsFail() {
        App.showToast("获取动态权限失败,跳转到设置页面");
        PermissionHelper.openPermissionSettingPage(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (mPermissionHelper.requestPermissionsResult(requestCode, permissions, grantResults)) {
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
