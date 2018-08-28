// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.arch.lifecycle;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

// Referenced classes of package android.arch.lifecycle:
//            ViewModel

public final class ViewModelStore
{

    public final HashMap mMap = new HashMap();

    public ViewModelStore()
    {
    }

    public final void clear()
    {
        for (Iterator iterator = mMap.values().iterator(); iterator.hasNext(); ((ViewModel)iterator.next()).onCleared()) { }
        mMap.clear();
    }
}
