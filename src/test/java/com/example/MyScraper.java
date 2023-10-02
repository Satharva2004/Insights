package com.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MyScraper {
    public static void main(String[] args) {
        // String url =
        // "https://timesofindia.indiatimes.com/city/chennai/body-of-tn-fisherman-recovered-from-sea/articleshow/104071085.cms";
        // String url =
        // "https://timesofindia.indiatimes.com/business/international-business/uae-and-dubai-continue-to-grow-as-strong-emerging-venture-markets/articleshow/102191844.cms";
        // String url =
        // "https://timesofindia.indiatimes.com/business/international-business/dubai-continues-to-cement-its-position-as-a-higher-education-hub-for-both-indian-and-global-universities/articleshow/102391870.cms";
        String url = "https://timesofindia.indiatimes.com/business/india-business/indias-unemployment-rate-declines-to-1-year-low-in-september/articleshow/104105736.cms";
        try {
            // Download and parse the web page
            Document document = Jsoup.connect(url).get();

            // Get the article title
            String title = document.title();

            // Find article author information (if available)
            String authors = findAuthors(document);

            // Find article publish date (if available)
            String publishDate = findPublishDate(document);

            // Get the article summary (if available)
            String summary = findSummary(document);

            // Print the results
            System.out.println("Title: " + title);
            System.out.println("Authors: " + authors);
            System.out.println("Publish Date: " + publishDate);
            System.out.println("Summary: " + summary);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String findAuthors(Document document) {
        Elements authorElements = document.select(
                ".author, .byline, .author-name, .writer, .reporter, .journalist, .contributor, .by, .author-info, .byline-info, .byline-author, .byline-name, .author-bio, .credit, .noamphtml, .follow_author_title, .bulletProj, .ssrcss-68pt20-Text-TextContributorName e8mq1e96");

        return authorElements.isEmpty() ? "N/A" : authorElements.text();
    }

    private static String findPublishDate(Document document) {
        Elements dateElements = document.select(
                ".date, .article-date, .publish-date, .pub-date, .datetime, .post-date, .entry-date, .story-date, .timestamp, .article-timestamp, .meta-date, .article-meta-date, .time, .article-time, .article-meta-time, .date-time, .article-date-time, .epaper-date, .yhwZO, ssrcss-1mh4yp1-IconContainer e4wm5bw0, .xf8Pm byline, .t8vf3 byline_action, .xf8Pm byline, .xf8Pm byline, .noopener");
        Element spanElement = document.select("div.xf8Pm.byline span").first();

        // Extract the text (date) from the <span> element
        String date = spanElement.text();

        String axxyx = dateElements.text() + "" + date;
        // Use "date" class
        return axxyx.isEmpty() ? "N/A" : axxyx;
    }

    private static String findSummary(Document document) {
        // Select elements for summaries by class names
        Elements summaryElements = document.select(
                ".article-summary, .summary, .article-abstract, .article-description, .post-summary, .entry-summary, .excerpt, .article-excerpt, .post-excerpt, .story-summary, .article-teaser, .post-teaser, .intro, .article-intro, .post-intro, .blurb, .article-blurb, .post-blurb, .brief, .article-brief, .post-brief, .lead, .article-lead, .post-lead, .description, .article-description, .post-description, .snippet, .article-snippet, .post-snippet, .article-preview, .post-preview, .article-highlight, .post-highlight, .article-content-summary, .article-lead-summary, .post-lead-summary, .article-main-summary, .post-main-summary, .article-body-summary, .post-body-summary, .article-text-summary, .post-text-summary, .article-abstract-summary, .post-abstract-summary, .article-description-summary, .post-description-summary, .article-teaser-summary, .post-teaser-summary, .article-excerpt-summary, .post-excerpt-summary, .story-summary-summary, .article-info-summary, .post-info-summary, .article-content-description, .post-content-description, .article-main-description, .post-main-description, .article-body-description, .post-body-description, .article-text-description, .post-text-description, .article-abstract-description, .post-abstract-description, .article-description-description, .post-description-description, .article-teaser-description, .post-teaser-description, .article-excerpt-description, .post-excerpt-description, .story-summary-description, .article-info-description, .post-info-description");

        // Select the <meta> tag with the name attribute equal to "description"
        Element metaElement = document.selectFirst("meta[name=description]");

        // Get the description from the <meta> tag, or "N/A" if not found
        String description = metaElement != null ? metaElement.attr("content") : "N/A";

        // Combine summary content with description
        String combinedSummary = summaryElements.text() + "" + description;

        return combinedSummary.isEmpty() ? "N/A" : combinedSummary;
    }

}
