// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.view.View;
import com.google.android.apps.calendar.util.concurrent.Subscription;
import com.google.android.apps.calendar.util.concurrent.SubscriptionManager;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.timely.interaction.helper.DelayedUpdateHelper;
import java.util.concurrent.Executor;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelyDayView, MonthData

final class _cls1
{

    public Subscription dataSubscription;
    public int day;
    public MonthData monthData;
    public final TimelyDayView this$0;

    _cls1.this._cls1()
    {
        this$0 = TimelyDayView.this;
        super();
        class _cls1
            implements android.view.View.OnAttachStateChangeListener
        {

            public final TimelyDayView.Subscriber this$1;

            public final void onViewAttachedToWindow(View view)
            {
                boolean flag;
                if (dataSubscription == null)
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
                if (monthData != null)
                {
                    view = TimelyDayView.Subscriber.this;
                    MonthData monthdata = monthData;
                    class .Lambda._cls0
                        implements Consumer
                    {

                        private final _cls1 arg$1;

                        public final void accept(Object obj1)
                        {
                            _cls1 _lcls1 = arg$1;
                            obj1 = (MonthData)obj1;
                            _lcls1._fld1.this$0.onUpdate(((MonthData) (obj1)), _lcls1._fld1.day, false);
                        }

                        .Lambda._cls0()
                        {
                            arg$1 = _cls1.this;
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
                    view.dataSubscription = monthdata.subscriptions.subscribeFunction(new com.google.android.apps.calendar.util.concurrent.SubscriptionManager..Lambda._cls0(_lcls0), ((Executor) (obj)));
                }
            }

            public final void onViewDetachedFromWindow(View view)
            {
                if (dataSubscription != null)
                {
                    dataSubscription.cancel(false);
                    dataSubscription = null;
                }
            }

            _cls1(TimelyDayView timelydayview)
            {
                this$1 = TimelyDayView.Subscriber.this;
                super();
            }
        }

        addOnAttachStateChangeListener(new _cls1(TimelyDayView.this));
    }
}
