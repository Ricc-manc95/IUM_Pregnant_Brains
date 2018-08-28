// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.calendar;

import android.content.Context;
import android.support.v7.preference.DialogPreference;
import android.util.AttributeSet;

public class ColorDialogPreference extends DialogPreference
{

    public ColorDialogPreference(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        super.mDialogLayoutResId = 0x7f05002f;
        super.mPositiveButtonText = null;
    }
}
