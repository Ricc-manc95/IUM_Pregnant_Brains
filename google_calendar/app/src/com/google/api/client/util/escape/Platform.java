// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.util.escape;


final class Platform
{

    public static final ThreadLocal DEST_TL = new _cls1();


    private class _cls1 extends ThreadLocal
    {

        protected final Object initialValue()
        {
            return new char[1024];
        }

        _cls1()
        {
        }
    }

}
