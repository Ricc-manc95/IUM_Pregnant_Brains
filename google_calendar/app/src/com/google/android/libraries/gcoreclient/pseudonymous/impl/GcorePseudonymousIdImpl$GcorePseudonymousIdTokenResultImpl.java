// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.gcoreclient.pseudonymous.impl;

import com.google.android.libraries.gcoreclient.common.api.GcoreStatus;
import com.google.android.libraries.gcoreclient.common.api.support.GcoreStatusImpl;
import com.google.android.libraries.gcoreclient.common.api.support.ResultWrapper;
import com.google.android.libraries.gcoreclient.pseudonymous.GcorePseudonymousIdToken;
import com.google.android.libraries.gcoreclient.pseudonymous.GcorePseudonymousIdTokenResult;

// Referenced classes of package com.google.android.libraries.gcoreclient.pseudonymous.impl:
//            GcorePseudonymousIdTokenImpl

final class result
    implements GcorePseudonymousIdTokenResult
{

    public static final ResultWrapper TOKEN_RESULT_WRAPPER = new _cls1();
    private final com.google.android.gms.pseudonymous.ymousIdTokenResultImpl._cls1 result;

    public final GcorePseudonymousIdToken getPseudonymousIdToken()
    {
        return new GcorePseudonymousIdTokenImpl(result.result());
    }

    public final GcoreStatus getStatus()
    {
        return new GcoreStatusImpl(result.result());
    }


    _cls1(com.google.android.gms.pseudonymous.ymousIdTokenResultImpl ymousidtokenresultimpl)
    {
        result = ymousidtokenresultimpl;
    }

    class _cls1
        implements ResultWrapper
    {

        public final GcoreResult wrap(Result result1)
        {
            return new GcorePseudonymousIdImpl.GcorePseudonymousIdTokenResultImpl((com.google.android.gms.pseudonymous.PseudonymousIdApi.PseudonymousIdTokenResult)result1);
        }

            _cls1()
            {
            }
    }

}
