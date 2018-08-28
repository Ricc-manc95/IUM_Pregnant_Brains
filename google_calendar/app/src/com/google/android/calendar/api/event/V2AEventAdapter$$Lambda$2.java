// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import com.google.common.base.Function;
import com.google.protos.calendar.feapi.v1.EventLocation;

// Referenced classes of package com.google.android.calendar.api.event:
//            V2AEventAdapter

final class 
    implements Function
{

    public static final Function $instance = new <init>();

    public final Object apply(Object obj)
    {
        return V2AEventAdapter.bridge$lambda$1$V2AEventAdapter((EventLocation)obj);
    }


    private ()
    {
    }
}
