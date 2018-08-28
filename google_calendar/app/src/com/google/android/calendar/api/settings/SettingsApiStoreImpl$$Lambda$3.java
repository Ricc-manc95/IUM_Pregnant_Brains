// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.settings;

import android.database.Cursor;

// Referenced classes of package com.google.android.calendar.api.settings:
//            SettingsApiStoreImpl

final class 
    implements com.google.android.apps.calendar.util.database.
{

    public static final com.google.android.apps.calendar.util.database.nts._cls5.SettingsApiStoreImpl $instance = new <init>();

    public final Object extract(Cursor cursor)
    {
        return SettingsApiStoreImpl.lambda$listAccounts$5$SettingsApiStoreImpl(cursor);
    }


    private ()
    {
    }
}
