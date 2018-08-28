// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley.toolbox;

import java.io.FilterInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;

// Referenced classes of package com.android.volley.toolbox:
//            HurlStack

final class mConnection extends FilterInputStream
{

    private final HttpURLConnection mConnection;

    public final void close()
        throws IOException
    {
        super.close();
        mConnection.disconnect();
    }

    (HttpURLConnection httpurlconnection)
    {
        super(HurlStack.inputStreamFromConnection(httpurlconnection));
        mConnection = httpurlconnection;
    }
}
