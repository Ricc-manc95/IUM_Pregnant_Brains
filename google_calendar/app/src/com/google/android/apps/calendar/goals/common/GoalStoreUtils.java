// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.goals.common;


public final class GoalStoreUtils
{

    public static int apiTypeToProtoType(int i)
    {
        switch (i)
        {
        default:
            throw new IllegalArgumentException("Invalid type");

        case 256: 
            return 100;

        case 258: 
            return 102;

        case 259: 
            return 103;

        case 257: 
            return 101;

        case 260: 
            return 104;

        case 262: 
            return 107;

        case 261: 
            return 105;

        case 263: 
            return 106;

        case 264: 
            return 108;

        case 265: 
            return 109;

        case 266: 
            return 110;

        case 267: 
            return 111;

        case 268: 
            return 112;

        case 269: 
            return 113;

        case 270: 
            return 114;

        case 512: 
            return 200;

        case 514: 
            return 202;

        case 513: 
            return 201;

        case 516: 
            return 203;

        case 515: 
            return 204;

        case 517: 
            return 205;

        case 518: 
            return 206;

        case 519: 
            return 207;

        case 520: 
            return 208;

        case 521: 
            return 209;

        case 522: 
            return 210;

        case 523: 
            return 211;

        case 524: 
            return 212;

        case 768: 
            return 300;

        case 772: 
            return 304;

        case 771: 
            return 303;

        case 769: 
            return 301;

        case 770: 
            return 302;

        case 773: 
            return 305;

        case 774: 
            return 306;

        case 775: 
            return 307;

        case 776: 
            return 308;

        case 777: 
            return 309;

        case 778: 
            return 310;

        case 779: 
            return 311;

        case 780: 
            return 312;

        case 1024: 
            return 400;

        case 1027: 
            return 403;

        case 1026: 
            return 402;

        case 1025: 
            return 401;

        case 1028: 
            return 404;

        case 1029: 
            return 405;

        case 1030: 
            return 406;

        case 1031: 
            return 407;

        case 1032: 
            return 408;

        case 1033: 
            return 409;

        case 1034: 
            return 410;

        case 1035: 
            return 411;

        case 1036: 
            return 412;

        case 1037: 
            return 413;

        case 1280: 
            return 500;

        case 1283: 
            return 503;

        case 1282: 
            return 502;

        case 1281: 
            return 501;

        case 1284: 
            return 504;

        case 1285: 
            return 505;

        case 1286: 
            return 506;

        case 1287: 
            return 507;

        case 1288: 
            return 508;

        case 1289: 
            return 509;

        case 1290: 
            return 510;

        case 1291: 
            return 511;

        case 1292: 
            return 512;

        case 0: // '\0'
            return 0;
        }
    }

    public static int protoTypeToApiType(int i)
    {
        switch (i)
        {
        default:
            throw new IllegalArgumentException("Invalid type");

        case 100: // 'd'
            return 256;

        case 102: // 'f'
            return 258;

        case 103: // 'g'
            return 259;

        case 101: // 'e'
            return 257;

        case 104: // 'h'
            return 260;

        case 107: // 'k'
            return 262;

        case 105: // 'i'
            return 261;

        case 106: // 'j'
            return 263;

        case 108: // 'l'
            return 264;

        case 109: // 'm'
            return 265;

        case 110: // 'n'
            return 266;

        case 111: // 'o'
            return 267;

        case 112: // 'p'
            return 268;

        case 113: // 'q'
            return 269;

        case 114: // 'r'
            return 270;

        case 200: 
            return 512;

        case 202: 
            return 514;

        case 201: 
            return 513;

        case 203: 
            return 516;

        case 204: 
            return 515;

        case 205: 
            return 517;

        case 206: 
            return 518;

        case 207: 
            return 519;

        case 208: 
            return 520;

        case 209: 
            return 521;

        case 210: 
            return 522;

        case 211: 
            return 523;

        case 212: 
            return 524;

        case 300: 
            return 768;

        case 304: 
            return 772;

        case 303: 
            return 771;

        case 301: 
            return 769;

        case 302: 
            return 770;

        case 305: 
            return 773;

        case 306: 
            return 774;

        case 307: 
            return 775;

        case 308: 
            return 776;

        case 309: 
            return 777;

        case 310: 
            return 778;

        case 311: 
            return 779;

        case 312: 
            return 780;

        case 400: 
            return 1024;

        case 401: 
            return 1025;

        case 403: 
            return 1027;

        case 402: 
            return 1026;

        case 404: 
            return 1028;

        case 405: 
            return 1029;

        case 406: 
            return 1030;

        case 407: 
            return 1031;

        case 408: 
            return 1032;

        case 409: 
            return 1033;

        case 410: 
            return 1034;

        case 411: 
            return 1035;

        case 412: 
            return 1036;

        case 413: 
            return 1037;

        case 500: 
            return 1280;

        case 503: 
            return 1283;

        case 502: 
            return 1282;

        case 501: 
            return 1281;

        case 504: 
            return 1284;

        case 505: 
            return 1285;

        case 506: 
            return 1286;

        case 507: 
            return 1287;

        case 508: 
            return 1288;

        case 509: 
            return 1289;

        case 510: 
            return 1290;

        case 511: 
            return 1291;

        case 512: 
            return 1292;

        case 0: // '\0'
            return 0;
        }
    }
}
