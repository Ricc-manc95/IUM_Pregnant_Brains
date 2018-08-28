// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.holidays;

import android.accounts.Account;
import com.google.android.apps.calendar.util.function.Consumer;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.android.calendar.settings.holidays:
//            HolidayPreferenceBinder, HolidayViewModel

final class arg._cls2
    implements Consumer
{

    private final HolidayPreferenceBinder arg$1;
    private final Account arg$2;

    public final void accept(Object obj)
    {
        HolidayPreferenceBinder holidaypreferencebinder = arg$1;
        Account account = arg$2;
        obj = (Set)obj;
        HolidayViewModel.applySubscriptions(account, (Set)holidaypreferencebinder.viewModel.countrySubscriptions.get(account), ((Set) (obj)));
    }

    (HolidayPreferenceBinder holidaypreferencebinder, Account account)
    {
        arg$1 = holidaypreferencebinder;
        arg$2 = account;
    }
}
