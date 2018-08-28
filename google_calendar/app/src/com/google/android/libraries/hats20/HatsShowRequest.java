// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20;

import android.app.Activity;

public final class HatsShowRequest
{

    public final boolean bottomSheet = false;
    public final Activity clientActivity;
    public final int hatsDisplayLogo;
    private final Integer maxPromptWidth = null;
    public final int parentResId = 0;
    public final Integer requestCode;
    public final boolean showSurveyWithoutPrompt;
    public final String siteId;

    public HatsShowRequest(Builder builder)
    {
        clientActivity = builder.clientActivity;
        siteId = builder.siteId;
        requestCode = builder.requestCode;
        hatsDisplayLogo = builder.hatsDisplayLogo;
        showSurveyWithoutPrompt = builder.showSurveyWithoutPrompt;
    }

    public final String toString()
    {
        String s = clientActivity.getLocalClassName();
        String s1 = siteId;
        String s2 = String.valueOf(requestCode);
        int i = parentResId;
        String s3 = String.valueOf(maxPromptWidth);
        boolean flag = bottomSheet;
        boolean flag1 = showSurveyWithoutPrompt;
        return (new StringBuilder(String.valueOf(s).length() + 149 + String.valueOf(s1).length() + String.valueOf(s2).length() + String.valueOf(s3).length())).append("HatsShowRequest{clientActivity=").append(s).append(", siteId='").append(s1).append('\'').append(", requestCode=").append(s2).append(", parentResId=").append(i).append(", maxPromptWidth=").append(s3).append(", bottomSheet=").append(flag).append(", showSurveyWithoutPrompt=").append(flag1).append('}').toString();
    }

    private class Builder
    {

        public final Activity clientActivity;
        public int hatsDisplayLogo;
        public Integer requestCode;
        public boolean showSurveyWithoutPrompt;
        public String siteId;

        public final Builder forSiteId(String s)
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

        public Builder(Activity activity)
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

}
