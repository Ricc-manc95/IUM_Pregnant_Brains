// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.home;

import com.google.android.calendar.settings.birthdays.BirthdayViewModel;
import com.google.common.base.Supplier;

// Referenced classes of package com.google.android.calendar.settings.home:
//            HomeViewModel

final class arg._cls1
    implements Supplier
{

    private final HomeViewModel arg$1;

    public final Object get()
    {
        return arg$1.birthdayViewModel.color;
    }

    (HomeViewModel homeviewmodel)
    {
        arg$1 = homeviewmodel;
    }
}
