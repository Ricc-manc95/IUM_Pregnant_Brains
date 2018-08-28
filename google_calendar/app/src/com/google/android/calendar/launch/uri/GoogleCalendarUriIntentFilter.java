// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.launch.uri;

import android.app.Activity;
import android.content.Intent;

public final class GoogleCalendarUriIntentFilter
{

    public final Activity activity;
    public final Intent launchIntent;

    public GoogleCalendarUriIntentFilter(Activity activity1, Intent intent)
    {
        activity = activity1;
        launchIntent = intent;
    }
}
