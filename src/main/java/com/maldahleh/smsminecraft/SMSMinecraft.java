package com.maldahleh.smsminecraft;

import com.maldahleh.smsminecraft.exceptions.MissingConfigException;
import com.maldahleh.smsminecraft.twilio.TwilioProvider;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class SMSMinecraft extends JavaPlugin {
    private TwilioProvider twilioProvider;

    @Override
    public void onEnable() {
        try {
            twilioProvider = new TwilioProvider(getConfig().getConfigurationSection("twilio"));
        } catch (MissingConfigException e) {
            getLogger().severe("Plugin shutting down; error=" + e.getMessage());
            getPluginLoader().disablePlugin(this);
        }
    }
}
