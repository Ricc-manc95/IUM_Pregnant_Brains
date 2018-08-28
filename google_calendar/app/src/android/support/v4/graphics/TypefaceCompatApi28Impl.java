// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.graphics;

import android.graphics.Typeface;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// Referenced classes of package android.support.v4.graphics:
//            TypefaceCompatApi26Impl

public final class TypefaceCompatApi28Impl extends TypefaceCompatApi26Impl
{

    public TypefaceCompatApi28Impl()
    {
    }

    protected final Typeface createFromFamiliesWithDefault(Object obj)
    {
        Object obj1 = Array.newInstance(mFontFamily, 1);
        Array.set(obj1, 0, obj);
        obj = (Typeface)mCreateFromFamiliesWithDefault.invoke(null, new Object[] {
            obj1, "sans-serif", Integer.valueOf(-1), Integer.valueOf(-1)
        });
        return ((Typeface) (obj));
        obj;
_L2:
        throw new RuntimeException(((Throwable) (obj)));
        obj;
        if (true) goto _L2; else goto _L1
_L1:
    }

    protected final Method obtainCreateFromFamiliesWithDefaultMethod(Class class1)
        throws NoSuchMethodException
    {
        class1 = android/graphics/Typeface.getDeclaredMethod("createFromFamiliesWithDefault", new Class[] {
            Array.newInstance(class1, 1).getClass(), java/lang/String, Integer.TYPE, Integer.TYPE
        });
        class1.setAccessible(true);
        return class1;
    }
}
