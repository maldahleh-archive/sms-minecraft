package com.maldahleh.smsminecraft.utils;

import com.maldahleh.smsminecraft.exceptions.MissingConfigException;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang.StringUtils;
import org.bukkit.configuration.ConfigurationSection;

@UtilityClass
public class ConfigUtils {
    public String getProperty(ConfigurationSection section, String property) throws MissingConfigException {
        String value = section.getString(property);
        if (StringUtils.isEmpty(value)) {
            throw new MissingConfigException("'" + property + "' is required.");
        }

        return value;
    }
}
