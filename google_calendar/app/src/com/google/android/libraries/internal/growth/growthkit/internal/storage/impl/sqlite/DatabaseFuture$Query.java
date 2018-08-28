// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite;

import android.database.Cursor;
import android.os.CancellationSignal;

public interface 
{

    public abstract String debugQueryAsString();

    public abstract Cursor query16(CancellationSignal cancellationsignal);
}
