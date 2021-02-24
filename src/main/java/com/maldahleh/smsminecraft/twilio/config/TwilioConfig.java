package com.maldahleh.smsminecraft.twilio.config;

import com.maldahleh.smsminecraft.exceptions.InvalidPropertyException;
import com.maldahleh.smsminecraft.utils.ConfigUtils;
import com.maldahleh.smsminecraft.utils.Constants;
import com.twilio.type.PhoneNumber;
import lombok.Getter;
import org.bukkit.configuration.ConfigurationSection;

@Getter
public class TwilioConfig {
  private final String sid;
  private final String authToken;

  private final PhoneNumber originNumber;

  public TwilioConfig(ConfigurationSection section) throws InvalidPropertyException {
    this.sid = ConfigUtils.getProperty(section, Constants.CONFIG_SID);
    this.authToken = ConfigUtils.getProperty(section, Constants.CONFIG_TOKEN);
    this.originNumber = buildPhoneNumber(section, Constants.ORIGIN_NUMBER);
  }

  private PhoneNumber buildPhoneNumber(ConfigurationSection section, String path)
      throws InvalidPropertyException {
    String phoneNumber = ConfigUtils.getProperty(section, path);
    return buildPhoneNumber(phoneNumber);
  }

  private PhoneNumber buildPhoneNumber(String phoneNumber) {
    return new PhoneNumber(phoneNumber);
  }
}
