// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.v2a.shared;

import com.google.calendar.v2a.shared.storage.AsyncAccountService;
import com.google.calendar.v2a.shared.storage.AsyncEventService;

public interface AsyncSharedApi
{

    public abstract AsyncAccountService accountService();

    public abstract AsyncEventService eventService();
}
