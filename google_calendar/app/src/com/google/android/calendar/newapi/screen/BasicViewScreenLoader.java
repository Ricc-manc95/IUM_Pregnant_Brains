// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen;

import android.content.Context;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventDescriptor;
import com.google.android.calendar.event.conference.PhoneNumberDetails;
import com.google.android.calendar.newapi.common.CompositeLoader;
import com.google.android.calendar.newapi.common.FullEventLoader;
import com.google.android.calendar.newapi.common.VisibleCalendarsLoader;
import com.google.android.calendar.newapi.model.BasicViewScreenModel;
import com.google.android.calendar.newapi.model.ViewScreenModel;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Present;
import java.util.List;

public abstract class BasicViewScreenLoader extends CompositeLoader
{

    public final Event event;
    public FullEventLoader fullEventLoader;
    private final PhoneNumberDetails localPhoneNumber;
    public int visibleCalendars;
    public VisibleCalendarsLoader visibleCalendarsLoader;

    BasicViewScreenLoader(Context context, EventDescriptor eventdescriptor, PhoneNumberDetails phonenumberdetails, BasicViewScreenModel basicviewscreenmodel)
    {
        Event event1;
        int i;
        if (basicviewscreenmodel == null)
        {
            event1 = null;
        } else
        {
            event1 = basicviewscreenmodel.event;
        }
        event = event1;
        localPhoneNumber = phonenumberdetails;
        if (basicviewscreenmodel == null)
        {
            i = -1;
        } else
        {
            i = basicviewscreenmodel.visibleCalendars;
        }
        visibleCalendars = i;
        if (event == null || localPhoneNumber == null)
        {
            context = new FullEventLoader(context, eventdescriptor);
            fullEventLoader = context;
            super.loaders.add(context);
        }
        if (visibleCalendars == -1)
        {
            context = new VisibleCalendarsLoader();
            visibleCalendarsLoader = context;
            super.loaders.add(context);
        }
    }

    public final Optional getLocalPhoneNumber()
    {
        if (localPhoneNumber != null)
        {
            PhoneNumberDetails phonenumberdetails = localPhoneNumber;
            if (phonenumberdetails == null)
            {
                throw new NullPointerException();
            } else
            {
                return new Present(phonenumberdetails);
            }
        }
        PhoneNumberDetails phonenumberdetails1 = ((com.google.android.calendar.newapi.common.FullEventLoader.EventCalendarResult)fullEventLoader.getResult()).localPhone;
        if (phonenumberdetails1 == null)
        {
            return Absent.INSTANCE;
        } else
        {
            return new Present(phonenumberdetails1);
        }
    }

    public abstract ViewScreenModel getResult();

    public volatile Object getResult()
    {
        return getResult();
    }
}
