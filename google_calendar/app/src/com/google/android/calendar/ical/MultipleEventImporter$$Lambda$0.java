// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.ical;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;

// Referenced classes of package com.google.android.calendar.ical:
//            ICalEventOperation, MultipleEventImporter

final class arg._cls1
    implements Function
{

    private final MultipleEventImporter arg$1;

    public final Object apply(Object obj)
    {
        Object obj1;
label0:
        {
label1:
            {
label2:
                {
                    boolean flag1 = false;
                    obj1 = arg$1;
                    obj = (ICalEventOperation)obj;
                    if (((ICalEventOperation) (obj)).events().isEmpty())
                    {
                        if (true)
                        {
                            return com.google.common.util.concurrent.fulFuture.NULL;
                        } else
                        {
                            return new com.google.common.util.concurrent.fulFuture(null);
                        }
                    }
                    if (((ICalEventOperation) (obj)).canceled())
                    {
                        obj = ((ICalEventOperation) (obj)).events();
                        obj1 = new <init>(((MultipleEventImporter) (obj1)));
                        if (obj == null)
                        {
                            throw new NullPointerException();
                        }
                        if (obj1 == null)
                        {
                            throw new NullPointerException();
                        } else
                        {
                            return new com.google.common.util.concurrent.t>(ImmutableList.copyOf(new com.google.common.collect.ure(((Iterable) (obj)), ((Function) (obj1)))), true);
                        }
                    }
                    if (((ICalEventOperation) (obj)).getImportType() == 0)
                    {
                        obj = ((ICalEventOperation) (obj)).events();
                        obj1 = ((MultipleEventImporter) (obj1)).eventClient;
                        obj1.getClass();
                        obj1 = new <init>(((com.google.android.calendar.api.event.EventClient) (obj1)));
                        if (obj == null)
                        {
                            throw new NullPointerException();
                        }
                        if (obj1 == null)
                        {
                            throw new NullPointerException();
                        } else
                        {
                            return new com.google.common.util.concurrent.t>(ImmutableList.copyOf(new com.google.common.collect.ure(((Iterable) (obj)), ((Function) (obj1)))), true);
                        }
                    }
                    boolean flag;
                    int i;
                    if (((ICalEventOperation) (obj)).getImportType() == 7)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag)
                    {
                        break label1;
                    }
                    i = ((ICalEventOperation) (obj)).getImportType();
                    if (i != 5)
                    {
                        flag = flag1;
                        if (i != 6)
                        {
                            break label2;
                        }
                    }
                    flag = true;
                }
                if (!flag)
                {
                    break label0;
                }
            }
            if (true)
            {
                return com.google.common.util.concurrent.fulFuture.NULL;
            } else
            {
                return new com.google.common.util.concurrent.fulFuture(null);
            }
        }
        obj = ((ICalEventOperation) (obj)).events();
        obj1 = new <init>(((MultipleEventImporter) (obj1)));
        if (obj == null)
        {
            throw new NullPointerException();
        }
        if (obj1 == null)
        {
            throw new NullPointerException();
        } else
        {
            return new com.google.common.util.concurrent.t>(ImmutableList.copyOf(new com.google.common.collect.ure(((Iterable) (obj)), ((Function) (obj1)))), true);
        }
    }

    sfulFuture(MultipleEventImporter multipleeventimporter)
    {
        arg$1 = multipleeventimporter;
    }
}
