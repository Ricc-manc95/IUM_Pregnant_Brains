// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.conference;


abstract class $AutoValue_ConferenceSolution_Key_AddOnId extends ConferenceSolution.Key.AddOnId
{

    private final String deploymentId;
    private final String solutionId;

    $AutoValue_ConferenceSolution_Key_AddOnId(String s, String s1)
    {
        if (s == null)
        {
            throw new NullPointerException("Null deploymentId");
        }
        deploymentId = s;
        if (s1 == null)
        {
            throw new NullPointerException("Null solutionId");
        } else
        {
            solutionId = s1;
            return;
        }
    }

    public boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof ConferenceSolution.Key.AddOnId)
            {
                if (!deploymentId.equals(((ConferenceSolution.Key.AddOnId) (obj = (ConferenceSolution.Key.AddOnId)obj)).getDeploymentId()) || !solutionId.equals(((ConferenceSolution.Key.AddOnId) (obj)).getSolutionId()))
                {
                    return false;
                }
            } else
            {
                return false;
            }
        }
        return true;
    }

    public final String getDeploymentId()
    {
        return deploymentId;
    }

    public final String getSolutionId()
    {
        return solutionId;
    }

    public int hashCode()
    {
        return (deploymentId.hashCode() ^ 0xf4243) * 0xf4243 ^ solutionId.hashCode();
    }

    public String toString()
    {
        String s = deploymentId;
        String s1 = solutionId;
        return (new StringBuilder(String.valueOf(s).length() + 35 + String.valueOf(s1).length())).append("AddOnId{deploymentId=").append(s).append(", solutionId=").append(s1).append("}").toString();
    }
}
