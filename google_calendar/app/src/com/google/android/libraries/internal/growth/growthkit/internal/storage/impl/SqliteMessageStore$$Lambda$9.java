// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl;

import com.google.android.libraries.internal.growth.growthkit.internal.concurrent.AsyncCloseable;
import com.google.android.libraries.internal.growth.growthkit.internal.concurrent.AsyncCloseableFunction;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteDatabase;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.DatabaseFuture;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.SafeSQLiteQueryBuilder;
import com.google.common.util.concurrent.AbstractTransformFuture;
import java.util.ArrayList;
import java.util.concurrent.Executor;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl:
//            SqliteMessageStore

final class arg._cls2
    implements AsyncCloseableFunction
{

    private final SqliteMessageStore arg$1;
    private final String arg$2;

    public final AsyncCloseable apply(Object obj)
    {
        Object obj2 = arg$1;
        String s = arg$2;
        Object obj1 = (AsyncSQLiteDatabase)obj;
        SafeSQLiteQueryBuilder safesqlitequerybuilder = new SafeSQLiteQueryBuilder();
        safesqlitequerybuilder.builder.append("SELECT value");
        obj = String.valueOf(((SqliteMessageStore) (obj2)).tableName);
        if (((String) (obj)).length() != 0)
        {
            obj = " FROM ".concat(((String) (obj)));
        } else
        {
            obj = new String(" FROM ");
        }
        safesqlitequerybuilder.builder.append(((String) (obj)));
        safesqlitequerybuilder.builder.append(" WHERE (key = ?");
        safesqlitequerybuilder.args.add(s);
        safesqlitequerybuilder.builder.append(" AND account = ?");
        obj = ((SqliteMessageStore) (obj2)).accountName;
        safesqlitequerybuilder.args.add(obj);
        safesqlitequerybuilder.builder.append(")");
        safesqlitequerybuilder.builder.append(" LIMIT 1");
        obj = safesqlitequerybuilder.build();
        obj2 = ((com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.LStatement) (obj)).query;
        obj = DatabaseFuture.create(new com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.LStatement.query(((AsyncSQLiteDatabase) (obj1)), ((com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.LStatement) (obj)).args, ((String) (obj2))));
        ((AsyncSQLiteDatabase) (obj1)).exec.execute(((Runnable) (obj)));
        obj1 = new com.google.android.libraries.internal.growth.growthkit.internal.concurrent.<init>();
        return new AsyncCloseable(AbstractTransformFuture.create(((com.google.common.util.concurrent.ListenableFuture) (obj)), new com.google.android.libraries.internal.growth.growthkit.internal.concurrent.t>(((com.google.android.libraries.internal.growth.growthkit.internal.concurrent.t>) (obj1)), ((com.google.common.util.concurrent.ListenableFuture) (obj))), com.google.common.util.concurrent.INSTANCE), ((com.google.android.libraries.internal.growth.growthkit.internal.concurrent.t>) (obj1)));
    }

    .SafeSQLStatement(SqliteMessageStore sqlitemessagestore, String s)
    {
        arg$1 = sqlitemessagestore;
        arg$2 = s;
    }
}
