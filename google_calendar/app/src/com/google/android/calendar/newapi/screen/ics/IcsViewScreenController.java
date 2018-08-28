// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.ics;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.view.View;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.Utils;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListClientFutureImpl;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventDescriptor;
import com.google.android.calendar.api.event.EventModifications;
import com.google.android.calendar.common.dialog.SingleChoiceDialogListener;
import com.google.android.calendar.common.view.overlay.OverlayFragment;
import com.google.android.calendar.event.DetailsDialogFragment;
import com.google.android.calendar.ical.ICalEventOperation;
import com.google.android.calendar.ical.ICalTimelineEvent;
import com.google.android.calendar.ical.ICalUtils;
import com.google.android.calendar.newapi.commandbar.BottomBarController;
import com.google.android.calendar.newapi.commandbar.MoreOptionsSheetController;
import com.google.android.calendar.newapi.common.Loader;
import com.google.android.calendar.newapi.model.BasicViewScreenModel;
import com.google.android.calendar.newapi.model.CalendarList;
import com.google.android.calendar.newapi.model.EventViewScreenModel;
import com.google.android.calendar.newapi.model.ViewScreenModel;
import com.google.android.calendar.newapi.overflow.OverflowMenuController;
import com.google.android.calendar.newapi.screen.EventViewScreenController;
import com.google.android.calendar.newapi.screen.ViewScreen;
import com.google.android.calendar.newapi.screen.ViewScreenController;
import com.google.android.calendar.newapi.segment.attendee.AttendeeViewSegment;
import com.google.android.calendar.newapi.segment.calendar.CalendarDialog;
import com.google.android.calendar.newapi.segment.location.LocationViewSegment;
import com.google.android.calendar.newapi.segment.note.NoteViewSegment;
import com.google.android.calendar.newapi.segment.notification.EventNotificationViewSegment;
import com.google.android.calendar.newapi.segment.time.TimeViewSegment;
import com.google.android.calendar.newapi.segment.visibility.VisibilityAvailabilityViewSegment;
import com.google.android.calendar.timely.TimelineEvent;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.animations.EventInfoAnimationData;
import com.google.android.calendar.timely.settings.data.CalendarProperties;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ForwardingFluentFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.calendar.newapi.screen.ics:
//            IcsImporter, IcsUtils, IcsViewScreenModel, IcsCommandBarController, 
//            IcsViewScreenLoader, OverwriteConfirmationDialog

