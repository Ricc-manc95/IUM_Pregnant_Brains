// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.belong;

import android.content.Context;
import com.google.android.apps.calendar.util.function.Consumer;

// Referenced classes of package com.google.android.calendar.belong:
//            BelongUtils

final class arg._cls2
    implements Consumer
{

    private final Context arg$1;
    private final boolean arg$2;

    public final void accept(Object obj)
    {
        BelongUtils.lambda$startActivityCheck$0$BelongUtils(arg$1, arg$2, (Boolean)obj);
    }

    (Context context, boolean flag)
    {
        arg$1 = context;
        arg$2 = flag;
    }
}
