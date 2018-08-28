// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.clearcut;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.playlog.internal.PlayLoggerContext;
import java.util.Arrays;

// Referenced classes of package com.google.android.gms.clearcut:
//            zza

public class LogEventParcelable extends zza
{

    public static final android.os.Parcelable.Creator CREATOR = new com.google.android.gms.clearcut.zza();
    public boolean addPhenotypeExperimentTokens;
    public final ClearcutLogger.MessageProducer clientVisualElementsProducer;
    public int experimentIds[];
    public byte experimentTokens[][];
    public final ClearcutLogger.MessageProducer extensionProducer;
    public final com.google.android.gms.internal.zzcdr.zzc logEvent;
    public byte logEventBytes[];
    public String mendelPackages[];
    public PlayLoggerContext playLoggerContext;
    public int testCodes[];
    public final int versionCode;

    LogEventParcelable(int i, PlayLoggerContext playloggercontext, byte abyte0[], int ai[], String as[], int ai1[], byte abyte1[][], 
            boolean flag)
    {
        versionCode = i;
        playLoggerContext = playloggercontext;
        logEventBytes = abyte0;
        testCodes = ai;
        mendelPackages = as;
        logEvent = null;
        extensionProducer = null;
        clientVisualElementsProducer = null;
        experimentIds = ai1;
        experimentTokens = abyte1;
        addPhenotypeExperimentTokens = flag;
    }

    public LogEventParcelable(PlayLoggerContext playloggercontext, com.google.android.gms.internal.zzcdr.zzc zzc1, ClearcutLogger.MessageProducer messageproducer, ClearcutLogger.MessageProducer messageproducer1, int ai[], String as[], int ai1[], 
            byte abyte0[][], boolean flag)
    {
        versionCode = 1;
        playLoggerContext = playloggercontext;
        logEvent = zzc1;
        extensionProducer = messageproducer;
        clientVisualElementsProducer = messageproducer1;
        testCodes = ai;
        mendelPackages = as;
        experimentIds = ai1;
        experimentTokens = abyte0;
        addPhenotypeExperimentTokens = flag;
    }

    public boolean equals(Object obj)
    {
        if (this != obj) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (!(obj instanceof LogEventParcelable))
        {
            break MISSING_BLOCK_LABEL_277;
        }
        obj = (LogEventParcelable)obj;
        if (versionCode != ((LogEventParcelable) (obj)).versionCode)
        {
            break; /* Loop/switch isn't completed */
        }
        Object obj1 = playLoggerContext;
        Object obj2 = ((LogEventParcelable) (obj)).playLoggerContext;
        boolean flag;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag || !Arrays.equals(logEventBytes, ((LogEventParcelable) (obj)).logEventBytes) || !Arrays.equals(testCodes, ((LogEventParcelable) (obj)).testCodes) || !Arrays.equals(mendelPackages, ((LogEventParcelable) (obj)).mendelPackages))
        {
            break; /* Loop/switch isn't completed */
        }
        obj1 = logEvent;
        obj2 = ((LogEventParcelable) (obj)).logEvent;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
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
        obj1 = extensionProducer;
        obj2 = ((LogEventParcelable) (obj)).extensionProducer;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
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
        obj1 = clientVisualElementsProducer;
        obj2 = ((LogEventParcelable) (obj)).clientVisualElementsProducer;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag && Arrays.equals(experimentIds, ((LogEventParcelable) (obj)).experimentIds) && Arrays.deepEquals(experimentTokens, ((LogEventParcelable) (obj)).experimentTokens) && addPhenotypeExperimentTokens == ((LogEventParcelable) (obj)).addPhenotypeExperimentTokens) goto _L1; else goto _L3
_L3:
        return false;
        return false;
    }

    public int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            Integer.valueOf(versionCode), playLoggerContext, logEventBytes, testCodes, mendelPackages, logEvent, extensionProducer, clientVisualElementsProducer, experimentIds, experimentTokens, 
            Boolean.valueOf(addPhenotypeExperimentTokens)
        });
    }

    public String toString()
    {
        StringBuilder stringbuilder = (new StringBuilder("LogEventParcelable[")).append(versionCode).append(", ").append(playLoggerContext).append(", LogEventBytes: ");
        String s;
        if (logEventBytes == null)
        {
            s = null;
        } else
        {
            s = new String(logEventBytes);
        }
        return stringbuilder.append(s).append(", TestCodes: ").append(Arrays.toString(testCodes)).append(", MendelPackages: ").append(Arrays.toString(mendelPackages)).append(", LogEvent: ").append(logEvent).append(", ExtensionProducer: ").append(extensionProducer).append(", VeProducer: ").append(clientVisualElementsProducer).append(", ExperimentIDs: ").append(Arrays.toString(experimentIds)).append(", ExperimentTokens: ").append(Arrays.toString(experimentTokens)).append(", AddPhenotypeExperimentTokens: ").append(addPhenotypeExperimentTokens).append("]").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        int k = versionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(k);
        PlayLoggerContext playloggercontext = playLoggerContext;
        if (playloggercontext != null)
        {
            parcel.writeInt(-65534);
            parcel.writeInt(0);
            int l = parcel.dataPosition();
            playloggercontext.writeToParcel(parcel, i);
            i = parcel.dataPosition();
            parcel.setDataPosition(l - 4);
            parcel.writeInt(i - l);
            parcel.setDataPosition(i);
        }
        Object aobj[] = logEventBytes;
        if (aobj != null)
        {
            parcel.writeInt(-65533);
            parcel.writeInt(0);
            i = parcel.dataPosition();
            parcel.writeByteArray(((byte []) (aobj)));
            int i1 = parcel.dataPosition();
            parcel.setDataPosition(i - 4);
            parcel.writeInt(i1 - i);
            parcel.setDataPosition(i1);
        }
        aobj = testCodes;
        if (aobj != null)
        {
            parcel.writeInt(-65532);
            parcel.writeInt(0);
            i = parcel.dataPosition();
            parcel.writeIntArray(((int []) (aobj)));
            int j1 = parcel.dataPosition();
            parcel.setDataPosition(i - 4);
            parcel.writeInt(j1 - i);
            parcel.setDataPosition(j1);
        }
        aobj = mendelPackages;
        if (aobj != null)
        {
            parcel.writeInt(-65531);
            parcel.writeInt(0);
            i = parcel.dataPosition();
            parcel.writeStringArray(((String []) (aobj)));
            int k1 = parcel.dataPosition();
            parcel.setDataPosition(i - 4);
            parcel.writeInt(k1 - i);
            parcel.setDataPosition(k1);
        }
        aobj = experimentIds;
        if (aobj != null)
        {
            parcel.writeInt(-65530);
            parcel.writeInt(0);
            i = parcel.dataPosition();
            parcel.writeIntArray(((int []) (aobj)));
            int l1 = parcel.dataPosition();
            parcel.setDataPosition(i - 4);
            parcel.writeInt(l1 - i);
            parcel.setDataPosition(l1);
        }
        zzc.zza(parcel, 7, experimentTokens, false);
        boolean flag1 = addPhenotypeExperimentTokens;
        zzc.zzb(parcel, 8, 4);
        if (flag1)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        i = parcel.dataPosition();
        parcel.setDataPosition(j - 4);
        parcel.writeInt(i - j);
        parcel.setDataPosition(i);
    }

}
