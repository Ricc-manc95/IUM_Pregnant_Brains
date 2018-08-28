// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen;

import android.content.Context;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.attendee.Response;
import com.google.android.calendar.newapi.commandbar.BottomBarController;
import com.google.android.calendar.newapi.logging.LoggingUtils;
import com.google.android.calendar.newapi.model.BasicViewScreenModel;
import com.google.android.calendar.newapi.model.EventViewScreenModel;
import com.google.android.calendar.newapi.model.ViewScreenModel;
import com.google.android.calendar.timely.TimelineEvent;
import com.google.common.util.concurrent.FutureCallback;

// Referenced classes of package com.google.android.calendar.newapi.screen:
//            ViewScreenController, EventViewScreenController, ViewScreen

final class val.scope
    implements FutureCallback
{

    private final EventViewScreenController this$0;
    private final Context val$context;
    private final boolean val$dismiss;
    private final Response val$response;
    private final int val$scope;

    public final void onFailure(Throwable throwable)
    {
        LoggingUtils.logRsvp(val$context, false, (EventViewScreenModel)getModel(), val$scope, val$response);
        saveInProgress = false;
    }

    public final void onSuccess(Object obj)
    {
        obj = (Event)obj;
        ((TimelineEvent)model.timelineItem).selfAttendeeStatus = val$response.status;
        if (obj != null)
        {
            ((EventViewScreenModel)getModel()).setEvent(((Event) (obj)));
            EventViewScreenController eventviewscreencontroller = EventViewScreenController.this;
            if (((ViewScreenController) (eventviewscreencontroller)).commandBarController != null)
            {
                BottomBarController bottombarcontroller = ((ViewScreenController) (eventviewscreencontroller)).commandBarController;
                ViewScreenModel viewscreenmodel = eventviewscreencontroller.getModel();
                bottombarcontroller.model = viewscreenmodel;
                bottombarcontroller.onModelChanged(viewscreenmodel);
                ((ViewScreenController) (eventviewscreencontroller)).viewScreen.adjustExtraCommandBarPadding();
            }
        }
        if (val$dismiss || obj == null)
        {
            closeViewScreen();
        }
        LoggingUtils.logRsvp(val$context, true, (EventViewScreenModel)getModel(), val$scope, val$response);
        saveInProgress = false;
    }

    ()
    {
        this$0 = final_eventviewscreencontroller;
        val$response = response1;
        val$dismiss = flag;
        val$context = context1;
        val$scope = I.this;
        super();
    }
}
