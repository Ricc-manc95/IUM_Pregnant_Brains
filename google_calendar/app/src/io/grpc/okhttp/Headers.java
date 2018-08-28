// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.okhttp;

import io.grpc.InternalMetadata;
import io.grpc.Metadata;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.TransportFrameUtil;
import io.grpc.okhttp.internal.framed.Header;
import java.util.ArrayList;
import java.util.List;
import okio.ByteString;

final class Headers
{

    private static final Header CONTENT_TYPE_HEADER;
    private static final Header METHOD_GET_HEADER;
    private static final Header METHOD_HEADER;
    private static final Header SCHEME_HEADER;
    private static final Header TE_HEADER = new Header("te", "trailers");

    public static List createRequestHeaders(Metadata metadata, String s, String s1, String s2, boolean flag)
    {
        if (metadata == null)
        {
            throw new NullPointerException(String.valueOf("headers"));
        }
        if (s == null)
        {
            throw new NullPointerException(String.valueOf("defaultPath"));
        }
        if (s1 == null)
        {
            throw new NullPointerException(String.valueOf("authority"));
        }
        metadata.discardAll(GrpcUtil.CONTENT_TYPE_KEY);
        metadata.discardAll(GrpcUtil.TE_HEADER);
        metadata.discardAll(GrpcUtil.USER_AGENT_KEY);
        ArrayList arraylist = new ArrayList(InternalMetadata.headerCount(metadata) + 7);
        arraylist.add(SCHEME_HEADER);
        int i;
        if (flag)
        {
            arraylist.add(METHOD_GET_HEADER);
        } else
        {
            arraylist.add(METHOD_HEADER);
        }
        arraylist.add(new Header(Header.TARGET_AUTHORITY, s1));
        arraylist.add(new Header(Header.TARGET_PATH, s));
        arraylist.add(new Header(GrpcUtil.USER_AGENT_KEY.name, s2));
        arraylist.add(CONTENT_TYPE_HEADER);
        arraylist.add(TE_HEADER);
        metadata = TransportFrameUtil.toHttp2Headers(metadata);
        i = 0;
        while (i < metadata.length) 
        {
            s = ByteString.of(metadata[i]);
            s1 = s.utf8();
            boolean flag1;
            if (!s1.startsWith(":") && !GrpcUtil.CONTENT_TYPE_KEY.name.equalsIgnoreCase(s1) && !GrpcUtil.USER_AGENT_KEY.name.equalsIgnoreCase(s1))
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (flag1)
            {
                arraylist.add(new Header(s, ByteString.of(metadata[i + 1])));
            }
            i += 2;
        }
        return arraylist;
    }

    static 
    {
        SCHEME_HEADER = new Header(Header.TARGET_SCHEME, "https");
        METHOD_HEADER = new Header(Header.TARGET_METHOD, "POST");
        METHOD_GET_HEADER = new Header(Header.TARGET_METHOD, "GET");
        CONTENT_TYPE_HEADER = new Header(GrpcUtil.CONTENT_TYPE_KEY.name, "application/grpc");
    }
}
