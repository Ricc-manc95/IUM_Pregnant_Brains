// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.store;

import android.arch.lifecycle.FullLifecycleObserver;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import com.google.android.apps.calendar.util.api.ListenableFutureCache;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.android.apps.calendar.util.concurrent.Subscription;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.store:
//            CalendarStoreInvalidator, CalendarContentStore

public final class CalendarStoreInvalidatorImpl
    implements FullLifecycleObserver, CalendarStoreInvalidator
{

    private final ListenableFutureCache calendarsCache;
    private boolean invalidateOnResume;
    private final LifecycleOwner lifecycleOwner;
    private final ListenableFutureCache settingsCache;
    public final CalendarContentStore store;
    private Subscription subscription;
    private final ListenableFutureCache taskCache;
    private final ObservableReference timeZoneObservable;
    public Set visibleCalendarIds;

    public CalendarStoreInvalidatorImpl(boolean flag, CalendarContentStore calendarcontentstore, ListenableFutureCache listenablefuturecache, ListenableFutureCache listenablefuturecache1, ListenableFutureCache listenablefuturecache2, ObservableReference observablereference, LifecycleOwner lifecycleowner)
    {
        visibleCalendarIds = new HashSet();
        if (!flag)
        {
            throw new IllegalStateException();
        } else
        {
            store = calendarcontentstore;
            calendarsCache = listenablefuturecache;
            settingsCache = listenablefuturecache1;
            taskCache = listenablefuturecache2;
            timeZoneObservable = observablereference;
            lifecycleOwner = lifecycleowner;
            lifecycleowner.getLifecycle().addObserver(this);
            return;
        }
    }

    public final void onCreate(LifecycleOwner lifecycleowner)
    {
    }

    public final void onDestroy$51662RJ4E9NMIP1FC5P66Q1FDHKMCPB3F5HMOP9F9HKMCPB3F5HMOPAFETN6ASHR55B0____0()
    {
        lifecycleOwner.getLifecycle().removeObserver(this);
    }

    public final void onEventsChanged()
    {
        class .Lambda._cls4
            implements Consumer
        {

            public static final Consumer $instance = new .Lambda._cls4();

            public final void accept(Object obj)
            {
                ((CalendarContentStore.StoreTransaction)obj).invalidate();
            }


            private .Lambda._cls4()
            {
            }
        }

        store.updateStore(.Lambda._cls4..instance);
    }

    public final void onPause(LifecycleOwner lifecycleowner)
    {
        invalidateOnResume = true;
    }

    public final void onResume(LifecycleOwner lifecycleowner)
    {
        if (invalidateOnResume)
        {
            invalidateOnResume = false;
            store.updateStore(.Lambda._cls4..instance);
        }
    }

    public final void onStart(LifecycleOwner lifecycleowner)
    {
        class .Lambda._cls0
            implements Consumer
        {

            private final CalendarStoreInvalidatorImpl arg$1;

            public final void accept(Object obj)
            {
                CalendarStoreInvalidatorImpl calendarstoreinvalidatorimpl;
                boolean flag;
label0:
                {
                    boolean flag1 = false;
                    calendarstoreinvalidatorimpl = arg$1;
                    CalendarListEntry acalendarlistentry[] = (CalendarListEntry[])obj;
                    obj = new HashSet();
                    int j = acalendarlistentry.length;
                    for (int i = 0; i < j; i++)
                    {
                        CalendarListEntry calendarlistentry = acalendarlistentry[i];
                        if (calendarlistentry.isVisible() && calendarlistentry.isSyncEnabled())
                        {
                            ((Set) (obj)).add(calendarlistentry.getDescriptor().calendarKey);
                        }
                    }

                    Set set = calendarstoreinvalidatorimpl.visibleCalendarIds;
                    if (set != obj)
                    {
                        flag = flag1;
                        if (set == null)
                        {
                            break label0;
                        }
                        flag = flag1;
                        if (!set.equals(obj))
                        {
                            break label0;
                        }
                    }
                    flag = true;
                }
                if (!flag)
                {
                    calendarstoreinvalidatorimpl.visibleCalendarIds = ((Set) (obj));
                    class .Lambda._cls5
                        implements Consumer
                    {

                        public static final Consumer $instance = new .Lambda._cls5();

                        public final void accept(Object obj1)
                        {
                            ((CalendarContentStore.StoreTransaction)obj1).invalidate();
                        }


                        private .Lambda._cls5()
                        {
                        }
                    }

                    calendarstoreinvalidatorimpl.store.updateStore(.Lambda._cls5..instance);
                }
            }

            .Lambda._cls0()
            {
                arg$1 = CalendarStoreInvalidatorImpl.this;
            }
        }

        class .Lambda._cls1
            implements Consumer
        {

            private final CalendarStoreInvalidatorImpl arg$1;

            public final void accept(Object obj)
            {
                class .Lambda._cls8
                    implements Consumer
                {

                    public static final Consumer $instance = new .Lambda._cls8();

                    public final void accept(Object obj1)
                    {
                        ((CalendarContentStore.StoreTransaction)obj1).invalidate();
                    }


                        private .Lambda._cls8()
                        {
                        }
                }

                arg$1.store.updateStore(.Lambda._cls8..instance);
            }

            .Lambda._cls1()
            {
                arg$1 = CalendarStoreInvalidatorImpl.this;
            }
        }

        class .Lambda._cls2
            implements Consumer
        {

            private final CalendarStoreInvalidatorImpl arg$1;

            public final void accept(Object obj)
            {
                class .Lambda._cls7
                    implements Consumer
                {

                    public static final Consumer $instance = new .Lambda._cls7();

                    public final void accept(Object obj1)
                    {
                        ((CalendarContentStore.StoreTransaction)obj1).invalidate();
                    }


                        private .Lambda._cls7()
                        {
                        }
                }

                arg$1.store.updateStore(.Lambda._cls7..instance);
            }

            .Lambda._cls2()
            {
                arg$1 = CalendarStoreInvalidatorImpl.this;
            }
        }

        class .Lambda._cls3
            implements Consumer
        {

            private final CalendarStoreInvalidatorImpl arg$1;

            public final void accept(Object obj)
            {
                class .Lambda._cls6
                    implements Consumer
                {

                    public static final Consumer $instance = new .Lambda._cls6();

                    public final void accept(Object obj1)
                    {
                        ((CalendarContentStore.StoreTransaction)obj1).invalidate();
                    }


                        private .Lambda._cls6()
                        {
                        }
                }

                arg$1.store.updateStore(.Lambda._cls6..instance);
            }

            .Lambda._cls3()
            {
                arg$1 = CalendarStoreInvalidatorImpl.this;
            }
        }

        subscription = new com.google.android.apps.calendar.util.concurrent.Subscription..Lambda._cls0(Arrays.asList(new Subscription[] {
            calendarsCache.subscribe(new .Lambda._cls0(), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE, true), settingsCache.subscribe(new .Lambda._cls1(), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE, false), taskCache.subscribe(new .Lambda._cls2(), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE, false), timeZoneObservable.subscribe(new .Lambda._cls3(), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE, false)
        }));
    }

    public final void onStop(LifecycleOwner lifecycleowner)
    {
        invalidateOnResume = false;
        subscription.cancel(false);
        subscription = null;
    }
}
