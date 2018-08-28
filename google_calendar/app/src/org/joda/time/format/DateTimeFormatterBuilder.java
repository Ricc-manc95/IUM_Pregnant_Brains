// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.format;

import java.util.ArrayList;
import org.joda.time.DateTimeFieldType;

// Referenced classes of package org.joda.time.format:
//            DateTimeFormatter, DateTimeParser, DateTimePrinter

public final class DateTimeFormatterBuilder
{

    public ArrayList iElementPairs;
    public Object iFormatter;

    public DateTimeFormatterBuilder()
    {
        iElementPairs = new ArrayList();
    }

    private final Object getFormatter()
    {
        Object obj;
        Object obj1;
        obj1 = iFormatter;
        obj = obj1;
        if (obj1 != null) goto _L2; else goto _L1
_L1:
        obj = obj1;
        if (iElementPairs.size() != 2) goto _L4; else goto _L3
_L3:
        Object obj3;
        Object obj4;
        obj3 = iElementPairs.get(0);
        obj4 = iElementPairs.get(1);
        if (obj3 == null) goto _L6; else goto _L5
_L5:
        if (obj3 == obj4) goto _L8; else goto _L7
_L7:
        obj = obj1;
        if (obj4 != null) goto _L4; else goto _L8
_L8:
        obj = obj3;
_L4:
        Object obj2 = obj;
        if (obj == null)
        {
            obj2 = new Composite(iElementPairs);
        }
        iFormatter = obj2;
        obj = obj2;
_L2:
        return obj;
_L6:
        obj = obj4;
        if (true) goto _L4; else goto _L9
_L9:
    }

    public final DateTimeFormatterBuilder append(DateTimeFormatter datetimeformatter)
    {
        if (datetimeformatter == null)
        {
            throw new IllegalArgumentException("No formatter supplied");
        } else
        {
            DateTimePrinter datetimeprinter = datetimeformatter.iPrinter;
            datetimeformatter = datetimeformatter.iParser;
            iFormatter = null;
            iElementPairs.add(datetimeprinter);
            iElementPairs.add(datetimeformatter);
            return this;
        }
    }

    public final DateTimeFormatterBuilder append(DateTimeParser datetimeparser)
    {
        if (datetimeparser == null)
        {
            throw new IllegalArgumentException("No parser supplied");
        } else
        {
            iFormatter = null;
            iElementPairs.add(null);
            iElementPairs.add(datetimeparser);
            return this;
        }
    }

    public final DateTimeFormatterBuilder append(DateTimePrinter datetimeprinter, DateTimeParser adatetimeparser[])
    {
        int i = 0;
        int j = adatetimeparser.length;
        if (j == 1)
        {
            if (adatetimeparser[0] == null)
            {
                throw new IllegalArgumentException("No parser supplied");
            } else
            {
                datetimeprinter = adatetimeparser[0];
                iFormatter = null;
                iElementPairs.add(null);
                iElementPairs.add(datetimeprinter);
                return this;
            }
        }
        datetimeprinter = new DateTimeParser[j];
        for (; i < j - 1; i++)
        {
            DateTimeParser datetimeparser = adatetimeparser[i];
            datetimeprinter[i] = datetimeparser;
            if (datetimeparser == null)
            {
                throw new IllegalArgumentException("Incomplete parser array");
            }
        }

        datetimeprinter[i] = adatetimeparser[i];
        datetimeprinter = new MatchingParser(datetimeprinter);
        iFormatter = null;
        iElementPairs.add(null);
        iElementPairs.add(datetimeprinter);
        return this;
    }

    public final DateTimeFormatterBuilder appendDecimal(DateTimeFieldType datetimefieldtype, int i, int j)
    {
        if (datetimefieldtype == null)
        {
            throw new IllegalArgumentException("Field type must not be null");
        }
        int k = j;
        if (j < i)
        {
            k = i;
        }
        if (i < 0 || k <= 0)
        {
            throw new IllegalArgumentException();
        }
        if (i <= 1)
        {
            datetimefieldtype = new UnpaddedNumber(datetimefieldtype, k, false);
            iFormatter = null;
            iElementPairs.add(datetimefieldtype);
            iElementPairs.add(datetimefieldtype);
            return this;
        } else
        {
            datetimefieldtype = new PaddedNumber(datetimefieldtype, k, false, i);
            iFormatter = null;
            iElementPairs.add(datetimefieldtype);
            iElementPairs.add(datetimefieldtype);
            return this;
        }
    }

    public final DateTimeFormatterBuilder appendFixedDecimal(DateTimeFieldType datetimefieldtype, int i)
    {
        if (datetimefieldtype == null)
        {
            throw new IllegalArgumentException("Field type must not be null");
        }
        if (i <= 0)
        {
            throw new IllegalArgumentException((new StringBuilder(37)).append("Illegal number of digits: ").append(i).toString());
        } else
        {
            datetimefieldtype = new FixedNumber(datetimefieldtype, i, false);
            iFormatter = null;
            iElementPairs.add(datetimefieldtype);
            iElementPairs.add(datetimefieldtype);
            return this;
        }
    }

