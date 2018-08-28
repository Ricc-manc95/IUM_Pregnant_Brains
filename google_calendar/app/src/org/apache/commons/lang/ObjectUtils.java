// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.apache.commons.lang;


public final class ObjectUtils
{

    public static final Null NULL = new Null();

    public static boolean equals(Object obj, Object obj1)
    {
        if (obj == obj1)
        {
            return true;
        }
        if (obj == null || obj1 == null)
        {
            return false;
        } else
        {
            return obj.equals(obj1);
        }
    }


    private class Null
        implements Serializable
    {

        public static final long serialVersionUID = 0x626e04ed40667ec5L;

        private final Object readResolve()
        {
            return ObjectUtils.NULL;
        }

        Null()
        {
        }
    }

}
