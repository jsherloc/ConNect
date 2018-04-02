package com.example.james.connect;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.Toast;

/**
 * Created by james on 30/03/2018.
 */

public class InterceptCall extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent){
        String Hoax = "";
        if(intent.getAction().equals("android.intent.action.PHONE_STATE"))
        {

            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);

            if(state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)){
                //Log.d(TAG, "Inside Extra state off hook");
                String number = intent.getStringExtra(intent.EXTRA_PHONE_NUMBER);
                //Log.e(TAG, "outgoing number : " + number);
            }

            else if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)){
                //Log.e(TAG, "Inside EXTRA_STATE_RINGING");
                String number = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
                String country = foreignCall(number);
                Toast.makeText(context, "INCOMING CALL " + country, Toast.LENGTH_LONG).show();
                //Log.e(TAG, "incoming number : " + number);
            }
            else if(state.equals(TelephonyManager.EXTRA_STATE_IDLE)){
                //Log.d(TAG, "Inside EXTRA_STATE_IDLE");
            }
        }
        /*try {
            Toast.makeText(context, "This Incoming Call", Toast.LENGTH_LONG).show();
        } catch (Exception e) {

        }*/
    }

    public String foreignCall(String incomingNumber) {
        String country = "Unknown";

        if (incomingNumber.substring(0, 2).equals("00")) {
            countryFound:
            switch (incomingNumber.substring(2, 3)) {
                case "0":
                    switch (incomingNumber.substring(3, 5)) {
                        case "254":
                            country = "Kenya";
                            break countryFound;
                        case "255":
                            country = "Tanzania";
                            break countryFound;
                        case "256":
                            country = "Uganda";
                            break countryFound;
                    }
                case "1":
                    switch (incomingNumber.substring(3, 4)) {
                        case "0":
                            switch (incomingNumber.substring(4, 7)) {
                                case "591":
                                    country = "Bolivia";
                                    break countryFound;
                            }
                        case "4":
                            country = "Brazil";
                            break countryFound;
                        case "6":
                            switch (incomingNumber.substring(4, 5)) {
                                case "2":
                                    country = "Indonesia";
                                    break countryFound;
                                case "5":
                                    country = "Singapore";
                                    break countryFound;
                                case "6":
                                    country = "Thailand";
                                    break countryFound;
                                default:
                                    switch (incomingNumber.substring(4, 6)) {
                                        case "70":
                                            country = "East Timor";
                                            break countryFound;
                                        case "81":
                                            country = "Wallis et Futuna";
                                            break countryFound;
                                    }
                            }
                        case "8":
                            switch (incomingNumber.substring(4, 5)) //this can be done better
                            {
                                case "2":
                                    country = "South Korea";
                                    break countryFound;
                                default:
                                    country = "Hong Kong";
                                    break countryFound;
                            }
                    }
                case "2":
                    switch (incomingNumber.substring(3, 4)) {
                        case "0":
                            country = "Egypt";
                            break countryFound;
                        case "1":
                            switch (incomingNumber.substring(4, 5)) {
                                case "2":
                                    country = "Morocco";
                                    break countryFound;
                                case "3":
                                    country = "Algeria";
                                    break countryFound;
                                case "6":
                                    country = "Tunisia";
                                    break countryFound;
                                case "8":
                                    country = "Libya";
                                    break countryFound;
                                default:
                                    country = "Unknown";
                                    break countryFound;
                            }
                        case "2":
                            switch (incomingNumber.substring(4, 5)) {
                                case "0":
                                    country = "Gambia";
                                    break countryFound;
                                case "1":
                                    country = "Senegal";
                                    break countryFound;
                                case "2":
                                    country = "Mauritania";
                                    break countryFound;
                                case "3":
                                    country = "Mali";
                                    break countryFound;
                                case "4":
                                    country = "Guinea";
                                    break countryFound;
                                case "5":
                                    country = "Ivory Coast";
                                    break countryFound;
                                case "6":
                                    country = "Burkina Faso";
                                    break countryFound;
                                case "7":
                                    country = "Niger";
                                    break countryFound;
                                case "8":
                                    country = "Togo";
                                case "9":
                                    country = "Benin";
                                    break countryFound;
                                default:
                                    country = "Unknown";
                                    break countryFound;
                            }
                        case "3":
                            switch (incomingNumber.substring(4, 5)) {
                                case "1":
                                    country = "Liberia";
                                    break countryFound;
                                case "2":
                                    country = "Sierra Leone";
                                    break countryFound;
                                case "3":
                                    country = "Ghana";
                                    break countryFound;
                                case "6":
                                    country = "Central African Republic";
                                    break countryFound;
                                case "7":
                                    country = "Cameroon";
                                    break countryFound;
                                case "8":
                                    country = "Cape Verde";
                                    break countryFound;
                                case "9":
                                    country = "Sao Tome and Principe";
                                    break countryFound;
                                default:
                                    country = "Unknown";
                                    break countryFound;
                            }
                        case "4":
                            switch (incomingNumber.substring(4, 5)) {
                                case "1":
                                    country = "Gabon";
                                    break countryFound;
                                case "2":
                                    country = "Congo";
                                    break countryFound;
                                case "3":
                                    country = "DR Congoo";
                                    break countryFound;
                                case "4":
                                    country = "Angola";
                                    break countryFound;
                                case "5":
                                    country = "Guinea-Bissau";
                                    break countryFound;
                                case "8":
                                    country = "Seychelles";
                                    break countryFound;
                                case "9":
                                    country = "Sudan";
                                    break countryFound;
                                default:
                                    country = "Unknown";
                                    break countryFound;

                            }
                        case "5":
                            switch (incomingNumber.substring(4, 5)) {
                                case "0":
                                    country = "Rwanda";
                                    break countryFound;
                                case "1":
                                    country = "Ethiopia";
                                    break countryFound;
                                case "2":
                                    country = "Somalia";
                                    break countryFound;
                                case "3":
                                    country = "Dijibouti";
                                    break countryFound;
                                case "7":
                                    country = "Burundi";
                                    break countryFound;
                                case "8":
                                    country = "Mozambique";
                                    break countryFound;
                                default:
                                    country = "Unknown";
                                    break countryFound;
                            }
                        case "6":
                            switch (incomingNumber.substring(4, 5)) {
                                case "0":
                                    country = "Zambia";
                                    break countryFound;
                                case "1":
                                    country = "Madasgascar";
                                    break countryFound;
                                case "2":
                                    country = "Réunion and Mayotte";
                                    break countryFound;
                                case "3":
                                    country = "Zimbabwe";
                                    break countryFound;
                                case "4":
                                    country = "Namibia";
                                    break countryFound;
                                case "5":
                                    country = "Malawi";
                                    break countryFound;
                                case "6":
                                    country = "Lesotho";
                                    break countryFound;
                                case "7":
                                    country = "Botswana";
                                    break countryFound;
                                case "8":
                                    country = "Swaziland";
                                    break countryFound;
                                case "9":
                                    country = "Comoros";
                                    break countryFound;
                                default:
                                    country = "Unknown";
                                    break countryFound;
                            }
                        case "9":
                            switch (incomingNumber.substring(4, 5)) {
                                case "0":
                                    country = "Saint Helena";
                                    break countryFound;
                                case "1":
                                    country = "Eritrea";
                                    break countryFound;
                                case "7":
                                    country = "Aruba";
                                    break countryFound;
                                case "8":
                                    country = "Faroe Islands";
                                    break countryFound;
                                default:
                                    country = "Unknown";
                                    break countryFound;

                            }

                    }
                case "3":
                    switch (incomingNumber.substring(3, 4)) {
                        case "0":
                            country = "Greece";
                            break countryFound;
                        case "1":
                            country = "Netherlands";
                            break countryFound;
                        case "2":
                            country = "Belgium";
                            break countryFound;
                        case "3":
                            country = "France";
                            break countryFound;
                        case "4":
                            country = "Spain";
                            break countryFound;
                        case "5":
                            switch (incomingNumber.substring(4, 5)) {
                                case "0":
                                    country = "Gibraltar";
                                    break countryFound;
                                case "1":
                                    country = "Portugal";
                                    break countryFound;
                                case "2":
                                    country = "Luxembourg";
                                    break countryFound;
                                case "3":
                                    country = "Ireland";
                                    break countryFound;
                                case "4":
                                    country = "Iceland";
                                    break countryFound;
                                case "5":
                                    country = "Albania";
                                    break countryFound;
                                case "6":
                                    country = "Malta";
                                    break countryFound;
                                case "7":
                                    country = "Cyprus";
                                    break countryFound;
                                case "8":
                                    country = "Finland";
                                    break countryFound;
                                case "9":
                                    country = "Bulgaria";
                                    break countryFound;
                            }
                        case "6":
                            country = "Hungary";
                            break countryFound;
                        case "7":
                            switch (incomingNumber.substring(4, 5)) {
                                case "0":
                                    country = "Lithuania";
                                    break countryFound;
                                case "1":
                                    country = "Latvia";
                                    break countryFound;
                                case "2":
                                    country = "Estonia";
                                    break countryFound;
                                case "3":
                                    country = "Moldova";
                                    break countryFound;
                                case "4":
                                    country = "Armenia";
                                    break countryFound;
                                case "6":
                                    country = "Andorra";
                                    break countryFound;
                                case "7":
                                    country = "Monaco";
                                    break countryFound;
                                case "8":
                                    country = "San Marino";
                                    break countryFound;
                                case "9":
                                    country = "Vatican City";
                                    break countryFound;
                            }
                        case "8":
                            switch (incomingNumber.substring(4, 5)) {
                                case "5":
                                    country = "Croatia";
                                    break countryFound;
                                case "6":
                                    country = "Slovenia";
                                    break countryFound;
                                case "7":
                                    country = "Bosnia and Herzegovina";
                                    break countryFound;
                                case "9":
                                    country = "Macedonia";
                                    break countryFound;
                            }
                        case "9":
                            country = "Italy";
                            break countryFound;
                    }

                    //INSERT
                case "4":
                    switch (incomingNumber.substring(3, 4)) {
                        case "0":
                            country = "Romania";
                            break countryFound;
                        case "1":
                            country = "Switzerland";
                            break countryFound;
                        case "2":
                            switch (incomingNumber.substring(4, 5)) {
                                case "0":
                                    country = "Czech Republic";
                                    break countryFound;
                                case "1":
                                    country = "Slovakia";
                                    break countryFound;
                                case "3":
                                    country = "Lichtenstein";
                                    break countryFound;
                            }
                        case "3":
                            country = "Austria";
                            break countryFound;
                        case "4":
                            country = "United Kingdom";
                            break countryFound;
                        case "5":
                            country = "Denmark";
                            break countryFound;
                        case "6":
                            country = "Sweden";
                            break countryFound;
                        case "7":
                            country = "Norway";
                            break countryFound;
                        case "8":
                            country = "Poland";
                            break countryFound;
                        case "9":
                            country = "Germany";
                            break countryFound;
                    }
                case "5":
                    switch (incomingNumber.substring(3, 4)) {
                        case "0":
                            switch (incomingNumber.substring(4, 5)) {
                                case "0":
                                    country = "Falkland Islands/Maldivas";
                                    break countryFound;
                                case "1":
                                    country = "Belize";
                                    break countryFound;
                                case "2":
                                    country = "Guatemala";
                                    break countryFound;
                                case "3":
                                    country = "El Salvador";
                                    break countryFound;
                                case "4":
                                    country = "Honduras";
                                    break countryFound;
                                case "5":
                                    country = "Nicaragua";
                                    break countryFound;
                                case "6":
                                    country = "Costa Rica";
                                    break countryFound;
                                case "7":
                                    country = "Panama";
                                    break countryFound;
                                case "8":
                                    country = "Saint Pierre and Miquelon";
                                    break countryFound;
                                case "9":
                                    country = "Haiti";
                                    break countryFound;
                            }
                        case "1":
                            country = "Peru";
                            break countryFound;
                        case "2":
                            country = "Mexico";
                            break countryFound;
                        case "4":
                            country = "Argentina";
                            break countryFound;
                        case "5":
                            switch (incomingNumber.substring(4, 5)) {
                                default:
                                    country = "Colombia";
                                    break countryFound;
                            }
                        case "6":
                            country = "Chile";
                            break countryFound;
                        case "8":
                            country = "Venezuela";
                            break countryFound;
                        case "9":
                            switch (incomingNumber.substring(4, 5)) {
                                case "0":
                                    country = "Guadeloupe";
                                    break countryFound;
                                case "2":
                                    country = "Guyana";
                                    break countryFound;
                                case "3":
                                    country = "Ecuador";
                                    break countryFound;
                                case "4":
                                    country = "French Guinea";
                                    break countryFound;
                                case "6":
                                    country = "Martinique";
                                    break countryFound;
                                case "7":
                                    country = "Suriname";
                                    break countryFound;
                                case "8":
                                    country = "Uruguay";
                                    break countryFound;
                                case "9":
                                    country = "Netherlands Antilles";
                                    break countryFound;
                            }
                    }
                case "6":
                    switch (incomingNumber.substring(3, 4)) {
                        case "0":
                            country = "Malaysia";
                            break countryFound;
                        case "3":
                            country = "Philippines";
                            break countryFound;
                        case "4":
                            country = "New Zealand";
                            break countryFound;
                        case "7":
                            switch (incomingNumber.substring(4, 5)) {
                                case "2":
                                    country = "Norfolk Island";
                                    break countryFound;
                                case "3":
                                    country = "Brunei Darussalam";
                                    break countryFound;
                                case "4":
                                    country = "Nauru";
                                    break countryFound;
                                case "6":
                                    country = "Tonga";
                                    break countryFound;
                                case "7":
                                    country = "Solomon Islands";
                                    break countryFound;
                                case "8":
                                    country = "Vanuatu";
                                    break countryFound;
                                case "9":
                                    country = "Fiji";
                                    break countryFound;
                            }
                        case "8":
                            switch (incomingNumber.substring(4, 5)) {
                                case "0":
                                    country = "Palau";
                                    break countryFound;
                                case "2":
                                    country = "Cook Islands";
                                    break countryFound;
                                case "3":
                                    country = "Niue";
                                    break countryFound;
                                case "5":
                                    country = "Samoa";
                                    break countryFound;
                                case "6":
                                    country = "Kiribati";
                                    break countryFound;
                                case "7":
                                    country = "New Caledonia";
                                    break countryFound;
                                case "8":
                                    country = "Tuvalu";
                                    break countryFound;
                                case "9":
                                    country = "French Polynesia";
                                    break countryFound;
                            }
                        case "9":
                            switch (incomingNumber.substring(4, 5)) {
                                case "0":
                                    country = "Tokelau";
                                    break countryFound;
                                case "2":
                                    country = "Marshall Islands";
                                    break countryFound;
                            }
                    }
                case "8":
                    switch (incomingNumber.substring(3, 4)) {
                        case "4":
                            country = "Vietnam";
                            break countryFound;
                        case "5":
                            switch (incomingNumber.substring(4, 5)) {
                                case "0":
                                    country = "North Korea";
                                    break countryFound;
                                case "3":
                                    country = "Macao";
                                    break countryFound;
                                case "5":
                                    country = "Cambodia";
                                    break countryFound;
                                case "6":
                                    country = "Laos";
                                    break countryFound;
                            }
                        case "6":
                            country = "China";
                            break countryFound;
                        case "7":
                            country = "Pitcairn";
                            break countryFound;
                        case "8":
                            country = "Bangladesh";
                            break countryFound;
                    }
                case "9":
                    switch (incomingNumber.substring(3, 4)) {
                        case "0":
                            country = "Turkey";
                            break countryFound;
                        case "1":
                            country = "India";
                            break countryFound;
                        case "2":
                            switch (incomingNumber.substring(4, 7)) {
                                case "234":
                                    country = "Nigeria";
                                    break countryFound;
                                case "299":
                                    country = "Greenland";
                                    break countryFound;
                                default:
                                    country = "Pakistan";
                                    break countryFound;
                            }
                        case "3":
                            country = "Afghanistan";
                            break countryFound;
                        case "4":
                            country = "Sri Lanka";
                            break countryFound;
                        case "5":
                            country = "Myanmar";
                            break countryFound;
                        case "6":
                            switch (incomingNumber.substring(4, 5)) {
                                case "0":
                                    country = "Maldives";
                                    break countryFound;
                                case "1":
                                    country = "Lebanon";
                                    break countryFound;
                                case "2":
                                    country = "Jordan";
                                    break countryFound;
                                case "3":
                                    country = "Syria";
                                    break countryFound;
                                case "4":
                                    country = "Iraq";
                                    break countryFound;
                                case "5":
                                    country = "Kuwait";
                                    break countryFound;
                                case "6":
                                    country = "Saudi Arabia";
                                    break countryFound;
                                case "7":
                                    country = "Yemen";
                                    break countryFound;
                                case "8":
                                    country = "Oman";
                                    break countryFound;
                            }
                        case "7":
                            switch (incomingNumber.substring(4, 5)) {
                                case "0":
                                    country = "Palestine";
                                    break countryFound;
                                case "1":
                                    country = "UAE";
                                    break countryFound;
                                case "2":
                                    country = "Israel";
                                    break countryFound;
                                case "3":
                                    country = "Bahrain";
                                    break countryFound;
                                case "4":
                                    country = "Qatar";
                                    break countryFound;
                                case "5":
                                    country = "Bhutan";
                                    break countryFound;
                                case "7":
                                    country = "Nepal";
                                    break countryFound;
                            }
                        case "8":
                            country = "Iran";
                            break countryFound;
                        case "9":
                            country = "Kyrgyzstan";
                            break countryFound;
                    }
                default:
                    country = "Unknown";
                    break countryFound;
            }
        } else if (incomingNumber.substring(0, 3) == "810")  //Soviet Republic's protocol
        {
            countryFound:
            switch (incomingNumber.substring(3, 4)) {
                case "3":
                    switch (incomingNumber.substring(4, 5)) {
                        case "7":
                            country = "Belarus";
                            break countryFound;
                        case "8":
                            country = "Ukraine";
                            break countryFound;
                    }
                case "7":
                    country = "Russia/Kazakhstan";
                    break countryFound;
                case "9":
                    switch (incomingNumber.substring(4, 6)) {
                        case "92":
                            country = "Tajikistan";
                            break countryFound;
                        case "93":
                            country = "Turkmenistan";
                            break countryFound;
                        case "94":
                            country = "Azerbaijan";
                            break countryFound;
                        case "95":
                            country = "Georgia";
                            break countryFound;
                        case "98":
                            country = "Uzbekistan";
                            break countryFound;
                    }
                default:
                    country = "Unknown";
                    break countryFound;
            }

        } else if (incomingNumber.substring(0, 3) == "011")  //American protocol includes USA, Canada, Caribbean and Pacific regions
        {
            countryFound:
            switch (incomingNumber.substring(3, 4)) {
                case "1":
                    switch (incomingNumber.substring(4, 5)) {
                        case "2":
                            switch (incomingNumber.substring(5, 7)) {
                                case "42":
                                    country = "Bahamas";
                                    break countryFound;
                                case "46":
                                    country = "Barbados";
                                    break countryFound;
                                case "64":
                                    country = "Anguilla";
                                    break countryFound;
                                case "68":
                                    country = "Antigua and Barbuda";
                                    break countryFound;
                                case "84":
                                    country = "British Virgin Islands";
                                    break countryFound;
                            }
                        case "3":
                            switch (incomingNumber.substring(5, 7)) {
                                case "40":
                                    country = "Virgin Islands US";
                                    break countryFound;
                                case "45":
                                    country = "Cayman Islands";
                                    break countryFound;
                            }
                        case "4":
                            switch (incomingNumber.substring(5, 7)) {
                                case "41":
                                    country = "Bermuda";
                                    break countryFound;
                                case "73":
                                    country = "Grenada";
                                    break countryFound;
                            }
                        case "6":
                            switch (incomingNumber.substring(5, 7)) {
                                case "49":
                                    country = "Turks and Caicos";
                                    break countryFound;
                                case "64":
                                    country = "Monserrat";
                                    break countryFound;
                                case "70":
                                    country = "Northern Mariana Islands";
                                    break countryFound;
                                case "71":
                                    country = "Guam";
                                    break countryFound;
                                case "84":
                                    country = "American Samoa";
                                    break countryFound;
                            }
                        case "7":
                            switch (incomingNumber.substring(5, 7)) {
                                case "58":
                                    country = "Saint Lucia";
                                    break countryFound;
                                case "67":
                                    country = "Dominica";
                                    break countryFound;
                                case "84":
                                    country = "Saint Vincent";
                                    break countryFound;
                                case "87":
                                    country = "Puerto Rico";
                                    break countryFound;
                            }
                        case "8":
                            switch (incomingNumber.substring(5, 7)) {
                                case "09":
                                    country = "Dominican Republic";
                                    break countryFound;
                                case "29":
                                    country = "Dominican Republic";
                                    break countryFound;
                                case "49":
                                    country = "Dominican Republic";
                                    break countryFound;
                                case "68":
                                    country = "Trinidad and Tobago";
                                    break countryFound;
                                case "69":
                                    country = "Saint Kitts and Nevis";
                                    break countryFound;
                                case "76":
                                    country = "Jamaica";
                                    break countryFound;
                            }
                        case "9":
                            switch (incomingNumber.substring(5, 7)) {
                                case "39":
                                    country = "Puerto Rico";
                                    break countryFound;
                            }
                        default:
                            country = "USA/Canada";
                            break countryFound;

                    }
                default:
                    switch (incomingNumber.substring(3, 6)) {
                        case "691":
                            country = "Micronesia";
                            break countryFound;
                        default:
                            country = "Unknown";
                            break countryFound;
                    }
            }
        } else {
            if (incomingNumber.substring(0, 3) == "002") // With some tweaking this can be aggregated to the "00"/"2" switch stmnt.
            {
                countryFound:
                switch (incomingNumber.substring(3, 6)) {
                    case "595":
                        country = "Paraguay";
                        break countryFound;
                    case "886":
                        country = "Taiwan";
                        break countryFound;
                    default:
                        country = "Unknown";
                        break countryFound;
                }
            } else if (incomingNumber.substring(0, 2) == "99")  // Rest of the world codes, our list may not be exhaustive
            {
                countryFound:
                switch (incomingNumber.substring(2, 5)) {
                    case "381":
                        country = "Serbia";
                        break countryFound;
                    case "382":
                        country = "Montenegro";
                        break countryFound;
                    default:
                        country = "Unknown";
                        break countryFound;
                }
            } else if (incomingNumber.substring(1, 2) != "0") {
                countryFound:
                switch (incomingNumber.substring(1, 2)) {
                    case "1":
                        switch (incomingNumber.substring(3, 5)) {
                            case "81":
                                country = "Japan";
                                break countryFound;
                        }
                    case "2":
                        switch (incomingNumber.substring(3, 6)) {
                            case "230":
                                country = "Mauritius";
                                break countryFound;
                        }
                    case "5":
                        switch (incomingNumber.substring(3, 6)) {
                            case "675":
                                country = "Papua New Guinea";
                                break countryFound;
                        }
                    case "9":
                        switch (incomingNumber.substring(3, 5)) {
                            case "27":
                                country = "South Africa";
                                break countryFound;
                        }
                    default:
                        country = "Unknown";
                        break countryFound;
                }
            }
        }
        return country;
    }
}