public final class IcsViewScreenController extends EventViewScreenController
    implements SingleChoiceDialogListener, com.google.android.calendar.newapi.screen.event.EventSaveFlow.Listener, IcsCommandBarController.Callback, IcsImporter.IcsImportCompleteListener, OverwriteConfirmationDialog.Listener
{

    private com.google.android.calendar.newapi.segment.calendar.UiCalendarUtils.CalendarPickerFactory calendarPickerFactory;
    private IcsImporter icsImporter;
    private IcsUtils utils;

    public IcsViewScreenController()
    {
        icsImporter = new IcsImporter(CalendarApi.Events);
        utils = new IcsUtils();
        calendarPickerFactory = new com.google.android.calendar.newapi.segment.calendar.UiCalendarUtils.CalendarPickerFactory();
    }

    private final void chooseCalendar()
    {
        com.google.common.base.Predicates.NotPredicate notpredicate;
        Iterator iterator;
        if (((IcsViewScreenModel)getModel()).getCalendarList().calendars.isEmpty())
        {
            LogUtils.e("ViewScreenController", "ICS Import: No writable calendar found.", new Object[0]);
            return;
        }
        boolean flag;
        if (((IcsViewScreenModel)getModel()).getCalendarList().calendars.size() > 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            Object obj = (CalendarListEntry)((IcsViewScreenModel)getModel()).getCalendarList().calendars.get(0);
            IcsImporter icsimporter = icsImporter;
            ICalEventOperation icaleventoperation = (ICalEventOperation)getArguments().getParcelable("ICS_OPERATION");
            if (icsimporter.calendarClient != null)
            {
                obj = icsimporter.calendarClient.read(CalendarDescriptor.createWithoutLocalId(((CalendarListEntry) (obj)).getDescriptor().account, ((CalendarListEntry) (obj)).getDescriptor().calendarId));
            } else
            if (obj == null)
            {
                obj = com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
            } else
            {
                obj = new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(obj);
            }
            if (obj instanceof FluentFuture)
            {
                obj = (FluentFuture)obj;
            } else
            {
                obj = new ForwardingFluentFuture(((ListenableFuture) (obj)));
            }
            obj = (FluentFuture)AbstractTransformFuture.create(((ListenableFuture) (obj)), new IcsImporter..Lambda._cls0(icsimporter, icaleventoperation), new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.DISK));
            ((ListenableFuture) (obj)).addListener(new IcsImporter..Lambda._cls1(((ListenableFuture) (obj)), this), CalendarExecutor.MAIN);
            return;
        }
        ImmutableList immutablelist = ((ICalEventOperation)getArguments().getParcelable("ICS_OPERATION")).events();
        class .Lambda._cls0
            implements Predicate
        {

            public static final Predicate $instance = new .Lambda._cls0();

            public final boolean apply(Object obj2)
            {
                return ((EventModifications)obj2).isNewEvent();
            }


            private .Lambda._cls0()
            {
            }
        }

        notpredicate = new com.google.common.base.Predicates.NotPredicate(.Lambda._cls0..instance);
        iterator = immutablelist.iterator();
        if (iterator == null)
        {
            throw new NullPointerException();
        }
        if (notpredicate == null)
        {
            throw new NullPointerException();
        }
_L4:
        if (!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        Object obj1 = iterator.next();
        if (!notpredicate.apply(obj1)) goto _L4; else goto _L3
_L3:
        obj1 = (EventModifications)obj1;
        if (obj1 == null)
        {
            com.google.android.calendar.newapi.segment.calendar.UiCalendarUtils.CalendarPickerFactory.create(getContext(), ((IcsViewScreenModel)getModel()).getCalendarList().calendars, this, 4).show(super.mFragmentManager, CalendarDialog.TAG);
            return;
        }
        break; /* Loop/switch isn't completed */
_L2:
        obj1 = null;
        if (true) goto _L3; else goto _L5
_L5:
        obj1 = ((EventModifications) (obj1)).getCalendarListEntry();
        IcsImporter icsimporter1 = icsImporter;
        ICalEventOperation icaleventoperation1 = (ICalEventOperation)getArguments().getParcelable("ICS_OPERATION");
        if (icsimporter1.calendarClient != null)
        {
            obj1 = icsimporter1.calendarClient.read(CalendarDescriptor.createWithoutLocalId(((CalendarListEntry) (obj1)).getDescriptor().account, ((CalendarListEntry) (obj1)).getDescriptor().calendarId));
        } else
        if (obj1 == null)
        {
            obj1 = com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
        } else
        {
            obj1 = new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(obj1);
        }
        if (obj1 instanceof FluentFuture)
        {
            obj1 = (FluentFuture)obj1;
        } else
        {
            obj1 = new ForwardingFluentFuture(((ListenableFuture) (obj1)));
        }
        obj1 = (FluentFuture)AbstractTransformFuture.create(((ListenableFuture) (obj1)), new IcsImporter..Lambda._cls0(icsimporter1, icaleventoperation1), new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.DISK));
        ((ListenableFuture) (obj1)).addListener(new IcsImporter..Lambda._cls1(((ListenableFuture) (obj1)), this), CalendarExecutor.MAIN);
        return;
    }

    public static ViewScreenController forEvent(ICalTimelineEvent icaltimelineevent, EventInfoAnimationData eventinfoanimationdata)
    {
        Bundle bundle = new Bundle();
        bundle.putParcelable("ICS_OPERATION", icaltimelineevent.operation);
        IcsViewScreenController icsviewscreencontroller = new IcsViewScreenController();
        icsviewscreencontroller.setArguments(bundle);
        prepare(icsviewscreencontroller, icaltimelineevent, eventinfoanimationdata, null);
        return icsviewscreencontroller;
    }

    private final void updateActivityTimeline()
    {
        Object obj;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        if (!(obj instanceof DataSetChangedListener))
        {
            LogUtils.e("ViewScreenController", "Expected Activity of type DataSetChangedListener. Found %s", new Object[] {
                obj
            });
            return;
        } else
        {
            ((DataSetChangedListener)obj).onDataSetChanged();
            return;
        }
    }

    protected final void createBodySegments(EventViewScreenModel eventviewscreenmodel, List list)
    {
        Object obj1 = null;
        if (!((IcsViewScreenModel)getModel()).showSimplifiedScreen())
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
        list.add(new TimeViewSegment(((Context) (obj)), eventviewscreenmodel));
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        list.add(new LocationViewSegment(((Context) (obj)), super.mFragmentManager, eventviewscreenmodel));
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        list.add(new EventNotificationViewSegment(((Context) (obj)), eventviewscreenmodel));
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        list.add(new AttendeeViewSegment(((android.app.Activity) (obj)), eventviewscreenmodel, null));
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        list.add(new NoteViewSegment(((Context) (obj)), super.mFragmentManager, eventviewscreenmodel));
        if (super.mHost == null)
        {
            obj = obj1;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        list.add(new VisibilityAvailabilityViewSegment(((Context) (obj)), eventviewscreenmodel));
    }

    protected final volatile void createBodySegments(ViewScreenModel viewscreenmodel, List list)
    {
        createBodySegments((EventViewScreenModel)viewscreenmodel, list);
    }

    protected final BottomBarController createCommandBarController(MoreOptionsSheetController moreoptionssheetcontroller)
    {
        if (((IcsViewScreenModel)getModel()).showSimplifiedScreen() || ICalUtils.isUpdate(((IcsViewScreenModel)getModel()).importType))
        {
            return (BottomBarController)Utils.uncheckedCast(new IcsCommandBarController(this));
        } else
        {
            return super.createCommandBarController(moreoptionssheetcontroller);
        }
    }

    public final Loader createLoader(boolean flag)
    {
        return new IcsViewScreenLoader(getContext(), super.eventDescriptor, (IcsViewScreenModel)getModel());
    }

    public final EventViewScreenModel createModel(TimelineEvent timelineevent)
    {
        if (timelineevent instanceof ICalTimelineEvent)
        {
            ICalEventOperation icaleventoperation = ((ICalTimelineEvent)timelineevent).operation;
            timelineevent = new IcsViewScreenModel(icaleventoperation.getImportType(), timelineevent);
            timelineevent.setEvent((Event)icaleventoperation.events().get(0));
            return timelineevent;
        } else
        {
            return new IcsViewScreenModel(((ICalEventOperation)getArguments().getParcelable("ICS_OPERATION")).getImportType(), timelineevent);
        }
    }

    public final volatile ViewScreenModel createModel(TimelineItem timelineitem)
    {
        return createModel((TimelineEvent)timelineitem);
    }

    protected final OverflowMenuController createOverflowMenuController()
    {
        if (((IcsViewScreenModel)getModel()).showSimplifiedScreen())
        {
            return null;
        } else
        {
            return super.createOverflowMenuController();
        }
    }

    protected final ViewScreen createViewScreen()
    {
        ViewScreen viewscreen = super.createViewScreen();
        if (!isFullScreen(requireContext().getResources()))
        {
            boolean flag;
            if (getLoadingBackground().gravity == 80)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                View view = viewscreen.findViewById(0x7f100269);
                com.google.android.calendar.tiles.view.Tile.Dimensions dimensions = com.google.android.calendar.tiles.view.Tile.Dimensions.MEDIUM_TILE_MIN_HEIGHT;
                FragmentActivity fragmentactivity;
                if (super.mHost == null)
                {
                    fragmentactivity = null;
                } else
                {
                    fragmentactivity = (FragmentActivity)super.mHost.mActivity;
                }
                view.setMinimumHeight(fragmentactivity.getResources().getDimensionPixelOffset(dimensions.dimenResId) * 4);
                viewscreen.contentView.requestLayout();
            }
        }
        return viewscreen;
    }

    public final ViewScreenModel getModel()
    {
        return (IcsViewScreenModel)Utils.uncheckedCast(super.getModel());
    }

    public final void onDialogItemChosen(com.google.android.calendar.newapi.segment.calendar.UiCalendarUtils.UiCalendarListEntry uicalendarlistentry, int i)
    {
        if (i == 4)
        {
            uicalendarlistentry = uicalendarlistentry.value();
            IcsImporter icsimporter = icsImporter;
            ICalEventOperation icaleventoperation = (ICalEventOperation)getArguments().getParcelable("ICS_OPERATION");
            if (icsimporter.calendarClient != null)
            {
                uicalendarlistentry = icsimporter.calendarClient.read(CalendarDescriptor.createWithoutLocalId(uicalendarlistentry.getDescriptor().account, uicalendarlistentry.getDescriptor().calendarId));
            } else
            if (uicalendarlistentry == null)
            {
                uicalendarlistentry = com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
            } else
            {
                uicalendarlistentry = new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(uicalendarlistentry);
            }
            if (uicalendarlistentry instanceof FluentFuture)
            {
                uicalendarlistentry = (FluentFuture)uicalendarlistentry;
            } else
            {
                uicalendarlistentry = new ForwardingFluentFuture(uicalendarlistentry);
            }
            uicalendarlistentry = (FluentFuture)AbstractTransformFuture.create(uicalendarlistentry, new IcsImporter..Lambda._cls0(icsimporter, icaleventoperation), new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.DISK));
            uicalendarlistentry.addListener(new IcsImporter..Lambda._cls1(uicalendarlistentry, this), CalendarExecutor.MAIN);
            return;
        } else
        {
            super.onDialogItemChosen(uicalendarlistentry, i);
            return;
        }
    }

    public final volatile void onDialogItemChosen(Object obj, int i)
    {
        onDialogItemChosen((com.google.android.calendar.newapi.segment.calendar.UiCalendarUtils.UiCalendarListEntry)obj, i);
    }

    public final void onEventDeleted(boolean flag, int i)
    {
        if (flag)
        {
            updateActivityTimeline();
        }
        super.onEventDeleted(flag, i);
    }

    public final void onIcsImportComplete(IcsImporter.ImportResult importresult)
    {
        Object obj;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        if (obj == null)
        {
            return;
        }
        if (importresult.event == null)
        {
            LogUtils.e("ViewScreenController", "ICS Import: failed.", new Object[0]);
            int i;
            if (importresult.updateOperation)
            {
                i = 0x7f130303;
            } else
            {
                i = 0x7f1302fe;
            }
            ICalUtils.showSnackbar(((android.app.Activity) (obj)), i, 1);
            return;
        }
        int j;
        if (importresult.updateOperation)
        {
            j = 0x7f130304;
        } else
        {
            j = 0x7f1302ff;
        }
        ICalUtils.showSnackbar(((android.app.Activity) (obj)), j, 1);
        ((IcsViewScreenModel)getModel()).importType = 5;
        ((IcsViewScreenModel)getModel()).setEvent(importresult.event);
        super.eventDescriptor = importresult.event.getDescriptor();
        setLoader(createLoader(false));
        super.loader.load();
        importresult = importresult.event.getCalendar();
        obj = CalendarProperties.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("CalendarProperties#initialize(...) must be called first"));
        } else
        {
            ((CalendarProperties)obj).setDefaultCalendarIdValue(importresult, true);
            updateActivityTimeline();
            return;
        }
    }

    public final void onImportClicked()
    {
        if (((IcsViewScreenModel)getModel()).importType == 7)
        {
            OverwriteConfirmationDialog overwriteconfirmationdialog = new OverwriteConfirmationDialog();
            overwriteconfirmationdialog.setTargetFragment(this, 0);
            overwriteconfirmationdialog.show(super.mFragmentManager, OverwriteConfirmationDialog.TAG);
            return;
        } else
        {
            chooseCalendar();
            return;
        }
    }

    public final void onLoadingSuccess(Loader loader)
    {
        boolean flag;
        if (super.eventDescriptor != null && ((IcsViewScreenModel)getModel()).showSimplifiedScreen())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            IcsViewScreenModel icsviewscreenmodel = (IcsViewScreenModel)Utils.uncheckedCast(super.loader.getResult());
            ((TimelineEvent)super.model.timelineItem).selfAttendeeStatus = com.google.android.calendar.api.event.attendee.Response.ResponseStatus.ACCEPTED;
            ((TimelineEvent)super.model.timelineItem).eventKey = ((BasicViewScreenModel) (icsviewscreenmodel)).event.getDescriptor().getKey();
            ((IcsViewScreenModel)getModel()).setTimelineItem((TimelineEvent)super.model.timelineItem);
            setupCommandBarController();
            setupOverflowMenuController();
        }
        super.onLoadingSuccess(loader);
    }

    public final void onOverwriteConfirmed()
    {
        chooseCalendar();
    }

    public final void onSaveCompleted(boolean flag, Event event, int i)
    {
        onIcsImportComplete(new IcsImporter.ImportResult(event, false));
    }

    private class DataSetChangedListener
    {

        public abstract void onDataSetChanged();
    }

}
