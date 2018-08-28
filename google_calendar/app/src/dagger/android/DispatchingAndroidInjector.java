// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package dagger.android;

import dagger.internal.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.inject.Provider;

// Referenced classes of package dagger.android:
//            AndroidInjector

public final class DispatchingAndroidInjector
    implements AndroidInjector
{

    private final Map injectorFactories;

    public DispatchingAndroidInjector(Map map)
    {
        injectorFactories = map;
    }

    private final boolean maybeInject(Object obj)
    {
        Object obj1 = (Provider)injectorFactories.get(obj.getClass());
        if (obj1 == null)
        {
            return false;
        }
        obj1 = (AndroidInjector.Factory)((Provider) (obj1)).get();
        try
        {
            ((AndroidInjector)Preconditions.checkNotNull(((AndroidInjector.Factory) (obj1)).create(obj), "%s.create(I) should not return null.", obj1.getClass())).inject(obj);
        }
        catch (ClassCastException classcastexception)
        {
            throw new InvalidInjectorBindingException(String.format("%s does not implement AndroidInjector.Factory<%s>", new Object[] {
                obj1.getClass().getCanonicalName(), obj.getClass().getCanonicalName()
            }), classcastexception);
        }
        return true;
    }

    public final void inject(Object obj)
    {
        if (!maybeInject(obj))
        {
            ArrayList arraylist = new ArrayList();
            Iterator iterator = injectorFactories.keySet().iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }
                Class class1 = (Class)iterator.next();
                if (class1.isInstance(obj))
                {
                    arraylist.add(class1.getCanonicalName());
                }
            } while (true);
            Collections.sort(arraylist);
            if (arraylist.isEmpty())
            {
                obj = String.format("No injector factory bound for Class<%s>", new Object[] {
                    obj.getClass().getCanonicalName()
                });
            } else
            {
                obj = String.format("No injector factory bound for Class<%1$s>. Injector factories were bound for supertypes of %1$s: %2$s. Did you mean to bind an injector factory for the subtype?", new Object[] {
                    obj.getClass().getCanonicalName(), arraylist
                });
            }
            throw new IllegalArgumentException(((String) (obj)));
        } else
        {
            return;
        }
    }

    private class InvalidInjectorBindingException extends RuntimeException
    {

        InvalidInjectorBindingException(String s, ClassCastException classcastexception)
        {
            super(s, classcastexception);
        }
    }

}
