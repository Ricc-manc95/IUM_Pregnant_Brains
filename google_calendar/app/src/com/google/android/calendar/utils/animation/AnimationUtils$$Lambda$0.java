// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.animation;

import android.animation.ValueAnimator;
import android.view.View;

public final class arg._cls9
    implements android.animation.ateListener
{

    private final View arg$1;
    private final int arg$2;
    private final int arg$3;
    private final int arg$4;
    private final int arg$5;
    private final int arg$6;
    private final int arg$7;
    private final int arg$8;
    private final int arg$9;

    public final void onAnimationUpdate(ValueAnimator valueanimator)
    {
        View view = arg$1;
        int i = arg$2;
        int j = arg$3;
        int k = arg$4;
        int l = arg$5;
        int i1 = arg$6;
        int j1 = arg$7;
        int k1 = arg$8;
        int l1 = arg$9;
        float f = ((Float)valueanimator.getAnimatedValue()).floatValue();
        view.setLeft((int)((float)i + (float)j * f));
        view.setTop((int)((float)k + (float)l * f));
        view.setRight((int)((float)i1 + (float)j1 * f));
        float f1 = k1;
        view.setBottom((int)(f * (float)l1 + f1));
    }

    public (View view, int i, int j, int k, int l, int i1, int j1, 
            int k1, int l1)
    {
        arg$1 = view;
        arg$2 = i;
        arg$3 = j;
        arg$4 = k;
        arg$5 = l;
        arg$6 = i1;
        arg$7 = j1;
        arg$8 = k1;
        arg$9 = l1;
    }
}
