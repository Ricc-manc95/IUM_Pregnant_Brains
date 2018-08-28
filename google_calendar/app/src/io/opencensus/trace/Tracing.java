// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.trace;

import io.opencensus.internal.Provider;
import java.util.logging.Level;
import java.util.logging.Logger;

// Referenced classes of package io.opencensus.trace:
//            TraceComponent

public final class Tracing
{

    private static final Logger logger = Logger.getLogger(io/opencensus/trace/Tracing.getName());
    public static final TraceComponent traceComponent = loadTraceComponent(io/opencensus/trace/TraceComponent.getClassLoader());

    private Tracing()
    {
    }

    private static TraceComponent loadTraceComponent(ClassLoader classloader)
    {
        TraceComponent tracecomponent;
        try
        {
            tracecomponent = (TraceComponent)Provider.createInstance(Class.forName("io.opencensus.impl.trace.TraceComponentImpl", true, classloader), io/opencensus/trace/TraceComponent);
        }
        catch (ClassNotFoundException classnotfoundexception)
        {
            logger.logp(Level.FINE, "io.opencensus.trace.Tracing", "loadTraceComponent", "Couldn't load full implementation for TraceComponent, now trying to load lite implementation.", classnotfoundexception);
            try
            {
                classloader = (TraceComponent)Provider.createInstance(Class.forName("io.opencensus.impllite.trace.TraceComponentImplLite", true, classloader), io/opencensus/trace/TraceComponent);
            }
            // Misplaced declaration of an exception variable
            catch (ClassLoader classloader)
            {
                logger.logp(Level.FINE, "io.opencensus.trace.Tracing", "loadTraceComponent", "Couldn't load lite implementation for TraceComponent, now using default implementation for TraceComponent.", classloader);
                return new TraceComponent.NoopTraceComponent();
            }
            return classloader;
        }
        return tracecomponent;
    }

}
