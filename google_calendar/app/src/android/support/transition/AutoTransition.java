// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;


// Referenced classes of package android.support.transition:
//            TransitionSet, Fade, ChangeBounds

public final class AutoTransition extends TransitionSet
{

    public AutoTransition()
    {
        super.mPlayTogether = false;
        addTransition(new Fade(2)).addTransition(new ChangeBounds()).addTransition(new Fade(1));
    }
}
