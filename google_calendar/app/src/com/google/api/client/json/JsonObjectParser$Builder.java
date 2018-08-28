// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.json;

import java.util.Collection;
import java.util.HashSet;

// Referenced classes of package com.google.api.client.json:
//            JsonFactory

public final class jsonFactory
{

    public final JsonFactory jsonFactory;
    public Collection wrapperKeys;

    public _cls9(JsonFactory jsonfactory)
    {
        wrapperKeys = new HashSet();
        if (jsonfactory == null)
        {
            throw new NullPointerException();
        } else
        {
            jsonFactory = (JsonFactory)jsonfactory;
            return;
        }
    }
}
