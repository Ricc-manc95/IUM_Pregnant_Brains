// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.tags;

import io.opencensus.tags.propagation.TagContextBinarySerializer;
import io.opencensus.tags.propagation.TagPropagationComponent;

final class r extends TagPropagationComponent
{

    public static final TagPropagationComponent INSTANCE = new <init>();

    public final TagContextBinarySerializer getBinarySerializer()
    {
        return er.INSTANCE;
    }


    private r()
    {
    }
}
