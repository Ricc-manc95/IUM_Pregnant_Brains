// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.habit;

import android.accounts.Account;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Entity;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.timely.store.GrooveStore;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.color.EventColor;
import com.google.android.calendar.utils.habit.HabitInstancesUtil;
import com.google.common.base.Platform;
import com.google.common.collect.Iterators;
import com.google.common.primitives.Ints;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.calendar.api.habit:
//            HabitModifications, HabitContractModifications, HabitContract, HabitDescriptor, 
//            HabitIdTypeUtil, HabitStoreUtils, Habit, HabitFilterOptions, 
//            FitIntegrationStore

public class HabitApiStoreImpl
{

    public HabitApiStoreImpl()
    {
    }

    static void propagateChangesToInstances(HabitModifications habitmodifications)
    {
        GrooveStore groovestore2;
        HabitDescriptor habitdescriptor;
        ContentValues contentvalues;
        boolean flag;
        if (habitmodifications.getContractModifications().isUntilMillisUtcModified() && habitmodifications.getContractModifications().getUntilMillisUtc() > 0L)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            GrooveStore groovestore = GrooveStore.store;
            if (groovestore == null)
            {
                throw new NullPointerException(String.valueOf("Not initialized"));
            }
            groovestore = (GrooveStore)groovestore;
            Object obj1 = habitmodifications.getDescriptor();
            long l = habitmodifications.getContract().getUntilMillisUtc();
            String s = (new StringBuilder(String.valueOf("(sync_data8=? OR sync_data8 LIKE ?)").length() + 15)).append("(sync_data8=? OR sync_data8 LIKE ?)").append(" AND dtstart").append(">=?").toString();
            String as1[] = HabitInstancesUtil.getSelectionArgs(((HabitDescriptor) (obj1)).habitId);
            String as[] = new String[as1.length + 1];
            int i;
            for (i = 0; i < as1.length; i++)
            {
                as[i] = as1[i];
            }

            as[i] = String.valueOf(l);
            obj1 = GrooveStore.createInstanceContentUri(((HabitDescriptor) (obj1)));
            i = groovestore.context.getContentResolver().delete(((android.net.Uri) (obj1)), s, as);
            if (i > 0)
            {
                GrooveStore.forceNotifyChange(groovestore.context, ((android.net.Uri) (obj1)));
                LogUtils.v(GrooveStore.TAG, "Locally deleted %d habit instances", new Object[] {
                    Integer.valueOf(i)
                });
            }
        }
        GrooveStore groovestore1 = GrooveStore.store;
        if (groovestore1 == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        }
        groovestore2 = (GrooveStore)groovestore1;
        habitdescriptor = habitmodifications.getDescriptor();
        contentvalues = new ContentValues();
        if (habitmodifications.isSummaryModified())
        {
            contentvalues.put("title", habitmodifications.getSummary());
        }
        if (!habitmodifications.isVisibilityModified()) goto _L2; else goto _L1
_L1:
        habitmodifications.getVisibility();
        JVM INSTR tableswitch 0 2: default 348
    //                   0 507
    //                   1 519
    //                   2 513;
           goto _L3 _L4 _L5 _L6
_L3:
        int j = 0;
_L14:
        contentvalues.put("accessLevel", Integer.valueOf(j));
_L2:
        if (!habitmodifications.isColorOverrideModified()) goto _L8; else goto _L7
_L7:
        Object obj = habitmodifications.getColorOverride();
        if (obj != null) goto _L10; else goto _L9
_L9:
        contentvalues.put("eventColor", null);
        obj = null;
_L12:
        contentvalues.put("eventColor_index", ((String) (obj)));
_L8:
        if (habitmodifications.isTypeModified())
        {
            contentvalues.put("sync_data8", HabitIdTypeUtil.createHabitIdTypeStringFromApiType(habitmodifications.getDescriptor().habitId, habitmodifications.getType()));
        }
        if (contentvalues.size() > 0)
        {
            habitmodifications = GrooveStore.createInstanceContentUri(habitdescriptor);
            j = groovestore2.context.getContentResolver().update(habitmodifications, contentvalues, "(sync_data8=? OR sync_data8 LIKE ?)", HabitInstancesUtil.getSelectionArgs(habitdescriptor.habitId));
            if (j > 0)
            {
                GrooveStore.forceNotifyChange(groovestore2.context, habitmodifications);
                LogUtils.v(GrooveStore.TAG, "Locally propagated changes to %d habit instances", new Object[] {
                    Integer.valueOf(j)
                });
            }
        }
        return;
_L4:
        j = 0;
        continue; /* Loop/switch isn't completed */
_L6:
        j = 2;
        continue; /* Loop/switch isn't completed */
_L5:
        j = 3;
        continue; /* Loop/switch isn't completed */
_L10:
        obj = ((EventColor) (obj)).getKey();
        boolean flag1;
        if (!Platform.stringIsNullOrEmpty(((String) (obj))))
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1) goto _L12; else goto _L11
_L11:
        throw new IllegalStateException(String.valueOf("Null or empty color key in color descriptor"));
        if (true) goto _L14; else goto _L13
