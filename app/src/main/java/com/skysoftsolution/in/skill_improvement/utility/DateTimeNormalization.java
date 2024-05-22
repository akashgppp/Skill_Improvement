package com.skysoftsolution.in.skill_improvement.utility;

public class DateTimeNormalization {
    public static String normalizeDate(String datetime) {
        String datetimeToReturn = "";
        try {
            String[] date = datetime.split(" ");
            String hour = date[1].split(":")[0];
            if (Integer.parseInt(hour) >= 24) {
                datetimeToReturn = date[0] + " 23:00:00";
            } else {
                datetimeToReturn = datetime;
            }

        } catch (Exception e) {
            datetimeToReturn = datetime;
        }
        return datetimeToReturn;
    }

    public static String changeDateFormat(String datetime) {
        String datetimeToReturn = "";
        String month1 = null, date1 = null, year = null;

        if (datetime.contains("/")) {
            try {
                String datenew = datetime.split(" ")[0];
                month1 = datenew.split("/")[0];
                date1 = datenew.split("/")[1];
                year = datenew.split("/")[2];
                if (month1.length() == 1) {
                    month1 = "0" + month1;
                }
                if (date1.length() == 1) {
                    date1 = "0" + date1;
                }

                datetimeToReturn = date1.concat("/").concat(month1).concat("/").concat(year);


            } catch (Exception e) {
                datetimeToReturn = datetime;
            }
        } else {
            try {
                String datenew = datetime.split(" ")[0];
                month1 = datenew.split("-")[1];
                date1 = datenew.split("-")[2];
                year = datenew.split("-")[0];
                if (month1.length() == 1) {
                    month1 = "0" + month1;
                }
                if (date1.length() == 1) {
                    date1 = "0" + date1;
                }

                datetimeToReturn = date1.concat("/").concat(month1).concat("/").concat(year);


            } catch (Exception e) {
                datetimeToReturn = datetime;
            }
        }

        return datetimeToReturn;
    }


}
