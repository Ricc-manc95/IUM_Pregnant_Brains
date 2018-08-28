// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util;

import com.google.android.apps.calendar.util.function.BiFunction;
import com.google.common.base.Function;

// Referenced classes of package com.google.android.apps.calendar.util:
//            Lens

public final class val.setter
    implements Lens
{

    private final Function val$getter;
    private final BiFunction val$setter;

    public final Object get(Object obj)
    {
        return val$getter.apply(obj);
    }

    public final Object update(Object obj, Object obj1)
    {
        return val$setter.apply(obj, obj1);
    }

    public BiFunction()
    {
        val$getter = function;
        val$setter = bifunction;
        super();
    }
}
