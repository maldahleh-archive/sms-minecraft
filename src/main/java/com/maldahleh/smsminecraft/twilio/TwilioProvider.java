package com.maldahleh.smsminecraft.twilio;

import com.maldahleh.smsminecraft.exceptions.InvalidPropertyException;
import com.maldahleh.smsminecraft.utils.ConfigUtils;
import com.twilio.Twilio;
import org.bukkit.configuration.ConfigurationSection;

public class TwilioProvider {
    public TwilioProvider(ConfigurationSection section) throws InvalidPropertyException {
        Twilio.init(
                ConfigUtils.getProperty(section, "sid"),
                ConfigUtils.getProperty(section, "auth-token")
        );
    }
}
