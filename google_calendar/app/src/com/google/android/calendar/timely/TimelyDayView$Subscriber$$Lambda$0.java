// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import com.google.android.apps.calendar.util.function.Consumer;

// Referenced classes of package com.google.android.calendar.timely:
//            MonthData, TimelyDayView

final class arg._cls2
    implements Consumer
{

    private final arg._cls2 arg$1;
    private final int arg$2;

    public final void accept(Object obj)
    {
        arg._cls2 _lcls2 = arg$1;
        int i = arg$2;
        obj = (MonthData)obj;
        _lcls2._fld2.onUpdate(((MonthData) (obj)), i, false);
    }

    ( , int i)
    {
        arg$1 = ;
        arg$2 = i;
    }
}
