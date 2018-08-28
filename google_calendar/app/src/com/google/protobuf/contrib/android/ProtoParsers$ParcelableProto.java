// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf.contrib.android;

import android.os.Parcelable;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.MessageLite;

public interface 
    extends Parcelable
{

    public abstract MessageLite getMessage(MessageLite messagelite, ExtensionRegistryLite extensionregistrylite);
}
