// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentController;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.settings.home.HomePreferenceFragment;
import com.google.common.util.concurrent.FutureCallback;

// Referenced classes of package com.google.android.calendar.settings:
//            SettingsActivity

final class this._cls0
    implements FutureCallback
{

    private final SettingsActivity this$0;

    public final void onFailure(Throwable throwable)
    {
        if (!isDestroyed())
        {
            LogUtils.wtf(SettingsActivity.TAG, "Unable to load settings.", new Object[0]);
        }
    }

    public final void onSuccess(Object obj)
    {
        Object obj2 = null;
        obj = SettingsActivity.this;
        Object obj1;
        SettingsActivity settingsactivity;
        if (((SettingsActivity) (obj)).getIntent().getExtras() == null || !((SettingsActivity) (obj)).getIntent().getExtras().containsKey(":android:show_fragment"))
        {
            obj = null;
        } else
        {
            String s = ((SettingsActivity) (obj)).getIntent().getExtras().getString(":android:show_fragment");
            if (!SettingsActivity.isValidFragment(s))
            {
                obj = null;
            } else
            {
                obj = Fragment.instantiate(((android.content.Context) (obj)), s);
            }
        }
        obj1 = obj;
        if (obj == null)
        {
            obj1 = new HomePreferenceFragment();
        }
        settingsactivity = SettingsActivity.this;
        obj = obj2;
        if (settingsactivity.getIntent().getExtras() != null)
        {
            if (!settingsactivity.getIntent().getExtras().containsKey(":android:show_fragment_args"))
            {
                obj = obj2;
            } else
            {
                obj = settingsactivity.getIntent().getExtras().getBundle(":android:show_fragment_args");
            }
        }
        ((Fragment) (obj1)).setArguments(((Bundle) (obj)));
        mFragments.mHost.mFragmentManager.beginTransaction().replace(0x1020002, ((Fragment) (obj1))).commit();
    }

    Fragment()
    {
        this$0 = SettingsActivity.this;
        super();
    }
}
