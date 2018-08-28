// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.interaction;


final class AutoValue_InteractionTracker_Interaction extends InteractionTracker.Interaction
{

    private final Object controller;
    private final Object target;

    AutoValue_InteractionTracker_Interaction(Object obj, Object obj1)
    {
        controller = obj;
        target = obj1;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this) goto _L2; else goto _L1
_L1:
        return true;
_L2:
label0:
        {
            if (!(obj instanceof InteractionTracker.Interaction))
            {
                break MISSING_BLOCK_LABEL_82;
            }
            obj = (InteractionTracker.Interaction)obj;
            if (controller != null ? controller.equals(((InteractionTracker.Interaction) (obj)).getController()) : ((InteractionTracker.Interaction) (obj)).getController() == null)
            {
                break label0;
            } else
            {
                break; /* Loop/switch isn't completed */
            }
        }
        if (target != null) goto _L4; else goto _L3
_L3:
        if (((InteractionTracker.Interaction) (obj)).getTarget() == null) goto _L1; else goto _L5
_L5:
        return false;
_L4:
        if (!target.equals(((InteractionTracker.Interaction) (obj)).getTarget())) goto _L5; else goto _L6
_L6:
        return true;
        return false;
    }

    final Object getController()
    {
        return controller;
    }

    final Object getTarget()
    {
        return target;
    }

    public final int hashCode()
    {
        int j = 0;
        int i;
        if (controller == null)
        {
            i = 0;
        } else
        {
            i = controller.hashCode();
        }
        if (target != null)
        {
            j = target.hashCode();
        }
        return (i ^ 0xf4243) * 0xf4243 ^ j;
    }

    public final String toString()
    {
        String s = String.valueOf(controller);
        String s1 = String.valueOf(target);
        return (new StringBuilder(String.valueOf(s).length() + 33 + String.valueOf(s1).length())).append("Interaction{controller=").append(s).append(", target=").append(s1).append("}").toString();
    }
}
