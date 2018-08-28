// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.image.cache.contactphoto;

import com.android.bitmap.BitmapCache;
import com.google.android.calendar.event.image.cache.WeakBitmapCache;

public final class ContactPhotoCacheHolder
{

    private static BitmapCache contactPhotoCache;

    public static BitmapCache getContactPhotoCache()
    {
        com/google/android/calendar/event/image/cache/contactphoto/ContactPhotoCacheHolder;
        JVM INSTR monitorenter ;
        BitmapCache bitmapcache;
        if (contactPhotoCache == null)
        {
            contactPhotoCache = new WeakBitmapCache(true);
        }
        bitmapcache = contactPhotoCache;
        com/google/android/calendar/event/image/cache/contactphoto/ContactPhotoCacheHolder;
        JVM INSTR monitorexit ;
        return bitmapcache;
        Exception exception;
        exception;
        throw exception;
    }
}
