// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite;

import android.database.Cursor;
import com.google.common.util.concurrent.AbstractFuture;
import java.io.Closeable;
import java.io.IOException;

public abstract class DatabaseFuture extends AbstractFuture
    implements Runnable
{

    private final Query query;

    DatabaseFuture(Query query1)
    {
        query = query1;
    }

    public static DatabaseFuture create(Query query1)
    {
        return new InternalDatabaseFuture16(query1);
    }

    protected final void initAndSetCursor(Cursor cursor)
    {
        if (isCancelled() || cursor == null)
        {
            break MISSING_BLOCK_LABEL_18;
        }
        cursor.getCount();
        if (set(cursor) || cursor == null)
        {
            break MISSING_BLOCK_LABEL_36;
        }
        cursor.close();
_L2:
        return;
        Object obj;
        obj;
        setException(((Throwable) (obj)));
        if (set(cursor) || cursor == null) goto _L2; else goto _L1
_L1:
        try
        {
            cursor.close();
            return;
        }
        // Misplaced declaration of an exception variable
        catch (Cursor cursor)
        {
            return;
        }
        obj;
        if (!set(cursor) && cursor != null)
        {
            try
            {
                cursor.close();
            }
            // Misplaced declaration of an exception variable
            catch (Cursor cursor) { }
        }
        throw obj;
        cursor;
    }

    public final void run()
    {
        if (isCancelled())
        {
            return;
        }
        String s = String.valueOf(query.debugQueryAsString());
        if (s.length() == 0)
        {
            break MISSING_BLOCK_LABEL_52;
        }
        "Query: ".concat(s);
_L1:
        Throwable throwable;
        runInternal(query);
        return;
        try
        {
            new String("Query: ");
        }
        // Misplaced declaration of an exception variable
        catch (Throwable throwable)
        {
            setException(throwable);
            return;
        }
          goto _L1
    }

    protected abstract void runInternal(Query query1);

    private class InternalDatabaseFuture16 extends DatabaseFuture
        implements android.os.CancellationSignal.OnCancelListener
    {

        private final CancellationSignal sig = new CancellationSignal();

        public final boolean cancel(boolean flag)
        {
            sig.cancel();
            return cancel(flag);
        }

        public final void onCancel()
        {
            cancel(true);
        }

        protected final void runInternal(Query query1)
        {
            try
            {
                sig.setOnCancelListener(this);
                initAndSetCursor(query1.query16(sig));
                return;
            }
            // Misplaced declaration of an exception variable
            catch (Query query1)
            {
                cancel(true);
            }
        }

        InternalDatabaseFuture16(Query query1)
        {
            super(query1);
        }
    }


    private class Query
    {

        public abstract String debugQueryAsString();

        public abstract Cursor query16(CancellationSignal cancellationsignal);
    }

}
