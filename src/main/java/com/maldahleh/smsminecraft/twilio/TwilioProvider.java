package com.maldahleh.smsminecraft.twilio;

import com.maldahleh.smsminecraft.exceptions.InvalidPropertyException;
import com.maldahleh.smsminecraft.twilio.config.TwilioConfig;
import com.twilio.Twilio;
import com.twilio.exception.AuthenticationException;
import com.twilio.type.PhoneNumber;
import org.bukkit.configuration.ConfigurationSection;

public class TwilioProvider {
  private final PhoneNumber origin;

  public TwilioProvider(ConfigurationSection section)
      throws AuthenticationException, InvalidPropertyException {
    TwilioConfig twilioConfig = new TwilioConfig(section);
    Twilio.init(twilioConfig.getSid(), twilioConfig.getAuthToken());

    this.origin = twilioConfig.getOriginNumber();
  }
}
