// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.social.analytics.events;

import android.content.Context;
import com.google.android.libraries.social.analytics.AnalyticsEvent;
import com.google.android.libraries.social.analytics.AuthenticationProvider;
import com.google.android.libraries.social.analytics.visualelement.VisualElementPath;
import java.util.Locale;

public final class UserEvent
    implements AnalyticsEvent
{

    public String accountName;
    public final int userAction;
    public final VisualElementPath visualElementPath;

    public UserEvent(int i, VisualElementPath visualelementpath)
    {
        userAction = i;
        visualElementPath = visualelementpath;
    }

    public final boolean equals(Object obj)
    {
        if (obj instanceof UserEvent)
        {
            Object obj1 = (UserEvent)obj;
            if (userAction == ((UserEvent) (obj1)).userAction)
            {
                if (true)
                {
                    boolean flag;
                    if (true)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag && visualElementPath.equals(((UserEvent) (obj1)).visualElementPath))
                    {
                        obj = accountName;
                        obj1 = ((UserEvent) (obj1)).accountName;
                        boolean flag1;
                        if (obj == null)
                        {
                            if (obj1 == null)
                            {
                                flag1 = true;
                            } else
                            {
                                flag1 = false;
                            }
                        } else
                        {
                            flag1 = obj.equals(obj1);
                        }
                        if (flag1)
                        {
                            return true;
                        }
                    }
                } else
                {
                    throw new NullPointerException();
                }
            }
            return false;
        } else
        {
            return false;
        }
    }

    public final String getAccountName(Context context, AuthenticationProvider authenticationprovider)
    {
        if (accountName != null)
        {
            return accountName;
        } else
        {
            return authenticationprovider.getAccountName(context);
        }
    }

    public final int hashCode()
    {
        int i = 0;
        int j = userAction;
        String s = accountName;
        int k = visualElementPath.hashCode();
        if (true)
        {
            if (s != null)
            {
                i = s.hashCode();
            }
            return (i + (0 + k * 31) * 31) * 31 + j;
        } else
        {
            throw new NullPointerException();
        }
    }

    public final String toString()
    {
        return String.format(Locale.US, "UserEvent action: %d%s on: %s ", new Object[] {
            Integer.valueOf(userAction), "", visualElementPath.toString()
        });
    }
}
