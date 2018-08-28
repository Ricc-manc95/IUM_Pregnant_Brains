// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.findatime.ui;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.time.DateTimeImpl;
import com.google.android.calendar.time.DateTimeUtils;
import com.google.android.calendar.time.TimeZoneImpl;
import com.google.android.calendar.timely.FindTimeTimeframe;
import com.google.android.calendar.timely.FindTimeUtil;
import com.google.calendar.v2.client.service.api.time.DateTime;
import com.google.calendar.v2.client.service.api.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.TimeZone;

public class DurationTimeframe
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    private static final android.os.Parcelable.Creator INTEGER_CREATOR = new _cls2();
    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timely/findatime/ui/DurationTimeframe);
    public boolean considerExistingRooms;
    public DateTime customDate;
    public int customDurationIndex;
    public int customTimeframeOption;
    public int durationInMinutes;
    public ArrayList durationLabels;
    public ArrayList durationValues;
    public boolean hasRooms;
    public ArrayList timeframeComposableWithDurationLabels;
    public ArrayList timeframeLabels;
    public int timeframeOption;

    private DurationTimeframe(int i, ArrayList arraylist, ArrayList arraylist1, DateTime datetime, int j, ArrayList arraylist2, ArrayList arraylist3, 
            boolean flag, boolean flag1)
    {
        customDurationIndex = -1;
        customTimeframeOption = -1;
        timeframeOption = i;
        timeframeLabels = arraylist;
        timeframeComposableWithDurationLabels = arraylist1;
        customDate = datetime;
        durationInMinutes = j;
        durationLabels = arraylist3;
        durationValues = arraylist2;
        considerExistingRooms = false;
        hasRooms = flag1;
    }

    DurationTimeframe(Parcel parcel)
    {
        boolean flag1 = true;
        super();
        customDurationIndex = -1;
        customTimeframeOption = -1;
        timeframeOption = parcel.readInt();
        long l = parcel.readLong();
        String s = parcel.readString();
        boolean flag;
        if (l == -1L)
        {
            customDate = null;
        } else
        {
            customDate = new DateTimeImpl(l, new TimeZoneImpl(s));
        }
        durationInMinutes = parcel.readInt();
        timeframeLabels = new ArrayList();
        parcel.readStringList(timeframeLabels);
        timeframeComposableWithDurationLabels = new ArrayList();
        parcel.readStringList(timeframeComposableWithDurationLabels);
        durationLabels = new ArrayList();
        parcel.readStringList(durationLabels);
        durationValues = new ArrayList();
        parcel.readTypedList(durationValues, INTEGER_CREATOR);
        customDurationIndex = parcel.readInt();
        customTimeframeOption = parcel.readInt();
        if (parcel.readByte() == 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        considerExistingRooms = flag;
        if (parcel.readByte() == 1)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        hasRooms = flag;
    }

    public DurationTimeframe(DurationTimeframe durationtimeframe)
    {
        customDurationIndex = -1;
        customTimeframeOption = -1;
        timeframeOption = durationtimeframe.timeframeOption;
        timeframeLabels = new ArrayList(durationtimeframe.timeframeLabels);
        timeframeComposableWithDurationLabels = new ArrayList(durationtimeframe.timeframeComposableWithDurationLabels);
        Object obj;
        if (durationtimeframe.customDate == null)
        {
            obj = null;
        } else
        {
            obj = new DateTimeImpl(durationtimeframe.customDate.getMillis(), durationtimeframe.customDate.getTimeZone());
        }
        customDate = ((DateTime) (obj));
        durationInMinutes = durationtimeframe.durationInMinutes;
        customDurationIndex = durationtimeframe.customDurationIndex;
        durationLabels = new ArrayList(durationtimeframe.durationLabels);
        durationValues = new ArrayList(durationtimeframe.durationValues);
        customTimeframeOption = durationtimeframe.customTimeframeOption;
        considerExistingRooms = durationtimeframe.considerExistingRooms;
        hasRooms = durationtimeframe.hasRooms;
    }

    public static DurationTimeframe getDefault(long l, long l1, TimeZone timezone, ArrayList arraylist, ArrayList arraylist1, ArrayList arraylist2, 
            ArrayList arraylist3, boolean flag)
    {
        Object obj = null;
        byte byte0 = 2;
        if (!FindTimeUtil.isToday(l))
        {
            byte0 = 4;
            obj = Calendar.getInstance();
            ((Calendar) (obj)).setTimeZone(timezone);
            ((Calendar) (obj)).setTimeInMillis(l);
            obj = DateTimeUtils.getNowDateTime(timezone.getID()).withDate(((Calendar) (obj)).get(1), ((Calendar) (obj)).get(2) + 1, ((Calendar) (obj)).get(5));
        }
        l = (l1 - l) / 60000L;
        int i = (int)l;
        if ((long)i != l)
        {
            throw new IllegalArgumentException((new StringBuilder(34)).append("Out of range: ").append(l).toString());
        } else
        {
            return new DurationTimeframe(byte0, arraylist, arraylist1, ((DateTime) (obj)), i, arraylist3, arraylist2, false, flag);
        }
    }

    private final long getTimeframeStartMillis(TimeZone timezone)
    {
        DateTime datetime;
        datetime = DateTimeUtils.getNowDateTime(timezone.getID());
        timezone = datetime;
        timeframeOption;
        JVM INSTR tableswitch 0 4: default 48
    //                   0 96
    //                   1 96
    //                   2 96
    //                   3 58
    //                   4 103;
           goto _L1 _L2 _L2 _L2 _L3 _L4
_L2:
        break; /* Loop/switch isn't completed */
_L1:
        throw new IllegalStateException("Invalid timeframe option is set");
_L3:
        DateTime datetime1 = customDate.minusPeriod(new Period(0, 0, 0, 1, 0, 0, 0L));
        timezone = datetime;
        if (datetime1.isAfter(datetime))
        {
            timezone = datetime1;
        }
_L6:
        return timezone.getMillis();
_L4:
        timezone = customDate;
        if (timezone == null)
        {
            timezone = null;
        } else
        {
            timezone = timezone.withMillisOfDay(0);
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    public static boolean isDateBasedTimeframe(int i)
    {
        return i == 3 || i == 4;
    }

    public final void addDurationValue(int i)
    {
        durationValues.add(Integer.valueOf(i));
        Collections.sort(durationValues);
        durationInMinutes = i;
        customDurationIndex = durationValues.indexOf(Integer.valueOf(i));
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if (obj instanceof DurationTimeframe)
        {
            if (timeframeOption == ((DurationTimeframe) (obj = (DurationTimeframe)obj)).timeframeOption && customTimeframeOption == ((DurationTimeframe) (obj)).customTimeframeOption && customDurationIndex == ((DurationTimeframe) (obj)).customDurationIndex)
            {
                ArrayList arraylist = timeframeLabels;
                ArrayList arraylist3 = ((DurationTimeframe) (obj)).timeframeLabels;
                boolean flag;
                if (arraylist == arraylist3 || arraylist != null && arraylist.equals(arraylist3))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    ArrayList arraylist1 = timeframeComposableWithDurationLabels;
                    ArrayList arraylist4 = ((DurationTimeframe) (obj)).timeframeComposableWithDurationLabels;
                    if (arraylist1 == arraylist4 || arraylist1 != null && arraylist1.equals(arraylist4))
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag)
                    {
                        ArrayList arraylist2 = durationValues;
                        ArrayList arraylist5 = ((DurationTimeframe) (obj)).durationValues;
                        if (arraylist2 == arraylist5 || arraylist2 != null && arraylist2.equals(arraylist5))
                        {
                            flag = true;
                        } else
                        {
                            flag = false;
                        }
                        if (flag)
                        {
                            Object obj1 = customDate;
                            Object obj2 = ((DurationTimeframe) (obj)).customDate;
                            if (obj1 == null)
                            {
                                if (obj2 == null)
                                {
                                    flag = true;
                                } else
                                {
                                    flag = false;
                                }
                            } else
                            if (obj2 != null && ((DateTime) (obj1)).getMillis() == ((DateTime) (obj2)).getMillis() && TextUtils.equals(((DateTime) (obj1)).getTimeZone().getId(), ((DateTime) (obj2)).getTimeZone().getId()))
                            {
                                flag = true;
                            } else
                            {
                                flag = false;
                            }
                            if (flag)
                            {
                                obj1 = Integer.valueOf(durationInMinutes);
                                obj2 = Integer.valueOf(((DurationTimeframe) (obj)).durationInMinutes);
                                if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
                                {
                                    flag = true;
                                } else
                                {
                                    flag = false;
                                }
                                if (flag && considerExistingRooms == ((DurationTimeframe) (obj)).considerExistingRooms && hasRooms == ((DurationTimeframe) (obj)).hasRooms)
                                {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public final int getSelectedDurationIndex()
    {
        boolean flag;
        for (int i = 0; i < durationLabels.size(); i++)
        {
            if (i < durationValues.size() && ((Integer)durationValues.get(i)).intValue() == durationInMinutes)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                return i;
            }
        }

        LogUtils.wtf(TAG, "Could not find an index to select for the duration.", new Object[0]);
        return 1;
    }

    public final int getSelectedTimeframeIndex()
    {
        int i = timeframeOption;
        int j;
        if (i == 3 || i == 4)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            i = 5;
        } else
        {
            i = timeframeOption;
        }
        for (j = 0; j < timeframeLabels.size(); j++)
        {
            if (j == i)
            {
                return j;
            }
        }

        LogUtils.wtf(TAG, "Could not find an index to select for the timeframe.", new Object[0]);
        return 1;
    }

    public final FindTimeTimeframe getTimeframe(TimeZone timezone)
    {
        if (timeframeOption == 3)
        {
            DateTime datetime = customDate;
            long l;
            if (datetime == null)
            {
                datetime = null;
            } else
            {
                datetime = datetime.withMillisOfDay(0);
            }
            l = datetime.getMillis();
            return new FindTimeTimeframe(4, getTimeframeStartMillis(timezone), Long.valueOf(getTimeframeEnd(DateTimeUtils.getNowDateTime(timezone.getID())).getMillis()), (long)durationInMinutes * 60000L, Long.valueOf(l));
        }
        byte byte0;
        if (timeframeOption == 4)
        {
            byte0 = 5;
        } else
        if (timeframeOption == 0)
        {
            byte0 = 1;
        } else
        if (timeframeOption == 1)
        {
            byte0 = 2;
        } else
        if (timeframeOption == 2)
        {
            byte0 = 3;
        } else
        {
            LogUtils.wtf(TAG, "I cannot recognize the type of timeframe, defaulting to within a week.", new Object[0]);
            byte0 = 3;
        }
        return FindTimeTimeframe.other(byte0, getTimeframeStartMillis(timezone), getTimeframeEnd(DateTimeUtils.getNowDateTime(timezone.getID())).getMillis(), (long)durationInMinutes * 60000L);
    }

    public final DateTime getTimeframeEnd(DateTime datetime)
    {
        switch (timeframeOption)
        {
        default:
            throw new IllegalStateException("Invalid timeframe option is set");

        case 0: // '\0'
            return DateTimeUtils.roundUpToMidnight(datetime);

        case 1: // '\001'
            return datetime.plusPeriod(new Period(0, 0, 0, 3, 0, 0, 0L));

        case 2: // '\002'
            return datetime.plusPeriod(new Period(0, 0, 0, 7, 0, 0, 0L));

        case 3: // '\003'
            return DateTimeUtils.roundUpToMidnight(customDate.plusPeriod(new Period(0, 0, 0, 1, 0, 0, 0L)));

        case 4: // '\004'
            return DateTimeUtils.roundUpToMidnight(customDate);
        }
    }

    public final String getTimeframeTag()
    {
        switch (timeframeOption)
        {
        default:
            return "INVALID";

        case 0: // '\0'
            return "TODAY";

        case 1: // '\001'
            return "WITHIN_A_FEW_DAYS";

        case 2: // '\002'
            return "WITHIN_A_WEEK";

        case 3: // '\003'
            return "AROUND_DATE";

        case 4: // '\004'
            return "ON_DATE";

        case 5: // '\005'
            return "CUSTOM";
        }
    }

    public int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            Integer.valueOf(timeframeOption), Integer.valueOf(customTimeframeOption), Integer.valueOf(customDurationIndex), timeframeLabels, timeframeComposableWithDurationLabels, durationValues, Long.valueOf(customDate.getMillis()), customDate.getTimeZone().getId(), Integer.valueOf(durationInMinutes), Boolean.valueOf(considerExistingRooms), 
            Boolean.valueOf(hasRooms)
        });
    }

    public final void removeCustomTimeframeLabel()
    {
        if (timeframeLabels.size() == 6)
        {
            customTimeframeOption = -1;
            timeframeLabels.remove(5);
            timeframeComposableWithDurationLabels.remove(5);
        }
    }

    public String toString()
    {
        int i = timeframeOption;
        int j = customTimeframeOption;
        int k = customDurationIndex;
        ArrayList arraylist = timeframeLabels;
        ArrayList arraylist1 = timeframeComposableWithDurationLabels;
        ArrayList arraylist2 = durationLabels;
        ArrayList arraylist3 = durationValues;
        String s;
        long l;
        if (customDate == null)
        {
            l = -1L;
        } else
        {
            l = customDate.getMillis();
        }
        if (customDate == null)
        {
            s = "null";
        } else
        {
            s = customDate.getTimeZone().getId();
        }
        return String.format(null, "{mTimeframeOption=%d, mCustomTimeframeOption=%d, mCustomDurationIndex=%d, mTimeframeLabels=%s, mTimeframeComposableWithDurationLabels=%s, mDurationLabels=%s, mDurationValues=%s, cstDate.millis=%d, cstDate.tz=%s, durationInMinutes=%d,considerExistingRooms=%b, hasRooms=%b}", new Object[] {
            Integer.valueOf(i), Integer.valueOf(j), Integer.valueOf(k), arraylist, arraylist1, arraylist2, arraylist3, Long.valueOf(l), s, Integer.valueOf(durationInMinutes), 
            Boolean.valueOf(considerExistingRooms), Boolean.valueOf(hasRooms)
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeInt(timeframeOption);
        byte byte0;
        String s;
        long l;
        if (customDate == null)
        {
            l = -1L;
        } else
        {
            l = customDate.getMillis();
        }
        parcel.writeLong(l);
        if (customDate == null)
        {
            s = "";
        } else
        {
            s = customDate.getTimeZone().getId();
        }
        parcel.writeString(s);
        parcel.writeInt(durationInMinutes);
        parcel.writeStringList(timeframeLabels);
        parcel.writeStringList(timeframeComposableWithDurationLabels);
        parcel.writeStringList(durationLabels);
        parcel.writeList(durationValues);
        parcel.writeInt(customDurationIndex);
        parcel.writeInt(customTimeframeOption);
        if (considerExistingRooms)
        {
            byte0 = 1;
        } else
        {
            byte0 = 0;
        }
        parcel.writeByte(byte0);
        if (hasRooms)
        {
            byte0 = flag;
        } else
        {
            byte0 = 0;
        }
        parcel.writeByte(byte0);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new DurationTimeframe(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new DurationTimeframe[i];
        }

        _cls1()
        {
        }
    }


    private class _cls2
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return Integer.valueOf(parcel.readInt());
        }

        public final Object[] newArray(int i)
        {
            return new Integer[i];
        }

        _cls2()
        {
        }
    }

}
