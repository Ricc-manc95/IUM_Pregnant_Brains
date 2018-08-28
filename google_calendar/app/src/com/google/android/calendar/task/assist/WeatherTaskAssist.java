// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task.assist;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.SpannableString;
import com.google.android.apps.calendar.flair.FlairAllocatorFactory;
import com.google.android.calendar.utils.activity.ActivityUtils;
import com.google.personalization.assist.annotate.AssistType;
import com.google.personalization.assist.annotate.GrammarRuleType;
import com.google.personalization.assist.annotate.TemperatureUnit;
import com.google.personalization.assist.annotate.api.Assistance;
import com.google.personalization.assist.annotate.api.Temperature;
import com.google.personalization.assist.annotate.api.WeatherAssistance;

// Referenced classes of package com.google.android.calendar.task.assist:
//            TaskAssistHolder

public final class WeatherTaskAssist extends TaskAssistHolder
{

    public WeatherTaskAssist(Assistance assistance, GrammarRuleType grammarruletype, String s, String s1)
    {
        super(assistance, grammarruletype, s, s1);
        mAnalyticsLabel = "weather";
    }

    public final String getAssistActionText(Context context)
    {
        context = assistance;
        if (((Assistance) (context)).weatherAssistance_ == null)
        {
            context = WeatherAssistance.DEFAULT_INSTANCE;
        } else
        {
            context = ((Assistance) (context)).weatherAssistance_;
        }
        return ((WeatherAssistance) (context)).forecastText_;
    }

    public final String getAssistInfoText(Context context)
    {
        Object obj = assistance;
        if (((Assistance) (obj)).weatherAssistance_ == null)
        {
            obj = WeatherAssistance.DEFAULT_INSTANCE;
        } else
        {
            obj = ((Assistance) (obj)).weatherAssistance_;
        }
        if ((((WeatherAssistance) (obj)).bitField0_ & 4) == 4)
        {
            obj = assistance;
            if (((Assistance) (obj)).weatherAssistance_ == null)
            {
                obj = WeatherAssistance.DEFAULT_INSTANCE;
            } else
            {
                obj = ((Assistance) (obj)).weatherAssistance_;
            }
            if ((((WeatherAssistance) (obj)).bitField0_ & 8) == 8)
            {
                obj = assistance;
                if (((Assistance) (obj)).weatherAssistance_ == null)
                {
                    obj = WeatherAssistance.DEFAULT_INSTANCE;
                } else
                {
                    obj = ((Assistance) (obj)).weatherAssistance_;
                }
                if ((((WeatherAssistance) (obj)).bitField0_ & 1) == 1)
                {
                    obj = assistance;
                    TemperatureUnit temperatureunit;
                    if (((Assistance) (obj)).weatherAssistance_ == null)
                    {
                        obj = WeatherAssistance.DEFAULT_INSTANCE;
                    } else
                    {
                        obj = ((Assistance) (obj)).weatherAssistance_;
                    }
                    if (((WeatherAssistance) (obj)).currentTemp_ == null)
                    {
                        obj = Temperature.DEFAULT_INSTANCE;
                    } else
                    {
                        obj = ((WeatherAssistance) (obj)).currentTemp_;
                    }
                    temperatureunit = TemperatureUnit.forNumber(((Temperature) (obj)).preferredUnit_);
                    obj = temperatureunit;
                    if (temperatureunit == null)
                    {
                        obj = TemperatureUnit.FAHRENHEIT;
                    }
                    if (obj == TemperatureUnit.CELSIUS)
                    {
                        obj = assistance;
                        String s;
                        if (((Assistance) (obj)).weatherAssistance_ == null)
                        {
                            obj = WeatherAssistance.DEFAULT_INSTANCE;
                        } else
                        {
                            obj = ((Assistance) (obj)).weatherAssistance_;
                        }
                        if (((WeatherAssistance) (obj)).currentTemp_ == null)
                        {
                            obj = Temperature.DEFAULT_INSTANCE;
                        } else
                        {
                            obj = ((WeatherAssistance) (obj)).currentTemp_;
                        }
                        obj = ((Temperature) (obj)).tempC_;
                    } else
                    {
                        obj = assistance;
                        if (((Assistance) (obj)).weatherAssistance_ == null)
                        {
                            obj = WeatherAssistance.DEFAULT_INSTANCE;
                        } else
                        {
                            obj = ((Assistance) (obj)).weatherAssistance_;
                        }
                        if (((WeatherAssistance) (obj)).currentTemp_ == null)
                        {
                            obj = Temperature.DEFAULT_INSTANCE;
                        } else
                        {
                            obj = ((WeatherAssistance) (obj)).currentTemp_;
                        }
                        obj = ((Temperature) (obj)).tempF_;
                    }
                    if (obj != null)
                    {
                        Object obj1 = assistance;
                        if (((Assistance) (obj1)).weatherAssistance_ == null)
                        {
                            obj1 = WeatherAssistance.DEFAULT_INSTANCE;
                        } else
                        {
                            obj1 = ((Assistance) (obj1)).weatherAssistance_;
                        }
                        s = ((WeatherAssistance) (obj1)).currentConditionDesc_;
                        obj1 = assistance;
                        if (((Assistance) (obj1)).weatherAssistance_ == null)
                        {
                            obj1 = WeatherAssistance.DEFAULT_INSTANCE;
                        } else
                        {
                            obj1 = ((Assistance) (obj1)).weatherAssistance_;
                        }
                        return context.getString(0x7f1304bb, new Object[] {
                            s, obj, ((WeatherAssistance) (obj1)).location_
                        });
                    }
                }
            }
        }
        return null;
    }

    protected final AssistType getAssistType()
    {
        return AssistType.URL_ASSIST;
    }

    public final String getDescription(Context context)
    {
        if (getAssistInfoText(context) != null)
        {
            return context.getString(0x7f130177);
        } else
        {
            return null;
        }
    }

    public final String getDisplayText(Context context)
    {
        return getAssistActionText(context);
    }

    public final int getIconId()
    {
        return 0x7f020222;
    }

    public final String getImageUrl()
    {
        return FlairAllocatorFactory.getAssistFlairUrlString("pack");
    }

    public final SpannableString getSecondaryDisplayText(Context context)
    {
        context = getAssistInfoText(context);
        if (context == null)
        {
            return null;
        } else
        {
            return new SpannableString(context);
        }
    }

    public final void gotoAssist(Context context)
    {
        Object obj = assistance;
        if (((Assistance) (obj)).weatherAssistance_ == null)
        {
            obj = WeatherAssistance.DEFAULT_INSTANCE;
        } else
        {
            obj = ((Assistance) (obj)).weatherAssistance_;
        }
        obj = new Intent("android.intent.action.VIEW", Uri.parse(((WeatherAssistance) (obj)).forecastUrl_));
        ActivityUtils.prepareIntentToOpenLink(((Intent) (obj)));
        ((Intent) (obj)).addFlags(0x10000000);
        ((Intent) (obj)).putExtra("com.android.browser.application_id", context.getPackageName());
        startActivity(context, ((Intent) (obj)));
    }
}
