// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.base;


// Referenced classes of package com.google.common.base:
//            Function, Supplier

public final class  extends Enum
    implements Function
{

    private static final INSTANCE $VALUES[];
    public static final INSTANCE INSTANCE;

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    public final Object apply(Object obj)
    {
        return ((Supplier)obj).get();
    }

    public final String toString()
    {
        return "Suppliers.supplierFunction()";
    }

    static 
    {
        INSTANCE = new <init>("INSTANCE", 0);
        $VALUES = (new .VALUES[] {
            INSTANCE
        });
    }

    private (String s, int i)
    {
        super(s, 0);
    }
}
