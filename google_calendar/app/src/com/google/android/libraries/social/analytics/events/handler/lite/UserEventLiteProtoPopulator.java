// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.social.analytics.events.handler.lite;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.android.libraries.social.analytics.events.handler.lite:
//            VisualElementLiteMetadataHandler

public final class UserEventLiteProtoPopulator
{

    public final Map handlers = new HashMap();

    public UserEventLiteProtoPopulator(Set set)
    {
        if (set != null)
        {
            VisualElementLiteMetadataHandler visualelementlitemetadatahandler;
            for (set = set.iterator(); set.hasNext(); handlers.put(visualelementlitemetadatahandler.getHandledVisualElementClass(), visualelementlitemetadatahandler))
            {
                visualelementlitemetadatahandler = (VisualElementLiteMetadataHandler)set.next();
            }

        }
    }
}
