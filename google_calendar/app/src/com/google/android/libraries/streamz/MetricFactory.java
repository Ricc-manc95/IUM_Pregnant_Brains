// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.streamz;

import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.PrimitiveNonBoxingCollection;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.android.libraries.streamz:
//            MetricConfigProvider, GenericMetric, Field, CellFieldTuple, 
//            CellValue, StreamzMismatchException, IncrementListener

public final class MetricFactory
    implements MetricConfigProvider
{

    private static final Charset UTF_8 = Charset.forName("UTF-8");
    public static final MetricFactory defaultFactory = new MetricFactory(10);
    private volatile IncrementListener incrementListener;
    private final Object lock = new Object();
    private final Map nameToGenericMetricMap = new HashMap(10);

    private MetricFactory(int i)
    {
    }

    public static long umaMetricHash(String s)
    {
        long l;
        try
        {
            MessageDigest messagedigest = MessageDigest.getInstance("MD5");
            messagedigest.update(s.getBytes(UTF_8));
            l = ByteBuffer.wrap(messagedigest.digest()).getLong();
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            throw new RuntimeException(s);
        }
        return l;
    }

    public final com.google.clearcut.streamz.StreamzProto.IncrementRequest generateIncrementRequestProto()
    {
        Object obj = new ArrayList();
        Object obj2 = lock;
        obj2;
        JVM INSTR monitorenter ;
        for (Iterator iterator = nameToGenericMetricMap.values().iterator(); iterator.hasNext(); ((List) (obj)).add(((GenericMetric)iterator.next()).getMetricSnapshotAndClear())) { }
        break MISSING_BLOCK_LABEL_66;
        obj;
        obj2;
        JVM INSTR monitorexit ;
        throw obj;
        obj2;
        JVM INSTR monitorexit ;
        com.google.clearcut.streamz.StreamzProto.IncrementRequest.Builder builder = (com.google.clearcut.streamz.StreamzProto.IncrementRequest.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)com.google.clearcut.streamz.StreamzProto.IncrementRequest.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        ArrayList arraylist = (ArrayList)obj;
        int k1 = arraylist.size();
        for (int i = 0; i < k1; i++)
        {
            Object obj1 = (GenericMetric.MetricSnapshot)arraylist.get(i);
            Object obj3;
            if (((GenericMetric.MetricSnapshot) (obj1)).updates == 0)
            {
                obj1 = null;
            } else
            {
                obj3 = (com.google.frameworks.client.streamz.StreamzObjectsProto.IncrementBatch.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)com.google.frameworks.client.streamz.StreamzObjectsProto.IncrementBatch.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
                long l2 = umaMetricHash(((GenericMetric.MetricSnapshot) (obj1)).name);
                ((com.google.protobuf.GeneratedMessageLite.Builder) (obj3)).copyOnWrite();
                com.google.frameworks.client.streamz.StreamzObjectsProto.IncrementBatch incrementbatch = (com.google.frameworks.client.streamz.StreamzObjectsProto.IncrementBatch)((com.google.frameworks.client.streamz.StreamzObjectsProto.IncrementBatch.Builder) (obj3)).instance;
                incrementbatch.bitField0_ = incrementbatch.bitField0_ | 2;
                incrementbatch.hashedStreamzName_ = l2;
                Field afield[] = ((GenericMetric.MetricSnapshot) (obj1)).fields;
                int l1 = afield.length;
                for (int j = 0; j < l1; j++)
                {
                    long l3 = umaMetricHash(afield[j].name);
                    ((com.google.protobuf.GeneratedMessageLite.Builder) (obj3)).copyOnWrite();
                    com.google.frameworks.client.streamz.StreamzObjectsProto.IncrementBatch incrementbatch1 = (com.google.frameworks.client.streamz.StreamzObjectsProto.IncrementBatch)((com.google.frameworks.client.streamz.StreamzObjectsProto.IncrementBatch.Builder) (obj3)).instance;
                    if (!incrementbatch1.hashedFieldName_.isModifiable())
                    {
                        incrementbatch1.hashedFieldName_ = GeneratedMessageLite.mutableCopy(incrementbatch1.hashedFieldName_);
                    }
                    incrementbatch1.hashedFieldName_.addLong(l3);
                }

                com.google.frameworks.client.streamz.StreamzObjectsProto.IncrementBatch.Increment.Builder builder1;
                Object obj4;
                for (Iterator iterator1 = ((GenericMetric.MetricSnapshot) (obj1)).cellMap.entrySet().iterator(); iterator1.hasNext(); ((com.google.frameworks.client.streamz.StreamzObjectsProto.IncrementBatch) (obj4)).inc_.add((com.google.frameworks.client.streamz.StreamzObjectsProto.IncrementBatch.Increment)(GeneratedMessageLite)builder1.build()))
                {
                    obj4 = (java.util.Map.Entry)iterator1.next();
                    builder1 = (com.google.frameworks.client.streamz.StreamzObjectsProto.IncrementBatch.Increment.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)com.google.frameworks.client.streamz.StreamzObjectsProto.IncrementBatch.Increment.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
                    Object obj6 = (CellFieldTuple)((java.util.Map.Entry) (obj4)).getKey();
                    obj4 = (CellValue)((java.util.Map.Entry) (obj4)).getValue();
                    Object obj5;
                    if (((GenericMetric.MetricSnapshot) (obj1)).fields.length > 0)
                    {
                        obj5 = new ArrayList(((CellFieldTuple) (obj6)).fieldValues.length);
                        int k = 0;
                        while (k < ((CellFieldTuple) (obj6)).fieldValues.length) 
                        {
                            com.google.frameworks.client.streamz.StreamzObjectsProto.IncrementBatch.Increment.Field.Builder builder2 = (com.google.frameworks.client.streamz.StreamzObjectsProto.IncrementBatch.Increment.Field.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)com.google.frameworks.client.streamz.StreamzObjectsProto.IncrementBatch.Increment.Field.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
                            Object obj9 = ((CellFieldTuple) (obj6)).fieldValues[k];
                            if (obj9 instanceof String)
                            {
                                obj9 = (String)obj9;
                                builder2.copyOnWrite();
                                com.google.frameworks.client.streamz.StreamzObjectsProto.IncrementBatch.Increment.Field field = (com.google.frameworks.client.streamz.StreamzObjectsProto.IncrementBatch.Increment.Field)builder2.instance;
                                if (obj9 == null)
                                {
                                    throw new NullPointerException();
                                }
                                field.valueCase_ = 1;
                                field.value_ = obj9;
                            } else
                            if (obj9 instanceof Integer)
                            {
                                int i2 = ((Integer)obj9).intValue();
                                builder2.copyOnWrite();
                                obj9 = (com.google.frameworks.client.streamz.StreamzObjectsProto.IncrementBatch.Increment.Field)builder2.instance;
                                obj9.valueCase_ = 2;
                                obj9.value_ = Integer.valueOf(i2);
                            } else
                            if (obj9 instanceof Boolean)
                            {
                                boolean flag = ((Boolean)obj9).booleanValue();
                                builder2.copyOnWrite();
                                obj9 = (com.google.frameworks.client.streamz.StreamzObjectsProto.IncrementBatch.Increment.Field)builder2.instance;
                                obj9.valueCase_ = 3;
                                obj9.value_ = Boolean.valueOf(flag);
                            } else
                            {
                                obj1 = String.valueOf(obj9);
                                throw new IllegalArgumentException((new StringBuilder(String.valueOf(obj1).length() + 40)).append("Field ").append(k).append(" has unexpected value: ").append(((String) (obj1))).toString());
                            }
                            ((List) (obj5)).add((com.google.frameworks.client.streamz.StreamzObjectsProto.IncrementBatch.Increment.Field)(GeneratedMessageLite)builder2.build());
                            k++;
                        }
                        builder1.copyOnWrite();
                        obj6 = (com.google.frameworks.client.streamz.StreamzObjectsProto.IncrementBatch.Increment)builder1.instance;
                        if (!((com.google.frameworks.client.streamz.StreamzObjectsProto.IncrementBatch.Increment) (obj6)).field_.isModifiable())
                        {
                            obj6.field_ = GeneratedMessageLite.mutableCopy(((com.google.frameworks.client.streamz.StreamzObjectsProto.IncrementBatch.Increment) (obj6)).field_);
                        }
                        obj6 = ((com.google.frameworks.client.streamz.StreamzObjectsProto.IncrementBatch.Increment) (obj6)).field_;
                        Internal.checkNotNull(obj5);
                        if (obj5 instanceof LazyStringList)
                        {
                            List list = ((LazyStringList)obj5).getUnderlyingElements();
                            obj5 = (LazyStringList)obj6;
                            int l = ((List) (obj6)).size();
                            for (obj6 = list.iterator(); ((Iterator) (obj6)).hasNext();)
                            {
                                Object obj7 = ((Iterator) (obj6)).next();
                                if (obj7 == null)
                                {
                                    i = ((LazyStringList) (obj5)).size();
                                    obj1 = (new StringBuilder(37)).append("Element at index ").append(i - l).append(" is null.").toString();
                                    for (i = ((LazyStringList) (obj5)).size() - 1; i >= l; i--)
                                    {
                                        ((LazyStringList) (obj5)).remove(i);
                                    }

                                    throw new NullPointerException(((String) (obj1)));
                                }
                                if (obj7 instanceof ByteString)
                                {
                                    ((LazyStringList) (obj5)).add((ByteString)obj7);
                                } else
                                {
                                    ((LazyStringList) (obj5)).add((String)obj7);
                                }
                            }

                        } else
                        if (obj5 instanceof PrimitiveNonBoxingCollection)
                        {
                            ((List) (obj6)).addAll((Collection)obj5);
                        } else
                        {
                            if ((obj6 instanceof ArrayList) && (obj5 instanceof Collection))
                            {
                                ArrayList arraylist1 = (ArrayList)obj6;
                                int i1 = ((List) (obj6)).size();
                                arraylist1.ensureCapacity(((Collection)obj5).size() + i1);
                            }
                            int j1 = ((List) (obj6)).size();
                            obj5 = ((Iterable) (obj5)).iterator();
                            while (((Iterator) (obj5)).hasNext()) 
                            {
                                Object obj8 = ((Iterator) (obj5)).next();
                                if (obj8 == null)
                                {
                                    i = ((List) (obj6)).size();
                                    obj1 = (new StringBuilder(37)).append("Element at index ").append(i - j1).append(" is null.").toString();
                                    for (i = ((List) (obj6)).size() - 1; i >= j1; i--)
                                    {
                                        ((List) (obj6)).remove(i);
                                    }

                                    throw new NullPointerException(((String) (obj1)));
                                }
                                ((List) (obj6)).add(obj8);
                            }
                        }
                    }
                    obj4 = ((CellValue) (obj4)).toValueProto();
                    builder1.copyOnWrite();
                    obj5 = (com.google.frameworks.client.streamz.StreamzObjectsProto.IncrementBatch.Increment)builder1.instance;
                    if (obj4 == null)
                    {
                        throw new NullPointerException();
                    }
                    obj5.incBy_ = ((com.google.frameworks.client.streamz.StreamzObjectsProto.IncrementBatch.Increment.Value) (obj4));
                    obj5.bitField0_ = ((com.google.frameworks.client.streamz.StreamzObjectsProto.IncrementBatch.Increment) (obj5)).bitField0_ | 1;
                    ((com.google.protobuf.GeneratedMessageLite.Builder) (obj3)).copyOnWrite();
                    obj4 = (com.google.frameworks.client.streamz.StreamzObjectsProto.IncrementBatch)((com.google.frameworks.client.streamz.StreamzObjectsProto.IncrementBatch.Builder) (obj3)).instance;
                    if (!((com.google.frameworks.client.streamz.StreamzObjectsProto.IncrementBatch) (obj4)).inc_.isModifiable())
                    {
                        obj4.inc_ = GeneratedMessageLite.mutableCopy(((com.google.frameworks.client.streamz.StreamzObjectsProto.IncrementBatch) (obj4)).inc_);
                    }
                }

                obj1 = (com.google.frameworks.client.streamz.StreamzObjectsProto.IncrementBatch)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj3)).build();
            }
            if (obj1 == null)
            {
                continue;
            }
            builder.copyOnWrite();
            obj3 = (com.google.clearcut.streamz.StreamzProto.IncrementRequest)builder.instance;
            if (obj1 == null)
            {
                throw new NullPointerException();
            }
            if (!((com.google.clearcut.streamz.StreamzProto.IncrementRequest) (obj3)).batch_.isModifiable())
            {
                obj3.batch_ = GeneratedMessageLite.mutableCopy(((com.google.clearcut.streamz.StreamzProto.IncrementRequest) (obj3)).batch_);
            }
            ((com.google.clearcut.streamz.StreamzProto.IncrementRequest) (obj3)).batch_.add(obj1);
        }

        return (com.google.clearcut.streamz.StreamzProto.IncrementRequest)(GeneratedMessageLite)builder.build();
    }

    public final IncrementListener getIncrementListener()
    {
        return incrementListener;
    }

    public final GenericMetric getOrCreate(GenericMetric genericmetric)
    {
        Object obj = lock;
        obj;
        JVM INSTR monitorenter ;
        Object obj1 = (GenericMetric)nameToGenericMetricMap.get(genericmetric.name);
        if (obj1 == null)
        {
            break MISSING_BLOCK_LABEL_217;
        }
        if (obj1.getClass() != genericmetric.getClass())
        {
            genericmetric = ((GenericMetric) (obj1)).name;
            throw new StreamzMismatchException((new StringBuilder(String.valueOf(genericmetric).length() + 36)).append("Streamz ").append(genericmetric).append(" with a different class name").toString());
        }
        break MISSING_BLOCK_LABEL_97;
        genericmetric;
        obj;
        JVM INSTR monitorexit ;
        throw genericmetric;
        if (!Arrays.equals(((GenericMetric) (obj1)).fields, genericmetric.fields))
        {
            String s = ((GenericMetric) (obj1)).name;
            obj1 = Arrays.toString(((GenericMetric) (obj1)).fields);
            genericmetric = Arrays.toString(genericmetric.fields);
            throw new StreamzMismatchException((new StringBuilder(String.valueOf(s).length() + 32 + String.valueOf(obj1).length() + String.valueOf(genericmetric).length())).append("Streamz ").append(s).append(" with field diffs: ").append(((String) (obj1))).append(" and ").append(genericmetric).toString());
        }
        obj;
        JVM INSTR monitorexit ;
        return ((GenericMetric) (obj1));
        nameToGenericMetricMap.put(genericmetric.name, genericmetric);
        obj;
        JVM INSTR monitorexit ;
        return genericmetric;
    }

}
