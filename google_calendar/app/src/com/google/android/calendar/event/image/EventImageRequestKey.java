// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.image;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.apps.calendar.flair.FlairAllocatorFactory;
import com.google.android.apps.calendar.graphics.RtlMirroring;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.event.image.cache.UnknownUrlVolleyRequestKey;
import com.google.android.gms.common.util.IOUtils;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

// Referenced classes of package com.google.android.calendar.event.image:
//            AutoValue_EventImageRequestKey, BitmapCallbacks, EventImage

public abstract class EventImageRequestKey extends UnknownUrlVolleyRequestKey
{

    private Context context;
    public EventImage eventImage;
    public RtlMirroring rtlMirroring;

    public EventImageRequestKey()
    {
        rtlMirroring = RtlMirroring.DO_NOT_MIRROR;
        eventImage = null;
    }

    public static EventImageRequestKey newInstance(Context context1, EventImage.Resolver resolver, int i, int j)
    {
        resolver = new AutoValue_EventImageRequestKey(resolver, i, j);
        resolver.context = context1;
        return resolver;
    }

    public final com.android.bitmap.RequestKey.Cancelable createFileDescriptorFactoryOrByteArrayAsync$51666RRD5TGMSP3IDTKM8BR2D5Q6QOBG5T96ASBLCLPN8IR5F4TKOORFDKNM2RJ4E9NMIP1FC9KN8RB1E0NL4PBHELIN6T2BCLSI8GR1DHM64OB3DCTIIJ33DTMIUOBECHP6UQB45TH6IT3DC5O2UKJ5E5QMASRK9DINI923C5N66PBCC5H6OP9R0(com.android.bitmap.RequestKey.Callback callback)
    {
        if (FlairAllocatorFactory.isFlairUrl(mUrlString))
        {
            rtlMirroring = RtlMirroring.MIRROR_IN_RTL;
        }
        return BitmapCallbacks.listen(this, callback, new com.google.android.calendar.event.image.cache.UnknownUrlVolleyRequestKey.FetchFuture(this));
    }

    protected final ListenableFuture getFallbackBytesAsync()
    {
        if (eventImage != null)
        {
            byte abyte0[] = loadIfFlair(eventImage);
            if (abyte0 == null)
            {
                return com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
            } else
            {
                return new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(abyte0);
            }
        }
        class .Lambda._cls1
            implements Function
        {

            private final EventImageRequestKey arg$1;

            public final Object apply(Object obj)
            {
                EventImageRequestKey eventimagerequestkey = arg$1;
                obj = (EventImage)obj;
                eventimagerequestkey.eventImage = ((EventImage) (obj));
                return eventimagerequestkey.loadIfFlair(((EventImage) (obj)));
            }

            .Lambda._cls1()
            {
                arg$1 = EventImageRequestKey.this;
            }
        }

        ListenableFuture listenablefuture;
        try
        {
            listenablefuture = getResolver().resolveAsync(context, getWidth(), getHeight());
        }
        catch (Exception exception)
        {
            rtlMirroring = RtlMirroring.DO_NOT_MIRROR;
            if (exception == null)
            {
                throw new NullPointerException();
            } else
            {
                return new com.google.common.util.concurrent.ImmediateFuture.ImmediateFailedFuture(exception);
            }
        }
        return AbstractTransformFuture.create(listenablefuture, new .Lambda._cls1(), CalendarExecutor.DISK);
    }

    public abstract int getHeight();

    abstract EventImage.Resolver getResolver();

    protected final ListenableFuture getUrlAsync()
    {
        class .Lambda._cls0
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

            .Lambda._cls0()
            {
                arg$1 = EventImageRequestKey.this;
            }
        }

        ListenableFuture listenablefuture;
        try
        {
            listenablefuture = getResolver().resolveAsync(context, getWidth(), getHeight());
        }
        catch (Exception exception)
        {
            rtlMirroring = RtlMirroring.DO_NOT_MIRROR;
            if (exception == null)
            {
                throw new NullPointerException();
            } else
            {
                return new com.google.common.util.concurrent.ImmediateFuture.ImmediateFailedFuture(exception);
            }
        }
        return AbstractTransformFuture.create(listenablefuture, new .Lambda._cls0(), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
    }

    public abstract int getWidth();

    public final boolean hasOrientationExif()
    {
        return false;
    }

    final byte[] loadIfFlair(EventImage eventimage)
    {
        boolean flag = true;
        if (eventimage.flair() == 0)
        {
            flag = false;
        }
        if (flag)
        {
            rtlMirroring = eventimage.rtlMirroring();
            try
            {
                eventimage = context.getResources().openRawResource(eventImage.flair());
                ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
                IOUtils.copyStream(eventimage, bytearrayoutputstream, true, 1024);
                eventimage = bytearrayoutputstream.toByteArray();
            }
            // Misplaced declaration of an exception variable
            catch (EventImage eventimage)
            {
                throw new RuntimeException(eventimage);
            }
            return eventimage;
        } else
        {
            rtlMirroring = RtlMirroring.DO_NOT_MIRROR;
            return null;
        }
    }
}
