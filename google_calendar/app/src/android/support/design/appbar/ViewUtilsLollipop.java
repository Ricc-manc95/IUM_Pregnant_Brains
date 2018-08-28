// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.appbar;

import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.content.res.Resources;
import android.view.View;

final class ViewUtilsLollipop
{

    static void setDefaultAppBarLayoutStateListAnimator(View view, float f)
    {
        int i = view.getResources().getInteger(0x7f110002);
        StateListAnimator statelistanimator = new StateListAnimator();
        ObjectAnimator objectanimator = ObjectAnimator.ofFloat(view, "elevation", new float[] {
            0.0F
        }).setDuration(i);
        statelistanimator.addState(new int[] {
            0x101000e, 0x7f01005b, 0x80feffa6
        }, objectanimator);
        objectanimator = ObjectAnimator.ofFloat(view, "elevation", new float[] {
            f
        }).setDuration(i);
        statelistanimator.addState(new int[] {
            0x101000e
        }, objectanimator);
        objectanimator = ObjectAnimator.ofFloat(view, "elevation", new float[] {
            0.0F
        }).setDuration(0L);
        statelistanimator.addState(new int[0], objectanimator);
        view.setStateListAnimator(statelistanimator);
    }
}
