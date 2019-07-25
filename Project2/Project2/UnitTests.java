import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.junit.Test;

public class UnitTests {
	private static int passed = 0;

	@Test
	public void testModifiable() {
		Tester t = new Tester();
		Force f = new Force(24, "wind");
		assertEquals(f, t.strengthen(f));
		assertEquals(f, t.weaken(f));
		assertNotEquals(0, ++passed);
	}

	@Test
	public void testTile() {
		Zone c = new Zone();
		Force f = new Force(24, "wind");
		c.setColumn(3);
		assertEquals(3, c.getColumn());
		c.setRow(4);
		assertEquals(4, c.getRow());
		assertEquals("row: 4 col: 3", c.toString());
		assertEquals(true, c.canPropagate());
		c.setMeasurement(100);
		assertEquals("100", c.getMeasurement());
		assertEquals("wind has a load of 19", c.weaken(f).toString());
		assertEquals(24, f.getLoad(), 0.0001);
		assertNotEquals(0, ++passed);
	}

	@Test
	public void testBuilding() {
		Building b = new Building(1, 5, 10);
		assertEquals(1, b.getPeople());
		assertEquals(5, b.getCars());
		assertEquals(10, b.getHeight());
		assertEquals(true, b.canPropagate());
		b.setHeight(100);
		assertEquals(false, b.canPropagate());

		Force force = new Force(100, "force");
		assertEquals("force has a load of 127", b.strengthen(force).toString());
		assertNotEquals(0, ++passed);
	}

	@Test
	public void testHighway() {
		Highway c = new Highway(35);
		Force f = new Force(24, "wind");
		c.setColumn(3);
		assertEquals(3, c.getColumn());
		c.setRow(4);
		assertEquals(4, c.getRow());
		assertEquals("row: 4 col: 3", c.toString());
		assertEquals(true, c.canPropagate());
		c.setMeasurement(100);
		assertEquals("100", c.getMeasurement());
		assertEquals("wind has a load of 19", c.weaken(f).toString());
		assertEquals(24, f.getLoad(), 0.0001);

		Force force = new Force(100, "force");
		assertEquals("force has a load of 275", c.strengthen(force).toString());
		assertNotEquals(0, ++passed);
	}

	@Test
	public void testIndustrialBuilding() {
		IndustrialBuilding b = new IndustrialBuilding(1, 2, 3, 4);
		assertEquals(1, b.getPeople());
		assertEquals(2, b.getCars());
		assertEquals(3, b.getHeight());
		assertEquals(true, b.canPropagate());
		b.setHeight(100);
		assertEquals(true, b.canPropagate());

		Force force = new Force(100, "force");
		assertEquals("force has a load of 152", b.strengthen(force).toString());
		assertNotEquals(0, ++passed);
	}

	@Test
	public void testBodyOfWater() {
		BodyOfWater c = new BodyOfWater();
		Force f = new Force(24, "wind");
		c.setColumn(3);
		assertEquals(3, c.getColumn());
		c.setRow(4);
		assertEquals(4, c.getRow());
		assertEquals("row: 4 col: 3", c.toString());
		assertEquals(true, c.canPropagate());
		c.setMeasurement(100);
		assertEquals("100", c.getMeasurement());
		assertEquals("wind has a load of 19", c.weaken(f).toString());
		assertEquals(24, f.getLoad(), 0.0001);

		f = new Force(100, "wind");
		Force result = c.strengthen(f);
		assertEquals(f.getLoad(), result.getLoad(), 0.001);
		result = c.weaken(f);
		assertEquals(80, result.getLoad(), 0.001);
		assertNotEquals(0, ++passed);
	}

