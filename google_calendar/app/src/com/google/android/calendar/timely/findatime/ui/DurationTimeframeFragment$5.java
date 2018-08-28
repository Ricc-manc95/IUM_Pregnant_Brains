// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.findatime.ui;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.google.android.calendar.utils.AccessibilityUtils;

final class val.group
    implements Runnable
{

    private final RadioGroup val$group;

    public final void run()
    {
        RadioButton radiobutton = (RadioButton)val$group.findViewById(val$group.getCheckedRadioButtonId());
        radiobutton.requestFocus();
        AccessibilityUtils.requestAccessibilityFocus(radiobutton);
    }

    Q()
    {
        val$group = radiogroup;
        super();
    }
}
