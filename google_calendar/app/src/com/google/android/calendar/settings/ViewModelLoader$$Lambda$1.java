// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings;

import android.content.Context;
import com.google.android.calendar.settings.birthdays.BirthdayViewModel;
import com.google.android.calendar.settings.general.GeneralPreferenceViewModel;
import com.google.android.calendar.settings.holidays.HolidayViewModel;
import com.google.android.calendar.settings.home.HomeViewModel;
import com.google.android.calendar.settings.smartmail.SmartMailViewModel;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Arrays;

// Referenced classes of package com.google.android.calendar.settings:
//            SettingsShims

final class arg._cls5
    implements Function
{

    private final ListenableFuture arg$1;
    private final ListenableFuture arg$2;
    private final ListenableFuture arg$3;
    private final ListenableFuture arg$4;
    private final Context arg$5;

    public final Object apply(Object obj)
    {
        Object obj2 = arg$1;
        Object obj3 = arg$2;
        Object obj1 = arg$3;
        obj = arg$4;
        Context context = arg$5;
        obj2 = Arrays.asList((com.google.android.calendar.api.calendarlist.CalendarListEntry[])Futures.getUnchecked(((java.util.concurrent.Future) (obj2))));
        obj3 = (ImmutableMap)Futures.getUnchecked(((java.util.concurrent.Future) (obj3)));
        obj1 = (com.google.calendar.v2.libs.proto.ry)Futures.getUnchecked(((java.util.concurrent.Future) (obj1)));
        boolean flag = ((Boolean)Futures.getUnchecked(((java.util.concurrent.Future) (obj)))).booleanValue();
        return new HomeViewModel(context, ((java.util.List) (obj2)), ((ImmutableMap) (obj3)), SettingsShims.instance, new SmartMailViewModel(context, ((ImmutableMap) (obj3)), ((java.util.List) (obj2))), new BirthdayViewModel(context, ((ImmutableMap) (obj3))), new HolidayViewModel(context, ((ImmutableMap) (obj3)), ((com.google.calendar.v2.libs.proto.el) (obj1)), ((java.util.List) (obj2))), new GeneralPreferenceViewModel(context, ((ImmutableMap) (obj3)), flag, SettingsShims.instance.shouldShowFlingingSettings(context)));
    }

    iewModel(ListenableFuture listenablefuture, ListenableFuture listenablefuture1, ListenableFuture listenablefuture2, ListenableFuture listenablefuture3, Context context)
    {
        arg$1 = listenablefuture;
        arg$2 = listenablefuture1;
        arg$3 = listenablefuture2;
        arg$4 = listenablefuture3;
        arg$5 = context;
    }
}
