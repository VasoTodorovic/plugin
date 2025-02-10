package org.example.demo2


import PopupList
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.Messages

class MyAction : AnAction("Show Message") {
    override fun actionPerformed(event: AnActionEvent) {
        Messages.showMessageDialog(

            event.project,
            "Hello from Kotlin Plugin!",
            "My Plugin",
            Messages.getInformationIcon()
        )
    }

}

class ShowPopupListAction : AnAction("Show Popup List") {
    override fun actionPerformed(event: AnActionEvent) {
        val popup = PopupList().create()
        popup.showInFocusCenter()
        popup.setRequestFocus(true)
    }
}
