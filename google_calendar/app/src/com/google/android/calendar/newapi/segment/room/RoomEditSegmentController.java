// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.room;

import android.accounts.Account;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Parcel;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import com.google.android.apps.calendar.api.util.attendee.AttendeeUtils;
import com.google.android.apps.calendar.loggers.CalendarClientLogger;
import com.google.android.calendar.AccountUtils;
import com.google.android.calendar.Utils;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.color.EntityColor;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventModifications;
import com.google.android.calendar.api.event.EventPermissions;
import com.google.android.calendar.api.event.attendee.Attendee;
import com.google.android.calendar.api.event.attendee.AttendeeDescriptor;
import com.google.android.calendar.api.event.attendee.AttendeeModifications;
import com.google.android.calendar.api.event.attendee.AttendeePermissions;
import com.google.android.calendar.api.event.attendee.Response;
import com.google.android.calendar.api.event.time.RecurRulePart;
import com.google.android.calendar.api.event.time.Recurrence;
import com.google.android.calendar.event.edit.segment.RoomsIntentFactory;
import com.google.android.calendar.experimental.ExperimentalOptions;
import com.google.android.calendar.newapi.model.PermissionHolder;
import com.google.android.calendar.newapi.model.edit.EventModificationsHolder;
import com.google.android.calendar.newapi.model.edit.EventReferenceIdHolder;
import com.google.android.calendar.newapi.model.edit.SettingsHolder;
import com.google.android.calendar.newapi.segment.common.EditSegmentController;
import com.google.android.calendar.newapi.segment.common.SegmentController;
import com.google.android.calendar.newapi.utils.LocationUtils;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.tiles.view.TileView;
import com.google.android.calendar.timely.rooms.controller.RoomBookingActivity;
import com.google.android.calendar.timely.rooms.data.AutoValue_Attendee;
import com.google.android.calendar.viewedit.segment.edit.EditSegment;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.identity.accounts.api.AccountData;
import com.google.android.gms.identity.accounts.api.AccountDataUtil;
import com.google.android.gms.identity.accounts.api.zzb;
import com.google.calendar.v2.client.service.api.time.Period;
import com.google.common.base.Optional;
import com.google.common.base.Platform;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.google.android.calendar.newapi.segment.room:
//            RoomEditSegment, RoomUtils

