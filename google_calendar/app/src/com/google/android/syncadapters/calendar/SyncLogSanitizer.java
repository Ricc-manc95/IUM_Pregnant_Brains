// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar;

import android.content.ContentValues;
import android.content.Entity;
import android.text.TextUtils;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventHabitInstance;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.SingletonImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

// Referenced classes of package com.google.android.syncadapters.calendar:
//            CalendarSyncInfo, SyncStatsHelper

public class SyncLogSanitizer
{

    public static final ImmutableSet CALENDAR_NAME_FIELDS = new SingletonImmutableSet("feed_internal");
    public static final ImmutableSet CALENDAR_URL_FIELDS = new SingletonImmutableSet("feed");
    private static final ImmutableSet DONT_SANITIZE_IN_PROGRESS_KEYS = ImmutableSet.of("pageToken", "maxResults", "maxAttendees", "timeMin", "timeMax", "supportsAllDayReminders", new String[] {
        "updatedMin"
    });
    public static final ImmutableSet DONT_SANITIZE_KEYS = ImmutableSet.of("sync_default", "sync_extra_update_client_status", "ignore_backoff", "force", "ignore_settings", "upload", new String[] {
        "only_groove"
    });
    public static final ImmutableSet DO_SANITIZE_KEYS = ImmutableSet.construct(2, new Object[] {
        "feed_internal", "feed"
    });
    private static final String TAG = com/google/android/syncadapters/calendar/SyncLogSanitizer.getSimpleName();
    public int calendarId;
    public String calendarName;

    public SyncLogSanitizer(int i, String s)
    {
        calendarId = -1;
        calendarName = "";
        calendarId = i;
        calendarName = s;
    }

    static Map getAnonymizedFeedDataMap(Map map)
    {
        HashMap hashmap = new HashMap();
        if (map == null)
        {
            return hashmap;
        }
        UnmodifiableIterator unmodifiableiterator = (UnmodifiableIterator)DONT_SANITIZE_IN_PROGRESS_KEYS.iterator();
        do
        {
            if (!unmodifiableiterator.hasNext())
            {
                break;
            }
            String s = (String)unmodifiableiterator.next();
            if (map.containsKey(s))
            {
                hashmap.put(s, map.get(s));
            }
        } while (true);
        return hashmap;
    }

