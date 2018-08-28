// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.format;

import org.joda.time.DateTimeFieldType;

// Referenced classes of package org.joda.time.format:
//            DateTimeFormatterBuilder, DateTimeFormatter, DateTimeParser

public final class 
{

    private static final DateTimeFormatter bd = basicDate();
    private static final DateTimeFormatter bdt;
    private static final DateTimeFormatter bdtx;
    private static final DateTimeFormatter bod = basicOrdinalDate();
    private static final DateTimeFormatter bodt;
    private static final DateTimeFormatter bodtx;
    private static final DateTimeFormatter bt = basicTime();
    private static final DateTimeFormatter btt = basicTTime();
    private static final DateTimeFormatter bttx = basicTTimeNoMillis();
    private static final DateTimeFormatter btx = basicTimeNoMillis();
    private static final DateTimeFormatter bwd = basicWeekDate();
    private static final DateTimeFormatter bwdt;
    private static final DateTimeFormatter bwdtx;
    private static final DateTimeFormatter dh;
    private static final DateTimeFormatter dhm;
    private static final DateTimeFormatter dhms;
    private static final DateTimeFormatter dhmsf;
    private static final DateTimeFormatter dhmsl;
    private static final DateTimeFormatter dme;
    private static final DateTimeFormatter dotp = dateOptionalTimeParser();
    private static final DateTimeFormatter dp;
    private static final DateTimeFormatter dpe = dateElementParser();
    public static final DateTimeFormatter dt;
    public static final DateTimeFormatter dtp;
    private static final DateTimeFormatter dtx;
    private static final DateTimeFormatter dwe;
    private static final DateTimeFormatter dye = dayOfYearElement();
    private static final DateTimeFormatter fse = fractionElement();
    public static final DateTimeFormatter hde;
    private static final DateTimeFormatter hm = hourMinute();
    private static final DateTimeFormatter hms = hourMinuteSecond();
    private static final DateTimeFormatter hmsf = hourMinuteSecondFraction();
    private static final DateTimeFormatter hmsl = hourMinuteSecondMillis();
    private static final DateTimeFormatter ldotp;
    public static final DateTimeFormatter ldp;
    private static final DateTimeFormatter lte;
    private static final DateTimeFormatter ltp;
    private static final DateTimeFormatter mhe = minuteElement();
    private static final DateTimeFormatter mye;
    private static final DateTimeFormatter od = ordinalDate();
    private static final DateTimeFormatter odt;
    private static final DateTimeFormatter odtx;
    private static final DateTimeFormatter sme = secondElement();
    private static final DateTimeFormatter t = time();
    private static final DateTimeFormatter tp;
    private static final DateTimeFormatter tpe = timeElementParser();
    private static final DateTimeFormatter tt = tTime();
    private static final DateTimeFormatter ttx = tTimeNoMillis();
    private static final DateTimeFormatter tx = timeNoMillis();
    private static final DateTimeFormatter wdt;
    private static final DateTimeFormatter wdtx;
    private static final DateTimeFormatter we;
    private static final DateTimeFormatter ww;
    public static final DateTimeFormatter wwd;
    private static final DateTimeFormatter wwe;
    private static final DateTimeFormatter ye;
    private static final DateTimeFormatter ym;
    public static final DateTimeFormatter ymd;
    private static final DateTimeFormatter ze;

    private static DateTimeFormatter basicDate()
    {
        if (bd == null)
        {
            return (new DateTimeFormatterBuilder()).appendSignedDecimal(DateTimeFieldType.YEAR_TYPE, 4, 4).appendFixedDecimal(DateTimeFieldType.MONTH_OF_YEAR_TYPE, 2).appendFixedDecimal(DateTimeFieldType.DAY_OF_MONTH_TYPE, 2).toFormatter();
        } else
        {
            return bd;
        }
    }

    private static DateTimeFormatter basicOrdinalDate()
    {
        if (bod == null)
        {
            return (new DateTimeFormatterBuilder()).appendSignedDecimal(DateTimeFieldType.YEAR_TYPE, 4, 4).appendFixedDecimal(DateTimeFieldType.DAY_OF_YEAR_TYPE, 3).toFormatter();
        } else
        {
            return bod;
        }
    }

    private static DateTimeFormatter basicTTime()
    {
        if (btt == null)
        {
            DateTimeFormatterBuilder datetimeformatterbuilder = new DateTimeFormatterBuilder();
            DateTimeFormatter datetimeformatter;
            if (lte == null)
            {
                datetimeformatter = (new DateTimeFormatterBuilder()).appendLiteral('T').toFormatter();
            } else
            {
                datetimeformatter = lte;
            }
            return datetimeformatterbuilder.append(datetimeformatter).append(basicTime()).toFormatter();
        } else
        {
            return btt;
        }
    }

    private static DateTimeFormatter basicTTimeNoMillis()
    {
        if (bttx == null)
        {
            DateTimeFormatterBuilder datetimeformatterbuilder = new DateTimeFormatterBuilder();
            DateTimeFormatter datetimeformatter;
            if (lte == null)
            {
                datetimeformatter = (new DateTimeFormatterBuilder()).appendLiteral('T').toFormatter();
            } else
            {
                datetimeformatter = lte;
            }
            return datetimeformatterbuilder.append(datetimeformatter).append(basicTimeNoMillis()).toFormatter();
        } else
        {
            return bttx;
        }
    }

