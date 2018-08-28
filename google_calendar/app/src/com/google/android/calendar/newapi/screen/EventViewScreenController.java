// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.api.util.attendee.AttendeeUtils;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.config.remote.RemoteFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeatureConfig;
import com.google.android.apps.calendar.loggers.ClearcutManager;
import com.google.android.apps.calendar.loggers.rlz.RlzConfig;
import com.google.android.apps.calendar.loggers.rlz.RlzTracker;
import com.google.android.apps.calendar.proposenewtime.ProposeNewTimeIntentFactory;
import com.google.android.apps.calendar.proposenewtime.state.TimeProposal;
import com.google.android.apps.calendar.proposenewtime.utils.ProposeNewTimeUtils;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.Utils;
import com.google.android.calendar.alerts.QuickResponseActivity;
import com.google.android.calendar.analytics.ActivationLogger;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventClient;
import com.google.android.calendar.api.event.EventDescriptor;
import com.google.android.calendar.api.event.EventFactory;
import com.google.android.calendar.api.event.EventModifications;
import com.google.android.calendar.api.event.EventPermissionUtils;
import com.google.android.calendar.api.event.EventPermissions;
import com.google.android.calendar.api.event.EventPermissionsFactory;
import com.google.android.calendar.api.event.GooglePrivateDataHelper;
import com.google.android.calendar.api.event.attendee.Attendee;
import com.google.android.calendar.api.event.attendee.AttendeeModifications;
import com.google.android.calendar.api.event.attendee.Response;
import com.google.android.calendar.api.event.location.Address;
import com.google.android.calendar.api.event.location.EventLocation;
import com.google.android.calendar.api.event.location.StructuredLocation;
import com.google.android.calendar.common.dialog.SingleChoiceDialogListener;
import com.google.android.calendar.event.DetailsDialogFragment;
import com.google.android.calendar.event.EventForwardingActivity;
import com.google.android.calendar.event.scope.ScopeSelectionDialog;
import com.google.android.calendar.experimental.ExperimentalOptions;
import com.google.android.calendar.newapi.commandbar.BottomBar;
import com.google.android.calendar.newapi.commandbar.BottomBarController;
import com.google.android.calendar.newapi.commandbar.EventCommandBarController;
import com.google.android.calendar.newapi.commandbar.EveryoneDeclinedBottomBarController;
import com.google.android.calendar.newapi.commandbar.MoreOptionsSheetController;
import com.google.android.calendar.newapi.commandbar.SmartRsvpBottomBarController;
import com.google.android.calendar.newapi.common.Loader;
import com.google.android.calendar.newapi.exchange.ResponseFollowUpController;
import com.google.android.calendar.newapi.exchange.ResponseFollowUpUtils;
import com.google.android.calendar.newapi.logging.LoggingUtils;
import com.google.android.calendar.newapi.model.BasicViewScreenModel;
import com.google.android.calendar.newapi.model.EventViewScreenModel;
import com.google.android.calendar.newapi.model.ViewScreenModel;
import com.google.android.calendar.newapi.model.edit.BasicEditScreenModel;
import com.google.android.calendar.newapi.overflow.EventOverflowMenuController;
import com.google.android.calendar.newapi.overflow.OverflowMenuController;
import com.google.android.calendar.newapi.screen.event.EventDeleteFlow;
import com.google.android.calendar.newapi.screen.event.EventSaveFlow;
import com.google.android.calendar.newapi.screen.event.EventViewSegmentProvider;
import com.google.android.calendar.newapi.segment.attachment.AttachmentViewSegment;
import com.google.android.calendar.newapi.segment.attendee.AttendeeViewSegment;
import com.google.android.calendar.newapi.segment.calendar.CalendarDialog;
import com.google.android.calendar.newapi.segment.calendar.CalendarViewSegment;
import com.google.android.calendar.newapi.segment.gplus.CustomAppViewSegment;
import com.google.android.calendar.newapi.segment.gplus.GooglePlusViewSegment;
import com.google.android.calendar.newapi.segment.location.LocationViewSegment;
import com.google.android.calendar.newapi.segment.note.NoteViewSegment;
import com.google.android.calendar.newapi.segment.notification.EventNotificationViewSegment;
import com.google.android.calendar.newapi.segment.ooo.OooViewSegment;
import com.google.android.calendar.newapi.segment.room.RoomViewSegment;
import com.google.android.calendar.newapi.segment.smartmail.SourceMessageViewSegment;
import com.google.android.calendar.newapi.segment.time.TimeViewSegment;
import com.google.android.calendar.newapi.segment.title.TitleViewSegment;
import com.google.android.calendar.newapi.segment.visibility.VisibilityAvailabilityViewSegment;
import com.google.android.calendar.newapi.utils.LegacyUtils;
import com.google.android.calendar.timely.AddNoteActivity;
import com.google.android.calendar.timely.TimelineEvent;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.utils.AccessibilityUtils;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.android.calendar.utils.flow.Flow;
import com.google.android.calendar.utils.fragment.FragmentUtils;
import com.google.android.calendar.utils.snackbar.SnackbarShower;
import com.google.android.calendar.utils.snackbar.SnackbarUtils;
import com.google.common.base.Absent;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Platform;
import com.google.common.base.Present;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

// Referenced classes of package com.google.android.calendar.newapi.screen:
//            AbstractEventViewScreenController, ResponseSaver, ViewScreenController, EventEditScreenController, 
//            HostDialog, EventViewScreenLoader, ViewScreen, EveryoneDeclinedManager, 
//            ProgressDialog, DuplicateEventEditScreen, ViewScreenOpenCloseHelper

