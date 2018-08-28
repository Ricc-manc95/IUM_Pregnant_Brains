// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.holidays;

import android.support.v14.preference.MultiSelectListPreference;
import android.support.v7.preference.Preference;
import com.google.android.apps.calendar.util.function.Consumer;
import java.util.HashSet;
import java.util.Set;

// Referenced classes of package com.google.android.calendar.settings.holidays:
//            HolidayPreferenceBinder

final class arg._cls4
    implements android.support.v7.preference.ner
{

    private final HolidayPreferenceBinder arg$1;
    private final Consumer arg$2;
    private final MultiSelectListPreference arg$3;
    private final int arg$4;

    public final boolean onPreferenceChange(Preference preference, Object obj)
    {
        preference = arg$1;
        Consumer consumer = arg$2;
        MultiSelectListPreference multiselectlistpreference = arg$3;
        int i = arg$4;
        obj = new HashSet(new com.google.common.collect.((Set)obj, new <init>(preference)));
        consumer.accept(obj);
        preference.setHolidaySummary(multiselectlistpreference, i, ((Set) (obj)));
        return true;
    }

    (HolidayPreferenceBinder holidaypreferencebinder, Consumer consumer, MultiSelectListPreference multiselectlistpreference, int i)
    {
        arg$1 = holidaypreferencebinder;
        arg$2 = consumer;
        arg$3 = multiselectlistpreference;
        arg$4 = i;
    }
}
