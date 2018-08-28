// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.timezonepicker.fullscreen;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import com.android.timezonepicker.TimeZoneLoader;
import com.android.timezonepicker.TimeZoneManager;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;

// Referenced classes of package com.android.timezonepicker.fullscreen:
//            TimeZoneFullScreenAdapter, TimeZoneToolbarController

public final class TimeZonePickerInitializer
{

    TimeZonePickerInitializer(Listener listener, View view, int i, int j, long l, List list)
    {
        android.content.Context context = view.getContext();
        list = new TimeZoneManager(list, new TimeZoneLoader(context, l));
        TimeZoneFullScreenAdapter timezonefullscreenadapter = new TimeZoneFullScreenAdapter(context, list, listener, l);
        Object obj = (ListView)view.findViewById(0x7f100386);
        ((ListView) (obj)).setAdapter(timezonefullscreenadapter);
        ((ListView) (obj)).setOnItemClickListener(timezonefullscreenadapter);
        obj = (Toolbar)view.findViewById(0x7f100387);
        listener = new TimeZoneToolbarController..Lambda._cls0(listener);
        ((Toolbar) (obj)).ensureNavButtonView();
        ((Toolbar) (obj)).mNavButtonView.setOnClickListener(listener);
        new TimeZoneToolbarController(context, ((Toolbar) (obj)), (EditText)((Toolbar) (obj)).findViewById(0x7f100389), (ImageButton)((Toolbar) (obj)).findViewById(0x7f10038a), i, j, list);
        view = view.findViewById(0x7f100385);
        list.listener = timezonefullscreenadapter;
        listener = (FluentFuture)CalendarExecutor.DISK.submit(new com.android.timezonepicker.TimeZoneManager..Lambda._cls0(list));
        view = new com.android.timezonepicker.TimeZoneManager._cls1(list, view);
        list = CalendarExecutor.MAIN;
        if (view == null)
        {
            throw new NullPointerException();
        } else
        {
            listener.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(listener, view), list);
            return;
        }
    }
}
