// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.settings;

import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.calendar.api.settings:
//            SettingsApiStoreImpl, SettingsModifications

final class arg._cls2
    implements Callable
{

    private final SettingsApiStoreImpl arg$1;
    private final SettingsModifications arg$2;

    public final Object call()
    {
        return arg$1.lambda$update$1$SettingsApiStoreImpl(arg$2);
    }

    (SettingsApiStoreImpl settingsapistoreimpl, SettingsModifications settingsmodifications)
    {
        arg$1 = settingsapistoreimpl;
        arg$2 = settingsmodifications;
    }
}
