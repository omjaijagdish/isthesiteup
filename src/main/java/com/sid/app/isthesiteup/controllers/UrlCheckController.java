package com.sid.app.isthesiteup.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlCheckController {

    private final String SITE_IS_UP = "Site is UP!";
    private final String SITE_IS_DOWN = "Site is Down!";
    private final String INCORRECT_URL = "URL is Incorrect!";

    @GetMapping("/check")
    public String getUrlStatusMessage(@RequestParam String url) {
        String returnMessage = "";

        try {
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCodeCategory = conn.getResponseCode() / 100;
            System.out.println("responseCodeCategory:: " + responseCodeCategory);
            returnMessage = (responseCodeCategory == 2 || responseCodeCategory == 3) ? SITE_IS_UP : SITE_IS_DOWN;
            /*
             * if (responseCodeCategory == 2) { returnMessage = SITE_IS_UP; } else {
             * returnMessage = SITE_IS_DOWN; }
             */
        } catch (MalformedURLException e) {
            System.out.println("Inside MalformedURLException..");
            returnMessage = INCORRECT_URL;
        } catch (IOException e) {
            System.out.println("Inside IOException..");
            returnMessage = INCORRECT_URL;
        }
        return returnMessage;

    }

}
