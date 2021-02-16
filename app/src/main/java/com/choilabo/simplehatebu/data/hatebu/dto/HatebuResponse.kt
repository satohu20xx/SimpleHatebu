package com.choilabo.simplehatebu.data.hatebu.dto

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.Root

/**
 * Created by sato_shinichiro on 2020/09/29
 */
@Namespace(prefix = "rdf")
@Root(name = "RDF", strict = false)
class HatebuResponse {
    @get:ElementList(name = "item", inline = true)
    @set:ElementList(name = "item", inline = true)
    var items: List<HatebuResponseItem>? = null
}

@Root(name = "item", strict = false)
class HatebuResponseItem {

    @get:Element(name = "link", required = false)
    @set:Element(name = "link", required = false)
    var link: String? = null

    @get:Element(name = "title", required = false)
    @set:Element(name = "title", required = false)
    var title: String? = null

    @get:Element(name = "description", required = false)
    @set:Element(name = "description", required = false)
    var description: String? = null

    @Namespace(prefix = "dc")
    @get:Element(name = "date", required = false)
    @set:Element(name = "date", required = false)
    var date: String? = null

    @Namespace(prefix = "hatena")
    @get:Element(name = "imageurl", required = false)
    @set:Element(name = "imageurl", required = false)
    var imageUrl: String? = null
}
