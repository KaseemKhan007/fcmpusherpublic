package com.machine.fcmpusherkk;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;

public class AppSettings {

    private static SharedPreferences getPrefs(Context paramContext) {
        return PreferenceManager.getDefaultSharedPreferences(paramContext);
    }

    public static void clearPrefs(Context paramContext) {
        SharedPreferences preferences = getPrefs(paramContext);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }

    public static boolean isLogin(Context paramContext) {
        return getPrefs(paramContext).getBoolean("isLogin", false);
    }

    public static void setLogin(Context paramContext, boolean islogin) {
        getPrefs(paramContext).edit().putBoolean("isLogin", islogin).commit();
    }

    public static boolean isSetProfile(Context paramContext) {
        return getPrefs(paramContext).getBoolean("isSetProfile", false);
    }

    public static void setAutoStartEnable(Context paramContext, boolean islogin) {
        getPrefs(paramContext).edit().putBoolean("isEnable", islogin).commit();
    }

    public static boolean isAutoStartEnable(Context paramContext) {
        return getPrefs(paramContext).getBoolean("isEnable", false);
    }

    public static void setProfileOnce(Context paramContext, boolean isSetProfile) {
        getPrefs(paramContext).edit().putBoolean("isSetProfile", isSetProfile).commit();
    }

    public static String getPassword(Context paramContext) {
        return getPrefs(paramContext).getString("password", null);
    }

    public static void setPassword(Context paramContext, String paramString) {
        getPrefs(paramContext).edit().putString("password", paramString).commit();
    }

    public static String getInvitedBy(Context context) {
        return getPrefs(context).getString("InvitedBy", "");
    }

    public static void setInvitedBy(Context context, String paramString) {
        getPrefs(context).edit().putString("InvitedBy", paramString).commit();
    }

    public static String getDeepLink(Context context) {
        return getPrefs(context).getString("DeepLink", "");
    }

    public static void setDeepLink(Context context, String paramString) {
        getPrefs(context).edit().putString("DeepLink", paramString).commit();
    }

    public static String getBlockStatus(Context context) {
        return getPrefs(context).getString("BlockStatus", "");
    }

    public static void setBlockStatus(Context context, String paramString) {
        getPrefs(context).edit().putString("BlockStatus", paramString).commit();
    }


    public static int getNotificationCounter(Context context) {
        return getPrefs(context).getInt("NotificationCounter", 0);
    }

    public static void setNotificationCounter(Context context, int counter) {
        getPrefs(context).edit().putInt("NotificationCounter", counter).commit();
    }


    public static String getUId(Context context) {
        return getPrefs(context).getString("UId", "");
    }

    public static void setUId(Context context, String paramString) {
        getPrefs(context).edit().putString("UId", paramString).commit();
    }


    public static String getFirstName(Context context) {
        return getPrefs(context).getString("firstName", null);
    }

    public static void setFirstName(Context context, String paramString) {
        getPrefs(context).edit().putString("firstName", paramString).commit();
    }

    public static void setStorageLocation(Context context, String paramString) {
        getPrefs(context).edit().putString("storageLocation", paramString).commit();
    }


    public static String getLastName(Context context) {
        return getPrefs(context).getString("lastName", null);
    }

    public static void setLastName(Context context, String paramString) {
        getPrefs(context).edit().putString("lastName", paramString).commit();
    }

    public static String getBio(Context context) {
        return getPrefs(context).getString("bio", null);
    }

    public static void setBio(Context context, String paramString) {
        getPrefs(context).edit().putString("bio", paramString).commit();
    }

    public static String getPhone(Context context) {
        return getPrefs(context).getString("phone", null);
    }

    public static void setPhone(Context context, String paramString) {
        getPrefs(context).edit().putString("phone", paramString).commit();
    }

    public static String getUserPic(Context context) {
        return getPrefs(context).getString("userPic", null);
    }

    public static void setUserPic(Context context, String paramString) {
        getPrefs(context).edit().putString("userPic", paramString).commit();
    }

    public static String getUserEmail(Context context) {
        return getPrefs(context).getString("userEmail", null);
    }

    public static void setUserEmail(Context context, String paramString) {
        getPrefs(context).edit().putString("userEmail", paramString).commit();
    }

    public static void setShopEmail(Context context, String paramString) {
        getPrefs(context).edit().putString("shopEmail", paramString).commit();
    }

    public static String getUserName(Context context) {
        return getPrefs(context).getString("username", null);
    }

    public static void setUserName(Context context, String paramString) {
        getPrefs(context).edit().putString("username", paramString).commit();
    }

