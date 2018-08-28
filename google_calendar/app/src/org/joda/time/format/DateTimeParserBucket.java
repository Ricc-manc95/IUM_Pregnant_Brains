// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.format;

import java.util.Arrays;
import java.util.Locale;
import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.IllegalInstantException;

public final class DateTimeParserBucket
{

    public final Chronology iChrono;
    private int iDefaultYear;
    public Locale iLocale;
    private final long iMillis = 0L;
    public Integer iOffset;
    public Integer iPivotYear;
    public SavedField iSavedFields[];
    public int iSavedFieldsCount;
    public boolean iSavedFieldsShared;
    public Object iSavedState;
    public DateTimeZone iZone;

    public DateTimeParserBucket(long l, Chronology chronology, Locale locale, Integer integer, int i)
    {
        iSavedFields = new SavedField[8];
        chronology = DateTimeUtils.getChronology(chronology);
        iZone = chronology.getZone();
        iChrono = chronology.withUTC();
        chronology = locale;
        if (locale == null)
        {
            chronology = Locale.getDefault();
        }
        iLocale = chronology;
        iPivotYear = integer;
        iDefaultYear = i;
    }

    static int compareReverse(DurationField durationfield, DurationField durationfield1)
    {
        if (durationfield == null || !durationfield.isSupported())
        {
            return durationfield1 != null && durationfield1.isSupported() ? -1 : 0;
        }
        if (durationfield1 == null || !durationfield1.isSupported())
        {
            return 1;
        } else
        {
            return -durationfield.compareTo(durationfield1);
        }
    }

    public final long computeMillis(boolean flag, String s)
    {
        Object obj;
        int i1;
        do
        {
label0:
            {
                obj = iSavedFields;
                i1 = iSavedFieldsCount;
                if (iSavedFieldsShared)
                {
                    obj = (SavedField[])iSavedFields.clone();
                    iSavedFields = ((SavedField []) (obj));
                    iSavedFieldsShared = false;
                }
                DurationField durationfield;
                DurationField durationfield1;
                DurationField durationfield2;
                int i;
                if (i1 > 10)
                {
                    Arrays.sort(((Object []) (obj)), 0, i1);
                } else
                {
                    int j = 0;
                    while (j < i1) 
                    {
                        for (int l = j; l > 0 && obj[l - 1].compareTo(obj[l]) > 0; l--)
                        {
                            Object obj1 = obj[l];
                            obj[l] = obj[l - 1];
                            obj[l - 1] = obj1;
                        }

                        j++;
                    }
                }
                if (i1 <= 0)
                {
                    break label0;
                }
                durationfield = DurationFieldType.MONTHS_TYPE.getField(iChrono);
                durationfield1 = DurationFieldType.DAYS_TYPE.getField(iChrono);
                durationfield2 = obj[0].iField.getDurationField();
                if (compareReverse(durationfield2, durationfield) < 0 || compareReverse(durationfield2, durationfield1) > 0)
                {
                    break label0;
                }
                obj = DateTimeFieldType.YEAR_TYPE;
                i = iDefaultYear;
                saveField(new SavedField(((DateTimeFieldType) (obj)).getField(iChrono), i));
            }
        } while (true);
        int k;
        long l1;
        l1 = iMillis;
        k = 0;
_L2:
        if (k >= i1)
        {
            break; /* Loop/switch isn't completed */
        }
        l1 = obj[k].set(l1, flag);
        k++;
        if (true) goto _L2; else goto _L1
_L19:
        String s1;
        l1 = s1.set(l1, flag);
        k++;
          goto _L3
_L17:
        flag = false;
        continue; /* Loop/switch isn't completed */
        obj;
        if (s == null) goto _L5; else goto _L4
_L4:
        s = (new StringBuilder(String.valueOf(s).length() + 15)).append("Cannot parse \"").append(s).append('"').toString();
        if (((IllegalFieldValueException) (obj)).iMessage != null) goto _L7; else goto _L6
_L6:
        obj.iMessage = s;
_L5:
        throw obj;
_L7:
        if (s != null)
        {
            s1 = ((IllegalFieldValueException) (obj)).iMessage;
            obj.iMessage = (new StringBuilder(String.valueOf(s).length() + 2 + String.valueOf(s1).length())).append(s).append(": ").append(s1).toString();
        }
        if (true) goto _L5; else goto _L8
_L8:
        if (iOffset == null) goto _L10; else goto _L9
_L9:
        long l2;
        l1 = l2 - (long)iOffset.intValue();
_L12:
        return l1;
_L10:
        l1 = l2;
        if (iZone == null) goto _L12; else goto _L11
_L11:
        k = iZone.getOffsetFromLocal(l2);
        l2 -= k;
        l1 = l2;
        if (k == iZone.getOffset(l2)) goto _L12; else goto _L13
_L13:
        obj = String.valueOf(iZone);
        s1 = (new StringBuilder(String.valueOf(obj).length() + 53)).append("Illegal instant due to time zone offset transition (").append(((String) (obj))).append(')').toString();
        obj = s1;
        if (s != null)
        {
            obj = (new StringBuilder(String.valueOf(s).length() + 17 + String.valueOf(s1).length())).append("Cannot parse \"").append(s).append("\": ").append(s1).toString();
        }
        throw new IllegalInstantException(((String) (obj)));
_L1:
        l2 = l1;
        if (!flag) goto _L8; else goto _L14
_L14:
        k = 0;
_L3:
        l2 = l1;
        if (k >= i1) goto _L8; else goto _L15
_L15:
        s1 = obj[k];
        if (k != i1 - 1) goto _L17; else goto _L16
_L16:
        flag = true;
        if (true) goto _L19; else goto _L18
_L18:
    }

