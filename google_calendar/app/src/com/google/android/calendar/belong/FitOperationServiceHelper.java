// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.belong;

import android.accounts.Account;
import android.app.backup.BackupManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.SparseArray;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.calendar.Utils;
import com.google.android.calendar.alerts.HabitsIntentServiceHelper;
import com.google.android.calendar.api.event.EventKey;
import com.google.android.calendar.api.habit.Habit;
import com.google.android.calendar.api.habit.HabitClient;
import com.google.android.calendar.api.habit.HabitFilterOptions;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.utils.account.AccountsUtil;
import com.google.android.calendar.utils.belong.FitIntegrationConstants;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.HistoryApi;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.result.DataReadResult;
import com.google.common.base.Optional;
import com.google.common.collect.FluentIterable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.calendar.belong:
//            FitApiManager, FitHabitsApiManager, FitIntegrationManager, BelongUtils

public class FitOperationServiceHelper
{

    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/belong/FitOperationServiceHelper);
    public final Context context;

    public FitOperationServiceHelper(Context context1)
    {
        context = context1.getApplicationContext();
    }

    static final boolean lambda$performActivityCheck$0$FitOperationServiceHelper(EventKey eventkey, EventKey eventkey1)
    {
        return EventKey.COMPARATOR.compare(eventkey, eventkey1) == 0;
    }

    public final boolean performActivityCheck(int i, EventKey eventkey, boolean flag)
    {
        Account aaccount[];
        ArrayList arraylist;
        int j;
        int j1;
        aaccount = AccountsUtil.getGoogleAccounts(context);
        arraylist = new ArrayList();
        j1 = aaccount.length;
        j = 0;
_L2:
        Object obj8;
        if (j >= j1)
        {
            break MISSING_BLOCK_LABEL_1495;
        }
        Object obj = aaccount[j];
        Context context1 = context;
        FitApiManager fitapimanager = new FitApiManager(context1, ((Account) (obj)));
        obj8 = new FitHabitsApiManager(context1, ((Account) (obj)));
        long l1;
        if (Clock.mockedTimestamp > 0L)
        {
            l1 = Clock.mockedTimestamp;
        } else
        {
            l1 = System.currentTimeMillis();
        }
        obj8 = new FitIntegrationManager(context1, ((Account) (obj)), fitapimanager, ((FitHabitsApiManager) (obj8)), l1);
        obj = ((FitIntegrationManager) (obj8)).account.name;
        obj = Features.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)obj).dogfood_features();
        if (((FitIntegrationManager) (obj8)).habitApiManager.hasHabitsWithEnabledIntegration(((FitIntegrationManager) (obj8)).timeMillis))
        {
            break; /* Loop/switch isn't completed */
        }
        obj = Features.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)obj).dogfood_features();
_L3:
        j++;
        if (true) goto _L2; else goto _L1
