// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import java.util.Comparator;

// Referenced classes of package com.google.android.calendar.api.event:
//            CpEventKey

final class 
    implements Comparator
{

    public static final Comparator $instance = new <init>();

    public final int compare(Object obj, Object obj1)
    {
        return CpEventKey.lambda$static$0$CpEventKey((CpEventKey)obj, (CpEventKey)obj1);
    }


    private ()
    {
    }
}
