// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.concurrent;

import com.google.android.apps.calendar.util.function.Consumer;
import java.util.concurrent.Executor;

// Referenced classes of package com.google.android.apps.calendar.util.concurrent:
//            Subscription

public interface Observable
{

    public abstract Subscription subscribe(Consumer consumer, Executor executor, boolean flag);
}
