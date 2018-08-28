// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.syncadapters.timely.sql;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.android.calendarcommon2.LogUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class SQLiteDatabaseUtils
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/apps/calendar/syncadapters/timely/sql/SQLiteDatabaseUtils);

    public SQLiteDatabaseUtils()
    {
    }

    public static int deleteAll(SQLiteDatabase sqlitedatabase, String s, String s1, String as[], String s2, Iterable iterable)
    {
        int i;
        sqlitedatabase.beginTransaction();
        i = 0;
        ArrayList arraylist;
        int j;
        arraylist = new ArrayList();
        iterable = iterable.iterator();
        do
        {
            if (!iterable.hasNext())
            {
                break;
            }
            arraylist.add(String.valueOf(iterable.next()));
            if (arraylist.size() == 50)
            {
                i = deleteAllInternal(sqlitedatabase, s, s1, as, s2, arraylist) + i;
                arraylist.clear();
            }
        } while (true);
        j = i;
        if (arraylist.size() > 0)
        {
            j = i + deleteAllInternal(sqlitedatabase, s, s1, as, s2, arraylist);
        }
        sqlitedatabase.setTransactionSuccessful();
        sqlitedatabase.endTransaction();
        return j;
        s;
        sqlitedatabase.endTransaction();
        throw s;
    }

    public static boolean deleteAllExcept(SQLiteDatabase sqlitedatabase, String s, String s1, Iterable iterable)
    {
        return deleteAllExcept(sqlitedatabase, s, s1, iterable, null, null);
    }

    public static boolean deleteAllExcept(SQLiteDatabase sqlitedatabase, String s, String s1, Iterable iterable, String s2, String as[])
    {
        Cursor cursor = sqlitedatabase.query(true, s, new String[] {
            s1
        }, s2, as, null, null, null, null);
        if (cursor == null)
        {
            break MISSING_BLOCK_LABEL_43;
        }
        boolean flag = cursor.moveToFirst();
        if (flag)
        {
            break MISSING_BLOCK_LABEL_57;
        }
        if (cursor != null)
        {
            cursor.close();
        }
        return false;
        HashSet hashset;
        hashset = new HashSet();
        do
        {
            hashset.add(cursor.getString(0));
        } while (cursor.moveToNext());
        cursor.close();
        break MISSING_BLOCK_LABEL_99;
        sqlitedatabase;
        cursor = null;
_L2:
        if (cursor != null)
        {
            cursor.close();
        }
        throw sqlitedatabase;
        if (cursor != null)
        {
            cursor.close();
        }
        for (iterable = iterable.iterator(); iterable.hasNext(); hashset.remove(String.valueOf(iterable.next()))) { }
        return deleteAll(sqlitedatabase, s, s2, as, s1, hashset) > 0;
        sqlitedatabase;
        if (true) goto _L2; else goto _L1
_L1:
    }

    private static int deleteAllInternal(SQLiteDatabase sqlitedatabase, String s, String s1, String as[], String s2, List list)
    {
        if (list.size() > 50)
        {
            LogUtils.wtf(TAG, "Attempt to delete data from database using in clause with more than %d arguments.", new Object[] {
                Integer.valueOf(list.size())
            });
        }
        LogUtils.v(TAG, "Deleting values {%s} from %s table.", new Object[] {
            TextUtils.join(", ", list), s
        });
        int i;
        if (!TextUtils.isEmpty(s1))
        {
            s2 = makeWhere(new String[] {
                s1, makeInClause(s2, list.size())
            });
            s1 = list;
            if (as != null)
            {
                s1 = new ArrayList();
                Collections.addAll(s1, as);
                s1.addAll(list);
            }
            list = new String[s1.size()];
            s1.toArray(list);
            as = s2;
            s1 = list;
        } else
        {
            as = makeInClause(s2, list.size());
            s1 = new String[list.size()];
            list.toArray(s1);
        }
        i = sqlitedatabase.delete(s, as, s1);
        LogUtils.v(TAG, "Deleted %d entries.", new Object[] {
            Integer.valueOf(i)
        });
        return i;
    }

    public static String makeInClause(String s, int i)
    {
        if (i == 0)
        {
            return "0<>0";
        }
        if (i == 1)
        {
            return String.valueOf(s).concat("=?");
        }
        if (i > 50)
        {
            LogUtils.wtf(TAG, "More than 50 host parameters when generating in clause", new Object[0]);
        }
        String s1 = TextUtils.join(",", Collections.nCopies(i, "?"));
        return (new StringBuilder(String.valueOf(s).length() + 6 + String.valueOf(s1).length())).append(s).append(" IN (").append(s1).append(")").toString();
    }

    public static String makeNot(String s)
    {
        return (new StringBuilder(String.valueOf(s).length() + 8)).append("(NOT (").append(s).append("))").toString();
    }

    public static transient String makeOrderBy(boolean flag, boolean flag1, String as[])
    {
        StringBuilder stringbuilder = new StringBuilder();
        for (int i = 0; i < as.length; i++)
        {
            if (i > 0)
            {
                stringbuilder.append(",");
            }
            stringbuilder.append(as[i]).append(" IS NULL DESC");
            stringbuilder.append(",");
            stringbuilder.append(as[i]).append(" ASC");
        }

        return stringbuilder.toString();
    }

    public static transient String makeWhere(String as[])
    {
        as = TextUtils.join(") AND (", as);
        return (new StringBuilder(String.valueOf(as).length() + 2)).append("(").append(as).append(")").toString();
    }

}
