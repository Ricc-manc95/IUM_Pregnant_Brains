// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.api;


public final class CommonStatusCodes
{

    public static String getStatusCodeString(int i)
    {
        switch (i)
        {
        case 1: // '\001'
        case 9: // '\t'
        case 11: // '\013'
        case 12: // '\f'
        default:
            return (new StringBuilder(32)).append("unknown status code: ").append(i).toString();

        case -1: 
            return "SUCCESS_CACHE";

        case 0: // '\0'
            return "SUCCESS";

        case 2: // '\002'
            return "SERVICE_VERSION_UPDATE_REQUIRED";

        case 3: // '\003'
            return "SERVICE_DISABLED";

        case 4: // '\004'
            return "SIGN_IN_REQUIRED";

        case 5: // '\005'
            return "INVALID_ACCOUNT";

        case 6: // '\006'
            return "RESOLUTION_REQUIRED";

        case 7: // '\007'
            return "NETWORK_ERROR";

        case 8: // '\b'
            return "INTERNAL_ERROR";

        case 10: // '\n'
            return "DEVELOPER_ERROR";

        case 13: // '\r'
            return "ERROR";

        case 14: // '\016'
            return "INTERRUPTED";

        case 15: // '\017'
            return "TIMEOUT";

        case 16: // '\020'
            return "CANCELED";

        case 17: // '\021'
            return "API_NOT_CONNECTED";

        case 18: // '\022'
            return "DEAD_CLIENT";
        }
    }
}
