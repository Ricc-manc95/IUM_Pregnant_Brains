// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.okhttp.internal.framed;


public final class ErrorCode extends Enum
{

    private static final ErrorCode $VALUES[];
    public static final ErrorCode CANCEL;
    public static final ErrorCode COMPRESSION_ERROR;
    public static final ErrorCode CONNECT_ERROR;
    public static final ErrorCode ENHANCE_YOUR_CALM;
    public static final ErrorCode FLOW_CONTROL_ERROR;
    public static final ErrorCode FRAME_TOO_LARGE;
    private static final ErrorCode HTTP_1_1_REQUIRED;
    public static final ErrorCode INADEQUATE_SECURITY;
    public static final ErrorCode INTERNAL_ERROR;
    private static final ErrorCode INVALID_CREDENTIALS;
    public static final ErrorCode INVALID_STREAM;
    public static final ErrorCode NO_ERROR;
    public static final ErrorCode PROTOCOL_ERROR;
    public static final ErrorCode REFUSED_STREAM;
    private static final ErrorCode STREAM_ALREADY_CLOSED;
    public static final ErrorCode STREAM_CLOSED;
    private static final ErrorCode STREAM_IN_USE;
    private static final ErrorCode UNSUPPORTED_VERSION;
    public final int httpCode;

    private ErrorCode(String s, int i, int j, int k, int l)
    {
        super(s, i);
        httpCode = j;
    }

    public static ErrorCode fromHttp2(int i)
    {
        ErrorCode aerrorcode[] = values();
        int k = aerrorcode.length;
        for (int j = 0; j < k; j++)
        {
            ErrorCode errorcode = aerrorcode[j];
            if (errorcode.httpCode == i)
            {
                return errorcode;
            }
        }

        return null;
    }

    public static ErrorCode[] values()
    {
        return (ErrorCode[])$VALUES.clone();
    }

    static 
    {
        NO_ERROR = new ErrorCode("NO_ERROR", 0, 0, -1, 0);
        PROTOCOL_ERROR = new ErrorCode("PROTOCOL_ERROR", 1, 1, 1, 1);
        INVALID_STREAM = new ErrorCode("INVALID_STREAM", 2, 1, 2, -1);
        UNSUPPORTED_VERSION = new ErrorCode("UNSUPPORTED_VERSION", 3, 1, 4, -1);
        STREAM_IN_USE = new ErrorCode("STREAM_IN_USE", 4, 1, 8, -1);
        STREAM_ALREADY_CLOSED = new ErrorCode("STREAM_ALREADY_CLOSED", 5, 1, 9, -1);
        INTERNAL_ERROR = new ErrorCode("INTERNAL_ERROR", 6, 2, 6, 2);
        FLOW_CONTROL_ERROR = new ErrorCode("FLOW_CONTROL_ERROR", 7, 3, 7, -1);
        STREAM_CLOSED = new ErrorCode("STREAM_CLOSED", 8, 5, -1, -1);
        FRAME_TOO_LARGE = new ErrorCode("FRAME_TOO_LARGE", 9, 6, 11, -1);
        REFUSED_STREAM = new ErrorCode("REFUSED_STREAM", 10, 7, 3, -1);
        CANCEL = new ErrorCode("CANCEL", 11, 8, 5, -1);
        COMPRESSION_ERROR = new ErrorCode("COMPRESSION_ERROR", 12, 9, -1, -1);
        CONNECT_ERROR = new ErrorCode("CONNECT_ERROR", 13, 10, -1, -1);
        ENHANCE_YOUR_CALM = new ErrorCode("ENHANCE_YOUR_CALM", 14, 11, -1, -1);
        INADEQUATE_SECURITY = new ErrorCode("INADEQUATE_SECURITY", 15, 12, -1, -1);
        HTTP_1_1_REQUIRED = new ErrorCode("HTTP_1_1_REQUIRED", 16, 13, -1, -1);
        INVALID_CREDENTIALS = new ErrorCode("INVALID_CREDENTIALS", 17, -1, 10, -1);
        $VALUES = (new ErrorCode[] {
            NO_ERROR, PROTOCOL_ERROR, INVALID_STREAM, UNSUPPORTED_VERSION, STREAM_IN_USE, STREAM_ALREADY_CLOSED, INTERNAL_ERROR, FLOW_CONTROL_ERROR, STREAM_CLOSED, FRAME_TOO_LARGE, 
            REFUSED_STREAM, CANCEL, COMPRESSION_ERROR, CONNECT_ERROR, ENHANCE_YOUR_CALM, INADEQUATE_SECURITY, HTTP_1_1_REQUIRED, INVALID_CREDENTIALS
        });
    }
}
