// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.predicates.impl;

import android.util.SparseArray;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Clock;
import com.google.android.libraries.internal.growth.growthkit.internal.predicates.PartialTriggeringConditionsPredicate;
import com.google.type.DayOfWeek;
import com.google.type.TimeOfDay;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

public final class TimeConstraintPredicate
    implements PartialTriggeringConditionsPredicate
{

    private static final SparseArray dayMap;
    private final Clock clock;

    public TimeConstraintPredicate(Clock clock1)
    {
        clock = clock1;
    }

    public final boolean apply(Object obj, Object obj1)
    {
        obj = ((com.google.identity.growth.proto.Promotion.TriggeringRule.TriggeringConditions)obj).timeConstraint_;
        if (((List) (obj)).isEmpty())
        {
            return true;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(clock.currentTimeMillis());
        obj1 = (DayOfWeek)dayMap.get(calendar.get(7));
        int i = calendar.get(11) * 60 + calendar.get(12);
        for (Iterator iterator = ((List) (obj)).iterator(); iterator.hasNext();)
        {
            com.google.identity.growth.proto.Promotion.TimeConstraintCondition timeconstraintcondition = (com.google.identity.growth.proto.Promotion.TimeConstraintCondition)iterator.next();
            int j;
            int k;
            int l;
            int i1;
            if (timeconstraintcondition.startTime_ == null)
            {
                obj = TimeOfDay.DEFAULT_INSTANCE;
            } else
            {
                obj = timeconstraintcondition.startTime_;
            }
            j = ((TimeOfDay) (obj)).hours_;
            k = ((TimeOfDay) (obj)).minutes_;
            if (timeconstraintcondition.endTime_ == null)
            {
                obj = TimeOfDay.DEFAULT_INSTANCE;
            } else
            {
                obj = timeconstraintcondition.endTime_;
            }
            l = ((TimeOfDay) (obj)).hours_;
            i1 = ((TimeOfDay) (obj)).minutes_;
            if ((new com.google.protobuf.Internal.ListAdapter(timeconstraintcondition.allowedDays_, com.google.identity.growth.proto.Promotion.TimeConstraintCondition.allowedDays_converter_)).contains(obj1) && i >= j * 60 + k && i <= i1 + l * 60)
            {
                return true;
            }
        }

        return false;
    }

    public final com.google.android.libraries.internal.growth.growthkit.internal.predicates.PartialTriggeringConditionsPredicate.TriggeringConditionType getTriggeringConditionType()
    {
        return com.google.android.libraries.internal.growth.growthkit.internal.predicates.PartialTriggeringConditionsPredicate.TriggeringConditionType.TIME_CONSTRAINT;
    }

    static 
    {
        SparseArray sparsearray = new SparseArray();
        dayMap = sparsearray;
        sparsearray.put(1, DayOfWeek.SUNDAY);
        dayMap.put(2, DayOfWeek.MONDAY);
        dayMap.put(3, DayOfWeek.TUESDAY);
        dayMap.put(4, DayOfWeek.WEDNESDAY);
        dayMap.put(5, DayOfWeek.THURSDAY);
        dayMap.put(6, DayOfWeek.FRIDAY);
        dayMap.put(7, DayOfWeek.SATURDAY);
    }
}
