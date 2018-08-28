// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

final class val.noThanksButton
    implements android.view.PromptDialogDelegate._cls1
{

    private final Button val$noThanksButton;

    public final boolean onTouch(View view, MotionEvent motionevent)
    {
        val$noThanksButton.onTouchEvent(motionevent);
        return true;
    }

    ()
    {
        val$noThanksButton = button;
        super();
    }
}