_L1:
        long l2;
        long l4;
        if (!((FitIntegrationManager) (obj8)).fitApiManager.connect())
        {
            LogUtils.e(FitIntegrationManager.TAG, "Failed to connect to GoogleApiClient", new Object[0]);
        } else
        {
label0:
            {
                l4 = ((FitIntegrationManager) (obj8)).timeMillis;
                Object obj1 = ((FitIntegrationManager) (obj8)).context;
                String s = Utils.sharedPrefKeyForAccount(((FitIntegrationManager) (obj8)).account.name, "fit_last_activity_check");
                long l5 = ((Context) (obj1)).getSharedPreferences("com.google.android.calendar_preferences", 0).getLong(s, 0L);
                l2 = ((FitIntegrationManager) (obj8)).timeMillis;
                obj1 = ((FitIntegrationManager) (obj8)).context;
                s = Utils.sharedPrefKeyForAccount(((FitIntegrationManager) (obj8)).account.name, "fit_last_activity_check");
                ((Context) (obj1)).getSharedPreferences("com.google.android.calendar_preferences", 0).edit().putLong(s, l2).apply();
                (new BackupManager(((Context) (obj1)))).dataChanged();
                l2 = ((FitIntegrationManager) (obj8)).timeMillis;
                l4 = Math.min((l4 - l5) + 0x5265c00L, 0x240c8400L);
                if (l4 > 0L)
                {
                    break label0;
                }
                obj1 = Features.instance;
                if (obj1 == null)
                {
                    throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
                }
                ((FeatureConfig)obj1).dogfood_features();
            }
        }
          goto _L3
        Object obj2;
        l4 = l2 - l4;
        obj2 = ((FitIntegrationManager) (obj8)).fitApiManager;
        if (l4 >= l2)
        {
            obj2 = null;
        } else
        {
label1:
            {
                Object obj4 = new com.google.android.gms.fitness.request.DataReadRequest.Builder();
                Object obj6 = FitApiManager.DATA_SOURCE;
                if (obj6 == null)
                {
                    throw new NullPointerException(String.valueOf("Attempting to add a null data source"));
                }
                boolean flag1;
                if (!((com.google.android.gms.fitness.request.DataReadRequest.Builder) (obj4)).zzbka.contains(obj6))
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                if (!flag1)
                {
                    throw new IllegalArgumentException(String.valueOf("Cannot add the same data source as aggregated and detailed"));
                }
                if (!((com.google.android.gms.fitness.request.DataReadRequest.Builder) (obj4)).zzbjU.contains(obj6))
                {
                    ((com.google.android.gms.fitness.request.DataReadRequest.Builder) (obj4)).zzbjU.add(obj6);
                }
                obj6 = TimeUnit.MILLISECONDS;
                obj4.zzadP = ((TimeUnit) (obj6)).toMillis(l4);
                obj4.zzbhw = ((TimeUnit) (obj6)).toMillis(l2);
                if (!((com.google.android.gms.fitness.request.DataReadRequest.Builder) (obj4)).zzbjU.isEmpty() || !((com.google.android.gms.fitness.request.DataReadRequest.Builder) (obj4)).zzbhv.isEmpty() || !((com.google.android.gms.fitness.request.DataReadRequest.Builder) (obj4)).zzbka.isEmpty() || !((com.google.android.gms.fitness.request.DataReadRequest.Builder) (obj4)).zzbjZ.isEmpty())
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                if (!flag1)
                {
                    throw new IllegalStateException(String.valueOf("Must add at least one data source (aggregated or detailed)"));
                }
                if (((com.google.android.gms.fitness.request.DataReadRequest.Builder) (obj4)).zzadP > 0L)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                l2 = ((com.google.android.gms.fitness.request.DataReadRequest.Builder) (obj4)).zzadP;
                if (!flag1)
                {
                    throw new IllegalStateException(String.format("Invalid start time: %s", new Object[] {
                        Long.valueOf(l2)
                    }));
                }
                if (((com.google.android.gms.fitness.request.DataReadRequest.Builder) (obj4)).zzbhw > 0L && ((com.google.android.gms.fitness.request.DataReadRequest.Builder) (obj4)).zzbhw > ((com.google.android.gms.fitness.request.DataReadRequest.Builder) (obj4)).zzadP)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                l2 = ((com.google.android.gms.fitness.request.DataReadRequest.Builder) (obj4)).zzbhw;
                if (!flag1)
                {
                    throw new IllegalStateException(String.format("Invalid end time: %s", new Object[] {
                        Long.valueOf(l2)
                    }));
                }
                if (((com.google.android.gms.fitness.request.DataReadRequest.Builder) (obj4)).zzbka.isEmpty() && ((com.google.android.gms.fitness.request.DataReadRequest.Builder) (obj4)).zzbjZ.isEmpty())
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                if (!flag1)
                {
                    flag1 = false;
                } else
                {
                    flag1 = true;
                }
                if (!flag1)
                {
                    throw new IllegalStateException(String.valueOf("Must specify a valid bucketing strategy while requesting aggregation"));
                }
                obj4 = new DataReadRequest(((com.google.android.gms.fitness.request.DataReadRequest.Builder) (obj4)));
                obj2 = (DataReadResult)Fitness.HistoryApi.readData(((FitApiManager) (obj2)).client, ((DataReadRequest) (obj4))).await();
                if (((DataReadResult) (obj2)).zzahG.zzaEP <= 0)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                if (flag1)
                {
                    break label1;
                }
                obj2 = null;
            }
        }
