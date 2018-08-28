// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.trace.export;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

// Referenced classes of package io.opencensus.trace.export:
//            SampledSpanStore, AutoValue_SampledSpanStore_PerSpanNameSummary

final class Summary extends SampledSpanStore
{

    static 
    {
        Map map1 = Collections.emptyMap();
        Map map = Collections.emptyMap();
        if (map1 == null)
        {
            throw new NullPointerException("numbersOfLatencySampledSpans");
        }
        map1 = Collections.unmodifiableMap(new HashMap((Map)map1));
        if (map == null)
        {
            throw new NullPointerException("numbersOfErrorSampledSpans");
        } else
        {
            new AutoValue_SampledSpanStore_PerSpanNameSummary(map1, Collections.unmodifiableMap(new HashMap((Map)map)));
        }
    }

    Summary()
    {
        new HashSet();
    }
}