public class EventViewScreenController extends AbstractEventViewScreenController
    implements SingleChoiceDialogListener, com.google.android.calendar.event.scope.ScopeSelectionDialog.OnScopeSelectedCallback, com.google.android.calendar.newapi.commandbar.EventCommandBarController.Callback, com.google.android.calendar.newapi.commandbar.SmartRsvpBottomBarController.Callback, com.google.android.calendar.newapi.overflow.EventOverflowMenuController.Callback, ResponseSaver, com.google.android.calendar.newapi.screen.event.EventDeleteFlow.Listener, com.google.android.calendar.newapi.screen.event.EventSaveFlow.Listener, com.google.android.calendar.newapi.segment.attendee.ProposalAttendeeTileView.Callback, SnackbarShower
{

    private com.google.android.calendar.newapi.segment.calendar.UiCalendarUtils.CalendarPickerFactory calendarPickerFactory;
    private com.google.android.calendar.newapi.screen.event.EventDeleteFlow.Factory deleteFlowFactory;
    private EventClient eventClient;
    public EventDescriptor eventDescriptor;
    private EveryoneDeclinedManager everyoneDeclinedManager;
    public boolean saveInProgress;
    private EventViewSegmentProvider segmentProvider;

    public EventViewScreenController()
    {
        eventClient = CalendarApi.Events;
        segmentProvider = new EventViewSegmentProvider();
        deleteFlowFactory = new com.google.android.calendar.newapi.screen.event.EventDeleteFlow.Factory();
        calendarPickerFactory = new com.google.android.calendar.newapi.segment.calendar.UiCalendarUtils.CalendarPickerFactory();
        saveInProgress = false;
    }

    private final int getCommandBarControllerType$50KKOORFDKNMERRFCTM6ABR1DPI74RR9CGNM6OBCCLN68OBI5TN6ATR1E1KIUSR3E9IMARHF8LR6ARJKAPKMATQJCDP6APBE8DNMST3IDTM6OPBI4H1MURBDC5N68GJ1E9A7IS357C______0()
    {
        if (((BasicViewScreenModel) ((EventViewScreenModel)getModel())).event != null)
        {
            Event event = ((BasicViewScreenModel) ((EventViewScreenModel)getModel())).event;
            boolean flag;
            if (RemoteFeatureConfig.EVERYONE_DECLINED.enabled() && GooglePrivateDataHelper.hasEveryoneDeclined(event) && !GooglePrivateDataHelper.isEveryoneDeclinedDismissed(event))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                return android.support.v4.content.ModernAsyncTask.Status.EVERYONE_DECLINED$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRECLRM2S395TPM6SJ5CLN2UHBMCLN78LJ9CLRL6ORICLIMSGRFDPQ74RRCDHIN4923DTMMQOBECH162SIKF5O6AEO_0;
            }
            if (ExperimentalOptions.isProposeNewTimeEnabled(getContext()) && AccountUtil.isGoogleAccount(((BasicViewScreenModel) ((EventViewScreenModel)getModel())).event.getCalendar().account))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                return android.support.v4.content.ModernAsyncTask.Status.SMART_RSVP$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRECLRM2S395TPM6SJ5CLN2UHBMCLN78LJ9CLRL6ORICLIMSGRFDPQ74RRCDHIN4923DTMMQOBECH162SIKF5O6AEO_0;
            }
        }
        return android.support.v4.content.ModernAsyncTask.Status.REGULAR$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRECLRM2S395TPM6SJ5CLN2UHBMCLN78LJ9CLRL6ORICLIMSGRFDPQ74RRCDHIN4923DTMMQOBECH162SIKF5O6AEO_0;
    }

    private final ResponseFollowUpController getResponseFollowUpController()
    {
        ResponseFollowUpController responsefollowupcontroller = (ResponseFollowUpController)getChildFragmentManager().findFragmentByTag(ResponseFollowUpController.TAG);
        Object obj = responsefollowupcontroller;
        if (responsefollowupcontroller == null)
        {
            obj = (BasicViewScreenModel)getModel();
            ResponseFollowUpController responsefollowupcontroller1 = new ResponseFollowUpController();
            Bundle bundle = new Bundle();
            bundle.putParcelable("EventData", ((android.os.Parcelable) (obj)));
            responsefollowupcontroller1.setArguments(bundle);
            getChildFragmentManager().beginTransaction().add(responsefollowupcontroller1, ResponseFollowUpController.TAG).commit();
            getChildFragmentManager().executePendingTransactions();
            obj = (ResponseFollowUpController)getChildFragmentManager().findFragmentByTag(ResponseFollowUpController.TAG);
        }
        return ((ResponseFollowUpController) (obj));
    }

    private final boolean needsToRecreateCommandBar()
    {
        BottomBarController bottombarcontroller = (BottomBarController)Utils.uncheckedCast(super.commandBarController);
        int i = getCommandBarControllerType$50KKOORFDKNMERRFCTM6ABR1DPI74RR9CGNM6OBCCLN68OBI5TN6ATR1E1KIUSR3E9IMARHF8LR6ARJKAPKMATQJCDP6APBE8DNMST3IDTM6OPBI4H1MURBDC5N68GJ1E9A7IS357C______0();
        if (bottombarcontroller instanceof EveryoneDeclinedBottomBarController)
        {
            return i != android.support.v4.content.ModernAsyncTask.Status.EVERYONE_DECLINED$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRECLRM2S395TPM6SJ5CLN2UHBMCLN78LJ9CLRL6ORICLIMSGRFDPQ74RRCDHIN4923DTMMQOBECH162SIKF5O6AEO_0;
        }
        if (bottombarcontroller instanceof SmartRsvpBottomBarController)
        {
            return i != android.support.v4.content.ModernAsyncTask.Status.SMART_RSVP$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRECLRM2S395TPM6SJ5CLN2UHBMCLN78LJ9CLRL6ORICLIMSGRFDPQ74RRCDHIN4923DTMMQOBECH162SIKF5O6AEO_0;
        }
        return i != android.support.v4.content.ModernAsyncTask.Status.REGULAR$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRECLRM2S395TPM6SJ5CLN2UHBMCLN78LJ9CLRL6ORICLIMSGRFDPQ74RRCDHIN4923DTMMQOBECH162SIKF5O6AEO_0;
    }

    private final void saveResponse(final Response response, final int scope, final boolean dismiss, com.google.android.calendar.api.event.GooglePrivateData.GuestNotification guestnotification)
    {
        if (saveInProgress)
        {
            return;
        }
        saveInProgress = true;
        Object obj = ((BasicViewScreenModel) ((EventViewScreenModel)getModel())).event.getCalendar();
        final Object context = AttendeeUtils.getCurrentAttendee(((BasicViewScreenModel) ((EventViewScreenModel)getModel())).event);
        EventModifications eventmodifications = CalendarApi.EventFactory.modifyEvent(((BasicViewScreenModel) ((EventViewScreenModel)getModel())).event);
        eventmodifications.getAttendeeModifications().setAttendeeResponse(((Attendee) (context)), response);
        Object obj1;
        if (super.mHost == null)
        {
            context = null;
        } else
        {
            context = (FragmentActivity)super.mHost.mActivity;
        }
        if (ActivationLogger.instance == null)
        {
            ActivationLogger.instance = new ActivationLogger(((Context) (context)));
        }
        context = ActivationLogger.instance;
        obj = ((CalendarDescriptor) (obj)).account.name;
        LogUtils.v("ActivationLogger", "userRespondedToInvite", new Object[0]);
        obj1 = ((ActivationLogger) (context)).rlzTracker;
        if (!((RlzTracker) (obj1)).shouldUseFirstUseBroadcast)
        {
            obj1 = RlzTracker.TAG;
            boolean flag;
            if (LogUtils.maxEnabledLogLevel > 2)
            {
                flag = false;
            } else
            if (Log.isLoggable(((String) (obj1)), 2))
            {
                flag = true;
            } else
            {
                flag = Log.isLoggable(((String) (obj1)), 2);
            }
            if (flag)
            {
                LogUtils.v(RlzTracker.TAG, "Received use event, but not broadcasting.", new Object[0]);
            }
        } else
        {
            Object obj2 = ((RlzTracker) (obj1)).config.accessPoints;
            Intent intent = new Intent("com.google.android.partnersetup.ACTION_RLZ_FIRST_USE");
            intent.putStringArrayListExtra("com.google.android.partnersetup.EXTRA_ACCESS_POINTS", new ArrayList(((java.util.Collection) (obj2))));
            intent.setComponent(RlzTracker.RLZ_PING_INTENT_SERVICE);
            obj2 = RlzTracker.TAG;
            boolean flag1;
            if (LogUtils.maxEnabledLogLevel > 2)
            {
                flag1 = false;
            } else
            if (Log.isLoggable(((String) (obj2)), 2))
            {
                flag1 = true;
            } else
            {
                flag1 = Log.isLoggable(((String) (obj2)), 2);
            }
            if (flag1)
            {
                LogUtils.v(RlzTracker.TAG, "Broadcasting intent: %s", new Object[] {
                    intent
                });
            }
            ((RlzTracker) (obj1)).context.sendBroadcast(intent);
            if (!((RlzTracker) (obj1)).sharedPrefs.edit().putBoolean("sent_rlz_first_use_broadcast", true).commit())
            {
                LogUtils.e(RlzTracker.TAG, "Couldn't persist sent_rlz_first_use_broadcast preference. Will send another broadcast on activation after process restart.", new Object[0]);
            }
            obj1.shouldUseFirstUseBroadcast = false;
        }
        obj1 = com.google.android.calendar.CalendarLoggingExtension.AndroidCalendarExtensionProto.ActionType.RESPONDED_EVENT;
        if (((ActivationLogger) (context)).clearcutManager != null)
        {
            ((ActivationLogger) (context)).clearcutManager.logAction(((com.google.android.calendar.CalendarLoggingExtension.AndroidCalendarExtensionProto.ActionType) (obj1)), ((String) (obj)));
        }
        if (getContext() == null)
        {
            context = null;
        } else
        {
            context = getContext().getApplicationContext();
        }
        guestnotification = eventClient.update(eventmodifications, scope, guestnotification);
        response = new _cls1();
        context = CalendarExecutor.MAIN;
        if (response == null)
        {
            throw new NullPointerException();
        } else
        {
            guestnotification.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(guestnotification, response), ((java.util.concurrent.Executor) (context)));
            return;
        }
    }

    private final void showEditScreen(EventEditScreenController eventeditscreencontroller)
    {
        Object obj;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        HostDialog.showWithChildFragment(((Activity) (obj)), super.mFragmentManager, EventEditScreenController.fromViewScreen(eventeditscreencontroller, (EventViewScreenModel)getModel()), null);
    }

    public void createBodySegments(EventViewScreenModel eventviewscreenmodel, List list)
    {
        Object obj;
        android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl;
        ArrayList arraylist;
        FeatureConfig featureconfig;
        boolean flag;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        fragmentmanagerimpl = super.mFragmentManager;
        flag = ExperimentalOptions.isFlingingEnabled$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7CKLK___0();
        arraylist = new ArrayList();
        arraylist.add(new TimeViewSegment(((Context) (obj)), eventviewscreenmodel));
        arraylist.add(new LocationViewSegment(((Context) (obj)), fragmentmanagerimpl, eventviewscreenmodel));
        arraylist.add(new RoomViewSegment(((Context) (obj)), eventviewscreenmodel));
        if (flag)
        {
            EventViewSegmentProvider.addConferenceSegments(arraylist, ((Activity) (obj)), eventviewscreenmodel, this);
        }
        arraylist.add(new SourceMessageViewSegment(((Context) (obj)), eventviewscreenmodel));
        arraylist.add(new EventNotificationViewSegment(((Context) (obj)), eventviewscreenmodel));
        arraylist.add(new GooglePlusViewSegment(((Context) (obj)), eventviewscreenmodel));
        arraylist.add(new CustomAppViewSegment(((Context) (obj)), eventviewscreenmodel));
        if (!flag)
        {
            EventViewSegmentProvider.addConferenceSegments(arraylist, ((Activity) (obj)), eventviewscreenmodel, this);
        }
        arraylist.add(new AttendeeViewSegment(((Activity) (obj)), eventviewscreenmodel, this));
        featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        if (((FeatureConfig)featureconfig).ooo())
        {
            arraylist.add(new OooViewSegment(((Context) (obj)), eventviewscreenmodel));
        }
        arraylist.add(new NoteViewSegment(((Context) (obj)), fragmentmanagerimpl, eventviewscreenmodel));
        arraylist.add(new AttachmentViewSegment(((Context) (obj)), eventviewscreenmodel));
        arraylist.add(new VisibilityAvailabilityViewSegment(((Context) (obj)), eventviewscreenmodel));
        arraylist.add(new CalendarViewSegment(((Context) (obj)), eventviewscreenmodel));
        list.addAll(arraylist);
    }

    public volatile void createBodySegments(ViewScreenModel viewscreenmodel, List list)
    {
        createBodySegments((EventViewScreenModel)viewscreenmodel, list);
    }

    public BottomBarController createCommandBarController(MoreOptionsSheetController moreoptionssheetcontroller)
    {
        switch (getCommandBarControllerType$50KKOORFDKNMERRFCTM6ABR1DPI74RR9CGNM6OBCCLN68OBI5TN6ATR1E1KIUSR3E9IMARHF8LR6ARJKAPKMATQJCDP6APBE8DNMST3IDTM6OPBI4H1MURBDC5N68GJ1E9A7IS357C______0() - 1)
        {
        default:
            return new EventCommandBarController(this, moreoptionssheetcontroller);

        case 1: // '\001'
            return new EveryoneDeclinedBottomBarController(everyoneDeclinedManager);

        case 2: // '\002'
            return new SmartRsvpBottomBarController(this);
        }
    }

    protected final View createHeaderSegment(ViewScreenModel viewscreenmodel)
    {
        return new TitleViewSegment(getContext(), viewscreenmodel);
    }

    public Loader createLoader(boolean flag)
    {
        Context context = getContext();
        TimelineEvent timelineevent = (TimelineEvent)super.model.timelineItem;
        EventDescriptor eventdescriptor = eventDescriptor;
        EventViewScreenModel eventviewscreenmodel;
        if (flag)
        {
            eventviewscreenmodel = (EventViewScreenModel)getModel();
        } else
        {
            eventviewscreenmodel = null;
        }
        return new EventViewScreenLoader(context, timelineevent, eventdescriptor, eventviewscreenmodel);
    }

    public EventViewScreenModel createModel(TimelineEvent timelineevent)
    {
        return new EventViewScreenModel(timelineevent);
    }

    public volatile ViewScreenModel createModel(TimelineItem timelineitem)
    {
        return createModel((TimelineEvent)timelineitem);
    }

    protected final MoreOptionsSheetController createMoreOptionsSheetController()
    {
        return new MoreOptionsSheetController();
    }

    public OverflowMenuController createOverflowMenuController()
    {
        if (((EventViewScreenModel)getModel()).isHolidayEvent())
        {
            return null;
        } else
        {
            return new EventOverflowMenuController(this);
        }
    }

    public ViewScreen createViewScreen()
    {
        return new ViewScreen(getContext());
    }

    protected final String getPrimesLogTag()
    {
        return "EventView";
    }

    public final String getTitle()
    {
        return requireContext().getResources().getString(0x7f1301d7);
    }

    public final void notifyLoadingFinished()
    {
        super.notifyLoadingFinished();
        if (!RemoteFeatureConfig.EVERYONE_DECLINED.enabled()) goto _L2; else goto _L1
_L1:
        Object obj;
        Object obj1;
        Object obj2;
        byte byte0;
        obj1 = everyoneDeclinedManager;
        obj = ((EveryoneDeclinedManager) (obj1)).eventViewScreenController;
        ViewScreen..Lambda._cls0 _lcls0;
        if (((Fragment) (obj)).mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)((Fragment) (obj)).mHost.mActivity;
        }
        if (obj == null) goto _L2; else goto _L3
