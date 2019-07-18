package ac.bram.HelloFX;

import java.util.HashMap;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

public class Controller {

	public Map<String, Student> students = new HashMap<String, Student>(); 
	public Map<String, Course> courses = new HashMap<String, Course>();

	@FXML
	private Label label1, label2, label3;
	@FXML
	private Button btnNewCourse, btnOK;
	@FXML
	private TextField txt1, txt2;
	@FXML
	private ListView<String> listCourse;
	@FXML
	private TreeView<String> listStudent;

	private TreeItem<String> rootItem, studentName, studentCourse, studentGrade;

	public void initialize() {
		rootItem = new TreeItem("Students");

		studentName = new TreeItem("Names");

		listStudent.setRoot(rootItem);
		rootItem.getChildren().add(studentName);
	}

	@FXML
	private void addNewMember()
	{
		label1.setText("Add A New Course");
		label2.setText("Course ID");

		txt1.setPromptText("course ID");
	}

	@FXML 
	public void clickSelectCourse(MouseEvent arg0) {
		System.out.println("clicked on " + listCourse.getSelectionModel().getSelectedItem());
	}

	@FXML
	private void addNewStudent() {
		label1.setText("Add A New Student");
		label2.setText("Student name");

		txt1.setPromptText("first name");
		txt2.setPromptText("last name");
	}

	@FXML 
	public void clickSelectStudent(MouseEvent arg0) {
		TreeItem<String> name = listStudent.getSelectionModel().getSelectedItem();
		String sName = name.getValue();
		if (students.containsKey(sName)) {
	        Student s = students.get(sName);
	        String ID = Integer.toString(s.ID());
	        label3.setText("Student's Information");
	        label2.setText(ID);
	        label1.setText(s.name());
		}
	}

	@FXML
	private void clickOK() {
		if (label2.getText().equals("Course ID") && txt1.getText().length() != 0) {
			String name = txt1.getText().toUpperCase();
			Course c = new Course(name);
			courses.put(c.name(), c);
			listCourse.getItems().add(c.name());
			txt1.clear();
		} else if (label2.getText().equals("Student name") && txt1.getText().length() != 0) {
			String firstName = txt1.getText();
			String lastName = txt2.getText();
			Student s = new Student(firstName + " " + lastName);
			students.put(s.name(), s);
			studentName.getChildren().add(new TreeItem(s.name()));
			txt1.clear();
			txt2.clear();
		}
	}
}