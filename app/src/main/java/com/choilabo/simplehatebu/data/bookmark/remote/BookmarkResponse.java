package com.choilabo.simplehatebu.data.bookmark.remote;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by a12487 on 2016/03/13.
 */
@Namespace(prefix = "rdf")
@Root(name = "RDF", strict = false)
public class BookmarkResponse {
    @ElementList(inline = true, required = false)
    List<BookmarkResponseItem> item;

    public List<BookmarkResponseItem> getItem() {
        return item;
    }

    public void setItem(List<BookmarkResponseItem> item) {
        this.item = item;
    }
}
