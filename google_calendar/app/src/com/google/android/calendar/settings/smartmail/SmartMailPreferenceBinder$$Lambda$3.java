// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.smartmail;

import android.accounts.Account;
import android.content.DialogInterface;
import android.support.v7.preference.ListPreference;

// Referenced classes of package com.google.android.calendar.settings.smartmail:
//            SmartMailPreferenceBinder, SmartMailViewModel

final class arg._cls5
    implements android.content.MailPreferenceBinder..Lambda._cls3
{

    private final SmartMailPreferenceBinder arg$1;
    private final Account arg$2;
    private final com.google.android.calendar.api.settings.teVisibilityMode arg$3;
    private final ListPreference arg$4;
    private final String arg$5[];

    public final void onClick(DialogInterface dialoginterface, int i)
    {
        dialoginterface = arg$1;
        Account account = arg$2;
        com.google.android.calendar.api.settings.bda._cls3 _lcls3 = arg$3;
        ListPreference listpreference = arg$4;
        String as[] = arg$5;
        ((SmartMailPreferenceBinder) (dialoginterface)).viewModel.setMode(account, _lcls3, true);
        dialoginterface.updateVisibilityMode(listpreference, as, _lcls3);
    }

    (SmartMailPreferenceBinder smartmailpreferencebinder, Account account, com.google.android.calendar.api.settings.bda._cls3 _pcls3, ListPreference listpreference, String as[])
    {
        arg$1 = smartmailpreferencebinder;
        arg$2 = account;
        arg$3 = _pcls3;
        arg$4 = listpreference;
        arg$5 = as;
    }
}
