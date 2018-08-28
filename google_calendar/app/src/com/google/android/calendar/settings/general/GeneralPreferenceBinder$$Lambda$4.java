// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.general;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.common.base.Supplier;

// Referenced classes of package com.google.android.calendar.settings.general:
//            GeneralPreferenceViewModel

final class arg._cls1
    implements Supplier
{

    private final GeneralPreferenceViewModel arg$1;

    public final Object get()
    {
        return Boolean.valueOf(arg$1.context.getSharedPreferences("com.google.android.calendar_preferences", 0).getBoolean("preference_showMoreEvents", false));
    }

    (GeneralPreferenceViewModel generalpreferenceviewmodel)
    {
        arg$1 = generalpreferenceviewmodel;
    }
}
