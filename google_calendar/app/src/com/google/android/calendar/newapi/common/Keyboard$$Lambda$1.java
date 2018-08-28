// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.common;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public final class 
    implements android.view.istener
{

    public static final android.view.istener $instance = new <init>();

    public final void onFocusChange(View view, boolean flag)
    {
        if (flag)
        {
            ((InputMethodManager)view.getContext().getSystemService("input_method")).showSoftInput(view, 1);
            view.setOnFocusChangeListener(null);
        }
    }


    private ()
    {
    }
}
