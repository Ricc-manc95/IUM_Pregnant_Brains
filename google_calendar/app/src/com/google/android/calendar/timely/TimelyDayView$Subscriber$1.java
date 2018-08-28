// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.view.View;
import com.google.android.apps.calendar.util.concurrent.Subscription;
import com.google.android.apps.calendar.util.concurrent.SubscriptionManager;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelyDayView, MonthData

final class this._cls1
    implements android.view.tener
{

    public final taSubscription this$1;

    public final void onViewAttachedToWindow(View view)
    {
        boolean flag;
        if (taSubscription == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException();
        }
        if (nthData != null)
        {
            view = this._cls1.this;
            MonthData monthdata = nthData;
            class .Lambda._cls0
                implements Consumer
            {

                private final TimelyDayView.Subscriber._cls1 arg$1;

                public final void accept(Object obj1)
                {
                    TimelyDayView.Subscriber._cls1 _lcls1 = arg$1;
                    obj1 = (MonthData)obj1;
                    _lcls1.this$1.this$0.onUpdate(((MonthData) (obj1)), _lcls1.this$1.day, false);
                }

            .Lambda._cls0()
            {
                arg$1 = TimelyDayView.Subscriber._cls1.this;
            }
            }

            .Lambda._cls0 _lcls0 = new .Lambda._cls0();
            Object obj = delayedUpdateHelper;
            obj.getClass();
            class .Lambda._cls1
                implements Executor
            {

                private final DelayedUpdateHelper arg$1;

                public final void execute(Runnable runnable)
                {
                    arg$1.postUpdate(runnable);
                }

            .Lambda._cls1(DelayedUpdateHelper delayedupdatehelper)
            {
                arg$1 = delayedupdatehelper;
            }
            }

            obj = new .Lambda._cls1(((DelayedUpdateHelper) (obj)));
            view.taSubscription = monthdata.subscriptions.subscribeFunction(new com.google.android.apps.calendar.util.concurrent.._cls0(_lcls0), ((Executor) (obj)));
        }
    }

    public final void onViewDetachedFromWindow(View view)
    {
        if (taSubscription != null)
        {
            taSubscription.cancel(false);
            taSubscription = null;
        }
    }

    anager(TimelyDayView timelydayview)
    {
        this$1 = this._cls1.this;
        super();
    }
}
