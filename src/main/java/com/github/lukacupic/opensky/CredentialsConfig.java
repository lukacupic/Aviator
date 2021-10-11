package com.github.lukacupic.opensky;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CredentialsConfig {

    private static final String FILE = "credentials.config";
    private static final Credential[] CREDENTIALS = com.github.lukacupic.opensky.Credential.values();

    private Map<String, String> map;

    public CredentialsConfig() throws IOException {
        map = getCredentials();
    }

    public Map<String, String> getCredentials() throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream(FILE);
        Properties config = new Properties();

        if (is != null) {
            config.load(is);
        } else {
            throw new FileNotFoundException("Credentials file '" + FILE + "' not found");
        }

        Map<String, String> map = new HashMap<>();
        for (Credential c : CREDENTIALS) {
            map.put(c.name(), config.getProperty(c.name()));
        }
        return map;
    }

    public String getCredential(Credential c) {
        String name = c.name();
        if (!map.containsKey(name)) throw new IllegalArgumentException("No matching credential for " + name);

        return map.get(name);
    }
}