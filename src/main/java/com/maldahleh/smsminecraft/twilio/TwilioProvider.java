package com.maldahleh.smsminecraft.twilio;

import com.maldahleh.smsminecraft.exceptions.MissingConfigException;
import com.maldahleh.smsminecraft.utils.ConfigUtils;
import com.twilio.Twilio;
import org.bukkit.configuration.ConfigurationSection;

public class TwilioProvider {
    public TwilioProvider(ConfigurationSection section) throws MissingConfigException {
        Twilio.init(
                ConfigUtils.getProperty(section, "sid"),
                ConfigUtils.getProperty(section, "auth-token")
        );
    }
}
