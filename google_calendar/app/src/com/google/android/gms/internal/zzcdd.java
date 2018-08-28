// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.io.IOException;

// Referenced classes of package com.google.android.gms.internal:
//            zzcdl, zzcdp, zzcdk, zzcdm

public final class zzcdd
{

    public int As;
    private int At;
    private int Au;
    public int Av;
    public int Aw;
    public int Ax;
    private int Ay;
    private int Az;
    public final byte buffer[];

    zzcdd(byte abyte0[], int i, int j)
    {
        Ax = 0x7fffffff;
        Az = 64;
        buffer = abyte0;
        As = i;
        At = i + j;
        Av = i;
    }

    private final void zzAN(int i)
        throws IOException
    {
        if (i < 0)
        {
            throw new zzcdl("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        }
        if (Av + i > Ax)
        {
            zzAN(Ax - Av);
            throw new zzcdl("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
        }
        if (i <= At - Av)
        {
            Av = Av + i;
            return;
        } else
        {
            throw new zzcdl("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
        }
    }

    public final byte[] readBytes()
        throws IOException
    {
        int i = zzakJ();
        if (i < 0)
        {
            throw new zzcdl("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        }
        if (i == 0)
        {
            return zzcdp.AU;
        }
        if (i > At - Av)
        {
            throw new zzcdl("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
        } else
        {
            byte abyte0[] = new byte[i];
            System.arraycopy(buffer, Av, abyte0, 0, i);
            Av = i + Av;
            return abyte0;
        }
    }

    public final String readString()
        throws IOException
    {
        int i = zzakJ();
        if (i < 0)
        {
            throw new zzcdl("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        }
        if (i > At - Av)
        {
            throw new zzcdl("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
        } else
        {
            String s = new String(buffer, Av, i, zzcdk.UTF_8);
            Av = i + Av;
            return s;
        }
    }

    public final boolean zzAI(int i)
        throws IOException
    {
        switch (i & 7)
        {
        default:
            throw new zzcdl("Protocol message tag had invalid wire type.");

        case 0: // '\0'
            zzakJ();
            return true;

        case 1: // '\001'
            if (Av == At)
            {
                throw new zzcdl("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
            }
            byte abyte0[] = buffer;
            i = Av;
            Av = i + 1;
            i = abyte0[i];
            if (Av == At)
            {
                throw new zzcdl("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
            }
            abyte0 = buffer;
            int j = Av;
            Av = j + 1;
            j = abyte0[j];
            if (Av == At)
            {
                throw new zzcdl("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
            }
            abyte0 = buffer;
            int l = Av;
            Av = l + 1;
            l = abyte0[l];
            if (Av == At)
            {
                throw new zzcdl("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
            }
            abyte0 = buffer;
            int i1 = Av;
            Av = i1 + 1;
            i1 = abyte0[i1];
            if (Av == At)
            {
                throw new zzcdl("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
            }
            abyte0 = buffer;
            int j1 = Av;
            Av = j1 + 1;
            j1 = abyte0[j1];
            if (Av == At)
            {
                throw new zzcdl("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
            }
            abyte0 = buffer;
            int k1 = Av;
            Av = k1 + 1;
            k1 = abyte0[k1];
            if (Av == At)
            {
                throw new zzcdl("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
            }
            abyte0 = buffer;
            int l1 = Av;
            Av = l1 + 1;
            l1 = abyte0[l1];
            if (Av == At)
            {
                throw new zzcdl("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
            } else
            {
                byte abyte1[] = buffer;
                int i2 = Av;
                Av = i2 + 1;
                i2 = abyte1[i2];
                long l2 = i;
                l2 = j;
                l2 = l;
                l2 = i1;
                l2 = j1;
                l2 = k1;
                l2 = l1;
                l2 = i2;
                return true;
            }

        case 2: // '\002'
            zzAN(zzakJ());
            return true;

        case 3: // '\003'
            int k;
            do
            {
                k = zzakA();
            } while (k != 0 && zzAI(k));
            if (Aw != ((i >>> 3) << 3 | 4))
            {
                throw new zzcdl("Protocol message end-group tag did not match expected tag.");
            } else
            {
                return true;
            }

        case 4: // '\004'
            return false;

        case 5: // '\005'
            break;
        }
        if (Av == At)
        {
            throw new zzcdl("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
        }
        byte abyte2[] = buffer;
        i = Av;
        Av = i + 1;
        i = abyte2[i];
        if (Av == At)
        {
            throw new zzcdl("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
        }
        abyte2 = buffer;
        i = Av;
        Av = i + 1;
        i = abyte2[i];
        if (Av == At)
        {
            throw new zzcdl("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
        }
        abyte2 = buffer;
        i = Av;
        Av = i + 1;
        i = abyte2[i];
        if (Av == At)
        {
            throw new zzcdl("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
        } else
        {
            byte abyte3[] = buffer;
            i = Av;
            Av = i + 1;
            i = abyte3[i];
            return true;
        }
    }

    public final int zzAK(int i)
        throws zzcdl
    {
        if (i < 0)
        {
            throw new zzcdl("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        }
        i = Av + i;
        int j = Ax;
        if (i > j)
        {
            throw new zzcdl("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
        } else
        {
            Ax = i;
            zzakN();
            return j;
        }
    }

    public final void zzAM(int i)
    {
        if (i > Av - As)
        {
            int j = Av;
            int k = As;
            throw new IllegalArgumentException((new StringBuilder(50)).append("Position ").append(i).append(" is beyond current ").append(j - k).toString());
        }
        if (i < 0)
        {
            throw new IllegalArgumentException((new StringBuilder(24)).append("Bad position ").append(i).toString());
        } else
        {
            Av = As + i;
            return;
        }
    }

    public final void zza(zzcdm zzcdm1)
        throws IOException
    {
        int i = zzakJ();
        if (Ay >= Az)
        {
            throw new zzcdl("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
        }
        i = zzAK(i);
        Ay = Ay + 1;
        zzcdm1.mergeFrom(this);
        if (Aw != 0)
        {
            throw new zzcdl("Protocol message end-group tag did not match expected tag.");
        } else
        {
            Ay = Ay - 1;
            Ax = i;
            zzakN();
            return;
        }
    }

    public final int zzakA()
        throws IOException
    {
        if (Av == At)
        {
            Aw = 0;
            return 0;
        }
        Aw = zzakJ();
        if (Aw == 0)
        {
            throw new zzcdl("Protocol message contained an invalid tag (zero).");
        } else
        {
            return Aw;
        }
    }

    public final int zzakJ()
        throws IOException
    {
        int i;
        if (Av == At)
        {
            throw new zzcdl("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
        }
        byte abyte0[] = buffer;
        i = Av;
        Av = i + 1;
        i = abyte0[i];
        if (i < 0) goto _L2; else goto _L1
_L1:
        return i;
_L2:
        i &= 0x7f;
        if (Av == At)
        {
            throw new zzcdl("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
        }
        byte abyte1[] = buffer;
        int j = Av;
        Av = j + 1;
        j = abyte1[j];
        if (j >= 0)
        {
            return i | j << 7;
        }
        i |= (j & 0x7f) << 7;
        if (Av == At)
        {
            throw new zzcdl("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
        }
        abyte1 = buffer;
        j = Av;
        Av = j + 1;
        j = abyte1[j];
        if (j >= 0)
        {
            return i | j << 14;
        }
        i |= (j & 0x7f) << 14;
        if (Av == At)
        {
            throw new zzcdl("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
        }
        abyte1 = buffer;
        j = Av;
        Av = j + 1;
        int l = abyte1[j];
        if (l >= 0)
        {
            return i | l << 21;
        }
        if (Av == At)
        {
            throw new zzcdl("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
        }
        abyte1 = buffer;
        j = Av;
        Av = j + 1;
        j = abyte1[j];
        l = i | (l & 0x7f) << 21 | j << 28;
        i = l;
        if (j < 0)
        {
            int k = 0;
label0:
            do
            {
label1:
                {
                    if (k >= 5)
                    {
                        break label1;
                    }
                    if (Av == At)
                    {
                        throw new zzcdl("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
                    }
                    byte abyte2[] = buffer;
                    int i1 = Av;
                    Av = i1 + 1;
                    i = l;
                    if (abyte2[i1] >= 0)
                    {
                        break label0;
                    }
                    k++;
                }
            } while (true);
        }
        if (true) goto _L1; else goto _L3
_L3:
        throw new zzcdl("CodedInputStream encountered a malformed varint.");
    }

    public final long zzakK()
        throws IOException
    {
        int i = 0;
        long l = 0L;
        for (; i < 64; i += 7)
        {
            if (Av == At)
            {
                throw new zzcdl("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
            }
            byte abyte0[] = buffer;
            int j = Av;
            Av = j + 1;
            j = abyte0[j];
            l |= (long)(j & 0x7f) << i;
            if ((j & 0x80) == 0)
            {
                return l;
            }
        }

        throw new zzcdl("CodedInputStream encountered a malformed varint.");
    }

    final void zzakN()
    {
        At = At + Au;
        int i = At;
        if (i > Ax)
        {
            Au = i - Ax;
            At = At - Au;
            return;
        } else
        {
            Au = 0;
            return;
        }
    }
}
