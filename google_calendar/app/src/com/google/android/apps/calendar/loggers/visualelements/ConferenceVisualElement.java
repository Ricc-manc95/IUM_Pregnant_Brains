// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.loggers.visualelements;

import com.google.android.calendar.event.conference.LocalPhoneSource;
import com.google.android.libraries.social.analytics.visualelement.VisualElement;
import com.google.android.libraries.social.analytics.visualelement.VisualElementTag;

public class ConferenceVisualElement extends VisualElement
{

    public final LocalPhoneSource localPhoneSource;

    ConferenceVisualElement(VisualElementTag visualelementtag, LocalPhoneSource localphonesource)
    {
        super(visualelementtag);
        localPhoneSource = localphonesource;
    }

    public boolean equals(Object obj)
    {
        if (!(obj instanceof ConferenceVisualElement))
        {
            return false;
        }
        Object obj1 = (ConferenceVisualElement)obj;
        if (super.equals(obj))
        {
            obj = localPhoneSource;
            obj1 = ((ConferenceVisualElement) (obj1)).localPhoneSource;
            boolean flag;
            if (obj == obj1 || obj != null && obj.equals(obj1))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                return true;
            }
        }
        return false;
    }
}
