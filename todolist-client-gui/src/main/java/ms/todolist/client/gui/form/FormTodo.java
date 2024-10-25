package ms.todolist.client.gui.form;

import java.awt.Component;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.Map;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import ms.todolist.client.gui.service.TodoServiceClientImpl;
import ms.todolist.domain.service.TodoService;
import ms.todolist.domain.todo.Todo;

/**
 *
 * @author MS
 */
public class FormTodo extends javax.swing.JFrame {

    private enum Action {
        SAVE, COMPELETE, DELETE;
    }

    private final TodoService todoService;

    @SuppressWarnings("unchecked")
    public FormTodo() {
        initComponents();

        listTodo.setModel(new DefaultListModel<>());
        listTodo.setCellRenderer(new StrikeThroughRenderer());

        todoService = new TodoServiceClientImpl();

        updateListView();
    }

    @SuppressWarnings("unchecked")
    private void updateListView() {
        var listModel = getListModel();
        listModel.removeAllElements();

        todoService.getData()
                .forEach(listModel::addElement);

        txtTodo.setText("");
        txtTodo.requestFocus();
    }

    private DefaultListModel getListModel() {
        var listModel = (DefaultListModel) listTodo.getModel();
        return listModel;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtTodo = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnDone = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listTodo = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Todo List");

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDone.setText("Done");
        btnDone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoneActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        listTodo.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(listTodo);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                    .addComponent(txtTodo)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addGap(18, 18, 18)
                        .addComponent(btnDone)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDelete)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnDone)
                    .addComponent(btnDelete))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        process(Action.SAVE);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoneActionPerformed
        process(Action.COMPELETE);
    }//GEN-LAST:event_btnDoneActionPerformed

    private void process(Action action) {
        switch (action) {
            case SAVE -> todoService.save(txtTodo.getText());
            case COMPELETE -> todoService.complete(getTodoId());
            case DELETE -> todoService.delete(getTodoId());
        }

        updateListView();
    }

    private int getTodoId() {        
        int id = 0, index = listTodo.getSelectedIndex();
        var listModel = getListModel();
        
        if (index != -1) {
            Todo todo = (Todo) listModel.get(index);
            id = todo.getId();
        }

        return id;
    }

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        process(Action.DELETE);
    }//GEN-LAST:event_btnDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDone;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> listTodo;
    private javax.swing.JTextField txtTodo;
    // End of variables declaration//GEN-END:variables

    class StrikeThroughRenderer implements ListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList list, Object value,
                int index, boolean isSelected, boolean cellHasFocus) {

            JLabel renderer = (JLabel) new DefaultListCellRenderer()
                    .getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            Todo todo = (Todo) value;
            renderer.setText(todo.getDeskripsi());

            return todo.isSudahDikerjakan() ? coretTodo(renderer) : renderer;
        }

        @SuppressWarnings("unchecked")
        private Component coretTodo(JLabel renderer) {
            Font font = renderer.getFont();
            Map attributes = font.getAttributes();
            attributes.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
            renderer.setFont(new Font(attributes));

            return renderer;
        }

    }

}
