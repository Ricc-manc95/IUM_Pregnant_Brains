// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import android.content.Context;
import com.google.android.calendar.api.calendarlist.CalendarKey;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Collection;

// Referenced classes of package com.google.android.calendar.api.event:
//            EventModifications, EventDescriptor, EventKey

public interface EventClient
{

    public abstract ListenableFuture create(EventModifications eventmodifications, GooglePrivateData.GuestNotification guestnotification);

    public abstract ListenableFuture delete(EventDescriptor eventdescriptor, int i, GooglePrivateData.GuestNotification guestnotification);

    public abstract ListenableFuture icsImport(EventModifications eventmodifications);

    public abstract ListenableFuture icsList(Collection collection);

    public abstract ListenableFuture icsUpdate(EventModifications eventmodifications, int i);

    public abstract void initialize(Context context);

    public abstract ListenableFuture read(EventDescriptor eventdescriptor);

    public abstract ListenableFuture read(EventKey eventkey);

    public abstract ListenableFuture readDescriptor(EventKey eventkey);

    public abstract ListenableFuture readGadgetPreferences(EventKey eventkey, CalendarKey calendarkey);

    public abstract ListenableFuture update(EventModifications eventmodifications, int i, GooglePrivateData.GuestNotification guestnotification);
}
