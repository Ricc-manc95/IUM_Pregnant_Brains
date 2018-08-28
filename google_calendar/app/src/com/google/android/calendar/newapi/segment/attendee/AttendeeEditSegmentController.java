// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.attendee;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.api.util.attendee.AttendeeUtils;
import com.google.android.apps.calendar.loggers.CalendarClientLogger;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.color.EntityColor;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventModifications;
import com.google.android.calendar.api.event.EventPermissions;
import com.google.android.calendar.api.event.attendee.Attendee;
import com.google.android.calendar.api.event.attendee.AttendeeModifications;
import com.google.android.calendar.api.event.attendee.AttendeePermissions;
import com.google.android.calendar.event.edit.InvalidDatesChosenDialog;
import com.google.android.calendar.newapi.logging.EventEditLogMetrics;
import com.google.android.calendar.newapi.model.CalendarList;
import com.google.android.calendar.newapi.model.edit.BasicEditScreenModel;
import com.google.android.calendar.newapi.model.edit.EditScreenModel;
import com.google.android.calendar.newapi.model.edit.EventEditScreenModel;
import com.google.android.calendar.newapi.screen.EditScreenController;
import com.google.android.calendar.newapi.screen.SegmentMap;
import com.google.android.calendar.newapi.segment.attendee.fullscreen.AttendeeEditFullScreenController;
import com.google.android.calendar.newapi.segment.common.EditSegmentController;
import com.google.android.calendar.newapi.segment.common.FullScreenEditSegmentController;
import com.google.android.calendar.newapi.segment.common.SegmentController;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.timely.findatime.FindTimeIntentFactory;
import com.google.android.calendar.utils.permission.AndroidPermissionUtils;
import com.google.android.calendar.viewedit.segment.edit.EditSegment;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.google.android.calendar.newapi.segment.attendee:
//            AttendeesUtils, AttendeeEditSegment, AttendeesResult

