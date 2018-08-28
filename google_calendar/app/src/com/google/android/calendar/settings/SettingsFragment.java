// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;
import android.view.View;
import android.view.Window;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.concurrent.CancelableFutureCallback;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.settings.calendar.ColorDialogPreference;
import com.google.android.calendar.settings.calendar.ColorPreferenceDialogFragment;
import com.google.android.calendar.utils.a11y.A11yHelper;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

// Referenced classes of package com.google.android.calendar.settings:
//            ViewModelLoader, SettingsShims, SettingsActivity

public abstract class SettingsFragment extends PreferenceFragmentCompat
{

    public final String analyticsCategory;
    private CancelableFutureCallback modelCallback;
    public final String tag = LogUtils.getLogTag(getClass());

    public SettingsFragment(String s)
    {
        analyticsCategory = s;
    }

    public final void loadModel(final Consumer receiver)
    {
        modelCallback = new CancelableFutureCallback(new _cls1());
        receiver = ViewModelLoader.getViewModelAsync(getContext());
        CancelableFutureCallback cancelablefuturecallback = modelCallback;
        com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0 _lcls0 = new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.MAIN);
        if (cancelablefuturecallback == null)
        {
            throw new NullPointerException();
        } else
        {
            receiver.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(receiver, cancelablefuturecallback), _lcls0);
            return;
        }
    }

    protected final android.support.v7.widget.RecyclerView.Adapter onCreateAdapter(PreferenceScreen preferencescreen)
    {
        if (android.os.Build.VERSION.SDK_INT > 23)
        {
            return super.onCreateAdapter(preferencescreen);
        } else
        {
            return new _cls2(preferencescreen);
        }
    }

    public void onDestroy()
    {
        modelCallback.nestedFutureCallbackReference.set(null);
        super.onDestroy();
    }

    public final void onDisplayPreferenceDialog(Preference preference)
    {
        if (preference instanceof ColorDialogPreference)
        {
            preference = preference.mKey;
            ColorPreferenceDialogFragment colorpreferencedialogfragment = new ColorPreferenceDialogFragment();
            Bundle bundle = new Bundle(1);
            bundle.putString("key", preference);
            colorpreferencedialogfragment.setArguments(bundle);
            colorpreferencedialogfragment.setTargetFragment(this, 0);
            colorpreferencedialogfragment.show(super.mFragmentManager, "android.support.v7.preference.PreferenceFragment.DIALOG");
            return;
        } else
        {
            super.onDisplayPreferenceDialog(preference);
            return;
        }
    }

    public boolean onStartHelp(AppCompatActivity appcompatactivity)
    {
        SettingsShims.instance.launchHelpAndFeedback(appcompatactivity, requireContext().getResources().getString(0x7f130443));
        return true;
    }

    public final void setActionBarTitle(String s)
    {
        Object obj;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        obj = (SettingsActivity)obj;
        if (obj != null)
        {
            ActionBar actionbar = ((AppCompatActivity) (obj)).getSupportActionBar();
            if (actionbar != null)
            {
                actionbar.setTitle(s);
            }
            A11yHelper.getInstance();
            if (A11yHelper.isAccessibilityEnabled(((Context) (obj))))
            {
                if (((SettingsActivity) (obj)).readFragmentActionBarTitle)
                {
                    AccessibilityManager accessibilitymanager = (AccessibilityManager)((SettingsActivity) (obj)).getSystemService("accessibility");
                    AccessibilityEvent accessibilityevent = AccessibilityEvent.obtain();
                    accessibilityevent.setEventType(16384);
                    accessibilityevent.getText().add(s);
                    accessibilitymanager.sendAccessibilityEvent(accessibilityevent);
                }
                obj.readFragmentActionBarTitle = true;
                ((SettingsActivity) (obj)).getWindow().getDecorView().sendAccessibilityEvent(32);
            }
        }
    }

    private class _cls1
        implements FutureCallback
    {

        private final SettingsFragment this$0;
        private final Consumer val$receiver;

        public final void onFailure(Throwable throwable)
        {
            if (!(throwable instanceof CancellationException))
            {
                Log.wtf(tag, "Unable to load preferences fragment.", throwable);
                throwable = getContext();
                if (throwable != null)
                {
                    AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
                    if (analyticslogger == null)
                    {
                        throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
                    }
                    ((AnalyticsLogger)analyticslogger).trackEvent(throwable, "settings_fragment_model_loaded_failure", analyticsCategory);
                }
            }
        }

        public final void onSuccess(Object obj)
        {
            obj = (HomeViewModel)obj;
            receiver.accept(obj);
            obj = getContext();
            if (obj != null)
            {
                AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
                if (analyticslogger == null)
                {
                    throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
                }
                ((AnalyticsLogger)analyticslogger).trackEvent(((Context) (obj)), "settings_fragment_model_loaded", analyticsCategory);
            }
        }

        _cls1()
        {
            this$0 = SettingsFragment.this;
            receiver = consumer;
            super();
        }
    }


    private class _cls2 extends PreferenceGroupAdapter
    {

        public final void onBindViewHolder(android.support.v7.widget.RecyclerView.ViewHolder viewholder, int i, List list)
        {
            viewholder = (PreferenceViewHolder)viewholder;
            View view = viewholder.findViewById(0x1020006);
            if (view != null && view.getClass().getSimpleName().equals("PreferenceImageView") && (view.getParent() instanceof ViewGroup))
            {
                ((ViewGroup)view.getParent()).setId(0x102003e);
            }
            super.onBindViewHolder(viewholder, i, list);
        }

        _cls2(PreferenceGroup preferencegroup)
        {
            super(preferencegroup);
        }
    }

}
