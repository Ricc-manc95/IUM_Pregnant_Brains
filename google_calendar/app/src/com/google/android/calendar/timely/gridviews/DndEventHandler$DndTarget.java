// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import android.graphics.Rect;
import com.google.android.apps.calendar.util.concurrent.Previewable;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.TimelineItemModifications;
import com.google.android.calendar.timely.geometry.TimelineSegment;
import com.google.common.base.Function;
import java.util.Comparator;

// Referenced classes of package com.google.android.calendar.timely.gridviews:
//            DndEventHandler

public static interface .Lambda._cls0
{

    public static final Comparator INDEX_COMPARATOR = .Lambda._cls0..instance;

    public abstract void clearTransientState();

    public abstract void dropItemDown(TimelineItem timelineitem, Previewable previewable, Function function);

    public abstract boolean getGlobalVisibleRect(Rect rect);

    public abstract int getIndex();

    public abstract boolean getItemFrame(TimelineSegment timelinesegment, Rect rect);

    public abstract void getLocationInWindow(int ai[]);

    public abstract Time getTimeFromPosition(int i);

    public abstract int getWidth();

    public abstract boolean pickItemUp(TimelineItem timelineitem, Rect rect);

    public abstract void setItemModifications(TimelineItemModifications timelineitemmodifications);


    class .Lambda._cls0
        implements Comparator
    {

        public static final Comparator $instance = new .Lambda._cls0();

        public final int compare(Object obj, Object obj1)
        {
            obj = (DndEventHandler.DndTarget)obj;
            obj1 = (DndEventHandler.DndTarget)obj1;
            return Integer.valueOf(((DndEventHandler.DndTarget) (obj)).getIndex()).compareTo(Integer.valueOf(((DndEventHandler.DndTarget) (obj1)).getIndex()));
        }


            private .Lambda._cls0()
            {
            }
    }

}
