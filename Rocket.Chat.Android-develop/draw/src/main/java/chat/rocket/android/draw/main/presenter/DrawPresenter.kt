package chat.rocket.luk.draw.main.presenter

import android.graphics.Bitmap
import chat.rocket.luk.core.lifecycle.CancelStrategy
import chat.rocket.luk.util.extension.compressImageAndGetByteArray
import chat.rocket.luk.util.extension.launchUI
import javax.inject.Inject

class DrawPresenter @Inject constructor(
    private val view: DrawView,
    private val strategy: CancelStrategy
) {

    fun processDrawingImage(bitmap: Bitmap) {
        launchUI(strategy) {
            val byteArray = bitmap.compressImageAndGetByteArray("image/png")
            if (byteArray != null) {
                view.sendByteArray(byteArray)
            } else {
                view.showWrongProcessingMessage()
            }
        }
    }
}