	@Test
	public void testNature() {
		Nature c = new Nature(33);
		Force f = new Force(24, "wind");
		c.setColumn(3);
		assertEquals(3, c.getColumn());
		c.setRow(4);
		assertEquals(4, c.getRow());
		assertEquals("row: 4 col: 3", c.toString());
		assertEquals(true, c.canPropagate());
		c.setMeasurement(100);
		assertEquals("100", c.getMeasurement());
		assertEquals("wind has a load of 16", c.weaken(f).toString());
		assertEquals(24, f.getLoad(), 0.0001);

		c = new Nature(51);
		assertEquals("wind has a load of 12", c.weaken(f).toString());
		assertEquals(false, c.canPropagate());

		f = new Force(100, "wind");
		Force result = c.strengthen(f);
		assertEquals(f.getLoad(), result.getLoad(), 0.001);
		result = c.weaken(f);
		assertEquals(49, result.getLoad(), 0.001);
		assertNotEquals(0, ++passed);
	}

	@Test
	public void testMap1() {
		Map map = new Map(2, 3);

		BodyOfWater tile1 = new BodyOfWater();
		BodyOfWater tile2 = new BodyOfWater();
		BodyOfWater tile3 = new BodyOfWater();
		Nature tile4 = new Nature(50);
		Nature tile5 = new Nature(50);
		Nature tile6 = new Nature(33);

		map.addTile(tile1);
		assertEquals(tile1, map.getTile(0, 0));
		map.addTile(tile2);
		assertEquals(tile2, map.getTile(0, 1));
		map.addTile(tile3);
		assertEquals(tile3, map.getTile(1, 0));
		map.addTile(tile4);
		assertEquals(tile4, map.getTile(1, 1));
		map.addTile(tile5);
		assertEquals(tile5, map.getTile(2, 0));
		assertEquals(null, map.getTile(2, 1));

		assertEquals(tile2, map.getNeighbors(tile1, Direction.EAST)[0]);
		assertEquals(tile4, map.getNeighbors(tile1, Direction.EAST)[1]);
		assertEquals(2, map.getNeighbors(tile1, Direction.EAST).length);

		assertEquals(tile3, map.getNeighbors(tile1, Direction.SOUTH)[0]);
		assertEquals(tile4, map.getNeighbors(tile1, Direction.SOUTH)[1]);
		assertEquals(2, map.getNeighbors(tile1, Direction.SOUTH).length);

		assertEquals(0, map.getNeighbors(tile1, Direction.WEST).length);
		assertEquals(0, map.getNeighbors(tile1, Direction.NORTH).length);

		map.addTile(tile6);
		assertEquals(tile3, map.getNeighbors(tile2, Direction.SOUTH)[0]);
		assertEquals(tile4, map.getNeighbors(tile2, Direction.SOUTH)[1]);
		assertEquals(2, map.getNeighbors(tile1, Direction.SOUTH).length);

		assertNotEquals(0, ++passed);

	}

	@Test
	public void testMap2() {
		Map city = new Map(5, 5);

		BodyOfWater chesapeake1 = new BodyOfWater();
		BodyOfWater chesapeake2 = new BodyOfWater();
		BodyOfWater chesapeake3 = new BodyOfWater();
		Nature forest1 = new Nature(50);
		Nature forest8 = new Nature(50);

		Nature forest2 = new Nature(33);
		IndustrialBuilding factory = new IndustrialBuilding(20, 15, 50, 30);
		Nature forest3 = new Nature(29);
		BodyOfWater chesapeake4 = new BodyOfWater();
		BodyOfWater chesapeake9 = new BodyOfWater();

		Nature forest4 = new Nature(40);
		Nature forest5 = new Nature(80);
		Nature forest6 = new Nature(90);
		Nature forest7 = new Nature(85);
		Nature forest9 = new Nature(85);

		BodyOfWater chesapeake5 = new BodyOfWater();
		BodyOfWater chesapeake6 = new BodyOfWater();
		BodyOfWater chesapeake7 = new BodyOfWater();
		BodyOfWater chesapeake8 = new BodyOfWater();
		BodyOfWater chesapeake10 = new BodyOfWater();

		Highway road = new Highway(1000);
		BodyOfWater pond1 = new BodyOfWater();
		Building houseSmall = new Building(2, 1, 25);
		Building houseTall = new Building(200, 45, 250);
		BodyOfWater pond2 = new BodyOfWater();

		city.addTile(chesapeake1);
		city.addTile(chesapeake2);
		city.addTile(chesapeake3);
		city.addTile(forest1);
		city.addTile(forest8);

		city.addTile(forest2);
		city.addTile(factory);
		city.addTile(forest3);
		city.addTile(chesapeake4);
		city.addTile(chesapeake9);

		city.addTile(forest4);
		city.addTile(forest5);
		city.addTile(forest6);
		city.addTile(forest7);
		city.addTile(forest9);

		city.addTile(chesapeake5);
		city.addTile(chesapeake6);
		city.addTile(chesapeake7);
		city.addTile(chesapeake8);
		city.addTile(chesapeake10);

		city.addTile(road);
		city.addTile(pond1);
		city.addTile(houseSmall);
		city.addTile(houseTall);
		city.addTile(pond2);

		Force smoke = new Force(100, "cruise ship smoke stack");

		 city.propagate(smoke, 0, 0, Direction.SOUTH); 
	        assertEquals("class BodyOfWater 90 class BodyOfWater 0 class BodyOfWater 0 class Nature 0 class Nature 0 \nclass Nature 75 class IndustrialBuilding 3161 class Nature 0 class BodyOfWater 0 class BodyOfWater 0 \nclass Nature 2529 class Nature 2829 class Nature 1927 class Nature 0 class Nature 0 \nclass BodyOfWater 2546 class BodyOfWater 1735 class BodyOfWater 2965 class BodyOfWater 2230 class BodyOfWater 0 \nclass Highway 4061 class BodyOfWater 2668 class Building 2012 class Building 2123 class BodyOfWater 1911 \n",city.toString()); 
	        assertNotEquals(0,++passed); 
	}

