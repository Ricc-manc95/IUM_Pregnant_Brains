// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.util;

import java.io.PrintWriter;

public final class TimeUtils
{

    private static char sFormatStr[] = new char[24];
    private static final Object sFormatSync = new Object();

    private static int accumField(int i, int j, boolean flag, int k)
    {
        if (i > 99 || flag && k >= 3)
        {
            return j + 3;
        }
        if (i > 9 || flag && k >= 2)
        {
            return j + 2;
        }
        if (flag || i > 0)
        {
            return j + 1;
        } else
        {
            return 0;
        }
    }

    public static void formatDuration(long l, long l1, PrintWriter printwriter)
    {
        if (l == 0L)
        {
            printwriter.print("--");
            return;
        } else
        {
            formatDuration(l - l1, printwriter, 0);
            return;
        }
    }

    public static void formatDuration(long l, PrintWriter printwriter)
    {
        formatDuration(l, printwriter, 0);
    }

    private static void formatDuration(long l, PrintWriter printwriter, int i)
    {
        Object obj = sFormatSync;
        obj;
        JVM INSTR monitorenter ;
        char ac[];
        if (sFormatStr.length < 0)
        {
            sFormatStr = new char[0];
        }
        ac = sFormatStr;
        if (l != 0L)
        {
            break MISSING_BLOCK_LABEL_295;
        }
        ac[0] = '0';
        i = 1;
_L1:
        printwriter.print(new String(sFormatStr, 0, i));
        obj;
        JVM INSTR monitorexit ;
        return;
        boolean flag;
        if (l > 0L)
        {
            c = '+';
        } else
        {
            l = -l;
            c = '-';
        }
        k1 = (int)(l % 1000L);
        k = (int)Math.floor(l / 1000L);
        j = 0;
        i = k;
        if (k <= 0x15180)
        {
            break MISSING_BLOCK_LABEL_109;
        }
        j = k / 0x15180;
        i = k - 0x15180 * j;
        if (i <= 3600)
        {
            break MISSING_BLOCK_LABEL_289;
        }
        i1 = i / 3600;
        k = i1;
_L3:
        for (i -= i1 * 3600; i <= 60;)
        {
            break MISSING_BLOCK_LABEL_283;
        }

        j1 = i / 60;
        i1 = j1;
        i -= j1 * 60;
_L2:
        ac[0] = c;
        j = printField(ac, j, 'd', 1, false, 0);
        if (j != 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        j = printField(ac, k, 'h', j, flag, 0);
        if (j != 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        j = printField(ac, i1, 'm', j, flag, 0);
        if (j != 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        i = printField(ac, k1, 'm', printField(ac, i, 's', j, flag, 0), true, 0);
        ac[i] = 's';
        i++;
          goto _L1
        printwriter;
        obj;
        JVM INSTR monitorexit ;
        throw printwriter;
        i1 = 0;
          goto _L2
        k = 0;
          goto _L3
    }

    private static int printField(char ac[], int i, char c, int j, boolean flag, int k)
    {
label0:
        {
label1:
            {
                int l;
                if (!flag)
                {
                    l = j;
                    if (i <= 0)
                    {
                        break label0;
                    }
                }
                int j1;
                if (flag && k >= 3 || i > 99)
                {
                    int i1 = i / 100;
                    ac[j] = (char)(i1 + 48);
                    l = j + 1;
                    i -= i1 * 100;
                } else
                {
                    l = j;
                }
                if ((!flag || k < 2) && i <= 9)
                {
                    j1 = l;
                    k = i;
                    if (j == l)
                    {
                        break label1;
                    }
                }
                j = i / 10;
                ac[l] = (char)(j + 48);
                j1 = l + 1;
                k = i - j * 10;
            }
            ac[j1] = (char)(k + 48);
            i = j1 + 1;
            ac[i] = c;
            l = i + 1;
        }
        return l;
    }

}
