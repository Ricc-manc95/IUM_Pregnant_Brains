// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.Status;

// Referenced classes of package io.grpc.internal:
//            GrpcUtil

public static final class status extends Enum
{

    private static final codeMap $VALUES[];
    private static final codeMap CANCEL;
    private static final codeMap COMPRESSION_ERROR;
    private static final codeMap CONNECT_ERROR;
    private static final codeMap ENHANCE_YOUR_CALM;
    private static final codeMap FLOW_CONTROL_ERROR;
    private static final codeMap FRAME_SIZE_ERROR;
    private static final codeMap HTTP_1_1_REQUIRED;
    private static final codeMap INADEQUATE_SECURITY;
    private static final codeMap INTERNAL_ERROR;
    private static final codeMap NO_ERROR;
    private static final codeMap PROTOCOL_ERROR;
    private static final codeMap REFUSED_STREAM;
    private static final codeMap SETTINGS_TIMEOUT;
    private static final codeMap STREAM_CLOSED;
    private static final codeMap codeMap[];
    private final int code;
    private final Status status;

    public static Status statusForCode(long l)
    {
        status status1;
        if (l >= (long)codeMap.length || l < 0L)
        {
            status1 = null;
        } else
        {
            status1 = codeMap[(int)l];
        }
        if (status1 == null)
        {
            return Status.fromCodeValue(INTERNAL_ERROR.status.code.status).withDescription((new StringBuilder(52)).append("Unrecognized HTTP/2 error code: ").append(l).toString());
        } else
        {
            return status1.status;
        }
    }

    public static status[] values()
    {
        return (status[])$VALUES.clone();
    }

    static 
    {
        int i = 0;
        NO_ERROR = new <init>("NO_ERROR", 0, 0, Status.UNAVAILABLE);
        PROTOCOL_ERROR = new <init>("PROTOCOL_ERROR", 1, 1, Status.INTERNAL);
        INTERNAL_ERROR = new <init>("INTERNAL_ERROR", 2, 2, Status.INTERNAL);
        FLOW_CONTROL_ERROR = new <init>("FLOW_CONTROL_ERROR", 3, 3, Status.INTERNAL);
        SETTINGS_TIMEOUT = new <init>("SETTINGS_TIMEOUT", 4, 4, Status.INTERNAL);
        STREAM_CLOSED = new <init>("STREAM_CLOSED", 5, 5, Status.INTERNAL);
        FRAME_SIZE_ERROR = new <init>("FRAME_SIZE_ERROR", 6, 6, Status.INTERNAL);
        REFUSED_STREAM = new <init>("REFUSED_STREAM", 7, 7, Status.UNAVAILABLE);
        CANCEL = new <init>("CANCEL", 8, 8, Status.CANCELLED);
        COMPRESSION_ERROR = new <init>("COMPRESSION_ERROR", 9, 9, Status.INTERNAL);
        CONNECT_ERROR = new <init>("CONNECT_ERROR", 10, 10, Status.INTERNAL);
        ENHANCE_YOUR_CALM = new <init>("ENHANCE_YOUR_CALM", 11, 11, Status.RESOURCE_EXHAUSTED.withDescription("Bandwidth exhausted"));
        INADEQUATE_SECURITY = new <init>("INADEQUATE_SECURITY", 12, 12, Status.PERMISSION_DENIED.withDescription("Permission denied as protocol is not secure enough to call"));
        HTTP_1_1_REQUIRED = new <init>("HTTP_1_1_REQUIRED", 13, 13, Status.UNKNOWN);
        $VALUES = (new .VALUES[] {
            NO_ERROR, PROTOCOL_ERROR, INTERNAL_ERROR, FLOW_CONTROL_ERROR, SETTINGS_TIMEOUT, STREAM_CLOSED, FRAME_SIZE_ERROR, REFUSED_STREAM, CANCEL, COMPRESSION_ERROR, 
            CONNECT_ERROR, ENHANCE_YOUR_CALM, INADEQUATE_SECURITY, HTTP_1_1_REQUIRED
        });
        r_3B_.clone aclone[] = values();
        r_3B_.clone aclone1[] = new values[aclone[aclone.length - 1].code + 1];
        for (int j = aclone.length; i < j; i++)
        {
            r_3B_.clone clone = aclone[i];
            aclone1[clone.code] = clone;
        }

        codeMap = aclone1;
    }

    private (String s, int i, int j, Status status1)
    {
        super(s, i);
        code = j;
        s = String.valueOf(name());
        if (s.length() != 0)
        {
            s = "HTTP/2 error code: ".concat(s);
        } else
        {
            s = new String("HTTP/2 error code: ");
        }
        status = status1.augmentDescription(s);
    }
}
