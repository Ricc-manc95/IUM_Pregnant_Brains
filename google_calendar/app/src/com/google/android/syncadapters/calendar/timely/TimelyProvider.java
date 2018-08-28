// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar.timely;

import android.accounts.Account;
import android.content.ContentProvider;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apiary.ParseException;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.config.feature.VariantFeatureConfig_release;
import com.google.android.apps.calendar.primes.PerformanceMetricCollectorImpl;
import com.google.android.apps.calendar.primes.api.PerformanceMetricCollectorHolder;
import com.google.android.apps.calendar.timely.store.AccountSettings;
import com.google.android.apps.calendar.timely.store.AccountSettingsStore;
import com.google.android.apps.calendar.timely.store.TimelyStore;
import com.google.android.apps.calendar.timely.store.TimelyStoreUtils;
import com.google.android.calendar.utils.permission.PermissionsUtil;
import com.google.android.calendar.utils.version.MncUtil;
import com.google.android.syncadapters.calendar.CalendarSyncAdapterApiary;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;

public class TimelyProvider extends ContentProvider
{

    private static final UriMatcher sUriMatcher;
    private TimelyStore mEventStore;

    public TimelyProvider()
    {
    }

    private final int updateFromClient(String s, com.google.android.apps.calendar.timely.store.AccountSettingsStore.UpdateBuilder updatebuilder)
    {
        s = new Account(s, "com.google");
        return mEventStore.accountSettingsStore.updateFromClient(s, updatebuilder) == null ? 0 : 1;
    }

    public Bundle call(String s, String s1, Bundle bundle)
    {
        boolean flag;
        flag = true;
        s1 = getContext();
        break MISSING_BLOCK_LABEL_8;
        if (MncUtil.isMnc() && (PermissionsUtil.checkSelfPermission(s1, "android.permission.READ_CALENDAR") != 0 || PermissionsUtil.checkSelfPermission(s1, "android.permission.WRITE_CALENDAR") != 0))
        {
            flag = false;
        }
        if (!flag)
        {
            LogUtils.wtf("TimelyProvider", "Insufficient permissions", new Object[0]);
            return null;
        }
        break MISSING_BLOCK_LABEL_57;
        s1 = getContext().getContentResolver().acquireContentProviderClient(android.provider.CalendarContract.Calendars.CONTENT_URI.getAuthority());
        if (s1 == null)
        {
            Log.wtf("TimelyProvider", "Provider should not be null");
            return null;
        }
        if (!com.google.android.apps.calendar.timely.contract.TimelyContract.ProviderCommand.SUBSCRIBE_CALENDAR.name().equals(s)) goto _L2; else goto _L1
_L1:
        CalendarSyncAdapterApiary.subscribeCalendar(new Account(bundle.getString("account_name"), bundle.getString("account_type")), bundle, s1);
_L4:
        s1.release();
        return null;
_L2:
        if (!com.google.android.apps.calendar.timely.contract.TimelyContract.ProviderCommand.UNSUBSCRIBE_CALENDAR.name().equals(s)) goto _L4; else goto _L3
_L3:
        CalendarSyncAdapterApiary.unsubscribeCalendar(new Account(bundle.getString("account_name"), bundle.getString("account_type")), bundle, s1);
          goto _L4
        s;
        ThrowableExtension.STRATEGY.printStackTrace(s);
        s1.release();
        return null;
        s;
        ThrowableExtension.STRATEGY.printStackTrace(s);
        s1.release();
        return null;
        s;
        s1.release();
        throw s;
    }

    public int delete(Uri uri, String s, String as[])
    {
        return 0;
    }

    public String getType(Uri uri)
    {
        return null;
    }

    public Uri insert(Uri uri, ContentValues contentvalues)
    {
        return uri;
    }

    public boolean onCreate()
    {
        mEventStore = TimelyStore.acquire(getContext());
        return true;
    }