    private static DateTimeFormatter basicTime()
    {
        if (bt == null)
        {
            return (new DateTimeFormatterBuilder()).appendFixedDecimal(DateTimeFieldType.HOUR_OF_DAY_TYPE, 2).appendFixedDecimal(DateTimeFieldType.MINUTE_OF_HOUR_TYPE, 2).appendFixedDecimal(DateTimeFieldType.SECOND_OF_MINUTE_TYPE, 2).appendLiteral('.').appendFraction(DateTimeFieldType.SECOND_OF_DAY_TYPE, 3, 9).appendTimeZoneOffset("Z", false, 2, 2).toFormatter();
        } else
        {
            return bt;
        }
    }

    private static DateTimeFormatter basicTimeNoMillis()
    {
        if (btx == null)
        {
            return (new DateTimeFormatterBuilder()).appendFixedDecimal(DateTimeFieldType.HOUR_OF_DAY_TYPE, 2).appendFixedDecimal(DateTimeFieldType.MINUTE_OF_HOUR_TYPE, 2).appendFixedDecimal(DateTimeFieldType.SECOND_OF_MINUTE_TYPE, 2).appendTimeZoneOffset("Z", false, 2, 2).toFormatter();
        } else
        {
            return btx;
        }
    }

    private static DateTimeFormatter basicWeekDate()
    {
        if (bwd == null)
        {
            return (new DateTimeFormatterBuilder()).appendSignedDecimal(DateTimeFieldType.WEEKYEAR_TYPE, 4, 4).appendLiteral('W').appendFixedDecimal(DateTimeFieldType.WEEK_OF_WEEKYEAR_TYPE, 2).appendFixedDecimal(DateTimeFieldType.DAY_OF_WEEK_TYPE, 1).toFormatter();
        } else
        {
            return bwd;
        }
    }

    private static DateTimeFormatter dateElementParser()
    {
        if (dpe == null)
        {
            DateTimeFormatterBuilder datetimeformatterbuilder = new DateTimeFormatterBuilder();
            Object obj = new DateTimeFormatterBuilder();
            DateTimeFormatter datetimeformatter;
            Object obj1;
            DateTimeFormatterBuilder datetimeformatterbuilder1;
            if (ye == null)
            {
                datetimeformatter = (new DateTimeFormatterBuilder()).appendSignedDecimal(DateTimeFieldType.YEAR_TYPE, 4, 9).toFormatter();
            } else
            {
                datetimeformatter = ye;
            }
            obj = ((DateTimeFormatterBuilder) (obj)).append(datetimeformatter);
            obj1 = new DateTimeFormatterBuilder();
            if (mye == null)
            {
                datetimeformatter = (new DateTimeFormatterBuilder()).appendLiteral('-').appendDecimal(DateTimeFieldType.MONTH_OF_YEAR_TYPE, 2, 2).toFormatter();
            } else
            {
                datetimeformatter = mye;
            }
            obj1 = ((DateTimeFormatterBuilder) (obj1)).append(datetimeformatter);
            if (dme == null)
            {
                datetimeformatter = (new DateTimeFormatterBuilder()).appendLiteral('-').appendDecimal(DateTimeFieldType.DAY_OF_MONTH_TYPE, 2, 2).toFormatter();
            } else
            {
                datetimeformatter = dme;
            }
            obj = ((DateTimeFormatterBuilder) (obj)).appendOptional(((DateTimeFormatterBuilder) (obj1)).appendOptional(datetimeformatter.iParser).toParser()).toParser();
            obj1 = new DateTimeFormatterBuilder();
            if (we == null)
            {
                datetimeformatter = (new DateTimeFormatterBuilder()).appendSignedDecimal(DateTimeFieldType.WEEKYEAR_TYPE, 4, 9).toFormatter();
            } else
            {
                datetimeformatter = we;
            }
            obj1 = ((DateTimeFormatterBuilder) (obj1)).append(datetimeformatter);
            if (wwe == null)
            {
                datetimeformatter = (new DateTimeFormatterBuilder()).appendLiteral("-W").appendDecimal(DateTimeFieldType.WEEK_OF_WEEKYEAR_TYPE, 2, 2).toFormatter();
            } else
            {
                datetimeformatter = wwe;
            }
            obj1 = ((DateTimeFormatterBuilder) (obj1)).append(datetimeformatter);
            if (dwe == null)
            {
                datetimeformatter = (new DateTimeFormatterBuilder()).appendLiteral('-').appendDecimal(DateTimeFieldType.DAY_OF_WEEK_TYPE, 1, 1).toFormatter();
            } else
            {
                datetimeformatter = dwe;
            }
            obj1 = ((DateTimeFormatterBuilder) (obj1)).appendOptional(datetimeformatter.iParser).toParser();
            datetimeformatterbuilder1 = new DateTimeFormatterBuilder();
            if (ye == null)
            {
                datetimeformatter = (new DateTimeFormatterBuilder()).appendSignedDecimal(DateTimeFieldType.YEAR_TYPE, 4, 9).toFormatter();
            } else
            {
                datetimeformatter = ye;
            }
            datetimeformatterbuilder1 = datetimeformatterbuilder1.append(datetimeformatter);
            if (dye == null)
            {
                datetimeformatter = (new DateTimeFormatterBuilder()).appendLiteral('-').appendDecimal(DateTimeFieldType.DAY_OF_YEAR_TYPE, 3, 3).toFormatter();
            } else
            {
                datetimeformatter = dye;
            }
            return datetimeformatterbuilder.append(null, new DateTimeParser[] {
                obj, obj1, datetimeformatterbuilder1.append(datetimeformatter).toParser()
            }).toFormatter();
        } else
        {
            return dpe;
        }
    }

