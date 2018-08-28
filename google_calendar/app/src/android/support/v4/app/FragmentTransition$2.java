// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.view.View;
import java.util.ArrayList;

// Referenced classes of package android.support.v4.app:
//            FragmentTransitionImpl, FragmentTransition, Fragment

final class val.exitTransition
    implements Runnable
{

    private final Object val$enterTransition;
    private final ArrayList val$enteringViews;
    private final Object val$exitTransition;
    private final ArrayList val$exitingViews;
    private final FragmentTransitionImpl val$impl;
    private final Fragment val$inFragment;
    private final View val$nonExistentView;
    private final ArrayList val$sharedElementsIn;

    public final void run()
    {
        if (val$enterTransition != null)
        {
            val$impl.removeTarget(val$enterTransition, val$nonExistentView);
            ArrayList arraylist = FragmentTransition.configureEnteringExitingViews(val$impl, val$enterTransition, val$inFragment, val$sharedElementsIn, val$nonExistentView);
            val$enteringViews.addAll(arraylist);
        }
        if (val$exitingViews != null)
        {
            if (val$exitTransition != null)
            {
                ArrayList arraylist1 = new ArrayList();
                arraylist1.add(val$nonExistentView);
                val$impl.replaceTargets(val$exitTransition, val$exitingViews, arraylist1);
            }
            val$exitingViews.clear();
            val$exitingViews.add(val$nonExistentView);
        }
    }

    l()
    {
        val$enterTransition = obj;
        val$impl = fragmenttransitionimpl;
        val$nonExistentView = view;
        val$inFragment = fragment;
        val$sharedElementsIn = arraylist;
        val$enteringViews = arraylist1;
        val$exitingViews = arraylist2;
        val$exitTransition = obj1;
        super();
    }
}
