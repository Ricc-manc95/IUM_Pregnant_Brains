// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.image;

import com.google.common.base.Function;

// Referenced classes of package com.google.android.calendar.event.image:
//            PlacePageOrMapUrl

final class arg._cls5
    implements Function
{

    private final String arg$1;
    private final String arg$2;
    private final String arg$3;
    private final int arg$4;
    private final int arg$5;

    public final Object apply(Object obj)
    {
        return PlacePageOrMapUrl.lambda$getPlacePageOrMapsUrl$0$PlacePageOrMapUrl(arg$1, arg$2, arg$3, arg$4, arg$5, (String)obj);
    }

    I(String s, String s1, String s2, int i, int j)
    {
        arg$1 = s;
        arg$2 = s1;
        arg$3 = s2;
        arg$4 = i;
        arg$5 = j;
    }
}
