// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.dnd;

import com.google.common.base.Function;

// Referenced classes of package com.google.android.calendar.timely.dnd:
//            InteractiveRescheduleManager

final class arg._cls2
    implements Function
{

    private final InteractiveRescheduleManager arg$1;
    private final dItem arg$2;

    public final Object apply(Object obj)
    {
        obj = arg$1;
        arg$2.nCancel();
        return ((InteractiveRescheduleManager) (obj)).rescheduledItem;
    }

    (InteractiveRescheduleManager interactivereschedulemanager,  )
    {
        arg$1 = interactivereschedulemanager;
        arg$2 = ;
    }
}
