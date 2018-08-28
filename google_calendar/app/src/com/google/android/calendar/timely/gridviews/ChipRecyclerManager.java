// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import android.app.Activity;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.loggers.visualelements.VisualElementAttacher;
import com.google.android.apps.calendar.loggers.visualelements.VisualElementHolder;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.timeline.chip.Chip;
import com.google.android.calendar.timeline.chip.ChipFactory;
import com.google.android.calendar.utils.ActivitySingletonCache;
import com.google.android.calendar.utils.recycler.Recycler;

// Referenced classes of package com.google.android.calendar.timely.gridviews:
//            GridDayView

public class ChipRecyclerManager
    implements com.google.android.calendar.utils.recycler.Recycler.RecyclerManager
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timely/gridviews/ChipRecyclerManager);
    private static final ActivitySingletonCache instances = new _cls1();
    private final ChipFactory chipFactory;

    ChipRecyclerManager(ChipFactory chipfactory)
    {
        chipFactory = chipfactory;
    }

    public static Recycler getOrCreateRecycler(Activity activity)
    {
        CalendarExecutor.MAIN.checkOnThread();
        return (Recycler)instances.getInstance(activity);
    }

    public final void clean(Object obj)
    {
        obj = (Chip)obj;
        boolean flag;
        if (!(((Chip) (obj)).getParent() instanceof GridDayView))
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
        ((Chip) (obj)).clean();
        VisualElementAttacher visualelementattacher = VisualElementHolder.instance;
        if (visualelementattacher == null)
        {
            throw new NullPointerException(String.valueOf("VisualElementHolder must receive an instance first"));
        } else
        {
            ((VisualElementAttacher)visualelementattacher).detachChip(((android.view.View) (obj)));
            ((Chip) (obj)).setVisibility(8);
            return;
        }
    }

    public final Object createObject()
    {
        LogUtils.d(TAG, "Inflating new TimelyChip from recycler", new Object[0]);
        Chip chip = chipFactory.create();
        VisualElementAttacher visualelementattacher = VisualElementHolder.instance;
        if (visualelementattacher == null)
        {
            throw new NullPointerException(String.valueOf("VisualElementHolder must receive an instance first"));
        } else
        {
            ((VisualElementAttacher)visualelementattacher).attachEventChip(chip);
            return chip;
        }
    }

    public final void prepareToReuse(Object obj)
    {
        obj = (Chip)obj;
        VisualElementAttacher visualelementattacher = VisualElementHolder.instance;
        if (visualelementattacher == null)
        {
            throw new NullPointerException(String.valueOf("VisualElementHolder must receive an instance first"));
        } else
        {
            ((VisualElementAttacher)visualelementattacher).attachEventChip(((android.view.View) (obj)));
            ((Chip) (obj)).setVisibility(0);
            return;
        }
    }


    private class _cls1 extends ActivitySingletonCache
    {

        protected final Object createInstance(Activity activity)
        {
            return new Recycler(new ChipRecyclerManager(ChipFactory.getInstance(activity)), 100);
        }

        _cls1()
        {
        }
    }

}