    private static DateTimeFormatter dateOptionalTimeParser()
    {
        if (dotp == null)
        {
            DateTimeFormatterBuilder datetimeformatterbuilder = (new DateTimeFormatterBuilder()).appendLiteral('T').appendOptional(timeElementParser().iParser);
            Object obj;
            if (ze == null)
            {
                obj = (new DateTimeFormatterBuilder()).appendTimeZoneOffset("Z", true, 2, 4).toFormatter();
            } else
            {
                obj = ze;
            }
            obj = datetimeformatterbuilder.appendOptional(((DateTimeFormatter) (obj)).iParser).toParser();
            return (new DateTimeFormatterBuilder()).append(dateElementParser()).appendOptional(((DateTimeParser) (obj))).toFormatter();
        } else
        {
            return dotp;
        }
    }

    private static DateTimeFormatter dayOfMonthElement()
    {
        if (dme == null)
        {
            return (new DateTimeFormatterBuilder()).appendLiteral('-').appendDecimal(DateTimeFieldType.DAY_OF_MONTH_TYPE, 2, 2).toFormatter();
        } else
        {
            return dme;
        }
    }

    private static DateTimeFormatter dayOfWeekElement()
    {
        if (dwe == null)
        {
            return (new DateTimeFormatterBuilder()).appendLiteral('-').appendDecimal(DateTimeFieldType.DAY_OF_WEEK_TYPE, 1, 1).toFormatter();
        } else
        {
            return dwe;
        }
    }

    private static DateTimeFormatter dayOfYearElement()
    {
        if (dye == null)
        {
            return (new DateTimeFormatterBuilder()).appendLiteral('-').appendDecimal(DateTimeFieldType.DAY_OF_YEAR_TYPE, 3, 3).toFormatter();
        } else
        {
            return dye;
        }
    }

    private static DateTimeFormatter fractionElement()
    {
        if (fse == null)
        {
            return (new DateTimeFormatterBuilder()).appendLiteral('.').appendFraction(DateTimeFieldType.SECOND_OF_DAY_TYPE, 3, 9).toFormatter();
        } else
        {
            return fse;
        }
    }

    private static DateTimeFormatter hourElement()
    {
        if (hde == null)
        {
            return (new DateTimeFormatterBuilder()).appendDecimal(DateTimeFieldType.HOUR_OF_DAY_TYPE, 2, 2).toFormatter();
        } else
        {
            return hde;
        }
    }

    private static DateTimeFormatter hourMinute()
    {
        if (hm == null)
        {
            DateTimeFormatterBuilder datetimeformatterbuilder = new DateTimeFormatterBuilder();
            DateTimeFormatter datetimeformatter;
            if (hde == null)
            {
                datetimeformatter = (new DateTimeFormatterBuilder()).appendDecimal(DateTimeFieldType.HOUR_OF_DAY_TYPE, 2, 2).toFormatter();
            } else
            {
                datetimeformatter = hde;
            }
            datetimeformatterbuilder = datetimeformatterbuilder.append(datetimeformatter);
            if (mhe == null)
            {
                datetimeformatter = (new DateTimeFormatterBuilder()).appendLiteral(':').appendDecimal(DateTimeFieldType.MINUTE_OF_HOUR_TYPE, 2, 2).toFormatter();
            } else
            {
                datetimeformatter = mhe;
            }
            return datetimeformatterbuilder.append(datetimeformatter).toFormatter();
        } else
        {
            return hm;
        }
    }

    private static DateTimeFormatter hourMinuteSecond()
    {
        if (hms == null)
        {
            DateTimeFormatterBuilder datetimeformatterbuilder = new DateTimeFormatterBuilder();
            DateTimeFormatter datetimeformatter;
            if (hde == null)
            {
                datetimeformatter = (new DateTimeFormatterBuilder()).appendDecimal(DateTimeFieldType.HOUR_OF_DAY_TYPE, 2, 2).toFormatter();
            } else
            {
                datetimeformatter = hde;
            }
            datetimeformatterbuilder = datetimeformatterbuilder.append(datetimeformatter);
            if (mhe == null)
            {
                datetimeformatter = (new DateTimeFormatterBuilder()).appendLiteral(':').appendDecimal(DateTimeFieldType.MINUTE_OF_HOUR_TYPE, 2, 2).toFormatter();
            } else
            {
                datetimeformatter = mhe;
            }
            datetimeformatterbuilder = datetimeformatterbuilder.append(datetimeformatter);
            if (sme == null)
            {
                datetimeformatter = (new DateTimeFormatterBuilder()).appendLiteral(':').appendDecimal(DateTimeFieldType.SECOND_OF_MINUTE_TYPE, 2, 2).toFormatter();
            } else
            {
                datetimeformatter = sme;
            }
            return datetimeformatterbuilder.append(datetimeformatter).toFormatter();
        } else
        {
            return hms;
        }
    }

