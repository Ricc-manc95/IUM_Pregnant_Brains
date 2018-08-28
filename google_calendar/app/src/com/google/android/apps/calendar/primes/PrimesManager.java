// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.primes;

import android.content.Context;
import com.google.android.gms.clearcut.ClearcutLogger;
import com.google.android.gms.common.api.GoogleApiClient;

public final class PrimesManager
{

    public static PrimesManager primesManager;
    public GoogleApiClient client;
    public final com.google.android.gms.common.api.GoogleApiClient.Builder clientBuilder;
    public final ClearcutLogger memoryLogger;

    PrimesManager(Context context)
    {
        memoryLogger = new ClearcutLogger(context, "CALENDAR_ANDROID_PRIMES", null, null);
        clientBuilder = (new com.google.android.gms.common.api.GoogleApiClient.Builder(context)).addApi(ClearcutLogger.API);
    }
}
