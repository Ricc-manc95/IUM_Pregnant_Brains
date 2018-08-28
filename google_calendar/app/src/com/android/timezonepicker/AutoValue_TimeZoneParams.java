// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.timezonepicker;


// Referenced classes of package com.android.timezonepicker:
//            TimeZoneParams

final class AutoValue_TimeZoneParams extends TimeZoneParams
{

    private final String city;
    private final String country;
    private final String countryAbbreviation;
    private final String displayName;
    private final String id;
    private final String nameAbbreviation;
    private final int offset;

    AutoValue_TimeZoneParams(String s, String s1, String s2, String s3, String s4, int i, String s5)
    {
        displayName = s;
        nameAbbreviation = s1;
        country = s2;
        countryAbbreviation = s3;
        id = s4;
        offset = i;
        city = s5;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this) goto _L2; else goto _L1
_L1:
        return true;
_L2:
label0:
        {
            if (!(obj instanceof TimeZoneParams))
            {
                break MISSING_BLOCK_LABEL_183;
            }
            obj = (TimeZoneParams)obj;
            if (displayName.equals(((TimeZoneParams) (obj)).getDisplayName()) && (nameAbbreviation != null ? nameAbbreviation.equals(((TimeZoneParams) (obj)).getNameAbbreviation()) : ((TimeZoneParams) (obj)).getNameAbbreviation() == null) && (country != null ? country.equals(((TimeZoneParams) (obj)).getCountry()) : ((TimeZoneParams) (obj)).getCountry() == null) && (countryAbbreviation != null ? countryAbbreviation.equals(((TimeZoneParams) (obj)).getCountryAbbreviation()) : ((TimeZoneParams) (obj)).getCountryAbbreviation() == null) && (id.equals(((TimeZoneParams) (obj)).getId()) && offset == ((TimeZoneParams) (obj)).getOffset()))
            {
                break label0;
            } else
            {
                break; /* Loop/switch isn't completed */
            }
        }
        if (city != null) goto _L4; else goto _L3
_L3:
        if (((TimeZoneParams) (obj)).getCity() == null) goto _L1; else goto _L5
_L5:
        return false;
_L4:
        if (!city.equals(((TimeZoneParams) (obj)).getCity())) goto _L5; else goto _L6
_L6:
        return true;
        return false;
    }

    public final String getCity()
    {
        return city;
    }

    public final String getCountry()
    {
        return country;
    }

    public final String getCountryAbbreviation()
    {
        return countryAbbreviation;
    }

    public final String getDisplayName()
    {
        return displayName;
    }

    public final String getId()
    {
        return id;
    }

    public final String getNameAbbreviation()
    {
        return nameAbbreviation;
    }

    final int getOffset()
    {
        return offset;
    }

    public final int hashCode()
    {
        int l = 0;
        int i1 = displayName.hashCode();
        int i;
        int j;
        int k;
        int j1;
        int k1;
        if (nameAbbreviation == null)
        {
            i = 0;
        } else
        {
            i = nameAbbreviation.hashCode();
        }
        if (country == null)
        {
            j = 0;
        } else
        {
            j = country.hashCode();
        }
        if (countryAbbreviation == null)
        {
            k = 0;
        } else
        {
            k = countryAbbreviation.hashCode();
        }
        j1 = id.hashCode();
        k1 = offset;
        if (city != null)
        {
            l = city.hashCode();
        }
        return (((k ^ (j ^ (i ^ (i1 ^ 0xf4243) * 0xf4243) * 0xf4243) * 0xf4243) * 0xf4243 ^ j1) * 0xf4243 ^ k1) * 0xf4243 ^ l;
    }

    public final TimeZoneParams.Builder toBuilder()
    {
        return new Builder(this);
    }

    public final String toString()
    {
        String s = displayName;
        String s1 = nameAbbreviation;
        String s2 = country;
        String s3 = countryAbbreviation;
        String s4 = id;
        int i = offset;
        String s5 = city;
        return (new StringBuilder(String.valueOf(s).length() + 111 + String.valueOf(s1).length() + String.valueOf(s2).length() + String.valueOf(s3).length() + String.valueOf(s4).length() + String.valueOf(s5).length())).append("TimeZoneParams{displayName=").append(s).append(", nameAbbreviation=").append(s1).append(", country=").append(s2).append(", countryAbbreviation=").append(s3).append(", id=").append(s4).append(", offset=").append(i).append(", city=").append(s5).append("}").toString();
    }

    private class Builder extends TimeZoneParams.Builder
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

        public final TimeZoneParams.Builder setCity(String s)
        {
            city = s;
            return this;
        }

        public final TimeZoneParams.Builder setCountry(String s)
        {
            country = s;
            return this;
        }

        public final TimeZoneParams.Builder setCountryAbbreviation(String s)
        {
            countryAbbreviation = s;
            return this;
        }

        public final TimeZoneParams.Builder setDisplayName(String s)
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

        public final TimeZoneParams.Builder setId(String s)
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

        public final TimeZoneParams.Builder setNameAbbreviation(String s)
        {
            nameAbbreviation = s;
            return this;
        }

        public final TimeZoneParams.Builder setOffset(int i)
        {
            offset = Integer.valueOf(i);
            return this;
        }

        Builder()
        {
        }

        Builder(TimeZoneParams timezoneparams)
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

}