    private static DateTimeFormatter hourMinuteSecondFraction()
    {
        if (hmsf == null)
        {
            DateTimeFormatterBuilder datetimeformatterbuilder = new DateTimeFormatterBuilder();
            DateTimeFormatter datetimeformatter;
            if (hde == null)
            {
                datetimeformatter = (new DateTimeFormatterBuilder()).appendDecimal(DateTimeFieldType.HOUR_OF_DAY_TYPE, 2, 2).toFormatter();
            } else
            {
                datetimeformatter = hde;
            }
            datetimeformatterbuilder = datetimeformatterbuilder.append(datetimeformatter);
            if (mhe == null)
            {
                datetimeformatter = (new DateTimeFormatterBuilder()).appendLiteral(':').appendDecimal(DateTimeFieldType.MINUTE_OF_HOUR_TYPE, 2, 2).toFormatter();
            } else
            {
                datetimeformatter = mhe;
            }
            datetimeformatterbuilder = datetimeformatterbuilder.append(datetimeformatter);
            if (sme == null)
            {
                datetimeformatter = (new DateTimeFormatterBuilder()).appendLiteral(':').appendDecimal(DateTimeFieldType.SECOND_OF_MINUTE_TYPE, 2, 2).toFormatter();
            } else
            {
                datetimeformatter = sme;
            }
            datetimeformatterbuilder = datetimeformatterbuilder.append(datetimeformatter);
            if (fse == null)
            {
                datetimeformatter = (new DateTimeFormatterBuilder()).appendLiteral('.').appendFraction(DateTimeFieldType.SECOND_OF_DAY_TYPE, 3, 9).toFormatter();
            } else
            {
                datetimeformatter = fse;
            }
            return datetimeformatterbuilder.append(datetimeformatter).toFormatter();
        } else
        {
            return hmsf;
        }
    }

    private static DateTimeFormatter hourMinuteSecondMillis()
    {
        if (hmsl == null)
        {
            DateTimeFormatterBuilder datetimeformatterbuilder = new DateTimeFormatterBuilder();
            DateTimeFormatter datetimeformatter;
            if (hde == null)
            {
                datetimeformatter = (new DateTimeFormatterBuilder()).appendDecimal(DateTimeFieldType.HOUR_OF_DAY_TYPE, 2, 2).toFormatter();
            } else
            {
                datetimeformatter = hde;
            }
            datetimeformatterbuilder = datetimeformatterbuilder.append(datetimeformatter);
            if (mhe == null)
            {
                datetimeformatter = (new DateTimeFormatterBuilder()).appendLiteral(':').appendDecimal(DateTimeFieldType.MINUTE_OF_HOUR_TYPE, 2, 2).toFormatter();
            } else
            {
                datetimeformatter = mhe;
            }
            datetimeformatterbuilder = datetimeformatterbuilder.append(datetimeformatter);
            if (sme == null)
            {
                datetimeformatter = (new DateTimeFormatterBuilder()).appendLiteral(':').appendDecimal(DateTimeFieldType.SECOND_OF_MINUTE_TYPE, 2, 2).toFormatter();
            } else
            {
                datetimeformatter = sme;
            }
            return datetimeformatterbuilder.append(datetimeformatter).appendLiteral('.').appendFraction(DateTimeFieldType.SECOND_OF_DAY_TYPE, 3, 3).toFormatter();
        } else
        {
            return hmsl;
        }
    }

    private static DateTimeFormatter minuteElement()
    {
        if (mhe == null)
        {
            return (new DateTimeFormatterBuilder()).appendLiteral(':').appendDecimal(DateTimeFieldType.MINUTE_OF_HOUR_TYPE, 2, 2).toFormatter();
        } else
        {
            return mhe;
        }
    }

    private static DateTimeFormatter monthElement()
    {
        if (mye == null)
        {
            return (new DateTimeFormatterBuilder()).appendLiteral('-').appendDecimal(DateTimeFieldType.MONTH_OF_YEAR_TYPE, 2, 2).toFormatter();
        } else
        {
            return mye;
        }
    }

    private static DateTimeFormatter ordinalDate()
    {
        if (od == null)
        {
            DateTimeFormatterBuilder datetimeformatterbuilder = new DateTimeFormatterBuilder();
            DateTimeFormatter datetimeformatter;
            if (ye == null)
            {
                datetimeformatter = (new DateTimeFormatterBuilder()).appendSignedDecimal(DateTimeFieldType.YEAR_TYPE, 4, 9).toFormatter();
            } else
            {
                datetimeformatter = ye;
            }
            datetimeformatterbuilder = datetimeformatterbuilder.append(datetimeformatter);
            if (dye == null)
            {
                datetimeformatter = (new DateTimeFormatterBuilder()).appendLiteral('-').appendDecimal(DateTimeFieldType.DAY_OF_YEAR_TYPE, 3, 3).toFormatter();
            } else
            {
                datetimeformatter = dye;
            }
            return datetimeformatterbuilder.append(datetimeformatter).toFormatter();
        } else
        {
            return od;
        }
    }

