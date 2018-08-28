// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.settings;

import android.accounts.Account;
import android.content.Context;
import com.google.android.gms.common.api.PendingResult;

// Referenced classes of package com.google.android.calendar.api.settings:
//            SettingsModifications

public interface SettingsClient
{

    public abstract void initialize(Context context);

    public abstract PendingResult list();

    public abstract PendingResult read(Account account);

    public abstract PendingResult update(SettingsModifications settingsmodifications);
}
