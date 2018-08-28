// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar.timely.consistency;

import com.google.api.services.calendar.model.Event;
import java.util.List;

interface t
{

    public abstract t classify(List list, Event event, Event event1);
}
