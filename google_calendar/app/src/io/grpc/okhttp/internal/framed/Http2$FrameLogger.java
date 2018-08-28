// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.okhttp.internal.framed;


// Referenced classes of package io.grpc.okhttp.internal.framed:
//            Http2

static final class 
{

    private static final String BINARY[];
    private static final String FLAGS[];
    private static final String TYPES[] = {
        "DATA", "HEADERS", "PRIORITY", "RST_STREAM", "SETTINGS", "PUSH_PROMISE", "PING", "GOAWAY", "WINDOW_UPDATE", "CONTINUATION"
    };

    static String formatHeader(boolean flag, int i, int j, byte byte0, byte byte1)
    {
        String s;
        String s2;
        if (byte0 < TYPES.length)
        {
            s2 = TYPES[byte0];
        } else
        {
            s2 = String.format("0x%02x", new Object[] {
                Byte.valueOf(byte0)
            });
        }
        if (byte1 != 0) goto _L2; else goto _L1
_L1:
        s = "";
_L4:
        String s1;
        if (flag)
        {
            s1 = "<<";
        } else
        {
            s1 = ">>";
        }
        return String.format("%s 0x%08x %5d %-13s %s", new Object[] {
            s1, Integer.valueOf(i), Integer.valueOf(j), s2, s
        });
_L2:
        switch (byte0)
        {
        case 5: // '\005'
        default:
            if (byte1 < FLAGS.length)
            {
                s1 = FLAGS[byte1];
            } else
            {
                s1 = BINARY[byte1];
            }
            if (byte0 == 5 && (byte1 & 4) != 0)
            {
                s = s1.replace("HEADERS", "PUSH_PROMISE");
            } else
            {
                s = s1;
                if (byte0 == 0)
                {
                    s = s1;
                    if ((byte1 & 0x20) != 0)
                    {
                        s = s1.replace("PRIORITY", "COMPRESSED");
                    }
                }
            }
            break;

        case 4: // '\004'
        case 6: // '\006'
            if (byte1 == 1)
            {
                s = "ACK";
            } else
            {
                s = BINARY[byte1];
            }
            break;

        case 2: // '\002'
        case 3: // '\003'
        case 7: // '\007'
        case 8: // '\b'
            s = BINARY[byte1];
            break;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    static 
    {
        boolean flag = false;
        FLAGS = new String[64];
        BINARY = new String[256];
        for (int i = 0; i < BINARY.length; i++)
        {
            BINARY[i] = String.format("%8s", new Object[] {
                Integer.toBinaryString(i)
            }).replace(' ', '0');
        }

        FLAGS[0] = "";
        FLAGS[1] = "END_STREAM";
        int ai[] = new int[1];
        ai[0] = 1;
        FLAGS[8] = "PADDED";
        for (int j = 0; j <= 0; j++)
        {
            int l = ai[j];
            FLAGS[l | 8] = String.valueOf(FLAGS[l]).concat("|PADDED");
        }

        FLAGS[4] = "END_HEADERS";
        FLAGS[32] = "PRIORITY";
        FLAGS[36] = "END_HEADERS|PRIORITY";
        int k = 0;
        int i1;
        do
        {
            i1 = ((flag) ? 1 : 0);
            if (k >= 3)
            {
                break;
            }
            int j1 = (new int[] {
                4, 32, 36
            })[k];
            for (i1 = 0; i1 <= 0; i1++)
            {
                int k1 = ai[i1];
                String as[] = FLAGS;
                String s = FLAGS[k1];
                String s1 = FLAGS[j1];
                as[k1 | j1] = (new StringBuilder(String.valueOf(s).length() + 1 + String.valueOf(s1).length())).append(s).append('|').append(s1).toString();
                as = FLAGS;
                s = FLAGS[k1];
                s1 = FLAGS[j1];
                as[k1 | j1 | 8] = (new StringBuilder(String.valueOf(s).length() + 8 + String.valueOf(s1).length())).append(s).append('|').append(s1).append("|PADDED").toString();
            }

            k++;
        } while (true);
        for (; i1 < FLAGS.length; i1++)
        {
            if (FLAGS[i1] == null)
            {
                FLAGS[i1] = BINARY[i1];
            }
        }

    }

    ()
    {
    }
}
