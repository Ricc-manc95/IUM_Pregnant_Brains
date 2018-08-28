// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.datetimepicker.date;

import android.widget.DatePicker;

public final class wrappee
    implements android.app.eSetListenerWrapper
{

    private wrappee wrappee;

    public final void onDateSet(DatePicker datepicker, int i, int j, int k)
    {
        wrappee.wrappee(i, j, k);
    }

    public ( )
    {
        wrappee = ;
    }
}
