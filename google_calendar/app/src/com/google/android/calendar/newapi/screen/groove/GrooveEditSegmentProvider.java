// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.groove;

import android.util.SparseArray;
import com.google.android.calendar.api.habit.HabitContract;
import com.google.android.calendar.api.habit.HabitModifications;
import com.google.android.calendar.newapi.model.GrooveEditScreenModel;
import com.google.android.calendar.newapi.model.edit.BasicEditScreenModel;
import com.google.android.calendar.newapi.segment.belong.BelongIntegrationEditSegmentController;
import com.google.android.calendar.newapi.segment.calendar.EventCalendarEditSegmentController;
import com.google.android.calendar.newapi.segment.color.ColorEditSegmentController;
import com.google.android.calendar.newapi.segment.contract.ContractEditSegmentController;
import com.google.android.calendar.newapi.segment.notification.GrooveNotificationEditSegmentController;
import com.google.android.calendar.newapi.segment.time.GrooveTimeEditSegmentController;
import com.google.android.calendar.newapi.segment.title.GrooveTitleEditSegmentController;
import com.google.android.calendar.newapi.segment.visibility.VisibilityEditSegmentController;
import com.google.android.calendar.utils.belong.FitIntegrationConstants;
import java.util.ArrayList;
import java.util.List;

public final class GrooveEditSegmentProvider
{

    public static List getSupportedSegments(GrooveEditScreenModel grooveeditscreenmodel)
    {
        boolean flag = true;
        ArrayList arraylist = new ArrayList();
        arraylist.add(com/google/android/calendar/newapi/segment/title/GrooveTitleEditSegmentController);
        if (!grooveeditscreenmodel.showSimplifiedScreen())
        {
            arraylist.add(com/google/android/calendar/newapi/segment/calendar/EventCalendarEditSegmentController);
        }
        if (((BasicEditScreenModel) (grooveeditscreenmodel)).eventModifications != null)
        {
            arraylist.add(com/google/android/calendar/newapi/segment/time/GrooveTimeEditSegmentController);
        }
        if (!grooveeditscreenmodel.showSimplifiedScreen())
        {
            int i = grooveeditscreenmodel.habitModifications.getContractModifications().getInterval();
            if (i == 2 || i == 3)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                arraylist.add(com/google/android/calendar/newapi/segment/contract/ContractEditSegmentController);
            }
            arraylist.add(com/google/android/calendar/newapi/segment/notification/GrooveNotificationEditSegmentController);
            i = grooveeditscreenmodel.habitModifications.getType();
            if (FitIntegrationConstants.HABIT_TYPE_TO_FIT_ACTIVITY_MAP.get(i) != null)
            {
                i = ((flag) ? 1 : 0);
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                arraylist.add(com/google/android/calendar/newapi/segment/belong/BelongIntegrationEditSegmentController);
            }
            arraylist.add(com/google/android/calendar/newapi/segment/color/ColorEditSegmentController);
            arraylist.add(com/google/android/calendar/newapi/segment/visibility/VisibilityEditSegmentController);
        }
        return arraylist;
    }
}
