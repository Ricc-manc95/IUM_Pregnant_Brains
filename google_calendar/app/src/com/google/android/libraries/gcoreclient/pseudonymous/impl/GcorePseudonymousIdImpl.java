// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.gcoreclient.pseudonymous.impl;

import com.google.android.gms.pseudonymous.PseudonymousId;
import com.google.android.gms.pseudonymous.PseudonymousIdApi;
import com.google.android.libraries.gcoreclient.common.api.GcoreGoogleApiClient;
import com.google.android.libraries.gcoreclient.common.api.GcorePendingResult;
import com.google.android.libraries.gcoreclient.common.api.support.GcorePendingResultImpl;
import com.google.android.libraries.gcoreclient.common.api.support.GcoreWrapper;
import com.google.android.libraries.gcoreclient.pseudonymous.GcorePseudonymousId;

public final class GcorePseudonymousIdImpl
    implements GcorePseudonymousId
{

    private final GcoreWrapper wrapper;

    public GcorePseudonymousIdImpl(GcoreWrapper gcorewrapper)
    {
        wrapper = gcorewrapper;
    }

    public final GcorePendingResult getToken(GcoreGoogleApiClient gcoregoogleapiclient)
    {
        return new GcorePendingResultImpl(PseudonymousId.PseudonymousIdApi.getToken(wrapper.unwrap(gcoregoogleapiclient)), GcorePseudonymousIdTokenResultImpl.TOKEN_RESULT_WRAPPER);
    }

    private class GcorePseudonymousIdTokenResultImpl
        implements GcorePseudonymousIdTokenResult
    {

        public static final ResultWrapper TOKEN_RESULT_WRAPPER = new _cls1();
        private final com.google.android.gms.pseudonymous.PseudonymousIdApi.PseudonymousIdTokenResult result;

        public final GcorePseudonymousIdToken getPseudonymousIdToken()
        {
            return new GcorePseudonymousIdTokenImpl(result.getToken());
        }

        public final GcoreStatus getStatus()
        {
            return new GcoreStatusImpl(result.getStatus());
        }


        GcorePseudonymousIdTokenResultImpl(com.google.android.gms.pseudonymous.PseudonymousIdApi.PseudonymousIdTokenResult pseudonymousidtokenresult)
        {
            result = pseudonymousidtokenresult;
        }

        class _cls1
            implements ResultWrapper
        {

            public final GcoreResult wrap(Result result1)
            {
                return new GcorePseudonymousIdTokenResultImpl((com.google.android.gms.pseudonymous.PseudonymousIdApi.PseudonymousIdTokenResult)result1);
            }

                _cls1()
                {
                }
        }

    }

}
