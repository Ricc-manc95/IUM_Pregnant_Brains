// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.common.view;

import android.widget.CompoundButton;

// Referenced classes of package com.google.android.calendar.common.view:
//            NinjaSwitch

final class val.listener
    implements android.widget..OnCheckedChangeListener
{

    private final NinjaSwitch this$0;
    private final android.widget..OnCheckedChangeListener val$listener;

    public final void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
    {
        if (!stealth)
        {
            val$listener.onCheckedChanged(compoundbutton, flag);
        }
    }

    ()
    {
        this$0 = final_ninjaswitch;
        val$listener = android.widget..OnCheckedChangeListener.this;
        super();
    }
}