    public Cursor query(Uri uri, String as[], String s, String as1[], String s1)
    {
        s1 = null;
        sUriMatcher.match(uri);
        JVM INSTR tableswitch 1 2: default 32
    //                   1 55
    //                   2 81;
           goto _L1 _L2 _L3
_L1:
        as = s1;
_L5:
        if (as != null)
        {
            as.setNotificationUri(getContext().getContentResolver(), uri);
        }
        return as;
_L2:
        as = mEventStore.accountSettingsStore.database.query("timelysettings", as, s, as1, null, null, null);
        continue; /* Loop/switch isn't completed */
_L3:
        as1 = uri.getLastPathSegment();
        s = new MatrixCursor(as, 1);
        s1 = ((String) (new Object[as.length]));
        int i = 0;
        while (i < as.length) 
        {
            if ("account_name".equals(as[i]))
            {
                s1[i] = as1;
            } else
            if ("account_type".equals(as[i]))
            {
                s1[i] = "com.google";
            } else
            if ("calendar_color".equals(as[i]))
            {
                s1[i] = Integer.valueOf(TimelyStore.acquire(getContext()).accountSettingsStore.getSettings(as1).taskColor);
            } else
            {
                uri = as[i];
                throw new IllegalArgumentException((new StringBuilder(String.valueOf(uri).length() + 17)).append("Unknown column '").append(uri).append("'").toString());
            }
            i++;
        }
        s.addRow(s1);
        as = s;
        if (true) goto _L5; else goto _L4
_L4:
    }

    public int update(Uri uri, ContentValues contentvalues, String s, String as[])
    {
        sUriMatcher.match(uri);
        JVM INSTR tableswitch 2 2: default 24
    //                   2 30;
           goto _L1 _L2
_L1:
        int i = 0;
_L4:
        return i;
_L2:
        uri = uri.getLastPathSegment();
        contentvalues.remove("sync_events");
        if (contentvalues.containsKey("visible"))
        {
            int j;
            if (contentvalues.getAsInteger("visible").intValue() == 1)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            s = new com.google.android.apps.calendar.timely.store.AccountSettingsStore.UpdateBuilder();
            as = ((com.google.android.apps.calendar.timely.store.AccountSettingsStore.UpdateBuilder) (s)).values;
            if (i != 0)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            as.put("tasksselected", Integer.valueOf(i));
            i = Math.max(0, updateFromClient(uri, s));
            contentvalues.remove("visible");
        } else
        {
            i = 0;
        }
        j = i;
        if (contentvalues.containsKey("calendar_color"))
        {
            j = contentvalues.getAsInteger("calendar_color").intValue();
            s = new com.google.android.apps.calendar.timely.store.AccountSettingsStore.UpdateBuilder();
            ((com.google.android.apps.calendar.timely.store.AccountSettingsStore.UpdateBuilder) (s)).values.put("taskscolor", TimelyStoreUtils.colorIntToHex(j));
            j = Math.max(i, updateFromClient(uri, s));
            contentvalues.remove("calendar_color");
            contentvalues.remove("calendar_color_index");
        }
        i = j;
        if (contentvalues.size() > 0)
        {
            LogUtils.wtf("TimelyProvider", "Tried to update tasks calendar with unsupported values: %s", new Object[] {
                contentvalues
            });
            return j;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    static 
    {
        sUriMatcher = new UriMatcher(-1);
        Features.instance = new VariantFeatureConfig_release();
        PerformanceMetricCollectorImpl performancemetriccollectorimpl = new PerformanceMetricCollectorImpl();
        if (PerformanceMetricCollectorHolder.instance == null)
        {
            PerformanceMetricCollectorHolder.instance = performancemetriccollectorimpl;
        }
        sUriMatcher.addURI("com.google.android.timely", "accountsettings", 1);
        sUriMatcher.addURI("com.google.android.timely", "tasks/*", 2);
    }
}
