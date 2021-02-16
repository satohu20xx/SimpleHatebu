package com.choilabo.simplehatebu.data.hatebu.converter

import com.choilabo.simplehatebu.data.hatebu.dto.HatebuResponse
import com.choilabo.simplehatebu.data.hatebu.entity.HatebuEntry
import dagger.Reusable
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * Created by sato_shinichiro on 2020/09/30
 */
@Reusable
class HatebuEntryConverter @Inject constructor(
) {

    private val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.getDefault())
    private val formatter = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())

    fun from(response: HatebuResponse?): List<HatebuEntry> = response?.items?.mapNotNull {
        val link = it.link ?: return@mapNotNull null
        val title = it.title ?: return@mapNotNull null
        HatebuEntry(
            link = link,
            title = title,
            description = it.description,
            imageUrl = it.imageUrl,
            date = it.date
                ?.let { parser.parse(it) }
                ?.let { formatter.format(it) },
            updatedAt = System.currentTimeMillis(),
            isRead = false
        )
    }.orEmpty()
}