// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.material.animation;

import android.view.Choreographer;

// Referenced classes of package com.google.android.libraries.material.animation:
//            ChoreographerCompat

final class choreographer extends ChoreographerCompat
{

    private Choreographer choreographer;

    public final void postFrameCallback(choreographer choreographer1)
    {
        Choreographer choreographer2 = choreographer;
        if (choreographer1.Callback == null)
        {
            choreographer1.Callback = new nit>(choreographer1);
        }
        choreographer2.postFrameCallback(choreographer1.Callback);
    }

    public ()
    {
        choreographer = Choreographer.getInstance();
    }
}