	public class Zone extends Tile {

		@Override
		public Force strengthen(Force f) {
			return null;
		}

		@Override
		public boolean canPropagate() {
			return true;
		}
	}

	public static StringBuffer systemCall(String cmd) {
		StringBuffer result = new StringBuffer();
		try {
			System.err.println("doing " + cmd);
			Runtime run = Runtime.getRuntime();
			Process pr = run.exec(cmd);
			pr.waitFor();
			BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
			String line = null;

			while ((line = buf.readLine()) != null)
				result.append(line + "\n");
			buf.close();
			buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			while ((line = buf.readLine()) != null)
				result.append(line + "\n");
			buf.close();
			pr.getOutputStream().close();
			pr.destroy();
		} catch (Exception e) {
			StackTraceElement[] elts = e.getStackTrace();
			for (int i = 0; i < elts.length; i++)
				result.append(elts[i].toString() + "\n");
		}
		return result;
	}

	@Test
	public void testStyle() {
		assertEquals("passed", checkPrivacy("Building.java", "int", "people",
				"Building: All appropriate attributes are private and commented."));
		assertEquals("passed", checkPrivacy("Building.java", "int", "cars",
				"Building: All appropriate attributes are private and commented."));
		assertEquals("passed", checkPrivacy("Building.java", "int", "height",
				"Building: All appropriate attributes are private and commented."));
		assertEquals("passed", checkPrivacy("Force.java", "double", "load",
				"Force: All appropriate attributes are private and commented."));
		assertEquals("passed", checkPrivacy("Force.java", "String", "name",
				"Force: All appropriate attributes are private and commented."));
		assertEquals("passed", checkPrivacy("Highway.java", "int", "carDensity",
				"Highway: All appropriate attributes are private and commented."));
		assertEquals("passed", checkPrivacy("IndustrialBuilding.java", "double", "emissions",
				"IndustrialBuilding: All appropriate attributes are private and commented."));
		assertEquals("passed", checkPrivacy("IndustrialBuilding.java", "int", "EMISSIONS_CONSTANT",
				"IndustrialBuilding: All appropriate attributes are private and commented."));
		assertEquals("passed", checkPrivacy("Map.java", "Tile[][]", "map",
				"Map: All appropriate attributes are private and commented."));
		assertEquals("passed", checkPrivacy("Map.java", "int", "freeRow",
				"Map: All appropriate attributes are private and commented."));
		assertEquals("passed", checkPrivacy("Map.java", "int", "freeColumn",
				"Map: All appropriate attributes are private and commented."));
		assertEquals("passed", checkPrivacy("Nature.java", "int", "treeDensity",
				"Nature: All appropriate attributes are private and commented."));
		assertEquals("passed",
				checkPrivacy("Tile.java", "int", "row", "Tile: All appropriate attributes are private and commented."));
		assertEquals("passed", checkPrivacy("Tile.java", "int", "column",
				"Tile: All appropriate attributes are private and commented."));
		assertEquals("passed", checkPrivacy("Tile.java", "String", "measurement",
				"Tile: All appropriate attributes are private and commented."));
		// assertEquals("passed", checkPrivacy("Tile.java", "int",
		// "PEOPLE_WASTE", "Tile: All appropriate attributes are private and
		// commented."));
		// assertEquals("passed", checkPrivacy("Tile.java", "int", "CARS_WASTE",
		// "Tile: All appropriate attributes are private and commented."));
		assertNotEquals(0, ++passed);
	}

