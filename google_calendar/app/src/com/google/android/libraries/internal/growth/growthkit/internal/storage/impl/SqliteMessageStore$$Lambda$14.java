// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteDatabase;
import com.google.protobuf.MessageLite;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl:
//            SqliteMessageStore

final class arg._cls3
    implements com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.n
{

    private final SqliteMessageStore arg$1;
    private final String arg$2;
    private final MessageLite arg$3;

    public final void execute(com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.Database database)
    {
        Object obj = arg$1;
        String s = arg$2;
        MessageLite messagelite = arg$3;
        ContentValues contentvalues = new ContentValues(3);
        contentvalues.put("account", ((SqliteMessageStore) (obj)).accountName);
        contentvalues.put("key", s);
        contentvalues.put("value", messagelite.toByteArray());
        obj = ((SqliteMessageStore) (obj)).tableName;
        AsyncSQLiteDatabase.checkInterrupt();
        if (database.db.insertWithOnConflict(((String) (obj)), null, contentvalues, 5) == -1L)
        {
            throw new SQLException("Failed to put() to DB.");
        } else
        {
            return;
        }
    }

    cSqliteDatabase(SqliteMessageStore sqlitemessagestore, String s, MessageLite messagelite)
    {
        arg$1 = sqlitemessagestore;
        arg$2 = s;
        arg$3 = messagelite;
    }
}
