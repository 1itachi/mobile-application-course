package edu.neu.madcourse.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Patterns;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class LinkUnit {
    private String linkName;
    private String linkUrl;

    public LinkUnit(String linkName, String linkUrl) {
        this.linkName = linkName;
        this.linkUrl = linkUrl;
    }

    public void onLinkUnitClicked(Context context) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkUrl));
        context.startActivity(browserIntent);
    }

    public String getLinkName() {
        return linkName;
    }

    public String getLinkUrl() {
        return linkUrl;
    }


    public boolean isValid() {
        try {
            new URL(linkUrl).toURI();
        } catch (MalformedURLException | URISyntaxException e) {
            return false;
        }
        return Patterns.WEB_URL.matcher(linkUrl).matches();
    }
}