    public final DateTimeFormatterBuilder appendFraction(DateTimeFieldType datetimefieldtype, int i, int j)
    {
        if (datetimefieldtype == null)
        {
            throw new IllegalArgumentException("Field type must not be null");
        }
        int k = j;
        if (j < i)
        {
            k = i;
        }
        if (i < 0 || k <= 0)
        {
            throw new IllegalArgumentException();
        } else
        {
            datetimefieldtype = new Fraction(datetimefieldtype, i, k);
            iFormatter = null;
            iElementPairs.add(datetimefieldtype);
            iElementPairs.add(datetimefieldtype);
            return this;
        }
    }

    public final DateTimeFormatterBuilder appendLiteral(char c)
    {
        CharacterLiteral characterliteral = new CharacterLiteral(c);
        iFormatter = null;
        iElementPairs.add(characterliteral);
        iElementPairs.add(characterliteral);
        return this;
    }

    public final DateTimeFormatterBuilder appendLiteral(String s)
    {
        switch (s.length())
        {
        default:
            s = new StringLiteral(s);
            iFormatter = null;
            iElementPairs.add(s);
            iElementPairs.add(s);
            // fall through

        case 0: // '\0'
            return this;

        case 1: // '\001'
            s = new CharacterLiteral(s.charAt(0));
            break;
        }
        iFormatter = null;
        iElementPairs.add(s);
        iElementPairs.add(s);
        return this;
    }

    public final DateTimeFormatterBuilder appendOptional(DateTimeParser datetimeparser)
    {
        if (datetimeparser == null)
        {
            throw new IllegalArgumentException("No parser supplied");
        } else
        {
            datetimeparser = new MatchingParser(new DateTimeParser[] {
                datetimeparser, null
            });
            iFormatter = null;
            iElementPairs.add(null);
            iElementPairs.add(datetimeparser);
            return this;
        }
    }

    public final DateTimeFormatterBuilder appendSignedDecimal(DateTimeFieldType datetimefieldtype, int i, int j)
    {
        if (datetimefieldtype == null)
        {
            throw new IllegalArgumentException("Field type must not be null");
        }
        int k = j;
        if (j < i)
        {
            k = i;
        }
        if (i < 0 || k <= 0)
        {
            throw new IllegalArgumentException();
        }
        if (i <= 1)
        {
            datetimefieldtype = new UnpaddedNumber(datetimefieldtype, k, true);
            iFormatter = null;
            iElementPairs.add(datetimefieldtype);
            iElementPairs.add(datetimefieldtype);
            return this;
        } else
        {
            datetimefieldtype = new PaddedNumber(datetimefieldtype, k, true, i);
            iFormatter = null;
            iElementPairs.add(datetimefieldtype);
            iElementPairs.add(datetimefieldtype);
            return this;
        }
    }

    public final DateTimeFormatterBuilder appendTimeZoneOffset(String s, boolean flag, int i, int j)
    {
        s = new TimeZoneOffset(s, s, flag, 2, j);
        iFormatter = null;
        iElementPairs.add(s);
        iElementPairs.add(s);
        return this;
    }

    public final DateTimeFormatter toFormatter()
    {
        Object obj = getFormatter();
        DateTimePrinter datetimeprinter;
        boolean flag;
        if (obj instanceof DateTimePrinter)
        {
            if (obj instanceof Composite)
            {
                if (((Composite)obj).iPrinters != null)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
            } else
            {
                flag = true;
            }
        } else
        {
            flag = false;
        }
        if (flag)
        {
            datetimeprinter = (DateTimePrinter)obj;
        } else
        {
            datetimeprinter = null;
        }
        if (obj instanceof DateTimeParser)
        {
            if (obj instanceof Composite)
            {
                if (((Composite)obj).iParsers != null)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
            } else
            {
                flag = true;
            }
        } else
        {
            flag = false;
        }
        if (flag)
        {
            obj = (DateTimeParser)obj;
        } else
        {
            obj = null;
        }
        if (datetimeprinter != null || obj != null)
        {
            return new DateTimeFormatter(datetimeprinter, ((DateTimeParser) (obj)));
        } else
        {
            throw new UnsupportedOperationException("Both printing and parsing not supported");
        }
    }

    public final DateTimeParser toParser()
    {
        Object obj = getFormatter();
        boolean flag;
        if (obj instanceof DateTimeParser)
        {
            if (obj instanceof Composite)
            {
                if (((Composite)obj).iParsers != null)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
            } else
            {
                flag = true;
            }
        } else
        {
            flag = false;
        }
        if (flag)
        {
            return (DateTimeParser)obj;
        } else
        {
            throw new UnsupportedOperationException("Parsing is not supported");
        }
    }

