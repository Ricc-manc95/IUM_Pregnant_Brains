// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.metriccapture;


final class depth
{

    public final int depth;
    public final String relativeDir;
    public final depth this$0;

    a()
    {
        this$0 = this._cls0.this;
        super();
        relativeDir = "";
        depth = 0;
    }

    depth(depth depth2, String s)
    {
        this$0 = this._cls0.this;
        super();
        if (depth2.depth != 0)
        {
            depth1 = depth2.relativeDir;
            s = (new StringBuilder(String.valueOf(relativeDir.this).length() + 1 + String.valueOf(s).length())).append(relativeDir.this).append('/').append(s).toString();
        }
        relativeDir = s;
        depth = depth2.depth + 1;
    }
}
