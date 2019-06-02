package chat.rocket.luk.chatinformation.presentation

import chat.rocket.luk.chatinformation.viewmodel.ReadReceiptViewModel
import chat.rocket.luk.core.behaviours.LoadingView

interface MessageInfoView : LoadingView {

    fun showGenericErrorMessage()

    fun showReadReceipts(messageReceipts: List<ReadReceiptViewModel>)
}
