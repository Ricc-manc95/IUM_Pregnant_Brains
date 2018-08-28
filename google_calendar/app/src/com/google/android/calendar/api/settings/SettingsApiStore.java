// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.settings;

import android.accounts.Account;
import android.content.Context;
import java.io.IOException;

// Referenced classes of package com.google.android.calendar.api.settings:
//            Settings, SettingsModifications

interface SettingsApiStore
{

    public abstract void initialize(Context context);

    public abstract Settings[] list()
        throws IOException;

    public abstract Settings read(Account account)
        throws IOException;

    public abstract Settings update(SettingsModifications settingsmodifications)
        throws IOException;
}
