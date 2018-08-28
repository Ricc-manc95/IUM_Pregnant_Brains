// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen;

import android.support.v4.app.Fragment;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.newapi.segment.common.EditSegmentController;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Present;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SegmentMap
{

    public static final String LOG_TAG = com/google/android/calendar/newapi/screen/SegmentMap.getSimpleName();
    public final Map segmentControllers = new HashMap();

    private SegmentMap(Map map)
    {
        segmentControllers.putAll(map);
    }

    public static SegmentMap create(Fragment fragment, Object obj, List list)
    {
        HashMap hashmap = new HashMap();
        list = list.iterator();
        do
        {
            if (!list.hasNext())
            {
                break;
            }
            EditSegmentController editsegmentcontroller = (EditSegmentController)EditSegmentController.newInstance(fragment, (Class)list.next(), obj);
            if (editsegmentcontroller != null)
            {
                hashmap.put(editsegmentcontroller.getClass().getSimpleName(), editsegmentcontroller);
            }
        } while (true);
        return new SegmentMap(hashmap);
    }

    public final Optional getSegmentView(Class class1)
    {
        class1 = class1.getSimpleName();
        EditSegmentController editsegmentcontroller = (EditSegmentController)segmentControllers.get(class1);
        if (editsegmentcontroller == null)
        {
            LogUtils.wtf(LOG_TAG, "Tried to access unsupported segment '%s'.", new Object[] {
                class1
            });
        }
        class .Lambda._cls0
            implements Function
        {

            public static final Function $instance = new .Lambda._cls0();

            public final Object apply(Object obj)
            {
                return ((SegmentController)(EditSegmentController)obj).view;
            }


            private .Lambda._cls0()
            {
            }
        }

        if (editsegmentcontroller == null)
        {
            class1 = Absent.INSTANCE;
        } else
        {
            class1 = new Present(editsegmentcontroller);
        }
        return class1.transform(.Lambda._cls0..instance);
    }

}
