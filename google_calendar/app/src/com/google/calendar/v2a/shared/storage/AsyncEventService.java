// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.v2a.shared.storage;

import com.google.calendar.v2a.shared.storage.proto.GetEventRequest;
import com.google.calendar.v2a.shared.storage.proto.GetEventsRequest;
import com.google.common.util.concurrent.ListenableFuture;

public interface AsyncEventService
{

    public abstract ListenableFuture getEvent(GetEventRequest geteventrequest);

    public abstract ListenableFuture getEvents(GetEventsRequest geteventsrequest);
}
