// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.tz;

import java.io.DataInput;
import java.io.IOException;
import org.joda.time.DateTimeZone;

// Referenced classes of package org.joda.time.tz:
//            FixedDateTimeZone, CachedDateTimeZone

public final class DateTimeZoneBuilder
{

    public static DateTimeZone readFrom(DataInput datainput, String s)
        throws IOException
    {
        switch (datainput.readUnsignedByte())
        {
        default:
            throw new IOException("Invalid encoding");

        case 70: // 'F'
            s = new FixedDateTimeZone(s, datainput.readUTF(), (int)readMillis(datainput), (int)readMillis(datainput));
            datainput = s;
            if (s.equals(DateTimeZone.UTC))
            {
                datainput = DateTimeZone.UTC;
            }
            return datainput;

        case 67: // 'C'
            return CachedDateTimeZone.forZone(PrecalculatedZone.readFrom(datainput, s));

        case 80: // 'P'
            return PrecalculatedZone.readFrom(datainput, s);
        }
    }

    static long readMillis(DataInput datainput)
        throws IOException
    {
        int i = datainput.readUnsignedByte();
        switch (i >> 6)
        {
        default:
            return (long)((i << 26) >> 26) * 0x1b7740L;

        case 1: // '\001'
            return (long)((i << 26) >> 2 | datainput.readUnsignedByte() << 16 | datainput.readUnsignedByte() << 8 | datainput.readUnsignedByte()) * 60000L;

        case 2: // '\002'
            return (((long)i << 58) >> 26 | (long)(datainput.readUnsignedByte() << 24) | (long)(datainput.readUnsignedByte() << 16) | (long)(datainput.readUnsignedByte() << 8) | (long)datainput.readUnsignedByte()) * 1000L;

        case 3: // '\003'
            return datainput.readLong();
        }
    }

    private class PrecalculatedZone extends DateTimeZone
    {

        public static final long serialVersionUID = 0x6c69b735442cb4f9L;
        private final String iNameKeys[];
        private final int iStandardOffsets[];
        private final DSTZone iTailZone;
        private final long iTransitions[];
        private final int iWallOffsets[];

        static PrecalculatedZone readFrom(DataInput datainput, String s)
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
                obj = new DSTZone(s, (int)DateTimeZoneBuilder.readMillis(datainput), new Recurrence(new OfYear((char)datainput.readUnsignedByte(), datainput.readUnsignedByte(), datainput.readByte(), datainput.readUnsignedByte(), datainput.readBoolean(), (int)DateTimeZoneBuilder.readMillis(datainput)), datainput.readUTF(), (int)DateTimeZoneBuilder.readMillis(datainput)), new Recurrence(new OfYear((char)datainput.readUnsignedByte(), datainput.readUnsignedByte(), datainput.readByte(), datainput.readUnsignedByte(), datainput.readBoolean(), (int)DateTimeZoneBuilder.readMillis(datainput)), datainput.readUTF(), (int)DateTimeZoneBuilder.readMillis(datainput)));
            }
            return new PrecalculatedZone(s, al, ai, ai1, as, ((DSTZone) (obj)));
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
            if (!(obj instanceof PrecalculatedZone))
            {
                break MISSING_BLOCK_LABEL_121;
            }
            obj = (PrecalculatedZone)obj;
            if (!super.iID.equals(((DateTimeZone) (obj)).iID) || !Arrays.equals(iTransitions, ((PrecalculatedZone) (obj)).iTransitions) || !Arrays.equals(iNameKeys, ((PrecalculatedZone) (obj)).iNameKeys) || !Arrays.equals(iWallOffsets, ((PrecalculatedZone) (obj)).iWallOffsets) || !Arrays.equals(iStandardOffsets, ((PrecalculatedZone) (obj)).iStandardOffsets))
            {
                break; /* Loop/switch isn't completed */
            }
            if (iTailZone != null) goto _L4; else goto _L3
_L3:
            if (((PrecalculatedZone) (obj)).iTailZone == null) goto _L1; else goto _L5
_L5:
            return false;
_L4:
            if (!iTailZone.equals(((PrecalculatedZone) (obj)).iTailZone)) goto _L5; else goto _L6
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

