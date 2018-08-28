// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20.util;

import android.graphics.Rect;
import android.view.TouchDelegate;
import android.view.View;

public final class val.bigView
    implements Runnable
{

    private final View val$bigView;
    private final int val$extraPaddingBottom;
    private final int val$extraPaddingLeft;
    private final int val$extraPaddingRight;
    private final int val$extraPaddingTop;
    private final View val$smallView;

    public final void run()
    {
        Rect rect = new Rect();
        val$smallView.getHitRect(rect);
        rect.top = rect.top - val$extraPaddingTop;
        rect.left = rect.left - val$extraPaddingLeft;
        rect.right = rect.right + val$extraPaddingRight;
        rect.bottom = rect.bottom + val$extraPaddingBottom;
        val$bigView.setTouchDelegate(new TouchDelegate(rect, val$smallView));
    }

    public ()
    {
        val$smallView = view;
        val$extraPaddingTop = i;
        val$extraPaddingLeft = j;
        val$extraPaddingRight = k;
        val$extraPaddingBottom = l;
        val$bigView = view1;
        super();
    }
}
