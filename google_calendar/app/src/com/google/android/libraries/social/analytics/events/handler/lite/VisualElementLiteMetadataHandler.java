// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.social.analytics.events.handler.lite;

import com.google.android.libraries.social.analytics.visualelement.VisualElement;

public interface VisualElementLiteMetadataHandler
{

    public abstract Class getHandledVisualElementClass();

    public abstract void handleMetadata(VisualElement visualelement, com.google.protobuf.MessageLite.Builder builder);
}
