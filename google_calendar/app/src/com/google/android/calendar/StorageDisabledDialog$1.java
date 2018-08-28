// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import com.google.android.calendar.utils.activity.ActivityUtils;

// Referenced classes of package com.google.android.calendar:
//            StorageDisabledDialog

final class val.context
    implements android.content.istener
{

    private final Context val$context;

    public final void onClick(DialogInterface dialoginterface, int i)
    {
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", "com.android.providers.calendar", null));
        intent.addFlags(0x10000000);
        intent.addFlags(32768);
        ActivityUtils.startActivity(val$context, intent, StorageDisabledDialog.TAG);
        dialoginterface.dismiss();
    }

    tils()
    {
        val$context = context1;
        super();
    }
}
