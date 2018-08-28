// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20.network;

import android.net.Uri;
import com.google.android.libraries.hats20.cookie.HatsCookieManager;

public final class GcsRequest
{

    public final HatsCookieManager hatsCookieManager;
    public final String postData;
    public final String requestUriWithNoParams;
    public final ResponseListener responseListener;

    public GcsRequest(ResponseListener responselistener, Uri uri, HatsCookieManager hatscookiemanager)
    {
        responseListener = responselistener;
        postData = uri.getEncodedQuery();
        requestUriWithNoParams = uri.buildUpon().clearQuery().build().toString();
        hatsCookieManager = hatscookiemanager;
    }
}
