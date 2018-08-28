// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package dagger.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Referenced classes of package dagger.internal:
//            SetFactory

public static final class collectionProviders
{

    public final List collectionProviders;
    public final List individualProviders;

    (int i, int j)
    {
        Object obj;
        if (i == 0)
        {
            obj = Collections.emptyList();
        } else
        {
            obj = new ArrayList(i);
        }
        individualProviders = ((List) (obj));
        if (j == 0)
        {
            obj = Collections.emptyList();
        } else
        {
            obj = new ArrayList(j);
        }
        collectionProviders = ((List) (obj));
    }
}
