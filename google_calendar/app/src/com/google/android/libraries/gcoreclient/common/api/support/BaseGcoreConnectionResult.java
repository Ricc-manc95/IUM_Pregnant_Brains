// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.gcoreclient.common.api.support;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.libraries.gcoreclient.common.GcoreConnectionResult;

final class BaseGcoreConnectionResult
    implements GcoreConnectionResult
{

    private final ConnectionResult mResult;

    public BaseGcoreConnectionResult(ConnectionResult connectionresult)
    {
        if (connectionresult == null)
        {
            throw new IllegalArgumentException("null connectionResult");
        } else
        {
            mResult = connectionresult;
            return;
        }
    }

    public final String toString()
    {
        return mResult.toString();
    }
}