    private class Composite
        implements DateTimeParser, DateTimePrinter
    {

        private final int iParsedLengthEstimate;
        public final DateTimeParser iParsers[];
        private final int iPrintedLengthEstimate;
        public final DateTimePrinter iPrinters[];

        public final int estimateParsedLength()
        {
            return iParsedLengthEstimate;
        }

        public final int estimatePrintedLength()
        {
            return iPrintedLengthEstimate;
        }

        public final int parseInto(DateTimeParserBucket datetimeparserbucket, String s, int i)
        {
            DateTimeParser adatetimeparser[] = iParsers;
            if (adatetimeparser == null)
            {
                throw new UnsupportedOperationException();
            }
            int k = adatetimeparser.length;
            boolean flag = false;
            int j = i;
            for (i = ((flag) ? 1 : 0); i < k && j >= 0; i++)
            {
                j = adatetimeparser[i].parseInto(datetimeparserbucket, s, j);
            }

            return j;
        }

        public final void printTo(StringBuffer stringbuffer, long l, Chronology chronology, int i, DateTimeZone datetimezone, Locale locale)
        {
            DateTimePrinter adatetimeprinter[] = iPrinters;
            if (adatetimeprinter == null)
            {
                throw new UnsupportedOperationException();
            }
            if (locale == null)
            {
                locale = Locale.getDefault();
            }
            int k = adatetimeprinter.length;
            for (int j = 0; j < k; j++)
            {
                adatetimeprinter[j].printTo(stringbuffer, l, chronology, i, datetimezone, locale);
            }

        }

        Composite(List list)
        {
            boolean flag = false;
            super();
            ArrayList arraylist1 = new ArrayList();
            ArrayList arraylist = new ArrayList();
            int l1 = list.size();
            for (int i = 0; i < l1; i += 2)
            {
                Object obj = list.get(i);
                if (obj instanceof Composite)
                {
                    obj = ((Composite)obj).iPrinters;
                    if (obj != null)
                    {
                        for (int l = 0; l < obj.length; l++)
                        {
                            arraylist1.add(obj[l]);
                        }

                    }
                } else
                {
                    arraylist1.add(obj);
                }
                obj = list.get(i + 1);
                if (obj instanceof Composite)
                {
                    obj = ((Composite)obj).iParsers;
                    if (obj == null)
                    {
                        continue;
                    }
                    for (int i1 = 0; i1 < obj.length; i1++)
                    {
                        arraylist.add(obj[i1]);
                    }

                } else
                {
                    arraylist.add(obj);
                }
            }

            if (arraylist1.contains(null) || arraylist1.isEmpty())
            {
                iPrinters = null;
                iPrintedLengthEstimate = 0;
            } else
            {
                l1 = arraylist1.size();
                iPrinters = new DateTimePrinter[l1];
                int j = 0;
                int j1 = 0;
                for (; j < l1; j++)
                {
                    list = (DateTimePrinter)arraylist1.get(j);
                    j1 += list.estimatePrintedLength();
                    iPrinters[j] = list;
                }

                iPrintedLengthEstimate = j1;
            }
            if (arraylist.contains(null) || arraylist.isEmpty())
            {
                iParsers = null;
                iParsedLengthEstimate = 0;
                return;
            }
            l1 = arraylist.size();
            iParsers = new DateTimeParser[l1];
            int k1 = 0;
            for (int k = ((flag) ? 1 : 0); k < l1; k++)
            {
                list = (DateTimeParser)arraylist.get(k);
                k1 += list.estimateParsedLength();
                iParsers[k] = list;
            }

            iParsedLengthEstimate = k1;
        }
    }


