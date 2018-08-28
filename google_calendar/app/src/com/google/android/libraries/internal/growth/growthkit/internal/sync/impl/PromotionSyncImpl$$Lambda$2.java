// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.sync.impl;

import com.google.android.libraries.gcoreclient.pseudonymous.GcorePseudonymousIdToken;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.sync.impl:
//            PromotionSyncImpl

final class arg._cls1
    implements AsyncFunction
{

    private final PromotionSyncImpl arg$1;

    public final ListenableFuture apply(Object obj)
    {
        PromotionSyncImpl promotionsyncimpl = arg$1;
        Object obj1 = (GcorePseudonymousIdToken)obj;
        if (((GcorePseudonymousIdToken) (obj1)).getValue() == null)
        {
            if (true)
            {
                return com.google.common.util.concurrent.cessfulFuture.NULL;
            } else
            {
                return new com.google.common.util.concurrent.cessfulFuture(null);
            }
        }
        obj = promotionsyncimpl.getRequestHeaderBuilder();
        obj1 = ((GcorePseudonymousIdToken) (obj1)).getValue();
        ((com.google.protobuf.) (obj)).copyOnWrite();
        com.google.identity.boq.growth.common.proto.eader eader = (com.google.identity.boq.growth.common.proto.eader)((com.google.identity.boq.growth.common.proto.eader.Builder) (obj)).instance;
        if (obj1 == null)
        {
            throw new NullPointerException();
        } else
        {
            eader.pseudonymousId_ = ((String) (obj1));
            return promotionsyncimpl.sync(((com.google.identity.boq.growth.common.proto.eader.Builder) (obj)), null);
        }
    }

    (PromotionSyncImpl promotionsyncimpl)
    {
        arg$1 = promotionsyncimpl;
    }
}
