// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.viewpager;


// Referenced classes of package com.google.android.calendar.utils.viewpager:
//            UserAwareViewPager

public final class swipeAwareOnPageChangeListener extends android.support.v4.view.angeListenerAdapter
{

    private r swipeAwareOnPageChangeListener;
    private final UserAwareViewPager this$0;

    public final void onPageSelected(int i)
    {
        swipeAwareOnPageChangeListener.onPageSelected(i, isCurrentChangeTriggeredByUser);
    }

    public r(r r)
    {
        this$0 = UserAwareViewPager.this;
        super();
        swipeAwareOnPageChangeListener = r;
    }
}
