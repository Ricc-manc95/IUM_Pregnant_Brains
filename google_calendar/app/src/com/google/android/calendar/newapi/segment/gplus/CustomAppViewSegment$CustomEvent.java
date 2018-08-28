// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.gplus;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.calendar.utils.activity.ActivityUtils;

final class logo
{

    public final Drawable icon;
    public final Intent intent;
    public final CharSequence label;
    public final Drawable logo;

    static logo extractInformation(Context context, Uri uri, long l, String s, String s1)
    {
        if (uri != null && !TextUtils.isEmpty(s) && !TextUtils.isEmpty(s1))
        {
            context = context.getPackageManager();
            if (context != null)
            {
                android.content.pm.ApplicationInfo applicationinfo;
                try
                {
                    applicationinfo = context.getApplicationInfo(s, 0);
                }
                // Misplaced declaration of an exception variable
                catch (Context context)
                {
                    return null;
                }
                if (applicationinfo != null)
                {
                    uri = new Intent("android.provider.calendar.action.HANDLE_CUSTOM_EVENT", uri);
                    uri.setPackage(s);
                    uri.putExtra("customAppUri", s1);
                    uri.putExtra("beginTime", l);
                    ActivityUtils.prepareIntentToOpenLink(uri);
                    if (context.resolveActivity(uri, 0) != null)
                    {
                        s = context.getApplicationIcon(applicationinfo);
                        s1 = context.getApplicationLabel(applicationinfo);
                        if (s1 != null && s1.length() != 0 || s != null)
                        {
                            return new <init>(uri, s, s1, context.getApplicationLogo(applicationinfo));
                        }
                    }
                }
            }
        }
        return null;
    }

    private (Intent intent1, Drawable drawable, CharSequence charsequence, Drawable drawable1)
    {
        intent = intent1;
        icon = drawable;
        label = charsequence;
        logo = drawable1;
    }
}
