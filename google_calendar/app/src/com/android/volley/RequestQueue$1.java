// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley;


// Referenced classes of package com.android.volley:
//            Request

final class val.tag
    implements questFilter
{

    private final Object val$tag;

    public final boolean apply(Request request)
    {
        return request.mTag == val$tag;
    }

    questFilter()
    {
        val$tag = obj;
        super();
    }
}
