// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package dagger.android;


// Referenced classes of package dagger.android:
//            AndroidInjector

public abstract class 
    implements 
{

    public abstract AndroidInjector build();

    public final AndroidInjector create(Object obj)
    {
        seedInstance(obj);
        return build();
    }

    public abstract void seedInstance(Object obj);

    public ()
    {
    }
}
