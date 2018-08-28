// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.image;

import com.android.bitmap.BitmapCache;
import com.android.bitmap.RequestKey;

// Referenced classes of package com.google.android.calendar.event.image:
//            EventImageRequestKey

public interface EventImageCache
    extends BitmapCache
{

    public abstract EventImageRequestKey getKey(RequestKey requestkey);
}
