// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import com.google.android.apps.calendar.timeline.alternate.util.YearMonth;
import com.google.android.apps.calendar.util.function.Consumer;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            MonthAdapter, AdapterWeek

final class arg._cls2
    implements Consumer
{

    private final MonthAdapter arg$1;
    private final AdapterWeek arg$2;

    public final void accept(Object obj)
    {
        MonthAdapter monthadapter = arg$1;
        AdapterWeek adapterweek = arg$2;
        monthadapter.update((YearMonth)obj, adapterweek);
    }

    (MonthAdapter monthadapter, AdapterWeek adapterweek)
    {
        arg$1 = monthadapter;
        arg$2 = adapterweek;
    }
}
