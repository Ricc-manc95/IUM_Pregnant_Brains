// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.stats;

import io.opencensus.common.AutoValue_Timestamp;
import java.util.HashMap;

// Referenced classes of package io.opencensus.stats:
//            ViewManager

final class  extends ViewManager
{

    static 
    {
        if (0L < 0xffffffb686346200L)
        {
            throw new IllegalArgumentException((new StringBuilder(68)).append("'seconds' is less than minimum (-315576000000): 0").toString());
        }
        if (0L > 0x4979cb9e00L)
        {
            throw new IllegalArgumentException((new StringBuilder(70)).append("'seconds' is greater than maximum (315576000000): 0").toString());
        } else
        {
            new AutoValue_Timestamp(0L, 0);
        }
    }

    ()
    {
        new HashMap();
    }
}
