// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.trace;


// Referenced classes of package io.opencensus.trace:
//            Status

public abstract class EndSpanOptions
{

    EndSpanOptions()
    {
    }

    public static Builder builder()
    {
        return (new AutoValue_EndSpanOptions.Builder()).setSampleToLocalSpanStore(false);
    }

    public abstract boolean getSampleToLocalSpanStore();

    public abstract Status getStatus();

    static 
    {
        (new AutoValue_EndSpanOptions.Builder()).setSampleToLocalSpanStore(false).build();
    }

    private class Builder
    {

        public abstract EndSpanOptions build();

        public abstract Builder setSampleToLocalSpanStore(boolean flag);

        public abstract Builder setStatus(Status status);

        Builder()
        {
        }
    }

}
