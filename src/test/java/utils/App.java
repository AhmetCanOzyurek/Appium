package utils;

public enum App {
    Phone("com.android.dialer",
            "com.android.dialer.main.impl.MainActivity"
    );


    App(String appPackage, String appActivity) {
        this.appPackage = appPackage;
        this.appActivity = appActivity;
    }

    public String getAppPackage() {
        return appPackage;
    }

    public String getAppActivity() {
        return appActivity;
    }

    String appPackage;
    String appActivity;



}
