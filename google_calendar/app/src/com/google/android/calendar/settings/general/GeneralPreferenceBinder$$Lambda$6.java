// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.general;

import com.google.common.base.Supplier;

// Referenced classes of package com.google.android.calendar.settings.general:
//            GeneralPreferenceViewModel

final class arg._cls1
    implements Supplier
{

    private final GeneralPreferenceViewModel arg$1;

    public final Object get()
    {
        return Boolean.valueOf(arg$1.notifyOnThisDevice);
    }

    (GeneralPreferenceViewModel generalpreferenceviewmodel)
    {
        arg$1 = generalpreferenceviewmodel;
    }
}
