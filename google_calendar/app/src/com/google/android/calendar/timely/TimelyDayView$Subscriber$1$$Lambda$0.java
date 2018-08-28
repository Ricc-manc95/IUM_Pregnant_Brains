// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import com.google.android.apps.calendar.util.function.Consumer;

// Referenced classes of package com.google.android.calendar.timely:
//            MonthData, TimelyDayView

final class arg._cls1
    implements Consumer
{

    private final arg._cls1 arg$1;

    public final void accept(Object obj)
    {
        arg._cls1 _lcls1 = arg$1;
        obj = (MonthData)obj;
        _lcls1._fld1._fld1.onUpdate(((MonthData) (obj)), _lcls1._fld1._fld1, false);
    }

    ( )
    {
        arg$1 = ;
    }
}