_L4:
        DataSource datasource;
        Iterator iterator;
        if (obj2 == null)
        {
            LogUtils.e(FitIntegrationManager.TAG, "Fit API call failed.", new Object[0]);
        } else
        {
            Object obj7 = ((FitIntegrationManager) (obj8)).habitApiManager;
            long l3 = ((FitIntegrationManager) (obj8)).timeMillis;
            Object obj5 = ((FitHabitsApiManager) (obj7)).habitClient;
            obj7 = (new HabitFilterOptions(((FitHabitsApiManager) (obj7)).account.name)).setFitIntegrationStatus(Integer.valueOf(20));
            obj7.activeAfterFilter = Long.valueOf(l3);
            Habit ahabit[] = ((com.google.android.calendar.api.habit.HabitClient.ListResult)((HabitClient) (obj5)).list(((HabitFilterOptions) (obj7))).await()).getHabits();
            SparseArray sparsearray = new SparseArray();
            int i1 = ahabit.length;
            for (int k = 0; k < i1; k++)
            {
                Habit habit = ahabit[k];
                List list = (List)sparsearray.get(habit.getType());
                obj5 = list;
                if (list == null)
                {
                    obj5 = new ArrayList();
                    sparsearray.put(habit.getType(), obj5);
                }
                ((List) (obj5)).add(habit);
            }

            sparsearray.size();
            obj5 = Features.instance;
            if (obj5 == null)
            {
                throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
            }
            ((FeatureConfig)obj5).dogfood_features();
            int l = 0;
            while (l < sparsearray.size()) 
            {
                int k1 = sparsearray.keyAt(l);
                obj5 = (List)sparsearray.get(k1);
                Set set = (Set)FitIntegrationConstants.HABIT_TYPE_TO_FIT_ACTIVITY_MAP.get(k1);
                boolean flag2;
                if (set == null || set.isEmpty())
                {
                    flag2 = true;
                } else
                {
                    flag2 = false;
                }
                if (flag2)
                {
                    obj5 = Features.instance;
                    if (obj5 == null)
                    {
                        throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
                    }
                    ((FeatureConfig)obj5).dogfood_features();
                } else
                {
                    ((List) (obj2)).size();
                    Object obj9 = Features.instance;
                    if (obj9 == null)
                    {
                        throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
                    }
                    ((FeatureConfig)obj9).dogfood_features();
                    obj9 = ((List) (obj2)).iterator();
                    while (((Iterator) (obj9)).hasNext()) 
                    {
                        ((FitIntegrationManager) (obj8)).activityCheckForSegment(k1, (DataPoint)((Iterator) (obj9)).next(), ((List) (obj5)), set, arraylist);
                    }
                }
                l++;
            }
            obj5 = ((FitIntegrationManager) (obj8)).context;
            if (i == 1)
            {
                obj2 = "follow_up";
            } else
            {
                obj2 = "on_start";
            }
            BelongUtils.log(((Context) (obj5)), "activity_check", ((String) (obj2)), Long.valueOf(ahabit.length));
        }
          goto _L3
        datasource = FitApiManager.DATA_SOURCE;
        iterator = ((DataReadResult) (obj2)).zzbhy.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break MISSING_BLOCK_LABEL_1004;
            }
            obj2 = (DataSet)iterator.next();
        } while (!datasource.equals(((DataSet) (obj2)).zzbhl));
_L5:
        if (obj2 == null)
        {
            obj2 = null;
        } else
        {
            obj2 = Collections.unmodifiableList(((DataSet) (obj2)).zzbhJ);
        }
          goto _L4
        obj2 = DataSet.create(datasource);
          goto _L5
        if (flag && (eventkey instanceof com.google.android.calendar.api.event.EventKey.Persisted))
        {
            context.sendBroadcast(HabitsIntentServiceHelper.createPostBelongCheckNotificationIntent(context, eventkey));
        }
        class .Lambda._cls0
            implements Predicate
        {

            private final EventKey arg$1;

            public final boolean apply(Object obj10)
            {
                return FitOperationServiceHelper.lambda$performActivityCheck$0$FitOperationServiceHelper(arg$1, (EventKey)obj10);
            }

            .Lambda._cls0(EventKey eventkey)
            {
                arg$1 = eventkey;
            }
        }

        Object obj3;
        if (arraylist instanceof FluentIterable)
        {
            obj3 = (FluentIterable)arraylist;
        } else
        {
            obj3 = new com.google.common.collect.FluentIterable._cls1(arraylist, arraylist);
        }
        eventkey = new .Lambda._cls0(eventkey);
        obj3 = (Iterable)((FluentIterable) (obj3)).iterableDelegate.or(obj3);
        if (obj3 == null)
        {
            throw new NullPointerException();
        }
        if (eventkey == null)
        {
            throw new NullPointerException();
        }
        eventkey = new com.google.common.collect.Iterables._cls4(((Iterable) (obj3)), eventkey);
        if (eventkey instanceof FluentIterable)
        {
            eventkey = (FluentIterable)eventkey;
        } else
        {
            eventkey = new com.google.common.collect.FluentIterable._cls1(eventkey, eventkey);
        }
        if (!((Iterable)((FluentIterable) (eventkey)).iterableDelegate.or(eventkey)).iterator().hasNext())
        {
            i = 1;
        } else
        {
            i = 0;
        }
        return i == 0;
    }

}
