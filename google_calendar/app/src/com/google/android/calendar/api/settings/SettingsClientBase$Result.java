// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.settings;

import com.google.android.gms.common.api.Status;
import com.google.common.base.Throwables;

// Referenced classes of package com.google.android.calendar.api.settings:
//            Settings

public final class status
    implements lt, lt, lt
{

    private final Settings settings;
    private final Settings settingsList[];
    private final Status status;

    public final Settings getSettings()
    {
        return settings;
    }

    public final Settings[] getSettingsList()
    {
        return settingsList;
    }

    public final Status getStatus()
    {
        return status;
    }

    lt(int i, int j, Settings settings1, Settings asettings[])
    {
        status = new Status(i);
        settings = settings1;
        settingsList = asettings;
    }

    protected settingsList(Throwable throwable)
    {
        settings = null;
        settingsList = null;
        status = new Status(8, Throwables.getStackTraceAsString(throwable));
    }
}
