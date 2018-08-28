// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.http.javanet;

import com.google.api.client.http.LowLevelHttpResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

final class NetHttpResponse extends LowLevelHttpResponse
{

    public final HttpURLConnection connection;
    private final ArrayList headerNames = new ArrayList();
    private final ArrayList headerValues = new ArrayList();
    private final int responseCode;
    private final String responseMessage;

    NetHttpResponse(HttpURLConnection httpurlconnection)
        throws IOException
    {
        connection = httpurlconnection;
        int j = httpurlconnection.getResponseCode();
        int i = j;
        if (j == -1)
        {
            i = 0;
        }
        responseCode = i;
        responseMessage = httpurlconnection.getResponseMessage();
        ArrayList arraylist = headerNames;
        ArrayList arraylist1 = headerValues;
        for (httpurlconnection = httpurlconnection.getHeaderFields().entrySet().iterator(); httpurlconnection.hasNext();)
        {
            Object obj = (java.util.Map.Entry)httpurlconnection.next();
            String s = (String)((java.util.Map.Entry) (obj)).getKey();
            if (s != null)
            {
                obj = ((List)((java.util.Map.Entry) (obj)).getValue()).iterator();
                while (((Iterator) (obj)).hasNext()) 
                {
                    String s1 = (String)((Iterator) (obj)).next();
                    if (s1 != null)
                    {
                        arraylist.add(s);
                        arraylist1.add(s1);
                    }
                }
            }
        }

    }

    public final void disconnect()
    {
        connection.disconnect();
    }

    public final InputStream getContent()
        throws IOException
    {
        Object obj;
        try
        {
            obj = connection.getInputStream();
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            obj = connection.getErrorStream();
        }
        if (obj == null)
        {
            return null;
        } else
        {
            return new SizeValidatingInputStream(((InputStream) (obj)));
        }
    }

    public final String getContentEncoding()
    {
        return connection.getContentEncoding();
    }

    public final String getContentType()
    {
        return connection.getHeaderField("Content-Type");
    }

    public final int getHeaderCount()
    {
        return headerNames.size();
    }

    public final String getHeaderName(int i)
    {
        return (String)headerNames.get(i);
    }

    public final String getHeaderValue(int i)
    {
        return (String)headerValues.get(i);
    }

    public final String getReasonPhrase()
    {
        return responseMessage;
    }

    public final int getStatusCode()
    {
        return responseCode;
    }

    public final String getStatusLine()
    {
        String s = connection.getHeaderField(0);
        if (s != null && s.startsWith("HTTP/1."))
        {
            return s;
        } else
        {
            return null;
        }
    }

    private class SizeValidatingInputStream extends FilterInputStream
    {

        private long bytesRead;
        private final NetHttpResponse this$0;

        private final void throwIfFalseEOF()
            throws IOException
        {
            String s = connection.getHeaderField("Content-Length");
            long l;
            if (s == null)
            {
                l = -1L;
            } else
            {
                l = Long.parseLong(s);
            }
            while (l == -1L || bytesRead == 0L || bytesRead >= l) 
            {
                return;
            }
            long l1 = bytesRead;
            throw new IOException((new StringBuilder(102)).append("Connection closed prematurely: bytesRead = ").append(l1).append(", Content-Length = ").append(l).toString());
        }

        public final int read()
            throws IOException
        {
            int i = in.read();
            if (i == -1)
            {
                throwIfFalseEOF();
                return i;
            } else
            {
                bytesRead = bytesRead + 1L;
                return i;
            }
        }

        public final int read(byte abyte0[], int i, int j)
            throws IOException
        {
            i = in.read(abyte0, i, j);
            if (i == -1)
            {
                throwIfFalseEOF();
                return i;
            } else
            {
                bytesRead = bytesRead + (long)i;
                return i;
            }
        }

        public SizeValidatingInputStream(InputStream inputstream)
        {
            this$0 = NetHttpResponse.this;
            super(inputstream);
            bytesRead = 0L;
        }
    }

}
