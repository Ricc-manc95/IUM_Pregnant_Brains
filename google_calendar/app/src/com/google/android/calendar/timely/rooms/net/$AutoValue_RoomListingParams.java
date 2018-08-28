// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.net;


// Referenced classes of package com.google.android.calendar.timely.rooms.net:
//            RoomListingParams

abstract class $AutoValue_RoomListingParams extends RoomListingParams
{

    private final Integer maxPageSize;
    private final String pageToken;
    private final boolean preferHierarchy;
    private final boolean showUnavailable;

    $AutoValue_RoomListingParams(boolean flag, Integer integer, boolean flag1, String s)
    {
        preferHierarchy = flag;
        maxPageSize = integer;
        showUnavailable = flag1;
        pageToken = s;
    }

    public boolean equals(Object obj)
    {
        if (obj != this) goto _L2; else goto _L1
_L1:
        return true;
_L2:
label0:
        {
            if (!(obj instanceof RoomListingParams))
            {
                break MISSING_BLOCK_LABEL_104;
            }
            obj = (RoomListingParams)obj;
            if (preferHierarchy == ((RoomListingParams) (obj)).isPreferHierarchy() && (maxPageSize != null ? maxPageSize.equals(((RoomListingParams) (obj)).getMaxPageSize()) : ((RoomListingParams) (obj)).getMaxPageSize() == null) && showUnavailable == ((RoomListingParams) (obj)).isShowUnavailable())
            {
                break label0;
            } else
            {
                break; /* Loop/switch isn't completed */
            }
        }
        if (pageToken != null) goto _L4; else goto _L3
_L3:
        if (((RoomListingParams) (obj)).getPageToken() == null) goto _L1; else goto _L5
_L5:
        return false;
_L4:
        if (!pageToken.equals(((RoomListingParams) (obj)).getPageToken())) goto _L5; else goto _L6
_L6:
        return true;
        return false;
    }

    public final Integer getMaxPageSize()
    {
        return maxPageSize;
    }

    public final String getPageToken()
    {
        return pageToken;
    }

    public int hashCode()
    {
        char c1 = '\u04CF';
        int j = 0;
        char c;
        int i;
        if (preferHierarchy)
        {
            c = '\u04CF';
        } else
        {
            c = '\u04D5';
        }
        if (maxPageSize == null)
        {
            i = 0;
        } else
        {
            i = maxPageSize.hashCode();
        }
        if (!showUnavailable)
        {
            c1 = '\u04D5';
        }
        if (pageToken != null)
        {
            j = pageToken.hashCode();
        }
        return ((i ^ (c ^ 0xf4243) * 0xf4243) * 0xf4243 ^ c1) * 0xf4243 ^ j;
    }

    public final boolean isPreferHierarchy()
    {
        return preferHierarchy;
    }

    public final boolean isShowUnavailable()
    {
        return showUnavailable;
    }

    public final RoomListingParams.Builder toBuilder()
    {
        class Builder extends RoomListingParams.Builder
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

            public final RoomListingParams.Builder setPageToken(String s)
            {
                pageToken = s;
                return this;
            }

            public final RoomListingParams.Builder setPreferHierarchy(boolean flag)
            {
                preferHierarchy = Boolean.valueOf(flag);
                return this;
            }

            public final RoomListingParams.Builder setShowUnavailable(boolean flag)
            {
                showUnavailable = Boolean.valueOf(flag);
                return this;
            }

            Builder()
            {
            }

            Builder(RoomListingParams roomlistingparams)
            {
                preferHierarchy = Boolean.valueOf(roomlistingparams.isPreferHierarchy());
                maxPageSize = roomlistingparams.getMaxPageSize();
                showUnavailable = Boolean.valueOf(roomlistingparams.isShowUnavailable());
                pageToken = roomlistingparams.getPageToken();
            }
        }

        return new Builder(this);
    }

    public String toString()
    {
        boolean flag = preferHierarchy;
        String s = String.valueOf(maxPageSize);
        boolean flag1 = showUnavailable;
        String s1 = pageToken;
        return (new StringBuilder(String.valueOf(s).length() + 89 + String.valueOf(s1).length())).append("RoomListingParams{preferHierarchy=").append(flag).append(", maxPageSize=").append(s).append(", showUnavailable=").append(flag1).append(", pageToken=").append(s1).append("}").toString();
    }
}
