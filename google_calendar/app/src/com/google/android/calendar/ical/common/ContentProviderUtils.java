// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.ical.common;

import com.google.common.base.Function;
import java.util.Collection;
import java.util.Iterator;

public final class ContentProviderUtils
{

    public static final String EXTENDED_PROPERTIES_PROJECTION[] = {
        "event_id", "name", "value"
    };

    public static String makePropertyValueSelectionString(Collection collection)
    {
        class .Lambda._cls1
            implements Function
        {

            public static final Function $instance = new .Lambda._cls1();

            public final Object apply(Object obj)
            {
                return DatabaseUtils.sqlEscapeString((String)obj);
            }


            private .Lambda._cls1()
            {
            }
        }

        Function function = .Lambda._cls1..instance;
        if (collection.isEmpty())
        {
            return "0<>0";
        }
        if (collection.size() == 1)
        {
            collection = (String)function.apply(collection.iterator().next());
            return (new StringBuilder(String.valueOf("value").length() + 3 + String.valueOf(collection).length())).append("value").append(" = ").append(collection).toString();
        }
        StringBuilder stringbuilder = new StringBuilder(String.valueOf("value").concat(" IN ("));
        for (collection = collection.iterator(); collection.hasNext(); stringbuilder.append(","))
        {
            stringbuilder.append((String)function.apply(collection.next()));
        }

        stringbuilder.deleteCharAt(stringbuilder.length() - 1);
        stringbuilder.append(")");
        return stringbuilder.toString();
    }

    public static String makeSyncData1SelectionString(Collection collection)
    {
        Function function = .Lambda._cls1..instance;
        if (collection.isEmpty())
        {
            return "0<>0";
        }
        if (collection.size() == 1)
        {
            collection = (String)function.apply(collection.iterator().next());
            return (new StringBuilder(String.valueOf("sync_data1").length() + 3 + String.valueOf(collection).length())).append("sync_data1").append(" = ").append(collection).toString();
        }
        StringBuilder stringbuilder = new StringBuilder(String.valueOf("sync_data1").concat(" IN ("));
        for (collection = collection.iterator(); collection.hasNext(); stringbuilder.append(","))
        {
            stringbuilder.append((String)function.apply(collection.next()));
        }

        stringbuilder.deleteCharAt(stringbuilder.length() - 1);
        stringbuilder.append(")");
        return stringbuilder.toString();
    }

}
