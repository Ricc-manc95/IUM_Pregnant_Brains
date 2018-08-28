// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;

// Referenced classes of package com.google.api.services.calendar.model:
//            Image

public final class ButtonAction extends GenericJson
{

    public String buttonText;
    public Image image;
    public Boolean isClicked;
    public Boolean isRepeatable;
    public Boolean requiresConfirmation;
    public String style;

    public ButtonAction()
    {
    }

    public final volatile GenericJson clone()
    {
        return (ButtonAction)clone();
    }

    public final volatile GenericData clone()
    {
        return (ButtonAction)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (ButtonAction)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (ButtonAction)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (ButtonAction)super.set(s, obj);
    }
}
