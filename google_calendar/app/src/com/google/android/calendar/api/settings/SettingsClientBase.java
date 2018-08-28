// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.settings;

import android.content.Context;

// Referenced classes of package com.google.android.calendar.api.settings:
//            SettingsClient, SettingsApiStore

abstract class SettingsClientBase
    implements SettingsClient
{

    public final SettingsApiStore api;

    SettingsClientBase(SettingsApiStore settingsapistore)
    {
        if (settingsapistore == null)
        {
            throw new NullPointerException();
        } else
        {
            api = (SettingsApiStore)settingsapistore;
            return;
        }
    }

    public void initialize(Context context)
    {
        api.initialize(context);
    }
}
