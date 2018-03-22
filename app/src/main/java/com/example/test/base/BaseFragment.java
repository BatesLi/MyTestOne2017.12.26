package com.example.test.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.trello.rxlifecycle.components.support.RxFragment;

/**
 * 项目Fragment的基类
 */
public abstract class BaseFragment extends RxFragment {
    protected View view;
    //宿主Activity
    protected Activity mActivity;

    //得到Fragment的布局Id
    protected abstract int getLayoutId();

    protected Activity getHoldingActivity() {
        return mActivity;
    }
    /*
    可能你遇到过getActivity()返回null，或者平时运行完好的代码，
    在“内存重启”之后，调用getActivity()的地方却返回null，报了空指针异常
    解决办法：
   (对于Fragment已经onDetach这种情况，我们应该避免在这之后再去调用宿主Activity对象，
    比如取消这些异步任务，但我们的团队可能会有粗心大意的情况，所以下面给出的这个方案会保证安全)
    在Fragment基类里设置一个Activity mActivity的全局变量，在onAttach(Activity activity)里赋值，
    使用mActivity代替getActivity()，保证Fragment即使在onDetach后，
    仍持有Activity的引用（有引起内存泄露的风险，但是异步任务没停止的情况下，本身就可能已内存泄漏，
    相比Crash，这种做法“安全”些）即:
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }
    * */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
        Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, rootView);
        init(rootView, savedInstanceState);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    //初始化
    protected abstract void init(View view, Bundle savedInstanceState);
}
