// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.arch.lifecycle;

import java.lang.reflect.Method;

final class mMethod
{

    public final int mCallType;
    public final Method mMethod;

    public final boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (obj == null || getClass() != obj.getClass())
            {
                return false;
            }
            obj = (mMethod)obj;
            if (mCallType != ((mCallType) (obj)).mCallType || !mMethod.getName().equals(((mMethod) (obj)).mMethod.getName()))
            {
                return false;
            }
        }
        return true;
    }

    public final int hashCode()
    {
        return mCallType * 31 + mMethod.getName().hashCode();
    }

    (int i, Method method)
    {
        mCallType = i;
        mMethod = method;
        mMethod.setAccessible(true);
    }
}
