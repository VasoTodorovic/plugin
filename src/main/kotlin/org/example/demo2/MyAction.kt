package org.example.demo2


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