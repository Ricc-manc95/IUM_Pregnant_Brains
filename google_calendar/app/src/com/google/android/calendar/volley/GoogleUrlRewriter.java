// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.volley;

import android.content.ContentResolver;
import android.content.Context;
import com.google.android.common.http.Rule;
import com.google.android.common.http.RuleMatcher;
import com.google.android.common.http.UrlRules;

public final class GoogleUrlRewriter
    implements com.android.volley.toolbox.HurlStack.UrlRewriter
{

    private final ContentResolver contentResolver;
    private final boolean sensitiveLogging;

    GoogleUrlRewriter(Context context, boolean flag)
    {
        contentResolver = context.getContentResolver();
        sensitiveLogging = flag;
    }

    public final String rewriteUrl(String s)
    {
        Object obj = contentResolver;
        obj = com.google.android.common.http.UrlRules.UrlRuleFetcher.instance.getRules(((ContentResolver) (obj)));
        if (((UrlRules) (obj)).ruleMatcher == null)
        {
            obj.ruleMatcher = new RuleMatcher(((UrlRules) (obj)).rules);
        }
        Object obj1 = ((UrlRules) (obj)).ruleMatcher.match(s);
        obj = obj1;
        if (obj1 == null)
        {
            obj = Rule.DEFAULT;
        }
        obj1 = s;
        if (obj != Rule.DEFAULT)
        {
            obj1 = ((Rule) (obj)).apply(s);
            if (sensitiveLogging)
            {
                String.format("Rule %s rewrites %s \u2192 %s", new Object[] {
                    ((Rule) (obj)).name, s, obj1
                });
            } else
            {
                String.format("Rule %s applied", new Object[] {
                    ((Rule) (obj)).name
                });
            }
        }
        return ((String) (obj1));
    }
}
