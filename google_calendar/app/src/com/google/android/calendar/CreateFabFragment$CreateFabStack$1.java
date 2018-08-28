// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.view.View;
import android.view.ViewTreeObserver;
import java.util.Iterator;
import java.util.Stack;

final class val.contentDescription
    implements android.view.ener
{

    private final val.contentDescription this$0;
    private final CharSequence val$contentDescription;
    private final View val$createFab;

    public final void onGlobalLayout()
    {
        val$createFab.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        for (Iterator iterator = ack.iterator(); iterator.hasNext(); ((ope)iterator.next()).createFab.setContentDescription(val$contentDescription)) { }
        ndCreateFab().setContentDescription(val$contentDescription);
    }

    ope()
    {
        this$0 = final_ope;
        val$createFab = view;
        val$contentDescription = CharSequence.this;
        super();
    }
}
