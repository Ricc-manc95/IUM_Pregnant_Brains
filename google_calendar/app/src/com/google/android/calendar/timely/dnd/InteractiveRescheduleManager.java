// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.dnd;

import android.app.Activity;
import android.content.Context;
import android.support.design.snackbar.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.timebox.TimeRange;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.concurrent.Previewable;
import com.google.android.calendar.Rescheduler;
import com.google.android.calendar.Utils;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.utils.snackbar.SnackbarUtils;
import com.google.common.util.concurrent.AbstractCatchingFuture;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ForwardingFluentFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.TimeZone;
import java.util.concurrent.CancellationException;

public class InteractiveRescheduleManager
{
    public static interface Callback
    {

        public abstract void onCancel();

        public abstract void onFailure();

        public abstract void onSuccess();
    }

    static final class RescheduleSnackbar
    {

        public final ListenableFuture rescheduleConfig;
        public final Snackbar snackbar;

        RescheduleSnackbar(ListenableFuture listenablefuture, Snackbar snackbar1)
        {
            rescheduleConfig = listenablefuture;
            snackbar = snackbar1;
        }
    }


    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timely/dnd/InteractiveRescheduleManager);
    public final Activity activity;
    public final TimelineItem rescheduledItem;
    public final Rescheduler rescheduler;

    public InteractiveRescheduleManager(Activity activity1, Rescheduler rescheduler1, TimelineItem timelineitem)
    {
        activity = activity1;
        rescheduler = rescheduler1;
        rescheduledItem = timelineitem;
    }

    public static boolean checkReschedulableWithFeedback(Activity activity1, TimelineItem timelineitem)
    {
        boolean flag = false;
        timelineitem = Rescheduler.isReschedulable(activity1.getResources(), timelineitem);
        if (timelineitem != null)
        {
            SnackbarUtils.showSnackbar(activity1, timelineitem, 0, null, null, null);
        }
        if (timelineitem == null)
        {
            flag = true;
        }
        return flag;
    }

    public static String getDndAnalyticsType(TimelineItem timelineitem)
    {
        return (String)timelineitem.perform(new _cls2(), new Void[0]);
    }

    static final void lambda$createRescheduleOrUndoSnackbar$7$InteractiveRescheduleManager$51662RJ4E9NMIP1FEPKMATPFAPKMATPR55B0____0()
    {
    }

    static final ListenableFuture lambda$reschedule$1$InteractiveRescheduleManager(RescheduleSnackbar reschedulesnackbar)
        throws Exception
    {
        return reschedulesnackbar.rescheduleConfig;
    }

    static final void lambda$reschedule$5$InteractiveRescheduleManager(RescheduleSnackbar reschedulesnackbar)
    {
        reschedulesnackbar.snackbar.dismiss();
    }

    static final ListenableFuture lambda$reschedule$6$InteractiveRescheduleManager(ListenableFuture listenablefuture, ListenableFuture listenablefuture1)
    {
        class .Lambda._cls8
            implements Consumer
        {

            public static final Consumer $instance = new .Lambda._cls8();

            public final void accept(Object obj)
            {
                InteractiveRescheduleManager.lambda$reschedule$5$InteractiveRescheduleManager((RescheduleSnackbar)obj);
            }


            private .Lambda._cls8()
            {
            }
        }

        com.google.common.util.concurrent.FutureCallback futurecallback = LogUtils.newFailureLoggingCallback(.Lambda._cls8..instance, TAG, "Could not expedite rescheduling because snackbar creation failed", new Object[0]);
        com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0 _lcls0 = new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.MAIN);
        if (futurecallback == null)
        {
            throw new NullPointerException();
        } else
        {
            listenablefuture.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(listenablefuture, futurecallback), _lcls0);
            return listenablefuture1;
        }
    }

    public final Previewable reschedule(long l, Callback callback)
    {
        TimelineItem timelineitem = null;
        if (rescheduledItem.getStartMillis() == l)
        {
            callback.onSuccess();
            timelineitem = rescheduledItem;
            if (timelineitem == null)
            {
                callback = com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
            } else
            {
                callback = new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(timelineitem);
            }
            callback = new Previewable(timelineitem, new com.google.common.base.Suppliers.SupplierOfInstance(callback));
        } else
        {
            Object obj1 = rescheduler;
            obj1 = Rescheduler.isReschedulableTo(((Rescheduler) (obj1)).context.getResources(), ((Rescheduler) (obj1)).rescheduledItem, l);
            if (obj1 != null)
            {
                callback.onFailure();
                Activity activity1 = activity;
                callback = timelineitem;
                if (obj1 != null)
                {
                    SnackbarUtils.showSnackbar(activity1, ((String) (obj1)), 0, null, null, null);
                    return null;
                }
            } else
            {
                Object obj2 = rescheduler;
                Object obj = ((Rescheduler) (obj2)).context;
                obj2 = ((Rescheduler) (obj2)).rescheduledItem;
                boolean flag;
                if (Rescheduler.isReschedulableTo(((Context) (obj)).getResources(), ((TimelineItem) (obj2)), l) == null)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    throw new IllegalArgumentException();
                }
                long l1 = l - ((TimelineItem) (obj2)).getStartMillis();
                obj2 = (TimelineItem)((TimelineItem) (obj2)).perform(new com.google.android.calendar.Rescheduler._cls3(TimeRange.newNonAllDay(TimeZone.getTimeZone(Utils.getTimeZoneId(((Context) (obj)))), ((TimelineItem) (obj2)).getStartMillis() + l1, l1 + ((TimelineItem) (obj2)).getEndMillis())), new Void[0]);
                obj = rescheduler;
                FluentFuture fluentfuture;
                if (((Rescheduler) (obj)).rescheduledEvent == null)
                {
                    class .Lambda._cls0
                        implements AsyncFunction
                    {

                        private final InteractiveRescheduleManager arg$1;

                        public final ListenableFuture apply(Object obj3)
                        {
                            InteractiveRescheduleManager interactivereschedulemanager = arg$1;
                            obj3 = (Event)obj3;
                            Object obj4;
                            boolean flag1;
                            if (obj3 != null && AttendeeUtils.hasGuests(((Event) (obj3))) && AccountUtil.isGoogleAccount(((Event) (obj3)).getCalendar().account))
                            {
                                flag1 = true;
                            } else
                            {
                                flag1 = false;
                            }
                            obj4 = Features.instance;
                            if (obj4 == null)
                            {
                                throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
                            }
                            ((FeatureConfig)obj4).notify_guests();
                            if (!flag1)
                            {
                                if (flag1)
                                {
                                    obj3 = com.google.android.calendar.api.event.GooglePrivateData.GuestNotification.UNDECIDED;
                                } else
                                {
                                    obj3 = null;
                                }
                                if (obj3 == null)
                                {
                                    return com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
                                } else
                                {
                                    return new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(obj3);
                                }
                            }
                            obj4 = new SettableFuture();
                            if (obj3 == null)
                            {
                                ((AbstractFuture) (obj4)).cancel(true);
                                return ((ListenableFuture) (obj4));
                            } else
                            {
                                String s = GuestNotificationDialogUtils.maybeExtendDialogText(((Event) (obj3)), interactivereschedulemanager.activity, interactivereschedulemanager.activity.getString(0x7f1302e1));
                                return GuestNotificationDialogUtils.showNotificationDialogAsync(((Event) (obj3)), interactivereschedulemanager.activity, s, "update_dnd");
                            }
                        }

            .Lambda._cls0()
            {
                arg$1 = InteractiveRescheduleManager.this;
            }
                    }

                    class .Lambda._cls1
                        implements Function
                    {

                        private final InteractiveRescheduleManager arg$1;
                        private final long arg$2;

                        public final Object apply(final Object rescheduleConfigValue)
                        {
                            InteractiveRescheduleManager interactivereschedulemanager = arg$1;
                            long l3 = arg$2;
                            rescheduleConfigValue = (com.google.android.calendar.api.event.GooglePrivateData.GuestNotification)rescheduleConfigValue;
                            class .Lambda._cls7
                                implements android.view.View.OnClickListener
                            {

                                public static final android.view.View.OnClickListener $instance = new .Lambda._cls7();

                                public final void onClick(View view)
                                {
                                    InteractiveRescheduleManager.lambda$createRescheduleOrUndoSnackbar$7$InteractiveRescheduleManager$51662RJ4E9NMIP1FEPKMATPFAPKMATPR55B0____0();
                                }


                                    private .Lambda._cls7()
                                    {
                                    }
                            }

                            String s;
                            final SettableFuture rescheduleConfigFuture;
                            int i;
                            long l2;
                            if (rescheduleConfigValue != null && rescheduleConfigValue != com.google.android.calendar.api.event.GooglePrivateData.GuestNotification.DISABLED)
                            {
                                i = 1;
                            } else
                            {
                                i = 0;
                            }
                            if (Clock.mockedTimestamp > 0L)
                            {
                                l2 = Clock.mockedTimestamp;
                            } else
                            {
                                l2 = System.currentTimeMillis();
                            }
                            s = Utils.getDisplayedDatetime(l3, l3, l2, Utils.getTimeZoneId(interactivereschedulemanager.activity), false, false, interactivereschedulemanager.activity);
                            rescheduleConfigFuture = interactivereschedulemanager.activity;
                            if (i != 0)
                            {
                                i = 0x7f1303f5;
                            } else
                            {
                                i = 0x7f1303f4;
                            }
                            s = rescheduleConfigFuture.getString(i, new Object[] {
                                s
                            });
                            rescheduleConfigFuture = new SettableFuture();
                            return new RescheduleSnackbar(rescheduleConfigFuture, SnackbarUtils.showSnackbar(interactivereschedulemanager.activity, s, 0, interactivereschedulemanager.activity.getString(0x7f1303f6), .Lambda._cls7..instance, new _cls1()));
                        }

            .Lambda._cls1(long l)
            {
                arg$1 = InteractiveRescheduleManager.this;
                arg$2 = l;
            }

                        private class _cls1 extends android.support.design.widget.Snackbar.Callback
                        {

                            private final SettableFuture val$rescheduleConfigFuture;
                            private final com.google.android.calendar.api.event.GooglePrivateData.GuestNotification val$rescheduleConfigValue;

                            public final void onDismissed(Snackbar snackbar, int i)
                            {
                                if (i == 1)
                                {
                                    rescheduleConfigFuture.cancel(false);
                                    return;
                                }
                                SettableFuture settablefuture = rescheduleConfigFuture;
                                if (rescheduleConfigValue == null)
                                {
                                    snackbar = com.google.android.calendar.api.event.GooglePrivateData.GuestNotification.UNDECIDED;
                                } else
                                {
                                    snackbar = rescheduleConfigValue;
                                }
                                settablefuture.set(snackbar);
                            }

                            public final volatile void onDismissed(Object obj, int i)
                            {
                                onDismissed((Snackbar)obj, i);
                            }

                            _cls1()
                            {
                                rescheduleConfigFuture = settablefuture;
                                rescheduleConfigValue = guestnotification;
                                super();
                            }
                        }

                    }

                    class .Lambda._cls6
                        implements Supplier
                    {

                        private final ListenableFuture arg$1;
                        private final ListenableFuture arg$2;

                        public final Object get()
                        {
                            return InteractiveRescheduleManager.lambda$reschedule$6$InteractiveRescheduleManager(arg$1, arg$2);
                        }

            .Lambda._cls6(ListenableFuture listenablefuture, ListenableFuture listenablefuture1)
            {
                arg$1 = listenablefuture;
                arg$2 = listenablefuture1;
            }
                    }

                    class .Lambda._cls2
                        implements AsyncFunction
                    {

                        public static final AsyncFunction $instance = new .Lambda._cls2();

                        public final ListenableFuture apply(Object obj3)
                        {
                            return InteractiveRescheduleManager.lambda$reschedule$1$InteractiveRescheduleManager((RescheduleSnackbar)obj3);
                        }


            private .Lambda._cls2()
            {
            }
                    }

                    class .Lambda._cls3
                        implements AsyncFunction
                    {

                        private final InteractiveRescheduleManager arg$1;
                        private final long arg$2;

                        public final ListenableFuture apply(Object obj3)
                        {
                            Object obj4 = arg$1;
                            long l2 = arg$2;
                            obj3 = (com.google.android.calendar.api.event.GooglePrivateData.GuestNotification)obj3;
                            obj4 = ((InteractiveRescheduleManager) (obj4)).rescheduler;
                            return (ListenableFuture)((Rescheduler) (obj4)).rescheduledItem.perform(new com.google.android.calendar.Rescheduler.TimelineItemRescheduleOperation(((Rescheduler) (obj4)), ((com.google.android.calendar.api.event.GooglePrivateData.GuestNotification) (obj3)), l2), new Void[0]);
                        }

            .Lambda._cls3(long l)
            {
                arg$1 = InteractiveRescheduleManager.this;
                arg$2 = l;
            }
                    }

                    class .Lambda._cls4
                        implements Function
                    {

                        private final InteractiveRescheduleManager arg$1;
                        private final Callback arg$2;
                        private final TimelineItem arg$3;

                        public final Object apply(Object obj3)
                        {
                            InteractiveRescheduleManager interactivereschedulemanager = arg$1;
                            Callback callback1 = arg$2;
                            TimelineItem timelineitem1 = arg$3;
                            obj3 = (com.google.android.calendar.Rescheduler.RescheduleResult)obj3;
                            Activity activity2 = interactivereschedulemanager.activity;
                            String s = ((com.google.android.calendar.Rescheduler.RescheduleResult) (obj3)).feedback;
                            if (s != null)
                            {
                                SnackbarUtils.showSnackbar(activity2, s, 0, null, null, null);
                            }
                            if (((com.google.android.calendar.Rescheduler.RescheduleResult) (obj3)).successful)
                            {
                                callback1.onSuccess();
                                return timelineitem1;
                            } else
                            {
                                callback1.onFailure();
                                return interactivereschedulemanager.rescheduledItem;
                            }
                        }

            .Lambda._cls4(Callback callback, TimelineItem timelineitem)
            {
                arg$1 = InteractiveRescheduleManager.this;
                arg$2 = callback;
                arg$3 = timelineitem;
            }
                    }

                    class .Lambda._cls5
                        implements Function
                    {

                        private final InteractiveRescheduleManager arg$1;
                        private final Callback arg$2;

                        public final Object apply(Object obj3)
                        {
                            obj3 = arg$1;
                            arg$2.onCancel();
                            return ((InteractiveRescheduleManager) (obj3)).rescheduledItem;
                        }

            .Lambda._cls5(Callback callback)
            {
                arg$1 = InteractiveRescheduleManager.this;
                arg$2 = callback;
            }
                    }

                    if (true)
                    {
                        obj = com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
                    } else
                    {
                        obj = new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(null);
                    }
                    if (obj instanceof FluentFuture)
                    {
                        obj = (FluentFuture)obj;
                    } else
                    {
                        obj = new ForwardingFluentFuture(((ListenableFuture) (obj)));
                    }
                } else
                {
                    obj = ((Rescheduler) (obj)).rescheduledEvent;
                }
                fluentfuture = (FluentFuture)AbstractTransformFuture.create((FluentFuture)AbstractTransformFuture.create(((ListenableFuture) (obj)), new .Lambda._cls0(), new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.MAIN)), new .Lambda._cls1(l), new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.MAIN));
                if (fluentfuture instanceof FluentFuture)
                {
                    obj = (FluentFuture)fluentfuture;
                } else
                {
                    obj = new ForwardingFluentFuture(fluentfuture);
                }
                return new Previewable(obj2, new .Lambda._cls6(fluentfuture, (FluentFuture)AbstractCatchingFuture.create((FluentFuture)AbstractTransformFuture.create((FluentFuture)AbstractTransformFuture.create((FluentFuture)AbstractTransformFuture.create(((ListenableFuture) (obj)), .Lambda._cls2..instance, com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE), new .Lambda._cls3(l), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE), new .Lambda._cls4(callback, ((TimelineItem) (obj2))), CalendarExecutor.MAIN), java/util/concurrent/CancellationException, new .Lambda._cls5(callback), new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.MAIN))));
            }
        }
        return callback;
    }


    private class _cls2 extends TimelineItemOperation
    {

        public final Object onAny(TimelineItem timelineitem, Object aobj[])
        {
            return "unknown";
        }

        public final Object onAnyEvent(TimelineEvent timelineevent, Object aobj[])
        {
            return "event";
        }

        public final Object onAnyReminder(TimelineTaskType timelinetasktype, Object aobj[])
        {
            return "reminder";
        }

        public final Object onGoalEvent(TimelineGroove timelinegroove, Object aobj[])
        {
            return "goal";
        }

        _cls2()
        {
        }
    }

}
