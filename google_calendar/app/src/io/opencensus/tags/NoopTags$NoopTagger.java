// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.tags;


// Referenced classes of package io.opencensus.tags:
//            Tagger, TagContext, TagContextBuilder

final class extBuilder extends Tagger
{

    public static final Tagger INSTANCE = new <init>();

    public final TagContext empty()
    {
        return ext.INSTANCE;
    }

    public final TagContext getCurrentTagContext()
    {
        return ext.INSTANCE;
    }

    public final TagContextBuilder toBuilder(TagContext tagcontext)
    {
        if (tagcontext == null)
        {
            throw new NullPointerException("tags");
        } else
        {
            return extBuilder.INSTANCE;
        }
    }


    private extBuilder()
    {
    }
}
