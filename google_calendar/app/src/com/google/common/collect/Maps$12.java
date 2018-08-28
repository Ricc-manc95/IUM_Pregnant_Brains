// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;


// Referenced classes of package com.google.common.collect:
//            AbstractMapEntry

final class MapEntry extends AbstractMapEntry
{

    private final java.util.y val$entry;
    private final ryTransformer val$transformer;

    public final Object getKey()
    {
        return val$entry.getKey();
    }

    public final Object getValue()
    {
        ryTransformer rytransformer = val$transformer;
        val$entry.getKey();
        return rytransformer._mth5166KOBMC4NMOOBECSNKUOJACLHN8EQCD9GNCO9FDHGMSPPF9TH6KPB3EGTIIJ3AC5R62BRCC5N6EBQFC9L6AORK7C______0(val$entry.getValue());
    }

    ryTransformer()
    {
        val$entry = y;
        val$transformer = rytransformer;
        super();
    }
}
