// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.store;

import android.util.SparseArray;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.view.api.ItemAdapter;

final class CalendarWeekCache
{

    public final ItemAdapter adapter;
    public final com.google.android.apps.calendar.util.concurrent.CalendarExecutors.SerialExecutor serialExecutor;
    public final TimeUtils timeUtils;
    public final SparseArray weeks = new SparseArray();

    public CalendarWeekCache(com.google.android.apps.calendar.util.concurrent.CalendarExecutors.SerialExecutor serialexecutor, TimeUtils timeutils, ItemAdapter itemadapter)
    {
        serialExecutor = serialexecutor;
        timeUtils = timeutils;
        adapter = itemadapter;
    }
}
