package org.example.demo2

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowManager
class FocusDiffPreviewAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {

        val project: Project = e.project ?: return

        // Get the Version Control tool window
        val toolWindowManager = ToolWindowManager.getInstance(project)
        val vcsToolWindow = toolWindowManager.getToolWindow("Version Control")

        // If the VCS tool window is available, activate it
        vcsToolWindow?.activate {
            // Focus on the changes tab (you can customize this as needed)
            focusOnChangesTab(project)
        }
    }

    private fun focusOnChangesTab(project: Project) {
        // Activate the "Changes" tab within the VCS tool window (this is a default behavior)
        val toolWindowManager = ToolWindowManager.getInstance(project)
        val vcsToolWindow = toolWindowManager.getToolWindow("Version Control")

        vcsToolWindow?.let {
            // Open the changes panel (diff view is usually accessible from here)
            it.show {
                // Trigger additional actions here if needed, e.g., displaying files, changes, etc.
            }
        }
    }
}