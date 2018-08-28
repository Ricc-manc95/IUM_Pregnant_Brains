// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.analytics.tracking.android;

import java.util.Map;

// Referenced classes of package com.google.analytics.tracking.android:
//            MetaModel

final class MetaModelInitializer
{

    private static final MetaModel.Formatter BOOLEAN_FORMATTER = new _cls1();
    private static final MetaModel.Formatter UP_TO_TWO_DIGIT_FLOAT_FORMATTER = new _cls2();

    public static void set(MetaModel metamodel)
    {
        metamodel.mMetaInfos.put("apiVersion", new MetaModel.MetaInfo("v", null, null));
        metamodel.mMetaInfos.put("libraryVersion", new MetaModel.MetaInfo("_v", null, null));
        MetaModel.Formatter formatter = BOOLEAN_FORMATTER;
        metamodel.mMetaInfos.put("anonymizeIp", new MetaModel.MetaInfo("aip", "0", formatter));
        metamodel.mMetaInfos.put("trackingId", new MetaModel.MetaInfo("tid", null, null));
        metamodel.mMetaInfos.put("hitType", new MetaModel.MetaInfo("t", null, null));
        metamodel.mMetaInfos.put("sessionControl", new MetaModel.MetaInfo("sc", null, null));
        metamodel.mMetaInfos.put("adSenseAdMobHitId", new MetaModel.MetaInfo("a", null, null));
        metamodel.mMetaInfos.put("usage", new MetaModel.MetaInfo("_u", null, null));
        metamodel.mMetaInfos.put("title", new MetaModel.MetaInfo("dt", null, null));
        metamodel.mMetaInfos.put("referrer", new MetaModel.MetaInfo("dr", null, null));
        metamodel.mMetaInfos.put("language", new MetaModel.MetaInfo("ul", null, null));
        metamodel.mMetaInfos.put("encoding", new MetaModel.MetaInfo("de", null, null));
        metamodel.mMetaInfos.put("page", new MetaModel.MetaInfo("dp", null, null));
        metamodel.mMetaInfos.put("screenColors", new MetaModel.MetaInfo("sd", null, null));
        metamodel.mMetaInfos.put("screenResolution", new MetaModel.MetaInfo("sr", null, null));
        metamodel.mMetaInfos.put("viewportSize", new MetaModel.MetaInfo("vp", null, null));
        formatter = BOOLEAN_FORMATTER;
        metamodel.mMetaInfos.put("javaEnabled", new MetaModel.MetaInfo("je", "1", formatter));
        metamodel.mMetaInfos.put("flashVersion", new MetaModel.MetaInfo("fl", null, null));
        metamodel.mMetaInfos.put("clientId", new MetaModel.MetaInfo("cid", null, null));
        metamodel.mMetaInfos.put("campaignName", new MetaModel.MetaInfo("cn", null, null));
        metamodel.mMetaInfos.put("campaignSource", new MetaModel.MetaInfo("cs", null, null));
        metamodel.mMetaInfos.put("campaignMedium", new MetaModel.MetaInfo("cm", null, null));
        metamodel.mMetaInfos.put("campaignKeyword", new MetaModel.MetaInfo("ck", null, null));
        metamodel.mMetaInfos.put("campaignContent", new MetaModel.MetaInfo("cc", null, null));
        metamodel.mMetaInfos.put("campaignId", new MetaModel.MetaInfo("ci", null, null));
        metamodel.mMetaInfos.put("gclid", new MetaModel.MetaInfo("gclid", null, null));
        metamodel.mMetaInfos.put("dclid", new MetaModel.MetaInfo("dclid", null, null));
        metamodel.mMetaInfos.put("gmob_t", new MetaModel.MetaInfo("gmob_t", null, null));
        metamodel.mMetaInfos.put("eventCategory", new MetaModel.MetaInfo("ec", null, null));
        metamodel.mMetaInfos.put("eventAction", new MetaModel.MetaInfo("ea", null, null));
        metamodel.mMetaInfos.put("eventLabel", new MetaModel.MetaInfo("el", null, null));
        metamodel.mMetaInfos.put("eventValue", new MetaModel.MetaInfo("ev", null, null));
        formatter = BOOLEAN_FORMATTER;
        metamodel.mMetaInfos.put("nonInteraction", new MetaModel.MetaInfo("ni", "0", formatter));
        metamodel.mMetaInfos.put("socialNetwork", new MetaModel.MetaInfo("sn", null, null));
        metamodel.mMetaInfos.put("socialAction", new MetaModel.MetaInfo("sa", null, null));
        metamodel.mMetaInfos.put("socialTarget", new MetaModel.MetaInfo("st", null, null));
        metamodel.mMetaInfos.put("appName", new MetaModel.MetaInfo("an", null, null));
        metamodel.mMetaInfos.put("appVersion", new MetaModel.MetaInfo("av", null, null));
        metamodel.mMetaInfos.put("description", new MetaModel.MetaInfo("cd", null, null));
        metamodel.mMetaInfos.put("appId", new MetaModel.MetaInfo("aid", null, null));
        metamodel.mMetaInfos.put("appInstallerId", new MetaModel.MetaInfo("aiid", null, null));
        metamodel.mMetaInfos.put("transactionId", new MetaModel.MetaInfo("ti", null, null));
        metamodel.mMetaInfos.put("transactionAffiliation", new MetaModel.MetaInfo("ta", null, null));
        metamodel.mMetaInfos.put("transactionShipping", new MetaModel.MetaInfo("ts", null, null));
        metamodel.mMetaInfos.put("transactionTotal", new MetaModel.MetaInfo("tr", null, null));
        metamodel.mMetaInfos.put("transactionTax", new MetaModel.MetaInfo("tt", null, null));
        metamodel.mMetaInfos.put("currencyCode", new MetaModel.MetaInfo("cu", null, null));
        metamodel.mMetaInfos.put("itemPrice", new MetaModel.MetaInfo("ip", null, null));
        metamodel.mMetaInfos.put("itemCode", new MetaModel.MetaInfo("ic", null, null));
        metamodel.mMetaInfos.put("itemName", new MetaModel.MetaInfo("in", null, null));
        metamodel.mMetaInfos.put("itemCategory", new MetaModel.MetaInfo("iv", null, null));
        metamodel.mMetaInfos.put("itemQuantity", new MetaModel.MetaInfo("iq", null, null));
        metamodel.mMetaInfos.put("exDescription", new MetaModel.MetaInfo("exd", null, null));
        formatter = BOOLEAN_FORMATTER;
        metamodel.mMetaInfos.put("exFatal", new MetaModel.MetaInfo("exf", "1", formatter));
        metamodel.mMetaInfos.put("timingVar", new MetaModel.MetaInfo("utv", null, null));
        metamodel.mMetaInfos.put("timingValue", new MetaModel.MetaInfo("utt", null, null));
        metamodel.mMetaInfos.put("timingCategory", new MetaModel.MetaInfo("utc", null, null));
        metamodel.mMetaInfos.put("timingLabel", new MetaModel.MetaInfo("utl", null, null));
        formatter = UP_TO_TWO_DIGIT_FLOAT_FORMATTER;
        metamodel.mMetaInfos.put("sampleRate", new MetaModel.MetaInfo("sf", "100", formatter));
        metamodel.mMetaInfos.put("hitTime", new MetaModel.MetaInfo("ht", null, null));
        metamodel.mMetaInfos.put("customDimension", new MetaModel.MetaInfo("cd", null, null));
        metamodel.mMetaInfos.put("customMetric", new MetaModel.MetaInfo("cm", null, null));
        metamodel.mMetaInfos.put("contentGrouping", new MetaModel.MetaInfo("cg", null, null));
    }


    private class _cls1
        implements MetaModel.Formatter
    {

        public final String format(String s)
        {
            if (Utils.safeParseBoolean(s))
            {
                return "1";
            } else
            {
                return "0";
            }
        }

        _cls1()
        {
        }
    }


    private class _cls2
        implements MetaModel.Formatter
    {

        private final DecimalFormat mFloatFormat = new DecimalFormat("0.##");

        public final String format(String s)
        {
            return mFloatFormat.format(Utils.safeParseDouble(s));
        }

        _cls2()
        {
        }
    }

}
