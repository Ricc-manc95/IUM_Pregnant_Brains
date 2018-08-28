// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.schedule;

import com.google.android.apps.calendar.timeline.alternate.util.DimensConverter;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.view.api.ItemAdapter;
import com.google.android.apps.calendar.timeline.alternate.view.api.ScheduleProvider;
import com.google.android.apps.calendar.timeline.alternate.view.impl.LayoutDimens;
import com.google.android.apps.calendar.timeline.alternate.view.impl.TimelineHostView;
import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterDay;
import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterEvent;
import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterWeek;
import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.TimelineAdapter;
import com.google.android.apps.calendar.timeline.alternate.view.impl.viewtype.CalendarViewType;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.schedule:
//            ScheduleDay, ScheduleDayFactory, AutoValue_ScheduleItem

final class ScheduleCache
{

    private final TimelineAdapter adapter;
    public final Map cache = new HashMap();
    private final ObservableReference currentTime;
    private final ScheduleDayFactory factory;
    private Integer lastSelectedJulianDay;
    private final TimeUtils timeUtils;

    ScheduleCache(TimeUtils timeutils, ObservableReference observablereference, TimelineAdapter timelineadapter, ScheduleDayFactory scheduledayfactory)
    {
        timeUtils = timeutils;
        currentTime = observablereference;
        adapter = timelineadapter;
        factory = scheduledayfactory;
    }

