// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.ccil.cowan.tagsoup;

import java.io.InputStream;
import java.io.Reader;

public interface AutoDetector
{

    public abstract Reader autoDetectingReader(InputStream inputstream);
}
