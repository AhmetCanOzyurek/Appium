package utils;

public enum Devices {
    Pixel_2("emulator-5554",
            "12",
            "pixel 2",
            "Android"
    );


    Devices(String udid, String version, String deviceName, String platformName) {
        this.udid = udid;
        this.version = version;
        this.deviceName = deviceName;
        this.platformName = platformName;
    }

    public String getUdid() {
        return udid;
    }

    public String getVersion() {
        return version;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getPlatformName() {
        return platformName;
    }

    private String udid;
    private String version;
    private String deviceName;
    private String platformName;

}
