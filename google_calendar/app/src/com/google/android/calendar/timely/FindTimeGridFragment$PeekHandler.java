// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import com.google.android.calendar.utils.rtl.RtlUtils;
import com.google.android.calendar.utils.viewpager.TransparentViewPager;
import java.lang.ref.WeakReference;

// Referenced classes of package com.google.android.calendar.timely:
//            FindTimeGridViewPager, FindTimeGridFragment

final class weakPager extends Handler
{

    private final WeakReference weakContext;
    private final WeakReference weakPager;

    public final void handleMessage(Message message)
    {
        byte byte0 = -1;
        message = (FindTimeGridViewPager)weakPager.get();
        Context context = (Context)weakContext.get();
        if (message != null && context != null)
        {
            if (((TransparentViewPager) (message)).dispatchOnPager)
            {
                sendEmptyMessageDelayed(0, 1000L);
                return;
            }
            message = new per(context, message);
            if (!FindTimeGridFragment.wasSlabSwiped(((per) (message)).context))
            {
                int i = ((per) (message)).pager.getAdapter().getCount();
                if (i >= 2)
                {
                    float f;
                    ValueAnimator valueanimator;
                    if (((per) (message)).pager.getCurrentItem() == i - 1)
                    {
                        i = 1;
                    } else
                    {
                        i = -1;
                    }
                    if (!RtlUtils.isLayoutDirectionRtl(((per) (message)).context))
                    {
                        byte0 = 1;
                    }
                    f = ((per) (message)).context.getResources().getDimension(0x7f0e0182);
                    valueanimator = ValueAnimator.ofFloat(new float[] {
                        0.0F, (float)(i * byte0) * f
                    });
                    valueanimator.setDuration(500L);
                    valueanimator.setInterpolator(new FastOutSlowInInterpolator());
                    valueanimator.addUpdateListener(new per._cls1(message));
                    valueanimator.addListener(new per._cls2(message));
                    valueanimator.start();
                    return;
                }
            }
        }
    }

    public per._cls2(Context context, FindTimeGridViewPager findtimegridviewpager)
    {
        weakContext = new WeakReference(context);
        weakPager = new WeakReference(findtimegridviewpager);
    }
}