	@Test
	public void testPropagate() {
		assertEquals("passed",
				checkPropagate("Map.java", "Map: propagate method is called instead of checking object type."));
		assertNotEquals(0, ++passed);
	}

	@Test
	public void testGetNeighbors() {
		ArrayList<String> body = pullMethodBody("Map.java", "public\\s+Tile\\[\\]\\s+getNeighbors\\(.*\\)\\s*\\{");
		boolean small = true;
		System.out.println("body.size() " + body.size());
		if (body.size() > 150)
			small = false;
		assertEquals(true, small);
		assertNotEquals(0, ++passed);
	}

	@Test
	public void testInheritance() {
		assertEquals("passed", checkSuperCtor("Building",
				"Building: All child classes appropriately use the parent class' constructors."));
		assertEquals("passed", checkSuperCtor("Highway",
				"Highway: All child classes appropriately use the parent class' constructors."));
		assertEquals("passed", checkSuperCtor("IndustrialBuilding",
				"IndustrialBuilding: All child classes appropriately use the parent class' constructors."));
		assertEquals("passed", checkSuperCtor("Nature",
				"Nature: All child classes appropriately use the parent class' constructors."));

		assertEquals("passed", checkOverride("BodyOfWater.java", "strengthen",
				"BodyOfWater: The @Override tag is used in all the appropriate places."));
		assertEquals("passed", checkOverride("BodyOfWater.java", "canPropagate",
				"BodyOfWater: The @Override tag is used in all the appropriate places."));
		assertEquals("passed", checkOverride("Building.java", "canPropagate",
				"Building: The @Override tag is used in all the appropriate places."));
		assertEquals("passed", checkOverride("Force.java", "toString",
				"Force: The @Override tag is used in all the appropriate places."));
		assertEquals("passed", checkOverride("Highway.java", "strengthen",
				"Highway: The @Override tag is used in all the appropriate places."));
		assertEquals("passed", checkOverride("Highway.java", "canPropagate",
				"Highway: The @Override tag is used in all the appropriate places."));
		assertEquals("passed", checkOverride("IndustrialBuilding.java", "strengthen",
				"IndustrialBuilding: The @Override tag is used in all the appropriate places."));
		assertEquals("passed", checkOverride("IndustrialBuilding.java", "canPropagate",
				"IndustrialBuilding: The @Override tag is used in all the appropriate places."));
		assertEquals("passed",
				checkOverride("Map.java", "toString", "Map: The @Override tag is used in all the appropriate places."));
		assertEquals("passed", checkOverride("Nature.java", "strengthen",
				"Nature: The @Override tag is used in all the appropriate places."));
		assertEquals("passed", checkOverride("Nature.java", "canPropagate",
				"Nature: The @Override tag is used in all the appropriate places."));
		assertEquals("passed", checkOverride("Nature.java", "weaken",
				"Nature: The @Override tag is used in all the appropriate places."));
		assertEquals("passed", checkOverride("Tile.java", "toString",
				"Tile: The @Override tag is used in all the appropriate places."));
		assertNotEquals(0, ++passed);
	}

