// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.protobuf:
//            MapFieldSchema, MapFieldLite, MapEntryLite, CodedOutputStream, 
//            FieldSet

final class MapFieldSchemaLite
    implements MapFieldSchema
{

    MapFieldSchemaLite()
    {
    }

    public final Map forMapData(Object obj)
    {
        return (MapFieldLite)obj;
    }

    public final MapEntryLite.Metadata forMapMetadata(Object obj)
    {
        return ((MapEntryLite)obj).metadata;
    }

    public final Map forMutableMapData(Object obj)
    {
        return (MapFieldLite)obj;
    }

    public final int getSerializedSize(int i, Object obj, Object obj1)
    {
        MapFieldLite mapfieldlite = (MapFieldLite)obj;
        obj = (MapEntryLite)obj1;
        if (mapfieldlite.isEmpty())
        {
            return 0;
        }
        obj1 = mapfieldlite.entrySet().iterator();
        Object obj3;
        MapEntryLite.Metadata metadata;
        int j;
        int k;
        int l;
        for (j = 0; ((Iterator) (obj1)).hasNext(); j = CodedOutputStream.computeLengthDelimitedFieldSize(FieldSet.computeElementSize(metadata.valueType, 2, obj3) + l) + k + j)
        {
            obj3 = (java.util.Map.Entry)((Iterator) (obj1)).next();
            Object obj2 = ((java.util.Map.Entry) (obj3)).getKey();
            obj3 = ((java.util.Map.Entry) (obj3)).getValue();
            k = CodedOutputStream.computeTagSize(i);
            metadata = ((MapEntryLite) (obj)).metadata;
            l = FieldSet.computeElementSize(metadata.keyType, 1, obj2);
        }

        return j;
    }

    public final boolean isImmutable(Object obj)
    {
        return !((MapFieldLite)obj).isMutable;
    }

    public final Object mergeFrom(Object obj, Object obj1)
    {
        obj = (MapFieldLite)obj;
        obj1 = (MapFieldLite)obj1;
        if (!((MapFieldLite) (obj1)).isEmpty())
        {
            if (!((MapFieldLite) (obj)).isMutable)
            {
                if (((MapFieldLite) (obj)).isEmpty())
                {
                    obj = new MapFieldLite();
                } else
                {
                    obj = new MapFieldLite(((Map) (obj)));
                }
            }
            if (!((MapFieldLite) (obj)).isMutable)
            {
                throw new UnsupportedOperationException();
            }
            if (!((MapFieldLite) (obj1)).isEmpty())
            {
                ((MapFieldLite) (obj)).putAll(((Map) (obj1)));
            }
            return obj;
        } else
        {
            return obj;
        }
    }

    public final Object newMapField$5166KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0()
    {
        MapFieldLite mapfieldlite = MapFieldLite.EMPTY_MAP_FIELD;
        if (mapfieldlite.isEmpty())
        {
            return new MapFieldLite();
        } else
        {
            return new MapFieldLite(mapfieldlite);
        }
    }

    public final Object toImmutable(Object obj)
    {
        ((MapFieldLite)obj).isMutable = false;
        return obj;
    }
}
