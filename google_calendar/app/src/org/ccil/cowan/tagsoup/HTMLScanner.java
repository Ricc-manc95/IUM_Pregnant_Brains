// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.ccil.cowan.tagsoup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;
import java.lang.reflect.Array;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

// Referenced classes of package org.ccil.cowan.tagsoup:
//            Scanner, ScanHandler

public final class HTMLScanner
    implements Scanner, Locator
{

    private static int statetable[];
    private static short statetableIndex[][];
    private static int statetableIndexMaxChar;
    private int theCurrentColumn;
    private int theCurrentLine;
    private int theLastColumn;
    private int theLastLine;
    private int theNextState;
    private char theOutputBuffer[];
    private String thePublicid;
    private int theSize;
    private int theState;
    private String theSystemid;
    private int theWinMap[] = {
        8364, 65533, 8218, 402, 8222, 8230, 8224, 8225, 710, 8240, 
        352, 8249, 338, 65533, 381, 65533, 65533, 8216, 8217, 8220, 
        8221, 8226, 8211, 8212, 732, 8482, 353, 8250, 339, 65533, 
        382, 376
    };

    public HTMLScanner()
    {
        theOutputBuffer = new char[200];
    }

    private final void save(int i, ScanHandler scanhandler)
        throws IOException, SAXException
    {
        int j;
        if (theSize >= theOutputBuffer.length - 20)
        {
            if (theState == 28 || theState == 10)
            {
                scanhandler.pcdata(theOutputBuffer, 0, theSize);
                theSize = 0;
            } else
            {
                scanhandler = new char[theOutputBuffer.length << 1];
                System.arraycopy(theOutputBuffer, 0, scanhandler, 0, theSize + 1);
                theOutputBuffer = scanhandler;
            }
        }
        scanhandler = theOutputBuffer;
        j = theSize;
        theSize = j + 1;
        scanhandler[j] = (char)i;
    }

    public final int getColumnNumber()
    {
        return theLastColumn;
    }

    public final int getLineNumber()
    {
        return theLastLine;
    }

    public final String getPublicId()
    {
        return thePublicid;
    }

    public final String getSystemId()
    {
        return theSystemid;
    }

    public final void resetDocumentLocator(String s, String s1)
    {
        thePublicid = s;
        theSystemid = s1;
        theCurrentColumn = 0;
        theCurrentLine = 0;
        theLastColumn = 0;
        theLastLine = 0;
    }

    public final void scan(Reader reader, ScanHandler scanhandler)
        throws IOException, SAXException
    {
        int i;
        int j;
        theState = 28;
        short word0;
        if (reader instanceof BufferedReader)
        {
            reader = new PushbackReader(reader, 5);
        } else
        {
            reader = new PushbackReader(new BufferedReader(reader), 5);
        }
        i = reader.read();
        if (i != 65279 && i != -1)
        {
            reader.unread(i);
        }
_L35:
        do
        {
            if (theState == 21)
            {
                break MISSING_BLOCK_LABEL_1792;
            }
            i = reader.read();
            j = i;
            if (i >= 128)
            {
                j = i;
                if (i <= 159)
                {
                    j = theWinMap[i - 128];
                }
            }
            i = j;
            if (j == 13)
            {
                j = reader.read();
                i = j;
                if (j != 10)
                {
                    if (j != -1)
                    {
                        reader.unread(j);
                    }
                    i = 10;
                }
            }
            if (i == 10)
            {
                theCurrentLine = theCurrentLine + 1;
                theCurrentColumn = 0;
            } else
            {
                theCurrentColumn = theCurrentColumn + 1;
            }
        } while (i < 32 && i != 10 && i != 9 && i != -1);
        if (i >= -1 && i < statetableIndexMaxChar)
        {
            j = i;
        } else
        {
            j = -2;
        }
        word0 = statetableIndex[theState][j + 2];
        char c;
        int k;
        if (word0 != -1)
        {
            j = statetable[word0 + 2];
            theNextState = statetable[word0 + 3];
        } else
        {
            j = 0;
        }
        j;
        JVM INSTR tableswitch 0 32: default 404
    //                   0 469
    //                   1 512
    //                   2 534
    //                   3 555
    //                   4 584
    //                   5 607
    //                   6 636
    //                   7 680
    //                   8 703
    //                   9 741
    //                   10 1436
    //                   11 1234
    //                   12 1666
    //                   13 828
    //                   14 798
    //                   15 1211
    //                   16 1257
    //                   17 1280
    //                   18 1318
    //                   19 1351
    //                   20 1506
    //                   21 1492
    //                   22 1475
    //                   23 1397
    //                   24 1523
    //                   25 1562
    //                   26 1585
    //                   27 1623
    //                   28 523
    //                   29 1633
    //                   30 1643
    //                   31 1727
    //                   32 1752;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L24 _L25 _L26 _L27 _L28 _L29 _L30 _L31 _L32 _L33 _L34
_L34:
        break MISSING_BLOCK_LABEL_1752;
_L30:
        break; /* Loop/switch isn't completed */
_L1:
        throw new Error("Can't process state " + j);
_L2:
        throw new Error("HTMLScanner can't cope with " + Integer.toString(i) + " in state " + Integer.toString(theState));
_L3:
        scanhandler.adup$51DK6IA955B0____0();
        theSize = 0;
_L36:
        theState = theNextState;
          goto _L35
_L4:
        scanhandler.adup$51DK6IA955B0____0();
        theSize = 0;
        save(i, scanhandler);
          goto _L36
_L5:
        scanhandler.adup$51DK6IA955B0____0();
        theSize = 0;
        scanhandler.stagc(theOutputBuffer, 0, theSize);
          goto _L36
_L6:
        scanhandler.aname(theOutputBuffer, 0, theSize);
        theSize = 0;
          goto _L36
_L7:
        scanhandler.aname(theOutputBuffer, 0, theSize);
        theSize = 0;
        scanhandler.adup$51DK6IA955B0____0();
          goto _L36
_L8:
        scanhandler.aname(theOutputBuffer, 0, theSize);
        theSize = 0;
        scanhandler.adup$51DK6IA955B0____0();
        scanhandler.stagc(theOutputBuffer, 0, theSize);
          goto _L36
_L9:
        scanhandler.aval(theOutputBuffer, 0, theSize);
        theSize = 0;
          goto _L36
_L10:
        scanhandler.aval(theOutputBuffer, 0, theSize);
        theSize = 0;
        scanhandler.stagc(theOutputBuffer, 0, theSize);
          goto _L36
_L11:
        theLastColumn = theCurrentColumn;
        theLastLine = theCurrentLine;
        if (theSize > 1)
        {
            theSize = theSize - 2;
        }
        scanhandler.pcdata(theOutputBuffer, 0, theSize);
        theSize = 0;
          goto _L36
_L16:
        scanhandler.pcdata(theOutputBuffer, 0, theSize);
        theSize = 0;
        save(i, scanhandler);
          goto _L36
_L15:
        theLastColumn = theCurrentColumn;
        theLastLine = theCurrentLine;
        c = (char)i;
        if (theState == 23 && c == '#')
        {
            theNextState = 27;
            save(i, scanhandler);
        } else
        if (theState == 27 && (c == 'x' || c == 'X'))
        {
            theNextState = 35;
            save(i, scanhandler);
        } else
        if (theState == 23 && Character.isLetterOrDigit(c))
        {
            save(i, scanhandler);
        } else
        if (theState == 27 && Character.isDigit(c))
        {
            save(i, scanhandler);
        } else
        if (theState == 35 && (Character.isDigit(c) || "abcdefABCDEF".indexOf(c) != -1))
        {
            save(i, scanhandler);
        } else
        {
            scanhandler.entity(theOutputBuffer, 1, theSize - 1);
            k = scanhandler.getEntity();
            if (k != 0)
            {
                theSize = 0;
                j = k;
                if (k >= 128)
                {
                    j = k;
                    if (k <= 159)
                    {
                        j = theWinMap[k - 128];
                    }
                }
                if (j >= 32 && (j < 55296 || j > 57343))
                {
                    if (j <= 65535)
                    {
                        save(j, scanhandler);
                    } else
                    {
                        j -= 0x10000;
                        save((j >> 10) + 55296, scanhandler);
                        save((j & 0x3ff) + 56320, scanhandler);
                    }
                }
                if (i != 59)
                {
                    if (i != -1)
                    {
                        reader.unread(i);
                    }
                    theCurrentColumn = theCurrentColumn - 1;
                }
            } else
            {
                if (i != -1)
                {
                    reader.unread(i);
                }
                theCurrentColumn = theCurrentColumn - 1;
            }
            theNextState = 28;
        }
          goto _L36
_L17:
        scanhandler.etag(theOutputBuffer, 0, theSize);
        theSize = 0;
          goto _L36
_L13:
        scanhandler.decl(theOutputBuffer, 0, theSize);
        theSize = 0;
          goto _L36
_L18:
        scanhandler.gi(theOutputBuffer, 0, theSize);
        theSize = 0;
          goto _L36
_L19:
        scanhandler.gi(theOutputBuffer, 0, theSize);
        theSize = 0;
        scanhandler.stagc(theOutputBuffer, 0, theSize);
          goto _L36
_L20:
        theLastColumn = theCurrentColumn;
        theLastLine = theCurrentLine;
        save(60, scanhandler);
        save(i, scanhandler);
          goto _L36
_L21:
        theLastColumn = theCurrentColumn;
        theLastLine = theCurrentLine;
        save(60, scanhandler);
        scanhandler.pcdata(theOutputBuffer, 0, theSize);
        theSize = 0;
          goto _L36
_L25:
        theLastColumn = theCurrentColumn;
        theLastLine = theCurrentLine;
        scanhandler.pcdata(theOutputBuffer, 0, theSize);
        theSize = 0;
          goto _L36
_L12:
        theLastColumn = theCurrentColumn;
        theLastLine = theCurrentLine;
        scanhandler.cmnt(theOutputBuffer, 0, theSize);
        theSize = 0;
          goto _L36
_L24:
        save(45, scanhandler);
        save(32, scanhandler);
          goto _L36
_L23:
        save(45, scanhandler);
        save(32, scanhandler);
_L22:
        save(45, scanhandler);
        save(i, scanhandler);
          goto _L36
_L26:
        theLastColumn = theCurrentColumn;
        theLastLine = theCurrentLine;
        scanhandler.pi(theOutputBuffer, 0, theSize);
        theSize = 0;
          goto _L36
_L27:
        scanhandler.pitarget(theOutputBuffer, 0, theSize);
        theSize = 0;
          goto _L36
_L28:
        scanhandler.pitarget(theOutputBuffer, 0, theSize);
        theSize = 0;
        scanhandler.pi(theOutputBuffer, 0, theSize);
          goto _L36
_L29:
        save(i, scanhandler);
          goto _L36
_L31:
        save(32, scanhandler);
          goto _L36
_L32:
        scanhandler.stagc(theOutputBuffer, 0, theSize);
        theSize = 0;
          goto _L36
_L14:
        theLastColumn = theCurrentColumn;
        theLastLine = theCurrentLine;
        if (theSize > 0)
        {
            scanhandler.gi(theOutputBuffer, 0, theSize);
        }
        theSize = 0;
        scanhandler.stage(theOutputBuffer, 0, theSize);
          goto _L36
_L33:
        if (i != -1)
        {
            reader.unread(i);
        }
        theCurrentColumn = theCurrentColumn - 1;
          goto _L36
        if (theSize > 0)
        {
            theSize = theSize - 1;
        }
        scanhandler.pcdata(theOutputBuffer, 0, theSize);
        theSize = 0;
          goto _L36
        scanhandler.eof$51DK6IA955B0____0();
        return;
    }

    public final void startCDATA()
    {
        theNextState = 10;
    }

    static 
    {
        int i1;
        int j1;
        int k1;
        statetable = (new int[] {
            1, 47, 5, 22, 1, 61, 4, 3, 1, 62, 
            6, 28, 1, 0, 27, 1, 1, -1, 6, 21, 
            1, 32, 4, 24, 1, 10, 4, 24, 1, 9, 
            4, 24, 2, 39, 7, 34, 2, 0, 27, 2, 
            2, -1, 8, 21, 2, 32, 29, 2, 2, 10, 
            29, 2, 2, 9, 29, 2, 3, 34, 28, 31, 
            3, 39, 28, 2, 3, 62, 8, 28, 3, 0, 
            27, 32, 3, -1, 8, 21, 3, 32, 28, 3, 
            3, 10, 28, 3, 3, 9, 28, 3, 4, 67, 
            28, 5, 4, 0, 28, 19, 4, -1, 28, 21, 
            5, 68, 28, 6, 5, 0, 28, 19, 5, -1, 
            28, 21, 6, 65, 28, 7, 6, 0, 28, 19, 
            6, -1, 28, 21, 7, 84, 28, 8, 7, 0, 
            28, 19, 7, -1, 28, 21, 8, 65, 28, 9, 
            8, 0, 28, 19, 8, -1, 28, 21, 9, 91, 
            28, 12, 9, 0, 28, 19, 9, -1, 28, 21, 
            10, 60, 27, 11, 10, 0, 27, 10, 10, -1, 
            23, 21, 11, 47, 32, 25, 11, 0, 27, 10, 
            11, -1, 32, 21, 12, 93, 27, 13, 12, 0, 
            27, 12, 12, -1, 28, 21, 13, 93, 27, 14, 
            13, 0, 27, 12, 13, -1, 28, 21, 14, 62, 
            9, 28, 14, 93, 27, 14, 14, 0, 27, 12, 
            14, -1, 28, 21, 15, 45, 28, 16, 15, 0, 
            27, 16, 15, -1, 10, 21, 16, 45, 28, 17, 
            16, 0, 27, 16, 16, -1, 10, 21, 17, 45, 
            28, 18, 17, 0, 20, 16, 17, -1, 10, 21, 
            18, 45, 22, 18, 18, 62, 10, 28, 18, 0, 
            21, 16, 18, -1, 10, 21, 19, 45, 28, 15, 
            19, 62, 28, 28, 19, 91, 28, 4, 19, 0, 
            27, 20, 19, -1, 28, 21, 20, 62, 11, 28, 
            20, 0, 27, 20, 20, -1, 28, 21, 22, 62, 
            12, 28, 22, 0, 27, 1, 22, 32, 28, 34, 
            22, 10, 28, 34, 22, 9, 28, 34, 23, 0, 
            13, 23, 23, -1, 13, 21, 24, 61, 28, 3, 
            24, 62, 3, 28, 24, 0, 2, 1, 24, -1, 
            3, 21, 24, 32, 28, 24, 24, 10, 28, 24, 
            24, 9, 28, 24, 25, 62, 15, 28, 25, 0, 
            27, 25, 25, -1, 15, 21, 25, 32, 28, 25, 
            25, 10, 28, 25, 25, 9, 28, 25, 26, 47, 
            28, 22, 26, 62, 17, 28, 26, 0, 27, 26, 
            26, -1, 28, 21, 26, 32, 16, 34, 26, 10, 
            16, 34, 26, 9, 16, 34, 27, 0, 13, 27, 
            27, -1, 13, 21, 28, 38, 14, 23, 28, 60, 
            23, 33, 28, 0, 27, 28, 28, -1, 23, 21, 
            29, 62, 24, 28, 29, 0, 27, 29, 29, -1, 
            24, 21, 30, 62, 26, 28, 30, 0, 27, 30, 
            30, -1, 26, 21, 30, 32, 25, 29, 30, 10, 
            25, 29, 30, 9, 25, 29, 31, 34, 7, 34, 
            31, 0, 27, 31, 31, -1, 8, 21, 31, 32, 
            29, 31, 31, 10, 29, 31, 31, 9, 29, 31, 
            32, 62, 8, 28, 32, 0, 27, 32, 32, -1, 
            8, 21, 32, 32, 7, 34, 32, 10, 7, 34, 
            32, 9, 7, 34, 33, 33, 28, 19, 33, 47, 
            28, 25, 33, 60, 27, 33, 33, 63, 28, 30, 
            33, 0, 27, 26, 33, -1, 19, 21, 33, 32, 
            18, 28, 33, 10, 18, 28, 33, 9, 18, 28, 
            34, 47, 28, 22, 34, 62, 30, 28, 34, 0, 
            27, 1, 34, -1, 30, 21, 34, 32, 28, 34, 
            34, 10, 28, 34, 34, 9, 28, 34, 35, 0, 
            13, 35, 35, -1, 13, 21
        });
        int i = 0;
        i1 = -1;
        int k;
        for (j1 = -1; i < statetable.length; j1 = k)
        {
            k = j1;
            if (statetable[i] > j1)
            {
                k = statetable[i];
            }
            j1 = i1;
            if (statetable[i + 1] > i1)
            {
                j1 = statetable[i + 1];
            }
            i += 4;
            i1 = j1;
        }

        statetableIndexMaxChar = i1 + 1;
        statetableIndex = (short[][])Array.newInstance(Short.TYPE, new int[] {
            j1 + 1, i1 + 3
        });
        k1 = 0;
_L13:
        int l1;
        if (k1 > j1)
        {
            break; /* Loop/switch isn't completed */
        }
        l1 = -2;
_L7:
        int j;
        int l;
        int i2;
        if (l1 > i1)
        {
            break MISSING_BLOCK_LABEL_4169;
        }
        j = 0;
        i2 = 0;
        l = -1;
_L10:
        int j2 = l;
        if (j >= statetable.length) goto _L2; else goto _L1
_L1:
        if (k1 == statetable[j]) goto _L4; else goto _L3
_L3:
        int k2;
        k2 = i2;
        j2 = l;
        if (i2 == 0) goto _L6; else goto _L5
_L5:
        j2 = l;
_L2:
        statetableIndex[k1][l1 + 2] = (short)j2;
        l1++;
          goto _L7
_L4:
        if (statetable[j + 1] != 0) goto _L9; else goto _L8
_L8:
        k2 = statetable[j + 2];
        j2 = j;
_L6:
        j += 4;
        i2 = k2;
        l = j2;
          goto _L10
_L9:
        k2 = i2;
        j2 = l;
        if (statetable[j + 1] != l1) goto _L6; else goto _L11
_L11:
        j2 = j;
          goto _L2
        k1++;
        if (true) goto _L13; else goto _L12
_L12:
    }
}
