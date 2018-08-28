// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package dagger.internal;


public final class Preconditions
{

    public static Object checkNotNull(Object obj, String s, Object obj1)
    {
        if (obj == null)
        {
            if (!s.contains("%s"))
            {
                throw new IllegalArgumentException("errorMessageTemplate has no format specifiers");
            }
            if (s.indexOf("%s") != s.lastIndexOf("%s"))
            {
                throw new IllegalArgumentException("errorMessageTemplate has more than one format specifier");
            }
            if (obj1 instanceof Class)
            {
                obj = ((Class)obj1).getCanonicalName();
            } else
            {
                obj = String.valueOf(obj1);
            }
            throw new NullPointerException(s.replace("%s", ((CharSequence) (obj))));
        } else
        {
            return obj;
        }
    }
}
