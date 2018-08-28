// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.fitness;


public final class FitnessActivities
{

    private static final String zzbhj[];

    public static String getName(int i)
    {
        String s;
        if (i < 0 || i >= zzbhj.length)
        {
            s = "unknown";
        } else
        {
            String s1 = zzbhj[i];
            s = s1;
            if (s1 == null)
            {
                return "unknown";
            }
        }
        return s;
    }

    static 
    {
        String as[] = new String[120];
        zzbhj = as;
        as[9] = "aerobics";
        zzbhj[119] = "archery";
        zzbhj[10] = "badminton";
        zzbhj[11] = "baseball";
        zzbhj[12] = "basketball";
        zzbhj[13] = "biathlon";
        zzbhj[1] = "biking";
        zzbhj[14] = "biking.hand";
        zzbhj[15] = "biking.mountain";
        zzbhj[16] = "biking.road";
        zzbhj[17] = "biking.spinning";
        zzbhj[18] = "biking.stationary";
        zzbhj[19] = "biking.utility";
        zzbhj[20] = "boxing";
        zzbhj[21] = "calisthenics";
        zzbhj[22] = "circuit_training";
        zzbhj[23] = "cricket";
        zzbhj[113] = "crossfit";
        zzbhj[106] = "curling";
        zzbhj[24] = "dancing";
        zzbhj[102] = "diving";
        zzbhj[117] = "elevator";
        zzbhj[25] = "elliptical";
        zzbhj[103] = "ergometer";
        zzbhj[118] = "escalator";
        zzbhj[6] = "exiting_vehicle";
        zzbhj[26] = "fencing";
        zzbhj[27] = "football.american";
        zzbhj[28] = "football.australian";
        zzbhj[29] = "football.soccer";
        zzbhj[30] = "frisbee_disc";
        zzbhj[31] = "gardening";
        zzbhj[32] = "golf";
        zzbhj[33] = "gymnastics";
        zzbhj[34] = "handball";
        zzbhj[114] = "interval_training.high_intensity";
        zzbhj[35] = "hiking";
        zzbhj[36] = "hockey";
        zzbhj[37] = "horseback_riding";
        zzbhj[38] = "housework";
        zzbhj[104] = "ice_skating";
        zzbhj[0] = "in_vehicle";
        zzbhj[115] = "interval_training";
        zzbhj[39] = "jump_rope";
        zzbhj[40] = "kayaking";
        zzbhj[41] = "kettlebell_training";
        zzbhj[107] = "kick_scooter";
        zzbhj[42] = "kickboxing";
        zzbhj[43] = "kitesurfing";
        zzbhj[44] = "martial_arts";
        zzbhj[45] = "meditation";
        zzbhj[46] = "martial_arts.mixed";
        zzbhj[2] = "on_foot";
        zzbhj[108] = "other";
        zzbhj[47] = "p90x";
        zzbhj[48] = "paragliding";
        zzbhj[49] = "pilates";
        zzbhj[50] = "polo";
        zzbhj[51] = "racquetball";
        zzbhj[52] = "rock_climbing";
        zzbhj[53] = "rowing";
        zzbhj[54] = "rowing.machine";
        zzbhj[55] = "rugby";
        zzbhj[8] = "running";
        zzbhj[56] = "running.jogging";
        zzbhj[57] = "running.sand";
        zzbhj[58] = "running.treadmill";
        zzbhj[59] = "sailing";
        zzbhj[60] = "scuba_diving";
        zzbhj[61] = "skateboarding";
        zzbhj[62] = "skating";
        zzbhj[63] = "skating.cross";
        zzbhj[105] = "skating.indoor";
        zzbhj[64] = "skating.inline";
        zzbhj[65] = "skiing";
        zzbhj[66] = "skiing.back_country";
        zzbhj[67] = "skiing.cross_country";
        zzbhj[68] = "skiing.downhill";
        zzbhj[69] = "skiing.kite";
        zzbhj[70] = "skiing.roller";
        zzbhj[71] = "sledding";
        zzbhj[72] = "sleep";
        zzbhj[109] = "sleep.light";
        zzbhj[110] = "sleep.deep";
        zzbhj[111] = "sleep.rem";
        zzbhj[112] = "sleep.awake";
        zzbhj[73] = "snowboarding";
        zzbhj[74] = "snowmobile";
        zzbhj[75] = "snowshoeing";
        zzbhj[76] = "squash";
        zzbhj[77] = "stair_climbing";
        zzbhj[78] = "stair_climbing.machine";
        zzbhj[79] = "standup_paddleboarding";
        zzbhj[3] = "still";
        zzbhj[80] = "strength_training";
        zzbhj[81] = "surfing";
        zzbhj[82] = "swimming";
        zzbhj[83] = "swimming.pool";
        zzbhj[84] = "swimming.open_water";
        zzbhj[85] = "table_tennis";
        zzbhj[86] = "team_sports";
        zzbhj[87] = "tennis";
        zzbhj[5] = "tilting";
        zzbhj[88] = "treadmill";
        zzbhj[4] = "unknown";
        zzbhj[89] = "volleyball";
        zzbhj[90] = "volleyball.beach";
        zzbhj[91] = "volleyball.indoor";
        zzbhj[92] = "wakeboarding";
        zzbhj[7] = "walking";
        zzbhj[93] = "walking.fitness";
        zzbhj[94] = "walking.nordic";
        zzbhj[95] = "walking.treadmill";
        zzbhj[116] = "walking.stroller";
        zzbhj[96] = "water_polo";
        zzbhj[97] = "weightlifting";
        zzbhj[98] = "wheelchair";
        zzbhj[99] = "windsurfing";
        zzbhj[100] = "yoga";
        zzbhj[101] = "zumba";
    }
}
