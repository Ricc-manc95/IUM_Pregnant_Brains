// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.apps.tiktok.inject;

import android.content.Context;
import com.google.android.libraries.sting.processor.managers.ComponentManager;

public final class SingletonEntryPoints
{

    public static Object getEntryPoint(Context context, Class class1)
    {
        context = context.getApplicationContext();
        if (context instanceof ComponentManager)
        {
            context = ((Context) (((ComponentManager)context).stingComponent()));
            try
            {
                context = ((Context) (class1.cast(context)));
            }
            // Misplaced declaration of an exception variable
            catch (Context context)
            {
                throw new IllegalStateException("Failed to get an entry point. Did you mark your interface with @SingletonEntryPoint?", context);
            }
            return context;
        } else
        {
            throw new IllegalStateException("Given application context does not implement ComponentManager");
        }
    }
}
