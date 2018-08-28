// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl;

import com.google.android.libraries.internal.growth.growthkit.internal.accounts.AccountManager;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Clock;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Logger;
import com.google.android.libraries.internal.growth.growthkit.internal.common.PerAccountProvider;
import com.google.android.libraries.internal.growth.growthkit.internal.concurrent.MoreFutures;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.ClearcutEventsStore;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.StorageUtilities;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.VisualElementEventsStore;
import java.util.Iterator;
import java.util.List;

public final class StorageUtilitiesImpl
    implements StorageUtilities
{

    public static final Logger logger = new Logger();
    private final AccountManager accountManager;
    private final long age;
    private final PerAccountProvider clearcutEventStoreProvider;
    private final Clock clock;
    private final PerAccountProvider veEventStoreProvider;

    public StorageUtilitiesImpl(PerAccountProvider peraccountprovider, PerAccountProvider peraccountprovider1, AccountManager accountmanager, Clock clock1, long l)
    {
        clearcutEventStoreProvider = peraccountprovider;
        veEventStoreProvider = peraccountprovider1;
        accountManager = accountmanager;
        clock = clock1;
        age = l;
    }

    public final void cleanup()
    {
        long l = clock.currentTimeMillis() - age;
        Logger logger1 = logger;
        class .Lambda._cls1
            implements Receiver
        {

            private final String arg$1;

            public final void accept(Object obj)
            {
                String s1 = arg$1;
                obj = (Integer)obj;
                obj = StorageUtilitiesImpl.logger;
            }

            .Lambda._cls1(String s)
            {
                arg$1 = s;
            }
        }

        String s;
        for (Iterator iterator = accountManager.getAccountsNames().iterator(); iterator.hasNext(); MoreFutures.addCallback(((VisualElementEventsStore)veEventStoreProvider.forAccount(s)).cleanup(l), new .Lambda._cls1(s), null))
        {
            s = (String)iterator.next();
            class .Lambda._cls0
                implements Receiver
            {

                private final String arg$1;

                public final void accept(Object obj)
                {
                    String s1 = arg$1;
                    obj = (Integer)obj;
                    obj = StorageUtilitiesImpl.logger;
                }

            .Lambda._cls0(String s)
            {
                arg$1 = s;
            }
            }

            MoreFutures.addCallback(((ClearcutEventsStore)clearcutEventStoreProvider.forAccount(s)).cleanup(l), new .Lambda._cls0(s), null);
        }

        class .Lambda._cls2
            implements Receiver
        {

            public static final Receiver $instance = new .Lambda._cls2();

            public final void accept(Object obj)
            {
                obj = (Integer)obj;
                obj = StorageUtilitiesImpl.logger;
            }


            private .Lambda._cls2()
            {
            }
        }

        MoreFutures.addCallback(((ClearcutEventsStore)clearcutEventStoreProvider.forAccount(null)).cleanup(l), .Lambda._cls2..instance, null);
        class .Lambda._cls3
            implements Receiver
        {

            public static final Receiver $instance = new .Lambda._cls3();

            public final void accept(Object obj)
            {
                obj = (Integer)obj;
                obj = StorageUtilitiesImpl.logger;
            }


            private .Lambda._cls3()
            {
            }
        }

        MoreFutures.addCallback(((VisualElementEventsStore)veEventStoreProvider.forAccount(null)).cleanup(l), .Lambda._cls3..instance, null);
    }

}