    private class MatchingParser
        implements DateTimeParser
    {

        private final int iParsedLengthEstimate;
        private final DateTimeParser iParsers[];

        public final int estimateParsedLength()
        {
            return iParsedLengthEstimate;
        }

        public final int parseInto(DateTimeParserBucket datetimeparserbucket, String s, int i)
        {
            Object obj;
            DateTimeParser adatetimeparser[];
            Object obj1;
            int j;
            int k;
            int l;
            int j1;
            adatetimeparser = iParsers;
            j1 = adatetimeparser.length;
            if (datetimeparserbucket.iSavedState == null)
            {
                datetimeparserbucket.iSavedState = new DateTimeParserBucket.SavedState(datetimeparserbucket);
            }
            obj1 = datetimeparserbucket.iSavedState;
            obj = null;
            l = 0;
            k = i;
            j = i;
_L3:
            Object obj2;
            if (l >= j1)
            {
                break MISSING_BLOCK_LABEL_414;
            }
            obj2 = adatetimeparser[l];
            if (obj2 != null) goto _L2; else goto _L1
_L1:
            if (k <= i)
            {
                return i;
            }
            l = 1;
_L4:
            if (k > i || k == i && l != 0)
            {
                if (obj != null && (obj instanceof DateTimeParserBucket.SavedState))
                {
                    s = (DateTimeParserBucket.SavedState)obj;
                    int i1;
                    if (datetimeparserbucket != ((DateTimeParserBucket.SavedState) (s)).this$0)
                    {
                        i = 0;
                    } else
                    {
                        datetimeparserbucket.iZone = ((DateTimeParserBucket.SavedState) (s)).iZone;
                        datetimeparserbucket.iOffset = ((DateTimeParserBucket.SavedState) (s)).iOffset;
                        datetimeparserbucket.iSavedFields = ((DateTimeParserBucket.SavedState) (s)).iSavedFields;
                        if (((DateTimeParserBucket.SavedState) (s)).iSavedFieldsCount < datetimeparserbucket.iSavedFieldsCount)
                        {
                            datetimeparserbucket.iSavedFieldsShared = true;
                        }
                        datetimeparserbucket.iSavedFieldsCount = ((DateTimeParserBucket.SavedState) (s)).iSavedFieldsCount;
                        i = 1;
                    }
                    if (i != 0)
                    {
                        datetimeparserbucket.iSavedState = obj;
                    }
                }
                return k;
            } else
            {
                return ~j;
            }
_L2:
            i1 = ((DateTimeParser) (obj2)).parseInto(datetimeparserbucket, s, i);
            if (i1 >= i)
            {
                if (i1 > k)
                {
                    if (i1 >= s.length() || l + 1 >= j1 || adatetimeparser[l + 1] == null)
                    {
                        return i1;
                    }
                    if (datetimeparserbucket.iSavedState == null)
                    {
                        datetimeparserbucket.iSavedState = new DateTimeParserBucket.SavedState(datetimeparserbucket);
                    }
                    obj = datetimeparserbucket.iSavedState;
                    k = i1;
                }
            } else
            if (i1 < 0)
            {
                i1 = ~i1;
                if (i1 > j)
                {
                    j = i1;
                }
            }
            if (obj1 instanceof DateTimeParserBucket.SavedState)
            {
                obj2 = (DateTimeParserBucket.SavedState)obj1;
                if (datetimeparserbucket != ((DateTimeParserBucket.SavedState) (obj2)).this$0)
                {
                    i1 = 0;
                } else
                {
                    datetimeparserbucket.iZone = ((DateTimeParserBucket.SavedState) (obj2)).iZone;
                    datetimeparserbucket.iOffset = ((DateTimeParserBucket.SavedState) (obj2)).iOffset;
                    datetimeparserbucket.iSavedFields = ((DateTimeParserBucket.SavedState) (obj2)).iSavedFields;
                    if (((DateTimeParserBucket.SavedState) (obj2)).iSavedFieldsCount < datetimeparserbucket.iSavedFieldsCount)
                    {
                        datetimeparserbucket.iSavedFieldsShared = true;
                    }
                    datetimeparserbucket.iSavedFieldsCount = ((DateTimeParserBucket.SavedState) (obj2)).iSavedFieldsCount;
                    i1 = 1;
                }
                if (i1 != 0)
                {
                    datetimeparserbucket.iSavedState = obj1;
                }
            }
            l++;
              goto _L3
            l = 0;
              goto _L4
        }

        MatchingParser(DateTimeParser adatetimeparser[])
        {
            iParsers = adatetimeparser;
            int i = 0;
            int j = adatetimeparser.length;
            do
            {
                j--;
                if (j < 0)
                {
                    break;
                }
                DateTimeParser datetimeparser = adatetimeparser[j];
                if (datetimeparser != null)
                {
                    int k = datetimeparser.estimateParsedLength();
                    if (k > i)
                    {
                        i = k;
                    }
                }
            } while (true);
            iParsedLengthEstimate = i;
        }
    }


    private class UnpaddedNumber extends NumberFormatter
    {
        private class NumberFormatter
            implements DateTimeParser, DateTimePrinter
        {

            public final DateTimeFieldType iFieldType;
            public final int iMaxParsedDigits;
            public final boolean iSigned;

            public final int estimateParsedLength()
            {
                return iMaxParsedDigits;
            }

            public int parseInto(DateTimeParserBucket datetimeparserbucket, String s, int i)
            {
                int j;
                int k;
                int l;
                int i1 = Math.min(iMaxParsedDigits, s.length() - i);
                k = 0;
                j = i;
                l = 0;
                i = k;
                k = l;
                do
                {
                    l = i;
                    if (k >= i1)
                    {
                        break;
                    }
                    char c = s.charAt(j + k);
                    if (k == 0 && (c == '-' || c == '+') && iSigned)
                    {
                        if (c == '-')
                        {
                            i = 1;
                        } else
                        {
                            i = 0;
                        }
                        l = i;
                        if (k + 1 >= i1)
                        {
                            break;
                        }
                        c = s.charAt(j + k + 1);
                        l = i;
                        if (c < '0')
                        {
                            break;
                        }
                        l = i;
                        if (c > '9')
                        {
                            break;
                        }
                        if (i != 0)
                        {
                            k++;
                        } else
                        {
                            j++;
                        }
                        i1 = Math.min(i1 + 1, s.length() - j);
                        continue;
                    }
                    l = i;
                    if (c < '0')
                    {
                        break;
                    }
                    l = i;
                    if (c > '9')
                    {
                        break;
                    }
                    k++;
                } while (true);
                if (k == 0)
                {
                    return ~j;
                }
                if (k < 9) goto _L2; else goto _L1
_L1:
                k = j + k;
                j = Integer.parseInt(s.substring(j, k));
_L4:
                datetimeparserbucket.saveField(new DateTimeParserBucket.SavedField(iFieldType.getField(datetimeparserbucket.iChrono), j));
                return k;
_L2:
                int j1;
                int k1;
                if (l != 0)
                {
                    i = j + 1;
                } else
                {
                    i = j;
                }
                k1 = i + 1;
                try
                {
                    i = s.charAt(i);
                }
                // Misplaced declaration of an exception variable
                catch (DateTimeParserBucket datetimeparserbucket)
                {
                    return ~j;
                }
                j1 = j + k;
                i -= 48;
                for (j = k1; j < j1; j++)
                {
                    i = (s.charAt(j) + ((i << 3) + (i << 1))) - 48;
                }

                j = i;
                k = j1;
                if (l != 0)
                {
                    j = -i;
                    k = j1;
                }
                if (true) goto _L4; else goto _L3
_L3:
            }

            NumberFormatter(DateTimeFieldType datetimefieldtype, int i, boolean flag)
            {
                iFieldType = datetimefieldtype;
                iMaxParsedDigits = i;
                iSigned = flag;
            }
        }


