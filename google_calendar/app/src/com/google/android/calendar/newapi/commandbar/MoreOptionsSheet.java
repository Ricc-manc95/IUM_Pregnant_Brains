// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.commandbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

public class MoreOptionsSheet extends FrameLayout
    implements android.view.View.OnClickListener
{
    public static interface OnMoreOptionsItemSelectedListener
    {

        public abstract void onMoreOptionsItemSelected(int i);
    }


    public OnMoreOptionsItemSelectedListener listener;
    public View panelView;
    public View scrimView;

    public MoreOptionsSheet(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public void onClick(View view)
    {
        if (listener == null)
        {
            return;
        }
        OnMoreOptionsItemSelectedListener onmoreoptionsitemselectedlistener = listener;
        int i = view.getId();
        if (i == 0x7f100285)
        {
            i = 1;
        } else
        if (i == 0x7f100286)
        {
            i = 2;
        } else
        {
            i = 0;
        }
        onmoreoptionsitemselectedlistener.onMoreOptionsItemSelected(i);
    }
}
