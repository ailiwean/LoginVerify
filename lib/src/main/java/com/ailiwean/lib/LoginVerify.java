package com.ailiwean.lib;

import android.view.View;

import com.ailiwean.lib.utils.ReflectUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;

public class LoginVerify {

    public static final int KEY = -100001;

    public static LoginVerify get(LoginChunk loginChunk) {
        return new LoginVerify(loginChunk);
    }

    private LoginChunk loginChunk;

    protected LoginVerify(LoginChunk loginChunk) {
        this.loginChunk = loginChunk;
    }

    public void registerView(final View... views) {

        if (loginChunk == null)
            return;

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (View v : views)
                    interceptView(v);
            }
        }).start();

    }

    public void registerApapter(final BaseQuickAdapter adapter) {

        if (loginChunk == null)
            return;

        new Thread(new Runnable() {
            @Override
            public void run() {
                interceptAdapterItem(adapter);
            }
        }).start();

    }

    public void registerAdapterChild(final BaseQuickAdapter adapter) {

        if (loginChunk == null)
            return;

        new Thread(new Runnable() {
            @Override
            public void run() {
                interceptAdapterChild(adapter);
            }
        }).start();

    }

    private void interceptView(final View v) {

        if (v == null)
            return;
        Object info = ReflectUtils.reflect(v).field("mListenerInfo").get();
        final View.OnClickListener oriClick = ReflectUtils.reflect(info).field("mOnClickListener").get();
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isNeedBreak(view))
                    return;

                if (!loginChunk.verifyLogin()) {
                    loginChunk.goLogin();
                } else if (oriClick != null) {
                    oriClick.onClick(view);
                }
            }
        });
    }

    private void interceptAdapterItem(BaseQuickAdapter adapter) {
        if (adapter == null)
            return;
        final BaseQuickAdapter.OnItemClickListener itemClickListener = ReflectUtils.reflect(adapter).field("mOnItemClickListener").get();
        if (itemClickListener == null)
            return;
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                if (isNeedBreak(view))
                    return;

                if (!loginChunk.verifyLogin())
                    loginChunk.goLogin();
                else {
                    itemClickListener.onItemClick(adapter, view, position);
                }
            }
        });
    }

    private void interceptAdapterChild(BaseQuickAdapter adapter) {
        if (adapter == null)
            return;
        final BaseQuickAdapter.OnItemChildClickListener itemChildClickListener = ReflectUtils.reflect(adapter).field("mOnItemChildClickListener").get();
        if (itemChildClickListener == null)
            return;
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                if (isNeedBreak(view))
                    return;

                if (!loginChunk.verifyLogin())
                    loginChunk.goLogin();
                else {
                    itemChildClickListener.onItemChildClick(adapter, view, position);
                }
            }
        });
    }

    protected boolean isNeedBreak(View view) {

        if (view.getTag(KEY) != null) {
            if (System.currentTimeMillis() - (Long) view.getTag(KEY) < loginChunk.breakTime()) {
                return true;
            }
        }
        view.setTag(KEY, System.currentTimeMillis());
        return false;
    }

}
