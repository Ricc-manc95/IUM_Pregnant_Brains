// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.provider;

import android.content.Context;
import android.support.v4.util.LruCache;
import java.util.concurrent.Callable;

// Referenced classes of package android.support.v4.provider:
//            FontsContractCompat, FontRequest

final class val.id
    implements Callable
{

    private final Context val$context;
    private final String val$id;
    private final FontRequest val$request;
    private final int val$style;

    public final Object call()
        throws Exception
    {
        pefaceResult pefaceresult = FontsContractCompat.getFontInternal(val$context, val$request, val$style);
        if (pefaceresult.mTypeface != null)
        {
            FontsContractCompat.sTypefaceCache.put(val$id, pefaceresult.mTypeface);
        }
        return pefaceresult;
    }

    pefaceResult()
    {
        val$context = context1;
        val$request = fontrequest;
        val$style = i;
        val$id = s;
        super();
    }
}
