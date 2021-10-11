## OpenSkyDemo

A real-time airspace information tracker. The application sends real-time updates whenever an aeroplane enters a region pre-defined by GPS coordiates.

### SETUP

OpenSky API is not yet on Maven Central. Thus, to use it, it needs to be installed locally:

```
git clone https://github.com/openskynetwork/opensky-api.git
cd opensky-api/java
mvn install
```

### CONFIGURATION

A credentials file called `credentials.config` should be manually added to the `src/main/resources` folder. The contents of the file should be as follows:

```
OPENSKY_USERNAME=<OPENSKY_USERNAME>
OPENSKY_PASSWORD=<OPENSKY_PASSWORD>

GMAIL_USERNAME=<GMAIL_EMAIL>
GMAIL_PASSWORD=<APP_PASSWORD>
```

All the `<...>` values should be replaced with actual values. `<APP_PASSWORD>` is a security token generated from the Google account management dashboard.

### RUNNING

After running, the app will start sending real-time updates to the configured email. That's it!
