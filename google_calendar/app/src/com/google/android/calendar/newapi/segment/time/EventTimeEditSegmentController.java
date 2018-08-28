// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.time;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.google.android.apps.calendar.loggers.CalendarClientLogger;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.apps.calendar.util.string.StringUtils;
import com.google.android.calendar.Utils;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.color.EntityColor;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventModifications;
import com.google.android.calendar.api.event.EventPermissions;
import com.google.android.calendar.common.view.NinjaSwitch;
import com.google.android.calendar.newapi.logging.EventEditLogMetrics;
import com.google.android.calendar.newapi.model.PermissionHolder;
import com.google.android.calendar.newapi.model.TimeZoneHolder;
import com.google.android.calendar.newapi.model.edit.EditScreenModel;
import com.google.android.calendar.newapi.model.edit.EventEditLogMetricsHolder;
import com.google.android.calendar.newapi.model.edit.EventModificationsHolder;
import com.google.android.calendar.newapi.model.edit.EventReferenceIdHolder;
import com.google.android.calendar.newapi.model.edit.MoreTimeOptionsStatusHolder;
import com.google.android.calendar.newapi.model.edit.SettingsHolder;
import com.google.android.calendar.newapi.screen.EditScreenController;
import com.google.android.calendar.newapi.screen.SegmentMap;
import com.google.android.calendar.newapi.screen.event.AutoValue_EventDuration;
import com.google.android.calendar.newapi.screen.event.EventDuration;
import com.google.android.calendar.newapi.segment.availability.AvailabilityUtils;
import com.google.android.calendar.newapi.segment.common.EditSegmentController;
import com.google.android.calendar.newapi.segment.common.IconAnimator;
import com.google.android.calendar.newapi.segment.common.SegmentController;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.time.CalendarUtils;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.viewedit.segment.edit.EditSegment;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.calendar.newapi.segment.time:
//            DateTimePickerFactory, TimeUtils, EventTimeEditSegment

