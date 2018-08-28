// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl;

import com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.SafeSQLiteQueryBuilder;
import com.google.common.base.Function;
import java.util.ArrayList;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl:
//            SqliteVisualElementEventsStore

final class arg._cls1
    implements Function
{

    private final SqliteVisualElementEventsStore arg$1;

    public final Object apply(Object obj)
    {
        SqliteVisualElementEventsStore sqlitevisualelementeventsstore = arg$1;
        SafeSQLiteQueryBuilder safesqlitequerybuilder = (SafeSQLiteQueryBuilder)obj;
        safesqlitequerybuilder.builder.append(" WHERE (account = ?");
        obj = sqlitevisualelementeventsstore.accountName;
        if (obj == null)
        {
            obj = "signedout";
        }
        safesqlitequerybuilder.args.add(obj);
        safesqlitequerybuilder.builder.append(")");
        return null;
    }

    (SqliteVisualElementEventsStore sqlitevisualelementeventsstore)
    {
        arg$1 = sqlitevisualelementeventsstore;
    }
}
