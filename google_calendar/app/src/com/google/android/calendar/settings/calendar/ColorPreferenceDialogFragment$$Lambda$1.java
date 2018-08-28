// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.calendar;

import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import com.google.android.apps.calendar.util.function.Consumer;

// Referenced classes of package com.google.android.calendar.settings.calendar:
//            ColorPreferenceDialogFragment

final class arg._cls1
    implements Consumer
{

    private final ColorPreferenceDialogFragment arg$1;

    public final void accept(Object obj)
    {
        ColorPreferenceDialogFragment colorpreferencedialogfragment = arg$1;
        obj = (String)obj;
        colorpreferencedialogfragment.getDialog().hide();
    }

    (ColorPreferenceDialogFragment colorpreferencedialogfragment)
    {
        arg$1 = colorpreferencedialogfragment;
    }
}
