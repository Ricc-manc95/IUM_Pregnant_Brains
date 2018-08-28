// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.base;

import java.io.Serializable;

// Referenced classes of package com.google.common.base:
//            Equivalence

public final class  extends Equivalence
    implements Serializable
{

    public static final INSTANCE INSTANCE = new <init>();
    public static final long serialVersionUID = 1L;

    private final Object readResolve()
    {
        return INSTANCE;
    }

    protected final boolean doEquivalent(Object obj, Object obj1)
    {
        return obj.equals(obj1);
    }


    ()
    {
    }
}
