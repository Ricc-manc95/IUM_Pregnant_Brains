// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.timezonepicker;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.NaturalOrdering;
import com.google.common.collect.Ordering;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public abstract class TimeZoneParams
    implements Comparable
{

    public static final TimeZoneParams STUB = (new AutoValue_TimeZoneParams.Builder()).setOffset(0).setDisplayName("").setId("").build();

    public TimeZoneParams()
    {
    }

    public static Builder builder(TimeZone timezone, Date date, String s, String s1)
    {
        String s2 = s1;
        if (s1 == null)
        {
            s2 = timezone.getDisplayName(timezone.inDaylightTime(date), 1, Locale.getDefault());
        }
        return (new AutoValue_TimeZoneParams.Builder()).setOffset(0).setDisplayName(s2).setNameAbbreviation(getAbbreviation(s2)).setCountry(s).setCountryAbbreviation(getAbbreviation(s)).setId(timezone.getID()).setOffset(timezone.getOffset(date.getTime()));
    }

    private static String getAbbreviation(String s)
    {
        if (s != null) goto _L2; else goto _L1
_L1:
        String s2 = null;
_L4:
        return s2;
_L2:
        String s1 = "";
        int i = 0;
        boolean flag1 = true;
        do
        {
            s2 = s1;
            if (i >= s.length())
            {
                continue;
            }
            boolean flag;
            if (!Character.isLetter(s.charAt(i)))
            {
                flag = true;
                s2 = s1;
            } else
            {
                flag = flag1;
                s2 = s1;
                if (flag1)
                {
                    s1 = String.valueOf(s1);
                    char c = s.charAt(i);
                    s2 = (new StringBuilder(String.valueOf(s1).length() + 1)).append(s1).append(c).toString();
                    flag = false;
                }
            }
            i++;
            flag1 = flag;
            s1 = s2;
        } while (true);
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final int compareTo(TimeZoneParams timezoneparams)
    {
        return ComparisonChain.ACTIVE.compare(getOffset(), timezoneparams.getOffset()).compare(getCountry(), timezoneparams.getCountry(), NaturalOrdering.INSTANCE.nullsFirst()).compare(getDisplayName(), timezoneparams.getDisplayName()).result();
    }

    public volatile int compareTo(Object obj)
    {
        return compareTo((TimeZoneParams)obj);
    }

    public abstract String getCity();

    public abstract String getCountry();

    public abstract String getCountryAbbreviation();

    public abstract String getDisplayName();

    public abstract String getId();

    public abstract String getNameAbbreviation();

    public abstract int getOffset();

    public abstract Builder toBuilder();


    private class Builder
    {

        public abstract TimeZoneParams build();

        public abstract Builder setCity(String s);

        public abstract Builder setCountry(String s);

        public abstract Builder setCountryAbbreviation(String s);

        public abstract Builder setDisplayName(String s);

        public abstract Builder setId(String s);

        public abstract Builder setNameAbbreviation(String s);

        public abstract Builder setOffset(int i);

        public Builder()
        {
        }
    }

}
