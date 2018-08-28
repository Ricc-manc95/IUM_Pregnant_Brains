// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar;

import android.accounts.Account;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.TransactionTooLargeException;
import android.text.TextUtils;
import android.text.format.Time;
import android.util.TimeFormatException;
import com.android.calendarcommon2.EventRecurrence;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apiary.ParseException;
import com.google.api.client.http.HttpResponseException;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

// Referenced classes of package com.google.android.syncadapters.calendar:
//            ProviderHelper, SyncLog, TimeRange, SyncUtil

public class Utilities
{

    private static final ImmutableList GOOGLE_CONSUMER_ACCOUNT_DOMAINS = ImmutableList.of("@gmail.com", "@googlemail.com");
    private static final String TAG = LogUtils.getLogTag(com/google/android/syncadapters/calendar/Utilities);

    public Utilities()
    {
    }

    public static void applyOperationsAsSyncAdapter(ContentProviderClient contentproviderclient, Account account, ArrayList arraylist)
        throws ParseException, RemoteException
    {
        if (!arraylist.isEmpty())
        {
            contentproviderclient = ProviderHelper.asSyncAdapter(account).applyBatch(contentproviderclient, arraylist);
            LogUtils.v(TAG, "Batch applied successfully, %d results", new Object[] {
                Integer.valueOf(contentproviderclient.length)
            });
        }
        arraylist.clear();
        return;
        contentproviderclient;
        SyncLog.logError(contentproviderclient, "Error applying batch, unknown number of yield points succeeded");
        throw new ParseException("error while applying batch", contentproviderclient);
        contentproviderclient;
        arraylist.clear();
        throw contentproviderclient;
        contentproviderclient;
        SyncLog.logError(contentproviderclient, "Error applying batch, %d yield points succeeded", new Object[] {
            Integer.valueOf(contentproviderclient.getNumSuccessfulYieldPoints())
        });
        throw new ParseException("error while applying batch", contentproviderclient);
    }

    public static TimeRange getEventsRange(ContentProviderClient contentproviderclient, String s, String as[])
        throws RemoteException, ParseException
    {
        contentproviderclient = ProviderHelper.asClient().query(contentproviderclient, android.provider.CalendarContract.Events.CONTENT_URI, new String[] {
            "dtstart"
        }, s, as, "dtstart");
        if (contentproviderclient == null)
        {
            break MISSING_BLOCK_LABEL_102;
        }
        long l;
        long l1;
        if (!contentproviderclient.moveToFirst())
        {
            break MISSING_BLOCK_LABEL_96;
        }
        l1 = contentproviderclient.getLong(0);
        if (!contentproviderclient.moveToLast())
        {
            break MISSING_BLOCK_LABEL_90;
        }
        l = contentproviderclient.getLong(0);
_L1:
        s = new TimeRange(l1 - 0x5265c00L, l + 0x5265c00L);
        contentproviderclient.close();
        return s;
        l = l1;
          goto _L1
        contentproviderclient.close();
        return null;
        s;
        contentproviderclient.close();
        throw s;
    }

    public static Boolean getValueAsBoolean(String s, Object obj)
    {
        if (!(obj instanceof CharSequence))
        {
            break MISSING_BLOCK_LABEL_55;
        }
        boolean flag;
        if (1L == Long.parseLong(obj.toString()))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        return Boolean.valueOf(flag);
        NumberFormatException numberformatexception;
        numberformatexception;
        LogUtils.e(TAG, "Cannot parse Long value for %s at key %s", new Object[] {
            obj, s
        });
        return null;
        LogUtils.e(TAG, "Cannot cast value for %s to a Long: %s", new Object[] {
            s, obj
        });
        return null;
    }

    public static boolean isGoogleConsumerAccount(Account account)
    {
label0:
        {
            if (!"com.google".equals(account.type))
            {
                break label0;
            }
            ImmutableList immutablelist = (ImmutableList)GOOGLE_CONSUMER_ACCOUNT_DOMAINS;
            int j = immutablelist.size();
            Object obj = (UnmodifiableIterator)null;
            int i = 0;
            do
            {
                if (i >= j)
                {
                    break label0;
                }
                obj = immutablelist.get(i);
                i++;
                obj = (String)obj;
            } while (!account.name.endsWith(((String) (obj))));
            return true;
        }
        return false;
    }

