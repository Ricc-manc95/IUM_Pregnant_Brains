// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.privacy.policy;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;

// Referenced classes of package com.google.android.libraries.privacy.policy:
//            BrowserNotFoundException

public final class PrivacyPolicyLauncherImpl
{

    public PrivacyPolicyLauncherImpl()
    {
    }

    public static void launchCustomTab(Context context)
    {
        try
        {
            Object obj = new android.support.customtabs.CustomTabsIntent.Builder();
            int i = Color.parseColor("#eeeeee");
            ((android.support.customtabs.CustomTabsIntent.Builder) (obj)).mIntent.putExtra("android.support.customtabs.extra.TOOLBAR_COLOR", i);
            obj = ((android.support.customtabs.CustomTabsIntent.Builder) (obj)).build();
            Uri uri = Uri.parse("https://www.google.com/policies/privacy/");
            ((CustomTabsIntent) (obj)).intent.setData(uri);
            ContextCompat.startActivity(context, ((CustomTabsIntent) (obj)).intent, ((CustomTabsIntent) (obj)).startAnimationBundle);
            return;
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            throw new BrowserNotFoundException("https://www.google.com/policies/privacy/");
        }
    }
}
