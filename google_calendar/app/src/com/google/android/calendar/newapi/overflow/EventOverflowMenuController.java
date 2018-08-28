// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.overflow;

import android.accounts.Account;
import android.view.Menu;
import android.view.MenuItem;
import com.google.android.apps.calendar.api.util.attendee.AttendeeUtils;
import com.google.android.calendar.Utils;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.api.calendarlist.CalendarAccessLevel;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventPermissions;
import com.google.android.calendar.api.event.attendee.Attendee;
import com.google.android.calendar.api.event.attendee.AttendeeDescriptor;
import com.google.android.calendar.api.event.userstatus.UserStatus;
import com.google.android.calendar.newapi.model.CalendarList;
import com.google.android.calendar.newapi.model.CalendarListHolder;
import com.google.android.calendar.newapi.model.EventHolder;
import com.google.android.calendar.newapi.model.PermissionHolder;
import com.google.android.calendar.newapi.screen.ics.IcsViewScreenModel;
import com.google.android.calendar.newapi.utils.SmartMailUtils;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.android.calendar.v2a.UnifiedSyncUtils;
import com.google.common.base.Optional;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import java.util.List;

// Referenced classes of package com.google.android.calendar.newapi.overflow:
//            OverflowMenuController

public final class EventOverflowMenuController extends OverflowMenuController
{

    public EventOverflowMenuController(Callback callback)
    {
        super(callback);
    }

    private final List getCopyDestinations()
    {
        Object obj = ((CalendarListHolder)(EventHolder)super.model).getCalendarList().calendars;
        class .Lambda._cls0
            implements Predicate
        {

            private final EventOverflowMenuController arg$1;

            public final boolean apply(Object obj1)
            {
                EventOverflowMenuController eventoverflowmenucontroller = arg$1;
                obj1 = (CalendarListEntry)obj1;
                return ((CalendarListEntry) (obj1)).getAccessLevel().isGreaterOrEqual(CalendarAccessLevel.WRITER) && !((CalendarListEntry) (obj1)).getDescriptor().matches(((EventHolder)((OverflowMenuController) (eventoverflowmenucontroller)).model).getEvent().getCalendarListEntry().getDescriptor());
            }

            .Lambda._cls0()
            {
                arg$1 = EventOverflowMenuController.this;
            }
        }

        .Lambda._cls0 _lcls0;
        if (obj instanceof FluentIterable)
        {
            obj = (FluentIterable)obj;
        } else
        {
            obj = new com.google.common.collect.FluentIterable._cls1(((Iterable) (obj)), ((Iterable) (obj)));
        }
        _lcls0 = new .Lambda._cls0();
        obj = (Iterable)((FluentIterable) (obj)).iterableDelegate.or(obj);
        if (obj == null)
        {
            throw new NullPointerException();
        }
        if (_lcls0 == null)
        {
            throw new NullPointerException();
        }
        obj = new com.google.common.collect.Iterables._cls4(((Iterable) (obj)), _lcls0);
        if (obj instanceof FluentIterable)
        {
            obj = (FluentIterable)obj;
        } else
        {
            obj = new com.google.common.collect.FluentIterable._cls1(((Iterable) (obj)), ((Iterable) (obj)));
        }
        return ImmutableList.copyOf((Iterable)((FluentIterable) (obj)).iterableDelegate.or(obj));
    }

    private static int getEmailLabelId(EventHolder eventholder)
    {
        String s = eventholder.getEvent().getCalendar().account.name;
        Object obj = (ImmutableList)eventholder.getEvent().getAttendees();
        int j = ((ImmutableList) (obj)).size();
        for (int i = 0; i < j;)
        {
            Object obj1 = ((ImmutableList) (obj)).get(i);
            i++;
            if (Utils.isEmailableFrom(((Attendee)obj1).attendeeDescriptor.email, s))
            {
                return 0x7f1301bf;
            }
        }

        obj = eventholder.getEvent().getOrganizer().email;
        return ((String) (obj)).equalsIgnoreCase(eventholder.getEvent().getCalendar().calendarId) || !Utils.isEmailableFrom(((String) (obj)), s) ? 0 : 0x7f1301c0;
    }

