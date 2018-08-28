// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.animation.Animator;
import android.transition.TransitionValues;
import android.transition.Visibility;
import android.view.ViewGroup;

// Referenced classes of package com.google.android.calendar.groove:
//            BackButtonView

final class  extends Visibility
{

    public final Animator onAppear(ViewGroup viewgroup, TransitionValues transitionvalues, int i, TransitionValues transitionvalues1, int j)
    {
        if (transitionvalues1 != null)
        {
            return BackButtonView.createAnimator(transitionvalues1.view, true);
        } else
        {
            return null;
        }
    }

    ()
    {
    }
}
