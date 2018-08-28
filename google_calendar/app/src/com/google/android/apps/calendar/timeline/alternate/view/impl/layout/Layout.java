// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout;

import com.google.android.apps.calendar.timeline.alternate.view.api.ViewMode;
import com.google.common.base.Absent;
import com.google.common.base.Optional;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout:
//            LayoutUpdater

public abstract class Layout
{

    public static final Layout EMPTY = new _cls1();

    public Layout()
    {
    }

    public boolean canScrollHorizontally()
    {
        return false;
    }

    public boolean canScrollVertically()
    {
        return false;
    }

    public boolean fling(int i, int j)
    {
        return false;
    }

    public abstract ViewMode getViewMode();

    public void goToDay(int i)
    {
    }

    public void goToNow(boolean flag)
    {
    }

    public void goToTime(long l)
    {
    }

    public void invalidateCache()
    {
    }

    public abstract void onLayoutChildren(LayoutUpdater layoutupdater, boolean flag);

    public boolean onScale$5134CHI655D0____0(float f, float f1, float f2)
    {
        return false;
    }

    public boolean onScaleBegin()
    {
        return false;
    }

    public void onScaleEnd()
    {
    }

    public int onScrollStateChanged$51666RRD5TJMURR7DHIIUOBECHP6UQB45TGN0S3J5THM2R35DPI62SHFEHKMQPBCD5N6ABR1DHQ6ASJEC5Q6ABRMD5INEBR9DLO6OBRCC5SMUTBK5T9M6SJFDHM56T31EHIJMAACCDNMQBR7DTNMER355TGMSP3IDTKM8BR1E1O76BR3C5M6ARJ4C5P2UT39DLIMOQBECKNM2R3KCLP6SOBKCKNNCQB5ESNMIRBGDGNMOOBPDTQN8BQJCDP6UR3CADQ62T357C______0(int i)
    {
        return i;
    }

    public Optional scroll(boolean flag)
    {
        return Absent.INSTANCE;
    }

    public int scrollHorizontallyBy(int i, LayoutUpdater layoutupdater)
    {
        return 0;
    }

    public int scrollVerticallyBy(int i, LayoutUpdater layoutupdater)
    {
        return 0;
    }


    private class _cls1 extends Layout
    {

        public final ViewMode getViewMode()
        {
            return ViewMode.EMPTY;
        }

        public final void onLayoutChildren(LayoutUpdater layoutupdater, boolean flag)
        {
        }

        _cls1()
        {
        }
    }

}
