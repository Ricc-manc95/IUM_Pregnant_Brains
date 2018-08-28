// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.pseudonymous.impl;

import com.google.android.libraries.gcoreclient.common.api.GcoreGoogleApiClient;
import com.google.android.libraries.gcoreclient.pseudonymous.GcorePseudonymousId;
import com.google.common.base.Function;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.pseudonymous.impl:
//            PseudonymousIdHelperImpl

final class this._cls0
    implements Function
{

    private final PseudonymousIdHelperImpl this$0;

    public final Object apply(Object obj)
    {
        obj = (GcoreGoogleApiClient)obj;
        return pseudonymousId.getToken(((GcoreGoogleApiClient) (obj)));
    }

    ()
    {
        this$0 = PseudonymousIdHelperImpl.this;
        super();
    }
}
