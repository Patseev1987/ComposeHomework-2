package ru.otus.compose.customlayout


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout

private const val X_COORD_START = 20
private const val Y_COORD_START = 100

@Composable
fun CustomBogdanLayout(modifier: Modifier = Modifier, columns: Int, content: @Composable () -> Unit) {
    Layout(modifier = modifier, content = content) { measurables, constraints ->
        val placeables = measurables.map { measurable ->
            measurable.measure(constraints)
        }
        layout(constraints.maxWidth, constraints.maxHeight) {
            var xCoord = X_COORD_START
            var yCoord = Y_COORD_START
            var tempWidth = 0
            var tempHeight = 0
            placeables.forEachIndexed { index, placeable ->

                when {
                    index == 0 -> {
                        tempWidth = placeables.maxOf { it.width }
                        tempHeight = placeables.maxOf { it.height }
                    }

                    index % columns == 0 -> {
                        yCoord += tempHeight + 20
                        xCoord = X_COORD_START
                    }


                    else -> {
                        xCoord += tempWidth + 20
                    }
                }


                placeable.placeRelative(x = xCoord, y = yCoord)
            }
        }
    }
}
