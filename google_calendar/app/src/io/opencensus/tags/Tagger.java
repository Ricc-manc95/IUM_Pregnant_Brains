// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.tags;


// Referenced classes of package io.opencensus.tags:
//            TagContext, TagContextBuilder

public abstract class Tagger
{

    public Tagger()
    {
    }

    public abstract TagContext empty();

    public abstract TagContext getCurrentTagContext();

    public abstract TagContextBuilder toBuilder(TagContext tagcontext);
}
