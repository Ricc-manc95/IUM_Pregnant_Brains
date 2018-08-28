// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.CancellationSignal;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite:
//            AsyncSQLiteDatabase

public final class ery extends ery
{

    private final AsyncSQLiteDatabase this$0;
    private final String val$query;
    private final Object val$selectionArgs[];

    public final String debugQueryAsString()
    {
        return val$query;
    }

    public final Cursor query16(CancellationSignal cancellationsignal)
    {
        return db.rawQueryWithFactory(new gumentBindingCursorFactory(val$selectionArgs), val$query, null, null, cancellationsignal);
    }

    public gumentBindingCursorFactory()
    {
        this$0 = final_asyncsqlitedatabase;
        val$selectionArgs = aobj;
        val$query = String.this;
        super(final_asyncsqlitedatabase);
    }
}
