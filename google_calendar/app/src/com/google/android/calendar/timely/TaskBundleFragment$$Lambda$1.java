// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import com.google.android.apps.calendar.util.function.Consumer;

// Referenced classes of package com.google.android.calendar.timely:
//            MonthData, TaskBundleFragment

final class arg._cls1
    implements Consumer
{

    private final TaskBundleFragment arg$1;

    public final void accept(Object obj)
    {
        TaskBundleFragment taskbundlefragment = arg$1;
        obj = (MonthData)obj;
        taskbundlefragment.updateBundle(((MonthData) (obj)).getItems(taskbundlefragment.julianDay), ((MonthData) (obj)).allTasksReady);
    }

    (TaskBundleFragment taskbundlefragment)
    {
        arg$1 = taskbundlefragment;
    }
}
