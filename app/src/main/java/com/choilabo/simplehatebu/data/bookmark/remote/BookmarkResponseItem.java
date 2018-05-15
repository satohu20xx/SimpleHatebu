package com.choilabo.simplehatebu.data.bookmark.remote;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Created by a12487 on 2016/03/13.
 */
@Root(name = "item", strict = false)
public class BookmarkResponseItem {
    @Element(name = "title", required = false)
    String title;
    @Element(name = "link", required = false)
    String link;
    @Element(name = "description", required = false)
    String description;
    @Namespace(prefix = "content")
    @Element(name = "encoded", required = false)
    String encoded;
    @Namespace(prefix = "dc")
    @Element(name = "date", required = false)
    String date;
    @Namespace(prefix = "dc")
    @Element(name = "subject", required = false)
    String subject;
//    @Namespace(prefix = "hatena")
//    @Element(name = "bookmarkcount")
//    private String bookmarkcount;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEncoded() {
        return encoded;
    }

    public void setEncoded(String encoded) {
        this.encoded = encoded;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

//f    public String getBookmarkcount() {
//        return bookmarkcount;
//    }
//
//    public void setBookmarkcount(String bookmarkcount) {
//        this.bookmarkcount = bookmarkcount;
//    }
}
