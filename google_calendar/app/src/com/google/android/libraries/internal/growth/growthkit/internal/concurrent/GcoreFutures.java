// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.concurrent;

import com.google.android.libraries.gcoreclient.common.api.GcoreApi;
import com.google.android.libraries.gcoreclient.common.api.GcoreGoogleApiClient;
import com.google.android.libraries.gcoreclient.common.api.GcorePendingResult;
import com.google.common.base.Function;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import java.io.Closeable;
import java.util.concurrent.Executor;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.concurrent:
//            AsyncCloseable

public final class GcoreFutures
{

    public static AsyncCloseable from(final GcorePendingResult pendingResult)
    {
        final SettableFuture future = new SettableFuture();
        future.addListener(new _cls6(), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
        pendingResult.setResultCallback(new _cls7());
        return AsyncCloseable.fromFutureWithCloseables(future, new Closeable[] {
            new _cls8()
        });
    }

    public static AsyncCloseable makeGcoreCall(final com.google.android.libraries.gcoreclient.common.api.GcoreGoogleApiClient.Builder client, final GcoreApi settableFuture, final Function call, Executor executor)
    {
        client.addApi(settableFuture);
        client = client.build();
        settableFuture = new SettableFuture();
        client.registerConnectionCallbacks(new _cls4());
        client.registerConnectionFailedListener(new _cls5());
        client.connect();
        settableFuture = AsyncCloseable.fromFuture(settableFuture).transformAsyncCloseable(new _cls2(), executor).transformAsyncCloseable(new _cls1(), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
        call = ((AsyncCloseable) (settableFuture)).future;
        client = new _cls3();
        executor = com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE;
        if (client == null)
        {
            throw new NullPointerException();
        } else
        {
            call.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(call, client), executor);
            return settableFuture;
        }
    }

    private class _cls6
        implements Runnable
    {

        private final SettableFuture val$future;
        private final GcorePendingResult val$pendingResult;

        public final void run()
        {
            if (future.isCancelled())
            {
                pendingResult.cancel();
            }
        }

        _cls6()
        {
            future = settablefuture;
            pendingResult = gcorependingresult;
            super();
        }
    }


    private class _cls7
        implements GcoreResultCallback
    {

        private final SettableFuture val$future;

        public final void onResult(GcoreResult gcoreresult)
        {
            GcoreStatus gcorestatus = gcoreresult.getStatus();
            if (gcorestatus.isInterrupted())
            {
                gcoreresult = String.valueOf(gcoreresult);
                throw new AssertionError((new StringBuilder(String.valueOf(gcoreresult).length() + 47)).append("We never use the blocking API for these calls: ").append(gcoreresult).toString());
            }
            boolean flag;
            if (!gcorestatus.isSuccess())
            {
                flag = false;
                future.setException(new GcoreException(gcorestatus));
            } else
            {
                flag = future.set(gcoreresult);
            }
            if (!flag && (gcoreresult instanceof GcoreReleasable))
            {
                ((GcoreReleasable)gcoreresult).release();
            }
        }

        _cls7()
        {
            future = settablefuture;
            super();
        }

        private class GcoreException extends Exception
        {

            public GcoreException(GcoreStatus gcorestatus)
            {
                super(gcorestatus.toString());
            }
        }

    }


    private class _cls8
        implements Closeable
    {

        private final SettableFuture val$future;

        public final void close()
            throws IOException
        {
            if (!future.isCancelled() && future.isDone())
            {
                try
                {
                    Object obj = future;
                    if (!((Future) (obj)).isDone())
                    {
                        throw new IllegalStateException(Strings.lenientFormat("Future was expected to be done: %s", new Object[] {
                            obj
                        }));
                    }
                    obj = Uninterruptibles.getUninterruptibly(((Future) (obj)));
                    if (obj instanceof GcoreReleasable)
                    {
                        ((GcoreReleasable)obj).release();
                        return;
                    }
                }
                catch (ExecutionException executionexception) { }
            }
        }

        _cls8()
        {
            future = settablefuture;
            super();
        }
    }


    private class _cls4
        implements com.google.android.libraries.gcoreclient.common.api.GcoreGoogleApiClient.GcoreConnectionCallbacks
    {

        private final SettableFuture val$settableFuture;

        public final void onConnected$51662RJ4E9NMIP1FDTPIUGJLDPI6OP9R55B0____0()
        {
            settableFuture.set(null);
        }

        public final void onConnectionSuspended$514IILG_0()
        {
        }

        _cls4()
        {
            settableFuture = settablefuture;
            super();
        }
    }


    private class _cls5
        implements com.google.android.libraries.gcoreclient.common.api.GcoreGoogleApiClient.GcoreOnConnectionFailedListener
    {

        private final SettableFuture val$settableFuture;

        public final void onConnectionFailed(GcoreConnectionResult gcoreconnectionresult)
        {
            settableFuture.setException(new GcoreConnectionException(gcoreconnectionresult));
        }

        _cls5()
        {
            settableFuture = settablefuture;
            super();
        }

        private class GcoreConnectionException extends Exception
        {

            GcoreConnectionException(GcoreConnectionResult gcoreconnectionresult)
            {
                super(gcoreconnectionresult.toString());
            }
        }

    }


    private class _cls2
        implements AsyncCloseableFunction
    {

        private final Function val$call;
        private final GcoreGoogleApiClient val$client;

        public final AsyncCloseable apply(Object obj)
            throws Exception
        {
            return GcoreFutures.from((GcorePendingResult)call.apply(client));
        }

        _cls2()
        {
            call = function;
            client = gcoregoogleapiclient;
            super();
        }
    }


    private class _cls1
        implements AsyncCloseableFunction
    {

        public final GcoreGoogleApiClient val$client;
        private final boolean val$keepOpen;

        private final AsyncCloseable apply(GcoreResult gcoreresult)
            throws Exception
        {
            if (keepOpen || (gcoreresult instanceof GcoreReleasable))
            {
                class _cls1
                    implements Closeable
                {

                    private final _cls1 this$0;

                    public final void close()
                        throws IOException
                    {
                        client.disconnect();
                    }

                _cls1()
                {
                    this$0 = _cls1.this;
                    super();
                }
                }

                if (gcoreresult == null)
                {
                    gcoreresult = com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
                } else
                {
                    gcoreresult = new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(gcoreresult);
                }
                return AsyncCloseable.fromFutureWithCloseables(gcoreresult, new Closeable[] {
                    new _cls1()
                });
            }
            client.disconnect();
            if (gcoreresult == null)
            {
                gcoreresult = com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
            } else
            {
                gcoreresult = new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(gcoreresult);
            }
            return AsyncCloseable.fromFuture(gcoreresult);
            Exception exception;
            exception;
            if (gcoreresult == null)
            {
                gcoreresult = com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
            } else
            {
                gcoreresult = new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(gcoreresult);
            }
            return AsyncCloseable.fromFuture(gcoreresult);
        }

        public final volatile AsyncCloseable apply(Object obj)
            throws Exception
        {
            return apply((GcoreResult)obj);
        }

        _cls1()
        {
            keepOpen = flag;
            client = gcoregoogleapiclient;
            super();
        }
    }


    private class _cls3
        implements FutureCallback
    {

        private final GcoreGoogleApiClient val$client;

        public final void onFailure(Throwable throwable)
        {
            client.disconnect();
        }

        public final volatile void onSuccess(Object obj)
        {
        }

        _cls3()
        {
            client = gcoregoogleapiclient;
            super();
        }
    }

}