        private PrecalculatedZone(String s, long al[], int ai[], int ai1[], String as[], DSTZone dstzone)
        {
            super(s);
            iTransitions = al;
            iWallOffsets = ai;
            iStandardOffsets = ai1;
            iNameKeys = as;
            iTailZone = dstzone;
        }

        private class DSTZone extends DateTimeZone
        {

            public static final long serialVersionUID = 0x605522c6413e57d1L;
            private final Recurrence iEndRecurrence;
            private final int iStandardOffset;
            private final Recurrence iStartRecurrence;

            private final Recurrence findMatchingRecurrence(long l)
            {
                Recurrence recurrence;
                Recurrence recurrence1;
                int j;
                j = iStandardOffset;
                recurrence = iStartRecurrence;
                recurrence1 = iEndRecurrence;
                Object obj;
                int i;
                i = recurrence1.iSaveMillis;
                obj = recurrence.iOfYear;
                if (((OfYear) (obj)).iMode != 'w') goto _L2; else goto _L1
_L1:
                i += j;
_L9:
                long l4 = l + (long)i;
                ISOChronology isochronology;
                long l3;
                isochronology = ISOChronology.INSTANCE_UTC;
                long l1 = isochronology.monthOfYear().set(l4, ((OfYear) (obj)).iMonthOfYear);
                l1 = isochronology.millisOfDay().set(l1, 0);
                l3 = ((OfYear) (obj)).setDayOfMonthNext(isochronology, isochronology.millisOfDay().add(l1, ((OfYear) (obj)).iMillisOfDay));
                if (((OfYear) (obj)).iDayOfWeek != 0) goto _L4; else goto _L3
_L3:
                long l2;
                l2 = l3;
                if (l3 > l4)
                {
                    break MISSING_BLOCK_LABEL_153;
                }
                l2 = ((OfYear) (obj)).setDayOfMonthNext(isochronology, isochronology.year().add(l3, 1));
_L11:
                l3 = l2 - (long)i;
_L12:
                i = recurrence.iSaveMillis;
                obj = recurrence1.iOfYear;
                if (((OfYear) (obj)).iMode != 'w') goto _L6; else goto _L5
_L5:
                i += j;
_L14:
                long l5 = l + (long)i;
                isochronology = ISOChronology.INSTANCE_UTC;
                l2 = isochronology.monthOfYear().set(l5, ((OfYear) (obj)).iMonthOfYear);
                l2 = isochronology.millisOfDay().set(l2, 0);
                l4 = ((OfYear) (obj)).setDayOfMonthNext(isochronology, isochronology.millisOfDay().add(l2, ((OfYear) (obj)).iMillisOfDay));
                if (((OfYear) (obj)).iDayOfWeek != 0) goto _L8; else goto _L7
_L7:
                l2 = l4;
                if (l4 > l5)
                {
                    break MISSING_BLOCK_LABEL_297;
                }
                l2 = ((OfYear) (obj)).setDayOfMonthNext(isochronology, isochronology.year().add(l4, 1));
_L16:
                l = l2 - (long)i;
_L17:
                if (l3 > l)
                {
                    return recurrence;
                } else
                {
                    return recurrence1;
                }
_L2:
                Object obj1;
                if (((OfYear) (obj)).iMode == 's')
                {
                    i = j;
                } else
                {
                    i = 0;
                }
                  goto _L9
_L4:
                l3 = ((OfYear) (obj)).setDayOfWeek(isochronology, l3);
                l2 = l3;
                if (l3 > l4) goto _L11; else goto _L10
_L10:
                l2 = isochronology.year().add(l3, 1);
                l2 = ((OfYear) (obj)).setDayOfWeek(isochronology, ((OfYear) (obj)).setDayOfMonthNext(isochronology, isochronology.monthOfYear().set(l2, ((OfYear) (obj)).iMonthOfYear)));
                  goto _L11
                obj;
                l3 = l;
                  goto _L12
                obj;
                l3 = l;
                  goto _L12
_L6:
                i = j;
                if (((OfYear) (obj)).iMode == 's') goto _L14; else goto _L13
_L13:
                i = 0;
                  goto _L14
_L8:
                l4 = ((OfYear) (obj)).setDayOfWeek(isochronology, l4);
                l2 = l4;
                if (l4 > l5) goto _L16; else goto _L15
_L15:
                l2 = isochronology.year().add(l4, 1);
                l2 = ((OfYear) (obj)).setDayOfWeek(isochronology, ((OfYear) (obj)).setDayOfMonthNext(isochronology, isochronology.monthOfYear().set(l2, ((OfYear) (obj)).iMonthOfYear)));
                  goto _L16
                obj1;
                  goto _L17
                obj1;
                  goto _L17
            }

