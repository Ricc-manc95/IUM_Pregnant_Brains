// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Switch;

public class NinjaSwitch extends Switch
{

    public boolean stealth;

    public NinjaSwitch(Context context)
    {
        super(context);
        stealth = false;
    }

    public NinjaSwitch(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        stealth = false;
    }

    public NinjaSwitch(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        stealth = false;
    }

    public NinjaSwitch(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        stealth = false;
    }

    public void setOnCheckedChangeListener(final android.widget.CompoundButton.OnCheckedChangeListener listener)
    {
        _cls1 _lcls1 = null;
        if (listener != null)
        {
            _lcls1 = new _cls1();
        }
        super.setOnCheckedChangeListener(_lcls1);
    }

    private class _cls1
        implements android.widget.CompoundButton.OnCheckedChangeListener
    {

        private final NinjaSwitch this$0;
        private final android.widget.CompoundButton.OnCheckedChangeListener val$listener;

        public final void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
        {
            if (!stealth)
            {
                listener.onCheckedChanged(compoundbutton, flag);
            }
        }

        _cls1()
        {
            this$0 = NinjaSwitch.this;
            listener = oncheckedchangelistener;
            super();
        }
    }

}
