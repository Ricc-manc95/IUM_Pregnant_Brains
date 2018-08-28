// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.ccil.cowan.tagsoup;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

// Referenced classes of package org.ccil.cowan.tagsoup:
//            AutoDetector, Parser

final class tor
    implements AutoDetector
{

    public final Reader autoDetectingReader(InputStream inputstream)
    {
        return new InputStreamReader(inputstream);
    }

    tor(Parser parser)
    {
    }
}
