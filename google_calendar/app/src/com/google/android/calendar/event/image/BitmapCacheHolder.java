// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.image;

import com.android.bitmap.BitmapCache;
import com.android.bitmap.UnrefedBitmapCache;
import com.google.android.calendar.event.image.cache.WeakBitmapCache;

// Referenced classes of package com.google.android.calendar.event.image:
//            EventImageWeakBitmapCache, EventImageCache

public final class BitmapCacheHolder
{

    private static BitmapCache attachmentChipCache;
    private static BitmapCache attachmentIconCache;
    private static BitmapCache backgroundImageCache;
    private static EventImageCache eventImageCacheSingleton;
    private static BitmapCache monthHeaderBitmapCache;

    public static BitmapCache getAttachmentChipCache()
    {
        com/google/android/calendar/event/image/BitmapCacheHolder;
        JVM INSTR monitorenter ;
        BitmapCache bitmapcache;
        if (attachmentChipCache == null)
        {
            attachmentChipCache = new UnrefedBitmapCache(0, 0.0F, 0);
        }
        bitmapcache = attachmentChipCache;
        com/google/android/calendar/event/image/BitmapCacheHolder;
        JVM INSTR monitorexit ;
        return bitmapcache;
        Exception exception;
        exception;
        throw exception;
    }

    public static BitmapCache getAttachmentIconCache()
    {
        com/google/android/calendar/event/image/BitmapCacheHolder;
        JVM INSTR monitorenter ;
        BitmapCache bitmapcache;
        if (attachmentIconCache == null)
        {
            attachmentIconCache = new UnrefedBitmapCache(0, 0.0F, 0);
        }
        bitmapcache = attachmentIconCache;
        com/google/android/calendar/event/image/BitmapCacheHolder;
        JVM INSTR monitorexit ;
        return bitmapcache;
        Exception exception;
        exception;
        throw exception;
    }

    public static BitmapCache getBackgroundImageCache()
    {
        com/google/android/calendar/event/image/BitmapCacheHolder;
        JVM INSTR monitorenter ;
        BitmapCache bitmapcache;
        if (backgroundImageCache == null)
        {
            backgroundImageCache = new WeakBitmapCache(false);
        }
        bitmapcache = backgroundImageCache;
        com/google/android/calendar/event/image/BitmapCacheHolder;
        JVM INSTR monitorexit ;
        return bitmapcache;
        Exception exception;
        exception;
        throw exception;
    }

    public static EventImageCache getEventImageCache()
    {
        com/google/android/calendar/event/image/BitmapCacheHolder;
        JVM INSTR monitorenter ;
        EventImageCache eventimagecache;
        if (eventImageCacheSingleton == null)
        {
            eventImageCacheSingleton = new EventImageWeakBitmapCache(false);
        }
        eventimagecache = eventImageCacheSingleton;
        com/google/android/calendar/event/image/BitmapCacheHolder;
        JVM INSTR monitorexit ;
        return eventimagecache;
        Exception exception;
        exception;
        throw exception;
    }

    public static BitmapCache getMonthHeaderBitmapCache()
    {
        com/google/android/calendar/event/image/BitmapCacheHolder;
        JVM INSTR monitorenter ;
        BitmapCache bitmapcache;
        if (monthHeaderBitmapCache == null)
        {
            monthHeaderBitmapCache = new WeakBitmapCache(false);
        }
        bitmapcache = monthHeaderBitmapCache;
        com/google/android/calendar/event/image/BitmapCacheHolder;
        JVM INSTR monitorexit ;
        return bitmapcache;
        Exception exception;
        exception;
        throw exception;
    }
}
