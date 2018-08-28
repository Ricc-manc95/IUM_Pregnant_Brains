// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.event.EventClient;
import com.google.android.calendar.api.event.EventModifications;
import com.google.android.calendar.api.event.GooglePrivateDataModification;
import com.google.android.calendar.newapi.model.EventViewScreenModel;
import com.google.android.calendar.newapi.model.edit.BasicEditScreenModel;
import com.google.android.calendar.newapi.model.edit.EventEditScreenModel;
import com.google.android.calendar.timely.findatime.FindTimeIntentFactory;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.calendar.newapi.screen:
//            EventViewScreenController, ViewScreenController

class EveryoneDeclinedManager
    implements com.google.android.calendar.newapi.commandbar.EveryoneDeclinedBottomBarController.Callback
{

    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/newapi/screen/EveryoneDeclinedManager);
    public EventEditScreenModel editModel;
    private final com.google.android.calendar.newapi.model.edit.EventEditScreenModel.Factory editScreenModelFactory = new com.google.android.calendar.newapi.model.edit.EventEditScreenModel.Factory();
    private final com.google.android.calendar.newapi.screen.event.EventSaveFlow.Factory eventSaveFlowFactory = new com.google.android.calendar.newapi.screen.event.EventSaveFlow.Factory();
    public EventViewScreenController eventViewScreenController;

    EveryoneDeclinedManager(EventViewScreenController eventviewscreencontroller)
    {
        eventViewScreenController = eventviewscreencontroller;
    }

    private final EventEditScreenModel createEditModel(EventViewScreenModel eventviewscreenmodel)
    {
        EventEditScreenModel eventeditscreenmodel = new EventEditScreenModel();
        if (false)
        {
            eventeditscreenmodel.extras = null;
        }
        eventeditscreenmodel.mergeModel(eventviewscreenmodel);
        return eventeditscreenmodel;
    }

    public final void onDelete()
    {
        eventViewScreenController.onDeleteClicked();
    }

    public final void onDismiss()
    {
        final EventEditScreenModel editModel = createEditModel((EventViewScreenModel)eventViewScreenController.getModel());
        Object obj = ((BasicEditScreenModel) (editModel)).eventModifications;
        ((EventModifications) (obj)).getGooglePrivateDataModification().setIsEveryoneDeclinedDismissed(true);
        obj = CalendarApi.Events.update(((EventModifications) (obj)), 0, com.google.android.calendar.api.event.GooglePrivateData.GuestNotification.UNDECIDED);
        editModel = new _cls1();
        CalendarExecutor calendarexecutor = CalendarExecutor.MAIN;
        if (editModel == null)
        {
            throw new NullPointerException();
        } else
        {
            ((ListenableFuture) (obj)).addListener(new com.google.common.util.concurrent.Futures.CallbackListener(((java.util.concurrent.Future) (obj)), editModel), calendarexecutor);
            return;
        }
    }

    public final void onEmailGuests()
    {
        eventViewScreenController.onEmailGuestsClicked(false);
        onDismiss();
    }

    public final void onReschedule()
    {
        Object obj1 = null;
        editModel = createEditModel((EventViewScreenModel)eventViewScreenController.getModel());
        Object obj = eventViewScreenController;
        if (((Fragment) (obj)).mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)((Fragment) (obj)).mHost.mActivity;
        }
        obj = FindTimeIntentFactory.create(((android.content.Context) (obj)), editModel);
        if (obj == null)
        {
            LogUtils.wtf(TAG, "Find a Time intent should not be null.", new Object[0]);
            return;
        }
        ((Intent) (obj)).addFlags(0x24000000);
        eventViewScreenController.startActivityForResult(((Intent) (obj)), 1005);
        obj = eventViewScreenController;
        if (((Fragment) (obj)).mHost == null)
        {
            obj = obj1;
        } else
        {
            obj = (FragmentActivity)((Fragment) (obj)).mHost.mActivity;
        }
        ((FragmentActivity) (obj)).overridePendingTransition(0, 0);
    }


    private class _cls1
        implements FutureCallback
    {

        private final EveryoneDeclinedManager this$0;
        private final EventEditScreenModel val$editModel;

        public final void onFailure(Throwable throwable)
        {
            Toast.makeText(eventViewScreenController.getContext(), 0x7f1301ab, 0).show();
            LoggingUtils.logSaveFailure(eventViewScreenController.getContext(), editModel, 0);
        }

        public final void onSuccess(Object obj)
        {
            obj = (Event)obj;
            eventViewScreenController.updateSegmentsWithUpdatedEvent(((Event) (obj)));
        }

        _cls1()
        {
            this$0 = EveryoneDeclinedManager.this;
            editModel = eventeditscreenmodel;
            super();
        }
    }

}
