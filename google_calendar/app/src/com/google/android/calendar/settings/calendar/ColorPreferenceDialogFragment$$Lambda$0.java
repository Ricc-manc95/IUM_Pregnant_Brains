// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.calendar;

import android.support.v4.app.Fragment;
import com.google.common.base.Supplier;

// Referenced classes of package com.google.android.calendar.settings.calendar:
//            ColorPreferenceDialogFragment

final class arg._cls1
    implements Supplier
{

    private final ColorPreferenceDialogFragment arg$1;

    public final Object get()
    {
        ColorPreferenceDialogFragment colorpreferencedialogfragment = arg$1;
        if (((Fragment) (colorpreferencedialogfragment)).mLayoutInflater == null)
        {
            colorpreferencedialogfragment.mLayoutInflater = colorpreferencedialogfragment.onGetLayoutInflater(null);
            return ((Fragment) (colorpreferencedialogfragment)).mLayoutInflater;
        } else
        {
            return ((Fragment) (colorpreferencedialogfragment)).mLayoutInflater;
        }
    }

    (ColorPreferenceDialogFragment colorpreferencedialogfragment)
    {
        arg$1 = colorpreferencedialogfragment;
    }
}
