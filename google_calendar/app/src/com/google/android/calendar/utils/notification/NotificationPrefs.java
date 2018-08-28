// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.notification;

import android.content.Context;
import android.content.SharedPreferences;

// Referenced classes of package com.google.android.calendar.utils.notification:
//            NotificationUtil

public final class NotificationPrefs
{

    private static final String EMPTY_RINGTONE = null;
    public Context context;
    public Boolean defaultVibrate;
    private SharedPreferences prefs;
    public String ringtone;
    public boolean silenced;

    public NotificationPrefs(Context context1, SharedPreferences sharedpreferences)
    {
        context = context1;
        prefs = sharedpreferences;
    }

    public final boolean getDefaultVibrate()
    {
        if (defaultVibrate == null)
        {
            defaultVibrate = Boolean.valueOf(NotificationUtil.getDefaultVibrate(context, prefs));
        }
        return defaultVibrate.booleanValue();
    }

}
