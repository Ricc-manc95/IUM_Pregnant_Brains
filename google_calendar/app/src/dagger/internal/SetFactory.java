// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package dagger.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.inject.Provider;

// Referenced classes of package dagger.internal:
//            Factory, InstanceFactory

public final class SetFactory
    implements Factory
{
    public static final class Builder
    {

        public final List collectionProviders;
        public final List individualProviders;

        Builder(int i, int j)
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


    private final List collectionProviders;
    private final List individualProviders;

    public SetFactory(List list, List list1)
    {
        individualProviders = list;
        collectionProviders = list1;
    }

    public static Builder builder(int i, int j)
    {
        return new Builder(i, 0);
    }

    public final Object get()
    {
        boolean flag = false;
        int l = individualProviders.size();
        ArrayList arraylist = new ArrayList(collectionProviders.size());
        int i1 = collectionProviders.size();
        for (int i = 0; i < i1; i++)
        {
            Collection collection = (Collection)((Provider)collectionProviders.get(i)).get();
            l += collection.size();
            arraylist.add(collection);
        }

        HashSet hashset;
        int j;
        if (l < 3)
        {
            j = l + 1;
        } else
        if (l < 0x40000000)
        {
            j = (int)((float)l / 0.75F + 1.0F);
        } else
        {
            j = 0x7fffffff;
        }
        hashset = new HashSet(j);
        l = individualProviders.size();
        for (j = 0; j < l; j++)
        {
            Object obj = ((Provider)individualProviders.get(j)).get();
            if (obj == null)
            {
                throw new NullPointerException();
            }
            hashset.add(obj);
        }

        l = arraylist.size();
        for (int k = ((flag) ? 1 : 0); k < l; k++)
        {
            Object obj1;
            for (Iterator iterator = ((Collection)arraylist.get(k)).iterator(); iterator.hasNext(); hashset.add(obj1))
            {
                obj1 = iterator.next();
                if (obj1 == null)
                {
                    throw new NullPointerException();
                }
            }

        }

        return Collections.unmodifiableSet(hashset);
    }

    static 
    {
        Set set = Collections.emptySet();
        if (set == null)
        {
            throw new NullPointerException("instance cannot be null");
        } else
        {
            new InstanceFactory(set);
        }
    }
}
