// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.api;

import com.google.common.util.concurrent.FluentFuture;

public interface ItemProvider
{

    public abstract FluentFuture loadItems(int i, int j);
}
