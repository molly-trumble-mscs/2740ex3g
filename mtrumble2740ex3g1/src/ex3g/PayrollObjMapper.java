package ex3g;

import java.io.*;
import java.util.Scanner;
import javax.swing.DefaultListModel;

public class PayrollObjMapper {
	private String fileName;
	private PrintWriter outputFile;
	private Scanner inputFile;
	private File file;
	
	public PayrollObjMapper (String fileName) {
		super();
		this.fileName = fileName;
	}
	
	public boolean openInputFile() {
		boolean fileOpened = false;
		
		// Open the file.
	    try {
	    	File file = new File(this.fileName);
			fileOpened = file.exists();
			
			if (fileOpened) {
			    this.inputFile = new Scanner(file);
			}
	    }
		catch (IOException e) {}
	    
	    return fileOpened;
	}
	
	public void closeInputFile() {
		if(this.inputFile != null)
			this.inputFile.close();
	}
	
	public boolean openOutputFile() {
		boolean fileOpened = false;
		
		// Open the file.
		try {
			outputFile = new PrintWriter(this.fileName);
			fileOpened = true;
		}
		catch (IOException e) {}
		
		return fileOpened;
	}
	
	public void closeOutputFile() {
		if (outputFile != null) outputFile.close();
	}
	
	public Payroll getNextPayroll() {
		Payroll p = null;
		
		String textline = this.inputFile.nextLine();
		int id = Integer.parseInt(textline);
		String name = inputFile.nextLine();
		textline = this.inputFile.nextLine();
		double payRate = Double.parseDouble(textline);
		textline = this.inputFile.nextLine();
		double hours = Double.parseDouble(textline);
		
		p = new Payroll(id, name, payRate, hours);
		return p;
	}
	
	public void writePayroll(Payroll payroll) {
		if(outputFile != null && payroll != null){
			outputFile.println(payroll.getId());
			outputFile.println(payroll.getName());
			outputFile.println(payroll.getPayRate());
			outputFile.println(payroll.getHours());
		}
	}
	
	public DefaultListModel getAllPayroll() {
		DefaultListModel payrollCollection = new DefaultListModel();
		
		if(this.openInputFile()) {
			while(this.inputFile.hasNext()) {
				payrollCollection.addElement(this.getNextPayroll());
			}
		}
		
		this.closeInputFile();
		return payrollCollection;
	}
	
	public void writeAllPayroll(DefaultListModel payrollCollection) {
		if(this.openOutputFile()){
			for(int i=0; i<payrollCollection.getSize(); i++) {
				Payroll p = (Payroll) payrollCollection.get(i);
				this.writePayroll(p);
			}
		}
		outputFile.close();
	}
}