_L13:
    }

    public static Habit[] read(HabitDescriptor ahabitdescriptor[])
        throws IOException
    {
        ArrayList arraylist = new ArrayList();
        GrooveStore groovestore = GrooveStore.store;
        if (groovestore == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        }
        for (ahabitdescriptor = ((GrooveStore)groovestore).getHabits(ahabitdescriptor).iterator(); ahabitdescriptor.hasNext(); arraylist.add(HabitStoreUtils.entityToHabit((Entity)ahabitdescriptor.next()))) { }
        return (Habit[])arraylist.toArray(new Habit[arraylist.size()]);
    }

    private static void requestSyncUp(HabitDescriptor habitdescriptor, int i)
    {
        Bundle bundle = new Bundle(8);
        bundle.putBoolean("upload", true);
        bundle.putString("feed_internal", habitdescriptor.calendar.calendarId);
        bundle.putInt("groove_operation", i);
        bundle.putBoolean("only_groove", true);
        bundle.putString("upsync_tracking_id", habitdescriptor.habitId);
        bundle.putBoolean("ignore_settings", true);
        bundle.putBoolean("expedited", true);
        bundle.putBoolean("ignore_backoff", true);
        habitdescriptor = habitdescriptor.calendar.account;
        FeatureConfig featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        } else
        {
            ((FeatureConfig)featureconfig).fishfood_debug();
            ContentResolver.requestSync(habitdescriptor, "com.android.calendar", bundle);
            return;
        }
    }

    private static HabitDescriptor updateImpl(HabitModifications habitmodifications, boolean flag)
        throws IOException
    {
        Object obj;
        GrooveStore groovestore;
        obj = GrooveStore.store;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        }
        groovestore = (GrooveStore)obj;
        obj = habitmodifications.getDescriptor();
        groovestore.database.beginTransaction();
        obj = groovestore.getHabit(((HabitDescriptor) (obj)).calendar.account, ((HabitDescriptor) (obj)).calendar.calendarId, ((HabitDescriptor) (obj)).habitId).getEntityValues().getAsByteArray("data");
        if (obj != null) goto _L2; else goto _L1
_L1:
        obj = null;
_L3:
        boolean flag1;
        if (groovestore.updateHabit(HabitStoreUtils.modificationToEntity(habitmodifications, ((com.google.calendar.intention.habit.client.nano.ClientHabitProto.HabitData) (obj))), flag) == 1)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        groovestore.database.setTransactionSuccessful();
        if (!flag1)
        {
            break MISSING_BLOCK_LABEL_150;
        }
        habitmodifications = habitmodifications.getDescriptor();
_L4:
        groovestore.database.endTransaction();
        return habitmodifications;
