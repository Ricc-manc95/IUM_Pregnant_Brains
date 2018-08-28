// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.chrono;

import java.text.DateFormatSymbols;
import java.util.Locale;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.joda.time.DateTimeUtils;

final class GJLocaleSymbols
{

    private static ConcurrentMap cCache = new ConcurrentHashMap();
    public final String iDaysOfWeek[];
    public final String iEras[];
    public final String iHalfday[];
    public final int iMaxDayOfWeekLength;
    public final int iMaxEraLength;
    public final int iMaxHalfdayLength;
    public final int iMaxMonthLength;
    public final String iMonths[];
    public final TreeMap iParseDaysOfWeek;
    public final TreeMap iParseEras;
    public final TreeMap iParseMonths;
    public final String iShortDaysOfWeek[];
    public final String iShortMonths[];

    private GJLocaleSymbols(Locale locale)
    {
        DateFormatSymbols dateformatsymbols = DateTimeUtils.getDateFormatSymbols(locale);
        iEras = dateformatsymbols.getEras();
        iDaysOfWeek = realignDaysOfWeek(dateformatsymbols.getWeekdays());
        iShortDaysOfWeek = realignDaysOfWeek(dateformatsymbols.getShortWeekdays());
        String as[] = dateformatsymbols.getMonths();
        String as1[] = new String[13];
        for (int i = 1; i < 13; i++)
        {
            as1[i] = as[i - 1];
        }

        iMonths = as1;
        as = dateformatsymbols.getShortMonths();
        as1 = new String[13];
        for (int j = 1; j < 13; j++)
        {
            as1[j] = as[j - 1];
        }

        iShortMonths = as1;
        iHalfday = dateformatsymbols.getAmPmStrings();
        Integer ainteger[] = new Integer[13];
        for (int k = 0; k < 13; k++)
        {
            ainteger[k] = Integer.valueOf(k);
        }

        iParseEras = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        addSymbols(iParseEras, iEras, ainteger);
        if ("en".equals(locale.getLanguage()))
        {
            iParseEras.put("BCE", ainteger[0]);
            iParseEras.put("CE", ainteger[1]);
        }
        iParseDaysOfWeek = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        addSymbols(iParseDaysOfWeek, iDaysOfWeek, ainteger);
        addSymbols(iParseDaysOfWeek, iShortDaysOfWeek, ainteger);
        addNumerals(iParseDaysOfWeek, 1, 7, ainteger);
        iParseMonths = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        addSymbols(iParseMonths, iMonths, ainteger);
        addSymbols(iParseMonths, iShortMonths, ainteger);
        addNumerals(iParseMonths, 1, 12, ainteger);
        iMaxEraLength = maxLength(iEras);
        iMaxDayOfWeekLength = maxLength(iDaysOfWeek);
        maxLength(iShortDaysOfWeek);
        iMaxMonthLength = maxLength(iMonths);
        maxLength(iShortMonths);
        iMaxHalfdayLength = maxLength(iHalfday);
    }

    private static void addNumerals(TreeMap treemap, int i, int j, Integer ainteger[])
    {
        for (i = 1; i <= j; i++)
        {
            treemap.put(String.valueOf(i).intern(), ainteger[i]);
        }

    }

    private static void addSymbols(TreeMap treemap, String as[], Integer ainteger[])
    {
        int i = as.length;
        do
        {
            int j = i - 1;
            if (j < 0)
            {
                break;
            }
            String s = as[j];
            i = j;
            if (s != null)
            {
                treemap.put(s, ainteger[j]);
                i = j;
            }
        } while (true);
    }

    static GJLocaleSymbols forLocale(Locale locale)
    {
        GJLocaleSymbols gjlocalesymbols;
label0:
        {
            Locale locale1 = locale;
            if (locale == null)
            {
                locale1 = Locale.getDefault();
            }
            gjlocalesymbols = (GJLocaleSymbols)cCache.get(locale1);
            locale = gjlocalesymbols;
            if (gjlocalesymbols == null)
            {
                gjlocalesymbols = new GJLocaleSymbols(locale1);
                locale = (GJLocaleSymbols)cCache.putIfAbsent(locale1, gjlocalesymbols);
                if (locale == null)
                {
                    break label0;
                }
            }
            return locale;
        }
        return gjlocalesymbols;
    }

    private static int maxLength(String as[])
    {
        int i = 0;
        int j = as.length;
        do
        {
            j--;
            if (j < 0)
            {
                break;
            }
            String s = as[j];
            if (s != null)
            {
                int k = s.length();
                if (k > i)
                {
                    i = k;
                }
            }
        } while (true);
        return i;
    }

    private static String[] realignDaysOfWeek(String as[])
    {
        String as1[] = new String[8];
        int i = 1;
        while (i < 8) 
        {
            int j;
            if (i < 7)
            {
                j = i + 1;
            } else
            {
                j = 1;
            }
            as1[i] = as[j];
            i++;
        }
        return as1;
    }

}