    final void saveField(SavedField savedfield)
    {
        SavedField asavedfield[] = iSavedFields;
        int j = iSavedFieldsCount;
        if (j == asavedfield.length || iSavedFieldsShared)
        {
            SavedField asavedfield1[];
            int i;
            if (j == asavedfield.length)
            {
                i = j << 1;
            } else
            {
                i = asavedfield.length;
            }
            asavedfield1 = new SavedField[i];
            System.arraycopy(asavedfield, 0, asavedfield1, 0, j);
            iSavedFields = asavedfield1;
            iSavedFieldsShared = false;
            asavedfield = asavedfield1;
        }
        iSavedState = null;
        asavedfield[j] = savedfield;
        iSavedFieldsCount = j + 1;
    }

    private class SavedField
        implements Comparable
    {

        public final DateTimeField iField;
        private final Locale iLocale;
        private final String iText;
        private final int iValue;

        public final volatile int compareTo(Object obj)
        {
            return compareTo((SavedField)obj);
        }

        public final int compareTo(SavedField savedfield)
        {
            savedfield = savedfield.iField;
            int i = DateTimeParserBucket.compareReverse(iField.getRangeDurationField(), savedfield.getRangeDurationField());
            if (i != 0)
            {
                return i;
            } else
            {
                return DateTimeParserBucket.compareReverse(iField.getDurationField(), savedfield.getDurationField());
            }
        }

        final long set(long l, boolean flag)
        {
            long l1;
            if (iText == null)
            {
                l = iField.set(l, iValue);
            } else
            {
                l = iField.set(l, iText, iLocale);
            }
            l1 = l;
            if (flag)
            {
                l1 = iField.roundFloor(l);
            }
            return l1;
        }

        SavedField(DateTimeField datetimefield, int i)
        {
            iField = datetimefield;
            iValue = i;
            iText = null;
            iLocale = null;
        }

        SavedField(DateTimeField datetimefield, String s, Locale locale)
        {
            iField = datetimefield;
            iValue = 0;
            iText = s;
            iLocale = locale;
        }
    }

}
