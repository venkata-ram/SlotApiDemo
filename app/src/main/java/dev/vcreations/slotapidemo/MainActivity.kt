package dev.vcreations.slotapidemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.vcreations.slotapidemo.ui.theme.SlotApiDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SlotApiDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen(){
    var linearSelected by remember { mutableStateOf(true) }
    var imageSelected by remember { mutableStateOf(true) }

    val onLinearClick = { value : Boolean ->
        linearSelected = value
    }

    val onTitleClick = { value : Boolean ->
        imageSelected = value
    }

    ScreenContent(
        linearSelected = linearSelected,
        imageSelected = imageSelected,
        onTitleClick = onTitleClick,
        onLinearClick = onLinearClick,
        titleContent = {
            if (imageSelected) {
                TitleImage(drawing = R.drawable.ic_cloud_download)
            }else{
                Text(
                    text = "Downloading",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(30.dp)
                )
            }
        },
        progressContent = {
            if (linearSelected) {
                LinearProgressIndicator(modifier = Modifier.height(40.dp))
            } else {
                CircularProgressIndicator(
                    modifier = Modifier.size(200.dp),
                    strokeWidth = 18.dp
                )
            }
        }
    )


}

@Composable
fun ScreenContent(
    linearSelected : Boolean,
    imageSelected : Boolean,
    onTitleClick : (Boolean) -> Unit,
    onLinearClick : (Boolean) -> Unit,
    titleContent : @Composable () -> Unit,
    progressContent : @Composable () -> Unit,
){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        titleContent()
        progressContent()
        CheckBoxes(linearSelected = linearSelected, imageSelected = imageSelected, onTitleClick = onTitleClick, onLinearClick = onLinearClick)
    }
}


@Composable
fun CheckBoxes(
    linearSelected : Boolean,
    imageSelected : Boolean,
    onTitleClick : (Boolean) -> Unit,
    onLinearClick : (Boolean) -> Unit
){
    Row(
        modifier = Modifier.padding(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = imageSelected,
            onCheckedChange = onTitleClick
        )
        Text(text = "Image Title")
        Spacer(modifier = Modifier.width(20.dp))
        Checkbox(
            checked = linearSelected,
            onCheckedChange = onLinearClick
        )
        Text(text = "Linear Progress")
    }
}

@Composable
fun TitleImage(drawing : Int){
    Image(
        painter = painterResource(id = drawing),
        contentDescription = "title image",
        modifier = Modifier.size(150.dp)
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewFun(){
    MainScreen()
}


