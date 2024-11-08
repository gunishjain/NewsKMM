package com.example.kmpnewsapp.ui.common

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode
import com.example.kmpnewsapp.theme.*
import com.example.kmpnewsapp.utils.Type
import com.example.kmpnewsapp.utils.getType


@Composable
fun ShimmerEffect() {

    val isDesktop  = remember {
        getType() == Type.Desktop
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(if(isDesktop) 3 else 1),
        verticalArrangement = Arrangement.spacedBy(xLargePadding),
        horizontalArrangement = Arrangement.spacedBy(mediumPadding),
        contentPadding = PaddingValues(mediumPadding),
        userScrollEnabled = false
    ) {
        repeat(12){
            item {
                ArticleCardShimmer()
            }
        }
    }
}

@Composable
fun ArticleCardShimmer() {

    Row(horizontalArrangement = Arrangement.spacedBy(mediumPadding)) {

        Box(modifier = Modifier.size(
            imageSize
        ).shimmerEffect())

        Column(modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(xxSmallPadding)

        ) {
            Box(
                modifier = Modifier.fillMaxWidth().height(xxxLargePadding).shimmerEffect()
            )
            Box(
                modifier = Modifier.fillMaxWidth().height(xxLargePadding).shimmerEffect()
            )
            Box(
                modifier = Modifier.fillMaxWidth(0.4f).height(mediumPadding).shimmerEffect()
            )
        }


    }
    
}


fun Modifier.shimmerEffect() = composed {

    val transition = rememberInfiniteTransition()
    val translateAnimation by transition.animateFloat(
        initialValue = 0f,
        targetValue = 400f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 1500, easing = LinearOutSlowInEasing),
            RepeatMode.Reverse
        ),
    )
    val brush by remember {
        derivedStateOf {
            Brush.linearGradient(
                colors = shimmerColors,
                start = Offset(translateAnimation, translateAnimation),
                end = Offset(translateAnimation + 100f, translateAnimation + 100f),
                tileMode = TileMode.Mirror,
            )
        }
    }

    background(brush, RoundedCornerShape(10))

}