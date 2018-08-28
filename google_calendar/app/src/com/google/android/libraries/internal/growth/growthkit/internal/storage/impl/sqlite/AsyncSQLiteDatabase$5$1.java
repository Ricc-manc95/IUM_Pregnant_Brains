// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite;

import android.database.sqlite.SQLiteTransactionListener;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.testing.SQLiteTransactionRolledBackException;

final class this._cls1
    implements SQLiteTransactionListener
{

    private final nRolledBackException this$1;

    public final void onBegin()
    {
    }

    public final void onCommit()
    {
    }

    public final void onRollback()
    {
        if (!rewException)
        {
            throw new SQLiteTransactionRolledBackException();
        } else
        {
            return;
        }
    }

    nRolledBackException()
    {
        this$1 = this._cls1.this;
        super();
    }
}
