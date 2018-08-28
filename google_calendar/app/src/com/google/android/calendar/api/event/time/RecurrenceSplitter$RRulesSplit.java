// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.time;

import java.util.List;

// Referenced classes of package com.google.android.calendar.api.event.time:
//            RecurRulePart

final class continuation
{

    public final RecurRulePart continuation[];
    public final RecurRulePart original[];

    (List list, List list1)
    {
        if (!list.isEmpty())
        {
            list = (RecurRulePart[])list.toArray(new RecurRulePart[0]);
        } else
        {
            list = null;
        }
        original = list;
        if (!list1.isEmpty())
        {
            list = (RecurRulePart[])list1.toArray(new RecurRulePart[0]);
        } else
        {
            list = null;
        }
        continuation = list;
    }
}
