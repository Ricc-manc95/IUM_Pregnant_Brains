// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl;

import com.google.android.libraries.internal.growth.growthkit.internal.common.PerAccountProvider;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.MessageStore;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.PromotionKeysHelper;
import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl:
//            TestingToolsBroadcastReceiver

final class arg._cls3
    implements AsyncCallable
{

    private final TestingToolsBroadcastReceiver arg$1;
    private final String arg$2;
    private final com.google.identity.boq.growth.promoprovider.proto.n arg$3;

    public final ListenableFuture call()
    {
        Object obj = arg$1;
        Object obj1 = arg$2;
        com.google.identity.boq.growth.promoprovider.proto.n n = arg$3;
        obj1 = (MessageStore)((TestingToolsBroadcastReceiver) (obj)).promotionsStore.forAccount(((String) (obj1)));
        if (n.promoId_ == null)
        {
            obj = com.google.identity.boq.growth.promoprovider.proto.T_INSTANCE;
        } else
        {
            obj = n.promoId_;
        }
        return ((MessageStore) (obj1)).put(PromotionKeysHelper.of(((com.google.identity.boq.growth.promoprovider.proto..of) (obj))), n);
    }

    (TestingToolsBroadcastReceiver testingtoolsbroadcastreceiver, String s, com.google.identity.boq.growth.promoprovider.proto.n n)
    {
        arg$1 = testingtoolsbroadcastreceiver;
        arg$2 = s;
        arg$3 = n;
    }
}
