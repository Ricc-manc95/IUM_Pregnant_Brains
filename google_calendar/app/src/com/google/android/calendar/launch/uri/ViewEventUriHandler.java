// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.launch.uri;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.android.calendarcommon2.DateException;
import com.android.calendarcommon2.Duration;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.AllInOneCalendarActivity;
import com.google.android.calendar.Utils;
import com.google.android.calendar.launch.LaunchIntentConstants;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

// Referenced classes of package com.google.android.calendar.launch.uri:
//            BaseUriHandler

public class ViewEventUriHandler extends BaseUriHandler
{
    public final class ResolveSyncIdAndLaunchIntent extends AsyncTask
    {

        private final Activity activity;
        private final ViewEventUriHandler this$0;

        protected final Object doInBackground(Object aobj[])
        {
            Intent intent = null;
            aobj = ViewEventUriHandler.this;
            if (((ViewEventUriHandler) (aobj)).eidParts != null)
            {
                aobj = ((ViewEventUriHandler) (aobj)).eidParts[0];
            } else
            {
                aobj = null;
            }
            if (aobj == null)
            {
                LogUtils.i(ViewEventUriHandler.TAG, "Could not find event for uri: %s", new Object[] {
                    mIntent.getData()
                });
            } else
            {
                Intent intent1 = createIntentForEvent(activity, ((String) (aobj)));
                intent = intent1;
                if (intent1 == null)
                {
                    return createIntentForInstance(activity, ((String) (aobj)));
                }
            }
            return intent;
        }

        protected final void onPostExecute(Object obj)
        {
            Intent intent = (Intent)obj;
            ViewEventUriHandler vieweventurihandler = ViewEventUriHandler.this;
            obj = ViewEventUriHandler.this;
            if (((ViewEventUriHandler) (obj)).eidParts != null)
            {
                obj = ((ViewEventUriHandler) (obj)).eidParts[1];
            } else
            {
                obj = null;
            }
            vieweventurihandler.launchGeneratedEventIntent(intent, ((String) (obj)), activity);
        }

        public ResolveSyncIdAndLaunchIntent(Activity activity1)
        {
            this$0 = ViewEventUriHandler.this;
            super();
            activity = activity1;
        }
    }


