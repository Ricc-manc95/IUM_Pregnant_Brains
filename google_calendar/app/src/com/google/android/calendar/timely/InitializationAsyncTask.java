// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.accounts.Account;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Pair;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.syncadapters.timely.type.CalendarType;
import com.google.android.calendar.Utils;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.color.ColorFactory;
import com.google.android.calendar.prefs.PrefService;
import com.google.android.calendar.timely.settings.DirectoryLoader;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.android.calendar.utils.timely.TimelyUtils;
import com.google.android.gsf.Gservices;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class InitializationAsyncTask extends AsyncTask
{
    static final class ColorCache
    {

        public final Map colorKeyMap = new HashMap();

        ColorCache()
        {
        }
    }


    private static final String COLORS_PROJECTION[] = {
        "_id", "account_name", "account_type", "color", "color_index"
    };
    private static final String PROJECTION[] = {
        "_id", "ownerAccount", "account_name", "sync_events", "visible", "calendar_color", "account_type", "COALESCE(isPrimary,account_name=ownerAccount) AS isPrimary"
    };
    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timely/InitializationAsyncTask);
    private final Context applicationContext;
    private final ColorCache colorCache = new ColorCache();

    public InitializationAsyncTask(Context context)
    {
        applicationContext = context.getApplicationContext();
    }

    private final transient Void doInBackground(Account aaccount[])
    {
        Object obj;
        Object obj2;
        Object obj3;
        ContentResolver contentresolver;
        Object obj4;
        Cursor cursor;
        HashMap hashmap;
        int i1;
        int k1;
        int i2;
        int j2;
        contentresolver = applicationContext.getContentResolver();
        obj3 = Locale.getDefault();
        obj4 = applicationContext;
        obj2 = DirectoryLoader.directoryForLocale(((Context) (obj4)), ((Locale) (obj3)).toString());
        obj = obj2;
        if (obj2 == null)
        {
            obj = DirectoryLoader.directoryForLocale(((Context) (obj4)), ((Locale) (obj3)).getLanguage().toLowerCase());
        }
        obj = findLocalHolidayCalendarServerId(((com.google.calendar.v2.libs.proto.DirectoryProto.Directory) (obj)));
        if (obj == null)
        {
            obj4 = Locale.US;
            Context context = applicationContext;
            obj2 = DirectoryLoader.directoryForLocale(context, ((Locale) (obj4)).toString());
            obj = obj2;
            if (obj2 == null)
            {
                obj = DirectoryLoader.directoryForLocale(context, ((Locale) (obj4)).getLanguage().toLowerCase());
            }
            obj = findLocalHolidayCalendarServerId(((com.google.calendar.v2.libs.proto.DirectoryProto.Directory) (obj)));
        }
        if (obj == null)
        {
            LogUtils.e(TAG, "Unable to find holiday calendar for locale %s", new Object[] {
                obj3
            });
        }
        obj2 = contentresolver.query(android.provider.CalendarContract.Colors.CONTENT_URI, COLORS_PROJECTION, "color_type=0", null, null);
        obj3 = colorCache;
        if (obj2 != null && ((Cursor) (obj2)).moveToFirst())
        {
            ((ColorCache) (obj3)).colorKeyMap.clear();
            do
            {
                obj4 = new Account(((Cursor) (obj2)).getString(1), ((Cursor) (obj2)).getString(2));
                int i = ((Cursor) (obj2)).getInt(3);
                ((ColorCache) (obj3)).colorKeyMap.put(new Pair(obj4, Integer.valueOf(i)), Integer.valueOf(((Cursor) (obj2)).getInt(4)));
            } while (((Cursor) (obj2)).moveToNext());
        }
        ((Cursor) (obj2)).close();
        obj4 = android.provider.CalendarContract.Calendars.CONTENT_URI;
        String as[] = PROJECTION;
        if (obj == null)
        {
            obj2 = "(COALESCE(isPrimary,account_name=ownerAccount)=1) OR (((ownerAccount = '#contacts@group.v.calendar.google.com') OR (ownerAccount = 'addressbook#contacts@group.v.calendar.google.com'))) OR (calendar_color = '-6299161')";
        } else
        {
            obj2 = "(COALESCE(isPrimary,account_name=ownerAccount)=1) OR (((ownerAccount = '#contacts@group.v.calendar.google.com') OR (ownerAccount = 'addressbook#contacts@group.v.calendar.google.com'))) OR (calendar_color = '-6299161') OR (ownerAccount = ?)";
        }
        if (obj == null)
        {
            obj3 = null;
        } else
        {
            obj3 = new String[1];
            obj3[0] = obj;
        }
        cursor = contentresolver.query(((Uri) (obj4)), as, ((String) (obj2)), ((String []) (obj3)), null);
        obj2 = new HashMap();
        obj3 = new HashMap();
        obj4 = new HashMap();
        i2 = 0xff9fe1e7;
        j2 = 0xff9a9cff;
        k1 = 0;
        i1 = 0;
        if (cursor == null)
        {
            break MISSING_BLOCK_LABEL_822;
        }
        hashmap = new HashMap(aaccount.length);
        k1 = aaccount.length;
        for (int j = 0; j < k1; j++)
        {
            Account account1 = aaccount[j];
            if (AccountUtil.isGoogleAccount(account1))
            {
                hashmap.put(Integer.valueOf(account1.hashCode()), null);
            }
        }

        cursor.moveToPosition(-1);
        boolean flag = i1;
_L2:
        com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarItem calendaritem;
        long l3;
        if (!cursor.moveToNext())
        {
            break; /* Loop/switch isn't completed */
        }
        calendaritem = new com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarItem();
        l3 = cursor.getLong(0);
        String s1;
        String s2;
        String s3;
        boolean flag1;
        boolean flag2;
        boolean flag3;
        if (cursor.getInt(7) == 1)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        s1 = cursor.getString(1);
        s2 = cursor.getString(2);
        s3 = cursor.getString(6);
        if (cursor.getInt(3) == 1)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        if (cursor.getInt(4) == 1)
        {
            flag3 = true;
        } else
        {
            flag3 = false;
        }
        i1 = cursor.getInt(5);
        calendaritem.uri = ContentUris.withAppendedId(android.provider.CalendarContract.Calendars.CONTENT_URI, l3);
        calendaritem.isPrimary = flag1;
        calendaritem.account = new Account(s2, s3);
        calendaritem.ownerAccount = s1;
        calendaritem.isSynced = flag2;
        calendaritem.isVisible = flag3;
        calendaritem.color = i1;
        i1 = ((flag) ? 1 : 0);
        if (calendaritem.color == 0xff9fe1e7)
        {
            i1 = 1;
        }
        flag = i1;
        if (!hashmap.containsKey(Integer.valueOf(calendaritem.account.hashCode())))
        {
            continue; /* Loop/switch isn't completed */
        }
        if (!calendaritem.isPrimary)
        {
            break MISSING_BLOCK_LABEL_719;
        }
        ((Map) (obj2)).put(calendaritem.account, calendaritem);
        flag = i1;
        continue; /* Loop/switch isn't completed */
        aaccount;
        cursor.close();
        throw aaccount;
        if (!CalendarType.isBirthdayCalendar(calendaritem.ownerAccount))
        {
            break MISSING_BLOCK_LABEL_752;
        }
        ((Map) (obj3)).put(calendaritem.account, calendaritem);
        flag = i1;
        continue; /* Loop/switch isn't completed */
        if (!CalendarType.isHolidayCalendar(calendaritem.ownerAccount))
        {
            break MISSING_BLOCK_LABEL_785;
        }
        ((Map) (obj4)).put(calendaritem.account, calendaritem);
        flag = i1;
        continue; /* Loop/switch isn't completed */
        LogUtils.e(TAG, "Found unexpected calendar item %s", new Object[] {
            calendaritem
        });
        flag = i1;
        if (true) goto _L2; else goto _L1
_L1:
        cursor.close();
        k1 = ((flag) ? 1 : 0);
        int k = j2;
        int j1 = i2;
        if (k1 != 0)
        {
            j1 = 0;
            k = 0;
        }
        ArrayList arraylist = new ArrayList();
        int i3 = aaccount.length;
        i2 = 0;
        k1 = k;
        k = j1;
        j1 = k1;
        do
        {
            if (i2 < i3)
            {
                Account account = aaccount[i2];
                int k2 = j1;
                int l2 = k;
                if (AccountUtil.isGoogleAccount(account))
                {
                    Object obj5 = (com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarItem)((Map) (obj2)).get(account);
                    int l1;
                    if (obj5 == null)
                    {
                        LogUtils.e(TAG, "Unable to find primary account for %s", new Object[] {
                            account
                        });
                        l1 = k;
                    } else
                    {
                        setCalendarState(arraylist, ((com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarItem) (obj5)), k, j1);
                        l1 = k;
                        if (k != 0)
                        {
                            l1 = 0;
                            j1 = 0;
                        }
                    }
                    obj5 = (com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarItem)((Map) (obj3)).get(account);
                    if (obj5 == null)
                    {
                        obj5 = new Bundle();
                        ((Bundle) (obj5)).putInt("calendar_color", 0xff92e1c0);
                        TimelyUtils.subscribeBirthdayCalendar(applicationContext, account, true, ((Bundle) (obj5)));
                    } else
                    {
                        setCalendarState(arraylist, ((com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarItem) (obj5)), 0, 0);
                    }
                    obj5 = (com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarItem)((Map) (obj4)).get(account);
                    if (obj5 == null)
                    {
                        k2 = j1;
                        l2 = l1;
                        if (obj != null)
                        {
                            TimelyUtils.subscribeCalendar(applicationContext, account, ((String) (obj)), null);
                            l2 = l1;
                            k2 = j1;
                        }
                    } else
                    {
                        setCalendarState(arraylist, ((com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarItem) (obj5)), 0xff42d692, 0);
                        k2 = j1;
                        l2 = l1;
                    }
                }
                i2++;
                j1 = k2;
                k = l2;
                continue;
            }
            try
            {
                contentresolver.applyBatch("com.android.calendar", arraylist);
            }
            // Misplaced declaration of an exception variable
            catch (Object obj1)
            {
                LogUtils.e(TAG, ((Throwable) (obj1)), "Exception while applying initialization changes.", new Object[0]);
            }
            // Misplaced declaration of an exception variable
            catch (Object obj1)
            {
                LogUtils.e(TAG, ((Throwable) (obj1)), "Exception while applying initialization changes.", new Object[0]);
            }
            // Misplaced declaration of an exception variable
            catch (Object obj1)
            {
                LogUtils.e(TAG, ((Throwable) (obj1)), "Exception while applying initialization changes.", new Object[0]);
            }
            PrefService.getInstance(applicationContext).setHolidaysColor(CalendarApi.getColorFactory().defaultHolidayColor());
            j1 = aaccount.length;
            for (int l = 0; l < j1; l++)
            {
                Object obj1 = aaccount[l];
                if (!AccountUtil.isGoogleAccount(((Account) (obj1))))
                {
                    continue;
                }
                Bundle bundle = new Bundle();
                bundle.putBoolean("upload", true);
                Utils.appendSyncFlags(bundle);
                String s = android.provider.CalendarContract.Calendars.CONTENT_URI.getAuthority();
                FeatureConfig featureconfig = Features.instance;
                if (featureconfig == null)
                {
                    throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
                }
                ((FeatureConfig)featureconfig).fishfood_debug();
                ContentResolver.requestSync(((Account) (obj1)), s, bundle);
            }

            return null;
        } while (true);
    }

    private final String findLocalHolidayCalendarServerId(com.google.calendar.v2.libs.proto.DirectoryProto.Directory directory)
    {
label0:
        {
            Object obj1 = null;
            String s = Gservices.getString(applicationContext.getContentResolver(), "device_country", null);
            Object obj;
            if (s != null)
            {
                s = s.toLowerCase();
            } else
            {
                s = null;
            }
            obj = obj1;
            if (TextUtils.isEmpty(s))
            {
                break label0;
            }
            obj = obj1;
            if (directory == null)
            {
                break label0;
            }
            directory = directory.countryHoliday_.iterator();
            do
            {
                obj = obj1;
                if (!directory.hasNext())
                {
                    break label0;
                }
                obj = (com.google.calendar.v2.libs.proto.DirectoryProto.DirectoryEntry)directory.next();
            } while (!s.equalsIgnoreCase(((com.google.calendar.v2.libs.proto.DirectoryProto.DirectoryEntry) (obj)).country_));
            obj = ((com.google.calendar.v2.libs.proto.DirectoryProto.DirectoryEntry) (obj)).id_;
        }
        return ((String) (obj));
    }

    private final void setCalendarState(List list, com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarItem calendaritem, int i, int j)
    {
        boolean flag1 = true;
        android.content.ContentProviderOperation.Builder builder = ContentProviderOperation.newUpdate(calendaritem.uri);
        boolean flag = false;
        if (!calendaritem.isSynced)
        {
            builder.withValue("sync_events", Integer.valueOf(1));
            flag = true;
        }
        if (!calendaritem.isVisible)
        {
            builder.withValue("visible", Integer.valueOf(1));
            flag = true;
        }
        if (i != 0 && (j == 0 || calendaritem.color == j))
        {
            builder.withValue("calendar_color", Integer.valueOf(i));
            ColorCache colorcache = colorCache;
            calendaritem = calendaritem.account;
            calendaritem = (Integer)colorcache.colorKeyMap.get(new Pair(calendaritem, Integer.valueOf(i)));
            i = ((flag1) ? 1 : 0);
            if (calendaritem != null)
            {
                builder.withValue("calendar_color_index", calendaritem);
                i = ((flag1) ? 1 : 0);
            }
        } else
        {
            i = ((flag) ? 1 : 0);
        }
        if (i != 0)
        {
            list.add(builder.build());
        }
    }

    protected volatile Object doInBackground(Object aobj[])
    {
        return doInBackground((Account[])aobj);
    }

}