_L3:
        obj = ((Activity) (obj)).getIntent();
        obj2 = ((Intent) (obj)).getStringExtra("everyoneDeclinedAction");
        if (obj2 == null) goto _L2; else goto _L4
_L4:
        ((Intent) (obj)).removeExtra("everyoneDeclinedAction");
        byte0 = -1;
        ((String) (obj2)).hashCode();
        JVM INSTR lookupswitch 2: default 92
    //                   -1335458389: 359
    //                   505069002: 375;
           goto _L5 _L6 _L7
_L5:
        byte0;
        JVM INSTR tableswitch 0 1: default 116
    //                   0 391
    //                   1 546;
           goto _L8 _L9 _L10
_L8:
        LogUtils.w(EveryoneDeclinedManager.TAG, "Unexpected action", new Object[0]);
        break; /* Loop/switch isn't completed */
_L6:
        if (((String) (obj2)).equals("delete"))
        {
            byte0 = 0;
        }
        continue; /* Loop/switch isn't completed */
_L7:
        if (((String) (obj2)).equals("reschedule"))
        {
            byte0 = 1;
        }
        continue; /* Loop/switch isn't completed */
_L9:
        boolean flag = AttendeeUtils.isOrganizerAndListed(((BasicViewScreenModel) ((EventViewScreenModel)((EveryoneDeclinedManager) (obj1)).eventViewScreenController.getModel())).event);
        Object obj3 = ((EveryoneDeclinedManager) (obj1)).eventViewScreenController.getContext();
        if (flag)
        {
            obj = "delete";
        } else
        {
            obj = "remove";
        }
        LoggingUtils.logEveryoneDeclined(((Context) (obj3)), ((String) (obj)), false, ((BasicViewScreenModel) ((EventViewScreenModel)((EveryoneDeclinedManager) (obj1)).eventViewScreenController.getModel())).event.getAttendees());
        obj3 = ((EveryoneDeclinedManager) (obj1)).eventViewScreenController;
        LoggingUtils.logDeleteClicked(((Fragment) (obj3)).getContext(), ((ViewScreenController) (obj3)).getModel());
        obj1 = new com.google.android.calendar.newapi.screen.event.EventDeleteFlow.Factory..Lambda._cls0(((BasicViewScreenModel) ((EventViewScreenModel)((ViewScreenController) (obj3)).getModel())).event, true);
        if (((Fragment) (obj3)).mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)((Fragment) (obj3)).mHost.mActivity;
        }
        obj = (Flow)FragmentUtils.attachFragment(((Activity) (obj)), ((Fragment) (obj3)).mFragmentManager, com/google/android/calendar/newapi/screen/event/EventDeleteFlow, ((Fragment) (obj3)), null);
        if (obj != null)
        {
            ((Consumer) (obj1)).accept(obj);
        }
          goto _L2
