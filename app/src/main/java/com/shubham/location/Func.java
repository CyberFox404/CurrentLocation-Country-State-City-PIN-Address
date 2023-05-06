package com.shubham.location;

import static android.content.ContentValues.TAG;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellLocation;
import android.telephony.CellSignalStrengthCdma;
import android.telephony.CellSignalStrengthGsm;
import android.telephony.CellSignalStrengthLte;
import android.telephony.CellSignalStrengthWcdma;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;

import androidx.core.app.ActivityCompat;

//import org.jsoup.Connection;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

public class Func {

    Context context;
    Activity activity;


    // https://stackoverflow.com/questions/12491256/getting-cid-lac-and-signal-strength-of-all-cell-towers-in-range
    private int NET_TYPE = 0;
    private final int GSM = 1;
    private final int WCDA = 2;
    private final int LTE = 3;
    private final int CDMA = 4;
    private int CID = 0;
    private int MNC = 0;
    private int MCC = 0;

    private int LAC = 0;

    private int SID = 0;

    TelephonyManager tm;

    public Func(Context context) {
        this.context = context;
        this.activity = (Activity) context;
    }

    // https://stackoverflow.com/questions/43886256/getallcellinfo-returns-an-empty-list-in-huawei-honor-7
    public void loadCellInfoRoute() {
        String mTAG = "FAB";

        try {
            tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            Object subscriptionManager = context.getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Log.e(mTAG + " ", "return4");
            return;
        }
        int networkTypeInt = tm.getNetworkType();

        String networkOperator = "";
        if (tm.getPhoneType() == TelephonyManager.PHONE_TYPE_GSM) {
            networkOperator = tm.getNetworkOperator();
        }
        Log.e(mTAG + " networkOperator", networkOperator);


        //String networkOperator = tm.getNetworkOperator();


        if (networkOperator.length() > 3) {
            MCC = Integer.parseInt(networkOperator.substring(0, 3));
            MNC = Integer.parseInt(networkOperator.substring(3));
            Log.e(mTAG + " ", "networkOperator.length");
        }


        //CellLocation primaryLocation = tm.getCellLocation();
        //if (primaryLocation != null) {
         //   int primaryCellId = Integer.parseInt(primaryLocation.toString().split(",")[1]);
         //   int trackingAreaCode = Integer.parseInt(primaryLocation.toString().split(",")[0].replace("[", ""));

        //    Log.e(mTAG + " primaryLocation", String.valueOf(primaryLocation));
        //    Log.e(mTAG + " primaryCellId", String.valueOf(primaryCellId));
        //    Log.e(mTAG + " trackingAreaCode", String.valueOf(trackingAreaCode));


        //} else {
         //   Log.d(TAG, "updateCurrentCell: not even with getCellLocation");
        //}


        int lCurrentApiVersion = android.os.Build.VERSION.SDK_INT;

        try {



            List<CellInfo> cellInfoList = tm.getAllCellInfo();
            //cellInfoList = tm.getNeighboringCellInfo();
            //List<CellInfo> cellInfoList = tm.getAllCellInfo();

            Log.e(mTAG + " cellInfoList ", String.valueOf(cellInfoList.size()));

            //Log.e(mTAG + " cellInfoList ", cellInfoList.length());

            Log.e(mTAG + " cellInfoList != null ", "return");


            for (CellInfo info : cellInfoList) {
                if (info.isRegistered()) {
                Log.e(mTAG + " CellInfo ", String.valueOf(info));

                //Network Type
                //pDevice.mCell.setNetType(tm.getNetworkType());

                //if (info instanceof CellInfoGsm) {
                if (info.getClass().equals(CellInfoGsm.class)) {


                    Log.e(mTAG + " info ", "CellInfoGsm");

                   // final CellSignalStrengthGsm gsm = ((CellInfoGsm) info)
                    //        .getCellSignalStrength();
                    final CellIdentityGsm identityGsm = ((CellInfoGsm) info)
                            .getCellIdentity();

                    //identityGsm.getCid();
                    //identityGsm.getLac();
                    //Signal Strength
                    //pDevice.mCell.setDBM(gsm.getDbm()); // [dBm]
                    //Cell Identity
                    //pDevice.mCell.setCID(identityGsm.getCid());
                    //pDevice.mCell.setMCC(identityGsm.getMcc());
                    //pDevice.mCell.setMNC(identityGsm.getMnc());
                    // pDevice.mCell.setLAC(identityGsm.getLac());

                    NET_TYPE = GSM;
                    CID = identityGsm.getCid();
                    MNC = identityGsm.getMnc();
                    MCC = identityGsm.getMcc();
                    LAC = identityGsm.getLac();

                } else if (info.getClass().equals(CellInfoCdma.class)) {
               // } else if (info instanceof CellInfoCdma) {

                    Log.e(mTAG + " info ", "CellInfoCdma");
                    //final CellSignalStrengthCdma cdma = ((CellInfoCdma) info)
                    //        .getCellSignalStrength();
                    final CellIdentityCdma identityCdma = ((CellInfoCdma) info)
                            .getCellIdentity();

                    NET_TYPE = CDMA;
                    CID = identityCdma.getBasestationId();
                    MNC = identityCdma.getSystemId();
                    LAC = identityCdma.getNetworkId();
                    SID = identityCdma.getSystemId();

                    //identityCdma.getLatitude();
                    //identityCdma.getLatitude();
                    //Signal Strength
                    // pDevice.mCell.setDBM(cdma.getDbm());
                    //Cell Identity
                    // pDevice.mCell.setCID(identityCdma.getBasestationId());
                    //pDevice.mCell.setMNC(identityCdma.getSystemId());
                    //pDevice.mCell.setLAC(identityCdma.getNetworkId());
                    // pDevice.mCell.setSID(identityCdma.getSystemId());

                } else if (info.getClass().equals(CellInfoLte.class)) {
                //} else if (info instanceof CellInfoLte) {
                    Log.e(mTAG + " info ", "CellInfoLte");
                    //final CellSignalStrengthLte lte = ((CellInfoLte) info)
                           // .getCellSignalStrength();
                    final CellIdentityLte identityLte = ((CellInfoLte) info)
                            .getCellIdentity();
                    //Signal Strength
                    //pDevice.mCell.setDBM(lte.getDbm());
                    //pDevice.mCell.setTimingAdvance(lte.getTimingAdvance());
                    //Cell Identity
                    //pDevice.mCell.setMCC(identityLte.getMcc());
                    //pDevice.mCell.setMNC(identityLte.getMnc());
                    //pDevice.mCell.setCID(identityLte.getCi());

                    //CellLocationLte cellLocation = (CellLocationLte) tm.getCellLocation();

                    NET_TYPE = LTE;
                    CID = identityLte.getCi();
                    MNC = identityLte.getMnc();
                    MCC = identityLte.getMcc();
                    LAC = identityLte.getTac();
                    //MCC = identityLte.get
                    //MCC = identityLte.get();
                    //MCC = identityLte.get
                    //SID = identityLte.get

                //} else if  (lCurrentApiVersion >= Build.VERSION_CODES.JELLY_BEAN_MR2 &&
                        //info instanceof CellInfoWcdma) {.
                } else if (info.getClass().equals(CellInfoWcdma.class)) {
               // } else if  (info instanceof CellInfoWcdma) {

                    Log.e(mTAG + " info ", "CellInfoWcdma");
                    //final CellSignalStrengthWcdma wcdma = ((CellInfoWcdma) info)
                    //        .getCellSignalStrength();
                    final CellIdentityWcdma identityWcdma = ((CellInfoWcdma) info)
                            .getCellIdentity();


                    NET_TYPE = WCDA;
                    CID = identityWcdma.getCid();
                    MNC = identityWcdma.getMnc();
                    MCC = identityWcdma.getMcc();
                    LAC = identityWcdma.getLac();
                    //LAC = identityWcdma.getCellIdentity()



                    //Signal Strength
                    //pDevice.mCell.setDBM(wcdma.getDbm());
                    //Cell Identity
                    //pDevice.mCell.setLAC(identityWcdma.getLac());
                    //pDevice.mCell.setMCC(identityWcdma.getMcc());
                    //pDevice.mCell.setMNC(identityWcdma.getMnc());
                    // pDevice.mCell.setCID(identityWcdma.getCid());
                    // pDevice.mCell.setPSC(identityWcdma.getPsc());

                } else {
                    Log.e(mTAG + " info ", "NONE");
                    Log.i(TAG, mTAG + "Unknown type of cell signal! "
                            + "ClassName: " + info.getClass().getSimpleName()
                            + " ToString: " + info.toString());
                }

                //return int[] {NET_TYPE, CID};

                } else {

                    Log.e(mTAG + " rrrrrrrl ", "return");
                }


            }
            Log.e(mTAG + " cellInfoList: ", String.valueOf(cellInfoList));

        } catch (NullPointerException npe) {
            Log.e(TAG, mTAG + "loadCellInfo: Unable to obtain cell signal information: ", npe);
        } catch (SecurityException securityException)

    {
        Log.e( TAG, "SecurityException when getCellLocation is called ", securityException);
    } catch (Exception e){
            Log.e(mTAG + "e3: ", String.valueOf(e));

        }
        Log.e(mTAG + "NET_TYPE: ", String.valueOf(NET_TYPE));
        Log.e(mTAG + "CID: ", String.valueOf(CID));
        Log.e(mTAG + "MNC: ", String.valueOf(MNC));
        Log.e(mTAG + "MCC: ", String.valueOf(MCC));
        Log.e(mTAG + "LAC: ", String.valueOf(LAC));

        //NET_TYPE = GSM;
       // CID = identityGsm.getCid();
       // MNC = identityGsm.getMnc();
       // MCC = identityGsm.getMcc();
       // LAC = identityGsm.getLac();

    }

