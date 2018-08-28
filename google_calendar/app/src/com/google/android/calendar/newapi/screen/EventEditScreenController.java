// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.loggers.CalendarClientLogger;
import com.google.android.apps.calendar.loggers.ClearcutManager;
import com.google.android.apps.calendar.loggers.rlz.RlzConfig;
import com.google.android.apps.calendar.loggers.rlz.RlzTracker;
import com.google.android.apps.calendar.loggers.visualelements.VisualElementAttacher;
import com.google.android.apps.calendar.loggers.visualelements.VisualElementHolder;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.analytics.ActivationLogger;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventModifications;
import com.google.android.calendar.api.event.EventPermissions;
import com.google.android.calendar.api.event.EventPermissionsFactory;
import com.google.android.calendar.newapi.common.Loader;
import com.google.android.calendar.newapi.logging.AnalyticsViewType;
import com.google.android.calendar.newapi.logging.EventEditLogMetrics;
import com.google.android.calendar.newapi.logging.LoggingUtils;
import com.google.android.calendar.newapi.model.edit.BasicEditScreenModel;
import com.google.android.calendar.newapi.model.edit.EditScreenModel;
import com.google.android.calendar.newapi.model.edit.EventEditScreenModel;
import com.google.android.calendar.newapi.model.edit.SettingsHolder;
import com.google.android.calendar.newapi.screen.event.EventEditSegmentProvider;
import com.google.android.calendar.newapi.screen.event.EventSaveFlow;
import com.google.android.calendar.newapi.segment.attachment.AttachmentEditSegmentController;
import com.google.android.calendar.newapi.segment.attendee.AttendeeEditSegmentController;
import com.google.android.calendar.newapi.segment.availability.AvailabilityEditSegmentController;
import com.google.android.calendar.newapi.segment.calendar.EventCalendarEditSegmentController;
import com.google.android.calendar.newapi.segment.color.ColorEditSegmentController;
import com.google.android.calendar.newapi.segment.common.EditSegmentController;
import com.google.android.calendar.newapi.segment.conference.ConferenceEditSegmentController;
import com.google.android.calendar.newapi.segment.location.LocationEditSegmentController;
import com.google.android.calendar.newapi.segment.note.NoteEditSegmentController;
import com.google.android.calendar.newapi.segment.notification.EventNotificationEditSegmentController;
import com.google.android.calendar.newapi.segment.ooo.OooEditSegmentController;
import com.google.android.calendar.newapi.segment.recurrence.RecurrenceEditSegmentController;
import com.google.android.calendar.newapi.segment.room.RoomEditSegmentController;
import com.google.android.calendar.newapi.segment.time.EventTimeEditSegmentController;
import com.google.android.calendar.newapi.segment.timezone.TimeZoneEditSegmentController;
import com.google.android.calendar.newapi.segment.title.EventTitleEditSegmentController;
import com.google.android.calendar.newapi.segment.visibility.VisibilityEditSegmentController;
import com.google.android.calendar.utils.flow.Flow;
import com.google.android.calendar.utils.fragment.FragmentUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.google.android.calendar.newapi.screen:
//            EditScreenController, EventEditScreenLoader, SegmentMap, EditScreen, 
//            EventViewScreenController, ViewScreenController, SegmentViews

