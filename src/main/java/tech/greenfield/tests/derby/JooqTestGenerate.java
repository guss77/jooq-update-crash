package tech.greenfield.tests.derby;

import org.apache.derby.jdbc.EmbeddedDriver;
import org.jooq.util.DefaultGeneratorStrategy;
import org.jooq.util.GenerationTool;
import org.jooq.util.JavaGenerator;
import org.jooq.util.derby.DerbyDatabase;
import org.jooq.util.jaxb.*;

public class JooqTestGenerate {
	
	static String TARGET_FOLDER = "target/generated-sources/model";

	public static void main(String[] args) throws Exception {
		MockDatabase db = new MockDatabase();
		db.createTable();
		Configuration config = createGeneratorConfig(db);
		GenerationTool gen = new GenerationTool();
		gen.setDataSource(db.ds);
		gen.run(config);
		System.exit(0);
	}

	public static Configuration createGeneratorConfig(MockDatabase db){
		return new Configuration()
				.withJdbc(new Jdbc()
						.withDriver(EmbeddedDriver.class.getName())
						.withUrl(db.getUrl()))
				.withGenerator(new Generator()
						.withName(JavaGenerator.class.getName())
						.withDatabase(new Database()
								.withName(DerbyDatabase.class.getName())
								.withInputSchema("")
								.withOutputSchema("")
								.withIncludes("names"))
						.withTarget(new Target()
								.withPackageName("genearted.model")
								.withDirectory(TARGET_FOLDER))
						.withStrategy(new Strategy()
								.withName(DefaultGeneratorStrategy.class.getName()))
						.withGenerate(new Generate()
								.withInterfaces(true).withPojos(true).withFluentSetters(true)
								.withDaos(true)))
				;
	}
}
