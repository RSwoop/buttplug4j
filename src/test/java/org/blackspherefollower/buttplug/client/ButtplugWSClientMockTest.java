package org.blackspherefollower.buttplug.client;

import org.blackspherefollower.buttplug.client.client.ButtplugClientDevice;
import org.blackspherefollower.buttplug.client.client.ButtplugWSClient;
import org.blackspherefollower.buttplug.protocol.messages.Ok;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ButtplugWSClientMockTest {

    @Disabled
    @Test
    public void TestConnect() throws Exception {
        ButtplugWSClient client = new ButtplugWSClient("Java Test");
        client.connect(new URI("ws://localhost:12345/buttplug"));
        client.startScanning();

        Thread.sleep(5000);
        client.requestDeviceList();
        for (ButtplugClientDevice dev : client.getDevices()) {
            if (dev.getScalarVibrateCount() > 0) {
                dev.sendScalarVibrateCmd(0.5).get();
            }
        }

        Thread.sleep(1000);
        assertTrue(client.stopAllDevices());

        client.disconnect();
    }
}