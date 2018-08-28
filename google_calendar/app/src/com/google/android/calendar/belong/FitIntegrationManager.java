// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.belong;

import android.accounts.Account;
import android.content.Context;
import android.text.TextUtils;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.timebox.GoalItem;
import com.google.android.apps.calendar.timebox.TimeRange;
import com.google.android.apps.calendar.timebox.TimeRangeEntry;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.concurrent.CalendarFutures;
import com.google.android.calendar.Utils;
import com.google.android.calendar.alerts.HabitsAlerts;
import com.google.android.calendar.alerts.HabitsNotificationManager;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.common.Feature;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventDescriptor;
import com.google.android.calendar.api.habit.Habit;
import com.google.android.calendar.api.habit.HabitDescriptor;
import com.google.android.calendar.api.habit.HabitInstance;
import com.google.android.calendar.api.habit.HabitReminders;
import com.google.android.gms.fitness.FitnessActivities;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.data.Value;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.UncheckedExecutionException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.calendar.belong:
//            BelongUtils, FitHabitsApiManager, FitApiManager

public class FitIntegrationManager
{

    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/belong/FitIntegrationManager);
    public final Account account;
    public final Context context;
    public final FitApiManager fitApiManager;
    public final FitHabitsApiManager habitApiManager;
    public final long timeMillis;

    FitIntegrationManager(Context context1, Account account1, FitApiManager fitapimanager, FitHabitsApiManager fithabitsapimanager, long l)
    {
        context = context1.getApplicationContext();
        account = account1;
        fitApiManager = fitapimanager;
        habitApiManager = fithabitsapimanager;
        timeMillis = l;
    }

    final void activityCheckForSegment(int i, DataPoint datapoint, List list, Set set, List list1)
    {
        Object obj;
        long l;
        long l1;
        obj = Field.FIELD_ACTIVITY;
        DataType datatype = datapoint.zzbhl.zzbhk;
        int j = datatype.zzbhT.indexOf(obj);
        if (j < 0)
        {
            throw new IllegalArgumentException(String.format("%s not a field of %s", new Object[] {
                obj, datatype
            }));
        }
        obj = datapoint.zzbhF[j];
        boolean flag;
        if (((Value) (obj)).format == 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("Value is not in int format"));
        }
        obj = FitnessActivities.getName(Float.floatToRawIntBits(((Value) (obj)).value));
        if (TextUtils.isEmpty(((CharSequence) (obj))) || !set.contains(obj))
        {
            datapoint = Features.instance;
            if (datapoint == null)
            {
                throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
            } else
            {
                ((FeatureConfig)datapoint).dogfood_features();
                return;
            }
        }
        l1 = TimeUnit.MILLISECONDS.convert(datapoint.zzbhE, TimeUnit.NANOSECONDS);
        l = TimeUnit.MILLISECONDS.convert(datapoint.zzbhD, TimeUnit.NANOSECONDS) - l1;
        if (BelongUtils.isAutoTrackingSupported(i) && (float)l < 900000F)
        {
            datapoint = Features.instance;
            if (datapoint == null)
            {
                throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
            } else
            {
                ((FeatureConfig)datapoint).dogfood_features();
                return;
            }
        }
        obj = Collections.emptyList();
        long l2;
        long l3;
        try
        {
            datapoint = (List)Futures.getUnchecked(CalendarFutures.mapFold(list, new FitHabitsApiManager..Lambda._cls0(habitApiManager, l1), new ArrayList(), FitHabitsApiManager..Lambda._cls1.$instance, new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.BACKGROUND)));
        }
        // Misplaced declaration of an exception variable
        catch (DataPoint datapoint)
        {
            LogUtils.e(TAG, datapoint, "Failed to load habit instances.", new Object[0]);
            continue; /* Loop/switch isn't completed */
        }
        obj = datapoint;
_L19:
        datapoint = Calendar.getInstance(Utils.getTimeZone(context));
        datapoint.setTimeInMillis(l1);
        datapoint.set(11, 0);
        datapoint.set(12, 0);
        datapoint.set(13, 0);
        datapoint.set(14, 0);
        l2 = datapoint.getTimeInMillis();
        datapoint.add(5, 1);
        l3 = datapoint.getTimeInMillis();
        datapoint = ((List) (obj)).iterator();
        do
        {
            if (!datapoint.hasNext())
            {
                break MISSING_BLOCK_LABEL_487;
            }
            set = (TimeRangeEntry)datapoint.next();
        } while (!((GoalItem)set.getValue()).getGoal().isDone() || set.getRange().getUtcStartMillis() < l2 || set.getRange().getUtcEndMillis() >= l3);
        i = 1;
