package net.codejava.contact.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
//import java.util.*;
import javax.sql.DataSource;
//import javax.swing.tree.RowMapper;
//import javax.swing.tree.TreePath;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
//import org.springframework.jdbc.core.RowMapperResultSetExtractor;
//import org.springframework.jdbc.core.RowMapper;

import net.codejava.contact.model.Contact;

public class ContactDAOImpl implements ContactDAO {

	private JdbcTemplate jdbcTemplate;
	
	 public ContactDAOImpl(DataSource dataSource) {
		// TODO Auto-generated constructor stub
		this.jdbcTemplate=new JdbcTemplate(dataSource);
	}
	
	@Override
	public int save(Contact c) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO Contact(name,email,address,phone) VALUES(?,?,?,?)";
		return jdbcTemplate.update(sql,c.getName(),c.getEmail(),c.getAddress(),c.getPhone());
		
	}

	@Override
	public int update(Contact c) {
		// TODO Auto-generated method stub
		String sql="UPDATE Contact SET name=?,email=?,address=?,phone=? WHERE contact_id=?";
		return jdbcTemplate.update(sql,c.getName(),c.getEmail(),c.getAddress(),c.getPhone(),c.getId());
		
	}

	@Override
	public Contact get(Integer id) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM Contact Where contact_id ="+id;
		ResultSetExtractor<Contact> extractor = new ResultSetExtractor<Contact>() {
			
			@Override
			public Contact extractData(ResultSet rs) throws SQLException, DataAccessException {
				if(rs.next()) {
					String name= rs.getNString("name");
					String email= rs.getNString("email");
					String address= rs.getNString("address");
					String phone= rs.getNString("phone");
					return new Contact(id,name,email,address,phone);
					
				}
				return null;
			}
		};
		
		
		return jdbcTemplate.query(sql, extractor);
	}

	@Override
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM Contact WHERE contact_id="+id;
		
		return jdbcTemplate.update(sql);
	}
//ERROR IN LIST 
	@Override
	public List<Contact> list() {
		String sql="SELECT * FROM Contact";
		
		org.springframework.jdbc.core.RowMapper<Contact> rowMapper = new org.springframework.jdbc.core.RowMapper<Contact>() {
			
			@Override
			public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Integer id= rs.getInt("contact_id");
				String name= rs.getNString("name");
				String email= rs.getNString("email");
				String address= rs.getNString("address");
				String phone= rs.getNString("phone");
				return new Contact(id,name,email,address,phone);
			}
		};
	
		return jdbcTemplate.query(sql,rowMapper );
		//return null;
		// TODO Auto-generated method stub
	}
}
		
		/*	String sql="SELECT * FROM Contact";

		RowMapper<Contact> rowMapper =new RowMapper<Contact>() {
			
			@Override
			public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Integer id= rs.getInt("contact_id");
				String name= rs.getNString("name");
				String email= rs.getNString("email");
				String address= rs.getNString("address");
				String phone= rs.getNString("phone");
				return new Contact(id,name,email,address,phone);
			}
		};
		
		return jdbcTemplate.query(sql, rowMapper);
	
	}

}
*/