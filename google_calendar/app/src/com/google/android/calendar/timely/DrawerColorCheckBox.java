// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.widget.CheckBox;

public class DrawerColorCheckBox extends CheckBox
{

    public DrawerColorCheckBox(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public void setColor(int i)
    {
        setButtonTintList(new ColorStateList(new int[][] {
            new int[0]
        }, new int[] {
            i
        }));
    }
}
