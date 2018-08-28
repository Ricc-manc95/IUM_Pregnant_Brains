// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.lifecycle.impl;

import android.content.Context;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Logger;
import com.google.android.libraries.internal.growth.growthkit.internal.concurrent.MoreFutures;
import com.google.android.libraries.internal.growth.growthkit.internal.streamz.StreamzIncrements;
import com.google.android.libraries.internal.growth.growthkit.lifecycle.GrowthKitStartup;
import com.google.android.libraries.streamz.Counter2;
import com.google.common.util.concurrent.ListeningExecutorService;
import javax.inject.Provider;

public final class GrowthKitStartupImpl
    implements GrowthKitStartup
{

    public static final Logger logger = new Logger();
    private final Context applicationContext;
    public final Provider enableFlagProvider;
    private final ListeningExecutorService executor;
    public final Counter2 growthkitStartedCounter;
    public final Provider listeners;
    public final String packageName;
    public final StreamzIncrements streamzIncrements;

    public GrowthKitStartupImpl(Context context, ListeningExecutorService listeningexecutorservice, Provider provider, Provider provider1, Counter2 counter2, StreamzIncrements streamzincrements, String s)
    {
        applicationContext = context;
        executor = listeningexecutorservice;
        listeners = provider;
        enableFlagProvider = provider1;
        growthkitStartedCounter = counter2;
        streamzIncrements = streamzincrements;
        packageName = s;
    }

    public final void start()
    {
        class .Lambda._cls0
            implements Runnable
        {

            private final GrowthKitStartupImpl arg$1;

            public final void run()
            {
                Object obj = arg$1;
                if (!((Boolean)((GrowthKitStartupImpl) (obj)).enableFlagProvider.get()).booleanValue())
                {
                    obj = GrowthKitStartupImpl.logger;
                    obj = ((Object) (new Object[0]));
                    if (obj != null && obj.length > 0)
                    {
                        String.format("GrowthKit is disabled by Phenotype flag.", ((Object []) (obj)));
                    }
                } else
                {
                    obj = ((Set)((GrowthKitStartupImpl) (obj)).listeners.get()).iterator();
                    while (((Iterator) (obj)).hasNext()) 
                    {
                        GrowthKitStartupListener growthkitstartuplistener = (GrowthKitStartupListener)((Iterator) (obj)).next();
                        try
                        {
                            growthkitstartuplistener.onApplicationStartup$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7CKLC___0();
                        }
                        catch (Exception exception)
                        {
                            GrowthKitStartupImpl.logger.e(exception, "Failed startup listener: %s", new Object[] {
                                growthkitstartuplistener
                            });
                        }
                    }
                }
            }

            .Lambda._cls0()
            {
                arg$1 = GrowthKitStartupImpl.this;
            }
        }

        class .Lambda._cls1
            implements Receiver
        {

            private final GrowthKitStartupImpl arg$1;

            public final void accept(Object obj)
            {
                obj = arg$1;
                ((GrowthKitStartupImpl) (obj)).streamzIncrements.increment(((GrowthKitStartupImpl) (obj)).growthkitStartedCounter, ((GrowthKitStartupImpl) (obj)).packageName, "OK");
            }

            .Lambda._cls1()
            {
                arg$1 = GrowthKitStartupImpl.this;
            }
        }

        class .Lambda._cls2
            implements Receiver
        {

            private final GrowthKitStartupImpl arg$1;

            public final void accept(Object obj)
            {
                GrowthKitStartupImpl growthkitstartupimpl = arg$1;
                obj = (Throwable)obj;
                GrowthKitStartupImpl.logger.w(((Throwable) (obj)), "GrowthKit failed to start.", new Object[0]);
                growthkitstartupimpl.streamzIncrements.increment(growthkitstartupimpl.growthkitStartedCounter, growthkitstartupimpl.packageName, "ERROR");
            }

            .Lambda._cls2()
            {
                arg$1 = GrowthKitStartupImpl.this;
            }
        }

        MoreFutures.addCallback(executor.submit(new .Lambda._cls0()), new .Lambda._cls1(), new .Lambda._cls2());
    }

}
