package org.example.demo2
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindowManager
import com.intellij.openapi.wm.ToolWindowId
import com.intellij.openapi.ui.Messages

class FocusDiffPreviewAction : AnAction("Focus Diff Preview") {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return

        // Focus on the Version Control Tool Window
        val toolWindowManager = ToolWindowManager.getInstance(project)
        val toolWindow = toolWindowManager.getToolWindow(ToolWindowId.VCS)
        if (toolWindow != null) {
            toolWindow.activate {
                // Focus on the diff preview inside the version control tool window
                val vcsContent = toolWindow.contentManager.selectedContent.
            }
        }
    }
}
