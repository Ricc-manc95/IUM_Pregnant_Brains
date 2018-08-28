// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley.toolbox;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import java.io.UnsupportedEncodingException;
import java.util.Map;

// Referenced classes of package com.android.volley.toolbox:
//            HttpHeaderParser

public class StringRequest extends Request
{

    private com.android.volley.Response.Listener mListener;
    private final Object mLock;

    private StringRequest(int i, String s, com.android.volley.Response.Listener listener, com.android.volley.Response.ErrorListener errorlistener)
    {
        super(0, s, errorlistener);
        mLock = new Object();
        mListener = listener;
    }

    public StringRequest(String s, com.android.volley.Response.Listener listener, com.android.volley.Response.ErrorListener errorlistener)
    {
        this(0, s, listener, errorlistener);
    }

    public final void cancel()
    {
        super.cancel();
        synchronized (mLock)
        {
            mListener = null;
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    protected final void deliverResponse(Object obj)
    {
        String s = (String)obj;
        com.android.volley.Response.Listener listener;
        synchronized (mLock)
        {
            listener = mListener;
        }
        if (listener != null)
        {
            listener.onResponse(s);
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public Response parseNetworkResponse(NetworkResponse networkresponse)
    {
        Object obj;
        byte abyte0[];
        abyte0 = networkresponse.data;
        obj = (String)networkresponse.headers.get("Content-Type");
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_113;
        }
        String as[] = ((String) (obj)).split(";", 0);
        int i = 1;
_L3:
        String as1[];
        if (i >= as.length)
        {
            break MISSING_BLOCK_LABEL_113;
        }
        as1 = as[i].trim().split("=", 0);
        if (as1.length != 2 || !as1[0].equals("charset")) goto _L2; else goto _L1
_L1:
        as = as1[1];
_L4:
        try
        {
            as = new String(abyte0, as);
        }
        catch (UnsupportedEncodingException unsupportedencodingexception)
        {
            unsupportedencodingexception = new String(networkresponse.data);
        }
        return new Response(as, HttpHeaderParser.parseCacheHeaders(networkresponse));
_L2:
        i++;
          goto _L3
        as = "ISO-8859-1";
          goto _L4
    }
}
