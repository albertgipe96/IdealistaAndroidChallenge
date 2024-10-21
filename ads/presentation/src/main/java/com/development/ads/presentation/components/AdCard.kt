package com.development.ads.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.development.ads.domain.model.AdData
import com.development.ads.domain.model.OperationType

@Composable
fun AdCard(
    modifier: Modifier = Modifier,
    adData: AdData,
    onCardClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(450.dp)
            .padding(16.dp),
        onClick = onCardClick
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            ) {
                AsyncImage(
                    modifier = Modifier.fillMaxWidth(),
                    model = adData.thumbnail,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                if (adData.adSpecs.operation != OperationType.UNKNOWN) {
                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                            .background(Color.Magenta, RoundedCornerShape(16.dp))
                            .padding(6.dp)
                            .align(Alignment.TopStart)
                    ) {
                        Text(
                            text = adData.adSpecs.operation.toString(),
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
            Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "${adData.adSpecs.price}€",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.End
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "${adData.propertySpecs.fullAddress}, ${adData.propertySpecs.municipality}",
                    fontSize = 24.sp,
                    textAlign = TextAlign.Start
                )

            }
        }
    }
}