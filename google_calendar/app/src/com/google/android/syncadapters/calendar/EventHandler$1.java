// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar;

import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.config.remote.RemoteFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeatureConfig;
import com.google.api.services.calendar.model.EventAttendee;
import java.util.Comparator;

// Referenced classes of package com.google.android.syncadapters.calendar:
//            EventHandler

final class onfig
    implements Comparator
{

    public final int compare(Object obj, Object obj1)
    {
label0:
        {
            obj = (EventAttendee)obj;
            obj1 = (EventAttendee)obj1;
            int j = EventHandler.compareObjects(((EventAttendee) (obj)).email, ((EventAttendee) (obj1)).email);
            int i = j;
            if (j == 0)
            {
                i = EventHandler.compareObjects(((EventAttendee) (obj)).responseStatus, ((EventAttendee) (obj1)).responseStatus);
            }
            j = i;
            if (i == 0)
            {
                j = EventHandler.compareObjects(((EventAttendee) (obj)).displayName, ((EventAttendee) (obj1)).displayName);
            }
            i = j;
            if (j == 0)
            {
                i = EventHandler.compareObjects(((EventAttendee) (obj)).optional, ((EventAttendee) (obj1)).optional);
            }
            j = i;
            if (i == 0)
            {
                j = i;
                if (RemoteFeatureConfig.THIRD_PARTY_RESOURCE_SUPPORT.enabled())
                {
                    j = EventHandler.compareObjects(((EventAttendee) (obj)).resource, ((EventAttendee) (obj1)).resource);
                }
            }
            i = j;
            if (j == 0)
            {
                FeatureConfig featureconfig = Features.instance;
                if (featureconfig == null)
                {
                    throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
                }
                ((FeatureConfig)featureconfig).response_comments();
                featureconfig = Features.instance;
                if (featureconfig == null)
                {
                    throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
                }
                ((FeatureConfig)featureconfig).propose_new_time();
                i = EventHandler.compareObjects(((EventAttendee) (obj)).comment, ((EventAttendee) (obj1)).comment);
            }
            j = i;
            if (i == 0)
            {
                FeatureConfig featureconfig1 = Features.instance;
                if (featureconfig1 == null)
                {
                    throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
                }
                ((FeatureConfig)featureconfig1).propose_new_time();
                if (!EventHandler.isEqual(((EventAttendee) (obj)).timeChangeProposal, ((EventAttendee) (obj1)).timeChangeProposal))
                {
                    break label0;
                }
                j = 0;
            }
            return j;
        }
        return 1;
    }

    onfig()
    {
    }
}
