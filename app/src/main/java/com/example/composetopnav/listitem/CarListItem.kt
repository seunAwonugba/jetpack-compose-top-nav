package com.example.composetopnav.listitem

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.composetopnav.R
import com.example.composetopnav.model.MyCar
import com.example.composetopnav.ui.theme.darkBlue

@Composable
fun CarListItem(myCar: MyCar){
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp)

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp)
        ) {
            Text(
                text = myCar.carName,
                color = darkBlue,
                fontWeight = FontWeight.Bold
            )
            Row {
                Text(text = stringResource(id = R.string.price))
                Text(
                    text = myCar.carPrice,
                    color = darkBlue,
                    fontWeight = FontWeight.Bold
                )
            }
            Row {
                Text(text = stringResource(id = R.string.target))
                Text(text = "${myCar.target}")
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = stringResource(id = R.string.complected))
                Text(text = "${myCar.completed}")
            }
            Row {
                Text(text = stringResource(id = R.string.condition))
                Text(text = myCar.condition)
            }
            Button(
                onClick = { /*TODO*/ },
                contentPadding = PaddingValues(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(id = R.string.add_car))
            }
        }
    }

}