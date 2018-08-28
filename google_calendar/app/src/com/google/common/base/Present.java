// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.base;


// Referenced classes of package com.google.common.base:
//            Optional, Function, Supplier

public final class Present extends Optional
{

    public static final long serialVersionUID = 0L;
    private final Object reference;

    public Present(Object obj)
    {
        reference = obj;
    }

    public final boolean equals(Object obj)
    {
        if (obj instanceof Present)
        {
            obj = (Present)obj;
            return reference.equals(((Present) (obj)).reference);
        } else
        {
            return false;
        }
    }

    public final Object get()
    {
        return reference;
    }

    public final int hashCode()
    {
        return 0x598df91c + reference.hashCode();
    }

    public final boolean isPresent()
    {
        return true;
    }

    public final Object or(Supplier supplier)
    {
        if (supplier == null)
        {
            throw new NullPointerException();
        } else
        {
            return reference;
        }
    }

    public final Object or(Object obj)
    {
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("use Optional.orNull() instead of Optional.or(null)"));
        } else
        {
            return reference;
        }
    }

    public final Object orNull()
    {
        return reference;
    }

    public final String toString()
    {
        String s = String.valueOf(reference);
        return (new StringBuilder(String.valueOf(s).length() + 13)).append("Optional.of(").append(s).append(")").toString();
    }

    public final Optional transform(Function function)
    {
        function = ((Function) (function.apply(reference)));
        if (function == null)
        {
            throw new NullPointerException(String.valueOf("the Function passed to Optional.transform() must not return null."));
        } else
        {
            return new Present(function);
        }
    }
}
