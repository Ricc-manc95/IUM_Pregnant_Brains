// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.smartmail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.view.View;
import com.google.android.calendar.Utils;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.newapi.commandbar.BottomBarController;
import com.google.android.calendar.newapi.commandbar.CommandBarController;
import com.google.android.calendar.newapi.commandbar.MoreOptionsSheetController;
import com.google.android.calendar.newapi.common.Loader;
import com.google.android.calendar.newapi.model.BasicViewScreenModel;
import com.google.android.calendar.newapi.model.EventViewScreenModel;
import com.google.android.calendar.newapi.model.ViewScreenModel;
import com.google.android.calendar.newapi.screen.EventViewScreenController;
import com.google.android.calendar.newapi.screen.ViewScreenController;
import com.google.android.calendar.newapi.segment.calendar.CalendarViewSegment;
import com.google.android.calendar.newapi.segment.notification.EventNotificationViewSegment;
import com.google.android.calendar.newapi.segment.smartmail.SourceMessageViewSegment;
import com.google.android.calendar.newapi.segment.smartmail.event.EventInformationViewSegment;
import com.google.android.calendar.newapi.segment.smartmail.flight.FlightInformationViewSegment;
import com.google.android.calendar.newapi.segment.smartmail.lodging.LodgingInformationViewSegment;
import com.google.android.calendar.newapi.segment.smartmail.restaurant.RestaurantInformationViewSegment;
import com.google.android.calendar.newapi.segment.visibility.VisibilityAvailabilityViewSegment;
import com.google.android.calendar.timely.TimelineEvent;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.utils.activity.ActivityUtils;
import java.util.List;

// Referenced classes of package com.google.android.calendar.newapi.screen.smartmail:
//            SmartMailCommandBarController, SmartMailViewScreenLoader, SmartMailViewScreenModel

public final class SmartMailViewScreenController extends EventViewScreenController
    implements SmartMailCommandBarController.Callback
{

    public SmartMailViewScreenController()
    {
    }

    protected final void createBodySegments(EventViewScreenModel eventviewscreenmodel, List list)
    {
        Object obj1 = null;
        if (eventviewscreenmodel.showSimplifiedScreen())
        {
            super.createBodySegments(eventviewscreenmodel, list);
            return;
        }
        Object obj;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        list.add(new FlightInformationViewSegment(((android.content.Context) (obj)), eventviewscreenmodel));
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        list.add(new EventInformationViewSegment(((android.content.Context) (obj)), eventviewscreenmodel));
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        list.add(new RestaurantInformationViewSegment(((android.app.Activity) (obj)), eventviewscreenmodel));
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        list.add(new LodgingInformationViewSegment(((android.app.Activity) (obj)), eventviewscreenmodel));
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        list.add(new SourceMessageViewSegment(((android.content.Context) (obj)), eventviewscreenmodel));
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        list.add(new EventNotificationViewSegment(((android.content.Context) (obj)), eventviewscreenmodel));
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        list.add(new VisibilityAvailabilityViewSegment(((android.content.Context) (obj)), eventviewscreenmodel));
        if (super.mHost == null)
        {
            obj = obj1;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        list.add(new CalendarViewSegment(((android.content.Context) (obj)), eventviewscreenmodel));
    }

    protected final volatile void createBodySegments(ViewScreenModel viewscreenmodel, List list)
    {
        createBodySegments((EventViewScreenModel)viewscreenmodel, list);
    }

    protected final BottomBarController createCommandBarController(MoreOptionsSheetController moreoptionssheetcontroller)
    {
        return (CommandBarController)Utils.uncheckedCast(new SmartMailCommandBarController(this));
    }

    public final Loader createLoader(boolean flag)
    {
        android.content.Context context = super.mView.getContext();
        TimelineEvent timelineevent = (TimelineEvent)super.model.timelineItem;
        com.google.android.calendar.api.event.EventDescriptor eventdescriptor = super.eventDescriptor;
        EventViewScreenModel eventviewscreenmodel;
        if (flag)
        {
            eventviewscreenmodel = (EventViewScreenModel)getModel();
        } else
        {
            eventviewscreenmodel = null;
        }
        return new SmartMailViewScreenLoader(context, timelineevent, eventdescriptor, eventviewscreenmodel);
    }

    public final EventViewScreenModel createModel(TimelineEvent timelineevent)
    {
        return new SmartMailViewScreenModel(timelineevent);
    }

    public final volatile ViewScreenModel createModel(TimelineItem timelineitem)
    {
        return createModel((TimelineEvent)timelineitem);
    }

    public final void onCheckInClicked(String s)
    {
        Object obj1 = null;
        Object obj = AnalyticsLoggerHolder.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        AnalyticsLogger analyticslogger = (AnalyticsLogger)obj;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        analyticslogger.trackEvent(((android.content.Context) (obj)), "event_action", "flight_check_in");
        if (super.mHost == null)
        {
            obj = obj1;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        ActivityUtils.startActivityForUrl(((android.content.Context) (obj)), s, "ViewScreenController");
    }
}
