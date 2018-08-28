// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.proposenewtime;

import android.support.v4.app.Fragment;
import com.google.android.calendar.Utils;
import com.google.common.base.Supplier;

// Referenced classes of package com.google.android.apps.calendar.proposenewtime:
//            ProposeNewTimeFragment

final class arg._cls1
    implements Supplier
{

    private final ProposeNewTimeFragment arg$1;

    public final Object get()
    {
        return Utils.getTimeZone(arg$1.getContext());
    }

    (ProposeNewTimeFragment proposenewtimefragment)
    {
        arg$1 = proposenewtimefragment;
    }
}
