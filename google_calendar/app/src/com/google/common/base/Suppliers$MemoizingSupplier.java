// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.base;

import java.io.Serializable;

// Referenced classes of package com.google.common.base:
//            Supplier

public final class delegate
    implements Supplier, Serializable
{

    public static final long serialVersionUID = 0L;
    private final Supplier _flddelegate;
    private volatile transient boolean initialized;
    private transient Object value;

    public final Object get()
    {
        if (initialized) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorenter ;
        Object obj;
        if (initialized)
        {
            break MISSING_BLOCK_LABEL_40;
        }
        obj = _flddelegate.get();
        value = obj;
        initialized = true;
        this;
        JVM INSTR monitorexit ;
        return obj;
        this;
        JVM INSTR monitorexit ;
_L2:
        return value;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final String toString()
    {
        Object obj;
        if (initialized)
        {
            obj = String.valueOf(value);
            obj = (new StringBuilder(String.valueOf(obj).length() + 25)).append("<supplier that returned ").append(((String) (obj))).append(">").toString();
        } else
        {
            obj = _flddelegate;
        }
        obj = String.valueOf(obj);
        return (new StringBuilder(String.valueOf(obj).length() + 19)).append("Suppliers.memoize(").append(((String) (obj))).append(")").toString();
    }

    public (Supplier supplier)
    {
        if (supplier == null)
        {
            throw new NullPointerException();
        } else
        {
            _flddelegate = (Supplier)supplier;
            return;
        }
    }
}
