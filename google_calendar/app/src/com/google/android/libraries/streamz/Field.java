// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.streamz;


public final class Field
{

    public final String name;
    private final Class type;

    public Field(String s, Class class1)
    {
        if (s == null)
        {
            throw new NullPointerException();
        }
        name = (String)s;
        if (class1 == null)
        {
            throw new NullPointerException();
        } else
        {
            type = (Class)class1;
            return;
        }
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof Field)
            {
                if (type != ((Field) (obj = (Field)obj)).type || !name.equals(((Field) (obj)).name))
                {
                    return false;
                }
            } else
            {
                return false;
            }
        }
        return true;
    }

    public final int hashCode()
    {
        return name.hashCode();
    }

    public final String toString()
    {
        return String.format("(%s, %s)", new Object[] {
            name, type
        });
    }
}