    private static final String EVENT_PROJECTION[] = {
        "_id", "dtstart", "dtend", "duration"
    };
    private static final String INSTANCE_PROJECTION[] = {
        "event_id", "end"
    };
    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/launch/uri/ViewEventUriHandler);
    private final int attendeeStatus;
    public String eidParts[];

    public ViewEventUriHandler(Intent intent)
    {
        super(intent, Arrays.asList(new String[] {
            "http", "https"
        }), Arrays.asList(new String[] {
            "www.google.com", "calendar.google.com"
        }), Arrays.asList(new String[] {
            "/calendar/m?event.*", "/calendar/render.*", "/calendar/hosted/.*/event", "/calendar/hosted/.*/render"
        }));
        eidParts = null;
        if (mIntent != null && mIntent.getData() != null)
        {
            eidParts = extractEidAndAccountName(mIntent.getData());
        }
        attendeeStatus = extractAttendeeStatus(mIntent);
    }

    private static Intent createFakeViewEventIntent(Context context)
    {
        Uri uri = ContentUris.withAppendedId(android.provider.CalendarContract.Events.CONTENT_URI, -1L);
        if (LaunchIntentConstants.viewAction == null)
        {
            LaunchIntentConstants.viewAction = String.valueOf(context.getPackageName()).concat(".EVENT_VIEW");
        }
        Intent intent = new Intent(LaunchIntentConstants.viewAction);
        intent.setClass(context, com/google/android/calendar/AllInOneCalendarActivity);
        intent.setDataAndType(uri, "vnd.android.cursor.item/event");
        Utils.setThirdPartyEidSource(intent);
        return intent;
    }

    private final Intent createIntent(Context context, int i, long l, long l1)
    {
        if (l == 0L || l1 == 0L)
        {
            return null;
        } else
        {
            Uri uri = ContentUris.withAppendedId(android.provider.CalendarContract.Events.CONTENT_URI, i);
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setPackage(context.getPackageName());
            intent.setDataAndType(uri, "vnd.android.cursor.item/event");
            intent.putExtra("beginTime", l);
            intent.putExtra("endTime", l1);
            intent.putExtra("response_event_id", i);
            intent.putExtra("attendee_status", attendeeStatus);
            Utils.setThirdPartyEidSource(intent);
            return intent;
        }
    }

    private static int extractAttendeeStatus(Intent intent)
    {
        if (intent == null) goto _L2; else goto _L1
_L1:
        intent = intent.getData();
        if (intent == null || !"RESPOND".equals(intent.getQueryParameter("action"))) goto _L2; else goto _L3
_L3:
        int i;
        try
        {
            i = Integer.parseInt(intent.getQueryParameter("rst"));
        }
        // Misplaced declaration of an exception variable
        catch (Intent intent)
        {
            return 0;
        }
        i;
        JVM INSTR tableswitch 1 3: default 64
    //                   1 66
    //                   2 68
    //                   3 70;
           goto _L2 _L4 _L5 _L6
_L2:
        return 0;
_L4:
        return 1;
_L5:
        return 2;
_L6:
        return 4;
    }

    private static String[] extractEidAndAccountName(Uri uri)
    {
        Object obj;
        obj = uri.getQueryParameter("eid");
        LogUtils.d(TAG, "eid=%s", new Object[] {
            obj
        });
        if (obj == null)
        {
            return null;
        }
        byte abyte0[];
        abyte0 = Base64.decode(((String) (obj)), 0);
        obj = TAG;
        if (LogUtils.maxEnabledLogLevel <= 3) goto _L2; else goto _L1
_L1:
        boolean flag = false;
_L22:
        if (!flag) goto _L4; else goto _L3
_L3:
        LogUtils.d(TAG, "decoded eid=%s", new Object[] {
            new String(abyte0)
        });
          goto _L4
_L24:
        int j;
        if (j >= abyte0.length) goto _L6; else goto _L5
_L5:
        if (abyte0[j] != 32) goto _L8; else goto _L7
_L7:
        int i = abyte0.length - j - 1;
        if (j == 0 || i < 3) goto _L6; else goto _L9
_L9:
        if (abyte0[abyte0.length - 2] != 64) goto _L11; else goto _L10
_L10:
        i--;
        abyte0[abyte0.length - 1];
        JVM INSTR lookupswitch 5: default 427
    //                   103: 440
    //                   104: 447
    //                   105: 454
    //                   109: 433
    //                   118: 461;
           goto _L12 _L13 _L14 _L15 _L16 _L17
_L17:
        break MISSING_BLOCK_LABEL_461;
_L12:
        LogUtils.wtf(TAG, "Unexpected one letter domain: %s", new Object[] {
            Byte.valueOf(abyte0[abyte0.length - 1])
        });
        obj = "%";
_L25:
        String s;
        String s1;
        s1 = new String(abyte0, 0, j);
        s = new String(abyte0, j + 1, i);
        LogUtils.d(TAG, "eid=   %s", new Object[] {
            s1
        });
        LogUtils.d(TAG, "accountName= %s", new Object[] {
            s
        });
        LogUtils.d(TAG, "domain=%s", new Object[] {
            obj
        });
        if (obj == null) goto _L19; else goto _L18
_L18:
        s = String.valueOf(s);
        obj = String.valueOf(obj);
        if (((String) (obj)).length() == 0) goto _L21; else goto _L20
_L20:
        obj = s.concat(((String) (obj)));
        uri = ((Uri) (obj));
_L23:
        return (new String[] {
            s1, uri
        });
        obj;
        LogUtils.w(TAG, ((Throwable) (obj)), "Punting malformed URI %s", new Object[] {
            uri
        });
_L6:
        return null;
_L2:
        if (!Log.isLoggable(((String) (obj)), 3))
        {
            break MISSING_BLOCK_LABEL_378;
        }
        flag = true;
          goto _L22
        flag = Log.isLoggable(((String) (obj)), 3);
          goto _L22
_L21:
        obj = new String(s);
        uri = ((Uri) (obj));
          goto _L23
_L8:
        j++;
          goto _L24
_L19:
        uri = s;
          goto _L23
_L11:
        obj = null;
          goto _L25
_L4:
        j = 0;
          goto _L24
_L16:
        obj = "gmail.com";
          goto _L25
_L13:
        obj = "group.calendar.google.com";
          goto _L25
_L14:
        obj = "holiday.calendar.google.com";
          goto _L25
_L15:
        obj = "import.calendar.google.com";
          goto _L25
        obj = "group.v.calendar.google.com";
          goto _L25
    }

    private static long parseDate(String s)
    {
        Object obj;
        if (s.length() == 8)
        {
            obj = "yyyyMMdd";
        } else
        {
            obj = "yyyyMMdd'T'HHmmss'Z'";
        }
        try
        {
            obj = new SimpleDateFormat(((String) (obj)));
            ((SimpleDateFormat) (obj)).setTimeZone(TimeZone.getTimeZone("UTC"));
            return ((SimpleDateFormat) (obj)).parse(s).getTime();
        }
        // Misplaced declaration of an exception variable
        catch (String s) { }
        // Misplaced declaration of an exception variable
        catch (String s) { }
        LogUtils.w(TAG, s, "Parsing the date has failed.", new Object[0]);
        return -1L;
    }

    final Intent createIntentForEvent(Context context, String s)
    {
        Object obj;
        if (eidParts != null)
        {
            obj = eidParts[1];
        } else
        {
            obj = null;
        }
        LogUtils.d(TAG, "eidParts=%s/%s", new Object[] {
            s, obj
        });
        LogUtils.d(TAG, "selection: %s", new Object[] {
            "_sync_id = ? AND ownerAccount = ?"
        });
        obj = context.getContentResolver().query(android.provider.CalendarContract.Events.CONTENT_URI, EVENT_PROJECTION, "_sync_id = ? AND ownerAccount = ?", new String[] {
            s, obj
        }, "calendar_access_level desc");
        if (obj == null)
        {
            LogUtils.i(TAG, "NOTE: found no matches on event with id='%s'", new Object[] {
                s
            });
            return null;
        }
        LogUtils.i(TAG, "NOTE: found %d matches on event with id='%s'", new Object[] {
            Integer.valueOf(((Cursor) (obj)).getCount()), s
        });
_L4:
        int i;
        long l1;
        long l2;
        if (!((Cursor) (obj)).moveToNext())
        {
            break MISSING_BLOCK_LABEL_430;
        }
        i = ((Cursor) (obj)).getInt(0);
        l2 = ((Cursor) (obj)).getLong(1);
        l1 = ((Cursor) (obj)).getLong(2);
        LogUtils.d(TAG, "_id: %d", new Object[] {
            Long.valueOf(((Cursor) (obj)).getLong(0))
        });
        LogUtils.d(TAG, "startMillis: %d", new Object[] {
            Long.valueOf(l2)
        });
        LogUtils.d(TAG, "endMillis:   %d", new Object[] {
            Long.valueOf(l1)
        });
        long l = l1;
        if (l1 != 0L) goto _L2; else goto _L1
_L1:
        boolean flag;
        s = ((Cursor) (obj)).getString(3);
        LogUtils.d(TAG, "duration:    %s", new Object[] {
            s
        });
        flag = TextUtils.isEmpty(s);
        if (flag) goto _L4; else goto _L3
_L3:
        Duration duration = new Duration();
        duration.parse(s);
        l = l2 + duration.getMillis();
        LogUtils.d(TAG, "startMillis! %d", new Object[] {
            Long.valueOf(l2)
        });
        LogUtils.d(TAG, "endMillis!   %d", new Object[] {
            Long.valueOf(l)
        });
        if (l < l2) goto _L4; else goto _L2
_L2:
        context = createIntent(context, i, l2, l);
        ((Cursor) (obj)).close();
        return context;
        s;
        LogUtils.d(TAG, "duration: %s", new Object[] {
            s
        });
          goto _L4
        context;
        ((Cursor) (obj)).close();
        throw context;
        ((Cursor) (obj)).close();
        return null;
    }

    final Intent createIntentForInstance(Context context, String s)
    {
        s = s.split("_");
        if (s.length == 2) goto _L2; else goto _L1
_L1:
        long l;
        return null;
_L2:
        if ((l = parseDate(s[1])) <= 0L) goto _L1; else goto _L3
_L3:
        String s1 = s[0];
        String s2 = String.valueOf(s[0]).concat("_R%");
        android.net.Uri.Builder builder;
        if (eidParts != null)
        {
            s = eidParts[1];
        } else
        {
            s = null;
        }
        builder = android.provider.CalendarContract.Instances.CONTENT_URI.buildUpon();
        ContentUris.appendId(builder, l);
        ContentUris.appendId(builder, 1000L + l);
        s = context.getContentResolver().query(builder.build(), INSTANCE_PROJECTION, "(_sync_id = ? OR _sync_id LIKE ?) AND ownerAccount = ? AND begin = ?", new String[] {
            s1, s2, s, String.valueOf(l)
        }, "calendar_access_level desc");
        if (s == null) goto _L1; else goto _L4
_L4:
        if (!s.moveToFirst())
        {
            break MISSING_BLOCK_LABEL_186;
        }
        context = createIntent(context, s.getInt(0), l, s.getLong(1));
        s.close();
        return context;
        s.close();
        return null;
        context;
        s.close();
        throw context;
    }

    final void launchGeneratedEventIntent(Intent intent, String s, Activity activity)
    {
        if (intent == null) goto _L2; else goto _L1
_L1:
        if (attendeeStatus != 0) goto _L4; else goto _L3
_L3:
        activity.startActivity(intent);
_L6:
        return;
_L4:
        int i = intent.getIntExtra("response_event_id", -1);
        int j = attendeeStatus;
        intent = new _cls1(activity);
        activity = new ContentValues();
        activity.put("attendeeStatus", Integer.valueOf(j));
        intent.startUpdate(0, null, android.provider.CalendarContract.Attendees.CONTENT_URI, activity, "attendeeEmail=? AND event_id=?", new String[] {
            s, String.valueOf(i)
        });
        return;
_L2:
        if (activity.startNextMatchingActivity(mIntent)) goto _L6; else goto _L5
_L5:
        activity.startActivity(createFakeViewEventIntent(activity));
        return;
        intent;
        activity.startActivity(createFakeViewEventIntent(activity));
        return;
    }


    private class _cls1 extends AsyncQueryHandler
    {

        private final Activity val$activity;
        private final Intent val$intent;
        private final int val$status;

        protected final void onUpdateComplete(int i, Object obj, int j)
        {
            if (j == 0)
            {
                LogUtils.w(ViewEventUriHandler.TAG, "No rows updated - starting event viewer", new Object[0]);
                intent.putExtra("attendeeStatus", status);
                activity.startActivity(intent);
                return;
            }
            status;
            JVM INSTR tableswitch 1 4: default 76
        //                       1 77
        //                       2 93
        //                       3 76
        //                       4 99;
               goto _L1 _L2 _L3 _L1 _L4
_L1:
            return;
_L2:
            i = 0x7f130420;
_L6:
            Toast.makeText(activity, i, 1).show();
            return;
_L3:
            i = 0x7f130421;
            continue; /* Loop/switch isn't completed */
_L4:
            i = 0x7f130423;
            if (true) goto _L6; else goto _L5
_L5:
        }

        _cls1(Activity activity1)
        {
            intent = intent1;
            status = i;
            activity = activity1;
            super(final_contentresolver);
        }
    }

}
