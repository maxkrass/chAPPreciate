package com.maxkrass.appreciate.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.maxkrass.appreciate.R;

public class CheckBoxWidget extends FrameLayout implements Checkable {

    private CheckBox checkBox;
    private TextView titleView;

    public CheckBoxWidget(Context context) {
        super(context);
        init((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE), false, "");
    }

    public CheckBoxWidget(Context context, AttributeSet attributeset) {
        this(context, attributeset, 0);
    }

    public CheckBoxWidget(Context context, AttributeSet attributeset, int i) {
        super(context, attributeset, i);
        boolean checked;
        String text;
        TypedArray a = context.getTheme().obtainStyledAttributes(attributeset, R.styleable.CheckBoxWidget, 0, 0);
        try {
            checked = a.getBoolean(R.styleable.CheckBoxWidget_checked, false);
            text = a.getString(R.styleable.CheckBoxWidget_label);
        } finally {
            a.recycle();
        }
        init((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE), checked, text);
    }

    private void init(LayoutInflater layoutinflater, boolean checked, String text) {
        layoutinflater.inflate(R.layout.checkbox_widget, this);
        titleView = (TextView) findViewById(R.id.title);
        setTitleView(text);
        checkBox = (CheckBox) findViewById(R.id.checkbox);
        setChecked(checked);
    }

    public void toggle() {
        checkBox.toggle();
    }

    public boolean isChecked() {
        return checkBox.isChecked();
    }

    public void setChecked(boolean flag) {
        checkBox.setChecked(flag);
    }

    public void setTitleView(String s) {
        titleView.setText(s);
    }

    public void setEnabled(boolean enabled) {
        checkBox.setEnabled(enabled);
    }

    public String toString() {
        return String.valueOf(isChecked()) + "";
    }
}
