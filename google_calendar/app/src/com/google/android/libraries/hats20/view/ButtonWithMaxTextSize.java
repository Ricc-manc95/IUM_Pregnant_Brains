// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

public class ButtonWithMaxTextSize extends AppCompatButton
{

    public ButtonWithMaxTextSize(Context context)
    {
        super(context);
    }

    public ButtonWithMaxTextSize(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        capTextSize(context, attributeset);
    }

    public ButtonWithMaxTextSize(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        capTextSize(context, attributeset);
    }

    private final void capTextSize(Context context, AttributeSet attributeset)
    {
        context = context.obtainStyledAttributes(attributeset, com.google.android.libraries.hats20.R.styleable.ButtonWithMaxTextSize);
        setTextSize(0, Math.min(getTextSize(), context.getDimensionPixelSize(com.google.android.libraries.hats20.R.styleable.ButtonWithMaxTextSize_textSizeMaxDp, 0x7fffffff)));
        context.recycle();
    }
}
