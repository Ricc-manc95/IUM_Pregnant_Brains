// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.animation;

import android.animation.ValueAnimator;
import android.view.View;

final class arg._cls9
    implements android.animation.dateListener
{

    private final int arg$1;
    private final int arg$2;
    private final int arg$3;
    private final int arg$4;
    private final int arg$5;
    private final int arg$6;
    private final int arg$7;
    private final int arg$8;
    private final View arg$9;

    public final void onAnimationUpdate(ValueAnimator valueanimator)
    {
        int i1 = arg$1;
        int l1 = arg$2;
        int j1 = arg$3;
        int k1 = arg$4;
        int k = arg$5;
        int l = arg$6;
        int i = arg$7;
        int j = arg$8;
        View view = arg$9;
        float f = ((Float)valueanimator.getAnimatedValue()).floatValue();
        float f1 = i1;
        i1 = (int)((float)(l1 - i1) * f + f1);
        j1 = (int)((float)j1 + (float)(k1 - j1) * f);
        k = (int)((float)k + (float)(l - k) * f);
        f1 = i;
        i = (int)(f * (float)(j - i) + f1);
        view.setLeft(i1);
        view.setTop(j1);
        view.setRight(k);
        view.setBottom(i);
    }

    (int i, int j, int k, int l, int i1, int j1, int k1, 
            int l1, View view)
    {
        arg$1 = i;
        arg$2 = j;
        arg$3 = k;
        arg$4 = l;
        arg$5 = i1;
        arg$6 = j1;
        arg$7 = k1;
        arg$8 = l1;
        arg$9 = view;
    }
}
