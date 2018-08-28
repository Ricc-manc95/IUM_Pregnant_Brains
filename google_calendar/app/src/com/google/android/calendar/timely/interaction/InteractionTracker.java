// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.interaction;

import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.utils.listener.ListenerCollection;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

// Referenced classes of package com.google.android.calendar.timely.interaction:
//            AutoValue_InteractionTracker_Interaction

public class InteractionTracker
{
    public static final class EndInteractionCountdown
        implements Runnable
    {

        private final Object controller;
        private int counter;
        private final Object target;

        public final void run()
        {
            int i = counter - 1;
            counter = i;
            if (i == 0)
            {
                InteractionTracker.getInstance().trackInteractionEnd(controller, target);
            }
        }

        public EndInteractionCountdown(Object obj, Object obj1, int i)
        {
            controller = obj;
            target = obj1;
            counter = i;
        }
    }

    static abstract class Interaction
    {

        abstract Object getController();

        abstract Object getTarget();

        Interaction()
        {
        }
    }

    public static interface Listener
    {

        public abstract void onInteractionEnd();

        public abstract void onInteractionStart();
    }

    static final class SingletonHolder
    {

        public static final InteractionTracker INSTANCE = new InteractionTracker();

    }


    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timely/interaction/InteractionTracker);
    private final Set interactions = new HashSet();
    private final ListenerCollection listeners = new ListenerCollection(1);

    public InteractionTracker()
    {
    }

    public static InteractionTracker getInstance()
    {
        CalendarExecutor.MAIN.checkOnThread();
        return SingletonHolder.INSTANCE;
    }

    private static transient void logFishfoodInfo(String s, Object aobj[])
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

    public final void addListener(Listener listener)
    {
        CalendarExecutor.MAIN.checkOnThread();
        ListenerCollection listenercollection = listeners;
        listenercollection.cloneCollectionIfNeeded();
        boolean flag;
        if (!listenercollection.listeners.add(listener))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            LogUtils.wtf(TAG, "Add of already registered listener: %s", new Object[] {
                listener
            });
        } else
        if (!interactions.isEmpty())
        {
            listener.onInteractionStart();
            return;
        }
    }

    public final void removeListener(Listener listener)
    {
        CalendarExecutor.MAIN.checkOnThread();
        ListenerCollection listenercollection = listeners;
        listenercollection.cloneCollectionIfNeeded();
        boolean flag;
        if (!listenercollection.listeners.remove(listener))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            LogUtils.wtf(TAG, "Remove of not registered listener: %s", new Object[] {
                listener
            });
        }
    }

    public final void trackInteractionEnd(Object obj, Object obj1)
    {
        CalendarExecutor.MAIN.checkOnThread();
        obj = new AutoValue_InteractionTracker_Interaction(obj, obj1);
        logFishfoodInfo("Track End: %s", new Object[] {
            obj
        });
        boolean flag;
        if (!interactions.remove(obj))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            LogUtils.wtf(TAG, "End of interaction not in progress: %s", new Object[] {
                obj
            });
        }
        if (!flag && interactions.isEmpty())
        {
            logFishfoodInfo("Interaction End", new Object[0]);
            obj = (com.google.android.calendar.utils.listener.ListenerCollection.ReleasableIterator)listeners.iterator();
            do
            {
                if (!((Iterator) (obj)).hasNext())
                {
                    break;
                }
                obj1 = (Listener)((Iterator) (obj)).next();
                if (listeners.listeners.contains(obj1))
                {
                    ((Listener) (obj1)).onInteractionEnd();
                }
            } while (true);
        }
    }

    public final void trackInteractionStart(Object obj, Object obj1)
    {
        CalendarExecutor.MAIN.checkOnThread();
        if (interactions.isEmpty())
        {
            logFishfoodInfo("Interaction Start", new Object[0]);
            com.google.android.calendar.utils.listener.ListenerCollection.ReleasableIterator releasableiterator = (com.google.android.calendar.utils.listener.ListenerCollection.ReleasableIterator)listeners.iterator();
            do
            {
                if (!releasableiterator.hasNext())
                {
                    break;
                }
                Listener listener = (Listener)releasableiterator.next();
                if (listeners.listeners.contains(listener))
                {
                    listener.onInteractionStart();
                }
            } while (true);
        }
        obj = new AutoValue_InteractionTracker_Interaction(obj, obj1);
        logFishfoodInfo("Track Start: %s", new Object[] {
            obj
        });
        boolean flag;
        if (!interactions.add(obj))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            LogUtils.wtf(TAG, "Start of interaction in progress: %s", new Object[] {
                obj
            });
        }
    }

}
