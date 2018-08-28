// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.gcoreclient.phenotype.impl;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.phenotype.Phenotype;
import com.google.android.libraries.gcoreclient.common.api.support.BaseGcoreApi;
import com.google.android.libraries.gcoreclient.phenotype.PhenotypeApi;

public final class PhenotypeApiImpl
    implements BaseGcoreApi, PhenotypeApi
{

    public PhenotypeApiImpl()
    {
    }

    public final Api getApi()
    {
        return Phenotype.API;
    }
}
