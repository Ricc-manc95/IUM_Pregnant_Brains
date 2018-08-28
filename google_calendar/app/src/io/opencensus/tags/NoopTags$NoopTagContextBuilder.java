// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.tags;


// Referenced classes of package io.opencensus.tags:
//            TagContextBuilder, TagContext, TagKey, TagValue

final class  extends TagContextBuilder
{

    public static final TagContextBuilder INSTANCE = new <init>();

    public final TagContext build()
    {
        return E;
    }

    public final TagContextBuilder put(TagKey tagkey, TagValue tagvalue)
    {
        if (tagkey == null)
        {
            throw new NullPointerException("key");
        }
        if (tagvalue == null)
        {
            throw new NullPointerException("value");
        } else
        {
            return this;
        }
    }


    private ()
    {
    }
}
