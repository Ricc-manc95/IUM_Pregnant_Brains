// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.apps.xplat.tracing;

import com.google.apps.xplat.tracing.types.Level;

// Referenced classes of package com.google.apps.xplat.tracing:
//            TracerBackend, NoOpTraceSection, AsyncTraceSection

public final class NoOpTracerBackend
    implements TracerBackend
{

    public static final NoOpTracerBackend INSTANCE = new NoOpTracerBackend();

    private NoOpTracerBackend()
    {
    }

    public final AsyncTraceSection beginAsyncAt$5166KOBMC4NMOOBECSNL6T3ID5N6EEQCD9GNCO9FDHGMSPPFADQ74QBECSTKOORFDKNMERRFCTM6ABR1E1O76BROE1M62T1FEHP62OR9DPJIUT3PE1IN6BQCCLR6AR1R9HHMUR9FCTNMUPRCCKNM2S3GECNNGS3CC5Q2UT3IC5HMIRJ75TA74OB3CL9MAORKD5NMSEQCD9GNCO9FDHGMSPPF8HNNAOJCCKTIIJ33DTMIUPRFDTJMOP9FC5O70SPFF1O6OOBK5TQ74OB3D5N6EBQ1EDSMSOQKE9GM6PAJCLHN8QBFDOTG____0(String s, String s1, Level level, Double double1)
    {
        return NoOpTraceSection.INSTANCE;
    }

    public final boolean shouldBridge()
    {
        return true;
    }

}
