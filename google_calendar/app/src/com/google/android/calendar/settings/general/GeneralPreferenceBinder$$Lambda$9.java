// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.general;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.apps.calendar.util.function.Consumer;

// Referenced classes of package com.google.android.calendar.settings.general:
//            GeneralPreferenceViewModel, GeneralPreferenceBinder

final class arg._cls2
    implements Consumer
{

    private final GeneralPreferenceBinder arg$1;
    private final GeneralPreferenceViewModel arg$2;

    public final void accept(Object obj)
    {
        GeneralPreferenceBinder generalpreferencebinder = arg$1;
        GeneralPreferenceViewModel generalpreferenceviewmodel = arg$2;
        boolean flag = ((Boolean)obj).booleanValue();
        if (flag != generalpreferenceviewmodel.useStandardTone)
        {
            generalpreferenceviewmodel.useStandardTone = flag;
            generalpreferenceviewmodel.context.getSharedPreferences("com.google.android.calendar_preferences", 0).edit().n("preferences_use_standard_tone", generalpreferenceviewmodel.useStandardTone).useStandardTone();
        }
        generalpreferencebinder.bindNotifications(generalpreferenceviewmodel);
    }

    (GeneralPreferenceBinder generalpreferencebinder, GeneralPreferenceViewModel generalpreferenceviewmodel)
    {
        arg$1 = generalpreferencebinder;
        arg$2 = generalpreferenceviewmodel;
    }
}