_L10:
        LoggingUtils.logEveryoneDeclined(((EveryoneDeclinedManager) (obj1)).eventViewScreenController.getContext(), "reschedule", false, ((BasicViewScreenModel) ((EventViewScreenModel)((EveryoneDeclinedManager) (obj1)).eventViewScreenController.getModel())).event.getAttendees());
        ((EveryoneDeclinedManager) (obj1)).onReschedule();
_L2:
        if (needsToRecreateCommandBar())
        {
            super.commandBarController = createCommandBarController(super.moreOptionsSheetController);
            obj = super.viewScreen;
            obj2 = super.commandBarController;
            if (obj2 != null)
            {
                obj1 = (ViewGroup)((ViewScreen) (obj)).findViewById(0x7f1002af);
                ((ViewGroup) (obj1)).removeAllViews();
                obj2.commandBar = ((BottomBarController) (obj2)).inflateCommandBar(((ViewScreen) (obj)).getContext(), ((ViewGroup) (obj1)));
                ((BottomBarController) (obj2)).commandBar.initialize(((BottomBarController) (obj2)).getActionsLayout(), ((BottomBarController) (obj2)).getPrimaryActionIds());
                ((BottomBarController) (obj2)).commandBar.setVisibility(4);
                ((BottomBarController) (obj2)).commandBar.listener = ((com.google.android.calendar.newapi.commandbar.BottomBar.OnCommandBarActionClickListener) (obj2));
                ((BottomBarController) (obj2)).setupCommandBar(((BottomBarController) (obj2)).commandBar);
                obj.commandBar = ((BottomBarController) (obj2)).commandBar;
                obj2 = ((ViewScreen) (obj)).commandBar;
                _lcls0 = new ViewScreen..Lambda._cls0(((ViewScreen) (obj)));
                obj2.onHeightChangedListener = _lcls0;
                ((BottomBar) (obj2)).addOnLayoutChangeListener(new com.google.android.calendar.newapi.commandbar.BottomBar..Lambda._cls0(_lcls0));
                ((ViewGroup) (obj1)).addView(((ViewScreen) (obj)).commandBar);
            }
            if (super.commandBarController != null)
            {
                obj = super.commandBarController;
                obj1 = getModel();
                obj.model = obj1;
                ((BottomBarController) (obj)).onModelChanged(obj1);
                super.viewScreen.adjustExtraCommandBarPadding();
            }
        }
        if (RemoteFeatureConfig.EVERYONE_DECLINED.enabled())
        {
            obj = Features.instance;
            if (obj == null)
            {
                throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
            }
            ((FeatureConfig)obj).dogfood_features();
        }
        return;
        if (true) goto _L5; else goto _L11