_L1:
        if (i != 0)
        {
            datapoint = Features.instance;
            if (datapoint == null)
            {
                throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
            } else
            {
                ((FeatureConfig)datapoint).dogfood_features();
                return;
            }
        }
        break MISSING_BLOCK_LABEL_503;
        i = 0;
          goto _L1
        if (!((List) (obj)).isEmpty()) goto _L3; else goto _L2
_L2:
        set = null;
_L5:
        l1 = BelongUtils.roundTime(context, l1);
        if (set != null)
        {
            break MISSING_BLOCK_LABEL_762;
        }
        datapoint = habitApiManager.createNewInstanceImpl((Habit)list.get(0), l1);
        BelongUtils.log(context, "activity_detected", "created", Long.valueOf(l / 60000L));
        set = ((Habit)list.get(0)).getDescriptor().habitId;
        set = Features.instance;
        if (set == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        break MISSING_BLOCK_LABEL_726;
_L3:
        datapoint = null;
        i = 0;
_L10:
        set = datapoint;
        if (i >= ((List) (obj)).size()) goto _L5; else goto _L4
_L4:
        Object obj1 = (TimeRangeEntry)((List) (obj)).get(i);
        if (((GoalItem)((TimeRangeEntry) (obj1)).getValue()).getGoal().isDone()) goto _L7; else goto _L6
_L6:
        if (datapoint != null) goto _L9; else goto _L8
_L8:
        set = ((Set) (obj1));
_L11:
        i++;
        datapoint = set;
          goto _L10
_L9:
        set = ((Set) (obj1));
        if (Math.abs(l1 - ((TimeRangeEntry) (obj1)).getRange().getUtcStartMillis()) < Math.abs(l1 - datapoint.getRange().getUtcStartMillis())) goto _L11; else goto _L7
_L7:
        set = datapoint;
          goto _L11
        ((FeatureConfig)set).dogfood_features();
_L12:
        if (datapoint == null)
        {
            datapoint = Features.instance;
            if (datapoint == null)
            {
                throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
            } else
            {
                ((FeatureConfig)datapoint).dogfood_features();
                return;
            }
        }
        break MISSING_BLOCK_LABEL_872;
        datapoint = habitApiManager.moveAndMarkAsDone(((GoalItem)set.getValue()).getEventDescriptor(), l1);
        BelongUtils.log(context, "activity_detected", "moved", Long.valueOf(l / 60000L));
        ((GoalItem)set.getValue()).getGoal().getHabitId();
        set = Features.instance;
        if (set == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)set).dogfood_features();
          goto _L12
        set = datapoint.getDescriptor().getKey();
        list1.add(set);
        list1 = list.iterator();
_L16:
        if (!list1.hasNext())
        {
            break MISSING_BLOCK_LABEL_1079;
        }
        list = (Habit)list1.next();
        obj1 = list.getDescriptor();
        obj = ((HabitInstance)datapoint.getHabitInstance().getValue()).getHabitParentDescriptor();
        if (obj == null)
        {
            throw new NullPointerException();
        }
        if (((HabitDescriptor) (obj)).calendar == null || !((HabitDescriptor) (obj1)).calendar.matches(((HabitDescriptor) (obj)).calendar)) goto _L14; else goto _L13
_L13:
        obj1 = ((HabitDescriptor) (obj1)).habitId;
        obj = ((HabitDescriptor) (obj)).habitId;
        if (obj1 == obj || obj1 != null && obj1.equals(obj))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0) goto _L14; else goto _L15
_L15:
        i = 1;
_L17:
        if (i != 0)
        {
            if (!list.getReminders().enableFollowup)
            {
                datapoint = Features.instance;
                if (datapoint == null)
                {
                    throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
                } else
                {
                    ((FeatureConfig)datapoint).dogfood_features();
                    return;
                }
            }
            break MISSING_BLOCK_LABEL_1101;
        }
        if (true) goto _L16; else goto _L14
_L14:
        i = 0;
          goto _L17
        throw new IllegalStateException("Habit not found");
        datapoint = Features.instance;
        if (datapoint == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)datapoint).dogfood_features();
        HabitsNotificationManager.markPastNotificationsAsDismissed(list.getDescriptor().habitId, Long.valueOf(timeMillis));
        datapoint = context;
        if (HabitsAlerts.areNewHabitNotificationsEnabled())
        {
            LogUtils.i(HabitsAlerts.TAG, "Showing belong notification is ignored.", new Object[0]);
        } else
        {
            HabitsAlerts.showBelongNotification(datapoint, list, set, null, list.getDescriptor().habitId.hashCode());
        }
        BelongUtils.log(context, "detected_notification", "shown");
        return;
        if (true) goto _L19; else goto _L18
_L18:
    }

}