_L2:
        obj = (com.google.calendar.intention.habit.client.nano.ClientHabitProto.HabitData)MessageNano.mergeFrom(new com.google.calendar.intention.habit.client.nano.ClientHabitProto.HabitData(), ((byte []) (obj)), 0, obj.length);
          goto _L3
        habitmodifications = null;
          goto _L4
        habitmodifications;
        groovestore.database.endTransaction();
        throw habitmodifications;
    }

    public int count(HabitFilterOptions habitfilteroptions)
        throws IOException
    {
        return list(habitfilteroptions).length;
    }

    public Habit create(HabitModifications habitmodifications)
        throws IOException
    {
        habitmodifications = create(habitmodifications, true);
        requestSyncUp(habitmodifications.getDescriptor(), 1);
        return habitmodifications;
    }

    public Habit create(HabitModifications habitmodifications, boolean flag)
        throws IOException
    {
        GrooveStore groovestore = GrooveStore.store;
        if (groovestore == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        } else
        {
            ((GrooveStore)groovestore).insertHabit(HabitStoreUtils.modificationToEntity(habitmodifications, null), flag);
            return read(habitmodifications.getDescriptor());
        }
    }

    public Habit[] list(HabitFilterOptions habitfilteroptions)
        throws IOException
    {
        boolean flag2 = true;
        Object obj;
        Object obj1;
        Object obj2;
        boolean flag;
        int i;
        boolean flag1;
        int j;
        int k;
        int l;
        if (habitfilteroptions == null)
        {
            obj = HabitFilterOptions.DEFAULT;
        } else
        {
            obj = habitfilteroptions;
        }
        if (((HabitFilterOptions) (obj)).dirtyFilter == null && ((HabitFilterOptions) (obj)).fitIntegrationStatusFilter == null && ((HabitFilterOptions) (obj)).accountName == null && ((HabitFilterOptions) (obj)).calendarId == null && ((HabitFilterOptions) (obj)).activeAfterFilter == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            obj1 = null;
        } else
        {
            obj2 = new StringBuilder();
            if (((HabitFilterOptions) (obj)).dirtyFilter != null)
            {
                StringBuilder stringbuilder = ((StringBuilder) (obj2)).append("dirty");
                if (((HabitFilterOptions) (obj)).dirtyFilter.booleanValue())
                {
                    obj1 = "=1";
                } else
                {
                    obj1 = "=0";
                }
                stringbuilder.append(((String) (obj1))).append(" AND ");
            }
            ((StringBuilder) (obj2)).append("lastSynced=0").append(" AND ");
            if (((HabitFilterOptions) (obj)).activeAfterFilter != null)
            {
                ((StringBuilder) (obj2)).append("(untilMillisUtc").append("<=0 OR untilMillisUtc").append(">").append(((HabitFilterOptions) (obj)).activeAfterFilter).append(")").append(" AND ");
            }
            if (((HabitFilterOptions) (obj)).accountName != null)
            {
                if (!((HabitFilterOptions) (obj)).accountName.isEmpty())
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    throw new IllegalStateException();
                }
                ((StringBuilder) (obj2)).append("account=?").append(" AND ");
            }
            if (((HabitFilterOptions) (obj)).calendarId != null)
            {
                if (!((HabitFilterOptions) (obj)).calendarId.isEmpty())
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    throw new IllegalStateException();
                }
                ((StringBuilder) (obj2)).append("calendar=?").append(" AND ");
            }
            ((StringBuilder) (obj2)).delete(((StringBuilder) (obj2)).length() - " AND ".length(), ((StringBuilder) (obj2)).length());
            obj1 = ((StringBuilder) (obj2)).toString();
        }
        if (habitfilteroptions != null) goto _L2; else goto _L1
_L1:
        obj = null;
_L4:
        obj2 = GrooveStore.store;
        if (obj2 == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        }
        break; /* Loop/switch isn't completed */
