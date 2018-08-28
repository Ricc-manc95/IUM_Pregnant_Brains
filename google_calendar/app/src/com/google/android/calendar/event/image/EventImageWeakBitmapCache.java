// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.image;

import com.android.bitmap.RequestKey;
import com.android.bitmap.ReusableBitmap;
import com.google.android.calendar.event.image.cache.WeakBitmapCache;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.google.android.calendar.event.image:
//            EventImageCache, EventImageRequestKey

final class EventImageWeakBitmapCache extends WeakBitmapCache
    implements EventImageCache
{

    private final Map eventImageRequestKeyMap = new HashMap();

    EventImageWeakBitmapCache(boolean flag)
    {
        super(false);
    }

    public final EventImageRequestKey getKey(RequestKey requestkey)
    {
        return (EventImageRequestKey)eventImageRequestKeyMap.get(requestkey);
    }

    public final ReusableBitmap put(RequestKey requestkey, ReusableBitmap reusablebitmap)
    {
        eventImageRequestKeyMap.put(requestkey, (EventImageRequestKey)requestkey);
        return super.put(requestkey, reusablebitmap);
    }

    public final volatile Object put(Object obj, Object obj1)
    {
        return put((RequestKey)obj, (ReusableBitmap)obj1);
    }
}
