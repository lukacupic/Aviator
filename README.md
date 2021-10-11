**BUILDING**

OpenSky API is not yet on Maven Central. Thus, it's needed to install it locally:

```
git clone https://github.com/openskynetwork/opensky-api.git
cd opensky-api/java
mvn install
```

**CONFIGURATION**

A credentials file called `credentials.config` should be manually added in the `src/main/resources` folder. The contents of the file are as follows:

```
OPENSKY_USERNAME=<OPENSKY_USERNAME>
OPENSKY_PASSWORD=<OPENSKY_PASSWORD>

GMAIL_USERNAME=<GMAIL_EMAIL>
GMAIL_PASSWORD=<GMAIL_APP_PASSWORD>
```

All the `<...>` values should be replaced with actual values.

**RUNNING**

After running, the app will start sending real-time updates via the configured email. That's it!
