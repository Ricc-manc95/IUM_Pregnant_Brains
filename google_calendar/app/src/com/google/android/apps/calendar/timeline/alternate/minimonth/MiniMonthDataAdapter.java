// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.minimonth;

import android.util.SparseArray;
import com.google.android.apps.calendar.timeline.alternate.minimonth.data.MiniMonthDayDataFactory;
import com.google.android.apps.calendar.timeline.alternate.store.CalendarContentStore;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import java.util.Calendar;

final class MiniMonthDataAdapter
{

    public final Calendar calendar = Calendar.getInstance();
    public final MiniMonthDayDataFactory dayDataFactory;
    public final ObservableReference invalidationObservable = new com.google.android.apps.calendar.util.concurrent.ObservableReferences._cls1(new Object());
    public final TimeUtils timeUtils;
    public final SparseArray weeks = new SparseArray();

    MiniMonthDataAdapter(CalendarContentStore calendarcontentstore, MiniMonthDayDataFactory minimonthdaydatafactory, TimeUtils timeutils)
    {
        dayDataFactory = minimonthdaydatafactory;
        timeUtils = timeutils;
        class .Lambda._cls0
            implements Consumer
        {

            private final MiniMonthDataAdapter arg$1;

            public final void accept(Object obj)
            {
                MiniMonthDataAdapter minimonthdataadapter = arg$1;
                obj = (Collection)obj;
                SparseArray sparsearray;
                com.google.common.collect.ImmutableList.Builder builder;
                int j;
                for (Iterator iterator = ((Collection) (obj)).iterator(); iterator.hasNext(); sparsearray.put(j, ImmutableList.asImmutableList(builder.contents, builder.size)))
                {
                    Object obj1 = (CalendarWeek)iterator.next();
                    sparsearray = minimonthdataadapter.weeks;
                    j = ((CalendarWeek) (obj1)).getJulianWeek();
                    builder = ImmutableList.builder();
                    obj1 = (ImmutableList)((CalendarWeek) (obj1)).getDays();
                    int k = ((ImmutableList) (obj1)).size();
                    int i = 0;
                    com.google.common.collect.Collections2.TransformedCollection transformedcollection;
                    for (Object obj2 = (UnmodifiableIterator)null; i < k; obj2 = (com.google.common.collect.ImmutableList.Builder)builder.add(minimonthdataadapter.dayDataFactory.createData(((CalendarDay) (obj2)).getJulianDate(), transformedcollection)))
                    {
                        obj2 = ((ImmutableList) (obj1)).get(i);
                        i++;
                        obj2 = (CalendarDay)obj2;
                        class .Lambda._cls1
                            implements Function
                        {

                            public static final Function $instance = new .Lambda._cls1();

                            public final Object apply(Object obj3)
                            {
                                return ((VersionedItem)obj3).getItem();
                            }


                        private .Lambda._cls1()
                        {
                        }
                        }

                        transformedcollection = new com.google.common.collect.Collections2.TransformedCollection(((CalendarDay) (obj2)).getItems(), .Lambda._cls1..instance);
                    }

                    builder.forceCopy = true;
                }

                minimonthdataadapter.invalidationObservable.set(obj);
            }

            .Lambda._cls0()
            {
                arg$1 = MiniMonthDataAdapter.this;
            }
        }

        calendarcontentstore.subscribe(new .Lambda._cls0(), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
    }
}
