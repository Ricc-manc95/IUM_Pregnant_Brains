// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.interaction.helper;

import android.support.v4.view.ViewCompat;
import android.view.View;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.timely.interaction.InteractionTracker;
import com.google.common.util.concurrent.FluentFuture;

public class DelayedUpdateHelper
    implements com.google.android.calendar.timely.interaction.InteractionTracker.Listener
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timely/interaction/helper/DelayedUpdateHelper);
    public Runnable delayedUpdate;
    private boolean interactionInProgress;
    public boolean listening;
    public final View view;

    public DelayedUpdateHelper(View view1)
    {
        CalendarExecutor.MAIN.checkOnThread();
        view = view1;
        if (ViewCompat.isAttachedToWindow(view1))
        {
            InteractionTracker.getInstance().addListener(this);
            listening = true;
        } else
        {
            listening = false;
        }
        view.addOnAttachStateChangeListener(new _cls1());
    }

    public static transient void logFishfoodInfo(String s, Object aobj[])
    {
        s = Features.instance;
        if (s == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        } else
        {
            ((FeatureConfig)s).fishfood_debug();
            return;
        }
    }

    public final void onInteractionEnd()
    {
        interactionInProgress = false;
        class .Lambda._cls1
            implements Runnable
        {

            private final DelayedUpdateHelper arg$1;

            public final void run()
            {
                arg$1.updateIfIdleAndNeeded();
            }

            .Lambda._cls1()
            {
                arg$1 = DelayedUpdateHelper.this;
            }
        }

        FluentFuture fluentfuture = (FluentFuture)CalendarExecutor.MAIN.submit(new .Lambda._cls1());
    }

    public final void onInteractionStart()
    {
        interactionInProgress = true;
    }

    public final void postUpdate(Runnable runnable)
    {
        logFishfoodInfo("Post Update: %s", new Object[] {
            view
        });
        class .Lambda._cls0
            implements Runnable
        {

            private final DelayedUpdateHelper arg$1;
            private final Runnable arg$2;

            public final void run()
            {
                DelayedUpdateHelper delayedupdatehelper = arg$1;
                Runnable runnable1 = arg$2;
                CalendarExecutor.MAIN.checkOnThread();
                DelayedUpdateHelper.logFishfoodInfo("Attempt Update: %s", new Object[] {
                    delayedupdatehelper.view
                });
                delayedupdatehelper.delayedUpdate = runnable1;
                delayedupdatehelper.updateIfIdleAndNeeded();
            }

            .Lambda._cls0(Runnable runnable)
            {
                arg$1 = DelayedUpdateHelper.this;
                arg$2 = runnable;
            }
        }

        runnable = (FluentFuture)CalendarExecutor.MAIN.submit(new .Lambda._cls0(runnable));
    }

    public final void updateIfIdleAndNeeded()
    {
        CalendarExecutor.MAIN.checkOnThread();
        if (delayedUpdate != null && !interactionInProgress)
        {
            logFishfoodInfo("Perform Update: %s", new Object[] {
                view
            });
            delayedUpdate.run();
            delayedUpdate = null;
        } else
        if (delayedUpdate != null)
        {
            logFishfoodInfo("Delay Update: %s", new Object[] {
                view
            });
            return;
        }
    }


    private class _cls1
        implements android.view.View.OnAttachStateChangeListener
    {

        private final DelayedUpdateHelper this$0;

        public final void onViewAttachedToWindow(View view1)
        {
            boolean flag;
            if (!listening)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalStateException();
            } else
            {
                listening = true;
                InteractionTracker.getInstance().addListener(DelayedUpdateHelper.this);
                return;
            }
        }

        public final void onViewDetachedFromWindow(View view1)
        {
            if (!listening)
            {
                throw new IllegalStateException();
            } else
            {
                listening = false;
                InteractionTracker.getInstance().removeListener(DelayedUpdateHelper.this);
                return;
            }
        }

        _cls1()
        {
            this$0 = DelayedUpdateHelper.this;
            super();
        }
    }

}
