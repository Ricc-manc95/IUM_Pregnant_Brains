// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.battery;

import android.content.SharedPreferences;
import com.google.android.libraries.performance.primes.persistent.PersistentStorage;

public final class StatsStorage
{

    public final PersistentStorage storage;

    public StatsStorage(SharedPreferences sharedpreferences)
    {
        storage = new PersistentStorage(sharedpreferences);
    }
}
