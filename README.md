## Aviator

A real-time airspace information tracker. The application sends real-time updates whenever an aeroplane enters a region pre-defined by GPS coordiates.

### SETUP

This application uses OpenSky. Since the OpenSky API is not yet on Maven Central, it needs to be installed locally:

```
cd ~
git clone https://github.com/openskynetwork/opensky-api.git
cd opensky-api/java
mvn install
cd ../.. && rm -r opensky-api #Optional; remove the dowloaded repository
```

### CONFIGURATION

A credentials file called `credentials.config` should be manually added to the `src/main/resources/` folder. The contents of the file should be as follows:

```
OPENSKY_USERNAME=<OPENSKY_USERNAME>
OPENSKY_PASSWORD=<OPENSKY_PASSWORD>

GMAIL_USERNAME=<GMAIL_EMAIL_ADDRESS>
GMAIL_PASSWORD=<APP_PASSWORD>

BOTTOM_LEFT_LATITUDE=<BOTTOM_LEFT_LATITUDE>
BOTTOM_LEFT_LONGITUDE=<BOTTOM_LEFT_LONGITUDE>
TOP_RIGHT_LATITUDE=<TOP_RIGHT_LATITUDE>
TOP_RIGHT_LONGITUDE=<TOP_RIGHT_LONGITUDE>
```

All the `<...>` values should be replaced with actual values. `<APP_PASSWORD>` is a security token generated from the Google account management dashboard. The tracked region is defined by providing bottom left and top right coordinates in terms of longitude and latitude.

### RUNNING

After running, the app will start sending real-time updates to the configured email. That's it!
