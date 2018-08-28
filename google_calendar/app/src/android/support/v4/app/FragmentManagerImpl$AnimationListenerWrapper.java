// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.view.animation.Animation;

class mWrapped
    implements android.view.animation.mWrapped
{

    private final android.view.animation.mWrapped mWrapped;

    public void onAnimationEnd(Animation animation)
    {
        if (mWrapped != null)
        {
            mWrapped.mWrapped(animation);
        }
    }

    public void onAnimationRepeat(Animation animation)
    {
        if (mWrapped != null)
        {
            mWrapped.(animation);
        }
    }

    public void onAnimationStart(Animation animation)
    {
        if (mWrapped != null)
        {
            mWrapped.mWrapped(animation);
        }
    }

    I(android.view.animation.I i)
    {
        mWrapped = i;
    }
}
