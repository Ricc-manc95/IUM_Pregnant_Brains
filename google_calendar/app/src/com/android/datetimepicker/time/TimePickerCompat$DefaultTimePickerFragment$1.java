// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.datetimepicker.time;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;

final class  extends TimePickerDialog
{

    public final void onClick(DialogInterface dialoginterface, int i)
    {
        switch (i)
        {
        default:
            super.onClick(dialoginterface, i);
            return;

        case -2: 
            cancel();
            break;
        }
    }

    (Context context, android.app.imePickerFragment._cls1 _pcls1, int i, int j, boolean flag)
    {
        super(context, _pcls1, i, j, flag);
    }
}
