import com.intellij.openapi.ui.popup.JBPopup
import com.intellij.openapi.ui.popup.JBPopupFactory
import com.intellij.openapi.ui.popup.JBPopupListener
import com.intellij.openapi.ui.popup.LightweightWindowEvent
import com.intellij.openapi.wm.IdeFocusManager
import com.intellij.ui.components.JBList
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.*
import javax.swing.event.ListSelectionListener
import  java.awt.KeyboardFocusManager;

class PopupList {
    fun create(): JBPopup {
        val items = listOf("Alice", "Bob", "Charlie", "David")

        // Create a JBList for navigation
        val list = JBList(items)
        list.selectionMode = ListSelectionModel.SINGLE_SELECTION
        list.selectedIndex = 0  // Set initial selection

        // Detail label to update dynamically
        val detailLabel = JLabel("Vaso Selected: ${items[0]}")
        detailLabel.horizontalAlignment = SwingConstants.CENTER

        // Listen for selection changes and update the label
        val selectionListener = ListSelectionListener {
            if (!it.valueIsAdjusting) {
                val selectedValue = list.selectedValue
                detailLabel.text = "Selected: $selectedValue"
            }
        }
        list.addListSelectionListener(selectionListener)

        // Layout: List on the left, Detail on the right
        val panel = JPanel(BorderLayout())
        panel.preferredSize = Dimension(300, 150)  // Adjust size
        panel.add(JScrollPane(list), BorderLayout.WEST)
        panel.add(detailLabel, BorderLayout.CENTER)

        // Ensure the popup can capture keyboard input
        panel.setFocusCycleRoot(true)

        // Create the popup with the panel
        val popup = JBPopupFactory.getInstance()
            .createComponentPopupBuilder(panel, list)
            .setTitle("My Popup List")
            .setResizable(true)
            .setMovable(true)
            .createPopup()

        // Use the PopupStateListener to focus the list when the popup is shown
                popup.addListener(object : JBPopupListener {
                    override fun beforeShown(event: LightweightWindowEvent) {

                        SwingUtilities.invokeLater {
                            println("Before Popup Shown: Focus Owner = " + KeyboardFocusManager.getCurrentKeyboardFocusManager().focusOwner)

                            // First attempt to request focus using IdeFocusManager
                            IdeFocusManager.getInstance(null).requestFocus(panel, true)
                            SwingUtilities.invokeLater {
                                IdeFocusManager.getInstance(null).requestFocus(list, true)
                                println("After Focus Request: Focus Owner = " + KeyboardFocusManager.getCurrentKeyboardFocusManager().focusOwner)
                            }
                            // Forcefully request focus again after a delay
                            Timer(100) {
                                if (!list.hasFocus()) {
                                    println("Forcing focus to JBList...")
                                    list.requestFocusInWindow()
                                    println(list.hasFocus())
                                }
                            }.start()

                            println("After Focus Request: Focus Owner = " + KeyboardFocusManager.getCurrentKeyboardFocusManager().focusOwner)
                        }
                    }
                })

        return popup
    }
}
