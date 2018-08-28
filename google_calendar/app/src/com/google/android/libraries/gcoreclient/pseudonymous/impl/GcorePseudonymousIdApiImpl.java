// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.gcoreclient.pseudonymous.impl;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.pseudonymous.PseudonymousId;
import com.google.android.libraries.gcoreclient.common.api.support.BaseGcoreApi;
import com.google.android.libraries.gcoreclient.pseudonymous.GcorePseudonymousIdApi;

public final class GcorePseudonymousIdApiImpl
    implements BaseGcoreApi, GcorePseudonymousIdApi
{

    public GcorePseudonymousIdApiImpl()
    {
    }

    public final Api getApi()
    {
        return PseudonymousId.API;
    }
}
