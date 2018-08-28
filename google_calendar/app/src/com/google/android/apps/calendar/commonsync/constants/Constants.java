// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.commonsync.constants;

import com.google.common.collect.ImmutableMap;

// Referenced classes of package com.google.android.apps.calendar.commonsync.constants:
//            ExtendedPropertiesConstants

public interface Constants
    extends ExtendedPropertiesConstants
{

    public static final ImmutableMap ERROR_MAP = (new com.google.common.collect.ImmutableMap.Builder()).put(Integer.valueOf(400), "400 Bad Request").put(Integer.valueOf(304), "304 Not Modified").put(Integer.valueOf(401), "401 Unauthorized").put(Integer.valueOf(403), "403 Forbidden").put(Integer.valueOf(404), "404 Not Found").put(Integer.valueOf(409), "409 Conflict").put(Integer.valueOf(410), "410 Gone").put(Integer.valueOf(412), "412 Precondition Failed").put(Integer.valueOf(414), "414 Deleted From Server").put(Integer.valueOf(500), "500 Internal Server Error").put(Integer.valueOf(503), "503 Service Unavailable").build();

}
