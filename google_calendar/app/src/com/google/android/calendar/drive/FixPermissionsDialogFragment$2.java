// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.drive;

import android.graphics.Rect;
import android.view.TouchDelegate;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

final class val.buttonExtras
    implements Runnable
{

    private final View val$buttonExtras;
    private final TextView val$extraText;
    private final Spinner val$spinner;

    public final void run()
    {
        Rect rect = new Rect();
        Rect rect1 = new Rect();
        val$spinner.getHitRect(rect);
        val$extraText.getHitRect(rect1);
        rect.bottom = rect1.bottom;
        val$buttonExtras.setTouchDelegate(new TouchDelegate(rect, val$spinner));
    }

    ()
    {
        val$spinner = spinner1;
        val$extraText = textview;
        val$buttonExtras = view;
        super();
    }
}
