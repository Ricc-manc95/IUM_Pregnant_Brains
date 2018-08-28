// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.noinject;

import android.content.Context;
import com.google.common.util.concurrent.ListeningExecutorService;
import org.chromium.net.CronetEngine;

final class AutoValue_GrowthKitInstall_Params extends GrowthKitInstall.Params
{

    private final String apiKey;
    private final Context context;
    private final CronetEngine cronetEngine;
    private final ListeningExecutorService executorService;

    AutoValue_GrowthKitInstall_Params(Context context1, String s, ListeningExecutorService listeningexecutorservice, CronetEngine cronetengine)
    {
        context = context1;
        apiKey = s;
        executorService = listeningexecutorservice;
        cronetEngine = cronetengine;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (!(obj instanceof GrowthKitInstall.Params))
        {
            break MISSING_BLOCK_LABEL_93;
        }
        obj = (GrowthKitInstall.Params)obj;
        if (!context.equals(((GrowthKitInstall.Params) (obj)).getContext()) || !apiKey.equals(((GrowthKitInstall.Params) (obj)).getApiKey()) || !executorService.equals(((GrowthKitInstall.Params) (obj)).getExecutorService()))
        {
            break; /* Loop/switch isn't completed */
        }
        if (cronetEngine != null) goto _L4; else goto _L3
_L3:
        if (((GrowthKitInstall.Params) (obj)).getCronetEngine() == null) goto _L1; else goto _L5
_L5:
        return false;
_L4:
        if (!cronetEngine.equals(((GrowthKitInstall.Params) (obj)).getCronetEngine())) goto _L5; else goto _L6
_L6:
        return true;
        return false;
    }

    public final String getApiKey()
    {
        return apiKey;
    }

    public final Context getContext()
    {
        return context;
    }

    public final CronetEngine getCronetEngine()
    {
        return cronetEngine;
    }

    public final ListeningExecutorService getExecutorService()
    {
        return executorService;
    }

    public final int hashCode()
    {
        int j = context.hashCode();
        int k = apiKey.hashCode();
        int l = executorService.hashCode();
        int i;
        if (cronetEngine == null)
        {
            i = 0;
        } else
        {
            i = cronetEngine.hashCode();
        }
        return i ^ (((j ^ 0xf4243) * 0xf4243 ^ k) * 0xf4243 ^ l) * 0xf4243;
    }

    public final String toString()
    {
        String s = String.valueOf(context);
        String s1 = apiKey;
        String s2 = String.valueOf(executorService);
        String s3 = String.valueOf(cronetEngine);
        return (new StringBuilder(String.valueOf(s).length() + 58 + String.valueOf(s1).length() + String.valueOf(s2).length() + String.valueOf(s3).length())).append("Params{context=").append(s).append(", apiKey=").append(s1).append(", executorService=").append(s2).append(", cronetEngine=").append(s3).append("}").toString();
    }
}
