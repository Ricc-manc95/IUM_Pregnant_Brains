// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.transition.Transition;
import java.util.ArrayList;

// Referenced classes of package android.support.v4.app:
//            FragmentTransitionImpl, FragmentTransitionCompat21

final class val.sharedElementsIn
    implements android.transition.
{

    private final FragmentTransitionCompat21 this$0;
    private final Object val$enterTransition;
    private final ArrayList val$enteringViews;
    private final Object val$exitTransition;
    private final ArrayList val$exitingViews;
    private final Object val$sharedElementTransition;
    private final ArrayList val$sharedElementsIn;

    public final void onTransitionCancel(Transition transition)
    {
    }

    public final void onTransitionEnd(Transition transition)
    {
    }

    public final void onTransitionPause(Transition transition)
    {
    }

    public final void onTransitionResume(Transition transition)
    {
    }

    public final void onTransitionStart(Transition transition)
    {
        if (val$enterTransition != null)
        {
            replaceTargets(val$enterTransition, val$enteringViews, null);
        }
        if (val$exitTransition != null)
        {
            replaceTargets(val$exitTransition, val$exitingViews, null);
        }
        if (val$sharedElementTransition != null)
        {
            replaceTargets(val$sharedElementTransition, val$sharedElementsIn, null);
        }
    }

    _cls9()
    {
        this$0 = final_fragmenttransitioncompat21;
        val$enterTransition = obj;
        val$enteringViews = arraylist;
        val$exitTransition = obj1;
        val$exitingViews = arraylist1;
        val$sharedElementTransition = obj2;
        val$sharedElementsIn = ArrayList.this;
        super();
    }
}