public class RoomEditSegmentController extends EditSegmentController
    implements RoomEditSegment.Listener
{

    private Account account;

    public RoomEditSegmentController()
    {
    }

    private final void updateUi()
    {
        AttendeePermissions attendeepermissions = ((PermissionHolder)(EventModificationsHolder)super.model).getPermissions().getAttendeePermissions();
        if (attendeepermissions.isReadOnly() || !attendeepermissions.canAddAttendees())
        {
            break MISSING_BLOCK_LABEL_310;
        }
        if (!ExperimentalOptions.isRbEnabledForAllUsers(((RoomEditSegment)view).getContext())) goto _L2; else goto _L1
_L1:
        boolean flag = true;
_L3:
        int j;
        if (((RoomEditSegment)super.view).getVisibility() == 0)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (flag != j)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j != 0 && flag)
        {
            Object obj;
            Object obj2;
            if (super.mHost == null)
            {
                obj = null;
            } else
            {
                obj = (FragmentActivity)super.mHost.mActivity;
            }
            obj = CalendarClientLogger.getInstance(((Context) (obj)));
            obj2 = ((EventReferenceIdHolder)(EventModificationsHolder)super.model).getEventReferenceId();
            ((CalendarClientLogger) (obj)).log(((EventModificationsHolder)super.model).getEventModifications().getCalendarListEntry().getDescriptor().account, ((CalendarClientLogger) (obj)).getRoomBookingProto(com.google.calendar.client.events.logging.CalendarClientLogEvent.Type.ROOM_BOOKING_ENTRY_POINT_SHOWN, null, null, ((String) (obj2)), null, null, null, null, null, null, null, null, Collections.emptyList()));
        }
        obj = super.view;
        if (obj != null)
        {
            if (flag)
            {
                j = 0;
            } else
            {
                j = 8;
            }
            ((View) (obj)).setVisibility(j);
        }
        if (!flag)
        {
            return;
        }
        break MISSING_BLOCK_LABEL_349;
_L2:
        if (!AccountUtils.isDasher(((SettingsHolder)(EventModificationsHolder)super.model).getSettings()))
        {
            break MISSING_BLOCK_LABEL_310;
        }
        obj = ((RoomEditSegment)view).getContext();
        obj2 = ((EventModificationsHolder)super.model).getEventModifications().getCalendar().account;
        if (!ExperimentalOptions.isRbEnabledForAllUsers(((Context) (obj))))
        {
            obj2 = Utils.sharedPrefKeyForAccount(((Account) (obj2)).name, "room_service_status");
            if (!((Context) (obj)).getSharedPreferences("com.google.android.calendar_preferences", 0).getBoolean(((String) (obj2)), false))
            {
                break MISSING_BLOCK_LABEL_304;
            }
        }
        flag = true;
_L4:
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_310;
        }
        flag = true;
          goto _L3
        flag = false;
          goto _L4
        flag = false;
          goto _L3
        account = ((EventModificationsHolder)super.model).getEventModifications().getCalendar().account;
        RoomEditSegment roomeditsegment = (RoomEditSegment)super.view;
        Object obj1 = RoomUtils.getRooms(((EventModificationsHolder)super.model).getEventModifications());
        TextTileView texttileview;
        int i;
        int k;
        if (obj1 != null && !((List) (obj1)).isEmpty())
        {
            i = 1;
        } else
        {
            i = 0;
        }
        texttileview = roomeditsegment.roomsTile;
        if (texttileview != null)
        {
            boolean flag1;
            if (i != 0)
            {
                k = 0;
            } else
            {
                k = 8;
            }
            texttileview.setVisibility(k);
        }
        if (i != 0)
        {
            texttileview = roomeditsegment.roomsTile;
            obj1 = RoomUtils.createRoomLabels(((List) (obj1)));
            if (obj1 == null)
            {
                obj1 = null;
            } else
            {
                obj1 = (CharSequence[])((List) (obj1)).toArray(new CharSequence[((List) (obj1)).size()]);
            }
            texttileview.setPrimaryText(((CharSequence []) (obj1)));
        }
        obj1 = roomeditsegment.addRoomTile;
        if (i != 0)
        {
            k = 0;
        } else
        {
            k = 0x7f020212;
        }
        ((TileView) (obj1)).setIconDrawable(k);
        obj1 = roomeditsegment.addRoomTile;
        if (i != 0)
        {
            i = 0x7f13008c;
        } else
        {
            i = 0x7f130098;
        }
        ((TextTileView) (obj1)).setPrimaryText(new CharSequence[] {
            ((TextTileView) (obj1)).getResources().getString(i, new Object[0])
        });
        flag1 = LocationUtils.allowsUpdateLocation(((PermissionHolder)(EventModificationsHolder)super.model).getPermissions());
        obj1 = (RoomEditSegment)super.view;
        if (flag1)
        {
            i = 0;
        } else
        {
            i = (int)requireContext().getResources().getDimension(0x7f0e0296);
        }
        ((RoomEditSegment) (obj1)).setPaddingBottom(i);
        return;
    }

    public final View createView(LayoutInflater layoutinflater)
    {
        layoutinflater = (RoomEditSegment)layoutinflater.inflate(0x7f0500e3, null);
        layoutinflater.mListener = this;
        return layoutinflater;
    }

    public final void onActivityResult(int i, int j, Intent intent)
    {
        if (i == 1008)
        {
            if (j == -1 && RoomUtils.updateEventModificationsFromRoomBookingActivityResult(((EventModificationsHolder)super.model).getEventModifications(), intent))
            {
                updateUi();
                notifyAttendeesChanged();
            }
            return;
        } else
        {
            super.onActivityResult(i, j, intent);
            return;
        }
    }

    public final void onCalendarChanged(boolean flag, boolean flag1)
    {
        if (flag1 && account != null && !((EventModificationsHolder)super.model).getEventModifications().getCalendar().account.equals(account))
        {
            Object obj = ((EventModificationsHolder)super.model).getEventModifications().getAttendees();
            class .Lambda._cls0
                implements Predicate
            {

                public static final Predicate $instance = new .Lambda._cls0();

                public final boolean apply(Object obj1)
                {
                    return AttendeeUtils.isRoom((Attendee)obj1);
                }


            private .Lambda._cls0()
            {
            }
            }

            Predicate predicate;
            if (obj instanceof FluentIterable)
            {
                obj = (FluentIterable)obj;
            } else
            {
                obj = new com.google.common.collect.FluentIterable._cls1(((Iterable) (obj)), ((Iterable) (obj)));
            }
            predicate = .Lambda._cls0..instance;
            obj = (Iterable)((FluentIterable) (obj)).iterableDelegate.or(obj);
            if (obj == null)
            {
                throw new NullPointerException();
            }
            if (predicate == null)
            {
                throw new NullPointerException();
            }
            obj = new com.google.common.collect.Iterables._cls4(((Iterable) (obj)), predicate);
            int i;
            int j;
            if (obj instanceof FluentIterable)
            {
                obj = (FluentIterable)obj;
            } else
            {
                obj = new com.google.common.collect.FluentIterable._cls1(((Iterable) (obj)), ((Iterable) (obj)));
            }
            obj = (ImmutableList)ImmutableList.copyOf((Iterable)((FluentIterable) (obj)).iterableDelegate.or(obj));
            j = ((ImmutableList) (obj)).size();
            i = 0;
            while (i < j) 
            {
                Attendee attendee = (Attendee)((ImmutableList) (obj)).get(i);
                ((EventModificationsHolder)super.model).getEventModifications().getAttendeeModifications().removeAttendee(attendee);
                i++;
            }
        }
        updateUi();
    }

    public final void onEditRoomsClicked()
    {
        Object obj;
        Object obj2;
        Object obj3;
        Object obj5;
        obj = getContext();
        Object obj4 = ((EventModificationsHolder)super.model).getEventModifications();
        obj5 = ((PermissionHolder)(EventModificationsHolder)super.model).getPermissions().getAttendeePermissions();
        boolean flag2 = ((EventModificationsHolder)super.model).getEventModifications().isStartModified();
        obj3 = ((EventReferenceIdHolder)(EventModificationsHolder)super.model).getEventReferenceId();
        obj2 = new Intent(((Context) (obj)), com/google/android/calendar/timely/rooms/controller/RoomBookingActivity);
        ((Intent) (obj2)).putExtra("key_start_time", ((EventModifications) (obj4)).getStartMillis());
        long l;
        if (((EventModifications) (obj4)).isRecurring())
        {
            if (((EventModifications) (obj4)).getTimeZoneId() != null)
            {
                ((Intent) (obj2)).putExtra("key_timezone", ((EventModifications) (obj4)).getTimeZoneId());
            }
            boolean flag;
            if (((Event) (obj4)).isRecurring() && !((Event) (obj4)).getRecurrence().rrules.isEmpty())
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                obj = ((RecurRulePart)((Event) (obj4)).getRecurrence().rrules.get(0)).toRfc5545String();
            } else
            {
                obj = null;
            }
            ((Intent) (obj2)).putExtra("key_recurrence_rule", ((String) (obj)));
            if (!flag2)
            {
                flag2 = true;
            } else
            {
                flag2 = false;
            }
            ((Intent) (obj2)).putExtra("key_consider_exceptions", flag2);
        }
        Object obj6;
        ImmutableList immutablelist;
        UnmodifiableIterator unmodifiableiterator;
        int i;
        int k;
        if (((Event) (obj4)).isEndTimeUnspecified())
        {
            l = ((Event) (obj4)).getStartMillis();
            if (((Event) (obj4)).isAllDayEvent())
            {
                obj = new Period(0, 0, 0, 1, 0, 0, 0L);
            } else
            {
                obj = new Period(0, 0, 0, 0, 1, 0, 0L);
            }
            if (((Period) (obj)).years != 0 || ((Period) (obj)).months != 0)
            {
                throw new UnsupportedOperationException();
            }
            long l1 = ((Period) (obj)).weeks;
            long l2 = ((Period) (obj)).days;
            long l3 = ((Period) (obj)).hours;
            long l4 = ((Period) (obj)).minutes;
            l = ((Period) (obj)).millis + (l1 * 0x240c8400L + l2 * 0x5265c00L + l3 * 0x36ee80L + l4 * 60000L) + l;
        } else
        {
            l = ((Event) (obj4)).getEndMillis();
        }
        ((Intent) (obj2)).putExtra("key_end_time", l);
        ((Intent) (obj2)).putExtra("key_all_day", ((EventModifications) (obj4)).isAllDayEvent());
        ((Intent) (obj2)).putExtra("key_calendar_id", ((EventModifications) (obj4)).getCalendar().calendarId);
        ((Intent) (obj2)).putExtra("key_event_id", ((EventModifications) (obj4)).getGoogleSyncId());
        obj = new HashMap();
        obj6 = ((Event) (obj4)).getOrganizer().email;
        immutablelist = (ImmutableList)((Event) (obj4)).getAttendees();
        k = immutablelist.size();
        i = 0;
        unmodifiableiterator = (UnmodifiableIterator)null;
        do
        {
            if (i >= k)
            {
                break;
            }
            Object obj7 = immutablelist.get(i);
            int j = i + 1;
            Object obj8 = (Attendee)obj7;
            i = j;
            if (!AttendeeUtils.isRoom(((Attendee) (obj8))))
            {
                String s = ((Attendee) (obj8)).attendeeDescriptor.email;
                i = j;
                if (!TextUtils.isEmpty(s))
                {
                    String s1 = ((Attendee) (obj8)).displayName;
                    boolean flag3 = s.equals(obj6);
                    obj8 = ((Attendee) (obj8)).response.status;
                    ((Map) (obj)).put(s, new AutoValue_Attendee(s, Platform.emptyToNull(s1), flag3, ((com.google.android.calendar.api.event.attendee.Response.ResponseStatus) (obj8))));
                    i = j;
                }
            }
        } while (true);
        if (!((Map) (obj)).containsKey(obj6))
        {
            com.google.android.calendar.api.event.attendee.Response.ResponseStatus responsestatus = com.google.android.calendar.api.event.attendee.Response.ResponseStatus.NEEDS_ACTION;
            ((Map) (obj)).put(obj6, new AutoValue_Attendee(((String) (obj6)), Platform.emptyToNull(null), true, responsestatus));
        }
        ((Intent) (obj2)).putParcelableArrayListExtra("key_attendees", new ArrayList(((Map) (obj)).values()));
        ((Intent) (obj2)).putStringArrayListExtra("key_selected_room_emails", RoomsIntentFactory.getSelectedApiRoomEmails(((Event) (obj4))));
        obj = new ArrayList();
        obj6 = RoomUtils.getRooms(((Event) (obj4))).iterator();
        do
        {
            if (!((Iterator) (obj6)).hasNext())
            {
                break;
            }
            Attendee attendee = (Attendee)((Iterator) (obj6)).next();
            if (!TextUtils.isEmpty(attendee.attendeeDescriptor.email))
            {
                ((ArrayList) (obj)).add(attendee.displayName);
            }
        } while (true);
        ((Intent) (obj2)).putStringArrayListExtra("key_selected_room_names", ((ArrayList) (obj)));
        boolean flag1;
        if (((EventModifications) (obj4)).getOriginal() == null)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1 || ((AttendeePermissions) (obj5)).canRemoveAttendees())
        {
            obj = new ArrayList();
        } else
        {
            obj = RoomsIntentFactory.getSelectedApiRoomEmails(((EventModifications) (obj4)).getOriginal());
        }
        ((Intent) (obj2)).putStringArrayListExtra("key_non_removable_selected_room_emails", ((ArrayList) (obj)));
        obj5 = new ArrayList();
        obj4 = RoomUtils.getRooms(((Event) (obj4))).iterator();
        do
        {
            if (!((Iterator) (obj4)).hasNext())
            {
                break MISSING_BLOCK_LABEL_971;
            }
            obj = (Attendee)((Iterator) (obj4)).next();
        } while (TextUtils.isEmpty(((Attendee) (obj)).attendeeDescriptor.email));
        switch (((Attendee) (obj)).response.status.ordinal())
        {
        case 2: // '\002'
        default:
            obj = Integer.valueOf(0);
            break;

        case 1: // '\001'
            break; /* Loop/switch isn't completed */

        case 3: // '\003'
            break MISSING_BLOCK_LABEL_963;
        }
