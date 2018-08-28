// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.tags;


// Referenced classes of package io.opencensus.tags:
//            TagContext, TagKey, TagValue

public abstract class TagContextBuilder
{

    public TagContextBuilder()
    {
    }

    public abstract TagContext build();

    public abstract TagContextBuilder put(TagKey tagkey, TagValue tagvalue);
}
