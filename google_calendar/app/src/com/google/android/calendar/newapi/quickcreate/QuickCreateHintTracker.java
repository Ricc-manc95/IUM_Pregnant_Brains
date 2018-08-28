// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.quickcreate;

import android.app.backup.BackupManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;

public final class QuickCreateHintTracker
    implements Parcelable
{
    public static class Counter
        implements Parcelable
    {

        public static final android.os.Parcelable.Creator CREATOR = new _cls1();
        public final int threshold;
        public int value;

        public static Counter createFromSharedPref(Context context, int i, String s)
        {
            return new Counter(context.getResources().getInteger(i), context.getSharedPreferences("com.google.android.calendar_preferences", 0).getInt(s, 0));
        }

        static void writeToSharedPref(Context context, String s, Counter counter)
        {
            int i = counter.value;
            context.getSharedPreferences("com.google.android.calendar_preferences", 0).edit().putInt(s, i).apply();
            (new BackupManager(context)).dataChanged();
        }

        public int describeContents()
        {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(threshold);
            parcel.writeInt(value);
        }


        private Counter(int i, int j)
        {
            threshold = i;
            value = j;
        }

        Counter(Parcel parcel)
        {
            threshold = parcel.readInt();
            value = parcel.readInt();
        }

        class _cls1
            implements android.os.Parcelable.Creator
        {

            public final Object createFromParcel(Parcel parcel)
            {
                return new Counter(parcel);
            }

            public final Object[] newArray(int i)
            {
                return new Counter[i];
            }

                _cls1()
                {
                }
        }

    }


    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public Counter completedSessions;
    public int usage;
    public Counter usedConnectors;

    QuickCreateHintTracker(Parcel parcel)
    {
        usage = 0;
        completedSessions = (Counter)parcel.readParcelable(com/google/android/calendar/newapi/quickcreate/QuickCreateHintTracker$Counter.getClassLoader());
        usedConnectors = (Counter)parcel.readParcelable(com/google/android/calendar/newapi/quickcreate/QuickCreateHintTracker$Counter.getClassLoader());
        usage = parcel.readInt();
    }

    public QuickCreateHintTracker(Counter counter, Counter counter1)
    {
        usage = 0;
        completedSessions = counter;
        usedConnectors = counter1;
    }

    static final void lambda$saveToSharedPreferences$0$QuickCreateHintTracker(QuickCreateHintTracker quickcreatehinttracker, Context context)
    {
        boolean flag1 = true;
        boolean flag;
        if (quickcreatehinttracker.usage > 0)
        {
            Counter counter = quickcreatehinttracker.completedSessions;
            if (counter.value >= 0 && counter.value < counter.threshold)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                counter = quickcreatehinttracker.completedSessions;
                if (counter.value >= 0)
                {
                    counter.value = Math.min(counter.value + 1, counter.threshold);
                }
                Counter.writeToSharedPref(context, "com.google.android.calendar.event.quickcreate.hints.event_created", quickcreatehinttracker.completedSessions);
            }
        }
        if (quickcreatehinttracker.usage >= 2)
        {
            counter = quickcreatehinttracker.usedConnectors;
            if (counter.value >= 0 && counter.value < counter.threshold)
            {
                flag = flag1;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                counter = quickcreatehinttracker.usedConnectors;
                if (counter.value >= 0)
                {
                    counter.value = Math.min(counter.value + 1, counter.threshold);
                }
                Counter.writeToSharedPref(context, "com.google.android.calendar.event.quickcreate.hints.connector_accepted", quickcreatehinttracker.usedConnectors);
            }
        }
    }

    public static void saveToSharedPreferences(Context context, QuickCreateHintTracker quickcreatehinttracker)
    {
        class .Lambda._cls0
            implements Runnable
        {

            private final QuickCreateHintTracker arg$1;
            private final Context arg$2;

            public final void run()
            {
                QuickCreateHintTracker.lambda$saveToSharedPreferences$0$QuickCreateHintTracker(arg$1, arg$2);
            }

            .Lambda._cls0(Context context)
            {
                arg$1 = QuickCreateHintTracker.this;
                arg$2 = context;
            }
        }

        CalendarExecutor.DISK.execute(quickcreatehinttracker. new .Lambda._cls0(context));
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(completedSessions, i);
        parcel.writeParcelable(usedConnectors, i);
        parcel.writeInt(usage);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new QuickCreateHintTracker(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new QuickCreateHintTracker[i];
        }

        _cls1()
        {
        }
    }

}
