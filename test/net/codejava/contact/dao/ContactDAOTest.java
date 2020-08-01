package net.codejava.contact.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import net.codejava.contact.model.Contact;

class ContactDAOTest {
 
	private DriverManagerDataSource dataSource;
	private ContactDAO dao;
	
	 @BeforeEach
	
	
	 void setupBeforeEach() {
		// TODO Auto-generated method stub
		dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3308/contactdb");
		dataSource.setUsername("root");
		dataSource.setPassword("12345");
		
		dao = new ContactDAOImpl(dataSource);
	} //*/
	
	@Test
	void testSave() {
	/*	dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3308/contactdb");
		dataSource.setUsername("root");
		dataSource.setPassword("12345");
		
		dao = new ContactDAOImpl(dataSource);
	*/
		Contact contact=new Contact("BIll Gates","bill@microsoft.com","Canada","0987654321");
		int result=dao.save(contact);
		assertTrue(result>0);
	
	
	}

	@Test
	void testUpdate() {
	/*	dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3308/contactdb");
		dataSource.setUsername("root");
		dataSource.setPassword("12345");
		
		dao = new ContactDAOImpl(dataSource);
		*/
		Contact contact=new Contact(2,"BIllY Gates","billygates@microsoft.com","Washington","0935525421");

		int result=dao.update(contact);
		assertTrue(result>0);
	}

	@Test
	void testGet() {
		Integer id=1;
		Contact contact=dao.get(id);
		if(contact!=null) {
			System.out.println(contact);
		}
		
		
		assertNotNull(contact);
	}

	@Test
	void testDelete() {
		Integer id=3;
		int result=dao.delete(id);
		assertTrue(result>0);
	}

	@Test
	void testList() {
		List<Contact> listContacts= dao.list();
		for(Contact aContact : listContacts) {
			System.out.println(aContact);
		}
		assertTrue(listContacts.isEmpty());
	}

}