        public final int estimatePrintedLength()
        {
            return iMaxParsedDigits;
        }

        public final void printTo(StringBuffer stringbuffer, long l, Chronology chronology, int i, DateTimeZone datetimezone, Locale locale)
        {
            try
            {
                FormatUtils.appendUnpaddedInteger(stringbuffer, iFieldType.getField(chronology).get(l));
                return;
            }
            // Misplaced declaration of an exception variable
            catch (Chronology chronology)
            {
                stringbuffer.append('\uFFFD');
            }
        }

        protected UnpaddedNumber(DateTimeFieldType datetimefieldtype, int i, boolean flag)
        {
            super(datetimefieldtype, i, flag);
        }
    }


    private class PaddedNumber extends NumberFormatter
    {

        private final int iMinPrintedDigits;

        public final int estimatePrintedLength()
        {
            return iMaxParsedDigits;
        }

        public final void printTo(StringBuffer stringbuffer, long l, Chronology chronology, int i, DateTimeZone datetimezone, Locale locale)
        {
            FormatUtils.appendPaddedInteger(stringbuffer, iFieldType.getField(chronology).get(l), iMinPrintedDigits);
_L2:
            return;
            chronology;
            i = iMinPrintedDigits;
            do
            {
                i--;
                if (i < 0)
                {
                    continue;
                }
                stringbuffer.append('\uFFFD');
            } while (true);
            if (true) goto _L2; else goto _L1
_L1:
        }

        protected PaddedNumber(DateTimeFieldType datetimefieldtype, int i, boolean flag, int j)
        {
            super(datetimefieldtype, i, flag);
            iMinPrintedDigits = j;
        }
    }


    private class FixedNumber extends PaddedNumber
    {

        public final int parseInto(DateTimeParserBucket datetimeparserbucket, String s, int i)
        {
            int l = super.parseInto(datetimeparserbucket, s, i);
            if (l >= 0) goto _L2; else goto _L1
_L1:
            int k;
            return l;
_L2:
            int j;
label0:
            {
                if (l == (k = iMaxParsedDigits + i))
                {
                    continue; /* Loop/switch isn't completed */
                }
                j = k;
                if (!iSigned)
                {
                    break label0;
                }
                i = s.charAt(i);
                if (i != 45)
                {
                    j = k;
                    if (i != 43)
                    {
                        break label0;
                    }
                }
                j = k + 1;
            }
            if (l > j)
            {
                return ~(j + 1);
            }
            if (l < j)
            {
                return ~l;
            }
            if (true) goto _L1; else goto _L3
_L3:
        }

        protected FixedNumber(DateTimeFieldType datetimefieldtype, int i, boolean flag)
        {
            super(datetimefieldtype, i, false, i);
        }
    }


