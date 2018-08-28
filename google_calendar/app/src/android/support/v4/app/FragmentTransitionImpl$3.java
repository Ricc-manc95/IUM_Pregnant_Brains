// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.support.v4.view.ViewCompat;
import android.view.View;
import java.util.ArrayList;
import java.util.Map;

// Referenced classes of package android.support.v4.app:
//            FragmentTransitionImpl

final class val.nameOverrides
    implements Runnable
{

    private final Map val$nameOverrides;
    private final ArrayList val$sharedElementsIn;

    public final void run()
    {
        int j = val$sharedElementsIn.size();
        for (int i = 0; i < j; i++)
        {
            View view = (View)val$sharedElementsIn.get(i);
            String s = ViewCompat.getTransitionName(view);
            ViewCompat.setTransitionName(view, (String)val$nameOverrides.get(s));
        }

    }

    (Map map)
    {
        val$sharedElementsIn = arraylist;
        val$nameOverrides = map;
        super();
    }
}
