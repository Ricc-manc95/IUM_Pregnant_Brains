// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.time;


// Referenced classes of package com.google.android.calendar.api.event.time:
//            RecurRulePart

final class continuation
{

    public final RecurRulePart continuation;
    public final RecurRulePart original;

    (RecurRulePart recurrulepart, RecurRulePart recurrulepart1)
    {
        original = recurrulepart;
        continuation = recurrulepart1;
    }
}
