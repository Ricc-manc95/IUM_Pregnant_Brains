// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import android.os.Parcelable;

// Referenced classes of package com.google.android.calendar.api.event:
//            EventKey

public interface EventDescriptor
    extends Parcelable
{

    public abstract EventKey getKey();

    public abstract long getOriginalStart();

    public abstract boolean isCommitted();

    public abstract boolean isRecurring();

    public abstract boolean isRecurringException();

    public abstract boolean isRecurringPhantom();
}
