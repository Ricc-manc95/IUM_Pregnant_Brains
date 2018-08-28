// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.transition.Transition;
import android.view.View;
import java.util.ArrayList;

// Referenced classes of package android.support.v4.app:
//            FragmentTransitionCompat21

final class val.exitingViews
    implements android.transition.
{

    private final ArrayList val$exitingViews;
    private final View val$fragmentView;

    public final void onTransitionCancel(Transition transition)
    {
    }

    public final void onTransitionEnd(Transition transition)
    {
        transition.removeListener(this);
        val$fragmentView.setVisibility(8);
        int j = val$exitingViews.size();
        for (int i = 0; i < j; i++)
        {
            ((View)val$exitingViews.get(i)).setVisibility(0);
        }

    }

    public final void onTransitionPause(Transition transition)
    {
    }

    public final void onTransitionResume(Transition transition)
    {
    }

    public final void onTransitionStart(Transition transition)
    {
    }

    _cls9(ArrayList arraylist)
    {
        val$fragmentView = view;
        val$exitingViews = arraylist;
        super();
    }
}
