// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.image;

import android.content.Context;
import android.text.TextUtils;
import com.android.volley.Cache;
import com.android.volley.RequestQueue;
import com.google.android.apps.calendar.flair.FlairAllocatorFactory;
import com.google.android.apps.calendar.graphics.RtlMirroring;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.utils.network.NetworkUtil;
import com.google.android.calendar.volley.VolleyQueueHolder;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.calendar.event.image:
//            AutoValue_EventImage

public abstract class EventImage
{

    public static final EventImage DEFAULT_INSTANCE;

    public EventImage()
    {
    }

    static final EventImage lambda$newUrlInstanceAsync$0$EventImage(Context context, String s)
        throws Exception
    {
        Object obj = VolleyQueueHolder.requestQueue;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("VolleyQueueHolder#initialize(...) must be called first"));
        }
        obj = ((RequestQueue)obj).mCache;
        boolean flag;
        if (context != null && NetworkUtil.isConnectedToInternet(context))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        s = newInstanceIfAvailable(((Cache) (obj)), flag, s, RtlMirroring.mirrorInRtlIf(FlairAllocatorFactory.isFlairUrl(s)));
        context = s;
        if (s == null)
        {
            context = DEFAULT_INSTANCE;
        }
        return context;
    }

    public static EventImage newInstance(String s, int i, RtlMirroring rtlmirroring)
    {
        return new AutoValue_EventImage(s, i, rtlmirroring);
    }

    public static EventImage newInstanceIfAvailable(Cache cache, boolean flag, String s, RtlMirroring rtlmirroring)
    {
        if (!TextUtils.isEmpty(s))
        {
            if (flag)
            {
                return new AutoValue_EventImage(s, 0, rtlmirroring);
            }
            cache = cache.get(s);
            if (cache != null)
            {
                boolean flag1;
                if (((com.android.volley.Cache.Entry) (cache)).ttl < System.currentTimeMillis())
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                if (!flag1)
                {
                    return new AutoValue_EventImage(s, 0, rtlmirroring);
                }
            }
        }
        return null;
    }

    public static ListenableFuture newUrlInstanceAsync(Context context, String s)
    {
        class .Lambda._cls0
            implements Callable
        {

            private final Context arg$1;
            private final String arg$2;

            public final Object call()
            {
                return EventImage.lambda$newUrlInstanceAsync$0$EventImage(arg$1, arg$2);
            }

            .Lambda._cls0(Context context, String s)
            {
                arg$1 = context;
                arg$2 = s;
            }
        }

        return (FluentFuture)CalendarExecutor.DISK.submit(new .Lambda._cls0(context, s));
    }

    public abstract int flair();

    public abstract RtlMirroring rtlMirroring();

    public abstract String url();

    static 
    {
        DEFAULT_INSTANCE = new AutoValue_EventImage(null, 0, RtlMirroring.DO_NOT_MIRROR);
    }
}
