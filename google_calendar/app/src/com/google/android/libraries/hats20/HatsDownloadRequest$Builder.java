// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20;

import android.content.Context;

public final class context
{

    public String advertisingId;
    public boolean alreadyBuilt;
    public String baseDownloadUrl;
    public final Context context;
    public String siteId;

    public (Context context1)
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
