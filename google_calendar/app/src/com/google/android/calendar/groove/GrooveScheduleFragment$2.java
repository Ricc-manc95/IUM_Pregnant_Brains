// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.support.v4.view.PagerAdapter;
import com.android.bitmap.drawable.ExtendedBitmapDrawable;
import java.util.ArrayList;

// Referenced classes of package com.google.android.calendar.groove:
//            GrooveScheduleFragment

final class this._cls0
    implements android.support.v4.view.tener
{

    private final GrooveScheduleFragment this$0;

    public final void onPageScrollStateChanged(int i)
    {
        if (i == 0)
        {
            pagerAdapter.notifyDataSetChanged();
        }
    }

    public final void onPageScrolled(int i, float f, int j)
    {
label0:
        {
            if (backgroundDrawable != null)
            {
                if (!isRtl)
                {
                    break label0;
                }
                com.google.android.calendar.common.drawable.ListenableBitmapDrawable listenablebitmapdrawable = backgroundDrawable;
                listenablebitmapdrawable.parallaxFraction = (4F - (float)i - f) * 0.15F;
                listenablebitmapdrawable.invalidateSelf();
            }
            return;
        }
        com.google.android.calendar.common.drawable.ListenableBitmapDrawable listenablebitmapdrawable1 = backgroundDrawable;
        listenablebitmapdrawable1.parallaxFraction = (2.5F + (float)i + f) * 0.15F;
        listenablebitmapdrawable1.invalidateSelf();
    }

    public final void onPageSelected(int i)
    {
        GrooveScheduleFragment grooveschedulefragment = GrooveScheduleFragment.this;
        GrooveScheduleFragment grooveschedulefragment1 = GrooveScheduleFragment.this;
        if (i >= grooveschedulefragment1.screenList.size())
        {
            i = -1;
        } else
        {
            i = ((Integer)grooveschedulefragment1.screenList.get(i)).intValue();
        }
        grooveschedulefragment.requestInitialScreenFocus(i);
    }

    A()
    {
        this$0 = GrooveScheduleFragment.this;
        super();
    }
}
