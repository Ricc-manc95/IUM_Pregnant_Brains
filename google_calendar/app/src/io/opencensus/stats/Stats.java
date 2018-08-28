// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.stats;

import io.opencensus.internal.Provider;
import java.util.logging.Level;
import java.util.logging.Logger;

// Referenced classes of package io.opencensus.stats:
//            StatsComponent

public final class Stats
{

    private static final Logger logger = Logger.getLogger(io/opencensus/stats/Stats.getName());
    public static final StatsComponent statsComponent = loadStatsComponent(io/opencensus/stats/StatsComponent.getClassLoader());

    private Stats()
    {
    }

    private static StatsComponent loadStatsComponent(ClassLoader classloader)
    {
        StatsComponent statscomponent;
        try
        {
            statscomponent = (StatsComponent)Provider.createInstance(Class.forName("io.opencensus.impl.stats.StatsComponentImpl", true, classloader), io/opencensus/stats/StatsComponent);
        }
        catch (ClassNotFoundException classnotfoundexception)
        {
            logger.logp(Level.FINE, "io.opencensus.stats.Stats", "loadStatsComponent", "Couldn't load full implementation for StatsComponent, now trying to load lite implementation.", classnotfoundexception);
            try
            {
                classloader = (StatsComponent)Provider.createInstance(Class.forName("io.opencensus.impllite.stats.StatsComponentImplLite", true, classloader), io/opencensus/stats/StatsComponent);
            }
            // Misplaced declaration of an exception variable
            catch (ClassLoader classloader)
            {
                logger.logp(Level.FINE, "io.opencensus.stats.Stats", "loadStatsComponent", "Couldn't load lite implementation for StatsComponent, now using default implementation for StatsComponent.", classloader);
                return new NoopStats.NoopStatsComponent();
            }
            return classloader;
        }
        return statscomponent;
    }

}
