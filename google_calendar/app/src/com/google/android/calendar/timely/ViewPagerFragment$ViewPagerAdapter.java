// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.support.v4.view.PagerAdapter;
import android.view.View;

// Referenced classes of package com.google.android.calendar.timely:
//            ViewPagerFragment, DataFactory

public static abstract class  extends PagerAdapter
{

    public DataFactory mDataFactory;

    public boolean isViewFromObject(View view, Object obj)
    {
        return view == obj;
    }

    protected void setDataFactory(DataFactory datafactory)
    {
        mDataFactory = datafactory;
    }

    public void updateVisibleViews()
    {
    }

    public ()
    {
    }
}
