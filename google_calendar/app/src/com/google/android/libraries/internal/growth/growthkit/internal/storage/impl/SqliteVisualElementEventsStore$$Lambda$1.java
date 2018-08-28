// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl;

import com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.SafeSQLiteQueryBuilder;
import com.google.common.base.Function;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl:
//            SqliteVisualElementEventsStore

final class arg._cls2
    implements Function
{

    private final SqliteVisualElementEventsStore arg$1;
    private final Iterator arg$2;

    public final Object apply(Object obj)
    {
        SqliteVisualElementEventsStore sqlitevisualelementeventsstore = arg$1;
        Iterator iterator = arg$2;
        SafeSQLiteQueryBuilder safesqlitequerybuilder = (SafeSQLiteQueryBuilder)obj;
        if (iterator.hasNext())
        {
            safesqlitequerybuilder.builder.append(" WHERE (account = ?");
            obj = sqlitevisualelementeventsstore.accountName;
            if (obj == null)
            {
                obj = "signedout";
            }
            safesqlitequerybuilder.args.add(obj);
            safesqlitequerybuilder.builder.append(" AND (");
            SqliteVisualElementEventsStore.appendWherePart(safesqlitequerybuilder, (com.google.identity.growth.proto.pl.sqlite.SafeSQLiteQueryBuilder)iterator.next());
            for (; iterator.hasNext(); SqliteVisualElementEventsStore.appendWherePart(safesqlitequerybuilder, (com.google.identity.growth.proto.pl.sqlite.SafeSQLiteQueryBuilder)iterator.next()))
            {
                safesqlitequerybuilder.builder.append(" OR ");
            }

            safesqlitequerybuilder.builder.append("))");
        }
        return null;
    }

    (SqliteVisualElementEventsStore sqlitevisualelementeventsstore, Iterator iterator)
    {
        arg$1 = sqlitevisualelementeventsstore;
        arg$2 = iterator;
    }
}
