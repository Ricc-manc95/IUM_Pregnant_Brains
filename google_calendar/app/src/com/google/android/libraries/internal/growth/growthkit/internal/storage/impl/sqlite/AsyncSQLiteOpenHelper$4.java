// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite;

import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite:
//            AsyncSQLiteOpenHelper

final class this._cls0
    implements DatabaseErrorHandler
{

    private final AsyncSQLiteOpenHelper this$0;

    public final void onCorruption(SQLiteDatabase sqlitedatabase)
    {
        backUpDatabase(sqlitedatabase);
    }

    q()
    {
        this$0 = AsyncSQLiteOpenHelper.this;
        super();
    }
}
