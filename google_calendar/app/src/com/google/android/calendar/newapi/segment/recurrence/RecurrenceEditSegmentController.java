// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.recurrence;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.google.android.apps.calendar.util.preference.PreferenceUtils;
import com.google.android.calendar.api.event.time.ByDayRecurrence;
import com.google.android.calendar.api.event.time.RecurRulePart;
import com.google.android.calendar.api.event.time.Recurrence;
import com.google.android.calendar.api.event.time.RecurrenceParser;
import com.google.android.calendar.common.dialog.SingleChoiceDialogListener;
import com.google.android.calendar.common.dialog.SingleChoiceTextDialog;
import com.google.android.calendar.newapi.model.ColorHolder;
import com.google.android.calendar.newapi.model.RecurrenceHolder;
import com.google.android.calendar.newapi.model.TimeHolder;
import com.google.android.calendar.newapi.model.TimeZoneHolder;
import com.google.android.calendar.newapi.model.edit.RecurrenceEditHolder;
import com.google.android.calendar.newapi.segment.common.EditSegmentController;
import com.google.android.calendar.newapi.segment.common.SegmentController;
import com.google.android.calendar.newapi.segment.time.TimeUtils;
import com.google.android.calendar.recurrencepicker.RecurrencePickerActivity;
import com.google.android.calendar.recurrencepicker.RecurrencePickerUtils;
import com.google.android.calendar.recurrencepicker.StateConverter;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.viewedit.segment.edit.EditSegment;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.SingletonImmutableSet;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

// Referenced classes of package com.google.android.calendar.newapi.segment.recurrence:
//            RecurrenceEditSegment, RecurrenceChoiceCreator

