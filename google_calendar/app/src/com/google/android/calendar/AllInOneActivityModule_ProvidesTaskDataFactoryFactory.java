// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import com.google.android.calendar.task.TaskDataFactory;
import dagger.internal.Factory;

public final class AllInOneActivityModule_ProvidesTaskDataFactoryFactory
    implements Factory
{

    public static final AllInOneActivityModule_ProvidesTaskDataFactoryFactory INSTANCE = new AllInOneActivityModule_ProvidesTaskDataFactoryFactory();

    public AllInOneActivityModule_ProvidesTaskDataFactoryFactory()
    {
    }

    public final Object get()
    {
        if (TaskDataFactory.instance == null)
        {
            TaskDataFactory.instance = new TaskDataFactory();
        }
        TaskDataFactory taskdatafactory = TaskDataFactory.instance;
        if (taskdatafactory == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (TaskDataFactory)taskdatafactory;
        }
    }

}
