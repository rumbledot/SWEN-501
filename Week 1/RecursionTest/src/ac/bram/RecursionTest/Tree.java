package ac.bram.RecursionTest;

import java.util.ArrayList;

public class Tree {

	private ArrayList<Tree> children = new ArrayList<>();
	private String name = "Tree";
	
	public Tree(String name)
	{
		this.name = name;
		System.out.println(this.name);
	}
	
	public void addChild(Tree t)
	{
		children.add(t);
	}
	
	public ArrayList<Tree> getChildren() {
		return children;
	}
	
	public String toString() 
	{
		return name;
	}
}