    final ScheduleDay getDay(int i)
    {
        Object obj = (ScheduleDay)cache.get(Integer.valueOf(i));
        Object obj1 = adapter.getWeek(((2 - ((Integer)timeUtils.firstDayOfWeek.get()).intValue()) + i) / 7);
        if (obj != null && ((AdapterWeek) (obj1)).getCacheGeneration() == (long)((ScheduleDay) (obj)).getCacheGeneration())
        {
            return ((ScheduleDay) (obj));
        }
        obj1 = (AdapterDay)((AdapterWeek) (obj1)).getDays().get(((2 - ((Integer)timeUtils.firstDayOfWeek.get()).intValue()) + i) % 7);
        ScheduleDayFactory scheduledayfactory = factory;
        long l2 = ((Long)currentTime.get()).longValue();
        ArrayList arraylist;
        Object obj2;
        int j;
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        if (lastSelectedJulianDay != null && i == lastSelectedJulianDay.intValue())
        {
            j = 1;
        } else
        {
            j = 0;
        }
        arraylist = new ArrayList();
        if (((AdapterDay) (obj1)).getTimedEvents().isEmpty() && ((AdapterDay) (obj1)).getAllDayEvents().isEmpty())
        {
            obj = Collections.emptyList();
        } else
        {
            obj = new ArrayList(((AdapterDay) (obj1)).getAllDayEvents().size() + ((AdapterDay) (obj1)).getTimedEvents().size());
            ((List) (obj)).addAll(((AdapterDay) (obj1)).getAllDayEvents());
            obj2 = (ImmutableList)((AdapterDay) (obj1)).getTimedEvents();
            i1 = ((ImmutableList) (obj2)).size();
            k = 0;
            UnmodifiableIterator unmodifiableiterator = (UnmodifiableIterator)null;
            do
            {
                if (k >= i1)
                {
                    break;
                }
                Object obj3 = ((ImmutableList) (obj2)).get(k);
                l = k + 1;
                obj3 = (AdapterEvent)obj3;
                k = l;
                if (scheduledayfactory.itemAdapter.getStartDay(((AdapterEvent) (obj3)).getItem()) == ((AdapterEvent) (obj3)).getJulianDay())
                {
                    ((List) (obj)).add(obj3);
                    k = l;
                }
            } while (true);
            Collections.sort(((List) (obj)), new com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.EventComparators..Lambda._cls0(new com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.EventComparators..Lambda._cls1(scheduledayfactory.itemAdapter)));
        }
        k = 0;
        if (i == scheduledayfactory.timeUtils.msToJulianDate(l2))
        {
            i1 = 1;
        } else
        {
            i1 = 0;
        }
        if (scheduledayfactory.timeUtils.getCalendarFieldForJulianDay(i, 5) == 1)
        {
            l1 = 1;
        } else
        {
            l1 = 0;
        }
        if (((2 - ((Integer)scheduledayfactory.timeUtils.firstDayOfWeek.get()).intValue()) + i) % 7 == 0)
        {
            i2 = 1;
        } else
        {
            i2 = 0;
        }
        if (!((List) (obj)).isEmpty())
        {
            j1 = 1;
        } else
        {
            j1 = 0;
        }
        if (j1 != 0 || i1 != 0 || j != 0)
        {
            l = 1;
        } else
        {
            l = 0;
        }
        if (j1 == 0 && (i1 != 0 || j != 0))
        {
            k1 = 1;
        } else
        {
            k1 = 0;
        }
        if (l1 != 0)
        {
            arraylist.add(new AutoValue_ScheduleItem(CalendarViewType.MONTH_BANNER.minPosition + i, 0, 0, scheduledayfactory.hostView.getMeasuredWidth(), scheduledayfactory.monthBannerHeight + 0, null, null, false));
            k = scheduledayfactory.monthBannerHeight + 0 + scheduledayfactory.defaultMargin;
        }
        j = k;
        if (i2 != 0)
        {
            int j2;
            if (((Boolean)scheduledayfactory.isRtl.get()).booleanValue())
            {
                j = 0;
            } else
            {
                j = scheduledayfactory.chipsStartMargin;
            }
            if (((Boolean)scheduledayfactory.isRtl.get()).booleanValue())
            {
                l1 = scheduledayfactory.hostView.getMeasuredWidth() - scheduledayfactory.chipsStartMargin;
            } else
            {
                l1 = scheduledayfactory.hostView.getMeasuredWidth();
            }
            arraylist.add(new AutoValue_ScheduleItem(CalendarViewType.WEEK_BANNER.minPosition + i, j, k, l1, k + scheduledayfactory.weekBannerHeight, null, null, false));
            j = k + scheduledayfactory.weekBannerHeight;
        }
        if (l != 0)
        {
            l1 = ((AdapterDay) (obj1)).getJulianDay();
            i2 = CalendarViewType.DAY_HEADER.minPosition;
            if (((Boolean)scheduledayfactory.isRtl.get()).booleanValue())
            {
                k = scheduledayfactory.hostView.getMeasuredWidth() - scheduledayfactory.chipsStartMargin;
            } else
            {
                k = scheduledayfactory.dayHeaderStartMargin;
            }
            if (((Boolean)scheduledayfactory.isRtl.get()).booleanValue())
            {
                l = scheduledayfactory.hostView.getMeasuredWidth() - scheduledayfactory.dayHeaderStartMargin;
            } else
            {
                l = scheduledayfactory.chipsStartMargin;
            }
            arraylist.add(new AutoValue_ScheduleItem(l1 + i2 + 100, k, j, l, j + Math.round(scheduledayfactory.layoutDimens.converter.dpToPx(60F)), null, null, false));
            l = Math.round(scheduledayfactory.layoutDimens.converter.dpToPx(60F)) + j;
        } else
        {
            l = j;
        }
        if (k1 != 0)
        {
            if (((Boolean)scheduledayfactory.isRtl.get()).booleanValue())
            {
                k = 0;
            } else
            {
                k = scheduledayfactory.chipsStartMargin;
            }
            if (((Boolean)scheduledayfactory.isRtl.get()).booleanValue())
            {
                k1 = scheduledayfactory.hostView.getMeasuredWidth() - scheduledayfactory.chipsStartMargin;
            } else
            {
                k1 = scheduledayfactory.hostView.getMeasuredWidth();
            }
            arraylist.add(new AutoValue_ScheduleItem(CalendarViewType.NOTHING_PLANNED_BANNER.minPosition + i, k, j, k1, j + scheduledayfactory.nothingPlannedBannerHeight, null, null, false));
            k = scheduledayfactory.nothingPlannedBannerHeight;
            k = scheduledayfactory.dayBottomMargin + (k + j);
        } else
        {
            k = j;
        }
        j = k;
        if (j1 == 0)
        {
            break MISSING_BLOCK_LABEL_1503;
        }
        if (i1 == 0) goto _L2; else goto _L1
_L1:
        j = ((List) (obj)).size() - 1;
_L4:
        if (j >= 0)
        {
            obj2 = (AdapterEvent)((List) (obj)).get(j);
            if (scheduledayfactory.itemAdapter.isAllDay(((AdapterEvent) (obj2)).getItem()))
            {
                j++;
            } else
            {
label0:
                {
                    if (l2 - ((AdapterEvent) (obj2)).getStartTimeMs() > ((AdapterEvent) (obj2)).getEndTimeMs() - l2)
                    {
                        i1 = 1;
                    } else
                    {
                        i1 = 0;
                    }
                    if (i1 == 0)
                    {
                        break label0;
                    }
                    j++;
                }
            }
        } else
        {
            j = 0;
        }
          goto _L3
        j--;
          goto _L4
_L2:
        j = -1;
_L3:
        i1 = 0;
        do
        {
            if (i1 >= ((List) (obj)).size())
            {
                break;
            }
            if (j == i1)
            {
                if (i1 == 0)
                {
                    j1 = 0;
                } else
                {
                    j1 = scheduledayfactory.nowLineTopPadding;
                }
                k += j1;
                k = k + scheduledayfactory.addNowLine(arraylist, k) + scheduledayfactory.nowLineBottomPadding;
            } else
            if (i1 > 0)
            {
                k += scheduledayfactory.chipVerticalSpacing;
            }
            obj2 = (AdapterEvent)((List) (obj)).get(i1);
            l1 = scheduledayfactory.scheduleProvider.getItemHeight(((AdapterEvent) (obj2)).getItem(), ((AdapterEvent) (obj2)).getJulianDay());
            i2 = ((AdapterEvent) (obj2)).getPosition();
            if (((Boolean)scheduledayfactory.isRtl.get()).booleanValue())
            {
                j1 = scheduledayfactory.chipEndMargin;
            } else
            {
                j1 = scheduledayfactory.chipsStartMargin;
            }
            j2 = scheduledayfactory.hostView.getMeasuredWidth();
            if (((Boolean)scheduledayfactory.isRtl.get()).booleanValue())
            {
                k1 = scheduledayfactory.chipsStartMargin;
            } else
            {
                k1 = scheduledayfactory.chipEndMargin;
            }
            arraylist.add(new AutoValue_ScheduleItem(i2, j1, k, j2 - k1, k + l1, Long.valueOf(((AdapterEvent) (obj2)).getStartTimeMs()), Long.valueOf(((AdapterEvent) (obj2)).getEndTimeMs()), ((AdapterEvent) (obj2)).getIsTimedEvent()));
            i1++;
            k += l1;
        } while (true);
        i1 = k;
        if (j == ((List) (obj)).size())
        {
            j = k + scheduledayfactory.nowLineTopPadding;
            i1 = j + scheduledayfactory.addNowLine(arraylist, j);
        }
        j = scheduledayfactory.dayBottomMargin + i1;
        obj = (new AutoValue_ScheduleDay.Builder()).setLoaded(((AdapterDay) (obj1)).getLoaded()).setCacheGeneration(((AdapterDay) (obj1)).getCacheGeneration()).setHeightPx(Math.max(j, l)).setLayout(arraylist).build();
        cache.put(Integer.valueOf(i), obj);
        return ((ScheduleDay) (obj));
        if (true) goto _L3; else goto _L5
_L5:
    }

    public final void setSelectedDay(Integer integer)
    {
        Integer integer1 = lastSelectedJulianDay;
        boolean flag;
        if (integer1 == integer || integer1 != null && integer1.equals(integer))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            cache.clear();
            lastSelectedJulianDay = integer;
        }
    }
}
