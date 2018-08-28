// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.tz;

import java.io.DataInput;
import java.io.IOException;
import java.util.Arrays;
import org.joda.time.DateTimeZone;

// Referenced classes of package org.joda.time.tz:
//            DateTimeZoneBuilder

final class iTailZone extends DateTimeZone
{

    public static final long serialVersionUID = 0x6c69b735442cb4f9L;
    private final String iNameKeys[];
    private final int iStandardOffsets[];
    private final iTailZone iTailZone;
    private final long iTransitions[];
    private final int iWallOffsets[];

    static iTailZone readFrom(DataInput datainput, String s)
        throws IOException
    {
        Object obj;
        long al[];
        int ai[];
        int ai1[];
        String as[];
        int j;
        int l;
        int i1;
        l = datainput.readUnsignedShort();
        obj = new String[l];
        for (int i = 0; i < l; i++)
        {
            obj[i] = datainput.readUTF();
        }

        i1 = datainput.readInt();
        al = new long[i1];
        ai = new int[i1];
        ai1 = new int[i1];
        as = new String[i1];
        j = 0;
_L2:
        int k;
        if (j >= i1)
        {
            break MISSING_BLOCK_LABEL_154;
        }
        al[j] = DateTimeZoneBuilder.readMillis(datainput);
        ai[j] = (int)DateTimeZoneBuilder.readMillis(datainput);
        ai1[j] = (int)DateTimeZoneBuilder.readMillis(datainput);
        if (l < 256)
        {
            try
            {
                k = datainput.readUnsignedByte();
            }
            // Misplaced declaration of an exception variable
            catch (DataInput datainput)
            {
                throw new IOException("Invalid encoding");
            }
            break MISSING_BLOCK_LABEL_318;
        }
        k = datainput.readUnsignedShort();
        break MISSING_BLOCK_LABEL_318;
        obj = null;
        if (datainput.readBoolean())
        {
            obj = new iTailZone(s, (int)DateTimeZoneBuilder.readMillis(datainput), new iTailZone(new iTailZone((char)datainput.readUnsignedByte(), datainput.readUnsignedByte(), datainput.readByte(), datainput.readUnsignedByte(), datainput.readBoolean(), (int)DateTimeZoneBuilder.readMillis(datainput)), datainput.readUTF(), (int)DateTimeZoneBuilder.readMillis(datainput)), new iTailZone(new iTailZone((char)datainput.readUnsignedByte(), datainput.readUnsignedByte(), datainput.readByte(), datainput.readUnsignedByte(), datainput.readBoolean(), (int)DateTimeZoneBuilder.readMillis(datainput)), datainput.readUTF(), (int)DateTimeZoneBuilder.readMillis(datainput)));
        }
        return new <init>(s, al, ai, ai1, as, ((<init>) (obj)));
        as[j] = obj[k];
        j++;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public final boolean equals(Object obj)
    {
        if (this != obj) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (!(obj instanceof <init>))
        {
            break MISSING_BLOCK_LABEL_121;
        }
        obj = (<init>)obj;
        if (!super.iID.equals(((DateTimeZone) (obj)).iID) || !Arrays.equals(iTransitions, ((iTransitions) (obj)).iTransitions) || !Arrays.equals(iNameKeys, ((iNameKeys) (obj)).iNameKeys) || !Arrays.equals(iWallOffsets, ((iWallOffsets) (obj)).iWallOffsets) || !Arrays.equals(iStandardOffsets, ((iStandardOffsets) (obj)).iStandardOffsets))
        {
            break; /* Loop/switch isn't completed */
        }
        if (iTailZone != null) goto _L4; else goto _L3
_L3:
        if (((iTailZone) (obj)).iTailZone == null) goto _L1; else goto _L5
_L5:
        return false;
_L4:
        if (!iTailZone.equals(((iTailZone) (obj)).iTailZone)) goto _L5; else goto _L6
_L6:
        return true;
        return false;
    }

    public final String getNameKey(long l)
    {
        long al[] = iTransitions;
        int i = Arrays.binarySearch(al, l);
        if (i >= 0)
        {
            return iNameKeys[i];
        }
        i = ~i;
        if (i < al.length)
        {
            if (i > 0)
            {
                return iNameKeys[i - 1];
            } else
            {
                return "UTC";
            }
        }
        if (iTailZone == null)
        {
            return iNameKeys[i - 1];
        } else
        {
            return iTailZone.getNameKey(l);
        }
    }

    public final int getOffset(long l)
    {
        long al[] = iTransitions;
        int i = Arrays.binarySearch(al, l);
        if (i >= 0)
        {
            return iWallOffsets[i];
        }
        i = ~i;
        if (i < al.length)
        {
            if (i > 0)
            {
                return iWallOffsets[i - 1];
            } else
            {
                return 0;
            }
        }
        if (iTailZone == null)
        {
            return iWallOffsets[i - 1];
        } else
        {
            return iTailZone.getOffset(l);
        }
    }

    public final int getStandardOffset(long l)
    {
        long al[] = iTransitions;
        int i = Arrays.binarySearch(al, l);
        if (i >= 0)
        {
            return iStandardOffsets[i];
        }
        i = ~i;
        if (i < al.length)
        {
            if (i > 0)
            {
                return iStandardOffsets[i - 1];
            } else
            {
                return 0;
            }
        }
        if (iTailZone == null)
        {
            return iStandardOffsets[i - 1];
        } else
        {
            return iTailZone.getStandardOffset(l);
        }
    }

    public final boolean isFixed()
    {
        return false;
    }

    public final long nextTransition(long l)
    {
        long al[] = iTransitions;
        int i = Arrays.binarySearch(al, l);
        long l1;
        if (i >= 0)
        {
            i++;
        } else
        {
            i = ~i;
        }
        if (i < al.length)
        {
            l1 = al[i];
        } else
        {
            l1 = l;
            if (iTailZone != null)
            {
                long l3 = al[al.length - 1];
                long l2 = l;
                if (l < l3)
                {
                    l2 = l3;
                }
                return iTailZone.nextTransition(l2);
            }
        }
        return l1;
    }

    public final long previousTransition(long l)
    {
        long al[];
        int i;
        al = iTransitions;
        i = Arrays.binarySearch(al, l);
        if (i < 0) goto _L2; else goto _L1
_L1:
        long l1;
        l1 = l;
        if (l > 0x8000000000000000L)
        {
            l1 = l - 1L;
        }
_L4:
        return l1;
_L2:
        i = ~i;
        if (i >= al.length)
        {
            break; /* Loop/switch isn't completed */
        }
        l1 = l;
        if (i > 0)
        {
            long l2 = al[i - 1];
            l1 = l;
            if (l2 > 0x8000000000000000L)
            {
                return l2 - 1L;
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
        if (iTailZone != null)
        {
            l1 = iTailZone.previousTransition(l);
            if (l1 < l)
            {
                return l1;
            }
        }
        long l3 = al[i - 1];
        l1 = l;
        if (l3 > 0x8000000000000000L)
        {
            return l3 - 1L;
        }
        if (true) goto _L4; else goto _L5
_L5:
    }

    private (String s, long al[], int ai[], int ai1[], String as[],  )
    {
        super(s);
        iTransitions = al;
        iWallOffsets = ai;
        iStandardOffsets = ai1;
        iNameKeys = as;
        iTailZone = ;
    }
}
