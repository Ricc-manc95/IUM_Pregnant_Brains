// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.loggers.visualelements;

import com.google.android.libraries.social.analytics.visualelement.VisualElement;
import com.google.android.libraries.social.analytics.visualelement.VisualElementTag;
import java.util.Arrays;

final class EventVisualElement extends VisualElement
{

    public final String calendarId;
    public final String eventId;
    public final String eventReferenceId;

    EventVisualElement(VisualElementTag visualelementtag, String s, String s1, String s2)
    {
        super(visualelementtag);
        calendarId = s;
        eventId = s1;
        eventReferenceId = s2;
    }

    public final boolean equals(Object obj)
    {
        if (super.equals(obj))
        {
            obj = (EventVisualElement)obj;
            String s = calendarId;
            String s3 = ((EventVisualElement) (obj)).calendarId;
            boolean flag;
            if (s == s3 || s != null && s.equals(s3))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                String s1 = eventId;
                String s4 = ((EventVisualElement) (obj)).eventId;
                if (s1 == s4 || s1 != null && s1.equals(s4))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    String s2 = eventReferenceId;
                    obj = ((EventVisualElement) (obj)).eventReferenceId;
                    if (s2 == obj || s2 != null && s2.equals(obj))
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
            }
            return false;
        } else
        {
            return false;
        }
    }

    public final int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            eventId, Integer.valueOf(Arrays.hashCode(new Object[] {
                eventReferenceId, Integer.valueOf(Arrays.hashCode(new Object[] {
                    calendarId, Integer.valueOf(super.hashCode())
                }))
            }))
        });
    }
}
