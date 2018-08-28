// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.timeline;

import android.animation.Animator;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.timeline.chip.Chip;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.TimelineItemOperation;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TimelineItemCollection
{
    public static interface AnimatorProvider
    {

        public abstract Animator createAfterExcludeItemAnimator(Chip chip, TimelineItem timelineitem);

        public abstract Animator createBeforeIncludeItemAnimator(TimelineItem timelineitem);

        public abstract Animator createBeforeUpdateItemAnimator(Chip chip, TimelineItem timelineitem, TimelineItem timelineitem1);
    }

    public static final class Entry
    {

        public final Chip chip;
        public final TimelineItem timelineItem;
        public final TimelineItem timelineItemCopy;

        public Entry(TimelineItem timelineitem, Chip chip1)
        {
            if (timelineitem == null)
            {
                throw new NullPointerException();
            }
            timelineItem = (TimelineItem)timelineitem;
            chip = chip1;
            timelineItemCopy = timelineitem.clone();
            if (!timelineitem.isIdentical(timelineItemCopy))
            {
                LogUtils.wtf(TimelineItemCollection.TAG, "Cloned item is not identical: %s => %s", new Object[] {
                    timelineitem, timelineItemCopy
                });
            }
        }
    }


    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timely/timeline/TimelineItemCollection);
    public final Iterable allChipsView = new .Lambda._cls1();
    public final AnimatorProvider animatorProvider;
    public final TreeMap entries;
    public final Map entriesByChip = new HashMap();
    public final LinkedHashMap persistentEntries = new LinkedHashMap();
    public final Collection persistentItemsView;

    public TimelineItemCollection(Comparator comparator, AnimatorProvider animatorprovider)
    {
        class .Lambda._cls0
            implements Function
        {

            public static final Function $instance = new .Lambda._cls0();

            public final Object apply(Object obj)
            {
                return TimelineItemCollection.lambda$new$0$TimelineItemCollection((Entry)obj);
            }


            private .Lambda._cls0()
            {
            }
        }

        persistentItemsView = Collections.unmodifiableCollection(new com.google.common.collect.Collections2.TransformedCollection(persistentEntries.values(), .Lambda._cls0..instance));
        animatorProvider = animatorprovider;
        entries = new TreeMap(comparator);
        class .Lambda._cls1
            implements Iterable
        {

            private final TimelineItemCollection arg$1;

            public final Iterator iterator()
            {
                Object obj = arg$1.entries.values().iterator();
                class .Lambda._cls2
                    implements Function
                {

                    public static final Function $instance = new .Lambda._cls2();

                    public final Object apply(Object obj2)
                    {
                        return TimelineItemCollection.lambda$new$1$TimelineItemCollection((Entry)obj2);
                    }


                        private .Lambda._cls2()
                        {
                        }
                }

                Object obj1 = .Lambda._cls2..instance;
                if (obj1 == null)
                {
                    throw new NullPointerException();
                }
                obj = new com.google.common.collect.Iterators._cls6(((Iterator) (obj)), ((Function) (obj1)));
                obj1 = com.google.common.base.Predicates.ObjectPredicate.NOT_NULL;
                if (obj == null)
                {
                    throw new NullPointerException();
                }
                if (obj1 == null)
                {
                    throw new NullPointerException();
                }
                obj = new com.google.common.collect.Iterators._cls5(((Iterator) (obj)), ((com.google.common.base.Predicate) (obj1)));
                if (obj == null)
                {
                    throw new NullPointerException();
                } else
                {
                    return (UnmodifiableIterator)obj;
                }
            }

            .Lambda._cls1()
            {
                arg$1 = TimelineItemCollection.this;
            }
        }

    }

    static final TimelineItem lambda$new$0$TimelineItemCollection(Entry entry)
    {
        return entry.timelineItem;
    }

    static final Chip lambda$new$1$TimelineItemCollection(Entry entry)
    {
        return entry.chip;
    }

    public final boolean arePersistentItemsIdentical(List list)
    {
        boolean flag1 = false;
        if (list != null) goto _L2; else goto _L1
_L1:
        boolean flag = persistentEntries.isEmpty();
_L4:
        return flag;
_L2:
        flag = flag1;
        if (persistentEntries.size() == list.size())
        {
            Iterator iterator = persistentEntries.values().iterator();
            int i = 0;
label0:
            do
            {
label1:
                {
                    if (i >= list.size())
                    {
                        break label1;
                    }
                    flag = flag1;
                    if (!((TimelineItem)list.get(i)).isIdentical(((Entry)iterator.next()).timelineItemCopy))
                    {
                        break label0;
                    }
                    i++;
                }
            } while (true);
        }
        if (true) goto _L4; else goto _L3
_L3:
        return true;
    }

    public final Object find(TimelineItemOperation timelineitemoperation)
    {
        for (Iterator iterator = entries.values().iterator(); iterator.hasNext();)
        {
            Object obj = ((Entry)iterator.next()).timelineItem.perform(timelineitemoperation, new Void[0]);
            if (obj != null)
            {
                return obj;
            }
        }

        return null;
    }

    public final Chip getChipForInstance(TimelineItem timelineitem)
    {
        Object obj = (Entry)persistentEntries.get(timelineitem);
        if (obj == null)
        {
            obj = null;
        } else
        {
            obj = ((Entry) (obj)).chip;
        }
        if (obj != null)
        {
            return ((Chip) (obj));
        }
        for (Iterator iterator = entries.values().iterator(); iterator.hasNext();)
        {
            Entry entry = (Entry)iterator.next();
            if (entry.timelineItem.isSameInstance(timelineitem))
            {
                return entry.chip;
            }
        }

        return null;
    }

    public final TimelineItem getItemForChip(Chip chip)
    {
        if (chip == null)
        {
            return null;
        }
        chip = (Entry)entriesByChip.get(chip);
        if (chip == null)
        {
            return null;
        } else
        {
            return ((Entry) (chip)).timelineItem;
        }
    }

    public final boolean include(TimelineItem timelineitem, Chip chip)
    {
        if (timelineitem == null || entries.containsKey(timelineitem))
        {
            return false;
        } else
        {
            Entry entry = new Entry(timelineitem, chip);
            entries.put(timelineitem, entry);
            entriesByChip.put(chip, entry);
            return true;
        }
    }

    public final void put(TimelineItem timelineitem, Chip chip)
    {
        Entry entry = new Entry(timelineitem, chip);
        persistentEntries.put(timelineitem, entry);
        entries.put(timelineitem, entry);
        entriesByChip.put(chip, entry);
    }

}
