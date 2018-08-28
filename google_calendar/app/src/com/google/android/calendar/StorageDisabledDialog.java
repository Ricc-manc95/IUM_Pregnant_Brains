// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.app.AlertDialog;
import android.content.Context;
import com.android.calendarcommon2.LogUtils;

public class StorageDisabledDialog extends AlertDialog
{

    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/StorageDisabledDialog);

    public StorageDisabledDialog(final Context context)
    {
        super(context);
        setTitle(context.getString(0x7f1300f8));
        setMessage(context.getString(0x7f1300f7));
        setButton(-1, context.getString(0x7f1300f6), new _cls1());
        setCanceledOnTouchOutside(false);
    }


    private class _cls1
        implements android.content.DialogInterface.OnClickListener
    {

        private final Context val$context;

        public final void onClick(DialogInterface dialoginterface, int i)
        {
            Intent intent = new Intent();
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", "com.android.providers.calendar", null));
            intent.addFlags(0x10000000);
            intent.addFlags(32768);
            ActivityUtils.startActivity(context, intent, StorageDisabledDialog.TAG);
            dialoginterface.dismiss();
        }

        _cls1()
        {
            context = context1;
            super();
        }
    }

}