    public final int getMenuResourceId()
    {
        return 0x7f150003;
    }

    protected final void onMenuItemClicked(MenuItem menuitem, Object obj)
    {
        obj = (Callback)obj;
        if (menuitem.getItemId() == 0x7f10042d)
        {
            ((Callback) (obj)).onCopyToClicked(getCopyDestinations());
        } else
        {
            if (menuitem.getItemId() == 0x7f10042c)
            {
                ((Callback) (obj)).onDuplicateClicked();
                return;
            }
            if (menuitem.getItemId() == 0x7f100429)
            {
                ((Callback) (obj)).onDeleteClicked();
                return;
            }
            if (menuitem.getItemId() == 0x7f10042a)
            {
                AnalyticsLogger analyticslogger;
                boolean flag;
                if (getEmailLabelId((EventHolder)super.model) == 0x7f1301c0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    menuitem = "email_organizer";
                } else
                {
                    menuitem = "email_guests";
                }
                analyticslogger = AnalyticsLoggerHolder.instance;
                if (analyticslogger == null)
                {
                    throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
                } else
                {
                    ((AnalyticsLogger)analyticslogger).trackEvent(super.context, "event_action", menuitem);
                    ((Callback) (obj)).onEmailGuestsClicked(true);
                    return;
                }
            }
            if (menuitem.getItemId() == 0x7f10042b)
            {
                ((Callback) (obj)).onForwardEventClicked();
                return;
            }
        }
    }

    protected final void onModelChanged(OverflowMenuCompat.OverflowMenu overflowmenu, Object obj)
    {
        boolean flag2 = true;
        obj = (EventHolder)obj;
        overflowmenu = overflowmenu.getMenu();
        Object obj1;
        UserStatus userstatus;
        int i;
        boolean flag;
        boolean flag1;
        if (((PermissionHolder)obj).getPermissions().canDelete() && (!(super.model instanceof IcsViewScreenModel) || !UnifiedSyncUtils.shouldUseCalendarsAndEvents()))
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        overflowmenu.findItem(0x7f100429).setVisible(flag1).setEnabled(flag1);
        i = getEmailLabelId(((EventHolder) (obj)));
        if (i != 0)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        overflowmenu.findItem(0x7f10042a).setVisible(flag1).setEnabled(flag1);
        if (i != 0)
        {
            overflowmenu.findItem(0x7f10042a).setTitle(i);
        }
        obj1 = ((EventHolder) (obj)).getEvent();
        if (!AccountUtil.isGoogleType(((Event) (obj1)).getCalendar().account.type) && !AttendeeUtils.isOrganizerCopy(((Event) (obj1))))
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        overflowmenu.findItem(0x7f10042b).setVisible(flag1).setEnabled(flag1);
        obj1 = ((EventHolder)super.model).getEvent().getSmartMailInfo();
        userstatus = ((EventHolder)super.model).getEvent().getParticipantStatus();
        if (userstatus != null && userstatus.getOutOfOffice() != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!SmartMailUtils.isSupportedSmartMailType(((com.google.android.calendar.api.event.smartmail.SmartMailInfo) (obj1))) && !flag)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag && (!(obj instanceof IcsViewScreenModel) || !UnifiedSyncUtils.shouldUseCalendarsAndEvents()))
        {
            flag1 = ((EventHolder)super.model).getEvent().getCalendarListEntry().getAccessLevel().isGreaterOrEqual(CalendarAccessLevel.WRITER);
            overflowmenu.findItem(0x7f10042c).setVisible(flag1).setEnabled(flag1);
            if (!getCopyDestinations().isEmpty())
            {
                flag1 = flag2;
            } else
            {
                flag1 = false;
            }
            overflowmenu.findItem(0x7f10042d).setVisible(flag1).setEnabled(flag1);
        }
    }

    private class Callback
    {

        public abstract void onCopyToClicked(List list);

        public abstract void onDeleteClicked();

        public abstract void onDuplicateClicked();

        public abstract void onEmailGuestsClicked(boolean flag);

        public abstract void onForwardEventClicked();
    }

}
