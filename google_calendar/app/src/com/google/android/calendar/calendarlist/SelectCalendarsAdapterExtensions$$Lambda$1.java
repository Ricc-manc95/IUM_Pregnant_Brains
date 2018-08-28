// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.calendarlist;

import android.accounts.Account;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.calendar.RefreshUiManager;
import com.google.android.syncadapters.calendar.SyncProgressTracker;

final class arg._cls4
    implements android.content.pterExtensions..Lambda._cls1
{

    private final Context arg$1;
    private final Account arg$2;
    private final String arg$3;
    private final String arg$4;

    public final void onClick(DialogInterface dialoginterface, int i)
    {
        dialoginterface = arg$1;
        Account account = arg$2;
        String s = arg$3;
        String s1 = arg$4;
        Bundle bundle = new Bundle();
        bundle.putBoolean("force", true);
        bundle.putBoolean("expedited", true);
        bundle.putBoolean("do_not_retry", true);
        bundle.putBoolean("resync", true);
        if (!TextUtils.isEmpty(s))
        {
            bundle.putString("feed_internal", s);
        }
        if (RefreshUiManager.instance == null)
        {
            RefreshUiManager.instance = new RefreshUiManager();
        }
        SyncProgressTracker.getInstance().addPendingSync(account, bundle);
        String s2 = android.provider..addPendingSync.getAuthority();
        FeatureConfig featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)featureconfig).fishfood_debug();
        ContentResolver.requestSync(account, s2, bundle);
        if (!TextUtils.isEmpty(s))
        {
            Toast.makeText(dialoginterface, String.format("Calendar '%s' will be re-synced.", new Object[] {
                s1
            }), 0).show();
            return;
        } else
        {
            Toast.makeText(dialoginterface, String.format("Account '%s' will be re-synced.", new Object[] {
                account.name
            }), 0).show();
            return;
        }
    }

    Y(Context context, Account account, String s, String s1)
    {
        arg$1 = context;
        arg$2 = account;
        arg$3 = s;
        arg$4 = s1;
    }
}