public class EventTimeEditSegmentController extends EditSegmentController
    implements com.android.datetimepicker.date.DatePickerCompat.OnDateSetListener, com.android.datetimepicker.time.TimePickerCompat.OnTimeSetListener, EventTimeEditSegment.Listener
{
    static final class DateTimeType extends Enum
    {

        private static final DateTimeType $VALUES[];
        public static final DateTimeType END;
        public static final DateTimeType START;

        public static DateTimeType[] values()
        {
            return (DateTimeType[])$VALUES.clone();
        }

        static 
        {
            START = new DateTimeType("START", 0);
            END = new DateTimeType("END", 1);
            $VALUES = (new DateTimeType[] {
                START, END
            });
        }

        private DateTimeType(String s, int i)
        {
            super(s, i);
        }
    }


    private EventDuration defaultDuration;
    private Calendar endDateTime;
    private boolean instanceRestored;
    private DateTimeType lastRequestedDateTimeType;
    private DateTimePickerFactory pickerFactory;
    private Calendar startDateTime;

    public EventTimeEditSegmentController()
    {
        pickerFactory = new DateTimePickerFactory();
    }

    private final void logTimeChanged()
    {
        ((EventEditLogMetricsHolder)(EditScreenModel)super.model).getLogMetrics().didChangeTime = true;
        Object obj;
        CalendarClientLogger calendarclientlogger;
        String s;
        android.accounts.Account account;
        long l;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        calendarclientlogger = CalendarClientLogger.getInstance(((Context) (obj)));
        s = ((EventReferenceIdHolder)(EditScreenModel)super.model).getEventReferenceId();
        account = ((EventModificationsHolder)(EditScreenModel)super.model).getEventModifications().getCalendarListEntry().getDescriptor().account;
        l = ((EventModificationsHolder)(EditScreenModel)super.model).getEventModifications().getStartMillis();
        if (((EventModificationsHolder)(EditScreenModel)super.model).getEventModifications().isEndTimeUnspecified())
        {
            obj = null;
        } else
        {
            obj = Long.valueOf(((EventModificationsHolder)(EditScreenModel)super.model).getEventModifications().getEndMillis());
        }
        calendarclientlogger.log(account, CalendarClientLogger.getEventProto(com.google.calendar.client.events.logging.CalendarClientLogEvent.Type.EVENT_TIME_CHANGED, null, s, Long.valueOf(l), ((Long) (obj))));
    }

    private final void reloadTimeFields()
    {
        Object obj;
        Object obj1;
        Calendar calendar1;
        long l2;
        obj1 = ((EventModificationsHolder)(EditScreenModel)super.model).getEventModifications().getTimeZoneId();
        obj = obj1;
        if (obj1 == null)
        {
            obj1 = (TimeZoneHolder)(EditScreenModel)super.model;
            if (super.mHost == null)
            {
                obj = null;
            } else
            {
                obj = (FragmentActivity)super.mHost.mActivity;
            }
            obj = ((TimeZoneHolder) (obj1)).getDefaultTimeZoneId(((Context) (obj)));
        }
        obj = TimeZone.getTimeZone(((String) (obj)));
        if (!((EventModificationsHolder)(EditScreenModel)super.model).getEventModifications().isAllDayEvent()) goto _L2; else goto _L1
_L1:
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        obj1 = Calendar.getInstance(((TimeZone) (obj)));
        ((Calendar) (obj1)).setTimeInMillis(l);
        obj = CalendarUtils.createTimeInNewTimeZoneRetainingFields(((EventModificationsHolder)(EditScreenModel)super.model).getEventModifications().getStartMillis(), TimeZone.getTimeZone("UTC"), ((TimeZone) (obj)));
        ((Calendar) (obj)).set(11, ((Calendar) (obj1)).get(11));
        ((Calendar) (obj)).set(12, ((Calendar) (obj1)).get(12));
        ((Calendar) (obj)).set(13, 0);
        ((Calendar) (obj)).set(14, 0);
        l = ((Calendar) (obj)).getTimeInMillis();
        ((Calendar) (obj)).set(12, 30);
        ((Calendar) (obj)).set(13, 0);
        ((Calendar) (obj)).set(14, 0);
        if (((Calendar) (obj)).getTimeInMillis() <= l)
        {
            ((Calendar) (obj)).add(12, 30);
        }
        if (((Calendar) (obj)).get(11) == 0 && ((Calendar) (obj)).get(12) == 0)
        {
            ((Calendar) (obj)).add(6, -1);
            ((Calendar) (obj)).set(11, 23);
            ((Calendar) (obj)).set(12, 30);
        }
        startDateTime = ((Calendar) (obj));
        calendar1 = startDateTime;
        l = ((EventModificationsHolder)(EditScreenModel)super.model).getEventModifications().getEndMillis() - ((EventModificationsHolder)(EditScreenModel)super.model).getEventModifications().getStartMillis();
        l2 = defaultDuration.getMillis();
        obj1 = (Calendar)calendar1.clone();
        if (l <= 0x5265c00L) goto _L4; else goto _L3
_L3:
        ((Calendar) (obj1)).add(12, (int)TimeUnit.MILLISECONDS.toMinutes(l - 0x5265c00L));
        obj = obj1;
_L5:
        endDateTime = ((Calendar) (obj));
        return;
_L4:
        ((Calendar) (obj1)).add(14, (int)l2);
        if (calendar1.get(1) == ((Calendar) (obj1)).get(1))
        {
            obj = obj1;
            if (calendar1.get(6) == ((Calendar) (obj1)).get(6))
            {
                continue; /* Loop/switch isn't completed */
            }
        }
        obj = (Calendar)calendar1.clone();
        ((Calendar) (obj)).add(6, 1);
        ((Calendar) (obj)).set(11, 0);
        ((Calendar) (obj)).set(12, 0);
        if (true) goto _L5; else goto _L2
_L2:
        long l1 = ((EventModificationsHolder)(EditScreenModel)super.model).getEventModifications().getStartMillis();
        Calendar calendar = Calendar.getInstance(((TimeZone) (obj)));
        calendar.setTimeInMillis(l1);
        startDateTime = calendar;
        l1 = ((EventModificationsHolder)(EditScreenModel)super.model).getEventModifications().getEndMillis();
        obj = Calendar.getInstance(((TimeZone) (obj)));
        ((Calendar) (obj)).setTimeInMillis(l1);
        endDateTime = ((Calendar) (obj));
        return;
    }

    private final void updateEventTime(Calendar calendar, DateTimeType datetimetype)
    {
        long l1;
        long l2;
        if (datetimetype == DateTimeType.START)
        {
            long l = endDateTime.getTimeInMillis() - startDateTime.getTimeInMillis();
            if (l >= 0L)
            {
                endDateTime.setTimeInMillis(l + calendar.getTimeInMillis());
            }
            startDateTime.setTimeInMillis(calendar.getTimeInMillis());
        } else
        {
            endDateTime.setTimeInMillis(calendar.getTimeInMillis());
        }
        if (((EventModificationsHolder)(EditScreenModel)super.model).getEventModifications().isAllDayEvent())
        {
            l1 = TimeUtils.toMidnight(startDateTime, false);
        } else
        {
            l1 = startDateTime.getTimeInMillis();
        }
        if (((EventModificationsHolder)(EditScreenModel)super.model).getEventModifications().isAllDayEvent())
        {
            l2 = TimeUtils.toMidnight(endDateTime, true);
        } else
        {
            l2 = endDateTime.getTimeInMillis();
        }
        ((EventModificationsHolder)(EditScreenModel)super.model).getEventModifications().setStartMillis(l1);
        ((EventModificationsHolder)(EditScreenModel)super.model).getEventModifications().setEndMillis(l2);
        updateUi();
        notifyTimeChanged(false, false);
    }

    private final void updateUi()
    {
        int i1 = 0x10016;
        Object obj2 = null;
        byte byte0 = 8;
        View view = super.view;
        boolean flag1 = ((PermissionHolder)(EditScreenModel)super.model).getPermissions().canModifyStartTime();
        boolean flag3 = ((PermissionHolder)(EditScreenModel)super.model).getPermissions().canModifyEndTime();
        boolean flag4 = ((PermissionHolder)(EditScreenModel)super.model).getPermissions().canModifyAllDayProperty();
        boolean flag;
        if (flag1 == flag3 && flag3 == flag4)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        }
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
            Object obj;
            Object obj1;
            EventTimeEditSegment eventtimeeditsegment;
            Calendar calendar;
            int j;
            int k;
            int l;
            boolean flag2;
            if (((EventModificationsHolder)(EditScreenModel)super.model).getEventModifications().isEndTimeUnspecified())
            {
                obj = null;
            } else
            {
                obj = (Calendar)endDateTime.clone();
            }
            eventtimeeditsegment = (EventTimeEditSegment)super.view;
            calendar = (Calendar)startDateTime.clone();
            flag2 = ((EventModificationsHolder)(EditScreenModel)super.model).getEventModifications().isAllDayEvent();
            if (super.mHost == null)
            {
                obj1 = null;
            } else
            {
                obj1 = (FragmentActivity)super.mHost.mActivity;
            }
            if (!TimeUtils.shouldExpandMoreTimeOptions(((Context) (obj1)), (EditScreenModel)super.model) && (((EventModificationsHolder)(EditScreenModel)super.model).getEventModifications().isEndTimeUnspecified() || ((PermissionHolder)(EditScreenModel)super.model).getPermissions().canModifyRecurrence() || ((PermissionHolder)(EditScreenModel)super.model).getPermissions().canModifyTimeZone()))
            {
                j = 1;
            } else
            {
                j = 0;
            }
            if (super.mHost == null)
            {
                obj1 = obj2;
            } else
            {
                obj1 = (FragmentActivity)super.mHost.mActivity;
            }
            if (TimeUtils.shouldExpandMoreTimeOptions(((Context) (obj1)), (EditScreenModel)super.model) && ((EventModificationsHolder)(EditScreenModel)super.model).getEventModifications().isEndTimeUnspecified())
            {
                k = 1;
            } else
            {
                k = 0;
            }
            obj1 = eventtimeeditsegment.allDaySwitch;
            obj1.stealth = true;
            ((NinjaSwitch) (obj1)).setChecked(flag2);
            obj1.stealth = false;
            obj1 = eventtimeeditsegment.startTimeTextView;
            if (!flag2)
            {
                l = 1;
            } else
            {
                l = 0;
            }
            if (obj1 != null)
            {
                long l1;
                if (l != 0)
                {
                    l = 0;
                } else
                {
                    l = 8;
                }
                ((View) (obj1)).setVisibility(l);
            }
            obj1 = eventtimeeditsegment.endTimeTextView;
            if (!flag2)
            {
                l = 1;
            } else
            {
                l = 0;
            }
            if (obj1 != null)
            {
                if (l != 0)
                {
                    l = 0;
                } else
                {
                    l = 8;
                }
                ((View) (obj1)).setVisibility(l);
            }
            if (obj != null)
            {
                l = 1;
            } else
            {
                l = 0;
            }
            obj1 = eventtimeeditsegment.endTimeUnspecifiedTile;
            if (obj1 != null)
            {
                if (k != 0)
                {
                    k = 0;
                } else
                {
                    k = 8;
                }
                ((View) (obj1)).setVisibility(k);
            }
            obj1 = eventtimeeditsegment.endDateTile;
            if (obj1 != null)
            {
                if (l != 0)
                {
                    k = 0;
                } else
                {
                    k = 8;
                }
                ((View) (obj1)).setVisibility(k);
            }
            obj1 = eventtimeeditsegment.moreOptionsTile;
            if (obj1 != null)
            {
                k = byte0;
                if (j != 0)
                {
                    k = 0;
                }
                ((View) (obj1)).setVisibility(k);
            }
            if (!flag2)
            {
                obj1 = DateFormat.getTimeFormat(eventtimeeditsegment.getContext());
                ((java.text.DateFormat) (obj1)).setTimeZone(calendar.getTimeZone());
                obj1 = ((java.text.DateFormat) (obj1)).format(Long.valueOf(calendar.getTimeInMillis()));
                eventtimeeditsegment.startTimeTextView.setText(StringUtils.capitalizeStandalone(((String) (obj1)), Locale.getDefault()));
                eventtimeeditsegment.startTimeTextView.setContentDescription(eventtimeeditsegment.getResources().getString(0x7f13006e, new Object[] {
                    obj1
                }));
                if (l != 0)
                {
                    obj1 = DateFormat.getTimeFormat(eventtimeeditsegment.getContext());
                    ((java.text.DateFormat) (obj1)).setTimeZone(((Calendar) (obj)).getTimeZone());
                    obj1 = ((java.text.DateFormat) (obj1)).format(Long.valueOf(((Calendar) (obj)).getTimeInMillis()));
                    eventtimeeditsegment.endTimeTextView.setText(StringUtils.capitalizeStandalone(((String) (obj1)), Locale.getDefault()));
                    eventtimeeditsegment.endTimeTextView.setContentDescription(eventtimeeditsegment.getResources().getString(0x7f13006c, new Object[] {
                        obj1
                    }));
                }
            }
            obj1 = eventtimeeditsegment.getContext();
            l1 = calendar.getTimeInMillis();
            if (((Context) (obj1)).getResources().getBoolean(0x7f0c0006))
            {
                j = 0x18016;
            } else
            {
                j = 0x10016;
            }
            obj1 = Utils.formatDateTime(((Context) (obj1)), l1, j, calendar.getTimeZone().getID());
            eventtimeeditsegment.startDateTile.setPrimaryText(new CharSequence[] {
                StringUtils.capitalizeStandalone(((String) (obj1)), Locale.getDefault())
            });
            eventtimeeditsegment.startDateTile.setContentDescription(eventtimeeditsegment.getResources().getString(0x7f13006d, new Object[] {
                obj1
            }));
            if (l != 0)
            {
                obj1 = (Calendar)((Calendar) (obj)).clone();
                if (flag2)
                {
                    if (((Calendar) (obj)).get(11) == 0 && ((Calendar) (obj)).get(12) == 0)
                    {
                        j = 1;
                    } else
                    {
                        j = 0;
                    }
                    if (j != 0)
                    {
                        ((Calendar) (obj1)).add(6, -1);
                    }
                }
                obj = eventtimeeditsegment.getContext();
                l1 = ((Calendar) (obj1)).getTimeInMillis();
                j = i1;
                if (((Context) (obj)).getResources().getBoolean(0x7f0c0006))
                {
                    j = 0x18016;
                }
                obj = Utils.formatDateTime(((Context) (obj)), l1, j, ((Calendar) (obj1)).getTimeZone().getID());
                eventtimeeditsegment.endDateTile.setPrimaryText(new CharSequence[] {
                    StringUtils.capitalizeStandalone(((String) (obj)), Locale.getDefault())
                });
                eventtimeeditsegment.endDateTile.setContentDescription(eventtimeeditsegment.getResources().getString(0x7f13006b, new Object[] {
                    obj
                }));
                flag2 = Utils.isValidEventTime(calendar, ((Calendar) (obj1)), flag2);
                obj = eventtimeeditsegment.getResources();
                if (flag2)
                {
                    j = 0x7f0d00aa;
                } else
                {
                    j = 0x7f0d00a9;
                }
                j = ((Resources) (obj)).getColor(j);
                eventtimeeditsegment.startDateTile.setPrimaryTextColor(j);
                eventtimeeditsegment.startTimeTextView.setTextColor(j);
                return;
            }
        }
    }

    public final View createView(LayoutInflater layoutinflater)
    {
        layoutinflater = (EventTimeEditSegment)layoutinflater.inflate(0x7f0500d0, null);
        layoutinflater.mListener = this;
        return layoutinflater;
    }

    public final Integer getErrorMessageId()
    {
        if (endDateTime != null && !Utils.isValidEventTime(startDateTime, endDateTime, ((EventModificationsHolder)(EditScreenModel)super.model).getEventModifications().isAllDayEvent()))
        {
            return Integer.valueOf(0x7f13030a);
        } else
        {
            return null;
        }
    }

    public final void onAddEndTimeClicked()
    {
        ((EventModificationsHolder)(EditScreenModel)super.model).getEventModifications().setEndTimeUnspecified(false);
        notifyTimeChanged(false, false);
        updateUi();
    }

    public final void onAllDayToggled(boolean flag)
    {
        EditSegmentController editsegmentcontroller;
        android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl;
        boolean flag1;
        EditScreenController editscreencontroller;
        Consumer consumer;
        Iterator iterator;
        if (flag)
        {
            long l = TimeUtils.toMidnight(startDateTime, false);
            long l1 = TimeUtils.toMidnight(endDateTime, true);
            ((EventModificationsHolder)(EditScreenModel)super.model).getEventModifications().setToAllDayWithDates(l, l1);
        } else
        {
            ((EventModificationsHolder)(EditScreenModel)super.model).getEventModifications().setToTimedWithTimes(startDateTime.getTimeInMillis(), endDateTime.getTimeInMillis());
        }
        ((EventModificationsHolder)(EditScreenModel)super.model).getEventModifications().setAvailability(AvailabilityUtils.getDefaultAvailability(((EventModificationsHolder)(EditScreenModel)super.model).getEventModifications()));
        notifyTimeChanged(false, false);
        editscreencontroller = super.editScreenController;
        consumer = com.google.android.calendar.newapi.screen.EditScreenController..Lambda._cls3.$instance;
        iterator = editscreencontroller.segments.segmentControllers.values().iterator();
_L10:
        if (!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        editsegmentcontroller = (EditSegmentController)iterator.next();
        if (editsegmentcontroller == this || editsegmentcontroller != null && editsegmentcontroller.equals(this))
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (editsegmentcontroller == null) goto _L4; else goto _L3
_L3:
        fragmentmanagerimpl = ((Fragment) (editsegmentcontroller)).mFragmentManager;
        if (editsegmentcontroller == null) goto _L6; else goto _L5
_L5:
        if (((Fragment) (editsegmentcontroller)).mHost != null && ((Fragment) (editsegmentcontroller)).mAdded)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1) goto _L7; else goto _L6
_L6:
        flag1 = false;
_L8:
        if (flag1)
        {
            consumer.accept(editsegmentcontroller);
        }
        continue; /* Loop/switch isn't completed */
_L7:
        FragmentActivity fragmentactivity;
        if (((Fragment) (editsegmentcontroller)).mHost == null)
        {
            fragmentactivity = null;
        } else
        {
            fragmentactivity = (FragmentActivity)((Fragment) (editsegmentcontroller)).mHost.mActivity;
        }
        if (fragmentactivity == null || fragmentactivity.isDestroyed() || fragmentactivity.isFinishing())
        {
            flag1 = false;
            continue; /* Loop/switch isn't completed */
        }
        if (fragmentmanagerimpl != null && !fragmentmanagerimpl.isDestroyed())
        {
            flag1 = true;
            continue; /* Loop/switch isn't completed */
        }
_L4:
        flag1 = false;
        if (true) goto _L8; else goto _L2
_L2:
        updateUi();
        logTimeChanged();
        return;
        if (true) goto _L10; else goto _L9
_L9:
    }

    public final void onCalendarChanged(boolean flag, boolean flag1)
    {
        EventModifications eventmodifications = ((EventModificationsHolder)(EditScreenModel)super.model).getEventModifications();
        if ((new AutoValue_EventDuration(eventmodifications.getEndMillis() - eventmodifications.getStartMillis(), eventmodifications.isEndTimeUnspecified())).equals(defaultDuration) && flag1)
        {
            defaultDuration = EventDuration.createDefault(((SettingsHolder)(EditScreenModel)super.model).getSettings());
            ((EventModificationsHolder)(EditScreenModel)super.model).getEventModifications().setEndMillis(((EventModificationsHolder)(EditScreenModel)super.model).getEventModifications().getStartMillis() + defaultDuration.getMillis());
            ((EventModificationsHolder)(EditScreenModel)super.model).getEventModifications().setEndTimeUnspecified(defaultDuration.isEndTimeUnspecified());
            reloadTimeFields();
        }
        updateUi();
    }

    public final void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        if (bundle != null)
        {
            instanceRestored = true;
            lastRequestedDateTimeType = (DateTimeType)bundle.get("TIME_TYPE");
            defaultDuration = (EventDuration)bundle.getParcelable("DEFAULT_DURATION");
            startDateTime = TimeUtils.readCalendarFromBundle(bundle, "START_TIME");
            endDateTime = TimeUtils.readCalendarFromBundle(bundle, "END_TIME");
        }
    }

    public final void onDateSet(int i, int j, int k)
    {
        Calendar calendar;
        boolean flag;
        if (lastRequestedDateTimeType == DateTimeType.START)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            calendar = startDateTime;
        } else
        {
            calendar = endDateTime;
        }
        calendar = (Calendar)calendar.clone();
        calendar.set(i, j, k);
        if (!flag && ((EventModificationsHolder)(EditScreenModel)super.model).getEventModifications().isAllDayEvent())
        {
            Calendar calendar1 = endDateTime;
            if (calendar1.get(11) == 0 && calendar1.get(12) == 0)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                calendar.add(6, 1);
            }
        }
        updateEventTime(calendar, lastRequestedDateTimeType);
        logTimeChanged();
    }

    public final void onEndDateClicked()
    {
        lastRequestedDateTimeType = DateTimeType.END;
        pickerFactory.showDatePickerWithMinDate(this, endDateTime, null);
    }

    public final void onEndTimeClicked()
    {
        lastRequestedDateTimeType = DateTimeType.END;
        DateTimePickerFactory.showTimePicker(this, endDateTime);
    }

    protected final void onInitialize()
    {
        if (!instanceRestored)
        {
            defaultDuration = EventDuration.createDefault(((SettingsHolder)(EditScreenModel)super.model).getSettings());
            reloadTimeFields();
        }
        updateUi();
    }

    public final void onMoreOptionsClicked()
    {
        ((MoreTimeOptionsStatusHolder)(EditScreenModel)super.model).setMoreTimeOptionsButtonClicked(true);
        notifyTimeChanged(false, false);
        updateUi();
        ((EventTimeEditSegment)super.view).announceForAccessibility(requireContext().getResources().getString(0x7f13001c));
    }

    public final void onSaveInstanceState(Bundle bundle)
    {
        bundle.putSerializable("TIME_TYPE", lastRequestedDateTimeType);
        bundle.putParcelable("DEFAULT_DURATION", defaultDuration);
        TimeUtils.writeCalendarToBundle(bundle, "START_TIME", startDateTime);
        TimeUtils.writeCalendarToBundle(bundle, "END_TIME", endDateTime);
        super.onSaveInstanceState(bundle);
    }

    public final void onStartDateClicked()
    {
        lastRequestedDateTimeType = DateTimeType.START;
        pickerFactory.showDatePickerWithMinDate(this, startDateTime, null);
    }

    public final void onStartTimeClicked()
    {
        lastRequestedDateTimeType = DateTimeType.START;
        DateTimePickerFactory.showTimePicker(this, startDateTime);
    }

    public final void onTimeRelatedFieldChanged(boolean flag, boolean flag1)
    {
        reloadTimeFields();
        updateUi();
        if (flag)
        {
            EventTimeEditSegment eventtimeeditsegment = (EventTimeEditSegment)super.view;
            int i = ((EditScreenModel)model).getColor().getValue();
            IconAnimator.animate(eventtimeeditsegment.timeIcon, i);
        }
    }

    public final void onTimeSet(int i, int j)
    {
        Calendar calendar;
        if (lastRequestedDateTimeType == DateTimeType.START)
        {
            calendar = startDateTime;
        } else
        {
            calendar = endDateTime;
        }
        calendar = (Calendar)calendar.clone();
        calendar.set(11, i);
        calendar.set(12, j);
        updateEventTime(calendar, lastRequestedDateTimeType);
        logTimeChanged();
    }
}
