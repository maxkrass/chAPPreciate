package com.maxkrass.appreciate.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.maxkrass.appreciate.R;

public class CheckBoxWidget extends FrameLayout
{

    private CheckBox checkBox;
    private TextView titleView;

    public CheckBoxWidget(Context context)
    {
        super(context);
        init((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
    }

    public CheckBoxWidget(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        init((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
    }

    public CheckBoxWidget(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        init((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
    }

    private void init(LayoutInflater layoutinflater)
    {
        layoutinflater.inflate(R.layout.checkbox_widget, this);
        titleView = (TextView)findViewById(R.id.title);
        checkBox = (CheckBox)findViewById(R.id.checkbox);
    }

    public void toggle() {
        checkBox.setChecked(!checkBox.isChecked());
    }

    public boolean isChecked()
    {
        return checkBox.isChecked();
    }

    public void setCheckBox(boolean flag)
    {
        checkBox.setChecked(flag);
    }

    public void setTitleView(String s)
    {
        titleView.setText(s);
    }

	public void setEnabled(boolean enabled) {
		checkBox.setEnabled(enabled);
	}

    public String toString()
    {
        return String.valueOf(isChecked()) + "";
    }
}
