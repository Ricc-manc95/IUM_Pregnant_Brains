// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.common;


// Referenced classes of package com.google.android.calendar.api.common:
//            FieldModification

public final class nit> extends FieldModification
{

    private final Object val$value;

    public final Object getModificationValue()
    {
        return val$value;
    }

    public final boolean shouldModify()
    {
        return true;
    }

    public ()
    {
        val$value = obj;
        super();
    }
}
