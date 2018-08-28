// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.hash;

import java.nio.charset.Charset;

// Referenced classes of package com.google.common.hash:
//            HashCode

public interface Hasher
{

    public abstract HashCode hash();

    public abstract Hasher putByte(byte byte0);

    public abstract Hasher putBytes(byte abyte0[], int i, int j);

    public abstract Hasher putString(CharSequence charsequence, Charset charset);
}
