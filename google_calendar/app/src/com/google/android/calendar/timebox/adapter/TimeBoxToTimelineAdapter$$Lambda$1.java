// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timebox.adapter;

import android.content.Context;
import com.google.android.apps.calendar.util.preference.PreferenceUtils;
import javax.inject.Provider;

final class arg._cls1
    implements Provider
{

    private final Context arg$1;

    public final Object get()
    {
        return Integer.valueOf(PreferenceUtils.getBirthdayColor(arg$1));
    }

    (Context context)
    {
        arg$1 = context;
    }
}
