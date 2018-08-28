// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.okhttp.internal.framed;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import okio.ByteString;

// Referenced classes of package io.grpc.okhttp.internal.framed:
//            Header

final class Hpack
{

    public static final Map NAME_TO_FIRST_INDEX;
    public static final Header STATIC_HEADER_TABLE[];

    static ByteString checkLowercase(ByteString bytestring)
        throws IOException
    {
        int i = 0;
        for (int j = bytestring.size(); i < j; i++)
        {
            byte byte0 = bytestring.getByte(i);
            if (byte0 >= 65 && byte0 <= 90)
            {
                bytestring = String.valueOf(bytestring.utf8());
                if (bytestring.length() != 0)
                {
                    bytestring = "PROTOCOL_ERROR response malformed: mixed case name: ".concat(bytestring);
                } else
                {
                    bytestring = new String("PROTOCOL_ERROR response malformed: mixed case name: ");
                }
                throw new IOException(bytestring);
            }
        }

        return bytestring;
    }

    static 
    {
        int i = 0;
        STATIC_HEADER_TABLE = (new Header[] {
            new Header(Header.TARGET_AUTHORITY, ""), new Header(Header.TARGET_METHOD, "GET"), new Header(Header.TARGET_METHOD, "POST"), new Header(Header.TARGET_PATH, "/"), new Header(Header.TARGET_PATH, "/index.html"), new Header(Header.TARGET_SCHEME, "http"), new Header(Header.TARGET_SCHEME, "https"), new Header(Header.RESPONSE_STATUS, "200"), new Header(Header.RESPONSE_STATUS, "204"), new Header(Header.RESPONSE_STATUS, "206"), 
            new Header(Header.RESPONSE_STATUS, "304"), new Header(Header.RESPONSE_STATUS, "400"), new Header(Header.RESPONSE_STATUS, "404"), new Header(Header.RESPONSE_STATUS, "500"), new Header("accept-charset", ""), new Header("accept-encoding", "gzip, deflate"), new Header("accept-language", ""), new Header("accept-ranges", ""), new Header("accept", ""), new Header("access-control-allow-origin", ""), 
            new Header("age", ""), new Header("allow", ""), new Header("authorization", ""), new Header("cache-control", ""), new Header("content-disposition", ""), new Header("content-encoding", ""), new Header("content-language", ""), new Header("content-length", ""), new Header("content-location", ""), new Header("content-range", ""), 
            new Header("content-type", ""), new Header("cookie", ""), new Header("date", ""), new Header("etag", ""), new Header("expect", ""), new Header("expires", ""), new Header("from", ""), new Header("host", ""), new Header("if-match", ""), new Header("if-modified-since", ""), 
            new Header("if-none-match", ""), new Header("if-range", ""), new Header("if-unmodified-since", ""), new Header("last-modified", ""), new Header("link", ""), new Header("location", ""), new Header("max-forwards", ""), new Header("proxy-authenticate", ""), new Header("proxy-authorization", ""), new Header("range", ""), 
            new Header("referer", ""), new Header("refresh", ""), new Header("retry-after", ""), new Header("server", ""), new Header("set-cookie", ""), new Header("strict-transport-security", ""), new Header("transfer-encoding", ""), new Header("user-agent", ""), new Header("vary", ""), new Header("via", ""), 
            new Header("www-authenticate", "")
        });
        LinkedHashMap linkedhashmap = new LinkedHashMap(STATIC_HEADER_TABLE.length);
        for (; i < STATIC_HEADER_TABLE.length; i++)
        {
            if (!linkedhashmap.containsKey(STATIC_HEADER_TABLE[i].name))
            {
                linkedhashmap.put(STATIC_HEADER_TABLE[i].name, Integer.valueOf(i));
            }
        }

        NAME_TO_FIRST_INDEX = Collections.unmodifiableMap(linkedhashmap);
    }
}
