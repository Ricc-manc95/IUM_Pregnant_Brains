// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.support.v4.view.ViewCompat;
import android.view.View;
import java.util.ArrayList;

// Referenced classes of package android.support.v4.app:
//            FragmentTransitionImpl

final class val.outNames
    implements Runnable
{

    private final ArrayList val$inNames;
    private final int val$numSharedElements;
    private final ArrayList val$outNames;
    private final ArrayList val$sharedElementsIn;
    private final ArrayList val$sharedElementsOut;

    public final void run()
    {
        for (int i = 0; i < val$numSharedElements; i++)
        {
            ViewCompat.setTransitionName((View)val$sharedElementsIn.get(i), (String)val$inNames.get(i));
            ViewCompat.setTransitionName((View)val$sharedElementsOut.get(i), (String)val$outNames.get(i));
        }

    }

    (ArrayList arraylist3)
    {
        val$numSharedElements = i;
        val$sharedElementsIn = arraylist;
        val$inNames = arraylist1;
        val$sharedElementsOut = arraylist2;
        val$outNames = arraylist3;
        super();
    }
}