_L4:
        ((ArrayList) (obj5)).add(obj);
        if (true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_850;
_L1:
        obj = Integer.valueOf(1);
        continue; /* Loop/switch isn't completed */
        obj = Integer.valueOf(2);
        if (true) goto _L4; else goto _L3
_L3:
        ((Intent) (obj2)).putIntegerArrayListExtra("key_selected_room_availabilities", ((ArrayList) (obj5)));
        ((Intent) (obj2)).putExtra("key_calendar_event_reference", ((String) (obj3)));
        ((Intent) (obj2)).putExtra("event_color", ((EventModificationsHolder)super.model).getEventModifications().getColor().getValue());
        obj3 = ((EventModificationsHolder)super.model).getEventModifications().getCalendar().account;
        obj5 = getContext();
        AccountData accountdata = AccountData.forAccount(((Account) (obj3)).name);
        Object obj1;
        if (accountdata != null)
        {
            zzb zzb1 = AccountDataUtil.zzbxL;
            if (obj5 == null)
            {
                throw new NullPointerException(String.valueOf("Context must not be null."));
            }
            if (obj2 == null)
            {
                throw new NullPointerException(String.valueOf("Intent must not be null."));
            }
            if (accountdata == null)
            {
                throw new NullPointerException(String.valueOf("Account data must not be null."));
            }
            obj1 = ((Intent) (obj2)).getComponent();
            if (obj1 != null)
            {
                obj1 = ((ComponentName) (obj1)).getPackageName();
            } else
            {
                obj1 = ((Intent) (obj2)).getPackage();
            }
            if (obj1 != null && zzb1.zzbxM.zzG(((Context) (obj5)), ((String) (obj1))))
            {
                obj1 = Parcel.obtain();
                accountdata.writeToParcel(((Parcel) (obj1)), 0);
                byte abyte0[] = ((Parcel) (obj1)).marshall();
                ((Parcel) (obj1)).recycle();
                ((Intent) (obj2)).putExtra("com.google.android.gms.accounts.ACCOUNT_DATA", abyte0);
            }
        }
        ((Intent) (obj2)).addFlags(0x24000000);
        startActivityForResult(((Intent) (obj2)), 1008);
        if (obj3 != null)
        {
            obj1 = CalendarClientLogger.getInstance(getContext());
            obj2 = ((EventReferenceIdHolder)(EventModificationsHolder)super.model).getEventReferenceId();
            ((CalendarClientLogger) (obj1)).log(((Account) (obj3)), ((CalendarClientLogger) (obj1)).getRoomBookingProto(com.google.calendar.client.events.logging.CalendarClientLogEvent.Type.ROOM_BOOKING_ENTRY_POINT_CLICKED, null, null, ((String) (obj2)), null, null, null, null, null, null, null, null, Collections.emptyList()));
        }
        if (super.mHost == null)
        {
            obj1 = null;
        } else
        {
            obj1 = (FragmentActivity)super.mHost.mActivity;
        }
        obj1 = CalendarClientLogger.getInstance(((Context) (obj1)));
        obj2 = ((EventReferenceIdHolder)(EventModificationsHolder)super.model).getEventReferenceId();
        ((CalendarClientLogger) (obj1)).log(((EventModificationsHolder)super.model).getEventModifications().getCalendarListEntry().getDescriptor().account, ((CalendarClientLogger) (obj1)).getRoomBookingProto(com.google.calendar.client.events.logging.CalendarClientLogEvent.Type.ROOM_BOOKING_ENTRY_POINT_CLICKED, null, null, ((String) (obj2)), null, null, null, null, null, null, null, null, Collections.emptyList()));
        return;
    }

    protected final void onInitialize()
    {
        ((RoomEditSegment)super.view).setVisibility(8);
        updateUi();
    }
}
