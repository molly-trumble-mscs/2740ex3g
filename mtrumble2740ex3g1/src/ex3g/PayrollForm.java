package ex3g;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;

public class PayrollForm extends JFrame {

	private JPanel contentPane;
	private JTextField hoursTextField;
	private JLabel totalHoursLabel;
	private JLabel grossPayLabel;
	private DefaultListModel employeeListModel;
	private JList employeeList;
	private Payroll employee;
	private JTextField empIdTextField;
	private JTextField empNameTextField;
	private JTextField payRateTextField;
	private JButton addHoursButton;
	private JButton clearHoursButton;
	private JButton updateButton;
	private PayrollObjMapper payrollObjMapper;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PayrollForm frame = new PayrollForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PayrollForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				do_this_windowClosing(e);
			}
		});
		setTitle("COMC 2740 Ex3G");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 439, 439);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblE = new JLabel("Select employee:");
		lblE.setBounds(27, 13, 102, 16);
		contentPane.add(lblE);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(27, 42, 365, 115);
		contentPane.add(scrollPane);
		
//		JList employeeList = new JList();
//		employeeListModel = new DefaultListModel();
//		employeeListModel.addElement(new Payroll(101, "John Doe", 10.0));
//		employeeListModel.addElement(new Payroll(102, "Jane Doe", 20.0));
//		employeeListModel.addElement(new Payroll(103, "Roger Smith", 30.0));
//		employeeListModel.addElement(new Payroll(104, "Sally Johnson", 40.0));
//		employeeListModel.addElement(new Payroll(105, "Mike Cornwell", 50.0));
		payrollObjMapper = new PayrollObjMapper("exercise3g.txt");
		employeeListModel = payrollObjMapper.getAllPayroll();
		
		employeeList = new JList(employeeListModel);
		employeeList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				do_employeeList_mouseClicked(arg0);
			}
		});
		scrollPane.setViewportView(employeeList);
		
		JLabel lblNewLabel = new JLabel("Employee ID (>100):");
		lblNewLabel.setBounds(27, 170, 120, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblEmployeeName = new JLabel("Employee Name:");
		lblEmployeeName.setBounds(27, 199, 102, 16);
		contentPane.add(lblEmployeeName);
		
		JLabel lblPayRate = new JLabel("Pay rate (7.25-100):");
		lblPayRate.setBounds(27, 228, 120, 16);
		contentPane.add(lblPayRate);
		
		JLabel lblEnterHours = new JLabel("Enter hours (0.1 - 20.0):");
		lblEnterHours.setBounds(27, 257, 148, 16);
		contentPane.add(lblEnterHours);
		
		hoursTextField = new JTextField();
		hoursTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				do_hoursTextField_focusGained(arg0);
			}
		});
		hoursTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		hoursTextField.setText("0.00");
		hoursTextField.setBounds(177, 254, 82, 22);
		contentPane.add(hoursTextField);
		hoursTextField.setColumns(10);
		
		JLabel lblTotalHours = new JLabel("Total hours:");
		lblTotalHours.setBounds(27, 286, 82, 16);
		contentPane.add(lblTotalHours);
		
		totalHoursLabel = new JLabel("0.00");
		totalHoursLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		totalHoursLabel.setBounds(177, 289, 82, 16);
		contentPane.add(totalHoursLabel);
		
		JLabel lblGrossPay = new JLabel("Gross pay:");
		lblGrossPay.setBounds(27, 315, 82, 16);
		contentPane.add(lblGrossPay);
		
		grossPayLabel = new JLabel("$0.00");
		grossPayLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		grossPayLabel.setBounds(177, 318, 82, 16);
		contentPane.add(grossPayLabel);
		
		addHoursButton = new JButton("+");
		addHoursButton.setEnabled(false);
		addHoursButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_addHoursButton_actionPerformed(arg0);
			}
		});
		addHoursButton.setBounds(264, 253, 41, 25);
		contentPane.add(addHoursButton);
		
		clearHoursButton = new JButton("Clear");
		clearHoursButton.setEnabled(false);
		clearHoursButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_clearHoursButton_actionPerformed(e);
			}
		});
		clearHoursButton.setBounds(307, 253, 85, 25);
		contentPane.add(clearHoursButton);
		
		JButton clearFormbutton = new JButton("Clear Form");
		clearFormbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_clearFormbutton_actionPerformed(e);
			}
		});
		clearFormbutton.setBounds(295, 356, 97, 25);
		contentPane.add(clearFormbutton);
		
		empIdTextField = new JTextField();
		empIdTextField.setText("000");
		empIdTextField.setBounds(177, 167, 82, 22);
		contentPane.add(empIdTextField);
		empIdTextField.setColumns(10);
		
		empNameTextField = new JTextField();
		empNameTextField.setBounds(141, 196, 118, 22);
		contentPane.add(empNameTextField);
		empNameTextField.setColumns(10);
		
		payRateTextField = new JTextField();
		payRateTextField.setText("7.25");
		payRateTextField.setBounds(177, 225, 82, 22);
		contentPane.add(payRateTextField);
		payRateTextField.setColumns(10);
		
		updateButton = new JButton("Update");
		updateButton.setEnabled(false);
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_updateButton_actionPerformed(arg0);
			}
		});
		updateButton.setBounds(162, 356, 97, 25);
		contentPane.add(updateButton);
	}
	
	protected void do_employeeList_mouseClicked(MouseEvent arg0) {
		employee = (Payroll) employeeList.getSelectedValue();
		this.empIdTextField.setText(Integer.toString(employee.getId()));
		this.empNameTextField.setText(employee.getName());
		DecimalFormat payRateFmt = new DecimalFormat("0.00");
		this.payRateTextField.setText(payRateFmt.format(employee.getPayRate()));
		
		// format and display hours and gross pay
		DecimalFormat hrsFmt = new DecimalFormat("0.00");
		this.totalHoursLabel.setText(hrsFmt.format(employee.getHours()));
		DecimalFormat grossPayFmt = new DecimalFormat("$#,##0.00");
		this.grossPayLabel.setText(grossPayFmt.format(employee.calcGrossPay()));
		
		// enable buttons
		this.addHoursButton.setEnabled(true);
		this.clearHoursButton.setEnabled(true);
		this.updateButton.setEnabled(true);
		
	}
	
	protected void do_addHoursButton_actionPerformed(ActionEvent arg0) {
		//Payroll emp = (Payroll) employeeList.getSelectedValue();
		employee.addHours(Double.parseDouble(this.hoursTextField.getText()));
		// display formated hours and gross pay
		DecimalFormat hrsFmt = new DecimalFormat("0.00");
		this.totalHoursLabel.setText(hrsFmt.format(employee.getHours()));
		DecimalFormat grossPayFmt = new DecimalFormat("$#,##0.00");
		this.grossPayLabel.setText(grossPayFmt.format(employee.calcGrossPay()));
		this.hoursTextField.setText("0.00");
		this.hoursTextField.requestFocus();
	}
	
	protected void do_clearHoursButton_actionPerformed(ActionEvent e) {
		//Payroll emp = (Payroll) employeeList.getSelectedValue();
		employee.setHours(0.0);
		this.totalHoursLabel.setText("0.00");
		this.hoursTextField.setText("0.00");
		this.grossPayLabel.setText("$0.00");
	}
	
	protected void do_clearFormbutton_actionPerformed(ActionEvent e) {
		//clear labels
		this.empIdTextField.setText("0");
		this.empNameTextField.setText(" ");
		this.payRateTextField.setText("0.00");
		this.hoursTextField.setText("0.00");
		this.totalHoursLabel.setText("0.00");
		this.grossPayLabel.setText("$0.00");
		// set focus & clear selection
		this.hoursTextField.requestFocus();
		this.employeeList.clearSelection();
		// disable buttons
		this.addHoursButton.setEnabled(false);
		this.clearHoursButton.setEnabled(false);
		this.updateButton.setEnabled(false);
	}
	
	protected void do_hoursTextField_focusGained(FocusEvent arg0) {
		this.hoursTextField.selectAll();
	}
	
	protected void do_updateButton_actionPerformed(ActionEvent arg0) {
		int id = Integer.parseInt(this.empIdTextField.getText());
		double rate = Double.parseDouble(this.payRateTextField.getText());
		Payroll payroll = (Payroll) employeeList.getSelectedValue();
		
		if (!payroll.setId(id)) {
			JOptionPane.showMessageDialog(null, "Invalid employee ID. \nMust be > 100");
			empIdTextField.setText(Integer.toString(employee.getId()));
			empIdTextField.requestFocus();
		} else if (!payroll.setPayRate(rate)) {
			JOptionPane.showMessageDialog(null, "Invalid pay rate. \nMust be between 7.25 and 100");
			payRateTextField.setText(Double.toString(employee.getPayRate()));
			payRateTextField.requestFocus();
		} else if (!payroll.setName(empNameTextField.getText())) {
			JOptionPane.showMessageDialog(null, "Employee must have a name.");
			empNameTextField.setText(employee.getName());
			empNameTextField.requestFocus();
		}
		employeeList.repaint();
	}
	
	protected void do_this_windowClosing(WindowEvent arg0) {
		if(payrollObjMapper != null)
			payrollObjMapper.writeAllPayroll(employeeListModel);
	}
}
