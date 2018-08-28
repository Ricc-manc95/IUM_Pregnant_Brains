// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.phenotype;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;

public interface PhenotypeApi
{

    public abstract PendingResult commitToConfiguration(GoogleApiClient googleapiclient, String s);

    public abstract PendingResult getConfigurationSnapshot(GoogleApiClient googleapiclient, String s, String s1);

    public abstract PendingResult getConfigurationSnapshot(GoogleApiClient googleapiclient, String s, String s1, String s2);

    public abstract PendingResult register(GoogleApiClient googleapiclient, String s, int i, String as[], byte abyte0[]);
}
