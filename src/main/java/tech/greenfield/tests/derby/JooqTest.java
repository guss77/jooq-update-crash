package tech.greenfield.tests.derby;

import org.jooq.Configuration;
import org.jooq.SQLDialect;
import org.jooq.exception.NoDataFoundException;
import org.jooq.impl.DefaultConfiguration;

import genearted.model.app.tables.daos.NamesDao;
import genearted.model.app.tables.pojos.Names;

public class JooqTest {

	private NamesDao dao;
	private Names name;

	public JooqTest(Configuration config) {
		dao = new NamesDao(config);
	}

	public static void main(String[] args) throws Exception {
		try {
		MockDatabase db = new MockDatabase();
		db.createTable();
		Configuration config = new DefaultConfiguration().derive(SQLDialect.DERBY).set(db.ds);
		new JooqTest(config).run();
		System.exit(0);
		}catch (NoDataFoundException e) {
			System.err.println("Error: " + e);
			e.printStackTrace();
			System.exit(1);
		}
	}

	private void run() {
		dao.insert(new Names(null, "Oded"));
		setName(dao.findAll().get(0));
		getName().setName("guss77");
		dao.update(getName());
		System.out.println("Updated: " + getName());
	}

	private Names getName() {
		return name;
	}

	private void setName(Names name) {
		this.name = name;
	}

}
