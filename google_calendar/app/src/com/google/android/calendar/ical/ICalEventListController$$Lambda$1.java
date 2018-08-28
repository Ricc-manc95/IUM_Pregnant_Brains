// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.ical;

import com.google.common.base.Predicate;

// Referenced classes of package com.google.android.calendar.ical:
//            ICalEventOperation, ICalEventListController

final class 
    implements Predicate
{

    public static final Predicate $instance = new <init>();

    public final boolean apply(Object obj)
    {
        return ICalEventListController.lambda$getNewEvents$0$ICalEventListController((ICalEventOperation)obj);
    }


    private ()
    {
    }
}
