// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.dynamite;

import dalvik.system.PathClassLoader;

final class  extends PathClassLoader
{

    protected final Class loadClass(String s, boolean flag)
        throws ClassNotFoundException
    {
        if (s.startsWith("java.") || s.startsWith("android."))
        {
            break MISSING_BLOCK_LABEL_27;
        }
        Class class1 = findClass(s);
        return class1;
        ClassNotFoundException classnotfoundexception;
        classnotfoundexception;
        return super.loadClass(s, flag);
    }

    (String s, ClassLoader classloader)
    {
        super(s, classloader);
    }
}
