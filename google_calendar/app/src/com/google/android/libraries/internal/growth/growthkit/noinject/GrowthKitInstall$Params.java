// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.noinject;

import android.content.Context;
import com.google.common.util.concurrent.ListeningExecutorService;
import org.chromium.net.CronetEngine;

public abstract class 
{

    public abstract String getApiKey();

    public abstract Context getContext();

    public abstract CronetEngine getCronetEngine();

    public abstract ListeningExecutorService getExecutorService();

    ()
    {
    }
}
