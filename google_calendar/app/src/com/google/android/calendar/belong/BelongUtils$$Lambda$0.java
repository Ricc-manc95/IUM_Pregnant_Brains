// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.belong;

import com.google.common.base.Supplier;

// Referenced classes of package com.google.android.calendar.belong:
//            BelongUtils

final class 
    implements Supplier
{

    public static final Supplier $instance = new <init>();

    public final Object get()
    {
        return BelongUtils.hasHabitsWithFitIntegrationAsync();
    }


    private ()
    {
    }
}
