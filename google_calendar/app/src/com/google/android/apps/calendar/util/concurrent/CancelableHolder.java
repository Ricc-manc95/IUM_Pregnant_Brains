// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.concurrent;

import java.util.concurrent.atomic.AtomicReference;

// Referenced classes of package com.google.android.apps.calendar.util.concurrent:
//            Cancelable

public final class CancelableHolder
{

    public final AtomicReference previousCancelableRef;

    public CancelableHolder()
    {
        previousCancelableRef = new AtomicReference(Cancelable.EMPTY);
    }
}
