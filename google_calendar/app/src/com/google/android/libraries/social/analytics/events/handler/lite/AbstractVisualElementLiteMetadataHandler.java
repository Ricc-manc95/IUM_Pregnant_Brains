// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.social.analytics.events.handler.lite;

import com.google.android.libraries.social.analytics.visualelement.VisualElement;

// Referenced classes of package com.google.android.libraries.social.analytics.events.handler.lite:
//            VisualElementLiteMetadataHandler

public abstract class AbstractVisualElementLiteMetadataHandler
    implements VisualElementLiteMetadataHandler
{

    public AbstractVisualElementLiteMetadataHandler()
    {
    }

    public final void handleMetadata(VisualElement visualelement, com.google.protobuf.MessageLite.Builder builder)
    {
        handleVisualElementMetadata(visualelement, builder);
    }

    public abstract void handleVisualElementMetadata(VisualElement visualelement, com.google.protobuf.MessageLite.Builder builder);
}
