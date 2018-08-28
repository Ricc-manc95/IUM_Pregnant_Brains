// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.image.cache;

import com.android.bitmap.RequestKey;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.volley.VolleyRequests;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicReference;

public abstract class UnknownUrlVolleyRequestKey
    implements RequestKey
{
    public final class FetchFuture extends AbstractFuture
    {

        public AtomicReference pendingFuture;
        public final UnknownUrlVolleyRequestKey this$0;

        protected final void interruptTask()
        {
            ListenableFuture listenablefuture = (ListenableFuture)pendingFuture.getAndSet(null);
            if (listenablefuture != null)
            {
                listenablefuture.cancel(true);
            }
        }

        public FetchFuture()
        {
            this$0 = UnknownUrlVolleyRequestKey.this;
            super();
            pendingFuture = new AtomicReference(null);
            class .Lambda._cls0
                implements Runnable
            {

                private final FetchFuture arg$1;
                private final ListenableFuture arg$2;

                public final void run()
                {
                    FetchFuture fetchfuture;
label0:
                    {
                        fetchfuture = arg$1;
                        ListenableFuture listenablefuture = arg$2;
                        try
                        {
                            fetchfuture._fld0.mUrlString = (String)listenablefuture.get();
                        }
                        catch (Throwable throwable)
                        {
                            LogUtils.e(UnknownUrlVolleyRequestKey.TAG, throwable, "Unable to resolve URL", new Object[0]);
                        }
                        if (!fetchfuture.isCancelled())
                        {
                            if (fetchfuture._fld0.mUrlString != null)
                            {
                                break label0;
                            }
                            fetchfuture.setFuture(fetchfuture._fld0.getFallbackBytesAsync());
                        }
                        return;
                    }
                    ListenableFuture listenablefuture1 = VolleyRequests.loadBytesAsync(fetchfuture._fld0.mUrlString);
                    fetchfuture.pendingFuture.set(listenablefuture1);
                    class .Lambda._cls1
                        implements Runnable
                    {

                        private final FetchFuture arg$1;
                        private final ListenableFuture arg$2;

                        public final void run()
                        {
                            FetchFuture fetchfuture1 = arg$1;
                            ListenableFuture listenablefuture2 = arg$2;
                            try
                            {
                                fetchfuture1.set((byte[])listenablefuture2.get());
                                return;
                            }
                            catch (Throwable throwable1)
                            {
                                LogUtils.e(UnknownUrlVolleyRequestKey.TAG, throwable1, "Unable to perform Volley request", new Object[0]);
                            }
                            fetchfuture1.setFuture(fetchfuture1._fld0.getFallbackBytesAsync());
                        }

                            .Lambda._cls1(ListenableFuture listenablefuture)
                            {
                                arg$1 = FetchFuture.this;
                                arg$2 = listenablefuture;
                            }
                    }

                    listenablefuture1.addListener(fetchfuture. new .Lambda._cls1(listenablefuture1), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
                }

                .Lambda._cls0(ListenableFuture listenablefuture)
                {
                    arg$1 = FetchFuture.this;
                    arg$2 = listenablefuture;
                }
            }

            if (mUrlString == null)
            {
                unknownurlvolleyrequestkey = getUrlAsync();
                pendingFuture.set(UnknownUrlVolleyRequestKey.this);
            } else
            {
                unknownurlvolleyrequestkey = mUrlString;
                if (UnknownUrlVolleyRequestKey.this == null)
                {
                    unknownurlvolleyrequestkey = com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
                } else
                {
                    unknownurlvolleyrequestkey = new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(UnknownUrlVolleyRequestKey.this);
                }
            }
            addListener(new .Lambda._cls0(UnknownUrlVolleyRequestKey.this), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
        }
    }


    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/event/image/cache/UnknownUrlVolleyRequestKey);
    public String mUrlString;

    public UnknownUrlVolleyRequestKey()
    {
    }

    public final InputStream createInputStream()
        throws IOException
    {
        return null;
    }

    public ListenableFuture getFallbackBytesAsync()
    {
        if (true)
        {
            return com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
        } else
        {
            return new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(null);
        }
    }

    public abstract ListenableFuture getUrlAsync();

}
