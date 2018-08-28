// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.pseudonymous.impl;

import com.google.android.libraries.gcoreclient.pseudonymous.GcorePseudonymousId;
import com.google.android.libraries.gcoreclient.pseudonymous.GcorePseudonymousIdApi;
import com.google.android.libraries.internal.growth.growthkit.internal.concurrent.AsyncCloseable;
import com.google.android.libraries.internal.growth.growthkit.internal.concurrent.GcoreFutures;
import com.google.android.libraries.internal.growth.growthkit.internal.pseudonymous.PseudonymousIdHelper;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;

public final class PseudonymousIdHelperImpl
    implements PseudonymousIdHelper
{

    private final ListeningExecutorService executor;
    private final com.google.android.libraries.gcoreclient.common.api.GcoreGoogleApiClient.Builder googleApiClientBuilder;
    public final GcorePseudonymousId pseudonymousId;
    private final GcorePseudonymousIdApi pseudonymousIdApi;

    public PseudonymousIdHelperImpl(ListeningExecutorService listeningexecutorservice, GcorePseudonymousId gcorepseudonymousid, GcorePseudonymousIdApi gcorepseudonymousidapi, com.google.android.libraries.gcoreclient.common.api.GcoreGoogleApiClient.Builder builder)
    {
        executor = listeningexecutorservice;
        pseudonymousId = gcorepseudonymousid;
        pseudonymousIdApi = gcorepseudonymousidapi;
        googleApiClientBuilder = builder;
    }

    public final ListenableFuture getPseudonymousIdToken()
    {
        class .Lambda._cls0
            implements Function
        {

            public static final Function $instance = new .Lambda._cls0();

            public final Object apply(Object obj)
            {
                return ((GcorePseudonymousIdTokenResult)obj).getPseudonymousIdToken();
            }


            private .Lambda._cls0()
            {
            }
        }

        return GcoreFutures.makeGcoreCall(googleApiClientBuilder, pseudonymousIdApi, new _cls1(), executor).transformAndClose(.Lambda._cls0..instance, com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
    }

    private class _cls1
        implements Function
    {

        private final PseudonymousIdHelperImpl this$0;

        public final Object apply(Object obj)
        {
            obj = (GcoreGoogleApiClient)obj;
            return pseudonymousId.getToken(((GcoreGoogleApiClient) (obj)));
        }

        _cls1()
        {
            this$0 = PseudonymousIdHelperImpl.this;
            super();
        }
    }

}
