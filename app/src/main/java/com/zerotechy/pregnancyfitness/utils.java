package com.zerotechy.pregnancyfitness;

public class utils {

    public static String URL= "https://pregnancy.zerotechy.com/wp-json/wp/v2/posts";
    public static String adId="IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID";
    public static String policyURL="https://www.google.com";

    public static String getPolicyURL() {
        return policyURL;
    }

    public static String getInterstitialAdID() {
        return interstitialAdID;
    }

    public static String interstitialAdID="YOUR_PLACEMENT_ID";
    public static String getAdId() {
        return adId;
    }



    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
