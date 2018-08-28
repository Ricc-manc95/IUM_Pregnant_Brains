// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.fitness.data;

import android.os.Parcel;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

// Referenced classes of package com.google.android.gms.fitness.data:
//            Field, zzj

public final class DataType extends zza
    implements ReflectedParcelable
{

    private static final Set AGGREGATE_INPUT_TYPES;
    public static final android.os.Parcelable.Creator CREATOR = new zzj();
    public static final DataType TYPE_ACTIVITY_SEGMENT;
    private static final DataType TYPE_BASAL_METABOLIC_RATE;
    private static final DataType TYPE_BODY_FAT_PERCENTAGE;
    private static final DataType TYPE_BODY_HIP_CIRCUMFERENCE;
    private static final DataType TYPE_BODY_WAIST_CIRCUMFERENCE;
    private static final DataType TYPE_CALORIES_CONSUMED;
    private static final DataType TYPE_CALORIES_EXPENDED;
    public static final DataType TYPE_DISTANCE_CUMULATIVE;
    private static final DataType TYPE_DISTANCE_DELTA;
    private static final DataType TYPE_FLOOR_CHANGE;
    private static final DataType TYPE_HEART_RATE_BPM;
    private static final DataType TYPE_HYDRATION;
    private static final DataType TYPE_LOCATION_SAMPLE;
    private static final DataType TYPE_NUTRITION;
    private static final DataType TYPE_POWER_SAMPLE;
    private static final DataType TYPE_SPEED;
    public static final DataType TYPE_STEP_COUNT_CUMULATIVE;
    private static final DataType TYPE_STEP_COUNT_DELTA;
    private static final DataType TYPE_WEIGHT;
    public final String name;
    public final int versionCode;
    public final List zzbhT;

    DataType(int i, String s, List list)
    {
        versionCode = i;
        name = s;
        zzbhT = Collections.unmodifiableList(list);
    }

    private transient DataType(String s, Field afield[])
    {
        int j = afield.length;
        ArrayList arraylist = new ArrayList(j);
        for (int i = 0; i < j; i++)
        {
            arraylist.add(afield[i]);
        }

        this(1, s, ((List) (arraylist)));
    }

    public final boolean equals(Object obj)
    {
label0:
        {
            boolean flag2 = false;
            if (this != obj)
            {
                boolean flag1 = flag2;
                if (!(obj instanceof DataType))
                {
                    break label0;
                }
                obj = (DataType)obj;
                boolean flag;
                if (name.equals(((DataType) (obj)).name) && zzbhT.equals(((DataType) (obj)).zzbhT))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                flag1 = flag2;
                if (!flag)
                {
                    break label0;
                }
            }
            flag1 = true;
        }
        return flag1;
    }

    public final int hashCode()
    {
        return name.hashCode();
    }

    public final String toString()
    {
        return String.format("DataType{%s%s}", new Object[] {
            name, zzbhT
        });
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        i = parcel.dataPosition();
        zzc.zza(parcel, 1, name, false);
        zzc.zzc(parcel, 2, zzbhT, false);
        int j = versionCode;
        zzc.zzb(parcel, 1000, 4);
        parcel.writeInt(j);
        zzc.zzJ(parcel, i);
    }

    static 
    {
        TYPE_STEP_COUNT_DELTA = new DataType("com.google.step_count.delta", new Field[] {
            Field.FIELD_STEPS
        });
        TYPE_STEP_COUNT_CUMULATIVE = new DataType("com.google.step_count.cumulative", new Field[] {
            Field.FIELD_STEPS
        });
        new DataType("com.google.step_count.cadence", new Field[] {
            Field.FIELD_RPM
        });
        new DataType("com.google.stride_model", new Field[] {
            Field.zzbid
        });
        TYPE_ACTIVITY_SEGMENT = new DataType("com.google.activity.segment", new Field[] {
            Field.FIELD_ACTIVITY
        });
        TYPE_FLOOR_CHANGE = new DataType("com.google.floor_change", new Field[] {
            Field.FIELD_ACTIVITY, Field.FIELD_CONFIDENCE, Field.FIELD_ELEVATION_CHANGE, Field.FIELD_FLOORS
        });
        TYPE_CALORIES_CONSUMED = new DataType("com.google.calories.consumed", new Field[] {
            Field.FIELD_CALORIES
        });
        TYPE_CALORIES_EXPENDED = new DataType("com.google.calories.expended", new Field[] {
            Field.FIELD_CALORIES
        });
        TYPE_BASAL_METABOLIC_RATE = new DataType("com.google.calories.bmr", new Field[] {
            Field.FIELD_CALORIES
        });
        TYPE_POWER_SAMPLE = new DataType("com.google.power.sample", new Field[] {
            Field.FIELD_WATTS
        });
        new DataType("com.google.activity.sample", new Field[] {
            Field.FIELD_ACTIVITY, Field.FIELD_CONFIDENCE
        });
        new DataType("com.google.activity.samples", new Field[] {
            Field.FIELD_ACTIVITY_CONFIDENCE
        });
        new DataType("com.google.accelerometer", new Field[] {
            Field.zza.zzbif, Field.zza.zzbig, Field.zza.zzbih
        });
        new DataType("com.google.sensor.events", new Field[] {
            Field.FIELD_SENSOR_TYPE, Field.FIELD_TIMESTAMPS, Field.FIELD_SENSOR_VALUES
        });
        new DataType("com.google.sensor.const_rate_events", new Field[] {
            Field.FIELD_SENSOR_TYPES, Field.FIELD_SAMPLE_PERIOD, Field.FIELD_NUM_SAMPLES, Field.FIELD_NUM_DIMENSIONS, Field.FIELD_SENSOR_VALUES
        });
        TYPE_HEART_RATE_BPM = new DataType("com.google.heart_rate.bpm", new Field[] {
            Field.FIELD_BPM
        });
        TYPE_LOCATION_SAMPLE = new DataType("com.google.location.sample", new Field[] {
            Field.FIELD_LATITUDE, Field.FIELD_LONGITUDE, Field.FIELD_ACCURACY, Field.FIELD_ALTITUDE
        });
        new DataType("com.google.location.track", new Field[] {
            Field.FIELD_LATITUDE, Field.FIELD_LONGITUDE, Field.FIELD_ACCURACY, Field.FIELD_ALTITUDE
        });
        TYPE_DISTANCE_DELTA = new DataType("com.google.distance.delta", new Field[] {
            Field.FIELD_DISTANCE
        });
        TYPE_DISTANCE_CUMULATIVE = new DataType("com.google.distance.cumulative", new Field[] {
            Field.FIELD_DISTANCE
        });
        TYPE_SPEED = new DataType("com.google.speed", new Field[] {
            Field.FIELD_SPEED
        });
        new DataType("com.google.cycling.wheel_revolution.cumulative", new Field[] {
            Field.FIELD_REVOLUTIONS
        });
        new DataType("com.google.cycling.wheel_revolution.rpm", new Field[] {
            Field.FIELD_RPM
        });
        new DataType("com.google.cycling.pedaling.cumulative", new Field[] {
            Field.FIELD_REVOLUTIONS
        });
        new DataType("com.google.cycling.pedaling.cadence", new Field[] {
            Field.FIELD_RPM
        });
        new DataType("com.google.height", new Field[] {
            Field.FIELD_HEIGHT
        });
        TYPE_WEIGHT = new DataType("com.google.weight", new Field[] {
            Field.FIELD_WEIGHT
        });
        TYPE_BODY_FAT_PERCENTAGE = new DataType("com.google.body.fat.percentage", new Field[] {
            Field.FIELD_PERCENTAGE
        });
        TYPE_BODY_WAIST_CIRCUMFERENCE = new DataType("com.google.body.waist.circumference", new Field[] {
            Field.FIELD_CIRCUMFERENCE
        });
        TYPE_BODY_HIP_CIRCUMFERENCE = new DataType("com.google.body.hip.circumference", new Field[] {
            Field.FIELD_CIRCUMFERENCE
        });
        TYPE_NUTRITION = new DataType("com.google.nutrition", new Field[] {
            Field.FIELD_NUTRIENTS, Field.FIELD_MEAL_TYPE, Field.FIELD_FOOD_ITEM
        });
        TYPE_HYDRATION = new DataType("com.google.hydration", new Field[] {
            Field.FIELD_VOLUME
        });
        new DataType("com.google.activity.exercise", new Field[] {
            Field.FIELD_EXERCISE, Field.FIELD_REPETITIONS, Field.FIELD_DURATION, Field.FIELD_RESISTANCE_TYPE, Field.FIELD_RESISTANCE
        });
        new DataType("com.google.activity.summary", new Field[] {
            Field.FIELD_ACTIVITY, Field.FIELD_DURATION, Field.FIELD_NUM_SEGMENTS
        });
        new DataType("com.google.floor_change.summary", new Field[] {
            Field.FIELD_ACTIVITY_DURATION_ASCENDING, Field.FIELD_ACTIVITY_DURATION_DESCENDING, Field.FIELD_ELEVATION_GAIN, Field.FIELD_ELEVATION_LOSS, Field.FIELD_FLOOR_GAIN, Field.FIELD_FLOOR_LOSS
        });
        new DataType("com.google.calories.bmr.summary", new Field[] {
            Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN
        });
        new DataType("com.google.heart_rate.summary", new Field[] {
            Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN
        });
        new DataType("com.google.location.bounding_box", new Field[] {
            Field.FIELD_LOW_LATITUDE, Field.FIELD_LOW_LONGITUDE, Field.FIELD_HIGH_LATITUDE, Field.FIELD_HIGH_LONGITUDE
        });
        new DataType("com.google.power.summary", new Field[] {
            Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN
        });
        new DataType("com.google.speed.summary", new Field[] {
            Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN
        });
        new DataType("com.google.body.fat.percentage.summary", new Field[] {
            Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN
        });
        new DataType("com.google.body.hip.circumference.summary", new Field[] {
            Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN
        });
        new DataType("com.google.body.waist.circumference.summary", new Field[] {
            Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN
        });
        new DataType("com.google.weight.summary", new Field[] {
            Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN
        });
        new DataType("com.google.nutrition.summary", new Field[] {
            Field.FIELD_NUTRIENTS, Field.FIELD_MEAL_TYPE
        });
        com.google.android.gms.common.util.zza zza1 = new com.google.android.gms.common.util.zza();
        AGGREGATE_INPUT_TYPES = zza1;
        zza1.add(TYPE_ACTIVITY_SEGMENT);
        AGGREGATE_INPUT_TYPES.add(TYPE_BASAL_METABOLIC_RATE);
        AGGREGATE_INPUT_TYPES.add(TYPE_BODY_FAT_PERCENTAGE);
        AGGREGATE_INPUT_TYPES.add(TYPE_BODY_HIP_CIRCUMFERENCE);
        AGGREGATE_INPUT_TYPES.add(TYPE_BODY_WAIST_CIRCUMFERENCE);
        AGGREGATE_INPUT_TYPES.add(TYPE_CALORIES_CONSUMED);
        AGGREGATE_INPUT_TYPES.add(TYPE_CALORIES_EXPENDED);
        AGGREGATE_INPUT_TYPES.add(TYPE_DISTANCE_DELTA);
        AGGREGATE_INPUT_TYPES.add(TYPE_FLOOR_CHANGE);
        AGGREGATE_INPUT_TYPES.add(TYPE_LOCATION_SAMPLE);
        AGGREGATE_INPUT_TYPES.add(TYPE_NUTRITION);
        AGGREGATE_INPUT_TYPES.add(TYPE_HYDRATION);
        AGGREGATE_INPUT_TYPES.add(TYPE_HEART_RATE_BPM);
        AGGREGATE_INPUT_TYPES.add(TYPE_POWER_SAMPLE);
        AGGREGATE_INPUT_TYPES.add(TYPE_SPEED);
        AGGREGATE_INPUT_TYPES.add(TYPE_STEP_COUNT_DELTA);
        AGGREGATE_INPUT_TYPES.add(TYPE_WEIGHT);
    }
}
