// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.trace;


// Referenced classes of package io.opencensus.trace:
//            AutoValue_EndSpanOptions, Status, EndSpanOptions

final class  extends 
{

    private Boolean sampleToLocalSpanStore;
    private Status status;

    public final EndSpanOptions build()
    {
        String s = "";
        if (sampleToLocalSpanStore == null)
        {
            s = String.valueOf("").concat(" sampleToLocalSpanStore");
        }
        if (!s.isEmpty())
        {
            s = String.valueOf(s);
            if (s.length() != 0)
            {
                s = "Missing required properties:".concat(s);
            } else
            {
                s = new String("Missing required properties:");
            }
            throw new IllegalStateException(s);
        } else
        {
            return new AutoValue_EndSpanOptions(sampleToLocalSpanStore.booleanValue(), status);
        }
    }

    public final status setSampleToLocalSpanStore(boolean flag)
    {
        sampleToLocalSpanStore = Boolean.valueOf(flag);
        return this;
    }

    public final sampleToLocalSpanStore setStatus(Status status1)
    {
        status = status1;
        return this;
    }

    ()
    {
    }
}
