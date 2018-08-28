// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.habit;


public final class HabitUtil
{

    public static int checkFitIntegrationStatus(int i)
    {
        switch (i)
        {
        default:
            throw new IllegalStateException("Invalid integrations status value");

        case 0: // '\0'
        case 10: // '\n'
        case 20: // '\024'
            return i;
        }
    }

    static int checkInterval(int i)
    {
        switch (i)
        {
        default:
            throw new IllegalStateException((new StringBuilder(31)).append("Invalid type value: ").append(i).toString());

        case 0: // '\0'
        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
            return i;
        }
    }

    public static int checkType(int i)
    {
        switch (i)
        {
        default:
            throw new IllegalStateException("Invalid type value");

        case 0: // '\0'
        case 256: 
        case 257: 
        case 258: 
        case 259: 
        case 260: 
        case 261: 
        case 262: 
        case 263: 
        case 264: 
        case 265: 
        case 266: 
        case 267: 
        case 268: 
        case 269: 
        case 270: 
        case 512: 
        case 513: 
        case 514: 
        case 515: 
        case 516: 
        case 517: 
        case 518: 
        case 519: 
        case 520: 
        case 521: 
        case 522: 
        case 523: 
        case 524: 
        case 768: 
        case 769: 
        case 770: 
        case 771: 
        case 772: 
        case 773: 
        case 774: 
        case 775: 
        case 776: 
        case 777: 
        case 778: 
        case 779: 
        case 780: 
        case 1024: 
        case 1025: 
        case 1026: 
        case 1027: 
        case 1028: 
        case 1029: 
        case 1030: 
        case 1031: 
        case 1032: 
        case 1033: 
        case 1034: 
        case 1035: 
        case 1036: 
        case 1037: 
        case 1280: 
        case 1281: 
        case 1282: 
        case 1283: 
        case 1284: 
        case 1285: 
        case 1286: 
        case 1287: 
        case 1288: 
        case 1289: 
        case 1290: 
        case 1291: 
        case 1292: 
            return i;
        }
    }

    static int checkVisibility(int i)
    {
        switch (i)
        {
        default:
            throw new IllegalStateException("Invalid visibility value");

        case 0: // '\0'
        case 1: // '\001'
        case 2: // '\002'
            return i;
        }
    }

    static int intToHabitInstanceStatus(int i)
    {
        switch (i)
        {
        default:
            throw new IllegalStateException((new StringBuilder(31)).append("Invalid type value: ").append(i).toString());

        case 0: // '\0'
        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
            return i;
        }
    }
}
