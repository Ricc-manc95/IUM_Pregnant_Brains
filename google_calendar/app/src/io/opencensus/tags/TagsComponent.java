// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.tags;

import io.opencensus.tags.propagation.TagPropagationComponent;

// Referenced classes of package io.opencensus.tags:
//            Tagger

public abstract class TagsComponent
{

    public TagsComponent()
    {
    }

    public abstract TagPropagationComponent getTagPropagationComponent();

    public abstract Tagger getTagger();
}
