// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.util.escape;


// Referenced classes of package com.google.api.client.util.escape:
//            Escaper

public abstract class UnicodeEscaper extends Escaper
{

    public UnicodeEscaper()
    {
    }

    protected abstract char[] escape(int i);

    protected abstract int nextEscapeIndex(CharSequence charsequence, int i, int j);
}
