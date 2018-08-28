// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.base;

import java.util.Arrays;

// Referenced classes of package com.google.common.base:
//            Predicate

public final class Predicates
{

    public static Predicate and(Predicate predicate, Predicate predicate1)
    {
        if (predicate == null)
        {
            throw new NullPointerException();
        }
        predicate = (Predicate)predicate;
        if (predicate1 == null)
        {
            throw new NullPointerException();
        } else
        {
            return new AndPredicate(Arrays.asList(new Predicate[] {
                predicate, (Predicate)predicate1
            }));
        }
    }

    private class AndPredicate
        implements Predicate, Serializable
    {

        public static final long serialVersionUID = 0L;
        private final List components;

        public final boolean apply(Object obj)
        {
            for (int i = 0; i < components.size(); i++)
            {
                if (!((Predicate)components.get(i)).apply(obj))
                {
                    return false;
                }
            }

            return true;
        }

        public final boolean equals(Object obj)
        {
            if (obj instanceof AndPredicate)
            {
                obj = (AndPredicate)obj;
                return components.equals(((AndPredicate) (obj)).components);
            } else
            {
                return false;
            }
        }

        public final int hashCode()
        {
            return components.hashCode() + 0x12472c2c;
        }

        public final String toString()
        {
            Object obj = components;
            StringBuilder stringbuilder = (new StringBuilder("Predicates.")).append("and").append('(');
            boolean flag = true;
            for (obj = ((Iterable) (obj)).iterator(); ((Iterator) (obj)).hasNext();)
            {
                Object obj1 = ((Iterator) (obj)).next();
                if (!flag)
                {
                    stringbuilder.append(',');
                }
                stringbuilder.append(obj1);
                flag = false;
            }

            return stringbuilder.append(')').toString();
        }

        public AndPredicate(List list)
        {
            components = list;
        }
    }

}
