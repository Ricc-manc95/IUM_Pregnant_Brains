// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.graphics.Rect;
import android.support.v4.util.ArrayMap;
import android.view.View;
import java.util.ArrayList;

// Referenced classes of package android.support.v4.app:
//            FragmentTransition, FragmentTransitionImpl, Fragment

final class val.inEpicenter
    implements Runnable
{

    private final Object val$enterTransition;
    private final Object val$finalSharedElementTransition;
    private final agmentContainerTransition val$fragments;
    private final FragmentTransitionImpl val$impl;
    private final Rect val$inEpicenter;
    private final Fragment val$inFragment;
    private final boolean val$inIsPop;
    private final ArrayMap val$nameOverrides;
    private final View val$nonExistentView;
    private final Fragment val$outFragment;
    private final ArrayList val$sharedElementsIn;
    private final ArrayList val$sharedElementsOut;

    public final void run()
    {
        Object obj = FragmentTransition.captureInSharedElements(val$impl, val$nameOverrides, val$finalSharedElementTransition, val$fragments);
        if (obj != null)
        {
            val$sharedElementsIn.addAll(((ArrayMap) (obj)).values());
            val$sharedElementsIn.add(val$nonExistentView);
        }
        FragmentTransition.callSharedElementStartEnd(val$inFragment, val$outFragment, val$inIsPop, ((ArrayMap) (obj)), false);
        if (val$finalSharedElementTransition != null)
        {
            val$impl.swapSharedElementTargets(val$finalSharedElementTransition, val$sharedElementsOut, val$sharedElementsIn);
            obj = FragmentTransition.getInEpicenterView(((ArrayMap) (obj)), val$fragments, val$enterTransition, val$inIsPop);
            if (obj != null)
            {
                FragmentTransitionImpl.getBoundsOnScreen(((View) (obj)), val$inEpicenter);
            }
        }
    }

    agmentContainerTransition()
    {
        val$impl = fragmenttransitionimpl;
        val$nameOverrides = arraymap;
        val$finalSharedElementTransition = obj;
        val$fragments = agmentcontainertransition;
        val$sharedElementsIn = arraylist;
        val$nonExistentView = view;
        val$inFragment = fragment;
        val$outFragment = fragment1;
        val$inIsPop = flag;
        val$sharedElementsOut = arraylist1;
        val$enterTransition = obj1;
        val$inEpicenter = rect;
        super();
    }
}