_L2:
        if (habitfilteroptions.accountName != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (habitfilteroptions.calendarId != null)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag)
        {
            k = 1;
        } else
        {
            k = 0;
        }
        if (flag1)
        {
            l = 1;
        } else
        {
            l = 0;
        }
        k = l + k + 0;
        if (k == 0)
        {
            obj = null;
        } else
        {
            obj = new String[k];
            if (flag)
            {
                obj[0] = habitfilteroptions.accountName;
                i = ((flag2) ? 1 : 0);
            } else
            {
                i = 0;
            }
            if (flag1)
            {
                obj[i] = habitfilteroptions.calendarId;
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
        obj1 = ((GrooveStore)obj2).database.query("habit", null, ((String) (obj1)), ((String []) (obj)), null, null, null);
        obj = new Entity[((Cursor) (obj1)).getCount()];
        for (i = 0; i < obj.length; i++)
        {
            ((Cursor) (obj1)).moveToPosition(i);
            obj2 = new ContentValues(((Cursor) (obj1)).getColumnCount());
            DatabaseUtils.cursorRowToContentValues(((Cursor) (obj1)), ((ContentValues) (obj2)));
            obj[i] = new Entity(((ContentValues) (obj2)));
        }

        ((Cursor) (obj1)).close();
        obj1 = new ArrayList(obj.length);
        j = obj.length;
        for (i = 0; i < j; i++)
        {
            obj2 = HabitStoreUtils.entityToHabit(obj[i]);
            if (habitfilteroptions == null || habitfilteroptions.fitIntegrationStatusFilter == null || Ints.contains(habitfilteroptions.fitIntegrationStatusFilter, ((Habit) (obj2)).getFitIntegrationStatus()))
            {
                ((ArrayList) (obj1)).add(obj2);
            }
        }

        obj = ((Object) ((Object[])Array.newInstance(com/google/android/calendar/api/habit/Habit, 0)));
        if (obj1 instanceof Collection)
        {
            habitfilteroptions = (Collection)obj1;
        } else
        {
            obj1 = ((Iterable) (obj1)).iterator();
            habitfilteroptions = new ArrayList();
            Iterators.addAll(habitfilteroptions, ((Iterator) (obj1)));
        }
        return (Habit[])habitfilteroptions.toArray(((Object []) (obj)));
    }

    public Habit read(HabitDescriptor habitdescriptor)
        throws IOException
    {
        GrooveStore groovestore = GrooveStore.store;
        if (groovestore == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        }
        habitdescriptor = ((GrooveStore)groovestore).getHabit(habitdescriptor.calendar.account, habitdescriptor.calendar.calendarId, habitdescriptor.habitId);
        if (habitdescriptor != null)
        {
            return HabitStoreUtils.entityToHabit(habitdescriptor);
        } else
        {
            return null;
        }
    }

    public boolean removeDeleted(HabitDescriptor habitdescriptor)
    {
        GrooveStore groovestore = GrooveStore.store;
        if (groovestore == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        }
        groovestore = (GrooveStore)groovestore;
        Account account = habitdescriptor.calendar.account;
        String s = habitdescriptor.calendar.calendarId;
        habitdescriptor = habitdescriptor.habitId;
        (new FitIntegrationStore(groovestore.context)).setIntegration(account.name, s, habitdescriptor, 0);
        return groovestore.database.delete("habit", GrooveStore.WHERE_HABIT_DESCRIPTOR, new String[] {
            account.name, s, habitdescriptor
        }) > 0;
    }

    public Habit update(HabitModifications habitmodifications, Void void1)
        throws IOException
    {
        if (!habitmodifications.isModified())
        {
            return read(habitmodifications.getDescriptor());
        }
        void1 = updateImpl(habitmodifications, true);
        if (void1 == null)
        {
            return null;
        } else
        {
            requestSyncUp(habitmodifications.getDescriptor(), 0);
            propagateChangesToInstances(habitmodifications);
            return read(void1);
        }
    }

    public Habit update(HabitModifications habitmodifications, Void void1, boolean flag)
        throws IOException
    {
        habitmodifications = updateImpl(habitmodifications, flag);
        if (habitmodifications == null)
        {
            return null;
        } else
        {
            return read(habitmodifications);
        }
    }

    static 
    {
        LogUtils.getLogTag(com/google/android/calendar/api/habit/HabitApiStoreImpl);
    }
}
