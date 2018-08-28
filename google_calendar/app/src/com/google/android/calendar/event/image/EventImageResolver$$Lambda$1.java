// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.image;

import android.content.Context;
import com.android.volley.RequestQueue;
import com.google.android.apps.calendar.graphics.RtlMirroring;
import com.google.android.apps.calendar.timebox.EventImageDetails;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.api.event.smartmail.SmartMailAddress;
import com.google.android.calendar.utils.network.NetworkUtil;
import com.google.android.calendar.utils.timely.TimelyUtils;
import com.google.android.calendar.volley.VolleyQueueHolder;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.calendar.event.image:
//            EventImageResolver, EventImage, PlacePageOrMapUrl

final class arg._cls4
    implements AsyncFunction
{

    private final EventImageResolver arg$1;
    private final Context arg$2;
    private final int arg$3;
    private final int arg$4;

    public final ListenableFuture apply(Object obj)
    {
        EventImageDetails eventimagedetails;
        Object obj1;
        com.android.volley.Cache cache;
        boolean flag;
        int j;
        int k;
        boolean flag1;
        flag = false;
        obj1 = arg$1;
        Context context = arg$2;
        j = arg$3;
        k = arg$4;
        eventimagedetails = (EventImageDetails)obj;
        obj1 = ((EventImageResolver) (obj1)).title();
        if (eventimagedetails == null)
        {
            obj = EventImage.DEFAULT_INSTANCE;
            if (obj == null)
            {
                return com.google.common.util.concurrent.essfulFuture.NULL;
            } else
            {
                return new com.google.common.util.concurrent.essfulFuture(obj);
            }
        }
        obj = VolleyQueueHolder.requestQueue;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("VolleyQueueHolder#initialize(...) must be called first"));
        }
        cache = ((RequestQueue)obj).mCache;
        if (context != null && NetworkUtil.isConnectedToInternet(context))
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        obj = EventImage.newInstanceIfAvailable(cache, flag1, eventimagedetails.getGooglePlusImageUrl(), RtlMirroring.DO_NOT_MIRROR);
        if (obj == null) goto _L2; else goto _L1
_L1:
        int i;
        if (obj != null)
        {
            if (obj == null)
            {
                return com.google.common.util.concurrent.essfulFuture.NULL;
            } else
            {
                return new com.google.common.util.concurrent.essfulFuture(obj);
            }
        } else
        {
            return AbstractTransformFuture.create(PlacePageOrMapUrl.getPlacePageOrMapsUrl(eventimagedetails.getEventLocation(), j, k), new <init>(cache, flag1, ((String) (obj1))), CalendarExecutor.DISK);
        }
_L2:
        obj = eventimagedetails.getSmartMailImageUrl();
        i = ((flag) ? 1 : 0);
        if (eventimagedetails.getType() == null) goto _L4; else goto _L3
_L3:
        eventimagedetails.getType().ordinal();
        JVM INSTR tableswitch 0 3: default 196
    //                   0 248
    //                   1 227
    //                   2 234
    //                   3 241;
           goto _L5 _L6 _L7 _L8 _L9
_L6:
        break MISSING_BLOCK_LABEL_248;
_L5:
        i = ((flag) ? 1 : 0);
_L4:
        if (EventImage.newInstanceIfAvailable(cache, flag1, ((String) (obj)), RtlMirroring.DO_NOT_MIRROR) != null)
        {
            obj = EventImage.newInstance(((String) (obj)), i, RtlMirroring.DO_NOT_MIRROR);
        } else
        if (i != 0)
        {
            obj = EventImage.newInstance(null, i, RtlMirroring.MIRROR_IN_RTL);
        } else
        {
            obj = eventimagedetails.getSmartMailAddress();
            if (obj == null || ((SmartMailAddress) (obj)).latitude == null || ((SmartMailAddress) (obj)).longitude == null)
            {
                obj = null;
            } else
            {
                obj = TimelyUtils.getStaticMapsUrl(((SmartMailAddress) (obj)).latitude, ((SmartMailAddress) (obj)).longitude, ((SmartMailAddress) (obj)).longitude, j, k);
            }
            obj = EventImage.newInstanceIfAvailable(cache, flag1, ((String) (obj)), RtlMirroring.DO_NOT_MIRROR);
        }
          goto _L1
_L7:
        i = 0x7f02025f;
          goto _L4
_L8:
        i = 0x7f020260;
          goto _L4
_L9:
        i = 0x7f020261;
          goto _L4
        i = 0x7f020262;
          goto _L4
    }

    ype(EventImageResolver eventimageresolver, Context context, int i, int j)
    {
        arg$1 = eventimageresolver;
        arg$2 = context;
        arg$3 = i;
        arg$4 = j;
    }
}
