package com.maldahleh.smsminecraft.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConstantsTests {
  @Test
  public void testConstants() {
    assertEquals("sid", Constants.CONFIG_SID);
    assertEquals("auth-token", Constants.CONFIG_TOKEN);
    assertEquals("origin-number", Constants.ORIGIN_NUMBER);
  }
}