    private static DateTimeFormatter secondElement()
    {
        if (sme == null)
        {
            return (new DateTimeFormatterBuilder()).appendLiteral(':').appendDecimal(DateTimeFieldType.SECOND_OF_MINUTE_TYPE, 2, 2).toFormatter();
        } else
        {
            return sme;
        }
    }

    private static DateTimeFormatter tTime()
    {
        if (tt == null)
        {
            DateTimeFormatterBuilder datetimeformatterbuilder = new DateTimeFormatterBuilder();
            DateTimeFormatter datetimeformatter;
            if (lte == null)
            {
                datetimeformatter = (new DateTimeFormatterBuilder()).appendLiteral('T').toFormatter();
            } else
            {
                datetimeformatter = lte;
            }
            return datetimeformatterbuilder.append(datetimeformatter).append(time()).toFormatter();
        } else
        {
            return tt;
        }
    }

    private static DateTimeFormatter tTimeNoMillis()
    {
        if (ttx == null)
        {
            DateTimeFormatterBuilder datetimeformatterbuilder = new DateTimeFormatterBuilder();
            DateTimeFormatter datetimeformatter;
            if (lte == null)
            {
                datetimeformatter = (new DateTimeFormatterBuilder()).appendLiteral('T').toFormatter();
            } else
            {
                datetimeformatter = lte;
            }
            return datetimeformatterbuilder.append(datetimeformatter).append(timeNoMillis()).toFormatter();
        } else
        {
            return ttx;
        }
    }

    private static DateTimeFormatter time()
    {
        if (t == null)
        {
            DateTimeFormatterBuilder datetimeformatterbuilder = (new DateTimeFormatterBuilder()).append(hourMinuteSecondFraction());
            DateTimeFormatter datetimeformatter;
            if (ze == null)
            {
                datetimeformatter = (new DateTimeFormatterBuilder()).appendTimeZoneOffset("Z", true, 2, 4).toFormatter();
            } else
            {
                datetimeformatter = ze;
            }
            return datetimeformatterbuilder.append(datetimeformatter).toFormatter();
        } else
        {
            return t;
        }
    }

    private static DateTimeFormatter timeElementParser()
    {
        if (tpe == null)
        {
            DateTimeParser datetimeparser = (new DateTimeFormatterBuilder()).append(null, new DateTimeParser[] {
                (new DateTimeFormatterBuilder()).appendLiteral('.').toParser(), (new DateTimeFormatterBuilder()).appendLiteral(',').toParser()
            }).toParser();
            DateTimeFormatterBuilder datetimeformatterbuilder = new DateTimeFormatterBuilder();
            DateTimeFormatter datetimeformatter;
            DateTimeFormatterBuilder datetimeformatterbuilder1;
            DateTimeFormatterBuilder datetimeformatterbuilder2;
            if (hde == null)
            {
                datetimeformatter = (new DateTimeFormatterBuilder()).appendDecimal(DateTimeFieldType.HOUR_OF_DAY_TYPE, 2, 2).toFormatter();
            } else
            {
                datetimeformatter = hde;
            }
            datetimeformatterbuilder = datetimeformatterbuilder.append(datetimeformatter);
            datetimeformatterbuilder1 = new DateTimeFormatterBuilder();
            if (mhe == null)
            {
                datetimeformatter = (new DateTimeFormatterBuilder()).appendLiteral(':').appendDecimal(DateTimeFieldType.MINUTE_OF_HOUR_TYPE, 2, 2).toFormatter();
            } else
            {
                datetimeformatter = mhe;
            }
            datetimeformatterbuilder1 = datetimeformatterbuilder1.append(datetimeformatter);
            datetimeformatterbuilder2 = new DateTimeFormatterBuilder();
            if (sme == null)
            {
                datetimeformatter = (new DateTimeFormatterBuilder()).appendLiteral(':').appendDecimal(DateTimeFieldType.SECOND_OF_MINUTE_TYPE, 2, 2).toFormatter();
            } else
            {
                datetimeformatter = sme;
            }
            return datetimeformatterbuilder.append(null, new DateTimeParser[] {
                datetimeformatterbuilder1.append(null, new DateTimeParser[] {
                    datetimeformatterbuilder2.append(datetimeformatter).appendOptional((new DateTimeFormatterBuilder()).append(datetimeparser).appendFraction(DateTimeFieldType.SECOND_OF_DAY_TYPE, 1, 9).toParser()).toParser(), (new DateTimeFormatterBuilder()).append(datetimeparser).appendFraction(DateTimeFieldType.MINUTE_OF_DAY_TYPE, 1, 9).toParser(), null
                }).toParser(), (new DateTimeFormatterBuilder()).append(datetimeparser).appendFraction(DateTimeFieldType.HOUR_OF_DAY_TYPE, 1, 9).toParser(), null
            }).toFormatter();
        } else
        {
            return tpe;
        }
    }

