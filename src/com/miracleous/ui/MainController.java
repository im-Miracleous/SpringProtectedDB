package com.miracleous.ui;

import com.miracleous.entity.Department;
import com.miracleous.entity.Student;
import com.miracleous.service.DepartmentDao;
import com.miracleous.service.StudentDao;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainController {
    private JTable tableStudent;
    private JTextField txtID;
    private JTextField txtName;
    private JTextArea txtAddress;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JComboBox<Department> dropdownDepartment;
    private List<Department> departments;
    private DepartmentDao departmentDao;
    private JButton submitButton;
    private JPanel rootPanel;
    private JPanel infoPanel;
    private JPanel genderCell;
    private JPanel departmentCell;
    private JPanel btnCell;

    private List<Student> students;
    private StudentDao studentDao;
    private StudentTableModel studentTableModel;

    public MainController() {
        this.initData();
        this.initComponents();
    }

    private void initData() {
        departmentDao = new DepartmentDao();
        departments = new ArrayList<>();
        studentDao = new StudentDao();
        students = new ArrayList<>();
        try {
            departments.addAll(departmentDao.getAllData());
            students.addAll(studentDao.getAllData());
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(rootPanel, e.getMessage(), "Error",  JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initComponents() {
        DefaultComboBoxModel<Department> departmentModel = new DefaultComboBoxModel<>();
        departmentModel.addAll(departments);
        dropdownDepartment.setModel(departmentModel);

        studentTableModel = new StudentTableModel(students);
        tableStudent.setModel(studentTableModel);
    }

    public void createUI() {
        JFrame frame = new JFrame("Main Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(rootPanel);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    private class StudentTableModel extends AbstractTableModel {

        private String[] columnNames = {"ID", "Name", "Address", "Male", "Department"};
        private List<Student> students;

        public StudentTableModel(List<Student> students) {
            this.students = students;
        }

        @Override
        public int getRowCount() {
            return students.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return switch (columnIndex) {
                case 0 -> students.get(rowIndex).getId();
                case 1 -> students.get(rowIndex).getName();
                case 2 -> students.get(rowIndex).getAddress();
                case 3 -> students.get(rowIndex).isMale();
                case 4 -> students.get(rowIndex).getDepartment();
                default -> "";
            };
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (getValueAt(0, columnIndex) != null) {
                return getValueAt(0, columnIndex).getClass();
            }
            return Object.class;
        }
    }
}
