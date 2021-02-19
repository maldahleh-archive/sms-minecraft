package com.maldahleh.smsminecraft.utils;

import com.maldahleh.smsminecraft.exceptions.InvalidPropertyException;
import lombok.SneakyThrows;
import org.bukkit.configuration.ConfigurationSection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ConfigUtilsTests {
  private final ConfigurationSection configurationSection = mock(ConfigurationSection.class);

  @Test
  @SneakyThrows
  public void returnPropertyWhenPresent() {
    // GIVEN
    String input = "dummyProperty";
    String response = "dummyResponse";

    // WHEN
    doReturn(response).when(configurationSection).getString(input);

    // THEN
    assertEquals(response, ConfigUtils.getProperty(configurationSection, input));
  }

  @Test
  public void throwExceptionWhenNotPresent() {
    // GIVEN
    String input = "dummyProperty";
    String response = "";

    // WHEN
    doReturn(response).when(configurationSection).getString(input);

    // THEN
    Exception e =
        assertThrows(
            InvalidPropertyException.class,
            () -> ConfigUtils.getProperty(configurationSection, input));
    assertEquals("'dummyProperty' is required.", e.getMessage());
  }
}