    private class Fraction
        implements DateTimeParser, DateTimePrinter
    {

        private final DateTimeFieldType iFieldType;
        private int iMaxDigits;
        private int iMinDigits;

        private final void printTo(StringBuffer stringbuffer, Writer writer, long l, Chronology chronology)
            throws IOException
        {
            int i;
            writer = iFieldType.getField(chronology);
            i = iMinDigits;
            long l2 = writer.remainder(l);
            if (l2 != 0L) goto _L2; else goto _L1
_L1:
            int k = i;
            if (stringbuffer != null)
            {
                do
                {
                    i--;
                    if (i < 0)
                    {
                        break;
                    }
                    stringbuffer.append('0');
                } while (true);
            } else
            {
                i = k - 1;
                if (i >= 0)
                {
                    throw new NullPointerException();
                }
            }
              goto _L3
            writer;
            if (stringbuffer != null)
            {
                do
                {
                    i--;
                    if (i < 0)
                    {
                        break;
                    }
                    stringbuffer.append('\uFFFD');
                } while (true);
            } else
            {
                i--;
                if (i >= 0)
                {
                    throw new NullPointerException();
                }
            }
              goto _L3
_L2:
            int i1;
            long l1;
            l1 = writer.getDurationField().getUnitMillis();
            i1 = iMaxDigits;
_L31:
            i1;
            JVM INSTR tableswitch 1 18: default 228
        //                       1 251
        //                       2 258
        //                       3 265
        //                       4 272
        //                       5 279
        //                       6 286
        //                       7 293
        //                       8 300
        //                       9 307
        //                       10 314
        //                       11 321
        //                       12 328
        //                       13 335
        //                       14 342
        //                       15 349
        //                       16 356
        //                       17 363
        //                       18 370;
               goto _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22
_L4:
            l = 1L;
_L24:
            if ((l1 * l) / l == l1)
            {
                break; /* Loop/switch isn't completed */
            }
            i1--;
            continue; /* Loop/switch isn't completed */
_L5:
            l = 10L;
            continue; /* Loop/switch isn't completed */
_L6:
            l = 100L;
            continue; /* Loop/switch isn't completed */
_L7:
            l = 1000L;
            continue; /* Loop/switch isn't completed */
_L8:
            l = 10000L;
            continue; /* Loop/switch isn't completed */
_L9:
            l = 0x186a0L;
            continue; /* Loop/switch isn't completed */
_L10:
            l = 0xf4240L;
            continue; /* Loop/switch isn't completed */
_L11:
            l = 0x989680L;
            continue; /* Loop/switch isn't completed */
_L12:
            l = 0x5f5e100L;
            continue; /* Loop/switch isn't completed */
_L13:
            l = 0x3b9aca00L;
            continue; /* Loop/switch isn't completed */
_L14:
            l = 0x2540be400L;
            continue; /* Loop/switch isn't completed */
_L15:
            l = 0x174876e800L;
            continue; /* Loop/switch isn't completed */
_L16:
            l = 0xe8d4a51000L;
            continue; /* Loop/switch isn't completed */
_L17:
            l = 0x9184e72a000L;
            continue; /* Loop/switch isn't completed */
_L18:
            l = 0x5af3107a4000L;
            continue; /* Loop/switch isn't completed */
_L19:
            l = 0x38d7ea4c68000L;
            continue; /* Loop/switch isn't completed */
_L20:
            l = 0x2386f26fc10000L;
            continue; /* Loop/switch isn't completed */
_L21:
            l = 0x16345785d8a0000L;
            continue; /* Loop/switch isn't completed */
_L22:
            l = 0xde0b6b3a7640000L;
            if (true) goto _L24; else goto _L23
_L23:
            int j1;
            writer = new long[2];
            writer[0] = (l * l2) / l1;
            writer[1] = i1;
            l = writer[0];
            int k1 = (int)writer[1];
            if ((0x7fffffffL & l) == l)
            {
                writer = Integer.toString((int)l);
            } else
            {
                writer = Long.toString(l);
            }
            j1 = writer.length();
            i1 = i;
            for (i = k1; j1 < i;)
            {
                if (stringbuffer != null)
                {
                    stringbuffer.append('0');
                    i1--;
                    i--;
                } else
                {
                    throw new NullPointerException();
                }
            }

            if (i1 >= i) goto _L26; else goto _L25
_L25:
            for (; i1 < i && j1 > 1 && writer.charAt(j1 - 1) == '0'; j1--)
            {
                i--;
            }

            if (j1 >= writer.length()) goto _L26; else goto _L27
_L27:
            if (stringbuffer != null)
            {
                for (int j = 0; j < j1; j++)
                {
                    stringbuffer.append(writer.charAt(j));
                }

            } else
            if (j1 < 0)
            {
                writer.charAt(0);
                throw new NullPointerException();
            }
              goto _L3
_L26:
            if (stringbuffer == null) goto _L29; else goto _L28
_L28:
            stringbuffer.append(writer);
_L3:
            return;
_L29:
            throw new NullPointerException();
            if (true) goto _L31; else goto _L30
_L30:
        }

        public final int estimateParsedLength()
        {
            return iMaxDigits;
        }

        public final int estimatePrintedLength()
        {
            return iMaxDigits;
        }

        public final int parseInto(DateTimeParserBucket datetimeparserbucket, String s, int i)
        {
            DateTimeField datetimefield = iFieldType.getField(datetimeparserbucket.iChrono);
            int k = Math.min(iMaxDigits, s.length() - i);
            long l = 0L;
            long l1 = datetimefield.getDurationField().getUnitMillis() * 10L;
            int j = 0;
            do
            {
                if (j >= k)
                {
                    break;
                }
                char c = s.charAt(i + j);
                if (c < '0' || c > '9')
                {
                    break;
                }
                j++;
                l1 /= 10L;
                l += (long)(c - 48) * l1;
            } while (true);
            l /= 10L;
            if (j == 0)
            {
                return ~i;
            }
            if (l > 0x7fffffffL)
            {
                return ~i;
            } else
            {
                datetimeparserbucket.saveField(new DateTimeParserBucket.SavedField(new PreciseDateTimeField(DateTimeFieldType.MILLIS_OF_SECOND_TYPE, MillisDurationField.INSTANCE, datetimefield.getDurationField()), (int)l));
                return j + i;
            }
        }

        public final void printTo(StringBuffer stringbuffer, long l, Chronology chronology, int i, DateTimeZone datetimezone, Locale locale)
        {
            try
            {
                printTo(stringbuffer, null, l, chronology);
                return;
            }
            // Misplaced declaration of an exception variable
            catch (StringBuffer stringbuffer)
            {
                return;
            }
        }

        protected Fraction(DateTimeFieldType datetimefieldtype, int i, int j)
        {
            iFieldType = datetimefieldtype;
            int k = j;
            if (j > 18)
            {
                k = 18;
            }
            iMinDigits = i;
            iMaxDigits = k;
        }
    }


