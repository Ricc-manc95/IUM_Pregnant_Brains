// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.support.v4.util.ArrayMap;
import android.support.v4.util.SimpleArrayMap;
import java.util.ArrayList;

// Referenced classes of package android.support.transition:
//            TransitionListenerAdapter, Transition

final class val.runningTransitions extends TransitionListenerAdapter
{

    private final ceneRoot this$0;
    private final ArrayMap val$runningTransitions;

    public final void onTransitionEnd(Transition transition)
    {
        ((ArrayList)val$runningTransitions.get(ceneRoot)).remove(transition);
    }

    ()
    {
        this$0 = final_;
        val$runningTransitions = ArrayMap.this;
        super();
    }
}
