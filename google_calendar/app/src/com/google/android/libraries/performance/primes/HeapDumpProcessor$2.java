// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import com.google.android.libraries.performance.primes.hprof.HprofSerializer;
import java.io.File;
import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            HeapDumpProcessor

final class val.hprofFile
    implements Callable
{

    private final HeapDumpProcessor this$0;
    private final File val$hprofFile;

    public final Object call()
        throws Exception
    {
        return serializer.serializeTopRooted(val$hprofFile);
    }

    r()
    {
        this$0 = final_heapdumpprocessor;
        val$hprofFile = File.this;
        super();
    }
}
