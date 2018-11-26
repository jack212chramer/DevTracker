package pl.dao;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.components.Comment;
import pl.jdbc.CommentJDBC;

@Repository
public class CommentDao {
	
	@Autowired
	CommentJDBC commentJDBC;
	
		
	public ArrayList<Comment> getCommentsForElement(int element_id,String element_type){
		ArrayList<Comment> list = commentJDBC.selectProjectsFromDatabaseByElement(element_type, element_id);
		return list;
	}
	
}
