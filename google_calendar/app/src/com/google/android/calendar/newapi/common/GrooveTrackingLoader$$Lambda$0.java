// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.common;

import com.google.android.calendar.Utils;
import com.google.common.base.Supplier;

// Referenced classes of package com.google.android.calendar.newapi.common:
//            GrooveTrackingLoader

final class arg._cls1
    implements Supplier
{

    private final GrooveTrackingLoader arg$1;

    public final Object get()
    {
        return Utils.getTimeZone(arg$1.context);
    }

    (GrooveTrackingLoader groovetrackingloader)
    {
        arg$1 = groovetrackingloader;
    }
}