            public final boolean equals(Object obj)
            {
                if (this != obj)
                {
                    if (obj instanceof DSTZone)
                    {
                        if (!super.iID.equals(((DateTimeZone) (obj = (DSTZone)obj)).iID) || iStandardOffset != ((DSTZone) (obj)).iStandardOffset || !iStartRecurrence.equals(((DSTZone) (obj)).iStartRecurrence) || !iEndRecurrence.equals(((DSTZone) (obj)).iEndRecurrence))
                        {
                            return false;
                        }
                    } else
                    {
                        return false;
                    }
                }
                return true;
            }

            public final String getNameKey(long l)
            {
                return findMatchingRecurrence(l).iNameKey;
            }

            public final int getOffset(long l)
            {
                return iStandardOffset + findMatchingRecurrence(l).iSaveMillis;
            }

            public final int getStandardOffset(long l)
            {
                return iStandardOffset;
            }

            public final boolean isFixed()
            {
                return false;
            }

            public final long nextTransition(long l)
            {
                Object obj;
                Object obj2;
                int j;
                j = iStandardOffset;
                obj = iStartRecurrence;
                obj2 = iEndRecurrence;
                OfYear ofyear;
                int i;
                i = ((Recurrence) (obj2)).iSaveMillis;
                ofyear = ((Recurrence) (obj)).iOfYear;
                if (ofyear.iMode != 'w') goto _L2; else goto _L1
_L1:
                i += j;
_L9:
                long l4 = l + (long)i;
                ISOChronology isochronology;
                long l3;
                isochronology = ISOChronology.INSTANCE_UTC;
                long l1 = isochronology.monthOfYear().set(l4, ofyear.iMonthOfYear);
                l1 = isochronology.millisOfDay().set(l1, 0);
                l3 = ofyear.setDayOfMonthNext(isochronology, isochronology.millisOfDay().add(l1, ofyear.iMillisOfDay));
                if (ofyear.iDayOfWeek != 0) goto _L4; else goto _L3
_L3:
                long l2;
                l2 = l3;
                if (l3 > l4)
                {
                    break MISSING_BLOCK_LABEL_153;
                }
                l2 = ofyear.setDayOfMonthNext(isochronology, isochronology.year().add(l3, 1));
_L11:
                l3 = l2 - (long)i;
                l2 = l3;
                if (l > 0L)
                {
                    l2 = l3;
                    if (l3 < 0L)
                    {
                        l2 = l;
                    }
                }
                l3 = l2;
_L12:
                i = ((Recurrence) (obj)).iSaveMillis;
                obj = ((Recurrence) (obj2)).iOfYear;
                if (((OfYear) (obj)).iMode != 'w') goto _L6; else goto _L5
_L5:
                i += j;
_L14:
                long l5 = l + (long)i;
                obj2 = ISOChronology.INSTANCE_UTC;
                l2 = ((Chronology) (obj2)).monthOfYear().set(l5, ((OfYear) (obj)).iMonthOfYear);
                l2 = ((Chronology) (obj2)).millisOfDay().set(l2, 0);
                l4 = ((OfYear) (obj)).setDayOfMonthNext(((Chronology) (obj2)), ((Chronology) (obj2)).millisOfDay().add(l2, ((OfYear) (obj)).iMillisOfDay));
                if (((OfYear) (obj)).iDayOfWeek != 0) goto _L8; else goto _L7
_L7:
                l2 = l4;
                if (l4 > l5)
                {
                    break MISSING_BLOCK_LABEL_318;
                }
                l2 = ((OfYear) (obj)).setDayOfMonthNext(((Chronology) (obj2)), ((Chronology) (obj2)).year().add(l4, 1));
_L16:
                l2 -= i;
                Object obj1;
                Object obj3;
                if (l <= 0L || l2 >= 0L)
                {
                    l = l2;
                }
_L17:
                if (l3 > l)
                {
                    return l;
                } else
                {
                    return l3;
                }
_L2:
                if (ofyear.iMode == 's')
                {
                    i = j;
                } else
                {
                    i = 0;
                }
                  goto _L9
_L4:
                l3 = ofyear.setDayOfWeek(isochronology, l3);
                l2 = l3;
                if (l3 > l4) goto _L11; else goto _L10
_L10:
                l2 = isochronology.year().add(l3, 1);
                l2 = ofyear.setDayOfWeek(isochronology, ofyear.setDayOfMonthNext(isochronology, isochronology.monthOfYear().set(l2, ofyear.iMonthOfYear)));
                  goto _L11
                obj3;
                l3 = l;
                  goto _L12
                obj3;
                l3 = l;
                  goto _L12
_L6:
                i = j;
                if (((OfYear) (obj)).iMode == 's') goto _L14; else goto _L13
_L13:
                i = 0;
                  goto _L14
_L8:
                l4 = ((OfYear) (obj)).setDayOfWeek(((Chronology) (obj2)), l4);
                l2 = l4;
                if (l4 > l5) goto _L16; else goto _L15
_L15:
                l2 = ((Chronology) (obj2)).year().add(l4, 1);
                l2 = ((OfYear) (obj)).setDayOfWeek(((Chronology) (obj2)), ((OfYear) (obj)).setDayOfMonthNext(((Chronology) (obj2)), ((Chronology) (obj2)).monthOfYear().set(l2, ((OfYear) (obj)).iMonthOfYear)));
                  goto _L16
                obj1;
                  goto _L17
                obj1;
                  goto _L17
            }

