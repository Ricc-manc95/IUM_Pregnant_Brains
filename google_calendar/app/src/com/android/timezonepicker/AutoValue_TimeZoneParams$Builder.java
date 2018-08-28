// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.timezonepicker;


// Referenced classes of package com.android.timezonepicker:
//            TimeZoneParams, AutoValue_TimeZoneParams

final class city extends city
{

    private String city;
    private String country;
    private String countryAbbreviation;
    private String displayName;
    private String id;
    private String nameAbbreviation;
    private Integer offset;

    public final TimeZoneParams build()
    {
        String s2 = "";
        if (displayName == null)
        {
            s2 = String.valueOf("").concat(" displayName");
        }
        String s = s2;
        if (id == null)
        {
            s = String.valueOf(s2).concat(" id");
        }
        s2 = s;
        if (offset == null)
        {
            s2 = String.valueOf(s).concat(" offset");
        }
        if (!s2.isEmpty())
        {
            String s1 = String.valueOf(s2);
            if (s1.length() != 0)
            {
                s1 = "Missing required properties:".concat(s1);
            } else
            {
                s1 = new String("Missing required properties:");
            }
            throw new IllegalStateException(s1);
        } else
        {
            return new AutoValue_TimeZoneParams(displayName, nameAbbreviation, country, countryAbbreviation, id, offset.intValue(), city);
        }
    }

    public final city setCity(String s)
    {
        city = s;
        return this;
    }

    public final city setCountry(String s)
    {
        country = s;
        return this;
    }

    public final country setCountryAbbreviation(String s)
    {
        countryAbbreviation = s;
        return this;
    }

    public final countryAbbreviation setDisplayName(String s)
    {
        if (s == null)
        {
            throw new NullPointerException("Null displayName");
        } else
        {
            displayName = s;
            return this;
        }
    }

    public final displayName setId(String s)
    {
        if (s == null)
        {
            throw new NullPointerException("Null id");
        } else
        {
            id = s;
            return this;
        }
    }

    public final id setNameAbbreviation(String s)
    {
        nameAbbreviation = s;
        return this;
    }

    public final nameAbbreviation setOffset(int i)
    {
        offset = Integer.valueOf(i);
        return this;
    }

    A()
    {
    }

    A(TimeZoneParams timezoneparams)
    {
        displayName = timezoneparams.getDisplayName();
        nameAbbreviation = timezoneparams.getNameAbbreviation();
        country = timezoneparams.getCountry();
        countryAbbreviation = timezoneparams.getCountryAbbreviation();
        id = timezoneparams.getId();
        offset = Integer.valueOf(timezoneparams.getOffset());
        city = timezoneparams.getCity();
    }
}
