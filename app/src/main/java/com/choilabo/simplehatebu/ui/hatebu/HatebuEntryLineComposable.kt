package com.choilabo.simplehatebu.ui.hatebu

import android.net.Uri
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.choilabo.simplehatebu.R
import com.choilabo.simplehatebu.data.hatebu.entity.HatebuEntry

/**
 * Created by sato_shinichiro on 2021/10/10.
 */
@ExperimentalFoundationApi
@Composable
fun HatebuEntryLineComposable(
    hatebuEntry: HatebuEntry?,
    listener: HatebuEntryLineComposableListener?
) {
    MaterialTheme {
        Box {
            Column(
                Modifier
                    .padding(12.dp)
            ) {
                Text(
                    text = hatebuEntry?.title.orEmpty(),
                    maxLines = 2,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row() {
                    Column(
                        Modifier
                            .weight(1f)
                            .height(56.dp)
                    ) {
                        Text(
                            text = hatebuEntry?.description.orEmpty(),
                            maxLines = 2,
                            fontSize = 12.sp,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Row() {
                            Image(
                                painter = rememberImagePainter(hatebuEntry?.link?.let {
                                    "https://cdn-ak2.favicon.st-hatena.com/?url=$it"
                                }),
                                contentDescription = null,
                                modifier = Modifier.size(12.dp),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = hatebuEntry?.link?.let { Uri.parse(it).host }
                                    .orEmpty(),
                                fontSize = 10.sp,
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = hatebuEntry?.date.orEmpty(),
                                fontSize = 10.sp
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                        }
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    if (hatebuEntry?.imageUrl != null) {
                        Image(
                            painter = rememberImagePainter(hatebuEntry.imageUrl),
                            contentDescription = null,
                            modifier = Modifier.size(56.dp),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Text(
                            text = stringResource(id = R.string.no_image),
                            fontSize = 10.sp,
                            color = colorResource(id = R.color.white),
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .size(56.dp)
                                .background(colorResource(id = R.color.gray_C0))
                        )
                    }
                }
            }
            Spacer(modifier = Modifier
                .matchParentSize()
                .background(
                    color = if (hatebuEntry?.isRead == true) {
                        colorResource(id = R.color.disable)
                    } else {
                        colorResource(id = R.color.transparent)
                    }
                )
                .combinedClickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = LocalIndication.current,
                    onClick = { listener?.onHatebuEntryClicked(hatebuEntry?.link.orEmpty()) },
                    onLongClick = { listener?.onHatebuEntryLongClicked(hatebuEntry?.link.orEmpty()) }
                )
            )
            Divider()
        }
    }
}

interface HatebuEntryLineComposableListener {

    fun onHatebuEntryClicked(link: String)

    fun onHatebuEntryLongClicked(link: String)
}