            public final long previousTransition(long l)
            {
                Object obj;
                Object obj2;
                int j;
                l++;
                j = iStandardOffset;
                obj = iStartRecurrence;
                obj2 = iEndRecurrence;
                OfYear ofyear;
                int i;
                i = ((Recurrence) (obj2)).iSaveMillis;
                ofyear = ((Recurrence) (obj)).iOfYear;
                if (ofyear.iMode != 'w') goto _L2; else goto _L1
_L1:
                i += j;
_L9:
                long l4 = l + (long)i;
                ISOChronology isochronology;
                long l3;
                isochronology = ISOChronology.INSTANCE_UTC;
                long l1 = isochronology.monthOfYear().set(l4, ofyear.iMonthOfYear);
                l1 = isochronology.millisOfDay().set(l1, 0);
                l3 = ofyear.setDayOfMonthPrevious(isochronology, isochronology.millisOfDay().add(l1, ofyear.iMillisOfDay));
                if (ofyear.iDayOfWeek != 0) goto _L4; else goto _L3
_L3:
                long l2;
                l2 = l3;
                if (l3 < l4)
                {
                    break MISSING_BLOCK_LABEL_157;
                }
                l2 = ofyear.setDayOfMonthPrevious(isochronology, isochronology.year().add(l3, -1));
_L11:
                l3 = l2 - (long)i;
                l2 = l3;
                if (l < 0L)
                {
                    l2 = l3;
                    if (l3 > 0L)
                    {
                        l2 = l;
                    }
                }
_L12:
                i = ((Recurrence) (obj)).iSaveMillis;
                obj = ((Recurrence) (obj2)).iOfYear;
                if (((OfYear) (obj)).iMode != 'w') goto _L6; else goto _L5
_L5:
                i += j;
_L14:
                long l5 = l + (long)i;
                obj2 = ISOChronology.INSTANCE_UTC;
                l3 = ((Chronology) (obj2)).monthOfYear().set(l5, ((OfYear) (obj)).iMonthOfYear);
                l3 = ((Chronology) (obj2)).millisOfDay().set(l3, 0);
                l4 = ((OfYear) (obj)).setDayOfMonthPrevious(((Chronology) (obj2)), ((Chronology) (obj2)).millisOfDay().add(l3, ((OfYear) (obj)).iMillisOfDay));
                if (((OfYear) (obj)).iDayOfWeek != 0) goto _L8; else goto _L7
_L7:
                l3 = l4;
                if (l4 < l5)
                {
                    break MISSING_BLOCK_LABEL_318;
                }
                l3 = ((OfYear) (obj)).setDayOfMonthPrevious(((Chronology) (obj2)), ((Chronology) (obj2)).year().add(l4, -1));
_L16:
                l3 -= i;
                Object obj1;
                Object obj3;
                if (l >= 0L || l3 <= 0L)
                {
                    l = l3;
                }
_L17:
                l3 = l;
                if (l2 > l)
                {
                    l3 = l2;
                }
                return l3 - 1L;
_L2:
                if (ofyear.iMode == 's')
                {
                    i = j;
                } else
                {
                    i = 0;
                }
                  goto _L9
_L4:
                l3 = ofyear.setDayOfWeek(isochronology, l3);
                l2 = l3;
                if (l3 < l4) goto _L11; else goto _L10
_L10:
                l2 = isochronology.year().add(l3, -1);
                l2 = ofyear.setDayOfWeek(isochronology, ofyear.setDayOfMonthPrevious(isochronology, isochronology.monthOfYear().set(l2, ofyear.iMonthOfYear)));
                  goto _L11
                obj3;
                l2 = l;
                  goto _L12
                obj3;
                l2 = l;
                  goto _L12
_L6:
                i = j;
                if (((OfYear) (obj)).iMode == 's') goto _L14; else goto _L13
_L13:
                i = 0;
                  goto _L14
_L8:
                l4 = ((OfYear) (obj)).setDayOfWeek(((Chronology) (obj2)), l4);
                l3 = l4;
                if (l4 < l5) goto _L16; else goto _L15
_L15:
                l3 = ((Chronology) (obj2)).year().add(l4, -1);
                l3 = ((OfYear) (obj)).setDayOfWeek(((Chronology) (obj2)), ((OfYear) (obj)).setDayOfMonthPrevious(((Chronology) (obj2)), ((Chronology) (obj2)).monthOfYear().set(l3, ((OfYear) (obj)).iMonthOfYear)));
                  goto _L16
                obj1;
                  goto _L17
                obj1;
                  goto _L17
            }

