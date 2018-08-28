// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.fitness.data;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

// Referenced classes of package com.google.android.gms.fitness.data:
//            zzn

public final class Field extends com.google.android.gms.common.internal.safeparcel.zza
{
    public static final class zza
    {

        public static final Field zzbif = new Field("x", 2);
        public static final Field zzbig = new Field("y", 2);
        public static final Field zzbih = new Field("z", 2);

        static 
        {
            new Field("debug_session", 7, Boolean.valueOf(true));
            new Field("google.android.fitness.SessionV2", 7, Boolean.valueOf(true));
        }
    }


    public static final android.os.Parcelable.Creator CREATOR = new zzn();
    public static final Field FIELD_ACCURACY = new Field("accuracy", 2);
    public static final Field FIELD_ACTIVITY = new Field("activity", 1);
    public static final Field FIELD_ACTIVITY_CONFIDENCE = new Field("activity_confidence", 4);
    public static final Field FIELD_ACTIVITY_DURATION_ASCENDING = new Field("activity_duration.ascending", 4);
    public static final Field FIELD_ACTIVITY_DURATION_DESCENDING = new Field("activity_duration.descending", 4);
    public static final Field FIELD_ALTITUDE = new Field("altitude", 2, Boolean.valueOf(true));
    public static final Field FIELD_AVERAGE = new Field("average", 2);
    public static final Field FIELD_BPM = new Field("bpm", 2);
    public static final Field FIELD_CALORIES = new Field("calories", 2);
    public static final Field FIELD_CIRCUMFERENCE = new Field("circumference", 2);
    public static final Field FIELD_CONFIDENCE = new Field("confidence", 2);
    public static final Field FIELD_DISTANCE = new Field("distance", 2);
    public static final Field FIELD_DURATION = new Field("duration", 1);
    public static final Field FIELD_ELEVATION_CHANGE = new Field("elevation.change", 2);
    public static final Field FIELD_ELEVATION_GAIN = new Field("elevation.gain", 4);
    public static final Field FIELD_ELEVATION_LOSS = new Field("elevation.loss", 4);
    public static final Field FIELD_EXERCISE = new Field("exercise", 3);
    public static final Field FIELD_FLOORS = new Field("floors", 2);
    public static final Field FIELD_FLOOR_GAIN = new Field("floor.gain", 4);
    public static final Field FIELD_FLOOR_LOSS = new Field("floor.loss", 4);
    public static final Field FIELD_FOOD_ITEM = new Field("food_item", 3);
    public static final Field FIELD_HEIGHT = new Field("height", 2);
    public static final Field FIELD_HIGH_LATITUDE = new Field("high_latitude", 2);
    public static final Field FIELD_HIGH_LONGITUDE = new Field("high_longitude", 2);
    public static final Field FIELD_LATITUDE = new Field("latitude", 2);
    public static final Field FIELD_LONGITUDE = new Field("longitude", 2);
    public static final Field FIELD_LOW_LATITUDE = new Field("low_latitude", 2);
    public static final Field FIELD_LOW_LONGITUDE = new Field("low_longitude", 2);
    public static final Field FIELD_MAX = new Field("max", 2);
    public static final Field FIELD_MEAL_TYPE = new Field("meal_type", 1);
    public static final Field FIELD_MIN = new Field("min", 2);
    public static final Field FIELD_NUM_DIMENSIONS = new Field("num_dimensions", 1);
    public static final Field FIELD_NUM_SAMPLES = new Field("num_samples", 1);
    public static final Field FIELD_NUM_SEGMENTS = new Field("num_segments", 1);
    public static final Field FIELD_NUTRIENTS = new Field("nutrients", 4);
    public static final Field FIELD_PERCENTAGE = new Field("percentage", 2);
    public static final Field FIELD_REPETITIONS = new Field("repetitions", 1);
    public static final Field FIELD_RESISTANCE = new Field("resistance", 2);
    public static final Field FIELD_RESISTANCE_TYPE = new Field("resistance_type", 1);
    public static final Field FIELD_REVOLUTIONS = new Field("revolutions", 1);
    public static final Field FIELD_RPM = new Field("rpm", 2);
    public static final Field FIELD_SAMPLE_PERIOD = new Field("sample_period", 1);
    public static final Field FIELD_SENSOR_TYPE = new Field("sensor_type", 1);
    public static final Field FIELD_SENSOR_TYPES = new Field("sensor_types", 1);
    public static final Field FIELD_SENSOR_VALUES = new Field("sensor_values", 6);
    public static final Field FIELD_SPEED = new Field("speed", 2);
    public static final Field FIELD_STEPS = new Field("steps", 1);
    public static final Field FIELD_TIMESTAMPS = new Field("timestamps", 5);
    public static final Field FIELD_VOLUME = new Field("volume", 2);
    public static final Field FIELD_WATTS = new Field("watts", 2);
    public static final Field FIELD_WEIGHT = new Field("weight", 2);
    public static final Field zzbid = new Field("google.android.fitness.StrideModel", 7);
    public final int format;
    public final String name;
    public final int versionCode;
    public final Boolean zzbie;

    Field(int i, String s, int j, Boolean boolean1)
    {
        versionCode = i;
        if (s == null)
        {
            throw new NullPointerException("null reference");
        } else
        {
            name = (String)s;
            format = j;
            zzbie = boolean1;
            return;
        }
    }

    Field(String s, int i)
    {
        this(2, s, i, null);
    }

    Field(String s, int i, Boolean boolean1)
    {
        this(2, s, i, boolean1);
    }

    public final boolean equals(Object obj)
    {
        if (this != obj) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (!(obj instanceof Field))
        {
            break; /* Loop/switch isn't completed */
        }
        obj = (Field)obj;
        boolean flag;
        if (name.equals(((Field) (obj)).name) && format == ((Field) (obj)).format)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L1; else goto _L3
_L3:
        return false;
    }

    public final int hashCode()
    {
        return name.hashCode();
    }

    public final String toString()
    {
        String s1 = name;
        String s;
        if (format == 1)
        {
            s = "i";
        } else
        {
            s = "f";
        }
        return String.format("%s(%s)", new Object[] {
            s1, s
        });
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        i = 1;
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        zzc.zza(parcel, 1, name, false);
        int k = format;
        zzc.zzb(parcel, 2, 4);
        parcel.writeInt(k);
        Boolean boolean1 = zzbie;
        if (boolean1 != null)
        {
            zzc.zzb(parcel, 3, 4);
            if (!boolean1.booleanValue())
            {
                i = 0;
            }
            parcel.writeInt(i);
        }
        i = versionCode;
        zzc.zzb(parcel, 1000, 4);
        parcel.writeInt(i);
        zzc.zzJ(parcel, j);
    }

    static 
    {
        new Field("duration", 1, Boolean.valueOf(true));
        new Field("activity_duration", 4);
        new Field("occurrences", 1);
    }
}