public class EventEditScreenController extends EditScreenController
    implements com.google.android.calendar.newapi.common.Loader.Callback, com.google.android.calendar.newapi.screen.event.EventSaveFlow.Listener
{

    private EventEditScreenLoader.Factory loaderFactory;
    private com.google.android.calendar.newapi.model.edit.EventEditScreenModel.Factory modelFactory;
    private com.google.android.calendar.newapi.screen.event.EventSaveFlow.Factory saveFlowFactory;
    private EventEditSegmentProvider segmentProvider;

    public EventEditScreenController()
    {
        saveFlowFactory = new com.google.android.calendar.newapi.screen.event.EventSaveFlow.Factory();
        segmentProvider = new EventEditSegmentProvider();
        loaderFactory = new EventEditScreenLoader.Factory();
        modelFactory = new com.google.android.calendar.newapi.model.edit.EventEditScreenModel.Factory();
    }

    public static EventEditScreenController createEvent(Bundle bundle)
    {
        Bundle bundle1 = new Bundle(1);
        bundle1.putBundle("ARG_EXTRAS", bundle);
        bundle = new EventEditScreenController();
        bundle.setArguments(bundle1);
        return bundle;
    }

    protected final Loader createLoader()
    {
        return new EventEditScreenLoader(getContext(), (EventEditScreenModel)getModel());
    }

    protected volatile EditScreenModel createModel()
    {
        return createModel();
    }

    protected EventEditScreenModel createModel()
    {
        Bundle bundle;
        EventEditScreenModel eventeditscreenmodel;
        if (getArguments() != null && getArguments().containsKey("ARG_EXTRAS"))
        {
            bundle = getArguments().getBundle("ARG_EXTRAS");
        } else
        {
            bundle = null;
        }
        eventeditscreenmodel = new EventEditScreenModel();
        if (bundle != null)
        {
            eventeditscreenmodel.extras = bundle;
        }
        return eventeditscreenmodel;
    }

    protected SegmentMap createSegments()
    {
        Object obj = segmentProvider;
        obj = (EventEditScreenModel)getModel();
        ArrayList arraylist = new ArrayList(Arrays.asList(new Class[] {
            com/google/android/calendar/newapi/segment/title/EventTitleEditSegmentController, com/google/android/calendar/newapi/segment/calendar/EventCalendarEditSegmentController, com/google/android/calendar/newapi/segment/time/EventTimeEditSegmentController, com/google/android/calendar/newapi/segment/timezone/TimeZoneEditSegmentController, com/google/android/calendar/newapi/segment/recurrence/RecurrenceEditSegmentController, com/google/android/calendar/newapi/segment/room/RoomEditSegmentController, com/google/android/calendar/newapi/segment/location/LocationEditSegmentController, com/google/android/calendar/newapi/segment/notification/EventNotificationEditSegmentController, com/google/android/calendar/newapi/segment/attendee/AttendeeEditSegmentController, com/google/android/calendar/newapi/segment/color/ColorEditSegmentController, 
            com/google/android/calendar/newapi/segment/note/NoteEditSegmentController, com/google/android/calendar/newapi/segment/attachment/AttachmentEditSegmentController, com/google/android/calendar/newapi/segment/visibility/VisibilityEditSegmentController, com/google/android/calendar/newapi/segment/availability/AvailabilityEditSegmentController
        }));
        FeatureConfig featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        if (((FeatureConfig)featureconfig).ooo())
        {
            arraylist.add(com/google/android/calendar/newapi/segment/ooo/OooEditSegmentController);
        }
        featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        } else
        {
            ((FeatureConfig)featureconfig).third_party_conferences_editing();
            arraylist.add(com/google/android/calendar/newapi/segment/conference/ConferenceEditSegmentController);
            return SegmentMap.create(this, obj, arraylist);
        }
    }

    protected final int getDiscardChangesMessage()
    {
        return !((EventEditScreenModel)getModel()).isNew() ? 0x7f130182 : 0x7f130184;
    }

    protected SegmentViews getOrderedSegments(SegmentMap segmentmap)
    {
        getContext();
        return EventEditSegmentProvider.getOrderedSegments$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFDPINEOBGD4NMQRR4CLM2UPB4D5Q2UKR5EHQ6IRJ7ED46UR34CLP3MJ33DTMIUPRFDTJMOP9FC5N68SJFD5I2UOR1DHIMSP31E8NMSPBNC5O6IBRJCDP6APBE5T9MAPRDCLN78JB1E0TIIJ33DTMIUPRFDTJMOP9FC5N68SJFD5I2UOR1DHIMSP31E8NMSPBNC5O6IBRJCDP6APBE5T9MAPRDCLN78LJ9CLRN6EO_0((SettingsHolder)getModel(), segmentmap);
    }

    protected final String getPrimesLogTag()
    {
        return "EventEdit";
    }

    protected final void onCalendarChanged()
    {
        arrangeSegments();
    }

    public final void onCancelClicked()
    {
        Object obj1 = getContext();
        Object obj = (EventEditScreenModel)getModel();
        Object obj2 = super.editScreen.cancelButton;
        VisualElementAttacher visualelementattacher = VisualElementHolder.instance;
        if (visualelementattacher == null)
        {
            throw new NullPointerException(String.valueOf("VisualElementHolder must receive an instance first"));
        } else
        {
            ((VisualElementAttacher)visualelementattacher).recordTap(((Context) (obj1)), ((android.view.View) (obj2)), ((EditScreenModel) (obj)).getAccount());
            obj1 = CalendarClientLogger.getInstance(((Context) (obj1)));
            obj2 = ((EditScreenModel) (obj)).getAccount();
            obj = ((EventEditScreenModel) (obj)).eventReferenceId;
            ((CalendarClientLogger) (obj1)).log(((Account) (obj2)), CalendarClientLogger.getEventProto(com.google.calendar.client.events.logging.CalendarClientLogEvent.Type.EVENT_DETAILS_SESSION_ENDED, null, ((String) (obj)), null, null));
            super.onCancelClicked();
            return;
        }
    }

    protected void onLoadingCompleted(boolean flag)
    {
        super.onLoadingCompleted(flag);
        EventEditLogMetrics eventeditlogmetrics = ((EventEditScreenModel)getModel()).logMetrics;
        if (eventeditlogmetrics.loadedTime == -1L)
        {
            eventeditlogmetrics.loadedTime = SystemClock.elapsedRealtime();
        }
        if (flag)
        {
            Object obj1 = getContext();
            Object obj = (EventEditScreenModel)getModel();
            obj1 = CalendarClientLogger.getInstance(((Context) (obj1)));
            Account account = ((EditScreenModel) (obj)).getAccount();
            obj = ((EventEditScreenModel) (obj)).eventReferenceId;
            ((CalendarClientLogger) (obj1)).log(account, CalendarClientLogger.getEventProto(com.google.calendar.client.events.logging.CalendarClientLogEvent.Type.EVENT_DETAILS_SESSION_STARTED, null, ((String) (obj)), null, null));
        }
    }

    public final void onSaveClicked()
    {
        Object obj = super.segments.segmentControllers.values().iterator();
_L4:
        if (!((Iterator) (obj)).hasNext()) goto _L2; else goto _L1
_L1:
        EditSegmentController editsegmentcontroller = (EditSegmentController)((Iterator) (obj)).next();
        if (editsegmentcontroller.getErrorMessageId() == null) goto _L4; else goto _L3
_L3:
        obj = editsegmentcontroller.getErrorMessageId();
_L10:
        if (obj == null) goto _L6; else goto _L5
_L5:
        int i = ((Integer) (obj)).intValue();
        String s;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        obj = new android.support.v7.app.AlertDialog.Builder(((Context) (obj)));
        ((android.support.v7.app.AlertDialog.Builder) (obj)).P.mMessage = ((android.support.v7.app.AlertDialog.Builder) (obj)).P.mContext.getText(i);
        s = requireContext().getResources().getString(0x104000a);
        ((android.support.v7.app.AlertDialog.Builder) (obj)).P.mNegativeButtonText = s;
        ((android.support.v7.app.AlertDialog.Builder) (obj)).P.mNegativeButtonListener = null;
        ((android.support.v7.app.AlertDialog.Builder) (obj)).create().show();
_L8:
        return;
_L2:
        obj = null;
        continue; /* Loop/switch isn't completed */
_L6:
        Object obj2 = getContext();
        Object obj1 = (EventEditScreenModel)getModel();
        Object obj3 = super.editScreen;
        VisualElementAttacher visualelementattacher = VisualElementHolder.instance;
        if (visualelementattacher == null)
        {
            throw new NullPointerException(String.valueOf("VisualElementHolder must receive an instance first"));
        }
        ((VisualElementAttacher)visualelementattacher).recordTap(((Context) (obj2)), ((EditScreen) (obj3)).saveButton, ((EditScreenModel) (obj1)).getAccount());
        obj2 = CalendarClientLogger.getInstance(((Context) (obj2)));
        obj3 = ((EditScreenModel) (obj1)).getAccount();
        obj1 = ((EventEditScreenModel) (obj1)).eventReferenceId;
        ((CalendarClientLogger) (obj2)).log(((Account) (obj3)), CalendarClientLogger.getEventProto(com.google.calendar.client.events.logging.CalendarClientLogEvent.Type.EVENT_DETAILS_SESSION_ENDED, null, ((String) (obj1)), null, null));
        obj1 = ((BasicEditScreenModel) ((EventEditScreenModel)getModel())).eventModifications;
        obj2 = new com.google.android.calendar.newapi.screen.event.EventSaveFlow.Factory..Lambda._cls0(true, CalendarApi.EventPermissionsFactory.create(((BasicEditScreenModel) ((EventEditScreenModel)getModel())).eventModifications).getAllowedModificationScopes(), ((EventModifications) (obj1)));
        if (super.mHost == null)
        {
            obj1 = null;
        } else
        {
            obj1 = (FragmentActivity)super.mHost.mActivity;
        }
        obj1 = (Flow)FragmentUtils.attachFragment(((android.app.Activity) (obj1)), super.mFragmentManager, com/google/android/calendar/newapi/screen/event/EventSaveFlow, this, null);
        if (obj1 == null) goto _L8; else goto _L7
_L7:
        ((Consumer) (obj2)).accept(obj1);
        return;
        if (true) goto _L10; else goto _L9
_L9:
    }

    public void onSaveCompleted(boolean flag, Event event, int i)
    {
        Object obj;
        Context context;
        EventEditScreenModel eventeditscreenmodel;
        context = getContext();
        if (context == null)
        {
            return;
        }
        if (!flag)
        {
            LoggingUtils.logSaveFailure(context, getModel(), i);
            return;
        }
        eventeditscreenmodel = (EventEditScreenModel)getModel();
        Object obj2 = CalendarClientLogger.getInstance(context);
        if (ActivationLogger.instance == null)
        {
            ActivationLogger.instance = new ActivationLogger(context);
        }
        obj = ActivationLogger.instance;
        Object obj3 = ((BasicEditScreenModel) (eventeditscreenmodel)).eventModifications.getGoogleSyncId();
        Object obj4 = eventeditscreenmodel.getAccount();
        if (eventeditscreenmodel.isNew())
        {
            String s2 = eventeditscreenmodel.eventReferenceId;
            ((CalendarClientLogger) (obj2)).log(((Account) (obj4)), CalendarClientLogger.getEventProto(com.google.calendar.client.events.logging.CalendarClientLogEvent.Type.EVENT_CREATED, ((String) (obj3)), s2, null, null));
            obj2 = ((Account) (obj4)).name;
            LogUtils.v("ActivationLogger", "userCreatedNewEvent", new Object[0]);
            obj3 = ((ActivationLogger) (obj)).rlzTracker;
            if (!((RlzTracker) (obj3)).shouldUseFirstUseBroadcast)
            {
                obj3 = RlzTracker.TAG;
                if (LogUtils.maxEnabledLogLevel > 2)
                {
                    flag = false;
                } else
                if (Log.isLoggable(((String) (obj3)), 2))
                {
                    flag = true;
                } else
                {
                    flag = Log.isLoggable(((String) (obj3)), 2);
                }
                if (flag)
                {
                    LogUtils.v(RlzTracker.TAG, "Received use event, but not broadcasting.", new Object[0]);
                }
            } else
            {
                Object obj5 = ((RlzTracker) (obj3)).config.accessPoints;
                obj4 = new Intent("com.google.android.partnersetup.ACTION_RLZ_FIRST_USE");
                ((Intent) (obj4)).putStringArrayListExtra("com.google.android.partnersetup.EXTRA_ACCESS_POINTS", new ArrayList(((Collection) (obj5))));
                ((Intent) (obj4)).setComponent(RlzTracker.RLZ_PING_INTENT_SERVICE);
                obj5 = RlzTracker.TAG;
                if (LogUtils.maxEnabledLogLevel > 2)
                {
                    flag = false;
                } else
                if (Log.isLoggable(((String) (obj5)), 2))
                {
                    flag = true;
                } else
                {
                    flag = Log.isLoggable(((String) (obj5)), 2);
                }
                if (flag)
                {
                    LogUtils.v(RlzTracker.TAG, "Broadcasting intent: %s", new Object[] {
                        obj4
                    });
                }
                ((RlzTracker) (obj3)).context.sendBroadcast(((Intent) (obj4)));
                if (!((RlzTracker) (obj3)).sharedPrefs.edit().putBoolean("sent_rlz_first_use_broadcast", true).commit())
                {
                    LogUtils.e(RlzTracker.TAG, "Couldn't persist sent_rlz_first_use_broadcast preference. Will send another broadcast on activation after process restart.", new Object[0]);
                }
                obj3.shouldUseFirstUseBroadcast = false;
            }
            obj3 = com.google.android.calendar.CalendarLoggingExtension.AndroidCalendarExtensionProto.ActionType.CREATE_EVENT;
            if (((ActivationLogger) (obj)).clearcutManager != null)
            {
                ((ActivationLogger) (obj)).clearcutManager.logAction(((com.google.android.calendar.CalendarLoggingExtension.AndroidCalendarExtensionProto.ActionType) (obj3)), ((String) (obj2)));
            }
        } else
        {
            String s3 = eventeditscreenmodel.eventReferenceId;
            ((CalendarClientLogger) (obj2)).log(((Account) (obj4)), CalendarClientLogger.getEventProto(com.google.calendar.client.events.logging.CalendarClientLogEvent.Type.EVENT_UPDATED, ((String) (obj3)), s3, null, null));
            obj2 = ((Account) (obj4)).name;
            LogUtils.v("ActivationLogger", "userModifiedEvent", new Object[0]);
            obj3 = ((ActivationLogger) (obj)).rlzTracker;
            if (!((RlzTracker) (obj3)).shouldUseFirstUseBroadcast)
            {
                obj3 = RlzTracker.TAG;
                if (LogUtils.maxEnabledLogLevel > 2)
                {
                    flag = false;
                } else
                if (Log.isLoggable(((String) (obj3)), 2))
                {
                    flag = true;
                } else
                {
                    flag = Log.isLoggable(((String) (obj3)), 2);
                }
                if (flag)
                {
                    LogUtils.v(RlzTracker.TAG, "Received use event, but not broadcasting.", new Object[0]);
                }
            } else
            {
                Object obj6 = ((RlzTracker) (obj3)).config.accessPoints;
                Intent intent = new Intent("com.google.android.partnersetup.ACTION_RLZ_FIRST_USE");
                intent.putStringArrayListExtra("com.google.android.partnersetup.EXTRA_ACCESS_POINTS", new ArrayList(((Collection) (obj6))));
                intent.setComponent(RlzTracker.RLZ_PING_INTENT_SERVICE);
                obj6 = RlzTracker.TAG;
                if (LogUtils.maxEnabledLogLevel > 2)
                {
                    flag = false;
                } else
                if (Log.isLoggable(((String) (obj6)), 2))
                {
                    flag = true;
                } else
                {
                    flag = Log.isLoggable(((String) (obj6)), 2);
                }
                if (flag)
                {
                    LogUtils.v(RlzTracker.TAG, "Broadcasting intent: %s", new Object[] {
                        intent
                    });
                }
                ((RlzTracker) (obj3)).context.sendBroadcast(intent);
                if (!((RlzTracker) (obj3)).sharedPrefs.edit().putBoolean("sent_rlz_first_use_broadcast", true).commit())
                {
                    LogUtils.e(RlzTracker.TAG, "Couldn't persist sent_rlz_first_use_broadcast preference. Will send another broadcast on activation after process restart.", new Object[0]);
                }
                obj3.shouldUseFirstUseBroadcast = false;
            }
            obj3 = com.google.android.calendar.CalendarLoggingExtension.AndroidCalendarExtensionProto.ActionType.CHANGE_EVENT;
            if (((ActivationLogger) (obj)).clearcutManager != null)
            {
                ((ActivationLogger) (obj)).clearcutManager.logAction(((com.google.android.calendar.CalendarLoggingExtension.AndroidCalendarExtensionProto.ActionType) (obj3)), ((String) (obj2)));
            }
        }
        if (context == null) goto _L2; else goto _L1
_L1:
        AnalyticsLogger analyticslogger;
        obj = AnalyticsLoggerHolder.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        analyticslogger = (AnalyticsLogger)obj;
        i;
        JVM INSTR tableswitch 1 2: default 780
    //                   1 834
    //                   2 842;
           goto _L3 _L4 _L5
_L3:
        obj = "only_this_instance";
_L7:
        analyticslogger.setCustomDimension(context, 46, ((String) (obj)));
_L2:
        LoggingUtils.addAccountType(context, eventeditscreenmodel);
        if (context == null)
        {
            break MISSING_BLOCK_LABEL_867;
        }
        obj = AnalyticsLoggerHolder.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        break; /* Loop/switch isn't completed */
_L4:
        obj = "all_following_instances";
        continue; /* Loop/switch isn't completed */
_L5:
        obj = "all_instances";
        if (true) goto _L7; else goto _L6
_L6:
        ((AnalyticsLogger)obj).setCustomDimension(context, 47, "new");
        obj = AnalyticsViewType.fromEvent(((BasicEditScreenModel) (eventeditscreenmodel)).eventModifications);
        if (context != null)
        {
            analyticslogger = AnalyticsLoggerHolder.instance;
            if (analyticslogger == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            ((AnalyticsLogger)analyticslogger).trackEvent(context, "save_event", ((String) (obj)));
        }
        if (context == null) goto _L9; else goto _L8
_L8:
        obj = AnalyticsLoggerHolder.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        analyticslogger = (AnalyticsLogger)obj;
        i;
        JVM INSTR tableswitch 1 2: default 984
    //                   1 1038
    //                   2 1046;
           goto _L10 _L11 _L12
_L10:
        obj = "only_this_instance";
_L14:
        analyticslogger.setCustomDimension(context, 46, ((String) (obj)));
_L9:
        LoggingUtils.addAccountType(context, eventeditscreenmodel);
        if (context == null)
        {
            break MISSING_BLOCK_LABEL_1071;
        }
        obj = AnalyticsLoggerHolder.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        break; /* Loop/switch isn't completed */
_L11:
        obj = "all_following_instances";
        continue; /* Loop/switch isn't completed */
_L12:
        obj = "all_instances";
        if (true) goto _L14; else goto _L13
_L13:
        ((AnalyticsLogger)obj).setCustomDimension(context, 47, "new");
        Object obj1 = eventeditscreenmodel.logMetrics;
        AnalyticsLogger analyticslogger1 = AnalyticsLoggerHolder.instance;
        if (analyticslogger1 == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        analyticslogger1 = (AnalyticsLogger)analyticslogger1;
        String s = eventeditscreenmodel.getAnalyticsAction();
        ((EventEditLogMetrics) (obj1)).logSaveCustomDimensions(context, eventeditscreenmodel);
        analyticslogger1.trackEvent(context, "event", s);
        String s1 = EventEditLogMetrics.TAG;
        if (LogUtils.maxEnabledLogLevel > 3)
        {
            flag = false;
        } else
        if (Log.isLoggable(s1, 3))
        {
            flag = true;
        } else
        {
            flag = Log.isLoggable(s1, 3);
        }
        if (flag)
        {
            LogUtils.d(EventEditLogMetrics.TAG, "Logging event save: %s - %s", new Object[] {
                "event", s
            });
        }
        if (((EventEditLogMetrics) (obj1)).loadedTime != -1L)
        {
            ((EventEditLogMetrics) (obj1)).logSaveCustomDimensions(context, eventeditscreenmodel);
            long l = SystemClock.elapsedRealtime() - ((EventEditLogMetrics) (obj1)).loadedTime;
            LogUtils.d(EventEditLogMetrics.TAG, "Logging event edit timing: %s/%s = %d", new Object[] {
                "event", s, Long.valueOf(l)
            });
            analyticslogger1.trackTiming(context, "interaction", l, "event", s);
        } else
        {
            LogUtils.e(EventEditLogMetrics.TAG, "Saving a not loaded event", new Object[0]);
        }
        notifyEventSaved();
        obj1 = (EventViewScreenController)getViewScreenController(com/google/android/calendar/newapi/screen/EventViewScreenController);
        if (obj1 == null)
        {
            (new Handler(Looper.getMainLooper())).post(new HostedFragment..Lambda._cls0(this));
            return;
        }
        if (shouldCloseViewScreenAfterSave(event))
        {
            ((ViewScreenController) (obj1)).closeViewScreen();
        } else
        {
            ((EventViewScreenController) (obj1)).updateSegmentsWithUpdatedEvent(event);
        }
        (new Handler(Looper.getMainLooper())).post(new HostedFragment..Lambda._cls0(this));
        return;
    }

    protected boolean shouldCloseViewScreenAfterSave(Event event)
    {
        return event == null;
    }
}
