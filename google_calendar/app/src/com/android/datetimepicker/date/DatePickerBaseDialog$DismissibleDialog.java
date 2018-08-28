// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.datetimepicker.date;

import android.app.Dialog;
import java.util.Calendar;

interface 
{

    public abstract void dismiss();

    public abstract Dialog getDialog();

    public abstract void onDateSet(Calendar calendar);
}
