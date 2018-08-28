// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.datatypes;

import android.text.SpannableStringBuilder;

public final class size
{

    public int size;
    public final SpannableStringBuilder string;

    ()
    {
        string = null;
    }

    string(SpannableStringBuilder spannablestringbuilder)
    {
        string = spannablestringbuilder;
        size = spannablestringbuilder.length();
    }
}
