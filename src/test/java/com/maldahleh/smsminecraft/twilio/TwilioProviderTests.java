package com.maldahleh.smsminecraft.twilio;

import com.maldahleh.smsminecraft.exceptions.InvalidPropertyException;
import com.maldahleh.smsminecraft.utils.Constants;
import com.twilio.Twilio;
import com.twilio.exception.AuthenticationException;
import lombok.SneakyThrows;
import org.bukkit.configuration.ConfigurationSection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockedStatic;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
public class TwilioProviderTests {
  private final ConfigurationSection configurationSection = mock(ConfigurationSection.class);

  @Test
  @SneakyThrows
  @PrepareForTest(Twilio.class)
  public void twilioProviderInstantiatedCorrectly() {
    // GIVEN
    String sid = "sid";
    String authToken = "authToken";
    String phoneNumber = "+19050000000";

    // WHEN
    when(configurationSection.getString(Constants.CONFIG_SID)).thenReturn(sid);
    when(configurationSection.getString(Constants.CONFIG_TOKEN)).thenReturn(authToken);
    when(configurationSection.getString(Constants.ORIGIN_NUMBER)).thenReturn(phoneNumber);
    PowerMockito.mockStatic(Twilio.class);

    // THEN
    new TwilioProvider(configurationSection);
  }

  @Test
  public void missingCredentialsThrowsInvalidPropertyException() {
    // GIVEN
    String sid = "sid";
    String authToken = "";
    String phoneNumber = "+19050000000";

    // WHEN
    when(configurationSection.getString(Constants.CONFIG_SID)).thenReturn(sid);
    when(configurationSection.getString(Constants.CONFIG_TOKEN)).thenReturn(authToken);
    when(configurationSection.getString(Constants.ORIGIN_NUMBER)).thenReturn(phoneNumber);

    // THEN
    Exception exception =
        assertThrows(
            InvalidPropertyException.class, () -> new TwilioProvider(configurationSection));
    assertEquals("'auth-token' is required.", exception.getMessage());
  }

  @Test
  public void invalidCredentialsThrowsAuthenticationException() {
    // GIVEN
    String sid = "sid";
    String authToken = "authToken";
    String phoneNumber = "+19050000000";
    String invalidMessage = "invalid login";

    // WHEN
    when(configurationSection.getString(Constants.CONFIG_SID)).thenReturn(sid);
    when(configurationSection.getString(Constants.CONFIG_TOKEN)).thenReturn(authToken);
    when(configurationSection.getString(Constants.ORIGIN_NUMBER)).thenReturn(phoneNumber);

    try (MockedStatic<Twilio> twilio = mockStatic(Twilio.class)) {
      twilio
          .when(() -> Twilio.init(sid, authToken))
          .thenThrow(new AuthenticationException(invalidMessage));

      // THEN
      Exception exception =
          assertThrows(
              AuthenticationException.class, () -> new TwilioProvider(configurationSection));
      assertEquals("invalid login", exception.getMessage());
    }
  }
}
