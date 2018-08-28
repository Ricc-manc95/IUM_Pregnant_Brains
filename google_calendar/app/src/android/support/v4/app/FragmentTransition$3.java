// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.graphics.Rect;
import android.support.v4.util.ArrayMap;
import android.view.View;

// Referenced classes of package android.support.v4.app:
//            FragmentTransition, FragmentTransitionImpl, Fragment

final class val.epicenter
    implements Runnable
{

    private final Rect val$epicenter;
    private final View val$epicenterView;
    private final FragmentTransitionImpl val$impl;
    private final Fragment val$inFragment;
    private final boolean val$inIsPop;
    private final ArrayMap val$inSharedElements;
    private final Fragment val$outFragment;

    public final void run()
    {
        FragmentTransition.callSharedElementStartEnd(val$inFragment, val$outFragment, val$inIsPop, val$inSharedElements, false);
        if (val$epicenterView != null)
        {
            FragmentTransitionImpl.getBoundsOnScreen(val$epicenterView, val$epicenter);
        }
    }

    l()
    {
        val$inFragment = fragment;
        val$outFragment = fragment1;
        val$inIsPop = flag;
        val$inSharedElements = arraymap;
        val$epicenterView = view;
        val$impl = fragmenttransitionimpl;
        val$epicenter = rect;
        super();
    }
}
