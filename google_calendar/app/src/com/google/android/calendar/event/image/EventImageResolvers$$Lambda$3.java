// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.image;

import com.android.bitmap.ReusableBitmap;
import com.google.android.calendar.timeline.chip.image.AutoValue_Image;
import com.google.common.base.Function;

// Referenced classes of package com.google.android.calendar.event.image:
//            EventImageRequestKey

final class arg._cls1
    implements Function
{

    private final EventImageRequestKey arg$1;

    public final Object apply(Object obj)
    {
        EventImageRequestKey eventimagerequestkey = arg$1;
        return new AutoValue_Image((ReusableBitmap)obj, eventimagerequestkey.rtlMirroring);
    }

    (EventImageRequestKey eventimagerequestkey)
    {
        arg$1 = eventimagerequestkey;
    }
}
