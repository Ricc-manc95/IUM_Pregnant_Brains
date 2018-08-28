// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.ads.identifier;

import android.util.Log;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

// Referenced classes of package com.google.android.gms.ads.identifier:
//            zza

final class zzsv extends Thread
{

    private final String zzsv;

    public final void run()
    {
        String s;
        new zza();
        s = zzsv;
        HttpURLConnection httpurlconnection = (HttpURLConnection)(new URL(s)).openConnection();
        int i = httpurlconnection.getResponseCode();
        if (i >= 200 && i < 300)
        {
            break MISSING_BLOCK_LABEL_95;
        }
        Log.w("HttpUrlPinger", (new StringBuilder(String.valueOf(s).length() + 65)).append("Received non-success response code ").append(i).append(" from pinging URL: ").append(s).toString());
        httpurlconnection.disconnect();
        return;
        Exception exception;
        exception;
        httpurlconnection.disconnect();
        throw exception;
        Object obj;
        obj;
        String s1 = String.valueOf(((IndexOutOfBoundsException) (obj)).getMessage());
        Log.w("HttpUrlPinger", (new StringBuilder(String.valueOf(s).length() + 32 + String.valueOf(s1).length())).append("Error while parsing ping URL: ").append(s).append(". ").append(s1).toString(), ((Throwable) (obj)));
        return;
        obj;
_L2:
        String s2 = String.valueOf(((Exception) (obj)).getMessage());
        Log.w("HttpUrlPinger", (new StringBuilder(String.valueOf(s).length() + 27 + String.valueOf(s2).length())).append("Error while pinging URL: ").append(s).append(". ").append(s2).toString(), ((Throwable) (obj)));
        return;
        obj;
        if (true) goto _L2; else goto _L1
_L1:
    }

    A(String s)
    {
        zzsv = s;
        super();
    }
}
