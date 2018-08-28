// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.listener;

import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import java.util.Collection;
import java.util.Iterator;

// Referenced classes of package com.google.android.calendar.utils.listener:
//            ListenerCollection

final class impl
    implements rator
{

    private final Collection collection;
    private final Iterator impl;
    private boolean released;
    private final ListenerCollection this$0;

    private final boolean checkNextAndRelease()
    {
        boolean flag = impl.hasNext();
        if (!flag && !released)
        {
            released = true;
            if (collection == listeners)
            {
                Object obj = ListenerCollection.this;
                obj.inUseCounter = ((ListenerCollection) (obj)).inUseCounter - 1;
                obj = Features.instance;
                if (obj == null)
                {
                    throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
                }
                ((FeatureConfig)obj).dogfood_features();
            }
        }
        return flag;
    }

    public final boolean hasNext()
    {
        boolean flag = impl.hasNext();
        if (!flag && !released)
        {
            released = true;
            if (collection == listeners)
            {
                Object obj = ListenerCollection.this;
                obj.inUseCounter = ((ListenerCollection) (obj)).inUseCounter - 1;
                obj = Features.instance;
                if (obj == null)
                {
                    throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
                }
                ((FeatureConfig)obj).dogfood_features();
            }
        }
        return flag;
    }

    public final Object next()
    {
        checkNextAndRelease();
        return impl.next();
    }

    public final void remove()
    {
        throw new UnsupportedOperationException();
    }

    rator(Collection collection1)
    {
        this$0 = ListenerCollection.this;
        super();
        released = false;
        collection = collection1;
        impl = collection1.iterator();
        inUseCounter = inUseCounter + 1;
    }
}
