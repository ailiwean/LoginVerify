package com.ailiwean.lib;

import android.app.Activity;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.ailiwean.lib.annotations.LoginDunk;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class AnLoginVerify extends LoginVerify {

    private AnLoginVerify(LoginChunk loginChunk, final Activity instance) {
        super(loginChunk);
        if (instance != null)
            instance.getWindow().getDecorView().postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        retrospectView(instance);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }, 0);
    }

    private AnLoginVerify(LoginChunk loginChunk, final Fragment instance) {
        super(loginChunk);
        if (instance != null && instance.getActivity() != null)
            instance.getActivity().getWindow().getDecorView().postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        retrospectView(instance);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }, 0);
    }

    private AnLoginVerify(LoginChunk loginChunk, Object instance) {
        super(loginChunk);
        if (instance == null)
            return;
        try {
            retrospectView(instance);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static AnLoginVerify bind(LoginChunk loginChunk, Activity instance) {
        return new AnLoginVerify(loginChunk, instance);
    }

    public static AnLoginVerify bind(LoginChunk loginChunk, Fragment instance) {
        return new AnLoginVerify(loginChunk, instance);
    }

    public static AnLoginVerify bind(LoginChunk loginChunk, Object instance) {
        return new AnLoginVerify(loginChunk, instance);
    }

    private void retrospectView(Object instance) throws IllegalAccessException {

        Field[] fields = instance.getClass().getDeclaredFields();

        List<View> signViews = new ArrayList<>();

        for (Field field : fields) {

            if (field.getAnnotation(LoginDunk.class) == null)
                continue;

            if (field.getType() != View.class)
                continue;

            field.setAccessible(true);

            signViews.add((View) field.get(instance));
        }

        registerView(signViews.toArray(new View[]{}));

    }
}
