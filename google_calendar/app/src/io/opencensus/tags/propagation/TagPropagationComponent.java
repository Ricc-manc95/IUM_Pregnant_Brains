// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.tags.propagation;


// Referenced classes of package io.opencensus.tags.propagation:
//            TagContextBinarySerializer

public abstract class TagPropagationComponent
{

    public TagPropagationComponent()
    {
    }

    public abstract TagContextBinarySerializer getBinarySerializer();
}
