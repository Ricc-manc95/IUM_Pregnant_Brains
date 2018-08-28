// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;


// Referenced classes of package com.google.android.calendar.timely:
//            TaskBundleFragment, TimelyDayView

final class arg._cls1
    implements android.view.gedListener
{

    private final TaskBundleFragment arg$1;

    public final void onScrollChanged()
    {
        arg._cls1 _lcls1 = arg$1.dayView;
        _lcls1.maybeUpdateMonthHeaderParallax();
        _lcls1.updateDayHeaderViewPosition();
    }

    (TaskBundleFragment taskbundlefragment)
    {
        arg$1 = taskbundlefragment;
    }
}