	private String checkSuperCtor(String className, String errorMsg) {
		ArrayList<String> body = pullMethodBody(className + ".java",
				"(\\s*)public(\\s*)" + className + "(\\s*)\\([a-zA-Z0-9 ,\\[\\]]*\\)(\\s*)\\{(.*)");
		for (int i = 0; i < body.size(); i++) {
			System.out.println(body.get(i));
			if (body.get(i).matches("(\\s*)super\\((.*);"))
				return "passed";
		}
		return errorMsg;
	}

	private String checkOverride(String filename, String method, String errorMsg) {
		String line = null;
		try {
			FileReader fReader = new FileReader(filename);
			BufferedReader bReader = new BufferedReader(fReader);

			while ((line = bReader.readLine()) != null) {
				line = line.trim();
				if (!line.isEmpty()) {
					if (line.contains("@Override")) {
						while ((line = bReader.readLine()).trim().isEmpty()) {
							// spin until next line found
						}
						if (line.contains("public"))
							return "passed";
					}
				}
			}
			bReader.close();
		} catch (Exception e) {
			return "NOT MET: Couldn't open " + filename;
		}
		return errorMsg;
	}

	private String checkPrivacy(String filename, String type, String attribute, String errorMsg) {
		String line = null;
		try {
			FileReader fReader = new FileReader(filename);
			BufferedReader bReader = new BufferedReader(fReader);

			while ((line = bReader.readLine()) != null) {
				// System.out.println("(line.contains (//) ||
				// line.contains(/*))" + (line.contains ("//") ||
				// line.contains("/*")));
				line = line.trim();
				if (!line.isEmpty())
					if (line.contains("private") && line.contains(type) && line.contains(attribute)
							&& line.contains(";") && (line.contains("//") || line.contains("/*")))
						return "passed";
			}
			bReader.close();
		} catch (Exception e) {
			return "NOT MET: Couldn't open " + filename;
		}
		return errorMsg;
	}

	private String checkPropagate(String filename, String errorMsg) {
		String line = null;
		try {
			FileReader fReader = new FileReader(filename);
			BufferedReader bReader = new BufferedReader(fReader);

			while ((line = bReader.readLine()) != null) {
				line = line.trim();
				if (!line.isEmpty()) {
					if (line.contains("instanceof") || line.contains(".getClass().equals")
							|| line.contains(".getClass() ="))
						return errorMsg;
				}
			}
			bReader.close();
		} catch (Exception e) {
			return "NOT MET: Couldn't open WhiteBloodCell.java";
		}
		return "passed";
	}

	private ArrayList<String> pullMethodBody(String filename, String methodRegex) {
		ArrayList<String> body = new ArrayList<String>();
		String line = null;
		int brackets = 0;
		boolean found = false;
		int bodyBrackets = 0;
		try {
			FileReader fReader = new FileReader(filename);
			
			BufferedReader bReader = new BufferedReader(fReader);

			while ((line = bReader.readLine()) != null) {
				System.out.println(line);
				line = line.trim();
				if (!line.isEmpty()) {
					if (line.matches(methodRegex) && line.contains("{")) {
						// System.out.println("FOUND METHOD*************");
						bodyBrackets = brackets;
						brackets++;
						found = true;
					} else if (line.contains("{"))
						brackets++;
					else if (line.contains("}"))
						brackets--;

					if (found) {
						body.add(line);
						// System.out.println("BRACKETS************* " +
						// brackets);
						// System.out.println("BODYBRACKETS************* " +
						// bodyBrackets);
					}

					if (found && brackets == bodyBrackets)
						return body;
				}
			}
			bReader.close();
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	@Test
	public void countPassed() {
		assertEquals(13, passed);
		if (passed == 13)
			System.out.println(systemCall(
					"curl -k    https://cs.gmu.edu/~kdobolyi/sparc/process.php?user=sparc_31EwAoWFWxlWACZC-project2_0-COMPLETED"));
		else
			System.out.println(systemCall(
					"curl -k    https://cs.gmu.edu/~kdobolyi/sparc/process.php?user=sparc_31EwAoWFWxlWACZC-project2_0-PROGRESS"));
	}

}