    public static String getShopSign(Context context) {
        return getPrefs(context).getString("ShopSign", null);
    }


    public static void setUserToken(Context context, String paramString) {
        getPrefs(context).edit().putString("usertoken", paramString).commit();
    }

    public static void setShopFirstTime(Context paramContext, boolean b) {
        getPrefs(paramContext).edit().putBoolean("isShopCreated", b).commit();
    }

    public static String getPlanOrderId(Context context) {
        return getPrefs(context).getString("planOrderId", "");
    }

    public static void setPlanOrderId(Context context, String paramString) {
        getPrefs(context).edit().putString("planOrderId", paramString).commit();
    }

    public static String getPurchaseDate(Context context) {
        return getPrefs(context).getString("purchaseDate", "");
    }

    public static void setPurchaseDate(Context context, String paramString) {
        getPrefs(context).edit().putString("purchaseDate", paramString).commit();
    }

    public static String getPurchaseExpiryDate(Context context) {
        return getPrefs(context).getString("purchaseExpiryDate", "");
    }

    public static void setPurchaseExpiryDate(Context context, String paramString) {
        getPrefs(context).edit().putString("purchaseExpiryDate", paramString).commit();
    }

    public static String getPurchaseToken(Context context) {
        return getPrefs(context).getString("planId", "");
    }

    public static void setPurchaseToken(Context context, String paramString) {
        getPrefs(context).edit().putString("planId", paramString).commit();
    }

    public static String getUSDCurrencyRate(Context context) {
        return getPrefs(context).getString("USDCurrencyRate", "");
    }

    public static void setUSDCurrencyRate(Context context, String paramString) {
        getPrefs(context).edit().putString("USDCurrencyRate", paramString).commit();
    }


    public static int getIsExpired(Context context) {
        return getPrefs(context).getInt("isExpired", 0);
    }

    public static void setIsExpired(Context context, int paramString) {
        getPrefs(context).edit().putInt("isExpired", paramString).commit();
    }


    public static void setCurrency(Context context, String paramString) {
        getPrefs(context).edit().putString("Currency", paramString).commit();

    }

    public static String getCurrency(Context context) {
        return getPrefs(context).getString("Currency", "INR");
    }

    public static void setGoogleAccount(Context context, String paramString) {
        getPrefs(context).edit().putString("Currency", paramString).commit();
    }

    public static String getGoogleAccount(Context context) {
        return getPrefs(context).getString("Currency", "INR");
    }

    public static String getUserEmailCloudVision(Context context) {
        return getPrefs(context).getString("userEmailCloudVision", null);
    }

    public static void setUserEmailCloudVision(Context context, String paramString) {
        getPrefs(context).edit().putString("userEmailCloudVision", paramString).commit();
    }

    public static boolean isTokenGet(Context context) {
        return getPrefs(context).getBoolean("isTokenGet", false);
    }

    public static void setIsTokenGet(Context context, boolean paramString) {
        getPrefs(context).edit().putBoolean("isTokenGet", paramString).commit();
    }
    public static boolean isAppMaintainDownload(Context context) {
        return getPrefs(context).getBoolean("isDownload", false);
    }

    public static void setAppMaintainDownload(Context context, boolean paramString) {
        getPrefs(context).edit().putBoolean("isDownload", paramString).commit();
    }
    public static boolean isAppMaintainLike(Context context) {
        return getPrefs(context).getBoolean("isLike", false);
    }

    public static void setAppMaintainLike(Context context, boolean paramString) {
        getPrefs(context).edit().putBoolean("isLike", paramString).commit();
    }
    public static boolean isAppMaintainPostUpload(Context context) {
        return getPrefs(context).getBoolean("isPostUpload", false);
    }

    public static void setAppMaintainPostUpload(Context context, boolean paramString) {
        getPrefs(context).edit().putBoolean("isPostUpload", paramString).commit();
    }
    public static boolean isAppMaintainPostShow(Context context) {
        return getPrefs(context).getBoolean("isPostShow", false);
    }

    public static void setAppMaintainPostShow(Context context, boolean paramString) {
        getPrefs(context).edit().putBoolean("isPostShow", paramString).commit();
    }
    public static boolean isAppMaintainPayRequest(Context context) {
        return getPrefs(context).getBoolean("isPayRequest", false);
    }

    public static void setAppMaintainPayRequest(Context context, boolean paramString) {
        getPrefs(context).edit().putBoolean("isPayRequest", paramString).commit();
    }
}
