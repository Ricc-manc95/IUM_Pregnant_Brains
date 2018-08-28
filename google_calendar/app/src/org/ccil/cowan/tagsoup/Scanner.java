// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.ccil.cowan.tagsoup;

import java.io.IOException;
import java.io.Reader;
import org.xml.sax.SAXException;

// Referenced classes of package org.ccil.cowan.tagsoup:
//            ScanHandler

public interface Scanner
{

    public abstract void resetDocumentLocator(String s, String s1);

    public abstract void scan(Reader reader, ScanHandler scanhandler)
        throws IOException, SAXException;

    public abstract void startCDATA();
}