_L11:
    }

    public final void onActivityResult(int i, int j, Intent intent)
    {
        if (i != 1005) goto _L2; else goto _L1
_L1:
        Object obj2 = everyoneDeclinedManager;
        if (j == -1 && ((EveryoneDeclinedManager) (obj2)).editModel != null)
        {
            Object obj = ((BasicEditScreenModel) (((EveryoneDeclinedManager) (obj2)).editModel)).eventModifications.setToTimedWithTimes(intent.getLongExtra("start_millis", 0L), intent.getLongExtra("end_millis", 0L));
            Object obj4 = CalendarApi.EventPermissionsFactory.create(((Event) (obj))).getAllowedModificationScopes();
            obj2 = ((EveryoneDeclinedManager) (obj2)).eventViewScreenController;
            obj4 = new com.google.android.calendar.newapi.screen.event.EventSaveFlow.Factory..Lambda._cls0(true, ((List) (obj4)), ((EventModifications) (obj)));
            if (((Fragment) (obj2)).mHost == null)
            {
                obj = null;
            } else
            {
                obj = (FragmentActivity)((Fragment) (obj2)).mHost.mActivity;
            }
            obj = (Flow)FragmentUtils.attachFragment(((Activity) (obj)), ((Fragment) (obj2)).mFragmentManager, com/google/android/calendar/newapi/screen/event/EventSaveFlow, ((Fragment) (obj2)), null);
            if (obj != null)
            {
                ((Consumer) (obj4)).accept(obj);
            }
        }
_L9:
        super.onActivityResult(i, j, intent);
        return;
_L2:
        if (i != 1011) goto _L4; else goto _L3
_L3:
        if (j != -1 || intent == null) goto _L6; else goto _L5
_L5:
        Object obj1 = (TimeProposal)intent.getParcelableExtra("propose_new_time_proposal");
        if (obj1 == null || ((TimeProposal) (obj1)).getStartTimeMillis() > ((TimeProposal) (obj1)).getEndTimeMillis()) goto _L6; else goto _L7
_L7:
        if (obj1 != null)
        {
            Object obj3 = new Bundle();
            ((Bundle) (obj3)).putLong("beginTime", ((TimeProposal) (obj1)).getStartTimeMillis());
            ((Bundle) (obj3)).putLong("endTime", ((TimeProposal) (obj1)).getEndTimeMillis());
            obj1 = new Bundle(1);
            ((Bundle) (obj1)).putBundle("ARG_EXTRAS", ((Bundle) (obj3)));
            obj3 = new EventEditScreenController();
            ((Fragment) (obj3)).setArguments(((Bundle) (obj1)));
            showEditScreen(((EventEditScreenController) (obj3)));
        }
        continue; /* Loop/switch isn't completed */
_L6:
        obj1 = null;
        if (true) goto _L7; else goto _L4
_L4:
        if (i == 1012 && j == -1)
        {
            getContext();
            getModel();
        }
        if (true) goto _L9; else goto _L8
_L8:
    }

    public final void onAddNoteClicked()
    {
        Object obj2;
        ResponseFollowUpController responsefollowupcontroller;
        responsefollowupcontroller = getResponseFollowUpController();
        Object obj;
        BasicViewScreenModel basicviewscreenmodel;
        int i;
        if (((Fragment) (responsefollowupcontroller)).mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)((Fragment) (responsefollowupcontroller)).mHost.mActivity;
        }
        basicviewscreenmodel = responsefollowupcontroller.data;
        if (((Fragment) (responsefollowupcontroller)).mHost == null)
        {
            obj2 = null;
        } else
        {
            obj2 = (FragmentActivity)((Fragment) (responsefollowupcontroller)).mHost.mActivity;
        }
        i = basicviewscreenmodel.getColor(((Context) (obj2)));
        obj = new Intent(((Context) (obj)), com/google/android/calendar/timely/AddNoteActivity);
        ((Intent) (obj)).putExtra("color", i);
        responsefollowupcontroller.startActivityForResult(((Intent) (obj)), 1009);
        responsefollowupcontroller.redirectedToFollowUpFlow = true;
        if (responsefollowupcontroller.pendingResponse == null) goto _L2; else goto _L1
_L1:
        obj2 = new StringBuilder();
        responsefollowupcontroller.pendingResponse.status.ordinal();
        JVM INSTR tableswitch 1 3: default 124
    //                   1 153
    //                   2 278
    //                   3 289;
           goto _L2 _L3 _L4 _L5
_L2:
        return;
_L3:
        ((StringBuilder) (obj2)).append("yes");
        break MISSING_BLOCK_LABEL_161;
_L4:
        ((StringBuilder) (obj2)).append("maybe");
          goto _L6
_L5:
        ((StringBuilder) (obj2)).append("no");
_L6:
        ((StringBuilder) (obj2)).append("_");
        Object obj1;
        long l;
        if (responsefollowupcontroller.data.event.getDescriptor().isRecurringPhantom())
        {
            if (responsefollowupcontroller.updateScope == 0)
            {
                ((StringBuilder) (obj2)).append("exception");
            } else
            {
                ((StringBuilder) (obj2)).append("recurring");
            }
        } else
        if (responsefollowupcontroller.data.event.getDescriptor().isRecurringException())
        {
            ((StringBuilder) (obj2)).append("exception");
        } else
        {
            ((StringBuilder) (obj2)).append("single");
        }
        l = responsefollowupcontroller.data.event.getEndMillis() - responsefollowupcontroller.data.event.getStartMillis();
        if (l > 0x5265c00L)
        {
            ((StringBuilder) (obj2)).append("_");
            ((StringBuilder) (obj2)).append("multiday");
        } else
        if (l == 0x5265c00L)
        {
            ((StringBuilder) (obj2)).append("_");
            ((StringBuilder) (obj2)).append("allday");
        }
        obj1 = AnalyticsLoggerHolder.instance;
        if (obj1 == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        AnalyticsLogger analyticslogger = (AnalyticsLogger)obj1;
        if (((Fragment) (responsefollowupcontroller)).mHost == null)
        {
            obj1 = null;
        } else
        {
            obj1 = (FragmentActivity)((Fragment) (responsefollowupcontroller)).mHost.mActivity;
        }
        analyticslogger.trackEvent(((Context) (obj1)), "rsvp_commenting", "add_note", ((StringBuilder) (obj2)).toString(), Long.valueOf(0L));
        return;
    }

    public final void onCopyToClicked(List list)
    {
        Context context = getContext();
        String s = ((EventViewScreenModel)getModel()).getCategory();
        if (context != null)
        {
            AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
            if (analyticslogger == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            ((AnalyticsLogger)analyticslogger).trackEvent(context, s, "copy_pressed");
        }
        boolean flag;
        if (!list.isEmpty())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        } else
        {
            com.google.android.calendar.newapi.segment.calendar.UiCalendarUtils.CalendarPickerFactory.create(getContext(), list, this, 1).show(super.mFragmentManager, CalendarDialog.TAG);
            return;
        }
    }

    public final View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        if (bundle == null) goto _L2; else goto _L1
_L1:
        eventDescriptor = (EventDescriptor)bundle.getParcelable("EventDescriptorKey");
_L4:
        everyoneDeclinedManager = new EveryoneDeclinedManager(this);
        ProgressDialog progressdialog = (ProgressDialog)super.mFragmentManager.findFragmentByTag("ProgressDialog");
        if (progressdialog != null)
        {
            progressdialog.dismissAllowingStateLoss();
        }
        return super.onCreateView(layoutinflater, viewgroup, bundle);
