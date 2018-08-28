// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.android.calendar.api.habit.HabitModifications;
import com.google.android.calendar.common.view.fab.FloatingActionButton;
import com.google.android.calendar.material.Material;

public final class GrooveSummaryView extends LinearLayout
{

    public static int waitingTextIndex = 0;
    public TextView calendarNameTextView;
    public LinearLayout contractFrameView;
    public LinearLayout contractView;
    public View divider;
    public TextView durationTextView;
    public LinearLayout editButtonContainer;
    public FloatingActionButton fab;
    public TextView frequencyTextView;
    public HabitModifications habitModifications;
    public Handler handler;
    public ProgressBar progressBar;
    public TextView titleView;
    public Runnable waitingTextUpdateTask;
    public TextView waitingTextView;

    public GrooveSummaryView(final FragmentActivity activity)
    {
        super(activity);
        waitingTextUpdateTask = new _cls1();
        LayoutInflater.from(activity).inflate(0x7f050088, this);
        setOrientation(1);
        titleView = (TextView)findViewById(0x7f1001f0);
        contractFrameView = (LinearLayout)findViewById(0x7f1001f1);
        contractView = (LinearLayout)findViewById(0x7f1001f2);
        progressBar = (ProgressBar)findViewById(0x7f10011d);
        waitingTextView = (TextView)findViewById(0x7f1001f8);
        frequencyTextView = (TextView)findViewById(0x7f1001f4);
        durationTextView = (TextView)findViewById(0x7f1001f5);
        editButtonContainer = (LinearLayout)findViewById(0x7f1001f6);
        editButtonContainer.setOnClickListener(new _cls2());
        calendarNameTextView = (TextView)findViewById(0x7f100189);
        divider = findViewById(0x7f100145);
        fab = (FloatingActionButton)findViewById(0x7f100146);
        fab.setOnClickListener(new _cls3());
        fab.setSize(1);
        if (Material.robotoMedium != null)
        {
            activity = Material.robotoMedium;
        } else
        {
            activity = Typeface.create("sans-serif-medium", 0);
            Material.robotoMedium = activity;
        }
        ((TextView)findViewById(0x7f1001f7)).setTypeface(activity);
        ((TextView)findViewById(0x7f1001f3)).setTypeface(activity);
    }

    protected final void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        fab.setClickable(true);
        titleView.setVisibility(0);
        contractFrameView.setVisibility(0);
        divider.setVisibility(0);
        progressBar.setVisibility(8);
        waitingTextView.setVisibility(8);
    }

    protected final void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        if (handler != null)
        {
            handler.removeCallbacks(waitingTextUpdateTask);
        }
    }

    public final void setFabColor(int i)
    {
        if (i == -1)
        {
            fab.setIcon(0x7f0201e3);
            progressBar.getIndeterminateDrawable().setColorFilter(getResources().getColor(0x7f0d021a), android.graphics.PorterDuff.Mode.SRC_ATOP);
        } else
        {
            fab.setIcon(0x7f0201e4);
            progressBar.getIndeterminateDrawable().setColorFilter(null);
        }
        fab.setColor(i);
    }


    private class _cls1
        implements Runnable
    {

        private final GrooveSummaryView this$0;

        public final void run()
        {
            String as[] = getResources().getStringArray(0x7f0b0030);
            waitingTextView.setText(as[GrooveSummaryView.waitingTextIndex]);
            GrooveSummaryView.waitingTextIndex++;
            if (GrooveSummaryView.waitingTextIndex < as.length)
            {
                handler.postDelayed(waitingTextUpdateTask, 2500L);
            }
        }

        _cls1()
        {
            this$0 = GrooveSummaryView.this;
            super();
        }
    }


    private class _cls2
        implements android.view.View.OnClickListener
    {

        private final GrooveSummaryView this$0;
        private final FragmentActivity val$activity;

        public final void onClick(View view)
        {
            view = getContext();
            if (view != null)
            {
                AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
                if (analyticslogger == null)
                {
                    throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
                }
                ((AnalyticsLogger)analyticslogger).trackEvent(view, "groove", "more_options_clicked", "", null);
            }
            view = activity.mFragments.mHost.mFragmentManager;
            HostDialog.showWithChildFragment(activity, view, GrooveEditScreenController.createGroove(new GrooveEditScreenController(), habitModifications, true));
        }

        _cls2()
        {
            this$0 = GrooveSummaryView.this;
            activity = fragmentactivity;
            super();
        }
    }


    private class _cls3
        implements android.view.View.OnClickListener
    {

        private final GrooveSummaryView this$0;

        public final void onClick(View view)
        {
            if (getContext() instanceof Listener)
            {
                fab.setClickable(false);
                titleView.setVisibility(4);
                contractFrameView.setVisibility(8);
                progressBar.setVisibility(0);
                waitingTextView.setVisibility(0);
                divider.setVisibility(8);
                fab.setIcon(0);
                GrooveSummaryView.waitingTextIndex = 0;
                handler = new Handler();
                handler.post(waitingTextUpdateTask);
                view = GrooveSummaryView.this;
                view.announceForAccessibility(TextUtils.join(" ", view.getResources().getStringArray(0x7f0b0030)));
                ((Listener)getContext()).onConfirmContract();
            }
        }

        _cls3()
        {
            this$0 = GrooveSummaryView.this;
            super();
        }

        private class Listener
        {

            public abstract void onConfirmContract();
        }

    }

}