    private class CharacterLiteral
        implements DateTimeParser, DateTimePrinter
    {

        private final char iValue;

        public final int estimateParsedLength()
        {
            return 1;
        }

        public final int estimatePrintedLength()
        {
            return 1;
        }

        public final int parseInto(DateTimeParserBucket datetimeparserbucket, String s, int i)
        {
            if (i >= s.length())
            {
                return ~i;
            }
            char c1 = s.charAt(i);
            char c = iValue;
            if (c1 != c)
            {
                c1 = Character.toUpperCase(c1);
                c = Character.toUpperCase(c);
                if (c1 != c && Character.toLowerCase(c1) != Character.toLowerCase(c))
                {
                    return ~i;
                }
            }
            return i + 1;
        }

        public final void printTo(StringBuffer stringbuffer, long l, Chronology chronology, int i, DateTimeZone datetimezone, Locale locale)
        {
            stringbuffer.append(iValue);
        }

        CharacterLiteral(char c)
        {
            iValue = c;
        }
    }


    private class StringLiteral
        implements DateTimeParser, DateTimePrinter
    {

        private final String iValue;

        public final int estimateParsedLength()
        {
            return iValue.length();
        }

        public final int estimatePrintedLength()
        {
            return iValue.length();
        }

        public final int parseInto(DateTimeParserBucket datetimeparserbucket, String s, int i)
        {
            if (s.regionMatches(true, i, iValue, 0, iValue.length()))
            {
                return iValue.length() + i;
            } else
            {
                return ~i;
            }
        }

        public final void printTo(StringBuffer stringbuffer, long l, Chronology chronology, int i, DateTimeZone datetimezone, Locale locale)
        {
            stringbuffer.append(iValue);
        }

        StringLiteral(String s)
        {
            iValue = s;
        }
    }


