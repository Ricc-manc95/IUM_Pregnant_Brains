// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.net;

import com.google.common.collect.ImmutableList;

// Referenced classes of package com.google.android.calendar.timely.rooms.net:
//            RoomRecommendationsParams

abstract class $AutoValue_RoomRecommendationsParams extends RoomRecommendationsParams
{

    private final int getMaxSuggestions;
    private final ImmutableList getRoomCriteria;
    private final boolean preferLocationBasedSuggestions;
    private final boolean showUnavailable;

    $AutoValue_RoomRecommendationsParams(int i, boolean flag, boolean flag1, ImmutableList immutablelist)
    {
        getMaxSuggestions = i;
        showUnavailable = flag;
        preferLocationBasedSuggestions = flag1;
        if (immutablelist == null)
        {
            throw new NullPointerException("Null getRoomCriteria");
        } else
        {
            getRoomCriteria = immutablelist;
            return;
        }
    }

    public boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof RoomRecommendationsParams)
            {
                if (getMaxSuggestions != ((RoomRecommendationsParams) (obj = (RoomRecommendationsParams)obj)).getMaxSuggestions() || showUnavailable != ((RoomRecommendationsParams) (obj)).showUnavailable() || preferLocationBasedSuggestions != ((RoomRecommendationsParams) (obj)).preferLocationBasedSuggestions() || !getRoomCriteria.equals(((RoomRecommendationsParams) (obj)).getRoomCriteria()))
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

    public final int getMaxSuggestions()
    {
        return getMaxSuggestions;
    }

    public final ImmutableList getRoomCriteria()
    {
        return getRoomCriteria;
    }

    public int hashCode()
    {
        char c1 = '\u04CF';
        int i = getMaxSuggestions;
        char c;
        if (showUnavailable)
        {
            c = '\u04CF';
        } else
        {
            c = '\u04D5';
        }
        if (!preferLocationBasedSuggestions)
        {
            c1 = '\u04D5';
        }
        return ((c ^ (i ^ 0xf4243) * 0xf4243) * 0xf4243 ^ c1) * 0xf4243 ^ getRoomCriteria.hashCode();
    }

    public final boolean preferLocationBasedSuggestions()
    {
        return preferLocationBasedSuggestions;
    }

    public final boolean showUnavailable()
    {
        return showUnavailable;
    }

    public String toString()
    {
        int i = getMaxSuggestions;
        boolean flag = showUnavailable;
        boolean flag1 = preferLocationBasedSuggestions;
        String s = String.valueOf(getRoomCriteria);
        return (new StringBuilder(String.valueOf(s).length() + 135)).append("RoomRecommendationsParams{getMaxSuggestions=").append(i).append(", showUnavailable=").append(flag).append(", preferLocationBasedSuggestions=").append(flag1).append(", getRoomCriteria=").append(s).append("}").toString();
    }
}
