// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar;

import android.accounts.Account;
import android.content.ContentProviderClient;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apiary.ParseException;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.syncadapters.timely.sql.ColumnConstants;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.google.android.syncadapters.calendar:
//            ProviderHelper, CalendarSyncState, Utilities, TimeRange, 
//            FeedState

public class CalendarSyncStateUtils
{
    static interface IdTransformer
    {

        public abstract String transform$5166KOBMC4NMOOBECSNL6T3ID5N6EEQCD9GNCO9FDHGMSPPFADQ74QBECSTIIJ3AC5R62BRCC5N6EBQJEHP6IRJ77C______0(String s);
    }


    private static final String TAG = LogUtils.getLogTag(com/google/android/syncadapters/calendar/CalendarSyncStateUtils);

    public CalendarSyncStateUtils()
    {
    }

    static void applyOperationsAsSyncAdapter(ContentProviderClient contentproviderclient, Account account, ArrayList arraylist)
        throws RemoteException, ParseException
    {
        try
        {
            LogUtils.d(TAG, "Applying operations: %s", new Object[] {
                Integer.valueOf(arraylist.size())
            });
            ProviderHelper.asSyncAdapter(account).applyBatch(contentproviderclient, arraylist);
            return;
        }
        // Misplaced declaration of an exception variable
        catch (ContentProviderClient contentproviderclient)
        {
            LogUtils.e(TAG, contentproviderclient, "Failed to apply operations while upgrading from ICS to JB", new Object[0]);
        }
    }

    static CalendarSyncState fromBytes(Uri uri, byte abyte0[], ContentProviderClient contentproviderclient, Account account)
        throws RemoteException, ParseException
    {
        if (abyte0 != null)
        {
            CalendarSyncState calendarsyncstate;
            try
            {
                calendarsyncstate = new CalendarSyncState(uri, new JSONObject(new String(abyte0)));
            }
            catch (JSONException jsonexception)
            {
                return fromParcelBytes(uri, abyte0, contentproviderclient, account);
            }
            return calendarsyncstate;
        } else
        {
            LogUtils.d(TAG, "Resetting sync state for %s", new Object[] {
                uri
            });
            return new CalendarSyncState(uri, new JSONObject());
        }
    }

