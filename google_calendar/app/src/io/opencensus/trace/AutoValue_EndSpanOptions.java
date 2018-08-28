// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.trace;


// Referenced classes of package io.opencensus.trace:
//            EndSpanOptions, Status

final class AutoValue_EndSpanOptions extends EndSpanOptions
{

    private final boolean sampleToLocalSpanStore;
    private final Status status;

    AutoValue_EndSpanOptions(boolean flag, Status status1)
    {
        sampleToLocalSpanStore = flag;
        status = status1;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (!(obj instanceof EndSpanOptions))
        {
            break MISSING_BLOCK_LABEL_62;
        }
        obj = (EndSpanOptions)obj;
        if (sampleToLocalSpanStore != ((EndSpanOptions) (obj)).getSampleToLocalSpanStore())
        {
            break; /* Loop/switch isn't completed */
        }
        if (status != null) goto _L4; else goto _L3
_L3:
        if (((EndSpanOptions) (obj)).getStatus() == null) goto _L1; else goto _L5
_L5:
        return false;
_L4:
        if (!status.equals(((EndSpanOptions) (obj)).getStatus())) goto _L5; else goto _L6
_L6:
        return true;
        return false;
    }

    public final boolean getSampleToLocalSpanStore()
    {
        return sampleToLocalSpanStore;
    }

    public final Status getStatus()
    {
        return status;
    }

    public final int hashCode()
    {
        char c;
        int i;
        if (sampleToLocalSpanStore)
        {
            c = '\u04CF';
        } else
        {
            c = '\u04D5';
        }
        if (status == null)
        {
            i = 0;
        } else
        {
            i = status.hashCode();
        }
        return i ^ 0xf4243 * (c ^ 0xf4243);
    }

    public final String toString()
    {
        boolean flag = sampleToLocalSpanStore;
        String s = String.valueOf(status);
        return (new StringBuilder(String.valueOf(s).length() + 53)).append("EndSpanOptions{sampleToLocalSpanStore=").append(flag).append(", status=").append(s).append("}").toString();
    }
}
