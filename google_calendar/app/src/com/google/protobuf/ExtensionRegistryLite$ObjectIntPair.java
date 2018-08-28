// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;


// Referenced classes of package com.google.protobuf:
//            ExtensionRegistryLite

static final class number
{

    private final int number;
    private final Object object;

    public final boolean equals(Object obj)
    {
        if (obj instanceof number)
        {
            if (object == ((object) (obj = (object)obj)).object && number == ((number) (obj)).number)
            {
                return true;
            }
        }
        return false;
    }

    public final int hashCode()
    {
        return System.identityHashCode(object) * 65535 + number;
    }

    (Object obj, int i)
    {
        object = obj;
        number = i;
    }
}
