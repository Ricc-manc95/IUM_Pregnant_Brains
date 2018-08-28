// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.recurrencepicker;

import android.os.Parcel;
import com.google.android.calendar.utils.datatypes.ImmutableSetAdapter;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableSet;
import java.util.TimeZone;

// Referenced classes of package com.google.android.calendar.recurrencepicker:
//            $AutoValue_RecurrencePickerState, RecurrencePickerState

final class AutoValue_RecurrencePickerState extends $AutoValue_RecurrencePickerState
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public static final ImmutableSetAdapter IMMUTABLE_SET_ADAPTER = new ImmutableSetAdapter();

    AutoValue_RecurrencePickerState(RecurrencePickerState.Frequency frequency, Long long1, Integer integer, Integer integer1, ImmutableSet immutableset, ImmutableSet immutableset1, RecurrencePickerState.End end, 
            RecurrencePickerState.MonthFrequency monthfrequency, Boolean boolean1, Boolean boolean2, Long long2, TimeZone timezone, Integer integer2, Integer integer3)
    {
        super(frequency, long1, integer, integer1, immutableset, immutableset1, end, monthfrequency, boolean1, boolean2, long2, timezone, integer2, integer3);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeString(getFrequency().name());
        parcel.writeLong(getUntilDateTimeMillis().longValue());
        parcel.writeInt(getCount().intValue());
        parcel.writeInt(getInterval().intValue());
        parcel.writeList(getByDay().asList());
        parcel.writeList(getByMonthDay().asList());
        parcel.writeString(getEnd().name());
        parcel.writeString(getMonthFrequency().name());
        if (getHasLastOption().booleanValue())
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        if (getHasNthOption().booleanValue())
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        parcel.writeLong(getStartTimeInMillis().longValue());
        parcel.writeSerializable(getTimeZone());
        parcel.writeInt(getFirstDayOfWeek().intValue());
        parcel.writeInt(getStartWeekday().intValue());
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            RecurrencePickerState.Frequency frequency = RecurrencePickerState.Frequency.valueOf(parcel.readString());
            long l = parcel.readLong();
            int i = parcel.readInt();
            int j = parcel.readInt();
            ImmutableSet immutableset = ImmutableSet.copyOf(parcel.readArrayList(com/google/android/calendar/utils/datatypes/ImmutableSetAdapter.getClassLoader()));
            ImmutableSet immutableset1 = ImmutableSet.copyOf(parcel.readArrayList(com/google/android/calendar/utils/datatypes/ImmutableSetAdapter.getClassLoader()));
            RecurrencePickerState.End end = RecurrencePickerState.End.valueOf(parcel.readString());
            RecurrencePickerState.MonthFrequency monthfrequency = RecurrencePickerState.MonthFrequency.valueOf(parcel.readString());
            boolean flag;
            boolean flag1;
            if (parcel.readInt() == 1)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (parcel.readInt() == 1)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            return new AutoValue_RecurrencePickerState(frequency, Long.valueOf(l), Integer.valueOf(i), Integer.valueOf(j), immutableset, immutableset1, end, monthfrequency, Boolean.valueOf(flag), Boolean.valueOf(flag1), Long.valueOf(parcel.readLong()), (TimeZone)parcel.readSerializable(), Integer.valueOf(parcel.readInt()), Integer.valueOf(parcel.readInt()));
        }

        public final Object[] newArray(int i)
        {
            return new AutoValue_RecurrencePickerState[i];
        }

        _cls1()
        {
        }
    }

}
