// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.base;


// Referenced classes of package com.google.common.base:
//            Optional, Supplier, Function

public final class Absent extends Optional
{

    public static final Absent INSTANCE = new Absent();
    public static final long serialVersionUID = 0L;

    private Absent()
    {
    }

    private final Object readResolve()
    {
        return INSTANCE;
    }

    public final boolean equals(Object obj)
    {
        return obj == this;
    }

    public final Object get()
    {
        throw new IllegalStateException("Optional.get() cannot be called on an absent value");
    }

    public final int hashCode()
    {
        return 0x79a31aac;
    }

    public final boolean isPresent()
    {
        return false;
    }

    public final Object or(Supplier supplier)
    {
        supplier = ((Supplier) (supplier.get()));
        if (supplier == null)
        {
            throw new NullPointerException(String.valueOf("use Optional.orNull() instead of a Supplier that returns null"));
        } else
        {
            return supplier;
        }
    }

    public final Object or(Object obj)
    {
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("use Optional.orNull() instead of Optional.or(null)"));
        } else
        {
            return obj;
        }
    }

    public final Object orNull()
    {
        return null;
    }

    public final String toString()
    {
        return "Optional.absent()";
    }

    public final Optional transform(Function function)
    {
        if (function == null)
        {
            throw new NullPointerException();
        } else
        {
            return INSTANCE;
        }
    }

}
