// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentController;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.calendar.settings:
//            SettingsFragment, ViewModelLoader

public class SettingsActivity extends AppCompatActivity
{

    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/settings/SettingsActivity);
    public boolean readFragmentActionBarTitle;
    public ViewModelLoader viewModelLoader;

    public SettingsActivity()
    {
        readFragmentActionBarTitle = false;
    }

    static boolean isValidFragment(String s)
    {
        boolean flag;
        try
        {
            flag = com/google/android/calendar/settings/SettingsFragment.isAssignableFrom(Class.forName(s));
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            return false;
        }
        return flag;
    }

    protected void onCreate(Bundle bundle)
    {
        Object obj = (android.support.v4.app.FragmentActivity.NonConfigurationInstances)getLastNonConfigurationInstance();
        if (obj != null)
        {
            obj = ((android.support.v4.app.FragmentActivity.NonConfigurationInstances) (obj)).custom;
        } else
        {
            obj = null;
        }
        viewModelLoader = (ViewModelLoader)obj;
        if (viewModelLoader == null)
        {
            viewModelLoader = new ViewModelLoader(this);
        }
        obj = getSupportActionBar();
        if (obj != null)
        {
            ((ActionBar) (obj)).setTitle(0x7f1303af);
            ((ActionBar) (obj)).setDisplayHomeAsUpEnabled(true);
        }
        super.onCreate(bundle);
        if (bundle == null)
        {
            bundle = ViewModelLoader.getViewModelAsync(this);
            obj = new _cls1();
            CalendarExecutor calendarexecutor = CalendarExecutor.MAIN;
            if (obj == null)
            {
                throw new NullPointerException();
            }
            bundle.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(bundle, ((FutureCallback) (obj))), calendarexecutor);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(0x7f15000b, menu);
        return true;
    }

    protected void onDestroy()
    {
        if (isFinishing())
        {
            viewModelLoader.future.cancel(false);
        }
        super.onDestroy();
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        if (menuitem.getItemId() == 0x7f100432)
        {
            for (menuitem = super.mFragments.mHost.mFragmentManager.getFragments().iterator(); menuitem.hasNext();)
            {
                Fragment fragment = (Fragment)menuitem.next();
                if (fragment instanceof SettingsFragment)
                {
                    ((SettingsFragment)fragment).onStartHelp(this);
                    return true;
                }
            }

            return true;
        } else
        if (menuitem.getItemId() == 0x102002c)
        {
            onBackPressed();
            return true;
        } else
        {
            return false;
        }
    }

    public final Object onRetainCustomNonConfigurationInstance()
    {
        return viewModelLoader;
    }

    protected void onStop()
    {
        super.onStop();
        readFragmentActionBarTitle = false;
    }


    private class _cls1
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

        _cls1()
        {
            this$0 = SettingsActivity.this;
            super();
        }
    }

}
