// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.dnd;

import javax.inject.Provider;

public final class DragScrollPageControllerFactory
{

    public final Provider contextProvider;

    public DragScrollPageControllerFactory(Provider provider)
    {
        contextProvider = (Provider)checkNotNull(provider, 1);
    }

    public static Object checkNotNull(Object obj, int i)
    {
        if (obj == null)
        {
            throw new NullPointerException((new StringBuilder(93)).append("@AutoFactory method argument is null but is not marked @Nullable. Argument index: ").append(i).toString());
        } else
        {
            return obj;
        }
    }
}
