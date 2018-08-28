// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.ics;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.widget.ImageView;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventKey;
import com.google.android.calendar.event.image.helper.ImageHelper;
import com.google.android.calendar.newapi.image.helper.SmartMailHeaderAttributeImageHelper;
import com.google.android.calendar.newapi.model.BasicViewScreenModel;
import com.google.android.calendar.newapi.model.CalendarList;
import com.google.android.calendar.newapi.model.CalendarListHolder;
import com.google.android.calendar.newapi.model.EventViewScreenModel;
import com.google.android.calendar.newapi.model.ViewScreenModel;
import com.google.android.calendar.newapi.utils.SmartMailUtils;
import com.google.android.calendar.timely.TimelineEvent;
import com.google.android.calendar.v2a.UnifiedSyncUtils;
import com.google.common.util.concurrent.FutureCallback;

public final class IcsViewScreenModel extends EventViewScreenModel
    implements CalendarListHolder
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    private CalendarList calendarList;
    public int importType;

    public IcsViewScreenModel(int i, TimelineEvent timelineevent)
    {
        super(timelineevent);
        importType = i;
    }

    IcsViewScreenModel(Parcel parcel)
    {
        super(parcel);
        importType = parcel.readInt();
        calendarList = (CalendarList)parcel.readParcelable(com/google/android/calendar/newapi/model/CalendarList.getClassLoader());
    }

    public final Drawable getBackgroundDrawable(Context context, FutureCallback futurecallback)
    {
        boolean flag;
        if (super.event != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            return super.getBackgroundDrawable(context, futurecallback);
        }
        com.google.android.calendar.api.event.smartmail.SmartMailImage smartmailimage = SmartMailUtils.getImage(super.event.getSmartMailInfo());
        context = new SmartMailHeaderAttributeImageHelper(context, super.timelineItem, smartmailimage, futurecallback);
        futurecallback = ((ImageHelper) (context)).view;
        if (futurecallback == null)
        {
            return null;
        } else
        {
            context.bind();
            return futurecallback.getDrawable();
        }
    }

    public final CalendarList getCalendarList()
    {
        return calendarList;
    }

    public final int getColor(Context context)
    {
        if (showSimplifiedScreen())
        {
            return ((TimelineEvent)super.timelineItem).getColor();
        } else
        {
            return super.getColor(context);
        }
    }

    public final boolean isEditable()
    {
        return !showSimplifiedScreen() && super.isEditable() && !UnifiedSyncUtils.shouldUseCalendarsAndEvents();
    }

    public final void mergeModel(ViewScreenModel viewscreenmodel)
    {
        if (viewscreenmodel instanceof IcsViewScreenModel)
        {
            IcsViewScreenModel icsviewscreenmodel = (IcsViewScreenModel)viewscreenmodel;
            importType = icsviewscreenmodel.importType;
            setCalendarList(icsviewscreenmodel.getCalendarList());
        }
        super.mergeModel(viewscreenmodel);
    }

    public final void setCalendarList(CalendarList calendarlist)
    {
        calendarList = calendarlist;
    }

    public final boolean showSimplifiedScreen()
    {
        return !((EventKey)((TimelineEvent)super.timelineItem).eventKey instanceof com.google.android.calendar.api.event.EventKey.Persisted);
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        super.writeToParcel(parcel, i);
        parcel.writeInt(importType);
        parcel.writeParcelable(calendarList, i);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new IcsViewScreenModel(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new IcsViewScreenModel[i];
        }

        _cls1()
        {
        }
    }

}
