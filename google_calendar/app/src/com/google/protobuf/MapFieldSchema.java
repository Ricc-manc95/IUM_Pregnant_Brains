// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.util.Map;

interface MapFieldSchema
{

    public abstract Map forMapData(Object obj);

    public abstract MapEntryLite.Metadata forMapMetadata(Object obj);

    public abstract Map forMutableMapData(Object obj);

    public abstract int getSerializedSize(int i, Object obj, Object obj1);

    public abstract boolean isImmutable(Object obj);

    public abstract Object mergeFrom(Object obj, Object obj1);

    public abstract Object newMapField$5166KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0();

    public abstract Object toImmutable(Object obj);
}
