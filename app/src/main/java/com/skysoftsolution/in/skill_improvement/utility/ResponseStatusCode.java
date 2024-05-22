package com.skysoftsolution.in.skill_improvement.utility;

import android.content.Context;
import android.widget.Toast;
public class ResponseStatusCode {
    public static String getResponsefromstatuscode(int statuscode) {
        String result = null;
        switch (statuscode) {
            case 201:
                result = "ResponseError" + "/" + "Created";
                break;
            case 202:
                result = "ResponseError" + "/" + "Accepted";
                break;
            case 203:
                result = "ResponseError" + "/" + "NonAuthoritativeInformation";
                break;
            case 204:
                result = "ResponseError" + "/" + "Server is Down, Please try again";
                break;
            case 205:
                result = "ResponseError" + "/" + "ResetContent";
                break;
            case 206:
                result = "ResponseError" + "/" + "PartialContent";
                break;
            case 300:
                result = "ResponseError" + "/" + "MultipleChoices";
                break;
            case 301:
                result = "ResponseError" + "/" + "MovedPermanently";
                break;
            case 302:
                result = "ResponseError" + "/" + "Redirect";
                break;
            case 303:
                result = "ResponseError" + "/" + "RedirectMethod";
                break;
            case 304:
                result = "ResponseError" + "/" + "NotModified";
                break;
            case 305:
                result = "ResponseError" + "/" + "UseProxy";
                break;
            case 306:
                result = "ResponseError" + "/" + "Unused";
                break;
            case 307:
                result = "ResponseError" + "/" + "TemporaryRedirect";
                break;
            case 400:
                result = "ResponseError" + "/" + "Requested url not valid";
                break;
            case 401:
                result = "ResponseError" + "/" + "Unauthorized from server";
                break;
            case 402:
                result = "ResponseError" + "/" + "PaymentRequired";
                break;
            case 403:
                result = "ResponseError" + "/" + "Requested url not valid";
                break;
            case 404:
                result = "ResponseError" + "/" + "Connection Failed with server";
                break;
            case 405:
                result = "ResponseError" + "/" + "MethodNotAllowed";
                break;
            case 406:
                result = "ResponseError" + "/" + "NotAcceptable";
                break;
            case 407:
                result = "ResponseError" + "/" + "ProxyAuthenticationRequired";
                break;
            case 408:
                result = "ResponseError" + "/" + "RequestTimeout";
                break;
            case 409:
                result = "ResponseError" + "/" + "Conflict";
                break;
            case 410:
                result = "ResponseError" + "/" + "Gone";
                break;
            case 411:
                result = "ResponseError" + "/" + "LengthRequired";
                break;
            case 412:
                result = "ResponseError" + "/" + "PreconditionFailed";
                break;
            case 413:
                result = "ResponseError" + "/" + "RequestEntityTooLarge";
                break;
            case 414:
                result = "ResponseError" + "/" + "RequestUriTooLong";
                break;
            case 415:
                result = "ResponseError" + "/" + "UnsupportedMediaType";
                break;
            case 416:
                result = "ResponseError" + "/" + "RequestedRangeNotSatisfiable";
                break;
            case 417:
                result = "ResponseError" + "/" + "ExpectationFailed";
                break;
            case 500:
                result = "ResponseError" + "/" + "Error on Server. Kindly report app tech team";
                break;
            case 501:
                result = "ResponseError" + "/" + "NotImplemented";
                break;
            case 502:
                result = "ResponseError" + "/" + "BadGateway";
                break;
            case 503:
                result = "ResponseError" + "/" + "ServiceUnavailable";
                break;
            case 504:
                result = "ResponseError" + "/" + "GatewayTimeout";
                break;
            case 505:
                result = "ResponseError" + "/" + "HttpVersionNotSupported";
                break;
            default:
                result = "ResponseError" + "/" + "Server Connection Failed";
        }
        return result;

    }

  /*  public static void showResponseError(Context context, Object result, boolean isServerConnectionFailed) {


        if (result == null) {

            // Toast.makeText(context, context.getResources().getString(R.string.server_connection_failed), Toast.LENGTH_SHORT).show();
            Toast.makeText(context, "Please Check Your User Name Password", Toast.LENGTH_SHORT).show();
        } else if (isServerConnectionFailed) {
            if (result.toString().contains("ServerSideException")) {
                // Toast.makeText(context, context.getResources().getString(R.string.some_error_occured), Toast.LENGTH_SHORT).show();
                Toast.makeText(context, result.toString()+"", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, result.toString(), Toast.LENGTH_SHORT).show();
            }

        } else if (result.toString().equalsIgnoreCase("[]")) {
            Toast.makeText(context, context.getResources().getString(R.string.data_not_found), Toast.LENGTH_SHORT).show();
        } else if (result.toString().contains("ResponseError")) {

            String responseCodeError = result.toString().split("/")[1];
            Toast.makeText(context, responseCodeError, Toast.LENGTH_SHORT).show();

        }


    }*/
  /*  public static void showResponseError1(Context context, Object result, boolean isServerConnectionFailed) {


        if (result == null) {

            Toast.makeText(context, context.getResources().getString(R.string.server_connection_failed), Toast.LENGTH_SHORT).show();

        } else if (isServerConnectionFailed) {
            if (result.toString().contains("ServerSideException")) {
                Toast.makeText(context, context.getResources().getString(R.string.some_error_occured), Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(context, result.toString(), Toast.LENGTH_SHORT).show();
            }

        } else if (result.toString().equalsIgnoreCase("[]")) {
            Toast.makeText(context, context.getResources().getString(R.string.data_not_found), Toast.LENGTH_SHORT).show();
        } else if (result.toString().contains("ResponseError")) {
            String responseCodeError = result.toString().split("/")[1];
            Toast.makeText(context, responseCodeError, Toast.LENGTH_SHORT).show();

        }


    }*/
}
