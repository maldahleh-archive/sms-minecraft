package com.maldahleh.smsminecraft.twilio;

import com.maldahleh.smsminecraft.exceptions.InvalidPropertyException;
import com.maldahleh.smsminecraft.utils.ConfigUtils;
import com.maldahleh.smsminecraft.utils.Constants;
import com.twilio.Twilio;
import com.twilio.exception.AuthenticationException;
import org.bukkit.configuration.ConfigurationSection;

public class TwilioProvider {
  public TwilioProvider(ConfigurationSection section)
      throws AuthenticationException, InvalidPropertyException {
    Twilio.init(
        ConfigUtils.getProperty(section, Constants.CONFIG_SID),
        ConfigUtils.getProperty(section, Constants.CONFIG_TOKEN));
  }
}
