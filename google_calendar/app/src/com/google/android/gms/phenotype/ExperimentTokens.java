// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.phenotype;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// Referenced classes of package com.google.android.gms.phenotype:
//            zzd

public class ExperimentTokens extends zza
{

    public static final android.os.Parcelable.Creator CREATOR = new zzd();
    private static final byte EMPTY_BYTES[][];
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    public final byte additionalDirectExperimentTokens[][];
    public final byte alwaysCrossExperimentTokens[][];
    public final byte directExperimentToken[];
    public final byte gaiaCrossExperimentTokens[][];
    public final int mVersionCode;
    public final byte otherCrossExperimentTokens[][];
    public final byte pseudonymousCrossExperimentTokens[][];
    public final String user;
    public final int weakExperimentIds[];

    ExperimentTokens(int i, String s, byte abyte0[], byte abyte1[][], byte abyte2[][], byte abyte3[][], byte abyte4[][], 
            int ai[], byte abyte5[][])
    {
        mVersionCode = i;
        user = s;
        directExperimentToken = abyte0;
        gaiaCrossExperimentTokens = abyte1;
        pseudonymousCrossExperimentTokens = abyte2;
        alwaysCrossExperimentTokens = abyte3;
        otherCrossExperimentTokens = abyte4;
        weakExperimentIds = ai;
        additionalDirectExperimentTokens = abyte5;
    }

    private ExperimentTokens(String s, byte abyte0[], byte abyte1[][], byte abyte2[][], byte abyte3[][], byte abyte4[][], int ai[], 
            byte abyte5[][])
    {
        this(1, s, null, abyte1, abyte2, abyte3, abyte4, null, null);
    }

    private static void zza(StringBuilder stringbuilder, String s, byte abyte0[][])
    {
        stringbuilder.append(s);
        stringbuilder.append("=");
        if (abyte0 == null)
        {
            stringbuilder.append("null");
            return;
        }
        stringbuilder.append("(");
        int j = abyte0.length;
        boolean flag = true;
        for (int i = 0; i < j;)
        {
            s = abyte0[i];
            if (!flag)
            {
                stringbuilder.append(", ");
            }
            stringbuilder.append("'");
            stringbuilder.append(new String(s, UTF_8));
            stringbuilder.append("'");
            i++;
            flag = false;
        }

        stringbuilder.append(")");
    }

    private static List zzc(byte abyte0[][])
    {
        if (abyte0 == null)
        {
            return Collections.EMPTY_LIST;
        }
        ArrayList arraylist = new ArrayList(abyte0.length);
        int j = abyte0.length;
        for (int i = 0; i < j; i++)
        {
            arraylist.add(new String(abyte0[i], UTF_8));
        }

        Collections.sort(arraylist);
        return arraylist;
    }

    private static List zzh(int ai[])
    {
        if (ai == null)
        {
            return Collections.EMPTY_LIST;
        }
        ArrayList arraylist = new ArrayList(ai.length);
        int j = ai.length;
        for (int i = 0; i < j; i++)
        {
            arraylist.add(Integer.valueOf(ai[i]));
        }

        Collections.sort(arraylist);
        return arraylist;
    }

