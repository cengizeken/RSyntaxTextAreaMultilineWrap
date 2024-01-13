import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class RSyntaxMultilineAndLineNumbers extends JPanel implements TableCellRenderer {

    private final JLabel lineNumberLabel;
    private final RSyntaxTextArea textArea;

    public RSyntaxMultilineAndLineNumbers() {
        setLayout(new BorderLayout());

        lineNumberLabel = new JLabel();
        textArea = new RSyntaxTextArea();

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        add(lineNumberLabel, BorderLayout.WEST);
        add(new RTextScrollPane(textArea), BorderLayout.CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText(value != null ? value.toString() : "");

        // Set the line numbers
        lineNumberLabel.setText(getLineNumberText(table, row));

        // This line is important for dynamic row height
        table.setRowHeight(row, getPreferredSize().height);

        return this;
    }

    private String getLineNumberText(JTable table, int row) {
        int rowCount = table.getRowCount();
        int maxDigits = (int) Math.log10(Math.max(1, rowCount - 1)) + 1;
        return String.format("%" + maxDigits + "d ", row + 1);
    }

    private void setText(String text) {
        textArea.setText(text);
    }
}
