// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.gcoreclient.pseudonymous.impl;

import com.google.android.gms.common.api.Result;
import com.google.android.libraries.gcoreclient.common.api.GcoreResult;
import com.google.android.libraries.gcoreclient.common.api.support.ResultWrapper;

final class 
    implements ResultWrapper
{

    public final GcoreResult wrap(Result result)
    {
        return new nit>((com.google.android.gms.pseudonymous.ousIdTokenResultImpl)result);
    }

    ()
    {
    }
}
