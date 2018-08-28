// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.events.impl;

import java.util.concurrent.Callable;
import javax.inject.Provider;

final class arg._cls1
    implements Callable
{

    private final Provider arg$1;

    public final Object call()
    {
        return arg$1.get();
    }

    (Provider provider)
    {
        arg$1 = provider;
    }
}
