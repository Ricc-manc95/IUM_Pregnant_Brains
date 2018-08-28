// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl;

import com.google.android.libraries.internal.growth.growthkit.internal.concurrent.AsyncCloseable;
import com.google.android.libraries.internal.growth.growthkit.internal.concurrent.AsyncCloseableFunction;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteDatabase;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.DatabaseFuture;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.SafeSQLiteQueryBuilder;
import com.google.common.base.Function;
import com.google.common.util.concurrent.AbstractTransformFuture;
import java.util.concurrent.Executor;

final class arg._cls1
    implements AsyncCloseableFunction
{

    private final Function arg$1;

    public final AsyncCloseable apply(Object obj)
    {
        Object obj1 = arg$1;
        obj = (AsyncSQLiteDatabase)obj;
        Object obj2 = new SafeSQLiteQueryBuilder();
        ((SafeSQLiteQueryBuilder) (obj2)).builder.append("SELECT node_id_path,action, COUNT(*) as event_count");
        ((SafeSQLiteQueryBuilder) (obj2)).builder.append(" FROM visual_element_events_table");
        ((Function) (obj1)).apply(obj2);
        ((SafeSQLiteQueryBuilder) (obj2)).builder.append(" GROUP BY node_id_path,action");
        obj1 = ((SafeSQLiteQueryBuilder) (obj2)).build();
        obj2 = ((com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.) (obj1)).uery;
        obj1 = DatabaseFuture.create(new com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.uery(((AsyncSQLiteDatabase) (obj)), ((com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.uery) (obj1)).rgs, ((String) (obj2))));
        ((AsyncSQLiteDatabase) (obj)).exec.execute(((Runnable) (obj1)));
        obj = new com.google.android.libraries.internal.growth.growthkit.internal.concurrent.atement.args();
        return new AsyncCloseable(AbstractTransformFuture.create(((com.google.common.util.concurrent.ListenableFuture) (obj1)), new com.google.android.libraries.internal.growth.growthkit.internal.concurrent.atement.args(((com.google.android.libraries.internal.growth.growthkit.internal.concurrent.atement.args) (obj)), ((com.google.common.util.concurrent.ListenableFuture) (obj1))), com.google.common.util.concurrent.atement.args), ((com.google.android.libraries.internal.growth.growthkit.internal.concurrent.atement.args) (obj)));
    }

    ement(Function function)
    {
        arg$1 = function;
    }
}
