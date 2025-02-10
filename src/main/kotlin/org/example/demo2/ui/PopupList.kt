import com.intellij.openapi.ui.popup.JBPopup
import com.intellij.openapi.ui.popup.JBPopupFactory
import com.intellij.openapi.ui.popup.JBPopupListener
import com.intellij.openapi.ui.popup.LightweightWindowEvent
import com.intellij.openapi.wm.IdeFocusManager
import com.intellij.ui.components.JBList
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.KeyboardFocusManager
import javax.swing.*
import javax.swing.event.ListSelectionListener
import javax.swing.JFrame;


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
            .setRequestFocus(true)
            .setMovable(true)
            .createPopup()

        popup.addListener(object : JBPopupListener {
            override fun beforeShown(event: LightweightWindowEvent) {
                println(popup.isFocused()) //here will not have focus
                println(list.hasFocus()) //here will not have focus
            }

            override fun onClosed(event: LightweightWindowEvent) {
                println(popup.isFocused()) //here will not have focus
                println(list.hasFocus()) //here WILL HAVE
                println("end")
            }
        })

        return popup
    }
}