    private class TimeZoneOffset
        implements DateTimeParser, DateTimePrinter
    {

        private final int iMaxFields;
        private final int iMinFields;
        private final boolean iShowSeparators;
        private final String iZeroOffsetParseText;
        private final String iZeroOffsetPrintText;

        private static int digitCount(String s, int i, int j)
        {
            j = Math.min(s.length() - i, j);
            int k = 0;
            do
            {
                if (j <= 0)
                {
                    break;
                }
                char c = s.charAt(i + k);
                if (c < '0' || c > '9')
                {
                    break;
                }
                k++;
                j--;
            } while (true);
            return k;
        }

        public final int estimateParsedLength()
        {
            return estimatePrintedLength();
        }

        public final int estimatePrintedLength()
        {
            int j = iMinFields + 1 << 1;
            int i = j;
            if (iShowSeparators)
            {
                i = j + (iMinFields - 1);
            }
            j = i;
            if (iZeroOffsetPrintText != null)
            {
                j = i;
                if (iZeroOffsetPrintText.length() > i)
                {
                    j = iZeroOffsetPrintText.length();
                }
            }
            return j;
        }

        public final int parseInto(DateTimeParserBucket datetimeparserbucket, String s, int i)
        {
            int j;
            boolean flag;
            boolean flag1;
            int l1;
            int i2;
            int j2;
label0:
            {
                flag1 = false;
                j = s.length() - i;
                if (iZeroOffsetParseText == null)
                {
                    break label0;
                }
                if (iZeroOffsetParseText.length() == 0)
                {
                    if (j > 0)
                    {
                        char c = s.charAt(i);
                        if (c == '-' || c == '+')
                        {
                            break label0;
                        }
                    }
                    datetimeparserbucket.iSavedState = null;
                    datetimeparserbucket.iOffset = Integer.valueOf(0);
                    return i;
                }
                if (s.regionMatches(true, i, iZeroOffsetParseText, 0, iZeroOffsetParseText.length()))
                {
                    datetimeparserbucket.iSavedState = null;
                    datetimeparserbucket.iOffset = Integer.valueOf(0);
                    return i + iZeroOffsetParseText.length();
                }
            }
            if (j <= 1)
            {
                return ~i;
            }
            int k = s.charAt(i);
            if (k == '-')
            {
                flag = true;
            } else
            if (k == '+')
            {
                flag = false;
            } else
            {
                return ~i;
            }
            i++;
            if (digitCount(s, i, 2) < 2)
            {
                return ~i;
            }
            k = FormatUtils.parseTwoDigits(s, i);
            if (k > 23)
            {
                return ~i;
            }
            j2 = k * 0x36ee80;
            l1 = j - 1 - 2;
            i2 = i + 2;
            i = i2;
            j = j2;
            if (l1 <= 0) goto _L2; else goto _L1
_L1:
            int k2 = s.charAt(i2);
            if (k2 != ':') goto _L4; else goto _L3
_L3:
            int l;
            l1--;
            l = i2 + 1;
            flag1 = true;
_L10:
            i2 = digitCount(s, l, 2);
            if (i2 != 0) goto _L6; else goto _L5
_L5:
            i = l;
            j = j2;
            if (!flag1) goto _L2; else goto _L6
_L6:
            if (i2 < 2)
            {
                return ~l;
            }
            i = FormatUtils.parseTwoDigits(s, l);
            if (i > 59)
            {
                return ~l;
            }
            j2 += i * 60000;
            k2 = l1 - 2;
            i2 = l + 2;
            i = i2;
            j = j2;
              goto _L7
_L4:
            i = i2;
            j = j2;
            if (k2 < '0') goto _L2; else goto _L8
_L8:
            l = i2;
            if (k2 <= '9') goto _L10; else goto _L9
_L9:
            j = j2;
            i = i2;
_L2:
            l = j;
            j = i;
            i = l;
_L14:
            if (flag)
            {
                i = -i;
            }
            datetimeparserbucket.iSavedState = null;
            datetimeparserbucket.iOffset = Integer.valueOf(i);
            return j;
_L7:
            if (k2 <= 0) goto _L2; else goto _L11
_L11:
            l1 = k2;
            l = i2;
            if (!flag1)
            {
                break MISSING_BLOCK_LABEL_452;
            }
            i = i2;
            j = j2;
            if (s.charAt(i2) != ':') goto _L2; else goto _L12
_L12:
            l1 = k2 - 1;
            l = i2 + 1;
            i2 = digitCount(s, l, 2);
            if (i2 != 0)
            {
                break; /* Loop/switch isn't completed */
            }
            i = l;
            j = j2;
            if (!flag1) goto _L2; else goto _L13
_L13:
label1:
            {
                if (i2 < 2)
                {
                    return ~l;
                }
                i = FormatUtils.parseTwoDigits(s, l);
                if (i > 59)
                {
                    return ~l;
                }
                i2 = j2 + i * 1000;
                l += 2;
                i = l;
                if (l1 - 2 <= 0)
                {
                    break label1;
                }
                j = l;
                if (flag1)
                {
                    if (s.charAt(l) != '.')
                    {
                        i = l;
                        if (s.charAt(l) != ',')
                        {
                            break label1;
                        }
                    }
                    j = l + 1;
                }
                l1 = digitCount(s, j, 3);
                if (l1 == 0)
                {
                    i = j;
                    if (!flag1)
                    {
                        break label1;
                    }
                }
                if (l1 <= 0)
                {
                    return ~j;
                }
                int k1 = j + 1;
                i = (s.charAt(j) - 48) * 100 + i2;
                if (l1 > 1)
                {
                    int i1 = k1 + 1;
                    k1 = (s.charAt(k1) - 48) * 10 + i;
                    i = k1;
                    j = i1;
                    if (l1 > 2)
                    {
                        i = k1 + (s.charAt(i1) - 48);
                        j = i1 + 1;
                    }
                } else
                {
                    j = k1;
                }
            }
              goto _L14
            j = i2;
            int j1 = i;
            i = j;
            j = j1;
              goto _L14
        }

        public final void printTo(StringBuffer stringbuffer, long l, Chronology chronology, int i, DateTimeZone datetimezone, Locale locale)
        {
            if (datetimezone != null)
            {
                if (i == 0 && iZeroOffsetPrintText != null)
                {
                    stringbuffer.append(iZeroOffsetPrintText);
                    return;
                }
                int j;
                if (i >= 0)
                {
                    stringbuffer.append('+');
                } else
                {
                    stringbuffer.append('-');
                    i = -i;
                }
                j = i / 0x36ee80;
                FormatUtils.appendPaddedInteger(stringbuffer, j, 2);
                if (iMaxFields != 1)
                {
                    i -= j * 0x36ee80;
                    if (i != 0 || iMinFields > 1)
                    {
                        int k = i / 60000;
                        if (iShowSeparators)
                        {
                            stringbuffer.append(':');
                        }
                        FormatUtils.appendPaddedInteger(stringbuffer, k, 2);
                        if (iMaxFields != 2)
                        {
                            i -= k * 60000;
                            if (i != 0 || iMinFields > 2)
                            {
                                int i1 = i / 1000;
                                if (iShowSeparators)
                                {
                                    stringbuffer.append(':');
                                }
                                FormatUtils.appendPaddedInteger(stringbuffer, i1, 2);
                                if (iMaxFields != 3)
                                {
                                    i -= i1 * 1000;
                                    if (i != 0 || iMinFields > 3)
                                    {
                                        if (iShowSeparators)
                                        {
                                            stringbuffer.append('.');
                                        }
                                        FormatUtils.appendPaddedInteger(stringbuffer, i, 3);
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        public TimeZoneOffset(String s, String s1, boolean flag, int i, int j)
        {
            byte byte0 = 4;
            super();
            iZeroOffsetPrintText = s;
            iZeroOffsetParseText = s1;
            iShowSeparators = flag;
            if (i <= 0 || j < i)
            {
                throw new IllegalArgumentException();
            }
            if (i > 4)
            {
                j = 4;
                i = byte0;
            }
            iMinFields = i;
            iMaxFields = j;
        }
    }

}
