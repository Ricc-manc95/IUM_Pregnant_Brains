// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20;

import android.app.Activity;

public final class clientActivity
{

    public final Activity clientActivity;
    public int hatsDisplayLogo;
    public Integer requestCode;
    public boolean showSurveyWithoutPrompt;
    public String siteId;

    public final clientActivity forSiteId(String s)
    {
        if (s == null)
        {
            throw new NullPointerException("Site ID cannot be set to null.");
        }
        if (siteId != null)
        {
            throw new UnsupportedOperationException("Currently don't support multiple site IDs.");
        } else
        {
            siteId = s;
            return this;
        }
    }

    public A(Activity activity)
    {
        hatsDisplayLogo = 0x7f0201bc;
        showSurveyWithoutPrompt = false;
        if (activity == null)
        {
            throw new IllegalArgumentException("Client activity is missing.");
        } else
        {
            clientActivity = activity;
            return;
        }
    }
}
