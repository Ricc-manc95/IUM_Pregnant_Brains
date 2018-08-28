// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.stats;


final class AutoValue_Measure_MeasureLong extends Measure.MeasureLong
{

    private final String description;
    private final String name;
    private final String unit;

    AutoValue_Measure_MeasureLong(String s, String s1, String s2)
    {
        if (s == null)
        {
            throw new NullPointerException("Null name");
        }
        name = s;
        if (s1 == null)
        {
            throw new NullPointerException("Null description");
        }
        description = s1;
        if (s2 == null)
        {
            throw new NullPointerException("Null unit");
        } else
        {
            unit = s2;
            return;
        }
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof Measure.MeasureLong)
            {
                if (!name.equals(((Measure.MeasureLong) (obj = (Measure.MeasureLong)obj)).getName()) || !description.equals(((Measure.MeasureLong) (obj)).getDescription()) || !unit.equals(((Measure.MeasureLong) (obj)).getUnit()))
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

    public final String getDescription()
    {
        return description;
    }

    public final String getName()
    {
        return name;
    }

    public final String getUnit()
    {
        return unit;
    }

    public final int hashCode()
    {
        return ((name.hashCode() ^ 0xf4243) * 0xf4243 ^ description.hashCode()) * 0xf4243 ^ unit.hashCode();
    }

    public final String toString()
    {
        String s = name;
        String s1 = description;
        String s2 = unit;
        return (new StringBuilder(String.valueOf(s).length() + 39 + String.valueOf(s1).length() + String.valueOf(s2).length())).append("MeasureLong{name=").append(s).append(", description=").append(s1).append(", unit=").append(s2).append("}").toString();
    }
}
