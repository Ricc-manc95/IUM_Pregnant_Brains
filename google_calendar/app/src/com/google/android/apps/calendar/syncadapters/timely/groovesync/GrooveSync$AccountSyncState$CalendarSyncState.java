// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.syncadapters.timely.groovesync;

import com.google.api.client.json.GenericJson;

// Referenced classes of package com.google.android.apps.calendar.syncadapters.timely.groovesync:
//            GrooveSync

public static final class version extends GenericJson
{

    public Long lastSynced;
    public String nextSyncToken;
    public int version;

    public ()
    {
        version = 1;
        super.jsonFactory = GrooveSync.JSON_FACTORY;
    }
}
