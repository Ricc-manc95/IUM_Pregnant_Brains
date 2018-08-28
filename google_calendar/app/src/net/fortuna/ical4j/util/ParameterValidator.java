// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.util;

import java.util.List;
import net.fortuna.ical4j.model.Parameter;
import net.fortuna.ical4j.model.ParameterList;
import net.fortuna.ical4j.model.ValidationException;

public final class ParameterValidator
{

    public static ParameterValidator instance = new ParameterValidator();

    private ParameterValidator()
    {
    }

    public static void assertNone(String s, ParameterList parameterlist)
        throws ValidationException
    {
        if (parameterlist.getParameter(s) != null)
        {
            throw new ValidationException("Parameter [{0}] is not applicable", new Object[] {
                s
            });
        } else
        {
            return;
        }
    }

    public static void assertNullOrEqual(Parameter parameter, ParameterList parameterlist)
        throws ValidationException
    {
        parameterlist = parameterlist.getParameter(parameter.name);
        if (parameterlist != null && !parameter.equals(parameterlist))
        {
            throw new ValidationException("Parameter [{0}] is invalid", new Object[] {
                parameterlist
            });
        } else
        {
            return;
        }
    }

    public static void assertOne(String s, ParameterList parameterlist)
        throws ValidationException
    {
        if (parameterlist.getParameters(s).parameters.size() != 1)
        {
            throw new ValidationException("Parameter [{0}] must be specified once", new Object[] {
                s
            });
        } else
        {
            return;
        }
    }

    public static void assertOneOrLess(String s, ParameterList parameterlist)
        throws ValidationException
    {
        if (parameterlist.getParameters(s).parameters.size() > 1)
        {
            throw new ValidationException("Parameter [{0}] must only be specified once", new Object[] {
                s
            });
        } else
        {
            return;
        }
    }

}
