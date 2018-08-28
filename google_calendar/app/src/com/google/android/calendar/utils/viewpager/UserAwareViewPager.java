// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.viewpager;

import android.content.Context;
import android.util.AttributeSet;

// Referenced classes of package com.google.android.calendar.utils.viewpager:
//            LayoutDirectionAwareViewPager

public class UserAwareViewPager extends LayoutDirectionAwareViewPager
{

    public boolean isCurrentChangeTriggeredByUser;

    public UserAwareViewPager(Context context)
    {
        super(context);
        isCurrentChangeTriggeredByUser = true;
    }

    public UserAwareViewPager(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        isCurrentChangeTriggeredByUser = true;
    }

    public void setCurrentItem(int i)
    {
        isCurrentChangeTriggeredByUser = false;
        super.setCurrentItem(i);
        isCurrentChangeTriggeredByUser = true;
    }

    public final void setCurrentItem(int i, boolean flag)
    {
        isCurrentChangeTriggeredByUser = false;
        super.setCurrentItem(i, flag);
        isCurrentChangeTriggeredByUser = true;
    }
}
