package com.github.lukacupic.openskydemo;

import org.opensky.api.OpenSkyApi;
import org.opensky.model.StateVector;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Main {

    private static EmailSender sender;
    private static Set<String> knownCallsigns = new HashSet<>();

    public static void main(String[] args) throws IOException, MessagingException, InterruptedException {
        CredentialsConfig cc = new CredentialsConfig();

        sender = new EmailSender(cc.getCredential(Credential.GMAIL_USERNAME), cc.getCredential(Credential.GMAIL_USERNAME), cc);

        OpenSkyApi api = new OpenSkyApi(cc.getCredential(Credential.OPENSKY_USERNAME), cc.getCredential(Credential.OPENSKY_PASSWORD));

        double latitude1 = Double.parseDouble(cc.getCredential(Credential.BOTTOM_LEFT_LATITUDE));
        double longitude1 = Double.parseDouble(cc.getCredential(Credential.TOP_RIGHT_LATITUDE));

        double latitude2 = Double.parseDouble(cc.getCredential(Credential.BOTTOM_LEFT_LATITUDE));
        double longitude2 = Double.parseDouble(cc.getCredential(Credential.TOP_RIGHT_LONGITUDE));

        OpenSkyApi.BoundingBox box = new OpenSkyApi.BoundingBox(latitude1, longitude1, latitude2, longitude2);

        while (true) {
            Collection<StateVector> states = api.getStates(0, null, box).getStates();

            if (states == null || states.size() == 0) {
                System.out.println("No planes around!");

            } else {
                System.out.println(states.size() + " planes found.");
                
                for (StateVector state : states) {
                    if (!knownCallsigns.contains(state.getCallsign())) {
                        String info = getAircraftInfo(state);
                        knownCallsigns.add(state.getCallsign());

                        sendEmail(info);
                        System.out.println("Sent aircraft update email!");
                    }
                }
            }

            Thread.sleep(10000);
        }
    }

    private static void sendEmail(String text) throws MessagingException {
        sender.send("Aircraft Update", text);
    }

    private static String getAircraftInfo(StateVector state) {
        String callsign = state.getCallsign().trim();
        double heading = state.getHeading();
        double velocity = state.getVelocity();
        double lastContact = state.getLastContact();
        double seconds = System.currentTimeMillis() / 1000.0 - lastContact;

        return String.format("Aircraft with callsign %s has entered the Istrian air space. " +
                        "It is heading %.2f degrees off North at %s m/s and was last heard from %.2f seconds ago.\n",
                callsign, heading, velocity, seconds);
    }
}
