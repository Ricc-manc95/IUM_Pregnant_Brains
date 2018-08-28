// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package dagger.internal;

import dagger.Lazy;
import java.util.Collections;
import java.util.Map;

// Referenced classes of package dagger.internal:
//            Factory

public final class MapProviderFactory
    implements Lazy, Factory
{

    private final Map contributingMap;

    public MapProviderFactory(Map map)
    {
        contributingMap = Collections.unmodifiableMap(map);
    }

    public final Object get()
    {
        return contributingMap;
    }
}
