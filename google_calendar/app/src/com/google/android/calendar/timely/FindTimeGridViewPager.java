// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.content.Context;
import android.util.AttributeSet;
import com.google.android.calendar.utils.viewpager.TransparentViewPager;

public class FindTimeGridViewPager extends TransparentViewPager
{

    private static final int DISPATCHABLE_VIEW_IDS[] = {
        0x7f1001a3, 0x7f100146
    };

    public FindTimeGridViewPager(Context context)
    {
        super(context);
    }

    public FindTimeGridViewPager(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    protected final int[] getDispatchableViewIds()
    {
        return DISPATCHABLE_VIEW_IDS;
    }

}
