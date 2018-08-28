// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.viewpager;

import android.content.Context;

// Referenced classes of package com.google.android.calendar.utils.viewpager:
//            LayoutDirectionAwareViewPager

public class NinjaViewPager extends LayoutDirectionAwareViewPager
{

    public boolean isInStealthMode;

    public NinjaViewPager(Context context)
    {
        super(context);
        isInStealthMode = false;
    }
}
