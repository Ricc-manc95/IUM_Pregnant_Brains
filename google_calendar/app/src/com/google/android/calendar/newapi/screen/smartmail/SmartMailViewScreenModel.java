// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.smartmail;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.widget.ImageView;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.event.conference.PhoneNumberDetails;
import com.google.android.calendar.event.image.helper.ImageHelper;
import com.google.android.calendar.newapi.image.helper.SmartMailHeaderAttributeImageHelper;
import com.google.android.calendar.newapi.model.BasicViewScreenModel;
import com.google.android.calendar.newapi.model.CalendarList;
import com.google.android.calendar.newapi.model.EventViewScreenModel;
import com.google.android.calendar.newapi.model.ViewScreenModel;
import com.google.android.calendar.newapi.utils.SmartMailUtils;
import com.google.android.calendar.timely.TimelineEvent;
import com.google.common.util.concurrent.FutureCallback;

public final class SmartMailViewScreenModel extends EventViewScreenModel
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();

    protected SmartMailViewScreenModel(Parcel parcel)
    {
        super(parcel);
    }

    public SmartMailViewScreenModel(Event event, TimelineEvent timelineevent, int i, PhoneNumberDetails phonenumberdetails, CalendarList calendarlist)
    {
        super(event, timelineevent, i, phonenumberdetails, calendarlist);
    }

    protected SmartMailViewScreenModel(TimelineEvent timelineevent)
    {
        super(timelineevent);
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

    public final boolean isSmartMailEvent()
    {
        return true;
    }

    public final boolean showSimplifiedScreen()
    {
        boolean flag;
        if (super.event != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag && !SmartMailUtils.isSupportedSmartMailType(super.event.getSmartMailInfo()))
        {
            return true;
        } else
        {
            return super.showSimplifiedScreen();
        }
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new SmartMailViewScreenModel(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new SmartMailViewScreenModel[i];
        }

        _cls1()
        {
        }
    }

}
