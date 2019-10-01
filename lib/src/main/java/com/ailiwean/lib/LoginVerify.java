package com.ailiwean.lib;

import android.view.View;

import com.ailiwean.lib.utils.ReflectUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;

public class LoginVerify {

    public static LoginVerify get(LoginChunk loginChunk) {
        if (loginChunk == null)
            try {
                throw new Exception("LoginChunk does not null");
            } catch (Exception e) {
                e.printStackTrace();
            }
        return new LoginVerify(loginChunk);
    }

    private LoginChunk loginChunk;

    protected LoginVerify(LoginChunk loginChunk) {
        this.loginChunk = loginChunk;
    }

    public void registerView(View... views) {

        if (loginChunk == null)
            return;

        for (View v : views)
            interceptView(v);
    }

    public void registerApapter(BaseQuickAdapter adapter) {

        if (loginChunk == null)
            return;

        interceptAdapterItem(adapter);
    }

    public void registerAdapterChild(BaseQuickAdapter adapter) {

        if (loginChunk == null)
            return;

        interceptAdapterChild(adapter);
    }

    private void interceptView(final View v) {

        if (v == null)
            return;
        Object info = ReflectUtils.reflect(v).field("mListenerInfo").get();
        final View.OnClickListener oriClick = ReflectUtils.reflect(info).field("mOnClickListener").get();
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                if (!loginChunk.verifyLogin())
                    loginChunk.goLogin();
                else if (itemClickListener != null) {
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
                if (!loginChunk.verifyLogin())
                    loginChunk.goLogin();
                else if (itemChildClickListener != null) {
                    itemChildClickListener.onItemChildClick(adapter, view, position);
                }
            }
        });
    }
}