    public boolean equals(Object obj)
    {
        if (obj != null && (obj instanceof ExperimentTokens))
        {
            obj = (ExperimentTokens)obj;
            if (mVersionCode == ((ExperimentTokens) (obj)).mVersionCode)
            {
                String s = user;
                String s1 = ((ExperimentTokens) (obj)).user;
                boolean flag;
                if (s == s1 || s != null && s.equals(s1))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag && Arrays.equals(directExperimentToken, ((ExperimentTokens) (obj)).directExperimentToken))
                {
                    List list = zzc(gaiaCrossExperimentTokens);
                    List list6 = zzc(((ExperimentTokens) (obj)).gaiaCrossExperimentTokens);
                    if (list == list6 || list != null && list.equals(list6))
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag)
                    {
                        List list1 = zzc(pseudonymousCrossExperimentTokens);
                        List list7 = zzc(((ExperimentTokens) (obj)).pseudonymousCrossExperimentTokens);
                        if (list1 == list7 || list1 != null && list1.equals(list7))
                        {
                            flag = true;
                        } else
                        {
                            flag = false;
                        }
                        if (flag)
                        {
                            List list2 = zzc(alwaysCrossExperimentTokens);
                            List list8 = zzc(((ExperimentTokens) (obj)).alwaysCrossExperimentTokens);
                            if (list2 == list8 || list2 != null && list2.equals(list8))
                            {
                                flag = true;
                            } else
                            {
                                flag = false;
                            }
                            if (flag)
                            {
                                List list3 = zzc(otherCrossExperimentTokens);
                                List list9 = zzc(((ExperimentTokens) (obj)).otherCrossExperimentTokens);
                                if (list3 == list9 || list3 != null && list3.equals(list9))
                                {
                                    flag = true;
                                } else
                                {
                                    flag = false;
                                }
                                if (flag)
                                {
                                    List list4 = zzh(weakExperimentIds);
                                    List list10 = zzh(((ExperimentTokens) (obj)).weakExperimentIds);
                                    if (list4 == list10 || list4 != null && list4.equals(list10))
                                    {
                                        flag = true;
                                    } else
                                    {
                                        flag = false;
                                    }
                                    if (flag)
                                    {
                                        List list5 = zzc(additionalDirectExperimentTokens);
                                        obj = zzc(((ExperimentTokens) (obj)).additionalDirectExperimentTokens);
                                        if (list5 == obj || list5 != null && list5.equals(obj))
                                        {
                                            flag = true;
                                        } else
                                        {
                                            flag = false;
                                        }
                                        if (flag)
                                        {
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return false;
        } else
        {
            return false;
        }
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder("ExperimentTokens");
        stringbuilder.append("(");
        stringbuilder.append(mVersionCode);
        stringbuilder.append(", ");
        String s;
        if (user == null)
        {
            s = "null";
        } else
        {
            s = String.valueOf("'");
            String s1 = user;
            String s2 = String.valueOf("'");
            s = (new StringBuilder(String.valueOf(s).length() + 0 + String.valueOf(s1).length() + String.valueOf(s2).length())).append(s).append(s1).append(s2).toString();
        }
        stringbuilder.append(s);
        stringbuilder.append(", ");
        s = directExperimentToken;
        stringbuilder.append("direct");
        stringbuilder.append("=");
        if (s == null)
        {
            stringbuilder.append("null");
        } else
        {
            stringbuilder.append("'");
            stringbuilder.append(new String(s, UTF_8));
            stringbuilder.append("'");
        }
        stringbuilder.append(", ");
        zza(stringbuilder, "GAIA", gaiaCrossExperimentTokens);
        stringbuilder.append(", ");
        zza(stringbuilder, "PSEUDO", pseudonymousCrossExperimentTokens);
        stringbuilder.append(", ");
        zza(stringbuilder, "ALWAYS", alwaysCrossExperimentTokens);
        stringbuilder.append(", ");
        zza(stringbuilder, "OTHER", otherCrossExperimentTokens);
        stringbuilder.append(", ");
        s = weakExperimentIds;
        stringbuilder.append("weak");
        stringbuilder.append("=");
        if (s == null)
        {
            stringbuilder.append("null");
        } else
        {
            stringbuilder.append("(");
            int j = s.length;
            boolean flag = true;
            for (int i = 0; i < j;)
            {
                byte byte0 = s[i];
                if (!flag)
                {
                    stringbuilder.append(", ");
                }
                stringbuilder.append(byte0);
                i++;
                flag = false;
            }

            stringbuilder.append(")");
        }
        stringbuilder.append(", ");
        zza(stringbuilder, "directs", additionalDirectExperimentTokens);
        stringbuilder.append(")");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        i = parcel.dataPosition();
        int j = mVersionCode;
        com.google.android.gms.common.internal.safeparcel.zzc.zzb(parcel, 1, 4);
        parcel.writeInt(j);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 2, user, false);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 3, directExperimentToken, false);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 4, gaiaCrossExperimentTokens, false);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 5, pseudonymousCrossExperimentTokens, false);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 6, alwaysCrossExperimentTokens, false);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 7, otherCrossExperimentTokens, false);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 8, weakExperimentIds, false);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 9, additionalDirectExperimentTokens, false);
        com.google.android.gms.common.internal.safeparcel.zzc.zzJ(parcel, i);
    }

    static 
    {
        EMPTY_BYTES = new byte[0][];
        new ExperimentTokens("", null, EMPTY_BYTES, EMPTY_BYTES, EMPTY_BYTES, EMPTY_BYTES, null, null);
        new _cls1();
        new _cls2();
        new _cls3();
        new _cls4();
    }

    private class _cls1
    {

        _cls1()
        {
        }
    }


    private class _cls2
    {

        _cls2()
        {
        }
    }


    private class _cls3
    {

        _cls3()
        {
        }
    }


    private class _cls4
    {

        _cls4()
        {
        }
    }

}
