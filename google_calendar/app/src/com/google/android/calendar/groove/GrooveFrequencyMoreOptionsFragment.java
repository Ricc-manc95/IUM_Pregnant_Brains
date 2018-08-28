// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.view.ViewCompat;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.utils.SystemWindowInsetApplier;

// Referenced classes of package com.google.android.calendar.groove:
//            GrooveWizardFragment, BackButtonView

public class GrooveFrequencyMoreOptionsFragment extends GrooveWizardFragment
{
    static final class Frequency
    {

        public int instancesPerInterval;
        public int interval;
        public int stringResId;

        Frequency(int i, int j, int k)
        {
            interval = i;
            instancesPerInterval = j;
            stringResId = k;
        }
    }

    public static interface Listener
    {

        public abstract void onFrequencyMoreOptionsSelected(int i, int j);
    }


    private static final Frequency MONTHLY_FREQUENCIES[] = {
        new Frequency(3, 1, 0x7f1302c0), new Frequency(3, 2, 0x7f1302c5)
    };
    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/groove/GrooveFrequencyMoreOptionsFragment);
    private static final Frequency WEEKLY_FREQUENCIES[] = {
        new Frequency(2, 1, 0x7f1302c1), new Frequency(2, 2, 0x7f1302c6), new Frequency(2, 3, 0x7f1302c3), new Frequency(2, 4, 0x7f1302be), new Frequency(2, 5, 0x7f1302bd), new Frequency(2, 6, 0x7f1302c2), new Frequency(2, 7, 0x7f1301da)
    };
    private int backgroundColor;
    private final android.view.View.OnClickListener onFrequencyClickedListener = new _cls1();
    private ViewGroup optionsContainer;
    private int theme;
    private TextView titleView;

    public GrooveFrequencyMoreOptionsFragment()
    {
    }

    private final void addButtonsForFrequencies(ContextThemeWrapper contextthemewrapper, ViewGroup viewgroup, Frequency afrequency[])
    {
        int k = afrequency.length;
        int i = 0;
        while (i < k) 
        {
            Frequency frequency = afrequency[i];
            Button button = new Button(contextthemewrapper, null, 0x7f140151);
            Resources resources = contextthemewrapper.getResources();
            int j;
            if (theme == 0)
            {
                j = 0x7f0d011e;
            } else
            {
                j = 0x7f0d011d;
            }
            button.setTextColor(resources.getColor(j));
            button.setText(frequency.stringResId);
            button.setTag(frequency);
            button.setOnClickListener(onFrequencyClickedListener);
            viewgroup.addView(button);
            i++;
        }
    }

    public final void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        backgroundColor = getArguments().getInt("FREQUENCY_MORE_OPTIONS_BACKGROUND_COLOR");
        theme = getArguments().getInt("FREQUENCY_MORE_OPTIONS_THEME");
    }

    public final View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        viewgroup = layoutinflater.inflate(0x7f050082, viewgroup, false);
        mFrame = (ViewGroup)viewgroup.findViewById(0x7f100143);
        optionsContainer = (ViewGroup)viewgroup.findViewById(0x7f1001d8);
        layoutinflater = viewgroup.findViewById(0x7f10013e);
        bundle = new SystemWindowInsetApplier();
        bundle.addView(layoutinflater, 10, 2);
        ViewCompat.setOnApplyWindowInsetsListener(layoutinflater, bundle);
        adjustCardUi();
        layoutinflater = (BackButtonView)viewgroup.findViewById(0x7f10011e);
        layoutinflater.setOnClickListener(new _cls2());
        layoutinflater.setTheme(theme, false);
        titleView = (TextView)viewgroup.findViewById(0x7f1001d7);
        layoutinflater = titleView;
        bundle = viewgroup.getContext().getResources();
        int i;
        if (theme == 0)
        {
            i = 0x7f0d011e;
        } else
        {
            i = 0x7f0d011d;
        }
        layoutinflater.setTextColor(bundle.getColor(i));
        mFrame.setBackgroundColor(backgroundColor);
        setStatusBarTheme(theme);
        optionsContainer.removeAllViews();
        if (super.mHost == null)
        {
            layoutinflater = null;
        } else
        {
            layoutinflater = (FragmentActivity)super.mHost.mActivity;
        }
        layoutinflater = new ContextThemeWrapper(layoutinflater, 0x7f140151);
        addButtonsForFrequencies(layoutinflater, optionsContainer, MONTHLY_FREQUENCIES);
        addButtonsForFrequencies(layoutinflater, optionsContainer, WEEKLY_FREQUENCIES);
        return viewgroup;
    }

    public final void onResume()
    {
        super.onResume();
        titleView.requestFocus();
    }


    private class _cls1
        implements android.view.View.OnClickListener
    {

        private final GrooveFrequencyMoreOptionsFragment this$0;

        public final void onClick(View view)
        {
            view = ((View) (view.getTag()));
            if (!(view instanceof Frequency))
            {
                LogUtils.wtf(GrooveFrequencyMoreOptionsFragment.TAG, "Wrong tag type!", new Object[0]);
            } else
            {
                Frequency frequency = (Frequency)view;
                GrooveFrequencyMoreOptionsFragment groovefrequencymoreoptionsfragment = GrooveFrequencyMoreOptionsFragment.this;
                if (android.os.Build.VERSION.SDK_INT >= 23)
                {
                    if (((Fragment) (groovefrequencymoreoptionsfragment)).mHost == null)
                    {
                        view = null;
                    } else
                    {
                        view = (FragmentActivity)((Fragment) (groovefrequencymoreoptionsfragment)).mHost.mActivity;
                    }
                    groovefrequencymoreoptionsfragment.setExitTransition(AnimatorHelper.createSlideTransition(view, true).addTarget(0x7f100144));
                }
                if (((Fragment) (groovefrequencymoreoptionsfragment)).mHost == null)
                {
                    view = null;
                } else
                {
                    view = (FragmentActivity)((Fragment) (groovefrequencymoreoptionsfragment)).mHost.mActivity;
                }
                if (view instanceof Listener)
                {
                    if (((Fragment) (groovefrequencymoreoptionsfragment)).mHost == null)
                    {
                        view = null;
                    } else
                    {
                        view = (FragmentActivity)((Fragment) (groovefrequencymoreoptionsfragment)).mHost.mActivity;
                    }
                    ((Listener)view).onFrequencyMoreOptionsSelected(frequency.interval, frequency.instancesPerInterval);
                    return;
                }
            }
        }

        _cls1()
        {
            this$0 = GrooveFrequencyMoreOptionsFragment.this;
            super();
        }
    }


    private class _cls2
        implements android.view.View.OnClickListener
    {

        private final GrooveFrequencyMoreOptionsFragment this$0;

        public final void onClick(View view)
        {
            mFragmentManager.popBackStack();
        }

        _cls2()
        {
            this$0 = GrooveFrequencyMoreOptionsFragment.this;
            super();
        }
    }

}
