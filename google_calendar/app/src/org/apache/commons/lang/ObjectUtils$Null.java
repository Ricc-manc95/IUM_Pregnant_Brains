// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.apache.commons.lang;

import java.io.Serializable;

// Referenced classes of package org.apache.commons.lang:
//            ObjectUtils

public final class 
    implements Serializable
{

    public static final long serialVersionUID = 0x626e04ed40667ec5L;

    private final Object readResolve()
    {
        return ObjectUtils.NULL;
    }

    ()
    {
    }
}
