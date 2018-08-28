// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.graphics.net;

import android.graphics.Bitmap;
import android.net.Uri;
import com.android.calendarcommon2.LogUtils;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.google.android.apps.calendar.util.collect.ReferenceCache;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.concurrent.FutureReferenceCache;
import com.google.android.calendar.volley.ByteArrayRequest;
import com.google.android.calendar.volley.VolleyQueueHolder;
import com.google.common.base.Absent;
import com.google.common.base.FinalizableReferenceQueue;
import com.google.common.base.Optional;
import com.google.common.base.Present;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import java.util.Collections;
import java.util.HashMap;

// Referenced classes of package com.google.android.apps.calendar.graphics.net:
//            NetworkBitmapId, BitmapDecodeOptions

public final class NetworkBitmaps
{
    static final class DecodeImageRequest extends ImageRequest
    {

        DecodeImageRequest(BitmapDecodeOptions bitmapdecodeoptions)
        {
            super("", null, bitmapdecodeoptions.maxWidthOrZero(), bitmapdecodeoptions.maxHeightOrZero(), android.widget.ImageView.ScaleType.CENTER_CROP, null, null);
        }
    }


    public static final String LOG_TAG = LogUtils.getLogTag(com/google/android/apps/calendar/graphics/net/NetworkBitmaps);
    public static final FutureReferenceCache memoryCache;

    public NetworkBitmaps()
    {
    }

    static final Optional lambda$decode$4$NetworkBitmaps(BitmapDecodeOptions bitmapdecodeoptions, byte abyte0[])
        throws Exception
    {
        bitmapdecodeoptions = (Bitmap)(new DecodeImageRequest(bitmapdecodeoptions)).parseNetworkResponse(new NetworkResponse(abyte0)).result;
        if (bitmapdecodeoptions == null)
        {
            return Absent.INSTANCE;
        } else
        {
            return new Present(bitmapdecodeoptions);
        }
    }

    static final void lambda$directDiskOrNetworkBitmap$2$NetworkBitmaps(SettableFuture settablefuture, NetworkBitmapId networkbitmapid, byte abyte0[])
    {
        networkbitmapid = networkbitmapid.decodeOptions();
        class .Lambda._cls4
            implements Callable
        {

            private final BitmapDecodeOptions arg$1;
            private final byte arg$2[];

            public final Object call()
            {
                return NetworkBitmaps.lambda$decode$4$NetworkBitmaps(arg$1, arg$2);
            }

            .Lambda._cls4(BitmapDecodeOptions bitmapdecodeoptions, byte abyte0[])
            {
                arg$1 = bitmapdecodeoptions;
                arg$2 = abyte0;
            }
        }

        settablefuture.setFuture((FluentFuture)CalendarExecutor.BACKGROUND.submit(new .Lambda._cls4(networkbitmapid, abyte0)));
    }

    static final ListenableFuture lambda$networkBitmap$0$NetworkBitmaps(NetworkBitmapId networkbitmapid)
    {
        SettableFuture settablefuture = new SettableFuture();
        class .Lambda._cls2
            implements com.android.volley.Response.Listener
        {

            private final SettableFuture arg$1;
            private final NetworkBitmapId arg$2;

            public final void onResponse(Object obj)
            {
                NetworkBitmaps.lambda$directDiskOrNetworkBitmap$2$NetworkBitmaps(arg$1, arg$2, (byte[])obj);
            }

            .Lambda._cls2(SettableFuture settablefuture, NetworkBitmapId networkbitmapid)
            {
                arg$1 = settablefuture;
                arg$2 = networkbitmapid;
            }
        }

        class .Lambda._cls3
            implements com.android.volley.Response.ErrorListener
        {

            private final SettableFuture arg$1;

            public final void onErrorResponse(VolleyError volleyerror)
            {
                SettableFuture settablefuture1 = arg$1;
                LogUtils.e(NetworkBitmaps.LOG_TAG, volleyerror, "Could not get bitmap.", new Object[0]);
                settablefuture1.set(Absent.INSTANCE);
            }

            .Lambda._cls3(SettableFuture settablefuture)
            {
                arg$1 = settablefuture;
            }
        }

        networkbitmapid = new ByteArrayRequest(networkbitmapid.uri().toString(), new .Lambda._cls2(settablefuture, networkbitmapid), new .Lambda._cls3(settablefuture));
        RequestQueue requestqueue = VolleyQueueHolder.requestQueue;
        if (requestqueue == null)
        {
            throw new NullPointerException(String.valueOf("VolleyQueueHolder#initialize(...) must be called first"));
        } else
        {
            ((RequestQueue)requestqueue).add(networkbitmapid);
            return settablefuture;
        }
    }

    static 
    {
        com.google.android.apps.calendar.util.concurrent.FutureReferenceCache.Type type = com.google.android.apps.calendar.util.concurrent.FutureReferenceCache.Type.SOFT;
        memoryCache = new FutureReferenceCache(new HashMap(), new ReferenceCache(type.referenceCacheType, Collections.synchronizedMap(new HashMap()), new FinalizableReferenceQueue()));
    }
}
