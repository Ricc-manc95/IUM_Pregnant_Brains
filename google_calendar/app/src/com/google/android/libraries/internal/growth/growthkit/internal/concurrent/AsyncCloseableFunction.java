// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.concurrent;


// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.concurrent:
//            AsyncCloseable

public interface AsyncCloseableFunction
{

    public abstract AsyncCloseable apply(Object obj)
        throws Exception;
}