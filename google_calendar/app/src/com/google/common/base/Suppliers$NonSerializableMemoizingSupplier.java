// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.base;


// Referenced classes of package com.google.common.base:
//            Supplier

public final class delegate
    implements Supplier
{

    private volatile Supplier _flddelegate;
    private volatile boolean initialized;
    private Object value;

    public final Object get()
    {
        if (initialized) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorenter ;
        Object obj;
        if (initialized)
        {
            break MISSING_BLOCK_LABEL_45;
        }
        obj = _flddelegate.get();
        value = obj;
        initialized = true;
        _flddelegate = null;
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
        Supplier supplier = _flddelegate;
        Object obj = supplier;
        if (supplier == null)
        {
            obj = String.valueOf(value);
            obj = (new StringBuilder(String.valueOf(obj).length() + 25)).append("<supplier that returned ").append(((String) (obj))).append(">").toString();
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