            DSTZone(String s, int i, Recurrence recurrence, Recurrence recurrence1)
            {
                super(s);
                iStandardOffset = i;
                iStartRecurrence = recurrence;
                iEndRecurrence = recurrence1;
            }
        }


        private class Recurrence
        {

            public final String iNameKey;
            public final OfYear iOfYear;
            public final int iSaveMillis;

            public final boolean equals(Object obj)
            {
                if (this != obj)
                {
                    if (obj instanceof Recurrence)
                    {
                        if (iSaveMillis != ((Recurrence) (obj = (Recurrence)obj)).iSaveMillis || !iNameKey.equals(((Recurrence) (obj)).iNameKey) || !iOfYear.equals(((Recurrence) (obj)).iOfYear))
                        {
                            return false;
                        }
                    } else
                    {
                        return false;
                    }
                }
                return true;
            }

            Recurrence(OfYear ofyear, String s, int i)
            {
                iOfYear = ofyear;
                iNameKey = s;
                iSaveMillis = i;
            }
        }


        private class OfYear
        {

            private final boolean iAdvance;
            private final int iDayOfMonth;
            public final int iDayOfWeek;
            public final int iMillisOfDay;
            public final char iMode;
            public final int iMonthOfYear;

            private final long setDayOfMonth(Chronology chronology, long l)
            {
                if (iDayOfMonth >= 0)
                {
                    return chronology.dayOfMonth().set(l, iDayOfMonth);
                } else
                {
                    l = chronology.dayOfMonth().set(l, 1);
                    l = chronology.monthOfYear().add(l, 1);
                    return chronology.dayOfMonth().add(l, iDayOfMonth);
                }
            }