    private static DateTimeFormatter timeNoMillis()
    {
        if (tx == null)
        {
            DateTimeFormatterBuilder datetimeformatterbuilder = (new DateTimeFormatterBuilder()).append(hourMinuteSecond());
            DateTimeFormatter datetimeformatter;
            if (ze == null)
            {
                datetimeformatter = (new DateTimeFormatterBuilder()).appendTimeZoneOffset("Z", true, 2, 4).toFormatter();
            } else
            {
                datetimeformatter = ze;
            }
            return datetimeformatterbuilder.append(datetimeformatter).toFormatter();
        } else
        {
            return tx;
        }
    }

    private static DateTimeFormatter weekElement()
    {
        if (wwe == null)
        {
            return (new DateTimeFormatterBuilder()).appendLiteral("-W").appendDecimal(DateTimeFieldType.WEEK_OF_WEEKYEAR_TYPE, 2, 2).toFormatter();
        } else
        {
            return wwe;
        }
    }

    private static DateTimeFormatter weekyearElement()
    {
        if (we == null)
        {
            return (new DateTimeFormatterBuilder()).appendSignedDecimal(DateTimeFieldType.WEEKYEAR_TYPE, 4, 9).toFormatter();
        } else
        {
            return we;
        }
    }

    private static DateTimeFormatter yearElement()
    {
        if (ye == null)
        {
            return (new DateTimeFormatterBuilder()).appendSignedDecimal(DateTimeFieldType.YEAR_TYPE, 4, 9).toFormatter();
        } else
        {
            return ye;
        }
    }

