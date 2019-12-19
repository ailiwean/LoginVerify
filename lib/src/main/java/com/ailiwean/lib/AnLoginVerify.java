package com.ailiwean.lib;

import android.view.View;

import com.ailiwean.lib.annotations.LoginDunk;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class AnLoginVerify extends LoginVerify {

    private AnLoginVerify(LoginChunk loginChunk, final Object instance) {
        super(loginChunk);
        if (instance == null)
            return;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    retrospectView(instance);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }).start();
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
