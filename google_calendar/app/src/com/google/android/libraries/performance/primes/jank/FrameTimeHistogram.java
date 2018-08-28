// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.jank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import logs.proto.wireless.performance.mobile.nano.HistogramBucket;
import logs.proto.wireless.performance.mobile.nano.JankMetric;

// Referenced classes of package com.google.android.libraries.performance.primes.jank:
//            FrameTimeMeasurement

final class FrameTimeHistogram
    implements FrameTimeMeasurement
{

    private static final int BUCKETS_BOUNDS[] = {
        0, 4, 8, 10, 12, 14, 16, 18, 20, 25, 
        30, 40, 50, 60, 70, 80, 90, 100, 150, 200, 
        300, 400, 500, 600, 700, 800, 900, 1000
    };
    private final int buckets[];
    private int jankyFrameCount;
    private int maxRenderTimeMs;
    private int renderedFrameCount;
    private int totalFrameTimeMs;

    FrameTimeHistogram()
    {
        buckets = new int[BUCKETS_BOUNDS.length];
    }

    public final void addFrame(int i, int j)
    {
        boolean flag;
        if (i >= 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        }
        renderedFrameCount = renderedFrameCount + 1;
        if (i > j)
        {
            jankyFrameCount = jankyFrameCount + 1;
        }
        int ai[] = buckets;
        int k = Arrays.binarySearch(BUCKETS_BOUNDS, i);
        j = k;
        if (k < 0)
        {
            j = -(k + 2);
        }
        ai[j] = ai[j] + 1;
        maxRenderTimeMs = Math.max(maxRenderTimeMs, i);
        totalFrameTimeMs = totalFrameTimeMs + i;
    }

    public final JankMetric getMetric()
    {
        boolean flag;
        if (renderedFrameCount != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            return null;
        }
        JankMetric jankmetric = new JankMetric();
        jankmetric.jankyFrameCount = Integer.valueOf(jankyFrameCount);
        jankmetric.renderedFrameCount = Integer.valueOf(renderedFrameCount);
        jankmetric.durationMs = Integer.valueOf(totalFrameTimeMs);
        jankmetric.maxFrameRenderTimeMs = Integer.valueOf(maxRenderTimeMs);
        int ai[] = buckets;
        ArrayList arraylist = new ArrayList();
        for (int i = 0; i < BUCKETS_BOUNDS.length; i++)
        {
            if (ai[i] <= 0)
            {
                continue;
            }
            Integer integer;
            boolean flag1;
            int j;
            int k;
            if (i + 1 == BUCKETS_BOUNDS.length)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            j = ai[i];
            k = BUCKETS_BOUNDS[i];
            if (flag1)
            {
                integer = null;
            } else
            {
                integer = Integer.valueOf(BUCKETS_BOUNDS[i + 1] - 1);
            }
            if (j > 0)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (!flag1)
            {
                throw new IllegalArgumentException();
            }
            HistogramBucket histogrambucket = new HistogramBucket();
            histogrambucket.min = Integer.valueOf(k);
            histogrambucket.max = integer;
            histogrambucket.count = Integer.valueOf(j);
            arraylist.add(histogrambucket);
        }

        jankmetric.frameTimeHistogram = (HistogramBucket[])arraylist.toArray(new HistogramBucket[0]);
        return jankmetric;
    }

    public final boolean isMetricReadyToBeSent()
    {
        return renderedFrameCount != 0;
    }

}
