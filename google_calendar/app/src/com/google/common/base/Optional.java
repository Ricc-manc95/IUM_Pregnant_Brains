// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.base;

import java.io.Serializable;

// Referenced classes of package com.google.common.base:
//            Supplier, Function

public abstract class Optional
    implements Serializable
{

    public static final long serialVersionUID = 0L;

    Optional()
    {
    }

    public abstract boolean equals(Object obj);

    public abstract Object get();

    public abstract int hashCode();

    public abstract boolean isPresent();

    public abstract Object or(Supplier supplier);

    public abstract Object or(Object obj);

    public abstract Object orNull();

    public abstract Optional transform(Function function);
}