public class RecurrenceEditSegmentController extends EditSegmentController
    implements SingleChoiceDialogListener, RecurrenceEditSegment.Listener
{

    private RecurrenceUtils.RecurrencePickerFactory recurrencePickerFactory;
    private RecurrencePickerUtils recurrencePickerUtils;

    public RecurrenceEditSegmentController()
    {
        recurrencePickerFactory = new RecurrenceUtils.RecurrencePickerFactory();
        recurrencePickerUtils = new RecurrencePickerUtils();
    }

    private final void updateRecurrence(Recurrence recurrence)
    {
        boolean flag;
        boolean flag1;
        if (((RecurrenceHolder)(ColorHolder)model).getRecurrence() != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (recurrence != null)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (!flag && !flag1)
        {
            return;
        }
        ((RecurrenceEditHolder)(ColorHolder)model).setRecurrence(recurrence);
        boolean flag2;
        if (!flag)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        notifyTimeChanged(false, flag2);
        updateUi();
    }

    private final void updateUi()
    {
        View view = super.view;
        boolean flag = ((RecurrenceEditHolder)(ColorHolder)model).canModifyRecurrence(getContext());
        if (view != null)
        {
            int i;
            if (flag)
            {
                i = 0;
            } else
            {
                i = 8;
            }
            view.setVisibility(i);
        }
        if (!flag)
        {
            return;
        } else
        {
            String s = TimeUtils.getSimplifiedRecurrenceString(requireContext().getResources(), ((RecurrenceHolder)(ColorHolder)model).getRecurrence(), com.google.android.calendar.newapi.segment.time.TimeUtils.DisplayForm.LONG);
            RecurrenceEditSegment recurrenceeditsegment = (RecurrenceEditSegment)super.view;
            String s1 = TimeUtils.getSimplifiedRecurrenceString(requireContext().getResources(), ((RecurrenceHolder)(ColorHolder)model).getRecurrence(), com.google.android.calendar.newapi.segment.time.TimeUtils.DisplayForm.HEURISTIC);
            recurrenceeditsegment.tile.setPrimaryText(new CharSequence[] {
                s1
            });
            recurrenceeditsegment.tile.primaryLine.setContentDescription(s);
            ((RecurrenceEditSegment)super.view).announceForAccessibility(requireContext().getResources().getString(0x7f13000c, new Object[] {
                s
            }));
            return;
        }
    }

    public final View createView(LayoutInflater layoutinflater)
    {
        layoutinflater = (RecurrenceEditSegment)layoutinflater.inflate(0x7f0500e0, null);
        layoutinflater.mListener = this;
        return layoutinflater;
    }

    public final void onActivityResult(int i, int j, Intent intent)
    {
        Object obj = null;
        if (i == 0 && j == -1)
        {
            intent = intent.getStringExtra("recurrence_result");
            if (TextUtils.isEmpty(intent))
            {
                intent = obj;
            } else
            {
                intent = RecurrenceParser.parseFromStoreStrings(intent, null, null, null);
            }
            updateRecurrence(intent);
        }
    }

    public final void onCalendarChanged(boolean flag, boolean flag1)
    {
        updateUi();
    }

    public final void onDialogItemChosen(Object obj, int i)
    {
        obj = (Recurrence)obj;
        if (RecurrenceChoiceCreator.isCustomRecurrence(((Recurrence) (obj))))
        {
            Object obj2 = getContext();
            Integer integer = Integer.valueOf(((ColorHolder)model).getColor(getContext()));
            long l = ((TimeHolder)(ColorHolder)model).getStart(getContext());
            Object obj1;
            Long long1;
            com.google.android.calendar.recurrencepicker..AutoValue_RecurrencePickerState.Builder builder;
            RecurRulePart recurrulepart;
            boolean flag;
            if (((TimeHolder)(ColorHolder)model).isAllDay())
            {
                obj1 = "UTC";
            } else
            {
                obj1 = ((TimeZoneHolder)(ColorHolder)model).getTimeZoneId(getContext());
            }
            obj = ((RecurrenceHolder)(ColorHolder)model).getRecurrence();
            if (obj == null)
            {
                i = PreferenceUtils.getFirstDayOfWeekAsCalendar(((Context) (obj2)));
                obj = new com.google.android.calendar.api.event.time.RecurRulePart.Builder(4);
                obj.wkst = Integer.valueOf(i);
                obj = new Recurrence(new RecurRulePart[] {
                    ((com.google.android.calendar.api.event.time.RecurRulePart.Builder) (obj)).build()
                });
            }
            recurrulepart = (RecurRulePart)((Recurrence) (obj)).rrules.get(0);
            i = PreferenceUtils.getFirstDayOfWeekAsCalendar(((Context) (obj2)));
            long1 = Long.valueOf(l);
            builder = new com.google.android.calendar.recurrencepicker..AutoValue_RecurrencePickerState.Builder();
            obj2 = Calendar.getInstance(TimeZone.getTimeZone(((String) (obj1))), ((Context) (obj2)).getResources().getConfiguration().locale);
            ((Calendar) (obj2)).setTimeInMillis(long1.longValue());
            ((Calendar) (obj2)).setFirstDayOfWeek(i);
            builder.setFrequency(com.google.android.calendar.recurrencepicker.RecurrencePickerState.Frequency.WEEKLY);
            obj = (Calendar)((Calendar) (obj2)).clone();
            ((Calendar) (obj)).set(2, ((Calendar) (obj)).get(2) + 1);
            builder.setUntilDateTimeMillis(Long.valueOf(((Calendar) (obj)).getTimeInMillis()));
            builder.setCount(Integer.valueOf(1));
            builder.setInterval(Integer.valueOf(1));
            builder.setByDay(new SingletonImmutableSet(Integer.valueOf(((Calendar) (obj2)).get(7))));
            builder.setByMonthDay(new SingletonImmutableSet(Integer.valueOf(((Calendar) (obj2)).get(5))));
            if (StateConverter.RecurRuleFreqToStateFrequency(recurrulepart.freq) != null)
            {
                builder.setFrequency(StateConverter.RecurRuleFreqToStateFrequency(recurrulepart.freq));
            }
            if (recurrulepart.untilDateTimeMillis != null)
            {
                builder.setUntilDateTimeMillis(recurrulepart.untilDateTimeMillis);
            }
            if (recurrulepart.count != null)
            {
                builder.setCount(recurrulepart.count);
            }
            if (recurrulepart.interval != null)
            {
                builder.setInterval(recurrulepart.interval);
            }
            if (!StateConverter.recurRuleByDayToStateByDay(recurrulepart).isEmpty())
            {
                builder.setByDay(StateConverter.recurRuleByDayToStateByDay(recurrulepart));
            }
            if (recurrulepart.untilDateTimeMillis != null)
            {
                obj = com.google.android.calendar.recurrencepicker.RecurrencePickerState.End.DATE;
            } else
            if (recurrulepart.count != null)
            {
                obj = com.google.android.calendar.recurrencepicker.RecurrencePickerState.End.COUNT;
            } else
            {
                obj = com.google.android.calendar.recurrencepicker.RecurrencePickerState.End.INFINITE;
            }
            builder.setEnd(((com.google.android.calendar.recurrencepicker.RecurrencePickerState.End) (obj)));
            if (recurrulepart.freq == 5 && !recurrulepart.byDay.isEmpty())
            {
                if (Integer.valueOf(-1).equals(((ByDayRecurrence)recurrulepart.byDay.get(0)).offset))
                {
                    obj = com.google.android.calendar.recurrencepicker.RecurrencePickerState.MonthFrequency.LAST;
                } else
                {
                    obj = com.google.android.calendar.recurrencepicker.RecurrencePickerState.MonthFrequency.WEEKDAY;
                }
            } else
            {
                obj = com.google.android.calendar.recurrencepicker.RecurrencePickerState.MonthFrequency.MONTHDAY;
            }
            builder.setMonthFrequency(((com.google.android.calendar.recurrencepicker.RecurrencePickerState.MonthFrequency) (obj)));
            if (((Calendar) (obj2)).get(5) + 7 > ((Calendar) (obj2)).getActualMaximum(5))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            builder.setHasLastOption(Boolean.valueOf(flag));
            if (((Calendar) (obj2)).get(8) < 5)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            builder.setHasNthOption(Boolean.valueOf(flag));
            builder.setStartTimeInMillis(long1);
            builder.setTimeZone(TimeZone.getTimeZone(((String) (obj1))));
            builder.setFirstDayOfWeek(Integer.valueOf(i));
            builder.setStartWeekday(Integer.valueOf(((Calendar) (obj2)).get(7)));
            obj = builder.build();
            obj1 = new Bundle(2);
            ((Bundle) (obj1)).putParcelable("bundle_state", ((android.os.Parcelable) (obj)));
            if (integer != null)
            {
                ((Bundle) (obj1)).putInt("event_color", integer.intValue());
            }
            if (super.mHost == null)
            {
                obj = null;
            } else
            {
                obj = (FragmentActivity)super.mHost.mActivity;
            }
            obj = new Intent(((Context) (obj)), com/google/android/calendar/recurrencepicker/RecurrencePickerActivity);
            ((Intent) (obj)).putExtras(((Bundle) (obj1)));
            startActivityForResult(((Intent) (obj)), 0);
            return;
        } else
        {
            updateRecurrence(((Recurrence) (obj)));
            return;
        }
    }

    protected final void onInitialize()
    {
        updateUi();
    }

    public final void onRecurrenceClicked()
    {
        Recurrence recurrence = ((RecurrenceHolder)(ColorHolder)model).getRecurrence();
        if (this == null) goto _L2; else goto _L1
_L1:
        android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl = super.mFragmentManager;
        if (this == null) goto _L4; else goto _L3
_L3:
        boolean flag;
        if (super.mHost != null && super.mAdded)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L5; else goto _L4
_L4:
        flag = false;
_L7:
        if (flag)
        {
            Object obj = (new RecurrenceChoiceCreator(getContext())).createList(recurrence);
            obj = SingleChoiceTextDialog.newParcelableBaseInstance(((com.google.android.calendar.newapi.segment.common.ChoiceCreator.ChoiceList) (obj)).labels, ((com.google.android.calendar.newapi.segment.common.ChoiceCreator.ChoiceList) (obj)).values, ((com.google.android.calendar.newapi.segment.common.ChoiceCreator.ChoiceList) (obj)).selectedPosition, this, 0);
            super.mFragmentManager.beginTransaction().add(((Fragment) (obj)), "RecurrenceDialog").commitAllowingStateLoss();
        }
        return;
_L5:
        FragmentActivity fragmentactivity;
        if (super.mHost == null)
        {
            fragmentactivity = null;
        } else
        {
            fragmentactivity = (FragmentActivity)super.mHost.mActivity;
        }
        if (fragmentactivity == null || fragmentactivity.isDestroyed() || fragmentactivity.isFinishing())
        {
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        if (fragmentmanagerimpl != null && !fragmentmanagerimpl.isDestroyed())
        {
            flag = true;
            continue; /* Loop/switch isn't completed */
        }
_L2:
        flag = false;
        if (true) goto _L7; else goto _L6
_L6:
    }

    public final void onTimeRelatedFieldChanged(boolean flag, boolean flag1)
    {
        updateUi();
    }
}
