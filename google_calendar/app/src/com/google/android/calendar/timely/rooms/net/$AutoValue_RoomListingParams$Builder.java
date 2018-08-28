// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.net;


// Referenced classes of package com.google.android.calendar.timely.rooms.net:
//            RoomListingParams, AutoValue_RoomListingParams

final class pageToken extends pageToken
{

    private Integer maxPageSize;
    private String pageToken;
    private Boolean preferHierarchy;
    private Boolean showUnavailable;

    public final RoomListingParams build()
    {
        String s = "";
        if (preferHierarchy == null)
        {
            s = String.valueOf("").concat(" preferHierarchy");
        }
        String s2 = s;
        if (showUnavailable == null)
        {
            s2 = String.valueOf(s).concat(" showUnavailable");
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
            return new AutoValue_RoomListingParams(preferHierarchy.booleanValue(), maxPageSize, showUnavailable.booleanValue(), pageToken);
        }
    }

    public final pageToken setPageToken(String s)
    {
        pageToken = s;
        return this;
    }

    public final pageToken setPreferHierarchy(boolean flag)
    {
        preferHierarchy = Boolean.valueOf(flag);
        return this;
    }

    public final preferHierarchy setShowUnavailable(boolean flag)
    {
        showUnavailable = Boolean.valueOf(flag);
        return this;
    }

    ()
    {
    }

    (RoomListingParams roomlistingparams)
    {
        preferHierarchy = Boolean.valueOf(roomlistingparams.isPreferHierarchy());
        maxPageSize = roomlistingparams.getMaxPageSize();
        showUnavailable = Boolean.valueOf(roomlistingparams.isShowUnavailable());
        pageToken = roomlistingparams.getPageToken();
    }
}
