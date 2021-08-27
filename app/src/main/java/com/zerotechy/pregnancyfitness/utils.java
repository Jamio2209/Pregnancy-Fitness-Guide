package com.zerotechy.pregnancyfitness;

public class utils {

    public static String URL= "https://pregnancy.zerotechy.com/wp-json/wp/v2/posts?categories=2";
    public static String adId="b195f8dd8ded45fe847ad89ed1d016da";
    public static String policyURL="https://pregnancy.zerotechy.com/privacy-policy/";

    public static String getPolicyURL() {
        return policyURL;
    }

    public static String getInterstitialAdID() {
        return interstitialAdID;
    }

    public static String interstitialAdID="1478259299187150_1478278312518582";
    public static String getAdId() {
        return adId;
    }



    public static  String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
