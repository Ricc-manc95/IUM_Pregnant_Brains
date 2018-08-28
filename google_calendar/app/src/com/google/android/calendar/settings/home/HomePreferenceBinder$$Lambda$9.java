// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.home;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.InsetDrawable;
import android.support.v4.app.Fragment;
import android.support.v7.preference.Preference;
import com.google.android.calendar.api.color.EntityColor;
import com.google.android.calendar.common.drawable.ColorCircle;
import com.google.common.base.Supplier;
import java.util.concurrent.atomic.AtomicReference;

// Referenced classes of package com.google.android.calendar.settings.home:
//            HomePreferenceBinder

final class arg._cls4
    implements Runnable
{

    private final HomePreferenceBinder arg$1;
    private final Supplier arg$2;
    private final AtomicReference arg$3;
    private final Preference arg$4;

    public final void run()
    {
        Object obj = arg$1;
        Object obj1 = arg$2;
        Object obj2 = arg$3;
        Preference preference = arg$4;
        obj1 = (EntityColor)((Supplier) (obj1)).get();
        obj2 = (EntityColor)((AtomicReference) (obj2)).getAndSet(obj1);
        if (obj2 == null || ((EntityColor) (obj2)).getValue() != ((EntityColor) (obj1)).getValue())
        {
            obj = ((HomePreferenceBinder) (obj)).fragment.requireContext().getResources();
            int i = ((EntityColor) (obj1)).getValue();
            preference.setIcon(new InsetDrawable((new ColorCircle(((Resources) (obj)), 0x7f0e00af)).setColor(i), ((Resources) (obj)).getDimensionPixelOffset(0x7f0e00b0)));
        }
    }

    (HomePreferenceBinder homepreferencebinder, Supplier supplier, AtomicReference atomicreference, Preference preference)
    {
        arg$1 = homepreferencebinder;
        arg$2 = supplier;
        arg$3 = atomicreference;
        arg$4 = preference;
    }
}