public class AttendeeEditSegmentController extends FullScreenEditSegmentController
    implements AttendeeEditSegment.Listener, com.google.android.calendar.newapi.segment.common.fullscreen.EditFullScreenController.OnFullScreenResultListener
{

    private static final String TAG = com/google/android/calendar/newapi/segment/attendee/AttendeeEditSegmentController.getSimpleName();
    private int currentFindTimeState;

    public AttendeeEditSegmentController()
    {
        currentFindTimeState = 0;
    }

    private final List getFilteredAttendees()
    {
        Object obj = ((BasicEditScreenModel) ((EventEditScreenModel)super.model)).eventModifications;
        class .Lambda._cls0
            implements Predicate
        {

            private final Event arg$1;

            public final boolean apply(Object obj1)
            {
                return AttendeeEditSegmentController.lambda$getAttendeeFilter$0$AttendeeEditSegmentController(arg$1, (Attendee)obj1);
            }

            .Lambda._cls0(Event event)
            {
                arg$1 = event;
            }
        }

        obj = new ArrayList(Collections2.filter(((EventModifications) (obj)).getAttendees(), new .Lambda._cls0(((Event) (obj)))));
        Collections.sort(((List) (obj)), AttendeesUtils.DEFAULT_ATTENDEE_NAME_COMPARATOR);
        return ((List) (obj));
    }

    static final boolean lambda$getAttendeeFilter$0$AttendeeEditSegmentController(Event event, Attendee attendee)
    {
        Object obj;
        Iterator iterator;
        obj = event.getCalendar().calendarId;
        event = event.getAttendees();
        obj = new com.google.android.apps.calendar.api.util.attendee.AttendeeUtils..Lambda._cls1(((String) (obj)));
        iterator = event.iterator();
        if (iterator == null)
        {
            throw new NullPointerException();
        }
        if (obj == null)
        {
            throw new NullPointerException();
        }
_L4:
        if (!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        event = ((Event) (iterator.next()));
        if (!((Predicate) (obj)).apply(event)) goto _L4; else goto _L3
_L3:
        event = (Attendee)event;
        if (!AttendeeUtils.isPerson(attendee)) goto _L6; else goto _L5
_L5:
        if (event == null) goto _L8; else goto _L7
_L7:
        boolean flag;
        if (event == null && attendee == null)
        {
            flag = true;
        } else
        if (event == null || attendee == null)
        {
            flag = false;
        } else
        {
            flag = AttendeeUtils.isSameAttendee(((Attendee) (event)).attendeeDescriptor, attendee.attendeeDescriptor);
        }
        if (flag) goto _L6; else goto _L8
_L8:
        return true;
_L2:
        event = null;
        continue; /* Loop/switch isn't completed */
_L6:
        return false;
        if (true) goto _L3; else goto _L9
_L9:
    }

    private final void openFullScreenExperience()
    {
        List list1 = getFilteredAttendees();
        List list;
        if (!((EventEditScreenModel)super.model).permissions.getAttendeePermissions().canRemoveAttendees())
        {
            list = ((BasicEditScreenModel) ((EventEditScreenModel)super.model)).eventModifications.getAttendeeModifications().getOriginal();
        } else
        {
            list = null;
        }
        openInFullScreen(AttendeeEditFullScreenController.newInstance(list1, list, ((EventEditScreenModel)super.model).permissions.canModifyCanAttendeesAddAttendees(), ((BasicEditScreenModel) ((EventEditScreenModel)super.model)).eventModifications.canAttendeesAddAttendees(), ((EventEditScreenModel)super.model).getCalendarListEntry().getDescriptor().account, ((EventEditScreenModel)super.model).getColor().getValue()));
    }

    private final void updateUi()
    {
        boolean flag = true;
        int l = 0;
        View view = super.view;
        boolean flag1 = ((EventEditScreenModel)super.model).permissions.getAttendeePermissions().canAddAttendees();
        if (view != null)
        {
            int i;
            if (flag1)
            {
                i = 0;
            } else
            {
                i = 8;
            }
            view.setVisibility(i);
        }
        if (flag1)
        {
            Object obj1 = ((BasicEditScreenModel) ((EventEditScreenModel)super.model)).eventModifications;
            Object obj = ((EventModifications) (obj1)).getCalendarListEntry().getDescriptor();
            CalendarList calendarlist = ((BasicEditScreenModel) ((EventEditScreenModel)super.model)).calendarList;
            int j;
            int k;
            if (calendarlist.fatSupportMap == null)
            {
                obj = null;
            } else
            {
                obj = (Boolean)calendarlist.fatSupportMap.get(obj);
            }
            if (obj == null)
            {
                LogUtils.wtf(TAG, "Find a time support was not loaded.", new Object[0]);
                j = 0;
            } else
            if (!((Boolean) (obj)).booleanValue())
            {
                j = 0;
            } else
            if (((EventModifications) (obj1)).getAttendees().isEmpty())
            {
                j = 0;
            } else
            if (getFilteredAttendees().isEmpty())
            {
                j = 0;
            } else
            if (((EventModifications) (obj1)).isNewEvent() && ((EventModifications) (obj1)).isRecurring())
            {
                j = 0;
            } else
            if (((EventModifications) (obj1)).isAllDayEvent())
            {
                j = 0;
            } else
            if (((EventModifications) (obj1)).isEndTimeUnspecified())
            {
                j = 0;
            } else
            {
                long l1 = ((EventModifications) (obj1)).getEndMillis() - ((EventModifications) (obj1)).getStartMillis();
                if (l1 == 0L)
                {
                    j = 0;
                } else
                if (l1 > 0x5265c00L)
                {
                    j = 0;
                } else
                {
                    obj = ((EventEditScreenModel)super.model).permissions;
                    if (!((EventPermissions) (obj)).canModifyStartTime() || !((EventPermissions) (obj)).canModifyEndTime())
                    {
                        j = 0;
                    } else
                    {
                        j = 1;
                    }
                }
            }
            if (j == 0)
            {
                j = 0;
            } else
            if (((EventEditScreenModel)super.model).isFindTimeButtonClicked)
            {
                j = 2;
            } else
            {
                j = 1;
            }
            if (j != currentFindTimeState)
            {
                obj = getContext();
                if (obj != null)
                {
                    obj = CalendarClientLogger.getInstance(((android.content.Context) (obj)));
                    if (j == 0)
                    {
                        obj1 = ((EventEditScreenModel)super.model).eventReferenceId;
                        ((CalendarClientLogger) (obj)).log(((EventEditScreenModel)super.model).getCalendarListEntry().getDescriptor().account, ((CalendarClientLogger) (obj)).getFindTimeProto(com.google.calendar.client.events.logging.CalendarClientLogEvent.Type.FIND_TIME_BUTTON_HIDDEN, null, 0x80000000, false, null, null, null, null, null, ((String) (obj1))));
                    } else
                    if (currentFindTimeState != 0)
                    {
                        ((EventEditScreenModel)super.model).logMetrics.findTimeButtonShown = true;
                        obj1 = ((EventEditScreenModel)super.model).eventReferenceId;
                        ((CalendarClientLogger) (obj)).log(((EventEditScreenModel)super.model).getCalendarListEntry().getDescriptor().account, ((CalendarClientLogger) (obj)).getFindTimeProto(com.google.calendar.client.events.logging.CalendarClientLogEvent.Type.FIND_TIME_BUTTON_SHOWN, null, 0x80000000, false, null, null, null, null, null, ((String) (obj1))));
                    }
                    currentFindTimeState = j;
                }
            }
            obj = (AttendeeEditSegment)super.view;
            obj1 = getFilteredAttendees();
            if (((List) (obj1)).isEmpty())
            {
                obj1 = ((AttendeeEditSegment) (obj)).attendeeTile;
                ((TextTileView) (obj1)).setPrimaryText(new CharSequence[] {
                    ((TextTileView) (obj1)).getResources().getString(0x7f130091, new Object[0])
                });
                ((TextTileView) (obj1)).setPrimaryTextColor(((AttendeeEditSegment) (obj)).getResources().getColor(0x7f0d00ab));
            } else
            {
                CharSequence acharsequence[] = new CharSequence[((List) (obj1)).size()];
                for (k = 0; k < ((List) (obj1)).size(); k++)
                {
                    acharsequence[k] = AttendeesUtils.getAttendeeName((Attendee)((List) (obj1)).get(k));
                }

                ((AttendeeEditSegment) (obj)).attendeeTile.setPrimaryText(new CharSequence[] {
                    TextUtils.join(", ", acharsequence)
                }).setPrimaryTextColor(((AttendeeEditSegment) (obj)).getResources().getColor(0x7f0d0309));
            }
            obj1 = ((AttendeeEditSegment) (obj)).findTimeTile;
            if (j != 0)
            {
                k = ((flag) ? 1 : 0);
            } else
            {
                k = 0;
            }
            if (obj1 != null)
            {
                if (k == 0)
                {
                    l = 8;
                }
                ((View) (obj1)).setVisibility(l);
            }
            if (k != 0)
            {
                obj1 = ((AttendeeEditSegment) (obj)).getContext();
                if (j == 2)
                {
                    j = 0x7f0d021a;
                } else
                {
                    j = 0x7f0d01d7;
                }
                j = ContextCompat.getColor(((android.content.Context) (obj1)), j);
                ((AttendeeEditSegment) (obj)).findTimeTile.setPrimaryTextColor(j);
                return;
            }
        }
    }

    public final View createView(LayoutInflater layoutinflater)
    {
        layoutinflater = (AttendeeEditSegment)layoutinflater.inflate(0x7f0500bf, null);
        layoutinflater.mListener = this;
        return layoutinflater;
    }

    public final void onActivityResult(int i, int j, Intent intent)
    {
        if (i != 1005 || j != -1) goto _L2; else goto _L1
_L1:
        com.google.android.calendar.newapi.screen.EditScreenController..Lambda._cls2 _lcls2;
        Iterator iterator;
        ((BasicEditScreenModel) ((EventEditScreenModel)super.model)).eventModifications.setToTimedWithTimes(intent.getLongExtra("start_millis", 0L), intent.getLongExtra("end_millis", 0L));
        intent = super.editScreenController;
        _lcls2 = new com.google.android.calendar.newapi.screen.EditScreenController..Lambda._cls2(false, false);
        iterator = ((EditScreenController) (intent)).segments.segmentControllers.values().iterator();
_L12:
        if (!iterator.hasNext()) goto _L4; else goto _L3
_L3:
        EditSegmentController editsegmentcontroller;
        android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl;
        editsegmentcontroller = (EditSegmentController)iterator.next();
        if (editsegmentcontroller == this || editsegmentcontroller != null && editsegmentcontroller.equals(this))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (editsegmentcontroller == null) goto _L6; else goto _L5
_L5:
        fragmentmanagerimpl = ((Fragment) (editsegmentcontroller)).mFragmentManager;
        if (editsegmentcontroller == null) goto _L8; else goto _L7
_L7:
        if (((Fragment) (editsegmentcontroller)).mHost != null && ((Fragment) (editsegmentcontroller)).mAdded)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0) goto _L9; else goto _L8
_L8:
        i = 0;
_L10:
        if (i != 0)
        {
            _lcls2.accept(editsegmentcontroller);
        }
        continue; /* Loop/switch isn't completed */
_L9:
        if (((Fragment) (editsegmentcontroller)).mHost == null)
        {
            intent = null;
        } else
        {
            intent = (FragmentActivity)((Fragment) (editsegmentcontroller)).mHost.mActivity;
        }
        if (intent == null || intent.isDestroyed() || intent.isFinishing())
        {
            i = 0;
            continue; /* Loop/switch isn't completed */
        }
        if (fragmentmanagerimpl != null && !fragmentmanagerimpl.isDestroyed())
        {
            i = 1;
            continue; /* Loop/switch isn't completed */
        }
_L6:
        i = 0;
        if (true) goto _L10; else goto _L2
_L2:
        super.onActivityResult(i, j, intent);
_L4:
        return;
        if (true) goto _L12; else goto _L11
_L11:
    }

    public final void onAttendeeTileClicked()
    {
        Object obj;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        if (!AndroidPermissionUtils.hasContactsPermissions(((android.content.Context) (obj))))
        {
            requestPermissions(new String[] {
                "android.permission.READ_CONTACTS"
            }, 1337);
            return;
        } else
        {
            openFullScreenExperience();
            return;
        }
    }

    public final void onCalendarChanged(boolean flag, boolean flag1)
    {
        updateUi();
    }

    public final void onFindTimeTileClicked()
    {
        Object obj;
        Object obj1;
        android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl;
        boolean flag;
        boolean flag1;
        flag1 = false;
        obj1 = null;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        obj = FindTimeIntentFactory.create(((android.content.Context) (obj)), (EventEditScreenModel)super.model);
        if (obj != null) goto _L2; else goto _L1
_L1:
        flag = flag1;
        if (this == null) goto _L4; else goto _L3
_L3:
        fragmentmanagerimpl = super.mFragmentManager;
        flag = flag1;
        if (this == null) goto _L4; else goto _L5
_L5:
        if (super.mHost != null && super.mAdded)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L7; else goto _L6
_L6:
        flag = flag1;
_L4:
        if (flag)
        {
            (new InvalidDatesChosenDialog()).show(super.mFragmentManager, InvalidDatesChosenDialog.TAG);
        }
        return;
_L7:
        if (super.mHost == null)
        {
            obj = obj1;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        flag = flag1;
        if (obj != null)
        {
            flag = flag1;
            if (!((Activity) (obj)).isDestroyed())
            {
                flag = flag1;
                if (!((Activity) (obj)).isFinishing())
                {
                    flag = flag1;
                    if (fragmentmanagerimpl != null)
                    {
                        flag = flag1;
                        if (!fragmentmanagerimpl.isDestroyed())
                        {
                            flag = true;
                        }
                    }
                }
            }
        }
        if (true) goto _L4; else goto _L2
_L2:
        ((Intent) (obj)).addFlags(0x24000000);
        startActivityForResult(((Intent) (obj)), 1005);
        String s;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        ((FragmentActivity) (obj)).overridePendingTransition(0, 0);
        ((EventEditScreenModel)super.model).isFindTimeButtonClicked = true;
        updateUi();
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        obj = CalendarClientLogger.getInstance(((android.content.Context) (obj)));
        s = ((EventEditScreenModel)super.model).eventReferenceId;
        ((CalendarClientLogger) (obj)).log(((EventEditScreenModel)super.model).getCalendarListEntry().getDescriptor().account, ((CalendarClientLogger) (obj)).getFindTimeProto(com.google.calendar.client.events.logging.CalendarClientLogEvent.Type.FIND_TIME_BUTTON_CLICKED, null, 0x80000000, false, null, null, null, null, null, s));
        ((EventEditScreenModel)super.model).logMetrics.findTimeButtonClicked = true;
        return;
    }

    public final void onFullScreenResult(Object obj)
    {
        AttendeesResult attendeesresult = (AttendeesResult)obj;
        if (super.model == null) goto _L2; else goto _L1
_L1:
        EventModifications eventmodifications = ((BasicEditScreenModel) ((EventEditScreenModel)super.model)).eventModifications;
        if (!AttendeesUtils.setAttendeeList(eventmodifications, attendeesresult.attendees, new .Lambda._cls0(eventmodifications))) goto _L4; else goto _L3
_L3:
        Consumer consumer;
        Iterator iterator;
        updateUi();
        obj = super.editScreenController;
        consumer = com.google.android.calendar.newapi.screen.EditScreenController..Lambda._cls4.$instance;
        iterator = ((EditScreenController) (obj)).segments.segmentControllers.values().iterator();
_L13:
        if (!iterator.hasNext()) goto _L4; else goto _L5
_L5:
        EditSegmentController editsegmentcontroller;
        android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl;
        boolean flag;
        editsegmentcontroller = (EditSegmentController)iterator.next();
        if (editsegmentcontroller == this || editsegmentcontroller != null && editsegmentcontroller.equals(this))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (editsegmentcontroller == null) goto _L7; else goto _L6
_L6:
        fragmentmanagerimpl = ((Fragment) (editsegmentcontroller)).mFragmentManager;
        if (editsegmentcontroller == null) goto _L9; else goto _L8
_L8:
        if (((Fragment) (editsegmentcontroller)).mHost != null && ((Fragment) (editsegmentcontroller)).mAdded)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L10; else goto _L9
_L9:
        flag = false;
_L11:
        if (flag)
        {
            consumer.accept(editsegmentcontroller);
        }
        continue; /* Loop/switch isn't completed */
_L10:
        if (((Fragment) (editsegmentcontroller)).mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)((Fragment) (editsegmentcontroller)).mHost.mActivity;
        }
        if (obj == null || ((Activity) (obj)).isDestroyed() || ((Activity) (obj)).isFinishing())
        {
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        if (fragmentmanagerimpl != null && !fragmentmanagerimpl.isDestroyed())
        {
            flag = true;
            continue; /* Loop/switch isn't completed */
        }
_L7:
        flag = false;
        if (true) goto _L11; else goto _L4
_L4:
        if (eventmodifications.canAttendeesAddAttendees() != attendeesresult.canAttendeesAddAttendees)
        {
            eventmodifications.setCanAttendeesAddAttendees(attendeesresult.canAttendeesAddAttendees);
        }
_L2:
        return;
        if (true) goto _L13; else goto _L12
_L12:
    }

    protected final void onInitialize()
    {
        updateUi();
    }

    public final void onRequestPermissionsResult(int i, String as[], int ai[])
    {
        if (i != 1337)
        {
            super.onRequestPermissionsResult(i, as, ai);
            return;
        } else
        {
            openFullScreenExperience();
            return;
        }
    }

    public final void onTimeRelatedFieldChanged(boolean flag, boolean flag1)
    {
        updateUi();
    }

}
