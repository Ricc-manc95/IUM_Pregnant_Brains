// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.events;

import com.google.common.util.concurrent.ListenableFuture;

public interface GrowthKitEventManager
{

    public abstract ListenableFuture reportClearCutEventAsync(int i, int j, String s);
}