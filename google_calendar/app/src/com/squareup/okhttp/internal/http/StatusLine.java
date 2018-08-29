// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.squareup.okhttp.internal.http;

import com.squareup.okhttp.Protocol;
import java.io.IOException;
import java.net.ProtocolException;

public final class StatusLine
{

    public final int code;
    public final String message;
    private final Protocol protocol;

    private StatusLine(Protocol protocol1, int i, String s)
    {
        protocol = protocol1;
        code = i;
        message = s;
    }

    public static StatusLine parse(String s)
        throws IOException
    {
        byte byte0 = 9;
        Object obj;
        if (s.startsWith("HTTP/1."))
        {
            if (s.length() < 9 || s.charAt(8) != ' ')
            {
                throw new ProtocolException((new StringBuilder("Unexpected status line: ")).append(s).toString());
            }
            int i = s.charAt(7) - 48;
            if (i == 0)
            {
                obj = Protocol.HTTP_1_0;
            } else
            if (i == 1)
            {
                obj = Protocol.HTTP_1_1;
            } else
            {
                throw new ProtocolException((new StringBuilder("Unexpected status line: ")).append(s).toString());
            }
        } else
        if (s.startsWith("ICY "))
        {
            obj = Protocol.HTTP_1_0;
            byte0 = 4;
        } else
        {
            throw new ProtocolException((new StringBuilder("Unexpected status line: ")).append(s).toString());
        }
        if (s.length() < byte0 + 3)
        {
            throw new ProtocolException((new StringBuilder("Unexpected status line: ")).append(s).toString());
        }
        int j;
        try
        {
            j = Integer.parseInt(s.substring(byte0, byte0 + 3));
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            throw new ProtocolException((new StringBuilder("Unexpected status line: ")).append(s).toString());
        }
        if (s.length() > byte0 + 3)
        {
            if (s.charAt(byte0 + 3) != ' ')
            {
                throw new ProtocolException((new StringBuilder("Unexpected status line: ")).append(s).toString());
            }
            s = s.substring(byte0 + 4);
        } else
        {
            s = "";
        }
        return new StatusLine(((Protocol) (obj)), j, s);
    }

    public final String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        String s;
        if (protocol == Protocol.HTTP_1_0)
        {
            s = "HTTP/1.0";
        } else
        {
            s = "HTTP/1.1";
        }
        stringbuilder.append(s);
        stringbuilder.append(' ').append(code);
        if (message != null)
        {
            stringbuilder.append(' ').append(message);
        }
        return stringbuilder.toString();
    }
}