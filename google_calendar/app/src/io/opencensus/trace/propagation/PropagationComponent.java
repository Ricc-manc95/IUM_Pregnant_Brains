// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.trace.propagation;


// Referenced classes of package io.opencensus.trace.propagation:
//            BinaryFormat

public abstract class PropagationComponent
{

    public static final PropagationComponent NOOP_PROPAGATION_COMPONENT = new NoopPropagationComponent();

    public PropagationComponent()
    {
    }

    public abstract BinaryFormat getBinaryFormat();


    private class NoopPropagationComponent extends PropagationComponent
    {

        public final BinaryFormat getBinaryFormat()
        {
            return BinaryFormat.NOOP_BINARY_FORMAT;
        }

        NoopPropagationComponent()
        {
        }
    }

}
