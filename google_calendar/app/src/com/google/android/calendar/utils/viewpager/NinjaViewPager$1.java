// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.viewpager;


// Referenced classes of package com.google.android.calendar.utils.viewpager:
//            NinjaViewPager

public final class val.listener
    implements android.support.v4.view.hangeListener
{

    private final NinjaViewPager this$0;
    private final android.support.v4.view.hangeListener val$listener;

    public final void onPageScrollStateChanged(int i)
    {
        val$listener.onPageScrollStateChanged(i);
    }

    public final void onPageScrolled(int i, float f, int j)
    {
        val$listener.onPageScrolled(i, f, j);
    }

    public final void onPageSelected(int i)
    {
        if (!isInStealthMode)
        {
            val$listener.onPageSelected(i);
        }
    }

    public ()
    {
        this$0 = final_ninjaviewpager;
        val$listener = android.support.v4.view.hangeListener.this;
        super();
    }
}
