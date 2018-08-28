// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20;

import android.support.v4.util.ArrayMap;
import android.support.v4.util.SimpleArrayMap;
import android.text.TextUtils;
import com.google.android.libraries.hats20.cookie.HatsCookieManager;
import com.google.android.libraries.hats20.inject.HatsModule;
import com.google.android.libraries.hats20.network.GcsConnection;
import com.google.android.libraries.hats20.network.GcsRequest;

// Referenced classes of package com.google.android.libraries.hats20:
//            Constants

final class val.gcsRequest
    implements Runnable
{

    private final GcsRequest val$gcsRequest;

    public final void run()
    {
        GcsRequest gcsrequest = val$gcsRequest;
        byte abyte0[] = gcsrequest.postData.getBytes(Constants.UTF_8);
        ArrayMap arraymap = new ArrayMap();
        arraymap.put("Content-Type", "application/x-www-form-urlencoded");
        arraymap.put("Content-Length", Integer.toString(abyte0.length));
        arraymap.put("charset", "utf-8");
        arraymap.put("Connection", "close");
        arraymap.put("User-Agent", HatsModule.get().getUserAgent());
        String s = gcsrequest.hatsCookieManager.getCookie(gcsrequest.requestUriWithNoParams);
        if (!TextUtils.isEmpty(s))
        {
            arraymap.put("Cookie", s);
        }
        HatsModule.get().getGcsConnection().send(gcsrequest.requestUriWithNoParams, abyte0, arraymap, new com.google.android.libraries.hats20.network.ks(gcsrequest));
    }

    ()
    {
        val$gcsRequest = gcsrequest;
        super();
    }
}
