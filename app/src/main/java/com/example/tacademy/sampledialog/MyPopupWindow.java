package com.example.tacademy.sampledialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

/**
 * Created by Tacademy on 2016-08-02.
 */
public class MyPopupWindow extends PopupWindow {
    public MyPopupWindow(Context context) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.popup_button_menu, null);
        setContentView(view);
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setOutsideTouchable(true);
    }
}