    public final String getDebugString(CalendarSyncInfo calendarsyncinfo, Event event, Entity entity, boolean flag)
    {
        StringBuilder stringbuilder = new StringBuilder();
        FeatureConfig featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)featureconfig).bugfood_build();
        if (calendarsyncinfo != null)
        {
            if (flag)
            {
                stringbuilder.append("Calendar[ _id=").append(calendarsyncinfo.calendarId).append(" ] ");
            } else
            {
                stringbuilder.append("Calendar[ syncId=").append(LogUtils.sanitizeName(TAG, calendarsyncinfo.calendarSyncId)).append(" ] ");
            }
        }
        if (event != null)
        {
            stringbuilder.append("Event[ ");
            if (!TextUtils.isEmpty(event.recurringEventId))
            {
                stringbuilder.append("recurringEventId=").append(event.recurringEventId).append(" ");
            }
            if (event.habitInstance != null && !TextUtils.isEmpty(event.habitInstance.parentId))
            {
                stringbuilder.append("habitParentId=").append(event.habitInstance.parentId).append(" ");
            }
            if (event.sequence != null)
            {
                stringbuilder.append("sequence=").append(event.sequence).append(" ");
            }
            if (event.updated != null)
            {
                stringbuilder.append("updated=").append(event.updated.toStringRfc3339()).append(" ");
            }
            if (event.etag != null)
            {
                stringbuilder.append("etag=").append(event.etag).append(" ");
            }
            if (!TextUtils.isEmpty(event.id))
            {
                stringbuilder.append("id=").append(event.id).append(" ");
            }
            if (event.originalStartTime != null)
            {
                calendarsyncinfo = event.originalStartTime;
                if (((EventDateTime) (calendarsyncinfo)).date != null)
                {
                    calendarsyncinfo = ((EventDateTime) (calendarsyncinfo)).date;
                } else
                if (((EventDateTime) (calendarsyncinfo)).dateTime != null)
                {
                    calendarsyncinfo = ((EventDateTime) (calendarsyncinfo)).dateTime;
                } else
                {
                    calendarsyncinfo = null;
                }
                if (calendarsyncinfo != null)
                {
                    stringbuilder.append("originalStartTime=").append(calendarsyncinfo.toStringRfc3339());
                }
            }
            stringbuilder.append("] ");
        }
        if (entity != null && entity.getEntityValues() != null)
        {
            calendarsyncinfo = entity.getEntityValues();
            stringbuilder.append("Entity[ ");
            if (!TextUtils.isEmpty(calendarsyncinfo.getAsString("_id")))
            {
                stringbuilder.append("id=").append(calendarsyncinfo.getAsString("_id")).append(" ");
            }
            if (!TextUtils.isEmpty(calendarsyncinfo.getAsString("original_id")))
            {
                stringbuilder.append("original_id=").append(calendarsyncinfo.getAsString("original_id")).append(" ");
            }
            if (!TextUtils.isEmpty(calendarsyncinfo.getAsString("_sync_id")))
            {
                stringbuilder.append("sync_id=").append(calendarsyncinfo.getAsString("_sync_id")).append(" ");
            }
            if (!TextUtils.isEmpty(calendarsyncinfo.getAsString("original_sync_id")))
            {
                stringbuilder.append("original_sync_id=").append(calendarsyncinfo.getAsString("original_sync_id")).append(" ");
            }
            if (calendarsyncinfo.getAsInteger("sync_data2") != null)
            {
                stringbuilder.append("sequence=").append(calendarsyncinfo.getAsInteger("sync_data2")).append(" ");
            }
            if (!TextUtils.isEmpty(calendarsyncinfo.getAsString("sync_data5")))
            {
                stringbuilder.append("updated=").append(calendarsyncinfo.getAsString("sync_data5")).append(" ");
            }
            if (!TextUtils.isEmpty(calendarsyncinfo.getAsString("sync_data4")))
            {
                stringbuilder.append("etag=").append(calendarsyncinfo.getAsString("sync_data4")).append(" ");
            }
            if (!TextUtils.isEmpty(calendarsyncinfo.getAsString("calendar_id")))
            {
                stringbuilder.append("calendar_id=").append(calendarsyncinfo.getAsString("calendar_id")).append(" ");
            }
            if (!TextUtils.isEmpty(calendarsyncinfo.getAsString("cal_sync1")) && !flag)
            {
                stringbuilder.append("calendar_sync_id=").append(LogUtils.sanitizeName(TAG, calendarsyncinfo.getAsString("cal_sync1"))).append(" ");
            }
            if (calendarsyncinfo.getAsLong("originalInstanceTime") != null)
            {
                stringbuilder.append("original_instance_time=").append(calendarsyncinfo.getAsLong("originalInstanceTime")).append(" ");
            }
            if (Integer.valueOf(1).equals(calendarsyncinfo.getAsInteger("dirty")))
            {
                stringbuilder.append("mutators=").append(SyncStatsHelper.getMutatorType(calendarsyncinfo.getAsString("mutators"))).append(" ");
            }
            stringbuilder.append("] ");
        }
        return stringbuilder.toString();
    }

    public final Throwable getSanitizedThrowable(Throwable throwable)
    {
        Throwable throwable1 = null;
        if (throwable == null)
        {
            return null;
        }
        String s = throwable.getClass().getSimpleName();
        if (throwable.getCause() != null)
        {
            throwable1 = getSanitizedThrowable(throwable.getCause());
        }
        throwable1 = new Throwable(s, throwable1);
        throwable1.setStackTrace(throwable.getStackTrace());
        return throwable1;
    }

}
