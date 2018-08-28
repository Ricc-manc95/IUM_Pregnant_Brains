// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.content.Context;
import com.google.common.base.Supplier;

// Referenced classes of package com.google.android.calendar.groove:
//            GrooveUtils

final class arg._cls1
    implements Supplier
{

    private final Context arg$1;

    public final Object get()
    {
        return GrooveUtils.lambda$getInstanceInfoForParent$2$GrooveUtils(arg$1);
    }

    (Context context)
    {
        arg$1 = context;
    }
}
