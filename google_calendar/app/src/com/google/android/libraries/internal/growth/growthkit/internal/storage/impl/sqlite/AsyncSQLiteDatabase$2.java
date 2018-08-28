// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite;

import android.database.sqlite.SQLiteDatabase;
import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite:
//            AsyncSQLiteDatabase

public final class val.whereArgs
    implements Callable
{

    private final AsyncSQLiteDatabase this$0;
    private final String val$table;
    private final String val$whereArgs[];
    private final String val$whereClause;

    public final Object call()
        throws Exception
    {
        return Integer.valueOf(db.delete(val$table, val$whereClause, val$whereArgs));
    }

    public ()
    {
        this$0 = final_asyncsqlitedatabase;
        val$table = s;
        val$whereClause = s1;
        val$whereArgs = _5B_Ljava.lang.String_3B_.this;
        super();
    }
}
