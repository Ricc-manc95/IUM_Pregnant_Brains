// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20;

import android.content.Context;

public final class HatsDownloadRequest
{

    public final String advertisingId;
    public final String baseDownloadUrl;
    public final Context context;
    public final String siteContext = null;
    public final String siteId;

    public HatsDownloadRequest(Builder builder)
    {
        context = builder.context;
        siteId = builder.siteId;
        advertisingId = builder.advertisingId;
        baseDownloadUrl = builder.baseDownloadUrl;
    }

    private class Builder
    {

        public String advertisingId;
        public boolean alreadyBuilt;
        public String baseDownloadUrl;
        public final Context context;
        public String siteId;

        public Builder(Context context1)
        {
            baseDownloadUrl = "https://clients4.google.com/insights/consumersurveys/gk/prompt";
            alreadyBuilt = false;
            if (context1 == null)
            {
                throw new NullPointerException("Context was missing.");
            } else
            {
                context = context1;
                return;
            }
        }
    }

}
