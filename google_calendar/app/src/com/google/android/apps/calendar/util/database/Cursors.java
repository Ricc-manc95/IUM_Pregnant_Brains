// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.database;

import android.database.Cursor;
import com.google.common.base.Function;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Cursors
{

    public static List apply(Cursor cursor, Extractor extractor, String s)
        throws IOException
    {
        if (cursor == null)
        {
            cursor = String.valueOf(s);
            if (cursor.length() != 0)
            {
                cursor = "Error reading data (null cursor): ".concat(cursor);
            } else
            {
                cursor = new String("Error reading data (null cursor): ");
            }
            throw new IOException(cursor);
        }
        s = new ArrayList(cursor.getCount());
        for (; cursor.moveToNext(); s.add(extractor.extract(cursor))) { }
        break MISSING_BLOCK_LABEL_94;
        extractor;
        cursor.close();
        throw extractor;
        cursor.close();
        return s;
    }

    public static List apply(Cursor cursor, Function function)
    {
        if (cursor == null)
        {
            return null;
        }
        ArrayList arraylist;
        arraylist = new ArrayList(cursor.getCount());
        for (; cursor.moveToNext(); arraylist.add(function.apply(cursor))) { }
        break MISSING_BLOCK_LABEL_55;
        function;
        cursor.close();
        throw function;
        cursor.close();
        return arraylist;
    }

    public static int count(Cursor cursor, String s)
        throws IOException
    {
        if (cursor == null)
        {
            cursor = String.valueOf(s);
            if (cursor.length() != 0)
            {
                cursor = "Error reading data (null cursor): ".concat(cursor);
            } else
            {
                cursor = new String("Error reading data (null cursor): ");
            }
            throw new IOException(cursor);
        }
        int i = cursor.getCount();
        cursor.close();
        return i;
        s;
        cursor.close();
        throw s;
    }

    public static Object extractSingleEntry(Cursor cursor, Extractor extractor, String s)
        throws IOException
    {
        if (cursor == null)
        {
            cursor = String.valueOf(s);
            if (cursor.length() != 0)
            {
                cursor = "Error reading data (null cursor): ".concat(cursor);
            } else
            {
                cursor = new String("Error reading data (null cursor): ");
            }
            throw new IOException(cursor);
        }
        if (cursor.getCount() > 1)
        {
            throw new IOException(String.format(null, "Error reading data (too many results [%d]): %s", new Object[] {
                Integer.valueOf(cursor.getCount()), s
            }));
        }
        break MISSING_BLOCK_LABEL_98;
        extractor;
        cursor.close();
        throw extractor;
        int i = cursor.getCount();
        if (i == 0)
        {
            cursor.close();
            return null;
        }
        if (!cursor.moveToFirst())
        {
            extractor = String.valueOf(s);
            if (extractor.length() != 0)
            {
                extractor = "Error reading data (null cursor): ".concat(extractor);
            } else
            {
                extractor = new String("Error reading data (null cursor): ");
            }
            throw new IOException(extractor);
        }
        extractor = ((Extractor) (extractor.extract(cursor)));
        cursor.close();
        return extractor;
    }

    private class Extractor
    {

        public abstract Object extract(Cursor cursor)
            throws IOException;
    }

}