    static 
    {
        ye = yearElement();
        mye = monthElement();
        dme = dayOfMonthElement();
        we = weekyearElement();
        wwe = weekElement();
        dwe = dayOfWeekElement();
        hde = hourElement();
        Object obj;
        if (ze == null)
        {
            obj = (new DateTimeFormatterBuilder()).appendTimeZoneOffset("Z", true, 2, 4).toFormatter();
        } else
        {
            obj = ze;
        }
        ze = ((DateTimeFormatter) (obj));
        if (lte == null)
        {
            obj = (new DateTimeFormatterBuilder()).appendLiteral('T').toFormatter();
        } else
        {
            obj = lte;
        }
        lte = ((DateTimeFormatter) (obj));
        if (ym == null)
        {
            DateTimeFormatterBuilder datetimeformatterbuilder = new DateTimeFormatterBuilder();
            if (ye == null)
            {
                obj = (new DateTimeFormatterBuilder()).appendSignedDecimal(DateTimeFieldType.YEAR_TYPE, 4, 9).toFormatter();
            } else
            {
                obj = ye;
            }
            datetimeformatterbuilder = datetimeformatterbuilder.append(((DateTimeFormatter) (obj)));
            if (mye == null)
            {
                obj = (new DateTimeFormatterBuilder()).appendLiteral('-').appendDecimal(DateTimeFieldType.MONTH_OF_YEAR_TYPE, 2, 2).toFormatter();
            } else
            {
                obj = mye;
            }
            obj = datetimeformatterbuilder.append(((DateTimeFormatter) (obj))).toFormatter();
        } else
        {
            obj = ym;
        }
        ym = ((DateTimeFormatter) (obj));
        if (ymd == null)
        {
            datetimeformatterbuilder = new DateTimeFormatterBuilder();
            if (ye == null)
            {
                obj = (new DateTimeFormatterBuilder()).appendSignedDecimal(DateTimeFieldType.YEAR_TYPE, 4, 9).toFormatter();
            } else
            {
                obj = ye;
            }
            datetimeformatterbuilder = datetimeformatterbuilder.append(((DateTimeFormatter) (obj)));
            if (mye == null)
            {
                obj = (new DateTimeFormatterBuilder()).appendLiteral('-').appendDecimal(DateTimeFieldType.MONTH_OF_YEAR_TYPE, 2, 2).toFormatter();
            } else
            {
                obj = mye;
            }
            datetimeformatterbuilder = datetimeformatterbuilder.append(((DateTimeFormatter) (obj)));
            if (dme == null)
            {
                obj = (new DateTimeFormatterBuilder()).appendLiteral('-').appendDecimal(DateTimeFieldType.DAY_OF_MONTH_TYPE, 2, 2).toFormatter();
            } else
            {
                obj = dme;
            }
            obj = datetimeformatterbuilder.append(((DateTimeFormatter) (obj))).toFormatter();
        } else
        {
            obj = ymd;
        }
        ymd = ((DateTimeFormatter) (obj));
        if (ww == null)
        {
            datetimeformatterbuilder = new DateTimeFormatterBuilder();
            if (we == null)
            {
                obj = (new DateTimeFormatterBuilder()).appendSignedDecimal(DateTimeFieldType.WEEKYEAR_TYPE, 4, 9).toFormatter();
            } else
            {
                obj = we;
            }
            datetimeformatterbuilder = datetimeformatterbuilder.append(((DateTimeFormatter) (obj)));
            if (wwe == null)
            {
                obj = (new DateTimeFormatterBuilder()).appendLiteral("-W").appendDecimal(DateTimeFieldType.WEEK_OF_WEEKYEAR_TYPE, 2, 2).toFormatter();
            } else
            {
                obj = wwe;
            }
            obj = datetimeformatterbuilder.append(((DateTimeFormatter) (obj))).toFormatter();
        } else
        {
            obj = ww;
        }
        ww = ((DateTimeFormatter) (obj));
        if (wwd == null)
        {
            datetimeformatterbuilder = new DateTimeFormatterBuilder();
            if (we == null)
            {
                obj = (new DateTimeFormatterBuilder()).appendSignedDecimal(DateTimeFieldType.WEEKYEAR_TYPE, 4, 9).toFormatter();
            } else
            {
                obj = we;
            }
            datetimeformatterbuilder = datetimeformatterbuilder.append(((DateTimeFormatter) (obj)));
            if (wwe == null)
            {
                obj = (new DateTimeFormatterBuilder()).appendLiteral("-W").appendDecimal(DateTimeFieldType.WEEK_OF_WEEKYEAR_TYPE, 2, 2).toFormatter();
            } else
            {
                obj = wwe;
            }
            datetimeformatterbuilder = datetimeformatterbuilder.append(((DateTimeFormatter) (obj)));
            if (dwe == null)
            {
                obj = (new DateTimeFormatterBuilder()).appendLiteral('-').appendDecimal(DateTimeFieldType.DAY_OF_WEEK_TYPE, 1, 1).toFormatter();
            } else
            {
                obj = dwe;
            }
            obj = datetimeformatterbuilder.append(((DateTimeFormatter) (obj))).toFormatter();
        } else
        {
            obj = wwd;
        }
        wwd = ((DateTimeFormatter) (obj));
        if (dh == null)
        {
            datetimeformatterbuilder = (new DateTimeFormatterBuilder()).append(ymd);
            if (lte == null)
            {
                obj = (new DateTimeFormatterBuilder()).appendLiteral('T').toFormatter();
            } else
            {
                obj = lte;
            }
            obj = datetimeformatterbuilder.append(((DateTimeFormatter) (obj))).append(hde).toFormatter();
        } else
        {
            obj = dh;
        }
        dh = ((DateTimeFormatter) (obj));
        if (dhm == null)
        {
            datetimeformatterbuilder = (new DateTimeFormatterBuilder()).append(ymd);
            if (lte == null)
            {
                obj = (new DateTimeFormatterBuilder()).appendLiteral('T').toFormatter();
            } else
            {
                obj = lte;
            }
            obj = datetimeformatterbuilder.append(((DateTimeFormatter) (obj))).append(hourMinute()).toFormatter();
        } else
        {
            obj = dhm;
        }
        dhm = ((DateTimeFormatter) (obj));
        if (dhms == null)
        {
            datetimeformatterbuilder = (new DateTimeFormatterBuilder()).append(ymd);
            if (lte == null)
            {
                obj = (new DateTimeFormatterBuilder()).appendLiteral('T').toFormatter();
            } else
            {
                obj = lte;
            }
            obj = datetimeformatterbuilder.append(((DateTimeFormatter) (obj))).append(hourMinuteSecond()).toFormatter();
        } else
        {
            obj = dhms;
        }
        dhms = ((DateTimeFormatter) (obj));
        if (dhmsl == null)
        {
            datetimeformatterbuilder = (new DateTimeFormatterBuilder()).append(ymd);
            if (lte == null)
            {
                obj = (new DateTimeFormatterBuilder()).appendLiteral('T').toFormatter();
            } else
            {
                obj = lte;
            }
            obj = datetimeformatterbuilder.append(((DateTimeFormatter) (obj))).append(hourMinuteSecondMillis()).toFormatter();
        } else
        {
            obj = dhmsl;
        }
        dhmsl = ((DateTimeFormatter) (obj));
        if (dhmsf == null)
        {
            datetimeformatterbuilder = (new DateTimeFormatterBuilder()).append(ymd);
            if (lte == null)
            {
                obj = (new DateTimeFormatterBuilder()).appendLiteral('T').toFormatter();
            } else
            {
                obj = lte;
            }
            obj = datetimeformatterbuilder.append(((DateTimeFormatter) (obj))).append(hourMinuteSecondFraction()).toFormatter();
        } else
        {
            obj = dhmsf;
        }
        dhmsf = ((DateTimeFormatter) (obj));
        if (dt == null)
        {
            obj = (new DateTimeFormatterBuilder()).append(ymd).append(tTime()).toFormatter();
        } else
        {
            obj = dt;
        }
        dt = ((DateTimeFormatter) (obj));
        if (dtx == null)
        {
            obj = (new DateTimeFormatterBuilder()).append(ymd).append(tTimeNoMillis()).toFormatter();
        } else
        {
            obj = dtx;
        }
        dtx = ((DateTimeFormatter) (obj));
        if (wdt == null)
        {
            obj = (new DateTimeFormatterBuilder()).append(wwd).append(tTime()).toFormatter();
        } else
        {
            obj = wdt;
        }
        wdt = ((DateTimeFormatter) (obj));
        if (wdtx == null)
        {
            obj = (new DateTimeFormatterBuilder()).append(wwd).append(tTimeNoMillis()).toFormatter();
        } else
        {
            obj = wdtx;
        }
        wdtx = ((DateTimeFormatter) (obj));
        if (odt == null)
        {
            obj = (new DateTimeFormatterBuilder()).append(ordinalDate()).append(tTime()).toFormatter();
        } else
        {
            obj = odt;
        }
        odt = ((DateTimeFormatter) (obj));
        if (odtx == null)
        {
            obj = (new DateTimeFormatterBuilder()).append(ordinalDate()).append(tTimeNoMillis()).toFormatter();
        } else
        {
            obj = odtx;
        }
        odtx = ((DateTimeFormatter) (obj));
        if (bdt == null)
        {
            obj = (new DateTimeFormatterBuilder()).append(basicDate()).append(basicTTime()).toFormatter();
        } else
        {
            obj = bdt;
        }
        bdt = ((DateTimeFormatter) (obj));
        if (bdtx == null)
        {
            obj = (new DateTimeFormatterBuilder()).append(basicDate()).append(basicTTimeNoMillis()).toFormatter();
        } else
        {
            obj = bdtx;
        }
        bdtx = ((DateTimeFormatter) (obj));
        if (bodt == null)
        {
            obj = (new DateTimeFormatterBuilder()).append(basicOrdinalDate()).append(basicTTime()).toFormatter();
        } else
        {
            obj = bodt;
        }
        bodt = ((DateTimeFormatter) (obj));
        if (bodtx == null)
        {
            obj = (new DateTimeFormatterBuilder()).append(basicOrdinalDate()).append(basicTTimeNoMillis()).toFormatter();
        } else
        {
            obj = bodtx;
        }
        bodtx = ((DateTimeFormatter) (obj));
        if (bwdt == null)
        {
            obj = (new DateTimeFormatterBuilder()).append(basicWeekDate()).append(basicTTime()).toFormatter();
        } else
        {
            obj = bwdt;
        }
        bwdt = ((DateTimeFormatter) (obj));
        if (bwdtx == null)
        {
            obj = (new DateTimeFormatterBuilder()).append(basicWeekDate()).append(basicTTimeNoMillis()).toFormatter();
        } else
        {
            obj = bwdtx;
        }
        bwdtx = ((DateTimeFormatter) (obj));
        if (dp == null)
        {
            datetimeformatterbuilder = (new DateTimeFormatterBuilder()).appendLiteral('T');
            if (ze == null)
            {
                obj = (new DateTimeFormatterBuilder()).appendTimeZoneOffset("Z", true, 2, 4).toFormatter();
            } else
            {
                obj = ze;
            }
            obj = datetimeformatterbuilder.append(((DateTimeFormatter) (obj))).toParser();
            obj = (new DateTimeFormatterBuilder()).append(dateElementParser()).appendOptional(((DateTimeParser) (obj))).toFormatter();
        } else
        {
            obj = dp;
        }
        dp = ((DateTimeFormatter) (obj));
        if (ldp == null)
        {
            obj = dateElementParser().withZoneUTC();
        } else
        {
            obj = ldp;
        }
        ldp = ((DateTimeFormatter) (obj));
        if (tp == null)
        {
            datetimeformatterbuilder = new DateTimeFormatterBuilder();
            if (lte == null)
            {
                obj = (new DateTimeFormatterBuilder()).appendLiteral('T').toFormatter();
            } else
            {
                obj = lte;
            }
            datetimeformatterbuilder = datetimeformatterbuilder.appendOptional(((DateTimeFormatter) (obj)).iParser).append(timeElementParser());
            if (ze == null)
            {
                obj = (new DateTimeFormatterBuilder()).appendTimeZoneOffset("Z", true, 2, 4).toFormatter();
            } else
            {
                obj = ze;
            }
            obj = datetimeformatterbuilder.appendOptional(((DateTimeFormatter) (obj)).iParser).toFormatter();
        } else
        {
            obj = tp;
        }
        tp = ((DateTimeFormatter) (obj));
        if (ltp == null)
        {
            datetimeformatterbuilder = new DateTimeFormatterBuilder();
            if (lte == null)
            {
                obj = (new DateTimeFormatterBuilder()).appendLiteral('T').toFormatter();
            } else
            {
                obj = lte;
            }
            obj = datetimeformatterbuilder.appendOptional(((DateTimeFormatter) (obj)).iParser).append(timeElementParser()).toFormatter().withZoneUTC();
        } else
        {
            obj = ltp;
        }
        ltp = ((DateTimeFormatter) (obj));
        if (dtp == null)
        {
            datetimeformatterbuilder = (new DateTimeFormatterBuilder()).appendLiteral('T').append(timeElementParser());
            if (ze == null)
            {
                obj = (new DateTimeFormatterBuilder()).appendTimeZoneOffset("Z", true, 2, 4).toFormatter();
            } else
            {
                obj = ze;
            }
            obj = datetimeformatterbuilder.appendOptional(((DateTimeFormatter) (obj)).iParser).toParser();
            obj = (new DateTimeFormatterBuilder()).append(null, new DateTimeParser[] {
                obj, dateOptionalTimeParser().iParser
            }).toFormatter();
        } else
        {
            obj = dtp;
        }
        dtp = ((DateTimeFormatter) (obj));
        if (ldotp == null)
        {
            obj = (new DateTimeFormatterBuilder()).appendLiteral('T').append(timeElementParser()).toParser();
            obj = (new DateTimeFormatterBuilder()).append(dateElementParser()).appendOptional(((DateTimeParser) (obj))).toFormatter().withZoneUTC();
        } else
        {
            obj = ldotp;
        }
        ldotp = ((DateTimeFormatter) (obj));
    }
}