    public void loadCellInfo(TelephonyManager tm) {
        int lCurrentApiVersion = android.os.Build.VERSION.SDK_INT;
        String mTAG = null;

        try {

            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            List<CellInfo> cellInfoList = tm.getAllCellInfo();
            if (cellInfoList != null) {
                for (final CellInfo info : cellInfoList) {

                    //Network Type
                    //pDevice.mCell.setNetType(tm.getNetworkType());

                    if (info instanceof CellInfoGsm) {
                        final CellSignalStrengthGsm gsm = ((CellInfoGsm) info)
                                .getCellSignalStrength();
                        final CellIdentityGsm identityGsm = ((CellInfoGsm) info)
                                .getCellIdentity();

                        //identityGsm.getCid();
                        //identityGsm.getLac();
                        //Signal Strength
                        //pDevice.mCell.setDBM(gsm.getDbm()); // [dBm]
                        //Cell Identity
                        //pDevice.mCell.setCID(identityGsm.getCid());
                        //pDevice.mCell.setMCC(identityGsm.getMcc());
                        //pDevice.mCell.setMNC(identityGsm.getMnc());
                        // pDevice.mCell.setLAC(identityGsm.getLac());

                        NET_TYPE = GSM;
                        CID = identityGsm.getCid();
                        MNC = identityGsm.getMnc();
                        MCC = identityGsm.getMcc();
                        LAC = identityGsm.getLac();

                    } else if (info instanceof CellInfoCdma) {
                        final CellSignalStrengthCdma cdma = ((CellInfoCdma) info)
                                .getCellSignalStrength();
                        final CellIdentityCdma identityCdma = ((CellInfoCdma) info)
                                .getCellIdentity();

                        NET_TYPE = CDMA;
                        CID = identityCdma.getBasestationId();
                        MNC = identityCdma.getSystemId();
                        LAC = identityCdma.getNetworkId();
                        SID = identityCdma.getSystemId();

                        //identityCdma.getLatitude();
                        //identityCdma.getLatitude();
                        //Signal Strength
                        // pDevice.mCell.setDBM(cdma.getDbm());
                        //Cell Identity
                        // pDevice.mCell.setCID(identityCdma.getBasestationId());
                        //pDevice.mCell.setMNC(identityCdma.getSystemId());
                        //pDevice.mCell.setLAC(identityCdma.getNetworkId());
                        // pDevice.mCell.setSID(identityCdma.getSystemId());

                    } else if (info instanceof CellInfoLte) {
                        final CellSignalStrengthLte lte = ((CellInfoLte) info)
                                .getCellSignalStrength();
                        final CellIdentityLte identityLte = ((CellInfoLte) info)
                                .getCellIdentity();
                        //Signal Strength
                        //pDevice.mCell.setDBM(lte.getDbm());
                        //pDevice.mCell.setTimingAdvance(lte.getTimingAdvance());
                        //Cell Identity
                        //pDevice.mCell.setMCC(identityLte.getMcc());
                        //pDevice.mCell.setMNC(identityLte.getMnc());
                        //pDevice.mCell.setCID(identityLte.getCi());

                        NET_TYPE = LTE;
                        CID = identityLte.getCi();
                        MNC = identityLte.getMnc();
                        MCC = identityLte.getMcc();
                        //MCC = identityLte.get();
                        //MCC = identityLte.get
                        //SID = identityLte.getSystemId();

                    } else if  (lCurrentApiVersion >= Build.VERSION_CODES.JELLY_BEAN_MR2 &&
                            info instanceof CellInfoWcdma) {
                        final CellSignalStrengthWcdma wcdma = ((CellInfoWcdma) info)
                                .getCellSignalStrength();
                        final CellIdentityWcdma identityWcdma = ((CellInfoWcdma) info)
                                .getCellIdentity();


                        NET_TYPE = WCDA;
                        CID = identityWcdma.getCid();
                        MNC = identityWcdma.getMnc();
                        MCC = identityWcdma.getMcc();
                        LAC = identityWcdma.getLac();
                        //LAC = identityWcdma.getCellIdentity()



                        //Signal Strength
                        //pDevice.mCell.setDBM(wcdma.getDbm());
                        //Cell Identity
                        //pDevice.mCell.setLAC(identityWcdma.getLac());
                        //pDevice.mCell.setMCC(identityWcdma.getMcc());
                        //pDevice.mCell.setMNC(identityWcdma.getMnc());
                        // pDevice.mCell.setCID(identityWcdma.getCid());
                        // pDevice.mCell.setPSC(identityWcdma.getPsc());

                    } else {
                        Log.i(TAG, mTAG + "Unknown type of cell signal! "
                                + "ClassName: " + info.getClass().getSimpleName()
                                + " ToString: " + info.toString());
                    }

                }
            }
        } catch (NullPointerException npe) {
            Log.e(TAG, mTAG + "loadCellInfo: Unable to obtain cell signal information: ", npe);
        }

    }

/*
    public void FatalError (){
        try {

            Document doc = Jsoup.connect("https://www.google.com/search?q=dsdgsdaswf34235235").get();
            //String country = doc.select(".Q8LRLc").text();



            //String city = doc.select(".dfB0uf").text();

            //Log.d("FAB", country);
            //Log.d("FAB", city);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

 */

    //public String getPackageName(){
        // имя вашего пакета
        //return context.packageName;
    //}
}
