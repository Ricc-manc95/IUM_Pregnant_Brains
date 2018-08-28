// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.hash;

import java.nio.charset.Charset;

// Referenced classes of package com.google.common.hash:
//            HashCode, Hasher

public interface HashFunction
{

    public abstract HashCode hashString(CharSequence charsequence, Charset charset);

    public abstract Hasher newHasher();
}