    private static CalendarSyncState fromParcelBytes(Uri uri, byte abyte0[], ContentProviderClient contentproviderclient, Account account)
        throws RemoteException, ParseException
    {
        if (abyte0 == null) goto _L2; else goto _L1
_L1:
        Parcel parcel;
        parcel = Parcel.obtain();
        parcel.unmarshall(abyte0, 0, abyte0.length);
        parcel.setDataPosition(0);
        int i;
        int j;
        i = parcel.readInt();
        j = parcel.readInt();
        if (i == 0xdeadbeef && j == 2)
        {
            break MISSING_BLOCK_LABEL_109;
        }
        LogUtils.e(TAG, "Invalid MAGIC or VERSION: %x / %d", new Object[] {
            Integer.valueOf(i), Integer.valueOf(j)
        });
        parcel.recycle();
        return null;
        uri;
        LogUtils.e(TAG, uri, "Error Parsing", new Object[0]);
        parcel.recycle();
        return null;
        CalendarSyncState calendarsyncstate;
        LogUtils.d(TAG, "GData Sync State found, upgrading to V3. Account: %s", new Object[] {
            account.name
        });
        calendarsyncstate = new CalendarSyncState(uri, new JSONObject());
        calendarsyncstate.data.put("version", 15);
_L6:
        calendarsyncstate.data.put("jellyBeanOrNewer", true);
_L7:
        calendarsyncstate.data.put("firstSeen", false);
_L8:
        uri = parcel.readBundle();
        abyte0 = uri.keySet().iterator();
_L4:
        Object obj1;
        do
        {
            if (!abyte0.hasNext())
            {
                break MISSING_BLOCK_LABEL_614;
            }
            obj1 = (String)abyte0.next();
            LogUtils.d(TAG, "Converting state for feed %s", new Object[] {
                obj1
            });
        } while (obj1 == null);
        if (!((String) (obj1)).startsWith("http")) goto _L4; else goto _L3
_L3:
        Object obj;
        Object obj2;
        obj = getCalendarIdFromGdataFeedUrl(((String) (obj1)));
        calendarsyncstate.addFeed(((String) (obj)));
        obj = calendarsyncstate.getFeedState(((String) (obj)));
        obj2 = Utilities.getEventsRange(contentproviderclient, "cal_sync1=?", new String[] {
            obj1
        });
        if (obj2 == null)
        {
            break MISSING_BLOCK_LABEL_321;
        }
        ((FeedState) (obj)).putLong("upgrade_min_start", ((TimeRange) (obj2)).startTime);
        ((FeedState) (obj)).putLong("upgrade_max_start", ((TimeRange) (obj2)).endTime);
        long l;
        long l1;
        if (obj2 != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        obj1 = uri.getBundle(((String) (obj1)));
        ((Bundle) (obj1)).isEmpty();
        if (!i)
        {
            break MISSING_BLOCK_LABEL_496;
        }
        l = ((FeedState) (obj)).getLong("upgrade_max_start", 0L);
        l1 = ((Bundle) (obj1)).getLong("window_end");
        if (l1 <= l) goto _L4; else goto _L5
_L5:
        ((FeedState) (obj)).putLong("upgrade_max_start", l1);
          goto _L4
        uri;
        parcel.recycle();
        throw uri;
        uri;
        LogUtils.e("CalendarSyncAdapter", uri, "Failed to set version.", new Object[0]);
          goto _L6
        uri;
        LogUtils.e("CalendarSyncAdapter", uri, "Failed to set is jelly bean.", new Object[0]);
          goto _L7
        uri;
        LogUtils.e("CalendarSyncAdapter", uri, "Failed to set is first seen.", new Object[0]);
          goto _L8
        uri;
        LogUtils.e(TAG, uri, "Error Parsing", new Object[0]);
        parcel.recycle();
        return null;
        uri;
        LogUtils.e(TAG, uri, "Error Parsing", new Object[0]);
        parcel.recycle();
        return null;
        obj2 = ((Bundle) (obj1)).keySet().iterator();
_L12:
        if (!((Iterator) (obj2)).hasNext()) goto _L4; else goto _L9
_L9:
        String s;
        Object obj3;
        s = (String)((Iterator) (obj2)).next();
        obj3 = ((Bundle) (obj1)).get(s);
        if (!(obj3 instanceof String)) goto _L11; else goto _L10
_L10:
        ((FeedState) (obj)).putString(s, (String)obj3);
          goto _L12
_L11:
        if (!(obj3 instanceof Boolean)) goto _L14; else goto _L13
_L13:
        ((FeedState) (obj)).putBoolean(s, ((Boolean)obj3).booleanValue());
          goto _L12
_L14:
        if (!(obj3 instanceof Long)) goto _L12; else goto _L15
_L15:
        ((FeedState) (obj)).putLong(s, ((Long)obj3).longValue());
          goto _L12
          goto _L4
        LogUtils.d(TAG, "Upgrading calendars", new Object[0]);
        uri = ProviderHelper.asClient();
        abyte0 = android.provider.CalendarContract.Calendars.CONTENT_URI;
        obj = ColumnConstants.WHERE_ACCOUNT_AND_TYPE;
        obj1 = account.name;
        obj2 = account.type;
        obj = uri.query(contentproviderclient, abyte0, new String[] {
            "_id", "cal_sync1", "ownerAccount"
        }, ((String) (obj)), new String[] {
            obj1, obj2
        }, null);
        if (obj == null) goto _L17; else goto _L16
_L16:
        obj1 = new ArrayList();
        obj2 = new ContentValues();
        ((ContentValues) (obj2)).put("cal_sync2", null);
        ((ContentValues) (obj2)).put("cal_sync3", null);
_L21:
        if (!((Cursor) (obj)).moveToNext()) goto _L19; else goto _L18
_L18:
        l = ((Cursor) (obj)).getLong(0);
        abyte0 = getCalendarIdFromGdataFeedUrl(((Cursor) (obj)).getString(1));
        uri = abyte0;
        if (abyte0 != null)
        {
            break MISSING_BLOCK_LABEL_826;
        }
        abyte0 = ((Cursor) (obj)).getString(2);
        uri = abyte0;
        if (abyte0 != null)
        {
            break MISSING_BLOCK_LABEL_826;
        }
        Log.wtf(TAG, (new StringBuilder(74)).append("CAL_SYNC1 and ownerAccount are both null for calendar ").append(l).toString());
        uri = abyte0;
        ((ContentValues) (obj2)).put("cal_sync1", uri);
        abyte0 = ProviderHelper.asSyncAdapter(account);
        uri = ContentUris.withAppendedId(android.provider.CalendarContract.Calendars.CONTENT_URI, l);
        boolean flag;
        if (((ProviderHelper) (abyte0)).account != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
          goto _L20
_L22:
        ((ArrayList) (obj1)).add(ContentProviderOperation.newUpdate(uri).withValues(((ContentValues) (obj2))).withYieldAllowed(true).build());
        if (((ArrayList) (obj1)).size() > 100)
        {
            applyOperationsAsSyncAdapter(contentproviderclient, account, ((ArrayList) (obj1)));
            ((ArrayList) (obj1)).clear();
        }
          goto _L21
        uri;
        ((Cursor) (obj)).close();
        throw uri;
_L23:
        uri = ProviderHelper.toAsSyncAdapterUri(uri, ((ProviderHelper) (abyte0)).account);
        break; /* Loop/switch isn't completed */
_L19:
        if (!((ArrayList) (obj1)).isEmpty())
        {
            applyOperationsAsSyncAdapter(contentproviderclient, account, ((ArrayList) (obj1)));
        }
        ((Cursor) (obj)).close();
_L17:
        LogUtils.d(TAG, "Upgrading events", new Object[0]);
        transformSyncIds(contentproviderclient, account, "http://www.google.com/calendar/feeds/%/events/%", new _cls1());
        android.provider.SyncStateContract.Helpers.update(contentproviderclient, calendarsyncstate.uri, calendarsyncstate.data.toString().getBytes());
        LogUtils.d(TAG, "Requesting full sync in CalendarSyncState#fromParcelBytes", new Object[0]);
        uri = new Bundle();
        abyte0 = Features.instance;
        if (abyte0 != null)
        {
            break MISSING_BLOCK_LABEL_1052;
        }
        throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        ((FeatureConfig)abyte0).fishfood_debug();
        ContentResolver.requestSync(account, "com.android.calendar", uri);
        parcel.recycle();
        return calendarsyncstate;
_L2:
        return null;
_L20:
        if (flag) goto _L23; else goto _L22
    }

    private static String getCalendarIdFromGdataFeedUrl(String s)
    {
        String s1;
        if (s == null)
        {
            Log.w(TAG, "Feed ID is null");
            s1 = null;
        } else
        {
            s1 = s;
            if (s.startsWith("https://www.google.com/calendar/feeds/"))
            {
                s = s.substring(38, s.indexOf('/', 39));
                String s2;
                try
                {
                    s2 = URLDecoder.decode(s, "UTF-8");
                }
                catch (UnsupportedEncodingException unsupportedencodingexception)
                {
                    return s;
                }
                return s2;
            }
        }
        return s1;
    }

    public static CalendarSyncState getIfAvailable(ContentProviderClient contentproviderclient, Account account)
    {
        Pair pair = android.provider.SyncStateContract.Helpers.getWithUri(contentproviderclient, android.provider.CalendarContract.SyncState.CONTENT_URI, account);
        if (pair == null)
        {
            return null;
        }
        contentproviderclient = fromBytes(ProviderHelper.toAsSyncAdapterUri((Uri)pair.first, account), (byte[])pair.second, contentproviderclient, account);
        return contentproviderclient;
        contentproviderclient;
_L2:
        ThrowableExtension.STRATEGY.printStackTrace(contentproviderclient);
        return null;
        contentproviderclient;
        if (true) goto _L2; else goto _L1
_L1:
    }

    private static void sanitizeRecurrence(String s, ContentValues contentvalues, String s1)
    {
        if (s != null)
        {
            String s2 = Utilities.sanitizeRecurrence(s);
            if (!s2.equals(s))
            {
                LogUtils.v(TAG, "Fixed bad %s: '%s'=>'%s'", new Object[] {
                    s1, s, s2
                });
                contentvalues.put(s1, s2);
            }
        }
    }

    static void transformSyncIds(ContentProviderClient contentproviderclient, Account account, String s, IdTransformer idtransformer)
        throws RemoteException, ParseException
    {
        Object obj1;
        ProviderHelper providerhelper = ProviderHelper.asSyncAdapter(account);
        obj1 = android.provider.CalendarContract.Events.CONTENT_URI;
        s = (new StringBuilder(String.valueOf(s).length() + 44 + String.valueOf(s).length())).append("_sync_id LIKE '").append(s).append("' OR original_sync_id").append(" LIKE '").append(s).append("'").toString();
        obj1 = providerhelper.query(contentproviderclient, ((Uri) (obj1)), new String[] {
            "_id", "_sync_id", "original_sync_id", "cal_sync1", "rrule", "exrule"
        }, s, null, null);
        if (obj1 == null) goto _L2; else goto _L1
_L1:
        ArrayList arraylist = new ArrayList();
_L6:
        if (!((Cursor) (obj1)).moveToNext()) goto _L4; else goto _L3
_L3:
        Object obj;
        String s1;
        ContentValues contentvalues;
        String s2;
        long l = ((Cursor) (obj1)).getLong(0);
        ((Cursor) (obj1)).getString(3);
        s1 = ((Cursor) (obj1)).getString(1);
        s = ((Cursor) (obj1)).getString(2);
        obj = ((Cursor) (obj1)).getString(4);
        s2 = ((Cursor) (obj1)).getString(5);
        contentvalues = new ContentValues();
        contentvalues.put("_sync_id", idtransformer._mth5166KOBMC4NMOOBECSNL6T3ID5N6EEQCD9GNCO9FDHGMSPPFADQ74QBECSTIIJ3AC5R62BRCC5N6EBQJEHP6IRJ77C______0(s1));
        contentvalues.put("original_sync_id", idtransformer._mth5166KOBMC4NMOOBECSNL6T3ID5N6EEQCD9GNCO9FDHGMSPPFADQ74QBECSTIIJ3AC5R62BRCC5N6EBQJEHP6IRJ77C______0(s));
        s = ContentUris.withAppendedId(android.provider.CalendarContract.Events.CONTENT_URI, l);
        sanitizeRecurrence(((String) (obj)), contentvalues, "rrule");
        sanitizeRecurrence(s2, contentvalues, "exrule");
        obj = ProviderHelper.asSyncAdapter(account);
        boolean flag;
        if (((ProviderHelper) (obj)).account != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
          goto _L5
_L7:
        arraylist.add(ContentProviderOperation.newUpdate(((Uri) (obj))).withYieldAllowed(true).withValues(contentvalues).build());
_L9:
        if (arraylist.size() > 100)
        {
            applyOperationsAsSyncAdapter(contentproviderclient, account, arraylist);
            arraylist.clear();
        }
          goto _L6
        contentproviderclient;
        ((Cursor) (obj1)).close();
        throw contentproviderclient;
_L11:
        obj = ProviderHelper.toAsSyncAdapterUri(s, ((ProviderHelper) (obj)).account);
          goto _L7
        obj;
        LogUtils.e(TAG, ((Throwable) (obj)), "Bad recurrence rule in event %s. Removing it from database", new Object[] {
            s1
        });
        obj = ProviderHelper.asSyncAdapter(account);
        if (((ProviderHelper) (obj)).account != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
          goto _L8
_L12:
        arraylist.add(ContentProviderOperation.newDelete(s).withYieldAllowed(true).build());
          goto _L9
_L13:
        s = ProviderHelper.toAsSyncAdapterUri(s, ((ProviderHelper) (obj)).account);
        break; /* Loop/switch isn't completed */
_L4:
        if (!arraylist.isEmpty())
        {
            applyOperationsAsSyncAdapter(contentproviderclient, account, arraylist);
        }
        ((Cursor) (obj1)).close();
_L2:
        return;
_L5:
        if (flag) goto _L11; else goto _L10
_L10:
        obj = s;
          goto _L7
_L8:
        if (flag) goto _L13; else goto _L12
    }


    private class _cls1
        implements IdTransformer
    {

        public final String transform$5166KOBMC4NMOOBECSNL6T3ID5N6EEQCD9GNCO9FDHGMSPPFADQ74QBECSTIIJ3AC5R62BRCC5N6EBQJEHP6IRJ77C______0(String s)
        {
            int i;
            if (s != null && s.startsWith("http://www.google.com/calendar/feeds/"))
            {
                if ((i = s.indexOf("/events/", 37)) >= 0)
                {
                    return s.substring(i + 8);
                }
            }
            return s;
        }

        _cls1()
        {
        }
    }

}
