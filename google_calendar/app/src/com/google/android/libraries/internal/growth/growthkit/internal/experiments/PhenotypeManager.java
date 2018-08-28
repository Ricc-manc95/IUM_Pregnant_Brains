// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.experiments;

import android.content.Intent;

public interface PhenotypeManager
{

    public abstract void handlePhenotypeUpdateIntent(Intent intent);

    public abstract void register();
}
