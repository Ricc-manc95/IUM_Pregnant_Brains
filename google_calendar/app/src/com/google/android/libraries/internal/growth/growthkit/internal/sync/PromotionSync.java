// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.sync;

import com.google.common.util.concurrent.ListenableFuture;

public interface PromotionSync
{

    public abstract ListenableFuture syncAllAccounts();
}