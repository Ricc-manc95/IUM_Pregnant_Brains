// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Arrays;

// Referenced classes of package com.google.android.gms.internal:
//            zzcdf, zzcdp, zzcdm, zzcde, 
//            zzcdk, zzcdi, zzcdd

public final class AL extends zzcdf
    implements Cloneable
{

    public long Bd;
    public long Be;
    private long Bf;
    private e Bg[];
    private byte Bh[];
    private e Bi;
    public byte Bj[];
    private String Bk;
    private String Bl;
    private String Bm;
    public long Bn;
    private e Bo;
    public byte Bp[];
    private String Bq;
    private int Br;
    private int Bs[];
    private long Bt;
    private e Bu;
    public int eventCode;
    private boolean kd;
    private String tag;
    private int zzrm;

    private final AL zzalm()
    {
        Object obj;
        try
        {
            obj = (AL)super.zzakT();
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            throw new AssertionError(obj);
        }
        if (Bg != null && Bg.length > 0)
        {
            obj.Bg = new Bg[Bg.length];
            for (int i = 0; i < Bg.length; i++)
            {
                if (Bg[i] != null)
                {
                    ((Bg) (obj)).Bg[i] = (Bg)(Object)Bg[i].zzakU();
                }
            }

        }
        if (Bi != null)
        {
            obj.Bi = (Bi)(Object)Bi.zzakU();
        }
        if (Bo != null)
        {
            obj.Bo = (Bo)(Object)Bo.zzakU();
        }
        if (Bs != null && Bs.length > 0)
        {
            obj.Bs = (int[])Bs.clone();
        }
        if (Bu != null)
        {
            obj.Bu = (Bu)(Object)Bu.zzakU();
        }
        return ((U) (obj));
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return zzalm();
    }

    protected final int computeSerializedSize()
    {
        boolean flag = false;
        int i = super.computeSerializedSize();
        int j = i;
        if (Bd != 0L)
        {
            long l3 = Bd;
            j = zzcde.zzAV(8);
            j = i + (zzcde.zzbu(l3) + j);
        }
        i = j;
        if (tag != null)
        {
            i = j;
            if (!tag.equals(""))
            {
                String s = tag;
                i = zzcde.zzAV(16);
                int k = zzcde.zzb(s);
                i = j + (k + zzcde.zzAV(k) + i);
            }
        }
        j = i;
        if (Bg != null)
        {
            j = i;
            if (Bg.length > 0)
            {
                for (j = 0; j < Bg.length;)
                {
                    zzalm zzalm1 = Bg[j];
                    int l = i;
                    if (zzalm1 != null)
                    {
                        l = i + zzcde.zzc(3, zzalm1);
                    }
                    j++;
                    i = l;
                }

                j = i;
            }
        }
        i = j;
        if (!Arrays.equals(Bh, zzcdp.AU))
        {
            byte abyte0[] = Bh;
            i = zzcde.zzAV(32);
            int i1 = zzcde.zzAV(abyte0.length);
            i = j + (abyte0.length + i1 + i);
        }
        j = i;
        if (!Arrays.equals(Bj, zzcdp.AU))
        {
            byte abyte1[] = Bj;
            j = zzcde.zzAV(48);
            int j1 = zzcde.zzAV(abyte1.length);
            j = i + (abyte1.length + j1 + j);
        }
        i = j;
        if (Bk != null)
        {
            i = j;
            if (!Bk.equals(""))
            {
                String s1 = Bk;
                i = zzcde.zzAV(64);
                int k1 = zzcde.zzb(s1);
                i = j + (k1 + zzcde.zzAV(k1) + i);
            }
        }
        j = i;
        if (Bi != null)
        {
            j = i + zzcde.zzc(9, Bi);
        }
        i = j;
        if (kd)
        {
            i = j + (zzcde.zzAV(80) + 1);
        }
        j = i;
        if (eventCode != 0)
        {
            j = i + zzcde.zzaa(11, eventCode);
        }
        i = j;
        if (zzrm != 0)
        {
            i = j + zzcde.zzaa(12, zzrm);
        }
        j = i;
        if (Bl != null)
        {
            j = i;
            if (!Bl.equals(""))
            {
                String s2 = Bl;
                j = zzcde.zzAV(104);
                int l1 = zzcde.zzb(s2);
                j = i + (l1 + zzcde.zzAV(l1) + j);
            }
        }
        i = j;
        if (Bm != null)
        {
            i = j;
            if (!Bm.equals(""))
            {
                String s3 = Bm;
                i = zzcde.zzAV(112);
                int i2 = zzcde.zzb(s3);
                i = j + (i2 + zzcde.zzAV(i2) + i);
            }
        }
        int j2 = i;
        if (Bn != 0x2bf20L)
        {
            j2 = i + zzcde.zzl(15, Bn);
        }
        j = j2;
        if (Bo != null)
        {
            j = j2 + zzcde.zzc(16, Bo);
        }
        i = j;
        if (Be != 0L)
        {
            long l4 = Be;
            i = zzcde.zzAV(136);
            i = j + (zzcde.zzbu(l4) + i);
        }
        j = i;
        if (!Arrays.equals(Bp, zzcdp.AU))
        {
            byte abyte2[] = Bp;
            j = zzcde.zzAV(144);
            int k2 = zzcde.zzAV(abyte2.length);
            j = i + (abyte2.length + k2 + j);
        }
        i = j;
        if (Br != 0)
        {
            i = j + zzcde.zzaa(19, Br);
        }
        j = i;
        if (Bs != null)
        {
            j = i;
            if (Bs.length > 0)
            {
                int l2 = 0;
                j = ((flag) ? 1 : 0);
                while (j < Bs.length) 
                {
                    int j3 = Bs[j];
                    if (j3 >= 0)
                    {
                        j3 = zzcde.zzAV(j3);
                    } else
                    {
                        j3 = 10;
                    }
                    l2 += j3;
                    j++;
                }
                j = i + l2 + Bs.length * 2;
            }
        }
        i = j;
        if (Bf != 0L)
        {
            long l5 = Bf;
            i = j + (zzcde.zzAV(168) + zzcde.zzbu(l5));
        }
        j = i;
        if (Bt != 0L)
        {
            long l6 = Bt;
            j = i + (zzcde.zzAV(176) + zzcde.zzbu(l6));
        }
        i = j;
        if (Bu != null)
        {
            i = j + zzcde.zzc(23, Bu);
        }
        j = i;
        if (Bq != null)
        {
            j = i;
            if (!Bq.equals(""))
            {
                String s4 = Bq;
                j = zzcde.zzAV(192);
                int i3 = zzcde.zzb(s4);
                j = i + (i3 + zzcde.zzAV(i3) + j);
            }
        }
        return j;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        boolean flag;
        if (!(obj instanceof ))
        {
            return false;
        }
        obj = ()obj;
        if (Bd != ((Bd) (obj)).Bd)
        {
            return false;
        }
        if (Be != ((Be) (obj)).Be)
        {
            return false;
        }
        if (Bf != ((Bf) (obj)).Bf)
        {
            return false;
        }
        if (tag == null)
        {
            if (((tag) (obj)).tag != null)
            {
                return false;
            }
        } else
        if (!tag.equals(((tag) (obj)).tag))
        {
            return false;
        }
        if (eventCode != ((eventCode) (obj)).eventCode)
        {
            return false;
        }
        if (zzrm != ((zzrm) (obj)).zzrm)
        {
            return false;
        }
        if (kd != ((kd) (obj)).kd)
        {
            return false;
        }
        if (!zzcdk.equals(Bg, ((Bg) (obj)).Bg))
        {
            return false;
        }
        if (!Arrays.equals(Bh, ((Bh) (obj)).Bh))
        {
            return false;
        }
        if (Bi == null)
        {
            if (((Bi) (obj)).Bi != null)
            {
                return false;
            }
        } else
        if (!Bi.equals(((equals) (obj)).Bi))
        {
            return false;
        }
        if (!Arrays.equals(Bj, ((Bj) (obj)).Bj))
        {
            return false;
        }
        if (Bk == null)
        {
            if (((Bk) (obj)).Bk != null)
            {
                return false;
            }
        } else
        if (!Bk.equals(((Bk) (obj)).Bk))
        {
            return false;
        }
        if (Bl == null)
        {
            if (((Bl) (obj)).Bl != null)
            {
                return false;
            }
        } else
        if (!Bl.equals(((Bl) (obj)).Bl))
        {
            return false;
        }
        if (Bm == null)
        {
            if (((Bm) (obj)).Bm != null)
            {
                return false;
            }
        } else
        if (!Bm.equals(((Bm) (obj)).Bm))
        {
            return false;
        }
        if (Bn != ((Bn) (obj)).Bn)
        {
            return false;
        }
        if (Bo == null)
        {
            if (((Bo) (obj)).Bo != null)
            {
                return false;
            }
        } else
        if (!Bo.equals(((equals) (obj)).Bo))
        {
            return false;
        }
        if (!Arrays.equals(Bp, ((Bp) (obj)).Bp))
        {
            return false;
        }
        if (Bq == null)
        {
            if (((Bq) (obj)).Bq != null)
            {
                return false;
            }
        } else
        if (!Bq.equals(((Bq) (obj)).Bq))
        {
            return false;
        }
        if (Br != ((Br) (obj)).Br)
        {
            return false;
        }
        if (!zzcdk.equals(Bs, ((Bs) (obj)).Bs))
        {
            return false;
        }
        if (Bt != ((Bt) (obj)).Bt)
        {
            return false;
        }
        if (Bu == null)
        {
            if (((Bu) (obj)).Bu != null)
            {
                return false;
            }
        } else
        if (!Bu.equals(((equals) (obj)).Bu))
        {
            return false;
        }
        if (AC == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (AC.mSize == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        if (((e) (obj)).AC == null) goto _L1; else goto _L3
_L3:
        if (((AC) (obj)).AC.mSize == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            return false;
        }
        if (true) goto _L1; else goto _L4
_L4:
        return AC.equals(((ls) (obj)).AC);
    }

    public final int hashCode()
    {
        boolean flag = false;
        int j2 = getClass().getName().hashCode();
        int k2 = (int)(Bd ^ Bd >>> 32);
        int l2 = (int)(Be ^ Be >>> 32);
        int i3 = (int)(Bf ^ Bf >>> 32);
        int i;
        char c;
        int j;
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        int j3;
        int k3;
        int l3;
        int i4;
        int j4;
        int k4;
        int l4;
        int i5;
        int j5;
        int k5;
        if (tag == null)
        {
            i = 0;
        } else
        {
            i = tag.hashCode();
        }
        j3 = eventCode;
        k3 = zzrm;
        if (kd)
        {
            c = '\u04CF';
        } else
        {
            c = '\u04D5';
        }
        l3 = zzcdk.hashCode(Bg);
        i4 = Arrays.hashCode(Bh);
        if (Bi == null)
        {
            j = 0;
        } else
        {
            j = Bi.hashCode();
        }
        j4 = Arrays.hashCode(Bj);
        if (Bk == null)
        {
            k = 0;
        } else
        {
            k = Bk.hashCode();
        }
        if (Bl == null)
        {
            l = 0;
        } else
        {
            l = Bl.hashCode();
        }
        if (Bm == null)
        {
            i1 = 0;
        } else
        {
            i1 = Bm.hashCode();
        }
        k4 = (int)(Bn ^ Bn >>> 32);
        if (Bo == null)
        {
            j1 = 0;
        } else
        {
            j1 = Bo.hashCode();
        }
        l4 = Arrays.hashCode(Bp);
        if (Bq == null)
        {
            k1 = 0;
        } else
        {
            k1 = Bq.hashCode();
        }
        i5 = Br;
        j5 = zzcdk.hashCode(Bs);
        k5 = (int)(Bt ^ Bt >>> 32);
        if (Bu == null)
        {
            l1 = 0;
        } else
        {
            l1 = Bu.hashCode();
        }
        i2 = ((flag) ? 1 : 0);
        if (AC != null)
        {
            if (AC.mSize == 0)
            {
                i2 = 1;
            } else
            {
                i2 = 0;
            }
            if (i2 != 0)
            {
                i2 = ((flag) ? 1 : 0);
            } else
            {
                i2 = AC.hashCode();
            }
        }
        return (l1 + ((((k1 + ((j1 + ((i1 + (l + (k + ((j + (((c + (((i + ((((j2 + 527) * 31 + k2) * 31 + l2) * 31 + i3) * 31) * 31 + j3) * 31 + k3) * 31) * 31 + l3) * 31 + i4) * 31) * 31 + j4) * 31) * 31) * 31) * 31 + k4) * 31) * 31 + l4) * 31) * 31 + i5) * 31 + j5) * 31 + k5) * 31) * 31 + i2;
    }

    public final zzcdm mergeFrom(zzcdd zzcdd1)
        throws IOException
    {
_L26:
        int i = zzcdd1.zzakA();
        i;
        JVM INSTR lookupswitch 24: default 208
    //                   0: 217
    //                   8: 219
    //                   18: 230
    //                   26: 241
    //                   34: 365
    //                   50: 376
    //                   66: 387
    //                   74: 398
    //                   80: 427
    //                   88: 452
    //                   96: 463
    //                   106: 474
    //                   114: 485
    //                   120: 496
    //                   130: 519
    //                   136: 548
    //                   146: 559
    //                   152: 570
    //                   160: 615
    //                   162: 717
    //                   168: 884
    //                   176: 895
    //                   186: 906
    //                   194: 935;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L24 _L25
_L1:
        if (super.zza(zzcdd1, i)) goto _L26; else goto _L2
_L2:
        return this;
_L3:
        Bd = zzcdd1.zzakK();
          goto _L26
_L4:
        tag = zzcdd1.readString();
          goto _L26
_L5:
        int k1 = zzcdp.zzc(zzcdd1, 26);
        Code acode[];
        int j;
        if (Bg == null)
        {
            j = 0;
        } else
        {
            j = Bg.length;
        }
        acode = new Bg[k1 + j];
        k1 = j;
        if (j != 0)
        {
            System.arraycopy(Bg, 0, acode, 0, j);
            k1 = j;
        }
        for (; k1 < acode.length - 1; k1++)
        {
            acode[k1] = new <init>();
            zzcdd1.zza(acode[k1]);
            zzcdd1.zzakA();
        }

        acode[k1] = new <init>();
        zzcdd1.zza(acode[k1]);
        Bg = acode;
          goto _L26
_L6:
        Bh = zzcdd1.readBytes();
          goto _L26
_L7:
        Bj = zzcdd1.readBytes();
          goto _L26
_L8:
        Bk = zzcdd1.readString();
          goto _L26
_L9:
        if (Bi == null)
        {
            Bi = new <init>();
        }
        zzcdd1.zza(Bi);
          goto _L26
_L10:
        boolean flag;
        if (zzcdd1.zzakJ() != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        kd = flag;
          goto _L26
_L11:
        eventCode = zzcdd1.zzakJ();
          goto _L26
_L12:
        zzrm = zzcdd1.zzakJ();
          goto _L26
_L13:
        Bl = zzcdd1.readString();
          goto _L26
_L14:
        Bm = zzcdd1.readString();
          goto _L26
_L15:
        long l3 = zzcdd1.zzakK();
        Bn = -(l3 & 1L) ^ l3 >>> 1;
          goto _L26
_L16:
        if (Bo == null)
        {
            Bo = new <init>();
        }
        zzcdd1.zza(Bo);
          goto _L26
_L17:
        Be = zzcdd1.zzakK();
          goto _L26
_L18:
        Bp = zzcdd1.readBytes();
          goto _L26
_L19:
        int k = zzcdd1.zzakJ();
        switch (k)
        {
        case 0: // '\0'
        case 1: // '\001'
        case 2: // '\002'
            Br = k;
            break;
        }
        continue; /* Loop/switch isn't completed */
_L20:
        int l1 = zzcdp.zzc(zzcdd1, 160);
        int ai[];
        int l;
        if (Bs == null)
        {
            l = 0;
        } else
        {
            l = Bs.length;
        }
        ai = new int[l1 + l];
        l1 = l;
        if (l != 0)
        {
            System.arraycopy(Bs, 0, ai, 0, l);
            l1 = l;
        }
        for (; l1 < ai.length - 1; l1++)
        {
            ai[l1] = zzcdd1.zzakJ();
            zzcdd1.zzakA();
        }

        ai[l1] = zzcdd1.zzakJ();
        Bs = ai;
        continue; /* Loop/switch isn't completed */
_L21:
        int j2 = zzcdd1.zzAK(zzcdd1.zzakJ());
        int k2 = zzcdd1.Av;
        int l2 = zzcdd1.As;
        int i2 = 0;
        do
        {
            int i1;
            if (zzcdd1.Ax == 0x7fffffff)
            {
                i1 = -1;
            } else
            {
                i1 = zzcdd1.Av;
                i1 = zzcdd1.Ax - i1;
            }
            if (i1 > 0)
            {
                zzcdd1.zzakJ();
                i2++;
            } else
            {
                zzcdd1.zzAM(k2 - l2);
                int ai1[];
                int j1;
                if (Bs == null)
                {
                    j1 = 0;
                } else
                {
                    j1 = Bs.length;
                }
                ai1 = new int[i2 + j1];
                i2 = j1;
                if (j1 != 0)
                {
                    System.arraycopy(Bs, 0, ai1, 0, j1);
                    i2 = j1;
                }
                for (; i2 < ai1.length; i2++)
                {
                    ai1[i2] = zzcdd1.zzakJ();
                }

                Bs = ai1;
                zzcdd1.Ax = j2;
                zzcdd1.zzakN();
                continue; /* Loop/switch isn't completed */
            }
        } while (true);
_L22:
        Bf = zzcdd1.zzakK();
        continue; /* Loop/switch isn't completed */
_L23:
        Bt = zzcdd1.zzakK();
        continue; /* Loop/switch isn't completed */
_L24:
        if (Bu == null)
        {
            Bu = new <init>();
        }
        zzcdd1.zza(Bu);
        continue; /* Loop/switch isn't completed */
_L25:
        Bq = zzcdd1.readString();
        if (true) goto _L26; else goto _L27
_L27:
    }

    public final void writeTo(zzcde zzcde1)
        throws IOException
    {
        boolean flag = false;
        if (Bd != 0L)
        {
            zzcde1.zzf(1, Bd);
        }
        if (tag != null && !tag.equals(""))
        {
            zzcde1.zzs(2, tag);
        }
        if (Bg != null && Bg.length > 0)
        {
            for (int i = 0; i < Bg.length; i++)
            {
                String s = Bg[i];
                if (s != null)
                {
                    zzcde1.zza(3, s);
                }
            }

        }
        if (!Arrays.equals(Bh, zzcdp.AU))
        {
            zzcde1.zzb(4, Bh);
        }
        if (!Arrays.equals(Bj, zzcdp.AU))
        {
            zzcde1.zzb(6, Bj);
        }
        if (Bk != null && !Bk.equals(""))
        {
            zzcde1.zzs(8, Bk);
        }
        if (Bi != null)
        {
            zzcde1.zza(9, Bi);
        }
        if (kd)
        {
            zzcde1.zzj(10, kd);
        }
        if (eventCode != 0)
        {
            zzcde1.zzY(11, eventCode);
        }
        if (zzrm != 0)
        {
            zzcde1.zzY(12, zzrm);
        }
        if (Bl != null && !Bl.equals(""))
        {
            zzcde1.zzs(13, Bl);
        }
        if (Bm != null && !Bm.equals(""))
        {
            zzcde1.zzs(14, Bm);
        }
        if (Bn != 0x2bf20L)
        {
            zzcde1.zzh(15, Bn);
        }
        if (Bo != null)
        {
            zzcde1.zza(16, Bo);
        }
        if (Be != 0L)
        {
            zzcde1.zzf(17, Be);
        }
        if (!Arrays.equals(Bp, zzcdp.AU))
        {
            zzcde1.zzb(18, Bp);
        }
        if (Br != 0)
        {
            zzcde1.zzY(19, Br);
        }
        if (Bs != null && Bs.length > 0)
        {
            for (int j = ((flag) ? 1 : 0); j < Bs.length; j++)
            {
                zzcde1.zzY(20, Bs[j]);
            }

        }
        if (Bf != 0L)
        {
            zzcde1.zzf(21, Bf);
        }
        if (Bt != 0L)
        {
            zzcde1.zzf(22, Bt);
        }
        if (Bu != null)
        {
            zzcde1.zza(23, Bu);
        }
        if (Bq != null && !Bq.equals(""))
        {
            zzcde1.zzs(24, Bq);
        }
        super.writeTo(zzcde1);
    }

    public final zzcdf zzakT()
        throws CloneNotSupportedException
    {
        return (eTo)(Object)zzakU();
    }

    public final zzcdm zzakU()
        throws CloneNotSupportedException
    {
        return (U)clone();
    }

    public ()
    {
        Bd = 0L;
        Be = 0L;
        Bf = 0L;
        tag = "";
        eventCode = 0;
        zzrm = 0;
        kd = false;
        Bg = zzaln();
        Bh = zzcdp.AU;
        Bi = null;
        Bj = zzcdp.AU;
        Bk = "";
        Bl = "";
        Bm = "";
        Bn = 0x2bf20L;
        Bo = null;
        Bp = zzcdp.AU;
        Bq = "";
        Br = 0;
        Bs = zzcdp.AN;
        Bt = 0L;
        Bu = null;
        AC = null;
        AL = -1;
    }
}
