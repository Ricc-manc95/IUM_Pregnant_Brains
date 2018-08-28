// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.ContextCompat;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import java.util.ArrayList;

// Referenced classes of package android.support.v7.app:
//            AppCompatCallback, AppCompatDelegateImpl, AppCompatDelegate, ActionBar

public class AppCompatActivity extends FragmentActivity
    implements android.support.v4.app.TaskStackBuilder.SupportParentable, ActionBarDrawerToggle.DelegateProvider, AppCompatCallback
{

    private AppCompatDelegate mDelegate;
    private Resources mResources;
    private int mThemeId;

    public AppCompatActivity()
    {
        mThemeId = 0;
    }

    private final boolean onSupportNavigateUp()
    {
        Object obj = NavUtils.getParentActivityIntent(this);
        if (obj != null)
        {
            if (shouldUpRecreateTask(((Intent) (obj))))
            {
                TaskStackBuilder taskstackbuilder = new TaskStackBuilder(this);
                if (this instanceof android.support.v4.app.TaskStackBuilder.SupportParentable)
                {
                    obj = ((android.support.v4.app.TaskStackBuilder.SupportParentable)this).getSupportParentActivityIntent();
                } else
                {
                    obj = null;
                }
                if (obj == null)
                {
                    obj = NavUtils.getParentActivityIntent(this);
                }
                if (obj != null)
                {
                    android.content.ComponentName componentname1 = ((Intent) (obj)).getComponent();
                    android.content.ComponentName componentname = componentname1;
                    if (componentname1 == null)
                    {
                        componentname = ((Intent) (obj)).resolveActivity(taskstackbuilder.mSourceContext.getPackageManager());
                    }
                    taskstackbuilder.addParentStack(componentname);
                    taskstackbuilder.mIntents.add(obj);
                }
                if (taskstackbuilder.mIntents.isEmpty())
                {
                    throw new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities");
                }
                obj = (Intent[])taskstackbuilder.mIntents.toArray(new Intent[taskstackbuilder.mIntents.size()]);
                obj[0] = (new Intent(obj[0])).addFlags(0x1000c000);
                ContextCompat.startActivities(taskstackbuilder.mSourceContext, ((Intent []) (obj)), null);
                try
                {
                    ActivityCompat.finishAffinity(this);
                }
                // Misplaced declaration of an exception variable
                catch (Object obj)
                {
                    finish();
                }
            } else
            {
                navigateUpTo(((Intent) (obj)));
            }
            return true;
        } else
        {
            return false;
        }
    }

    public void addContentView(View view, android.view.ViewGroup.LayoutParams layoutparams)
    {
        if (mDelegate == null)
        {
            mDelegate = new AppCompatDelegateImpl(this, getWindow(), this);
        }
        mDelegate.addContentView(view, layoutparams);
    }

    public void closeOptionsMenu()
    {
        ActionBar actionbar = getSupportActionBar();
        if (getWindow().hasFeature(0) && (actionbar == null || !actionbar.closeOptionsMenu()))
        {
            super.closeOptionsMenu();
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyevent)
    {
        int i = keyevent.getKeyCode();
        ActionBar actionbar = getSupportActionBar();
        if (i == 82 && actionbar != null && actionbar.onMenuKeyEvent(keyevent))
        {
            return true;
        } else
        {
            return super.dispatchKeyEvent(keyevent);
        }
    }

    public View findViewById(int i)
    {
        if (mDelegate == null)
        {
            mDelegate = new AppCompatDelegateImpl(this, getWindow(), this);
        }
        return mDelegate.findViewById(i);
    }

    public final ActionBarDrawerToggle.Delegate getDrawerToggleDelegate()
    {
        if (mDelegate == null)
        {
            mDelegate = new AppCompatDelegateImpl(this, getWindow(), this);
        }
        return mDelegate.getDrawerToggleDelegate();
    }

    public MenuInflater getMenuInflater()
    {
        if (mDelegate == null)
        {
            mDelegate = new AppCompatDelegateImpl(this, getWindow(), this);
        }
        return mDelegate.getMenuInflater();
    }

    public Resources getResources()
    {
        if (mResources != null);
        if (mResources == null)
        {
            return super.getResources();
        } else
        {
            return mResources;
        }
    }

    public final ActionBar getSupportActionBar()
    {
        if (mDelegate == null)
        {
            mDelegate = new AppCompatDelegateImpl(this, getWindow(), this);
        }
        return mDelegate.getSupportActionBar();
    }

    public final Intent getSupportParentActivityIntent()
    {
        return NavUtils.getParentActivityIntent(this);
    }

    public void invalidateOptionsMenu()
    {
        if (mDelegate == null)
        {
            mDelegate = new AppCompatDelegateImpl(this, getWindow(), this);
        }
        mDelegate.invalidateOptionsMenu();
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
        if (mDelegate == null)
        {
            mDelegate = new AppCompatDelegateImpl(this, getWindow(), this);
        }
        mDelegate.onConfigurationChanged(configuration);
        if (mResources != null)
        {
            android.util.DisplayMetrics displaymetrics = super.getResources().getDisplayMetrics();
            mResources.updateConfiguration(configuration, displaymetrics);
        }
    }

    public void onContentChanged()
    {
    }

    public void onCreate(Bundle bundle)
    {
        if (mDelegate == null)
        {
            mDelegate = new AppCompatDelegateImpl(this, getWindow(), this);
        }
        AppCompatDelegate appcompatdelegate = mDelegate;
        appcompatdelegate.installViewFactory();
        appcompatdelegate.onCreate(bundle);
        if (appcompatdelegate.applyDayNight() && mThemeId != 0)
        {
            if (android.os.Build.VERSION.SDK_INT >= 23)
            {
                onApplyThemeResource(getTheme(), mThemeId, false);
            } else
            {
                setTheme(mThemeId);
            }
        }
        super.onCreate(bundle);
    }

    public void onDestroy()
    {
        super.onDestroy();
        if (mDelegate == null)
        {
            mDelegate = new AppCompatDelegateImpl(this, getWindow(), this);
        }
        mDelegate.onDestroy();
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        boolean flag;
        if (android.os.Build.VERSION.SDK_INT >= 26 || keyevent.isCtrlPressed() || KeyEvent.metaStateHasNoModifiers(keyevent.getMetaState()) || keyevent.getRepeatCount() != 0 || KeyEvent.isModifierKey(keyevent.getKeyCode()))
        {
            break MISSING_BLOCK_LABEL_79;
        }
        Window window = getWindow();
        if (window == null || window.getDecorView() == null || !window.getDecorView().dispatchKeyShortcutEvent(keyevent))
        {
            break MISSING_BLOCK_LABEL_79;
        }
        flag = true;
_L1:
        if (flag)
        {
            return true;
        } else
        {
            return super.onKeyDown(i, keyevent);
        }
        flag = false;
          goto _L1
    }

    public final boolean onMenuItemSelected(int i, MenuItem menuitem)
    {
        if (super.onMenuItemSelected(i, menuitem))
        {
            return true;
        }
        ActionBar actionbar = getSupportActionBar();
        if (menuitem.getItemId() == 0x102002c && actionbar != null && (actionbar.getDisplayOptions() & 4) != 0)
        {
            return onSupportNavigateUp();
        } else
        {
            return false;
        }
    }

    public void onPostCreate(Bundle bundle)
    {
        super.onPostCreate(bundle);
        if (mDelegate == null)
        {
            mDelegate = new AppCompatDelegateImpl(this, getWindow(), this);
        }
        mDelegate.onPostCreate$51662RJ4E9NMIP1FDTPIUGJLDPI6OP9R55B0____0();
    }

    public void onPostResume()
    {
        super.onPostResume();
        if (mDelegate == null)
        {
            mDelegate = new AppCompatDelegateImpl(this, getWindow(), this);
        }
        mDelegate.onPostResume();
    }

    public void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        if (mDelegate == null)
        {
            mDelegate = new AppCompatDelegateImpl(this, getWindow(), this);
        }
        mDelegate.onSaveInstanceState(bundle);
    }

    public void onStart()
    {
        super.onStart();
        if (mDelegate == null)
        {
            mDelegate = new AppCompatDelegateImpl(this, getWindow(), this);
        }
        mDelegate.onStart();
    }

    public void onStop()
    {
        super.onStop();
        if (mDelegate == null)
        {
            mDelegate = new AppCompatDelegateImpl(this, getWindow(), this);
        }
        mDelegate.onStop();
    }

    public final void onSupportActionModeFinished$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TR6IPBN5T0M6T39DTN4QRR4CKTIILG_0()
    {
    }

    public final void onSupportActionModeStarted$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TR6IPBN5T0M6T39DTN4QRR4CKTIILG_0()
    {
    }

    protected void onTitleChanged(CharSequence charsequence, int i)
    {
        super.onTitleChanged(charsequence, i);
        if (mDelegate == null)
        {
            mDelegate = new AppCompatDelegateImpl(this, getWindow(), this);
        }
        mDelegate.setTitle(charsequence);
    }

    public final ActionMode onWindowStartingSupportActionMode$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TR6IPBN5T0M6T39DTN4QRR4CKI46OBCDHH62ORB7CKKOOBECHP6UQB45TPNAS3GDTP78BRM6SNNCQB5ESNK2ORKD5NMSJBFCHIJM___0()
    {
        return null;
    }

    public void openOptionsMenu()
    {
        ActionBar actionbar = getSupportActionBar();
        if (getWindow().hasFeature(0) && (actionbar == null || !actionbar.openOptionsMenu()))
        {
            super.openOptionsMenu();
        }
    }

    public void setContentView(int i)
    {
        if (mDelegate == null)
        {
            mDelegate = new AppCompatDelegateImpl(this, getWindow(), this);
        }
        mDelegate.setContentView(i);
    }

    public void setContentView(View view)
    {
        if (mDelegate == null)
        {
            mDelegate = new AppCompatDelegateImpl(this, getWindow(), this);
        }
        mDelegate.setContentView(view);
    }

    public void setContentView(View view, android.view.ViewGroup.LayoutParams layoutparams)
    {
        if (mDelegate == null)
        {
            mDelegate = new AppCompatDelegateImpl(this, getWindow(), this);
        }
        mDelegate.setContentView(view, layoutparams);
    }

    public final void setSupportActionBar(Toolbar toolbar)
    {
        if (mDelegate == null)
        {
            mDelegate = new AppCompatDelegateImpl(this, getWindow(), this);
        }
        mDelegate.setSupportActionBar(toolbar);
    }

    public void setTheme(int i)
    {
        super.setTheme(i);
        mThemeId = i;
    }

    public final void supportInvalidateOptionsMenu()
    {
        if (mDelegate == null)
        {
            mDelegate = new AppCompatDelegateImpl(this, getWindow(), this);
        }
        mDelegate.invalidateOptionsMenu();
    }
}
