import javax.swing.*;
import javax.swing.table.AbstractTableModel;

public class RSyntaxCellEditorExample {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("RSyntaxTextArea Cell Editor Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JTable table = new JTable(new MyTableModel());

            // Set RSyntaxTextArea as a cell editor and cell renderer for the second column
            //table.getColumnModel().getColumn(1).setCellEditor(new RSyntaxMultilineAndLineNumbers());
            table.getColumnModel().getColumn(1).setCellRenderer(new RSyntaxMultilineAndLineNumbers());

            JScrollPane scrollPane = new JScrollPane(table);
            frame.getContentPane().add(scrollPane);

            frame.setSize(400, 300);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
    static class MyTableModel extends AbstractTableModel {

        private final String[] columnNames = {"Column 1", "Column 2"};
        private final Object[][] data = {
                {"Row 1", "This is a multiline text."},
                {"Row 2", "Another line of text.\nAnd another line.\nAnd another line."},
                {"Row 3", "A single line."},
        };

        @Override
        public int getRowCount() {
            return data.length;
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        @Override
        public String getColumnName(int col) {
            return columnNames[col];
        }

        @Override
        public boolean isCellEditable(int row, int col) {
            return true;
        }

        @Override
        public void setValueAt(Object value, int row, int col) {
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }
    }
}