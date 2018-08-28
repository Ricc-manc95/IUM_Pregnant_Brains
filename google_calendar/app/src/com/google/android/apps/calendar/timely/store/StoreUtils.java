// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timely.store;

import android.content.ContentProviderClient;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.syncadapters.timely.sql.SQLiteDatabaseUtils;
import com.google.common.collect.NaturalOrdering;
import com.google.common.collect.Ordering;
import java.util.Arrays;
import java.util.HashSet;

public final class StoreUtils
{

    private static int compare(Cursor cursor, Cursor cursor1, int i)
    {
        int j = 1;
_L8:
        if (j >= cursor.getColumnCount()) goto _L2; else goto _L1
_L1:
        cursor.getType(j);
        JVM INSTR tableswitch 1 2: default 40
    //                   1 70
    //                   2 103;
           goto _L3 _L4 _L5
_L3:
        i = NaturalOrdering.INSTANCE.nullsFirst().compare(cursor.getString(j), cursor1.getString(j));
_L7:
        if (i != 0)
        {
            return i;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        i = NaturalOrdering.INSTANCE.nullsFirst().compare(Long.valueOf(cursor.getLong(j)), Long.valueOf(cursor1.getLong(j)));
        continue; /* Loop/switch isn't completed */
_L5:
        i = NaturalOrdering.INSTANCE.nullsFirst().compare(Float.valueOf(cursor.getFloat(j)), Float.valueOf(cursor1.getFloat(j)));
        if (true) goto _L7; else goto _L6
_L6:
        j++;
          goto _L8
_L2:
        return 0;
    }

    public static int deleteObsoleteRowsFromDatabase(SQLiteDatabase sqlitedatabase, String s, String s1, String as[], ContentProviderClient contentproviderclient, Uri uri, String s2, String as1[])
    {
        if (as.length == as1.length) goto _L2; else goto _L1
_L1:
        int i;
        LogUtils.wtf("StoreUtils", "Number of columns to query should be the same for database and Calendar Provider.", new Object[0]);
        i = -2;
_L5:
        return i;
_L2:
        String as2[] = new String[as.length + 1];
        as2[0] = s1;
        System.arraycopy(as, 0, as2, 1, as.length);
        as = sqlitedatabase.query(s, as2, null, null, null, null, SQLiteDatabaseUtils.makeOrderBy(true, true, as));
        as2 = new String[as1.length + 1];
        as2[0] = s2;
        System.arraycopy(as1, 0, as2, 1, as1.length);
        contentproviderclient = contentproviderclient.query(uri, as2, null, null, SQLiteDatabaseUtils.makeOrderBy(true, true, as1));
        uri = new HashSet();
        if (as != null) goto _L4; else goto _L3
_L3:
        uri = new String[0];
_L6:
        int j = SQLiteDatabaseUtils.deleteAll(sqlitedatabase, s, null, null, s1, Arrays.asList(uri));
        if (as != null)
        {
            as.close();
        }
        i = j;
        if (contentproviderclient != null)
        {
            contentproviderclient.close();
            return j;
        }
          goto _L5
_L4:
        if (contentproviderclient != null)
        {
            break MISSING_BLOCK_LABEL_237;
        }
        if (as.moveToFirst())
        {
            do
            {
                uri.add(as.getString(0));
            } while (as.moveToNext());
        }
        uri = (String[])uri.toArray(new String[uri.size()]);
          goto _L6
        if (!as.moveToFirst() || !contentproviderclient.moveToFirst()) goto _L8; else goto _L7
_L7:
        if (as.isAfterLast() || contentproviderclient.isAfterLast()) goto _L8; else goto _L9
_L9:
        for (; !as.isAfterLast() && compare(as, contentproviderclient, 1) < 0; as.moveToNext())
        {
            uri.add(as.getString(0));
        }

          goto _L10
        uri;
        sqlitedatabase = contentproviderclient;
        s1 = as;
        as = uri;
_L13:
        LogUtils.e("StoreUtils", as, "Exhaustive cleanup of events in %s has failed.", new Object[] {
            s
        });
        if (s1 != null)
        {
            s1.close();
        }
        if (sqlitedatabase != null)
        {
            sqlitedatabase.close();
        }
        return -1;
_L10:
        for (; !as.isAfterLast() && !contentproviderclient.isAfterLast() && compare(as, contentproviderclient, 1) == 0; as.moveToNext()) { }
          goto _L11
        sqlitedatabase;
        s = contentproviderclient;
_L12:
        if (as != null)
        {
            as.close();
        }
        if (s != null)
        {
            s.close();
        }
        throw sqlitedatabase;
_L11:
        while (!as.isAfterLast() && !contentproviderclient.isAfterLast() && compare(as, contentproviderclient, 1) > 0) 
        {
            contentproviderclient.moveToNext();
        }
          goto _L7
_L8:
        for (; !as.isAfterLast(); as.moveToNext())
        {
            uri.add(as.getString(0));
        }

        uri = (String[])uri.toArray(new String[uri.size()]);
          goto _L6
        sqlitedatabase;
        s = null;
        as = null;
          goto _L12
        sqlitedatabase;
        s = null;
          goto _L12
        as;
        s = sqlitedatabase;
        sqlitedatabase = as;
        as = s1;
          goto _L12
        as;
        sqlitedatabase = null;
        s1 = null;
          goto _L13
        contentproviderclient;
        sqlitedatabase = null;
        s1 = as;
        as = contentproviderclient;
          goto _L13
    }
}