    public static boolean isNotFoundException(HttpResponseException httpresponseexception)
    {
        return httpresponseexception.statusCode == 404;
    }

    public static boolean isPermanentException(HttpResponseException httpresponseexception)
    {
        int i = httpresponseexception.statusCode;
        return i >= 400 && i < 500;
    }

    public static String parseFeedId(String s)
    {
        String s1;
        if (s == null)
        {
            s1 = null;
        } else
        {
            s1 = s;
            if (s.startsWith("http"))
            {
                String as[] = s.split("/");
                s1 = s;
                if (as.length > 5)
                {
                    s1 = s;
                    if ("feeds".equals(as[4]))
                    {
                        s = Uri.decode(as[5]);
                        LogUtils.d(TAG, "Changed feedId -> %s", new Object[] {
                            s
                        });
                        return s;
                    }
                }
            }
        }
        return s1;
    }

    public static String replaceFeedIdWith(String s, String s1)
    {
        String s2;
        if (s == null)
        {
            s2 = null;
        } else
        {
            s2 = s1;
            if (s.startsWith("http"))
            {
                s = s.split("/");
                s2 = s1;
                if (s.length > 5)
                {
                    s2 = s1;
                    if ("feeds".equals(s[4]))
                    {
                        s[5] = s1;
                        return TextUtils.join("/", s);
                    }
                }
            }
        }
        return s2;
    }

    public static void requestMetaFeedSync(Account account)
    {
        Bundle bundle = new Bundle();
        bundle.putBoolean("force", true);
        bundle.putBoolean("metafeedonly", true);
        SyncUtil.requestSync(account, android.provider.CalendarContract.Calendars.CONTENT_URI.getAuthority(), bundle);
    }

    static String sanitizeRecurrence(String s)
    {
        if (s == null)
        {
            return null;
        }
        Object obj = new EventRecurrence();
        try
        {
            ((EventRecurrence) (obj)).parse(s);
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            LogUtils.e(TAG, ((Throwable) (obj)), "Invalid recurrence rule: %s", new Object[] {
                s
            });
            return null;
        }
        s = ((EventRecurrence) (obj)).until;
        if (s != null)
        {
            Time time = new Time();
            try
            {
                time.parse(s);
                if (time.year >= 2038)
                {
                    obj.until = null;
                }
            }
            // Misplaced declaration of an exception variable
            catch (String s)
            {
                LogUtils.e(TAG, s, "Invalid recurrence until time", new Object[0]);
                obj.until = null;
            }
        }
        return ((EventRecurrence) (obj)).toString();
    }

    public static boolean writeToFile(Context context, String s, String s1, int i)
    {
        Context context1;
        Context context2;
        boolean flag;
        flag = false;
        context2 = null;
        context1 = null;
        context = context.openFileOutput(s, i);
        context1 = context;
        context2 = context;
        context.write(s1.getBytes());
        if (context != null)
        {
            try
            {
                context.close();
            }
            // Misplaced declaration of an exception variable
            catch (Context context)
            {
                LogUtils.e(TAG, context, "Saving report file", new Object[0]);
            }
        }
        flag = true;
_L2:
        return flag;
        context;
        context2 = context1;
        LogUtils.e(TAG, context, "Saving report file", new Object[0]);
        if (context1 == null) goto _L2; else goto _L1
_L1:
        try
        {
            context1.close();
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            LogUtils.e(TAG, context, "Saving report file", new Object[0]);
            return false;
        }
        return false;
        context;
        if (context2 != null)
        {
            try
            {
                context2.close();
            }
            // Misplaced declaration of an exception variable
            catch (String s)
            {
                LogUtils.e(TAG, s, "Saving report file", new Object[0]);
            }
        }
        throw context;
    }

}
