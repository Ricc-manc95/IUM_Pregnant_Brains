// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite:
//            SafeSQLiteQueryBuilder, AsyncSQLiteDatabase

public final class statement
    implements statement
{

    private final ent statement;

    public final void upgrade(se se)
        throws InterruptedException, SQLiteException
    {
        ent ent = statement;
        AsyncSQLiteDatabase.checkInterrupt();
        se.db.execSQL(ent.query, ent.args);
    }

    public se(String s)
    {
        SafeSQLiteQueryBuilder safesqlitequerybuilder = new SafeSQLiteQueryBuilder();
        safesqlitequerybuilder.builder.append(s);
        statement = safesqlitequerybuilder.build();
    }
}
