// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.tags;

import io.opencensus.internal.Provider;
import java.util.logging.Level;
import java.util.logging.Logger;

// Referenced classes of package io.opencensus.tags:
//            TagsComponent

public final class Tags
{

    private static final Logger logger = Logger.getLogger(io/opencensus/tags/Tags.getName());
    public static final TagsComponent tagsComponent = loadTagsComponent(io/opencensus/tags/TagsComponent.getClassLoader());

    private Tags()
    {
    }

    private static TagsComponent loadTagsComponent(ClassLoader classloader)
    {
        TagsComponent tagscomponent;
        try
        {
            tagscomponent = (TagsComponent)Provider.createInstance(Class.forName("io.opencensus.impl.tags.TagsComponentImpl", true, classloader), io/opencensus/tags/TagsComponent);
        }
        catch (ClassNotFoundException classnotfoundexception)
        {
            logger.logp(Level.FINE, "io.opencensus.tags.Tags", "loadTagsComponent", "Couldn't load full implementation for TagsComponent, now trying to load lite implementation.", classnotfoundexception);
            try
            {
                classloader = (TagsComponent)Provider.createInstance(Class.forName("io.opencensus.impllite.tags.TagsComponentImplLite", true, classloader), io/opencensus/tags/TagsComponent);
            }
            // Misplaced declaration of an exception variable
            catch (ClassLoader classloader)
            {
                logger.logp(Level.FINE, "io.opencensus.tags.Tags", "loadTagsComponent", "Couldn't load lite implementation for TagsComponent, now using default implementation for TagsComponent.", classloader);
                return new NoopTags.NoopTagsComponent();
            }
            return classloader;
        }
        return tagscomponent;
    }

}
