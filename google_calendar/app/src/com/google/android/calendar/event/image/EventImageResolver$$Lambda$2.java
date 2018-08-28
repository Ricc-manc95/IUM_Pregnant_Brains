// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.image;

import com.android.volley.Cache;
import com.google.android.apps.calendar.flair.FlairAllocatorFactory;
import com.google.android.apps.calendar.graphics.RtlMirroring;
import com.google.common.base.Function;

// Referenced classes of package com.google.android.calendar.event.image:
//            PlacePageOrMapUrl, EventImage

final class arg._cls3
    implements Function
{

    private final Cache arg$1;
    private final boolean arg$2;
    private final String arg$3;

    public final Object apply(Object obj)
    {
        Object obj1;
        Cache cache;
        PlacePageOrMapUrl placepageormapurl;
        boolean flag;
        cache = arg$1;
        flag = arg$2;
        obj1 = arg$3;
        placepageormapurl = (PlacePageOrMapUrl)obj;
        if (placepageormapurl == null) goto _L2; else goto _L1
_L1:
        obj = EventImage.newInstanceIfAvailable(cache, flag, placepageormapurl.placePageImageUrl, RtlMirroring.DO_NOT_MIRROR);
        if (obj == null) goto _L2; else goto _L3
_L3:
        obj1 = obj;
        if (obj == null)
        {
            obj1 = EventImage.DEFAULT_INSTANCE;
        }
        return obj1;
_L2:
        obj1 = EventImage.newInstanceIfAvailable(cache, flag, FlairAllocatorFactory.getFlairUrlString(((String) (obj1))), RtlMirroring.MIRROR_IN_RTL);
        obj = obj1;
        if (obj1 == null)
        {
            if (placepageormapurl == null)
            {
                obj = null;
            } else
            {
                obj = EventImage.newInstanceIfAvailable(cache, flag, placepageormapurl.staticMapUrl, RtlMirroring.DO_NOT_MIRROR);
            }
        }
        if (true) goto _L3; else goto _L4
_L4:
    }

    (Cache cache, boolean flag, String s)
    {
        arg$1 = cache;
        arg$2 = flag;
        arg$3 = s;
    }
}
