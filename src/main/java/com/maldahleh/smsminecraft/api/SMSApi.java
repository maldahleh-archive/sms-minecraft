package com.maldahleh.smsminecraft.api;

import com.maldahleh.smsminecraft.twilio.TwilioProvider;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SMSApi {
  private final TwilioProvider twilioProvider;
}