            public final boolean equals(Object obj)
            {
                if (this != obj)
                {
                    if (obj instanceof OfYear)
                    {
                        if (iMode != ((OfYear) (obj = (OfYear)obj)).iMode || iMonthOfYear != ((OfYear) (obj)).iMonthOfYear || iDayOfMonth != ((OfYear) (obj)).iDayOfMonth || iDayOfWeek != ((OfYear) (obj)).iDayOfWeek || iAdvance != ((OfYear) (obj)).iAdvance || iMillisOfDay != ((OfYear) (obj)).iMillisOfDay)
                        {
                            return false;
                        }
                    } else
                    {
                        return false;
                    }
                }
                return true;
            }

            final long setDayOfMonthNext(Chronology chronology, long l)
            {
                long l1;
                try
                {
                    l1 = setDayOfMonth(chronology, l);
                }
                catch (IllegalArgumentException illegalargumentexception)
                {
                    if (iMonthOfYear == 2 && iDayOfMonth == 29)
                    {
                        for (; !chronology.year().isLeap(l); l = chronology.year().add(l, 1)) { }
                        return setDayOfMonth(chronology, l);
                    } else
                    {
                        throw illegalargumentexception;
                    }
                }
                return l1;
            }

            final long setDayOfMonthPrevious(Chronology chronology, long l)
            {
                long l1;
                try
                {
                    l1 = setDayOfMonth(chronology, l);
                }
                catch (IllegalArgumentException illegalargumentexception)
                {
                    if (iMonthOfYear == 2 && iDayOfMonth == 29)
                    {
                        for (; !chronology.year().isLeap(l); l = chronology.year().add(l, -1)) { }
                        return setDayOfMonth(chronology, l);
                    } else
                    {
                        throw illegalargumentexception;
                    }
                }
                return l1;
            }

            final long setDayOfWeek(Chronology chronology, long l)
            {
                int k;
                long l1;
                int i = chronology.dayOfWeek().get(l);
                k = iDayOfWeek - i;
                l1 = l;
                if (k == 0) goto _L2; else goto _L1
_L1:
                if (!iAdvance) goto _L4; else goto _L3
_L3:
                int j;
                j = k;
                if (k < 0)
                {
                    j = k + 7;
                }
_L6:
                l1 = chronology.dayOfWeek().add(l, j);
_L2:
                return l1;
_L4:
                j = k;
                if (k > 0)
                {
                    j = k - 7;
                }
                if (true) goto _L6; else goto _L5
_L5:
            }

            OfYear(char c, int i, int j, int k, boolean flag, int l)
            {
                if (c != 'u' && c != 'w' && c != 's')
                {
                    throw new IllegalArgumentException((new StringBuilder(15)).append("Unknown mode: ").append(c).toString());
                } else
                {
                    iMode = c;
                    iMonthOfYear = i;
                    iDayOfMonth = j;
                    iDayOfWeek = k;
                    iAdvance = flag;
                    iMillisOfDay = l;
                    return;
                }
            }
        }

    }

}
