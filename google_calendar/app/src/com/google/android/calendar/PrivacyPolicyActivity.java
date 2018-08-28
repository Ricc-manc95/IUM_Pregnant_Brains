// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.accounts.Account;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.calendar.common.activity.CalendarSupportActivity;
import com.google.android.calendar.utils.account.AccountsUtil;
import com.google.android.libraries.privacy.policy.PrivacyPolicyLauncherImpl;
import java.util.Arrays;
import java.util.List;
import java.util.RandomAccess;

public class PrivacyPolicyActivity extends CalendarSupportActivity
{

    public PrivacyPolicyActivity()
    {
    }

    public void onCreate(Bundle bundle)
    {
        Object obj = null;
        super.onCreate(bundle);
        List list = Arrays.asList(AccountsUtil.getGoogleAccounts(this));
        class .Lambda._cls0
            implements Function
        {

            public static final Function $instance = new .Lambda._cls0();

            public final Object apply(Object obj1)
            {
                return ((Account)obj1).name;
            }


            private .Lambda._cls0()
            {
            }
        }

        bundle = .Lambda._cls0..instance;
        if (list instanceof RandomAccess)
        {
            bundle = new com.google.common.collect.Lists.TransformingRandomAccessList(list, bundle);
        } else
        {
            bundle = new com.google.common.collect.Lists.TransformingSequentialList(list, bundle);
        }
        if (list.isEmpty())
        {
            if (true)
            {
                new PrivacyPolicyLauncherImpl();
                if (true || null.isEmpty())
                {
                    PrivacyPolicyLauncherImpl.launchCustomTab(this);
                } else
                {
                    bundle = (new Intent("com.google.android.gms.accountsettings.action.VIEW_SETTINGS")).setPackage("com.google.android.gms").putExtra("extra.accountName", null).putExtra("extra.screenId", 500);
                    if (bundle.resolveActivity(getPackageManager()) == null)
                    {
                        PrivacyPolicyLauncherImpl.launchCustomTab(this);
                    } else
                    {
                        startActivityForResult(bundle, 0);
                    }
                }
                finish();
                return;
            } else
            {
                throw new NullPointerException();
            }
        }
        if (list.size() == 1)
        {
            bundle = (Account)list.get(0);
            if (bundle == null)
            {
                bundle = obj;
            } else
            {
                bundle = ((Account) (bundle)).name;
            }
            new PrivacyPolicyLauncherImpl();
            if (bundle == null || bundle.isEmpty())
            {
                PrivacyPolicyLauncherImpl.launchCustomTab(this);
            } else
            {
                bundle = (new Intent("com.google.android.gms.accountsettings.action.VIEW_SETTINGS")).setPackage("com.google.android.gms").putExtra("extra.accountName", bundle).putExtra("extra.screenId", 500);
                if (bundle.resolveActivity(getPackageManager()) == null)
                {
                    PrivacyPolicyLauncherImpl.launchCustomTab(this);
                } else
                {
                    startActivityForResult(bundle, 0);
                }
            }
            finish();
            return;
        } else
        {
            class .Lambda._cls1
                implements android.content.DialogInterface.OnClickListener
            {

                private final PrivacyPolicyActivity arg$1;
                private final List arg$2;

                public final void onClick(DialogInterface dialoginterface, int i)
                {
                    arg$1.showPrivacyPolicy((Account)arg$2.get(i));
                }

            .Lambda._cls1(List list)
            {
                arg$1 = PrivacyPolicyActivity.this;
                arg$2 = list;
            }
            }

            class .Lambda._cls2
                implements android.content.DialogInterface.OnDismissListener
            {

                private final PrivacyPolicyActivity arg$1;

                public final void onDismiss(DialogInterface dialoginterface)
                {
                    arg$1.finish();
                }

            .Lambda._cls2()
            {
                arg$1 = PrivacyPolicyActivity.this;
            }
            }

            (new android.app.AlertDialog.Builder(this)).setItems((CharSequence[])bundle.toArray(new CharSequence[bundle.size()]), new .Lambda._cls1(list)).setOnDismissListener(new .Lambda._cls2()).show();
            return;
        }
    }

    final void showPrivacyPolicy(Account account)
    {
        if (account == null)
        {
            account = null;
        } else
        {
            account = account.name;
        }
        new PrivacyPolicyLauncherImpl();
        if (account == null || account.isEmpty())
        {
            PrivacyPolicyLauncherImpl.launchCustomTab(this);
        } else
        {
            account = (new Intent("com.google.android.gms.accountsettings.action.VIEW_SETTINGS")).setPackage("com.google.android.gms").putExtra("extra.accountName", account).putExtra("extra.screenId", 500);
            if (account.resolveActivity(getPackageManager()) == null)
            {
                PrivacyPolicyLauncherImpl.launchCustomTab(this);
            } else
            {
                startActivityForResult(account, 0);
            }
        }
        finish();
    }
}
