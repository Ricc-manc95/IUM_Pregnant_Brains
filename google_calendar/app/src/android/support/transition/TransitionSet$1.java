// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;


// Referenced classes of package android.support.transition:
//            TransitionListenerAdapter, Transition, TransitionSet

final class erAdapter extends TransitionListenerAdapter
{

    private final Transition val$nextTransition;

    public final void onTransitionEnd(Transition transition)
    {
        val$nextTransition.runAnimators();
        transition.removeListener(this);
    }

    erAdapter(Transition transition)
    {
        val$nextTransition = transition;
        super();
    }
}
