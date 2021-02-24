package com.maldahleh.smsminecraft.twilio.config;

import com.maldahleh.smsminecraft.exceptions.InvalidPropertyException;
import com.maldahleh.smsminecraft.utils.Constants;
import lombok.SneakyThrows;
import org.bukkit.configuration.ConfigurationSection;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

public class TwilioConfigTests {
  private final ConfigurationSection configurationSection = mock(ConfigurationSection.class);

  @Test
  @SneakyThrows
  public void twilioConfigBuildsSuccessfully() {
    // GIVEN
    String sid = "sid";
    String authToken = "authToken";

    // WHEN
    when(configurationSection.getString(Constants.CONFIG_SID)).thenReturn(sid);
    when(configurationSection.getString(Constants.CONFIG_TOKEN)).thenReturn(authToken);
    when(configurationSection.getString(Constants.ORIGIN_NUMBER)).thenReturn("+10000000000");

    // THEN
    TwilioConfig twilioConfig = new TwilioConfig(configurationSection);
    assertEquals("sid", twilioConfig.getSid());
    assertEquals("authToken", twilioConfig.getAuthToken());
    assertEquals("+10000000000", twilioConfig.getOriginNumber().toString());
  }

  @Test
  public void missingCredentialsThrowsInvalidPropertyException() {
    // GIVEN
    String sid = "sid";
    String authToken = "";

    // WHEN
    when(configurationSection.getString(Constants.CONFIG_SID)).thenReturn(sid);
    when(configurationSection.getString(Constants.CONFIG_TOKEN)).thenReturn(authToken);

    // THEN
    Exception exception =
        assertThrows(InvalidPropertyException.class, () -> new TwilioConfig(configurationSection));
    assertEquals("'auth-token' is required.", exception.getMessage());
  }
}