_L2:
        if (getArguments().containsKey("EventDescriptorKey"))
        {
            eventDescriptor = (EventDescriptor)getArguments().getParcelable("EventDescriptorKey");
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final void onDeleteClicked()
    {
        LoggingUtils.logDeleteClicked(getContext(), getModel());
        com.google.android.calendar.newapi.screen.event.EventDeleteFlow.Factory..Lambda._cls0 _lcls0 = new com.google.android.calendar.newapi.screen.event.EventDeleteFlow.Factory..Lambda._cls0(((BasicViewScreenModel) ((EventViewScreenModel)getModel())).event, true);
        Object obj;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        obj = (Flow)FragmentUtils.attachFragment(((Activity) (obj)), super.mFragmentManager, com/google/android/calendar/newapi/screen/event/EventDeleteFlow, this, null);
        if (obj != null)
        {
            _lcls0.accept(obj);
        }
    }

    public void onDialogItemChosen(com.google.android.calendar.newapi.segment.calendar.UiCalendarUtils.UiCalendarListEntry uicalendarlistentry, int i)
    {
        if (i == 1)
        {
            com.google.android.calendar.api.calendarlist.CalendarListEntry calendarlistentry = uicalendarlistentry.value();
            Object obj;
            android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl;
            Event event;
            DuplicateEventEditScreen duplicateeventeditscreen;
            if (super.mHost == null)
            {
                uicalendarlistentry = null;
            } else
            {
                uicalendarlistentry = (FragmentActivity)super.mHost.mActivity;
            }
            fragmentmanagerimpl = super.mFragmentManager;
            event = ((BasicViewScreenModel) ((EventViewScreenModel)getModel())).event;
            duplicateeventeditscreen = new DuplicateEventEditScreen();
            obj = duplicateeventeditscreen.getArguments();
            if (obj == null)
            {
                obj = Absent.INSTANCE;
            } else
            {
                obj = new Present(obj);
            }
            obj = (Bundle)((Optional) (obj)).or(new Bundle());
            ((Bundle) (obj)).putInt("ARG_VIEW_MODE", i);
            ((Bundle) (obj)).putParcelable("ARG_ORIGINAL_EVENT", event);
            ((Bundle) (obj)).putParcelable("ARG_TARGET_CALENDAR", calendarlistentry);
            duplicateeventeditscreen.setArguments(((Bundle) (obj)));
            HostDialog.showWithChildFragment(uicalendarlistentry, fragmentmanagerimpl, duplicateeventeditscreen, null);
        }
    }

    public volatile void onDialogItemChosen(Object obj, int i)
    {
        onDialogItemChosen((com.google.android.calendar.newapi.segment.calendar.UiCalendarUtils.UiCalendarListEntry)obj, i);
    }

    public final void onDuplicateClicked()
    {
        Object obj = getContext();
        Object obj1 = ((EventViewScreenModel)getModel()).getCategory();
        if (obj != null)
        {
            AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
            if (analyticslogger == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            ((AnalyticsLogger)analyticslogger).trackEvent(((Context) (obj)), ((String) (obj1)), "duplicate_pressed");
        }
        com.google.android.calendar.api.calendarlist.CalendarListEntry calendarlistentry = ((EventViewScreenModel)getModel()).getCalendarListEntry();
        android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl;
        Event event;
        DuplicateEventEditScreen duplicateeventeditscreen;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        fragmentmanagerimpl = super.mFragmentManager;
        event = ((BasicViewScreenModel) ((EventViewScreenModel)getModel())).event;
        duplicateeventeditscreen = new DuplicateEventEditScreen();
        obj1 = duplicateeventeditscreen.getArguments();
        if (obj1 == null)
        {
            obj1 = Absent.INSTANCE;
        } else
        {
            obj1 = new Present(obj1);
        }
        obj1 = (Bundle)((Optional) (obj1)).or(new Bundle());
        ((Bundle) (obj1)).putInt("ARG_VIEW_MODE", 2);
        ((Bundle) (obj1)).putParcelable("ARG_ORIGINAL_EVENT", event);
        ((Bundle) (obj1)).putParcelable("ARG_TARGET_CALENDAR", calendarlistentry);
        duplicateeventeditscreen.setArguments(((Bundle) (obj1)));
        HostDialog.showWithChildFragment(((Activity) (obj)), fragmentmanagerimpl, duplicateeventeditscreen, null);
    }

    public final void onEmailGuestsClicked(boolean flag)
    {
        Object obj;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        obj = (new Intent(((Context) (obj)), com/google/android/calendar/alerts/QuickResponseActivity)).putExtra("eventKey", ((BasicViewScreenModel) ((EventViewScreenModel)getModel())).event.getDescriptor().getKey()).putExtra("showQuickResponses", flag).addFlags(0x10000000);
        if (super.mHost == null)
        {
            throw new IllegalStateException((new StringBuilder("Fragment ")).append(this).append(" not attached to Activity").toString());
        } else
        {
            super.mHost.onStartActivityFromFragment(this, ((Intent) (obj)), -1, null);
            return;
        }
    }

    public void onEventDeleted(boolean flag, int i)
    {
        LoggingUtils.logDelete(getContext(), flag, getModel(), i);
        if (flag)
        {
            if (super.mHost != null && super.mAdded)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                ViewScreen viewscreen = super.viewScreen;
                viewscreen.announceForAccessibility(viewscreen.getContext().getText(0x7f1301d4));
            }
            closeViewScreen();
        }
    }

    public final void onForwardEventClicked()
    {
        Object obj1 = null;
        Object obj;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        if (obj == null || ((BasicViewScreenModel) ((EventViewScreenModel)getModel())).event == null)
        {
            return;
        }
        Intent intent;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        intent = (new Intent(((Context) (obj)), com/google/android/calendar/event/EventForwardingActivity)).putExtra("eventDescriptor", ((BasicViewScreenModel) ((EventViewScreenModel)getModel())).event.getDescriptor()).putExtra("calendarDescriptor", ((BasicViewScreenModel) ((EventViewScreenModel)getModel())).event.getCalendar()).addFlags(0x10000000);
        if (super.mHost == null)
        {
            obj = obj1;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        ((FragmentActivity) (obj)).startActivity(intent);
    }

    public final void onGuestProposalClicked(Attendee attendee)
    {
        attendee = ProposeNewTimeIntentFactory.create(getContext(), (EventViewScreenModel)model, (new com.google.android.apps.calendar.proposenewtime.state..AutoValue_ProposeNewTimeState.Builder()).setMode(com.google.android.apps.calendar.proposenewtime.state.ProposeNewTimeState.Mode.REVIEW), attendee);
        attendee.addFlags(0x24000000);
        startActivityForResult(attendee, 1011);
        attendee = AnalyticsLoggerHolder.instance;
        if (attendee == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        } else
        {
            ((AnalyticsLogger)attendee).trackEvent(getContext(), "event_action", "review_time_proposal");
            return;
        }
    }

    public final void onProposeNewTimeChipClosed()
    {
        if (!saveInProgress)
        {
            Object obj = AnalyticsLoggerHolder.instance;
            if (obj == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            ((AnalyticsLogger)obj).trackEvent(getContext(), "event_action", "delete_time_proposal");
            obj = AttendeeUtils.getCurrentAttendee(((BasicViewScreenModel) ((EventViewScreenModel)getModel())).event);
            if (obj == null)
            {
                obj = null;
            } else
            {
                obj = ((Attendee) (obj)).response;
            }
            if (obj == null)
            {
                if (super.commandBarController != null)
                {
                    obj = super.commandBarController;
                    ViewScreenModel viewscreenmodel = getModel();
                    obj.model = viewscreenmodel;
                    ((BottomBarController) (obj)).onModelChanged(viewscreenmodel);
                    super.viewScreen.adjustExtraCommandBarPadding();
                    return;
                }
            } else
            {
                com.google.android.calendar.api.event.attendee.Response.Builder builder = new com.google.android.calendar.api.event.attendee.Response.Builder();
                builder.comment = Platform.nullToEmpty(((Response) (obj)).comment);
                builder.status = ((Response) (obj)).status;
                saveResponse(new Response(builder), 0, false, com.google.android.calendar.api.event.GooglePrivateData.GuestNotification.UNDECIDED);
                return;
            }
        }
    }

    public final void onProposeNewTimeClicked()
    {
        Object obj2 = null;
        Object obj = ((BasicViewScreenModel) ((EventViewScreenModel)getModel())).event;
        if (EventPermissionUtils.isExchangeEvent(((Event) (obj))))
        {
            obj2 = getResponseFollowUpController();
            if (((Fragment) (obj2)).mHost == null)
            {
                obj = null;
            } else
            {
                obj = (FragmentActivity)((Fragment) (obj2)).mHost.mActivity;
            }
            ((Fragment) (obj2)).startActivityForResult(ResponseFollowUpUtils.createProposeTimeIntent(((Context) (obj)), ((ResponseFollowUpController) (obj2)).data), 1006);
            obj2.redirectedToFollowUpFlow = true;
        } else
        if (ExperimentalOptions.isProposeNewTimeEnabled(getContext()) && AccountUtil.isGoogleAccount(((Event) (obj)).getCalendar().account) && !saveInProgress)
        {
            Object obj3 = getResponseFollowUpController();
            Object obj1;
            boolean flag;
            if (((Fragment) (obj3)).mHost == null)
            {
                obj1 = null;
            } else
            {
                obj1 = (FragmentActivity)((Fragment) (obj3)).mHost.mActivity;
            }
            obj1 = ProposeNewTimeIntentFactory.create(((Context) (obj1)), ((ResponseFollowUpController) (obj3)).data, (new com.google.android.apps.calendar.proposenewtime.state..AutoValue_ProposeNewTimeState.Builder()).setMode(com.google.android.apps.calendar.proposenewtime.state.ProposeNewTimeState.Mode.PROPOSE), null);
            ((Intent) (obj1)).addFlags(0x24000000);
            ((Fragment) (obj3)).startActivityForResult(((Intent) (obj1)), 1011);
            obj1 = AttendeeUtils.getCurrentAttendee(((ResponseFollowUpController) (obj3)).data.event);
            if (obj1 == null)
            {
                obj1 = obj2;
            } else
            {
                obj1 = ((Attendee) (obj1)).response;
            }
            flag = ProposeNewTimeUtils.responseHasProposal(((Response) (obj1)));
            obj1 = AnalyticsLoggerHolder.instance;
            if (obj1 == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            obj2 = (AnalyticsLogger)obj1;
            obj3 = ((Fragment) (obj3)).getContext();
            if (flag)
            {
                obj1 = "edit_time_proposal";
            } else
            {
                obj1 = "add_time_proposal";
            }
            ((AnalyticsLogger) (obj2)).trackEvent(((Context) (obj3)), "event_action", ((String) (obj1)));
            return;
        }
    }

    public final void onRsvpClicked(com.google.android.calendar.api.event.attendee.Response.ResponseStatus responsestatus, int i, boolean flag)
    {
        Object obj = new com.google.android.calendar.api.event.attendee.Response.Builder();
        obj.status = responsestatus;
        responsestatus = AttendeeUtils.getCurrentAttendee(((BasicViewScreenModel) ((EventViewScreenModel)getModel())).event);
        if (responsestatus != null)
        {
            Long long1 = ((Attendee) (responsestatus)).response.proposedStartTimeMillis;
            Long long2 = ((Attendee) (responsestatus)).response.proposedEndTimeMillis;
            obj.comment = Platform.nullToEmpty(((Attendee) (responsestatus)).response.comment);
            ((com.google.android.calendar.api.event.attendee.Response.Builder) (obj)).setProposedTime(long1, long2);
        }
        responsestatus = getResponseFollowUpController();
        if (flag)
        {
            obj = new Response(((com.google.android.calendar.api.event.attendee.Response.Builder) (obj)));
            responsestatus.updateScope = i;
            responsestatus.pendingResponse = ((Response) (obj));
            ((ResponseFollowUpController) (responsestatus)).rsvpDelayedSaveHandler.removeCallbacksAndMessages(null);
            obj = ((ResponseFollowUpController) (responsestatus)).rsvpDelayedSaveHandler;
            long l;
            if (((Fragment) (responsestatus)).mHost == null)
            {
                responsestatus = null;
            } else
            {
                responsestatus = (FragmentActivity)((Fragment) (responsestatus)).mHost.mActivity;
            }
            if (AccessibilityUtils.isAccessibilityEnabled(responsestatus))
            {
                l = 0x493e0L;
            } else
            {
                l = 5000L;
            }
            ((Handler) (obj)).sendEmptyMessageDelayed(0, l);
            return;
        }
        Response response = ((ResponseFollowUpController) (responsestatus)).pendingResponse;
        if (response != null)
        {
            ((com.google.android.calendar.api.event.attendee.Response.Builder) (obj)).setProposedTime(response.proposedStartTimeMillis, response.proposedEndTimeMillis).comment = Platform.nullToEmpty(response.comment);
            ((ResponseFollowUpController) (responsestatus)).rsvpDelayedSaveHandler.removeCallbacksAndMessages(null);
            responsestatus.pendingResponse = null;
        }
        saveResponse(new Response(((com.google.android.calendar.api.event.attendee.Response.Builder) (obj))), i, true, com.google.android.calendar.api.event.GooglePrivateData.GuestNotification.ENABLED);
    }

    public void onSaveCompleted(boolean flag, Event event, int i)
    {
        EveryoneDeclinedManager everyonedeclinedmanager = everyoneDeclinedManager;
        if (!flag)
        {
            LoggingUtils.logSaveFailure(everyonedeclinedmanager.eventViewScreenController.getContext(), everyonedeclinedmanager.editModel, i);
            return;
        } else
        {
            everyonedeclinedmanager.eventViewScreenController.updateSegmentsWithUpdatedEvent(event);
            return;
        }
    }

    public final void onSaveInstanceState(Bundle bundle)
    {
        bundle.putParcelable("EventDescriptorKey", eventDescriptor);
        super.onSaveInstanceState(bundle);
    }

    public final void onScopeSelected(int i, com.google.android.calendar.event.scope.ScopeSelectionDialog.Config config)
    {
        com.google.android.calendar.event.scope.ScopeSelectionDialog.OnScopeSelectedCallback onscopeselectedcallback;
        if (super.started)
        {
            if ((onscopeselectedcallback = (com.google.android.calendar.event.scope.ScopeSelectionDialog.OnScopeSelectedCallback)(BottomBarController)Utils.uncheckedCast(super.commandBarController)) != null)
            {
                onscopeselectedcallback.onScopeSelected(i, config);
                return;
            }
        }
    }

    public final void saveResponse(Response response, int i, boolean flag)
    {
        saveResponse(response, i, flag, com.google.android.calendar.api.event.GooglePrivateData.GuestNotification.ENABLED);
    }

    protected final void showEditScreen()
    {
        EventEditScreenController eventeditscreencontroller = new EventEditScreenController();
        Object obj;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        HostDialog.showWithChildFragment(((Activity) (obj)), super.mFragmentManager, EventEditScreenController.fromViewScreen(eventeditscreencontroller, (EventViewScreenModel)getModel()), null);
    }

    public final void showRsvpUpdateScopeSelectionDialog(com.google.android.calendar.api.event.attendee.Response.ResponseStatus responsestatus)
    {
        Object obj = ((BasicViewScreenModel) ((EventViewScreenModel)getModel())).permissions;
        responsestatus.ordinal();
        JVM INSTR tableswitch 1 3: default 44
    //                   1 199
    //                   2 44
    //                   3 207;
           goto _L1 _L2 _L1 _L3
_L1:
        int i = 0x7f130476;
_L7:
        responsestatus.ordinal();
        JVM INSTR tableswitch 1 3: default 80
    //                   1 215
    //                   2 80
    //                   3 223;
           goto _L4 _L5 _L4 _L6
_L4:
        int j = 0x104000a;
_L8:
        Bundle bundle = new Bundle();
        bundle.putInt("ARGUMENT_RSVP_STATUS", responsestatus.ordinal());
        com.google.android.calendar.event.scope.ScopeSelectionDialog.Config.Builder builder = (new com.google.android.calendar.event.scope..AutoValue_ScopeSelectionDialog_Config.Builder()).additionalArguments(new Bundle());
        responsestatus = new ArrayList(((EventPermissions) (obj)).getAllowedModificationScopes());
        Collections.sort(responsestatus);
        obj = com.google.android.calendar.event.scope.ScopeSelectionUtils..Lambda._cls0.$instance;
        if (responsestatus instanceof RandomAccess)
        {
            responsestatus = new com.google.common.collect.Lists.TransformingRandomAccessList(responsestatus, ((com.google.common.base.Function) (obj)));
        } else
        {
            responsestatus = new com.google.common.collect.Lists.TransformingSequentialList(responsestatus, ((com.google.common.base.Function) (obj)));
        }
        ScopeSelectionDialog.newInstance(this, builder.scopes(responsestatus).title(i).positiveButton(j).additionalArguments(bundle).build()).show(super.mFragmentManager, null);
        return;
_L2:
        i = 0x7f130047;
          goto _L7
_L3:
        i = 0x7f130159;
          goto _L7
_L5:
        j = 0x7f130046;
          goto _L8
_L6:
        j = 0x7f130158;
          goto _L8
    }

    public final void showSnackbar(String s, int i)
    {
        SnackbarUtils.showSnackbar(getContext(), super.mView.findViewById(0x7f1002a7), s, 0, null, null, null);
    }

    public final void updateSegmentsWithUpdatedEvent(Event event)
    {
        Object obj2 = null;
        Object obj = ((BasicViewScreenModel) ((EventViewScreenModel)getModel())).event;
        Object obj3;
        Object obj4;
        boolean flag;
        if (((Event) (obj)).getStartMillis() != event.getStartMillis() || ((Event) (obj)).getEndMillis() != event.getEndMillis())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            super.animationHelper.animationData = null;
        }
        eventDescriptor = event.getDescriptor();
        ((EventViewScreenModel)getModel()).setEvent(event);
        obj3 = (TimelineEvent)super.model.timelineItem;
        obj = event.getLocation().getEventLocations().iterator();
        if (((Iterator) (obj)).hasNext())
        {
            obj = ((Iterator) (obj)).next();
        } else
        {
            obj = null;
        }
        obj4 = (EventLocation)obj;
        if (obj4 == null)
        {
            LegacyUtils.updateTimelineEvent(event, null, ((TimelineEvent) (obj3)));
        } else
        {
            Object obj1;
            Object aobj[];
            if (((EventLocation) (obj4)).address == null)
            {
                obj1 = obj2;
            } else
            {
                obj1 = ((EventLocation) (obj4)).address.formattedAddress;
            }
            obj4 = ((EventLocation) (obj4)).name;
            obj2 = (new Joiner(", ")).skipNulls();
            obj4 = Platform.emptyToNull(((String) (obj4)));
            obj1 = Platform.emptyToNull(((String) (obj1)));
            aobj = new Object[0];
            if (aobj == null)
            {
                throw new NullPointerException();
            }
            obj1 = (new com.google.common.base.Joiner._cls3(aobj, obj4, obj1)).iterator();
            LegacyUtils.updateTimelineEvent(event, ((Joiner) (obj2)).appendTo(new StringBuilder(), ((Iterator) (obj1))).toString(), ((TimelineEvent) (obj3)));
        }
        updateSegments();
        if (needsToRecreateCommandBar())
        {
            super.commandBarController = createCommandBarController(super.moreOptionsSheetController);
            event = super.viewScreen;
            obj2 = super.commandBarController;
            if (obj2 != null)
            {
                obj = (ViewGroup)event.findViewById(0x7f1002af);
                ((ViewGroup) (obj)).removeAllViews();
                obj2.commandBar = ((BottomBarController) (obj2)).inflateCommandBar(event.getContext(), ((ViewGroup) (obj)));
                ((BottomBarController) (obj2)).commandBar.initialize(((BottomBarController) (obj2)).getActionsLayout(), ((BottomBarController) (obj2)).getPrimaryActionIds());
                ((BottomBarController) (obj2)).commandBar.setVisibility(4);
                ((BottomBarController) (obj2)).commandBar.listener = ((com.google.android.calendar.newapi.commandbar.BottomBar.OnCommandBarActionClickListener) (obj2));
                ((BottomBarController) (obj2)).setupCommandBar(((BottomBarController) (obj2)).commandBar);
                event.commandBar = ((BottomBarController) (obj2)).commandBar;
                obj2 = ((ViewScreen) (event)).commandBar;
                obj3 = new ViewScreen..Lambda._cls0(event);
                obj2.onHeightChangedListener = ((com.google.android.calendar.newapi.commandbar.BottomBar.OnHeightChanged) (obj3));
                ((BottomBar) (obj2)).addOnLayoutChangeListener(new com.google.android.calendar.newapi.commandbar.BottomBar..Lambda._cls0(((com.google.android.calendar.newapi.commandbar.BottomBar.OnHeightChanged) (obj3))));
                ((ViewGroup) (obj)).addView(((ViewScreen) (event)).commandBar);
            }
        }
        if (super.commandBarController != null)
        {
            event = super.commandBarController;
            obj = getModel();
            event.model = obj;
            event.onModelChanged(obj);
            super.viewScreen.adjustExtraCommandBarPadding();
        }
        if (super.overflowMenuController != null)
        {
            event = super.overflowMenuController;
            obj = getModel();
            event.model = obj;
            event.onModelChanged(((OverflowMenuController) (event)).overflowMenu, obj);
        }
        repositionDialog();
    }

    private class _cls1
        implements FutureCallback
    {

        private final EventViewScreenController this$0;
        private final Context val$context;
        private final boolean val$dismiss;
        private final Response val$response;
        private final int val$scope;

        public final void onFailure(Throwable throwable)
        {
            LoggingUtils.logRsvp(context, false, (EventViewScreenModel)getModel(), scope, response);
            saveInProgress = false;
        }

        public final void onSuccess(Object obj)
        {
            obj = (Event)obj;
            ((TimelineEvent)model.timelineItem).selfAttendeeStatus = response.status;
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
            if (dismiss || obj == null)
            {
                closeViewScreen();
            }
            LoggingUtils.logRsvp(context, true, (EventViewScreenModel)getModel(), scope, response);
            saveInProgress = false;
        }

        _cls1()
        {
            this$0 = EventViewScreenController.this;
            response = response1;
            dismiss = flag;
            context = context1;
            scope = i;
            super();
        }
    }

}
