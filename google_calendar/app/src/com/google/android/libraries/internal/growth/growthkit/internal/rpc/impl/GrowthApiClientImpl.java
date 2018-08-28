// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.rpc.impl;

import com.google.android.libraries.internal.growth.growthkit.internal.accounts.AccountManager;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Logger;
import com.google.android.libraries.internal.growth.growthkit.internal.concurrent.AsyncCloseable;
import com.google.android.libraries.internal.growth.growthkit.internal.rpc.GrowthApiClient;
import com.google.android.libraries.internal.growth.growthkit.internal.streamz.StreamzIncrements;
import com.google.android.libraries.streamz.Counter2;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.internal.identity.growth.v1.GrowthApiServiceGrpc;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientInterceptor;
import io.grpc.ClientInterceptors;
import io.grpc.Deadline;
import io.grpc.ManagedChannel;
import io.grpc.Metadata;
import io.grpc.stub.AbstractStub;
import io.grpc.stub.ClientCalls;
import java.io.Closeable;
import java.util.concurrent.TimeUnit;
import javax.inject.Provider;

public final class GrowthApiClientImpl
    implements GrowthApiClient
{

    private static final io.grpc.Metadata.Key ANDROID_CERTIFICATE_HEADER_KEY;
    private static final io.grpc.Metadata.Key ANDROID_PACKAGE_HEADER_KEY;
    private static final io.grpc.Metadata.Key API_KEY_HEADER_KEY;
    public static final Logger logger = new Logger();
    private final String apiKey;
    private final String appCertFingerprint;
    public final String appPackageName;
    private final Provider channelProvider;
    private final ListeningExecutorService executor;
    private final AccountManager gkAccountManager;
    private boolean persistChannelConnection;
    public final StreamzIncrements streamzIncrements;
    public final Counter2 syncsCounter;

    GrowthApiClientImpl(ListeningExecutorService listeningexecutorservice, String s, String s1, String s2, Provider provider, AccountManager accountmanager, Counter2 counter2, 
            StreamzIncrements streamzincrements)
    {
        persistChannelConnection = false;
        executor = listeningexecutorservice;
        appCertFingerprint = s;
        appPackageName = s1;
        apiKey = s2;
        channelProvider = provider;
        gkAccountManager = accountmanager;
        syncsCounter = counter2;
        streamzIncrements = streamzincrements;
    }

    static final ListenableFuture lambda$getPromos$0$GrowthApiClientImpl(com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosRequest getpromosrequest, com.google.internal.identity.growth.v1.GrowthApiServiceGrpc.GrowthApiServiceFutureStub growthapiservicefuturestub)
        throws Exception
    {
        return ClientCalls.futureUnaryCall(((AbstractStub) (growthapiservicefuturestub)).channel.newCall(GrowthApiServiceGrpc.getGetPromosMethod(), ((AbstractStub) (growthapiservicefuturestub)).callOptions), getpromosrequest);
    }

    public final ListenableFuture getPromos(com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosRequest getpromosrequest, final String accountName)
    {
        Object obj = logger;
        ManagedChannel managedchannel = (ManagedChannel)channelProvider.get();
        obj = new com.google.internal.identity.growth.v1.GrowthApiServiceGrpc.GrowthApiServiceFutureStub(managedchannel);
        Object obj1 = new Metadata();
        ((Metadata) (obj1)).put(API_KEY_HEADER_KEY, apiKey);
        ((Metadata) (obj1)).put(ANDROID_CERTIFICATE_HEADER_KEY, appCertFingerprint);
        ((Metadata) (obj1)).put(ANDROID_PACKAGE_HEADER_KEY, appPackageName);
        obj1 = new io.grpc.stub.MetadataUtils.HeaderAttachingClientInterceptor(((Metadata) (obj1)));
        obj = (com.google.internal.identity.growth.v1.GrowthApiServiceGrpc.GrowthApiServiceFutureStub)((AbstractStub) (obj)).build(ClientInterceptors.intercept(((AbstractStub) (obj)).channel, new ClientInterceptor[] {
            obj1
        }), ((AbstractStub) (obj)).callOptions);
        Object obj2 = TimeUnit.SECONDS;
        obj1 = ((AbstractStub) (obj)).channel;
        CallOptions calloptions = ((AbstractStub) (obj)).callOptions;
        obj2 = Deadline.after(20L, ((TimeUnit) (obj2)));
        calloptions = new CallOptions(calloptions);
        calloptions.deadline = ((Deadline) (obj2));
        obj = (com.google.internal.identity.growth.v1.GrowthApiServiceGrpc.GrowthApiServiceFutureStub)((AbstractStub) (obj)).build(((Channel) (obj1)), calloptions);
        class .Lambda._cls2
            implements Closeable
        {

            private final GrowthApiClientImpl arg$1;
            private final ManagedChannel arg$2;

            public final void close()
            {
                GrowthApiClientImpl growthapiclientimpl = arg$1;
                arg$2.shutdown();
            }

            .Lambda._cls2(ManagedChannel managedchannel)
            {
                arg$1 = GrowthApiClientImpl.this;
                arg$2 = managedchannel;
            }
        }

        class .Lambda._cls0
            implements AsyncFunction
        {

            private final com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosRequest arg$1;

            public final ListenableFuture apply(Object obj3)
            {
                return GrowthApiClientImpl.lambda$getPromos$0$GrowthApiClientImpl(arg$1, (com.google.internal.identity.growth.v1.GrowthApiServiceGrpc.GrowthApiServiceFutureStub)obj3);
            }

            .Lambda._cls0(com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosRequest getpromosrequest)
            {
                arg$1 = getpromosrequest;
            }
        }

        class .Lambda._cls1
            implements Function
        {

            private final GrowthApiClientImpl arg$1;
            private final com.google.internal.identity.growth.v1.GrowthApiServiceGrpc.GrowthApiServiceFutureStub arg$2;

            public final Object apply(Object obj3)
            {
                Object obj4 = arg$1;
                obj4 = arg$2;
                obj3 = new GoogleAuthLibraryCallCredentials(new OAuth2Credentials(new AccessToken((String)obj3, null)));
                Channel channel = ((AbstractStub) (obj4)).channel;
                CallOptions calloptions1 = new CallOptions(((AbstractStub) (obj4)).callOptions);
                calloptions1.credentials = ((io.grpc.CallCredentials) (obj3));
                return (com.google.internal.identity.growth.v1.GrowthApiServiceGrpc.GrowthApiServiceFutureStub)((AbstractStub) (obj4)).build(channel, calloptions1);
            }

            .Lambda._cls1(com.google.internal.identity.growth.v1.GrowthApiServiceGrpc.GrowthApiServiceFutureStub growthapiservicefuturestub)
            {
                arg$1 = GrowthApiClientImpl.this;
                arg$2 = growthapiservicefuturestub;
            }
        }

        if (accountName == null)
        {
            if (obj == null)
            {
                obj = com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
            } else
            {
                obj = new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(obj);
            }
        } else
        {
            obj = AbstractTransformFuture.create(gkAccountManager.getOAuthTokenAsync(accountName, "oauth2:https://www.googleapis.com/auth/mobile_user_preferences"), new .Lambda._cls1(((com.google.internal.identity.growth.v1.GrowthApiServiceGrpc.GrowthApiServiceFutureStub) (obj))), executor);
        }
        getpromosrequest = AsyncCloseable.fromFutureWithCloseables(((ListenableFuture) (obj)), new Closeable[] {
            new .Lambda._cls2(managedchannel)
        }).transformAsyncAndClose(new .Lambda._cls0(getpromosrequest), executor);
        accountName = new _cls1();
        obj = com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE;
        if (accountName == null)
        {
            throw new NullPointerException();
        } else
        {
            getpromosrequest.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(getpromosrequest, accountName), ((java.util.concurrent.Executor) (obj)));
            return getpromosrequest;
        }
    }

    static 
    {
        API_KEY_HEADER_KEY = io.grpc.Metadata.Key.of("X-Goog-Api-Key", Metadata.ASCII_STRING_MARSHALLER);
        ANDROID_CERTIFICATE_HEADER_KEY = io.grpc.Metadata.Key.of("X-Android-Cert", Metadata.ASCII_STRING_MARSHALLER);
        ANDROID_PACKAGE_HEADER_KEY = io.grpc.Metadata.Key.of("X-Android-Package", Metadata.ASCII_STRING_MARSHALLER);
    }

    private class _cls1
        implements FutureCallback
    {

        private final GrowthApiClientImpl this$0;
        private final String val$accountName;

        public final void onFailure(Throwable throwable)
        {
            boolean flag = true;
            GrowthApiClientImpl.logger.w(throwable, "Failed to fetch promotions.", new Object[0]);
            Object obj = streamzIncrements;
            syncsCounter.doRecord(Long.valueOf(1L), new CellFieldTuple(new Object[] {
                appPackageName, "ERROR"
            }));
            throwable = ((StreamzIncrements) (obj)).streamzLogger;
            obj = new com.google.android.libraries.streamz.GcoreClearcutStreamzLogger.GcoreMessageProducer(((StreamzIncrements) (obj)).metricFactory);
            if (((StreamzMessageProducer) (obj)).incrementRequest.batch_.size() != 0)
            {
                flag = false;
            }
            if (!flag)
            {
                ((GcoreClearcutStreamzLogger) (throwable)).gcoreClearcutLogger.newEvent(((com.google.android.libraries.gcoreclient.clearcut.GcoreClearcutMessageProducer) (obj))).setLogSourceName(((GcoreClearcutStreamzLogger) (throwable)).logSourceName).logAsync();
            }
            StreamzIncrements.incrementCounts = 0;
        }

        public final void onSuccess(Object obj)
        {
            obj = (com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosResponse)obj;
            boolean flag;
            if (obj != null && Log.isLoggable(GrowthApiClientImpl.logger.tag, 3))
            {
                Object obj1 = new Joiner(String.valueOf('\n'));
                obj = ((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosResponse) (obj)).promo_;
                class .Lambda._cls0
                    implements Function
                {

                    public static final Function $instance = new .Lambda._cls0();

                    public final Object apply(Object obj2)
                    {
                        com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosResponse.Promotion promotion = (com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosResponse.Promotion)obj2;
                        Object obj3;
                        int i;
                        int j;
                        if (promotion.promoId_ == null)
                        {
                            obj2 = com.google.identity.boq.growth.promoprovider.proto.PromoProvider.PromoIdentification.DEFAULT_INSTANCE;
                        } else
                        {
                            obj2 = promotion.promoId_;
                        }
                        i = ((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.PromoIdentification) (obj2)).impressionCappingId_;
                        if (promotion.promoId_ == null)
                        {
                            obj2 = com.google.identity.boq.growth.promoprovider.proto.PromoProvider.PromoIdentification.DEFAULT_INSTANCE;
                        } else
                        {
                            obj2 = promotion.promoId_;
                        }
                        j = ((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.PromoIdentification) (obj2)).mendelId_.getInt(0);
                        if (promotion.ui_ == null)
                        {
                            obj2 = com.google.identity.growth.proto.Promotion.PromoUi.DEFAULT_INSTANCE;
                        } else
                        {
                            obj2 = promotion.ui_;
                        }
                        obj3 = com.google.identity.growth.proto.Promotion.PromoUi.UiType.forNumber(((com.google.identity.growth.proto.Promotion.PromoUi) (obj2)).uiType_);
                        obj2 = obj3;
                        if (obj3 == null)
                        {
                            obj2 = com.google.identity.growth.proto.Promotion.PromoUi.UiType.UITYPE_NONE;
                        }
                        if (((com.google.identity.growth.proto.Promotion.PromoUi.UiType) (obj2)).equals(com.google.identity.growth.proto.Promotion.PromoUi.UiType.UITYPE_DO_NOT_DISPLAY))
                        {
                            obj2 = " [CONTROL GROUP]";
                        } else
                        {
                            obj2 = "";
                        }
                        if (promotion.ui_ == null)
                        {
                            obj3 = com.google.identity.growth.proto.Promotion.PromoUi.DEFAULT_INSTANCE;
                        } else
                        {
                            obj3 = promotion.ui_;
                        }
                        return String.format("Id: %s (%s)%s%s", new Object[] {
                            Integer.valueOf(i), Integer.valueOf(j), obj2, DebugUtil.getPromoTitle(((com.google.identity.growth.proto.Promotion.PromoUi) (obj3)))
                        });
                    }


                private .Lambda._cls0()
                {
                }
                }

                Function function = .Lambda._cls0..instance;
                if (obj instanceof RandomAccess)
                {
                    obj = new com.google.common.collect.Lists.TransformingRandomAccessList(((java.util.List) (obj)), function);
                } else
                {
                    obj = new com.google.common.collect.Lists.TransformingSequentialList(((java.util.List) (obj)), function);
                }
                obj = ((Iterable) (obj)).iterator();
                ((Joiner) (obj1)).appendTo(new StringBuilder(), ((java.util.Iterator) (obj))).toString();
                if (accountName != null)
                {
                    obj = String.format(" for account %s", new Object[] {
                        accountName
                    });
                } else
                {
                    obj = " for signed-out user";
                }
                obj = GrowthApiClientImpl.logger;
            }
            obj = GrowthApiClientImpl.logger;
            obj1 = streamzIncrements;
            syncsCounter.doRecord(Long.valueOf(1L), new CellFieldTuple(new Object[] {
                appPackageName, "OK"
            }));
            obj = ((StreamzIncrements) (obj1)).streamzLogger;
            obj1 = new com.google.android.libraries.streamz.GcoreClearcutStreamzLogger.GcoreMessageProducer(((StreamzIncrements) (obj1)).metricFactory);
            if (((StreamzMessageProducer) (obj1)).incrementRequest.batch_.size() == 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                ((GcoreClearcutStreamzLogger) (obj)).gcoreClearcutLogger.newEvent(((com.google.android.libraries.gcoreclient.clearcut.GcoreClearcutMessageProducer) (obj1))).setLogSourceName(((GcoreClearcutStreamzLogger) (obj)).logSourceName).logAsync();
            }
            StreamzIncrements.incrementCounts = 0;
        }

        _cls1()
        {
            this$0 = GrowthApiClientImpl.this;
            accountName = s;
            super();
        }
    }

}
