// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.image;

import com.google.common.base.Function;

// Referenced classes of package com.google.android.calendar.event.image:
//            EventImage, EventImageRequestKey

final class arg._cls1
    implements Function
{

    private final EventImageRequestKey arg$1;

    public final Object apply(Object obj)
    {
        EventImageRequestKey eventimagerequestkey = arg$1;
        obj = (EventImage)obj;
        eventimagerequestkey.rtlMirroring = ((EventImage) (obj)).rtlMirroring();
        return ((EventImage) (obj)).url();
    }

    (EventImageRequestKey eventimagerequestkey)
    {
        arg$1 = eventimagerequestkey;
    }
}
