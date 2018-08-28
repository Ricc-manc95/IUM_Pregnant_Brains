// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timely.store;

import android.accounts.Account;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.JsonWriter;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.commonsync.constants.ExtendedPropertiesConstants;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.android.calendar.utils.timely.TimelyUtils;
import com.google.common.collect.ImmutableCollection;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.google.android.apps.calendar.timely.store:
//            PreferredNotification

public class TimelyStoreUtils
    implements ExtendedPropertiesConstants
{

    private static final String TAG = com/google/android/apps/calendar/timely/store/TimelyStoreUtils.getSimpleName();

    private TimelyStoreUtils()
    {
    }

    public static int colorHexToInt(String s)
    {
        return Integer.parseInt(s, 16) | 0xff000000;
    }

    public static String colorIntToHex(int i)
    {
        return String.format("%06x", new Object[] {
            Integer.valueOf(0xffffff & i)
        });
    }

    public static List getAccountNames(Account aaccount[])
    {
        ArrayList arraylist = new ArrayList();
        for (int i = 0; i < aaccount.length; i++)
        {
            arraylist.add(aaccount[i].name);
        }

        return arraylist;
    }

    public static PreferredNotification[] getRecentNotificationOptions(Context context, Account account, boolean flag)
    {
        context = context.getResources();
        account = account.type;
        PreferredNotification apreferrednotification[];
        if (AccountUtil.EXCHANGE_TYPES.contains(account))
        {
            int i;
            int l;
            int i1;
            int j1;
            if (flag)
            {
                i = 0x7f0b0025;
            } else
            {
                i = 0x7f0b0027;
            }
            account = context.getIntArray(i);
            if (flag)
            {
                i = 0x7f0b0024;
            } else
            {
                i = 0x7f0b0026;
            }
            context = context.getIntArray(i);
        } else
        {
            int j;
            if (flag)
            {
                j = 0x7f0b003b;
            } else
            {
                j = 0x7f0b003d;
            }
            account = context.getIntArray(j);
            if (flag)
            {
                j = 0x7f0b003a;
            } else
            {
                j = 0x7f0b003c;
            }
            context = context.getIntArray(j);
        }
        l = account.length;
        apreferrednotification = new PreferredNotification[l];
        i = 0;
        while (i < l) 
        {
            i1 = account[i];
            j1 = context[i];
            int k;
            if (flag)
            {
                k = 1;
            } else
            {
                k = 0;
            }
            apreferrednotification[i] = new PreferredNotification(k, i1, j1);
            i++;
        }
        return apreferrednotification;
    }

    public static String jsonFromValues(String s, String s1)
    {
        try
        {
            StringWriter stringwriter = new StringWriter();
            (new JsonWriter(stringwriter)).beginObject().name(s).value(s1).endObject().close();
            s1 = stringwriter.toString();
        }
        // Misplaced declaration of an exception variable
        catch (String s1)
        {
            LogUtils.i(TAG, "Error creating JSON for %s", new Object[] {
                s
            });
            return null;
        }
        return s1;
    }

    public static Map loadExtendedProperties(ContentResolver contentresolver, long l)
    {
        contentresolver = contentresolver.query(android.provider.CalendarContract.ExtendedProperties.CONTENT_URI, EXTENDED_PROPERTIES_PROJECTION, "event_id=?", new String[] {
            Long.toString(l)
        }, null);
        if (contentresolver != null) goto _L2; else goto _L1
_L1:
        Object obj1 = Collections.emptyMap();
        Object obj;
        obj = obj1;
        if (contentresolver != null)
        {
            contentresolver.close();
            obj = obj1;
        }
_L5:
        return ((Map) (obj));
_L2:
        obj1 = new HashMap();
        for (; contentresolver.moveToNext(); ((Map) (obj1)).put(contentresolver.getString(0), contentresolver.getString(1))) { }
          goto _L3
        obj1;
        obj = contentresolver;
        contentresolver = ((ContentResolver) (obj1));
_L6:
        if (obj != null)
        {
            ((Cursor) (obj)).close();
        }
        throw contentresolver;
_L3:
        obj = obj1;
        if (contentresolver == null) goto _L5; else goto _L4
_L4:
        contentresolver.close();
        return ((Map) (obj1));
        contentresolver;
        obj = null;
          goto _L6
    }

    public static void triggerSyncAdapterRestoreTimelyData()
    {
        Bundle bundle = new Bundle();
        bundle.putBoolean("sync_extra_sync_timely_data", true);
        LogUtils.d(TAG, "Requesting restore of Timely event data", new Object[0]);
        String s = android.provider.CalendarContract.Calendars.CONTENT_URI.getAuthority();
        FeatureConfig featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        } else
        {
            ((FeatureConfig)featureconfig).fishfood_debug();
            ContentResolver.requestSync(null, s, bundle);
            return;
        }
    }

    protected static Bundle triggerSyncAdapterSettingUpdate(Account account)
    {
        Bundle bundle = new Bundle(3);
        bundle.putBoolean("force", true);
        bundle.putBoolean("upload", true);
        bundle.putBoolean("sync_extra_update_settings", true);
        LogUtils.d(TAG, "Requesting upgrading settings in triggerSyncAdapterSettingUpdate", new Object[0]);
        String s = android.provider.CalendarContract.Calendars.CONTENT_URI.getAuthority();
        FeatureConfig featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        } else
        {
            ((FeatureConfig)featureconfig).fishfood_debug();
            ContentResolver.requestSync(account, s, bundle);
            return bundle;
        }
    }

    public static void triggerSyncAdapterSyncWithExtras(Account account, String s, boolean flag, Bundle bundle)
    {
        TimelyUtils.triggerSyncAdapterSyncWithExtras(null, null, false, bundle);
    }

}
