import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Test;

public class Assign2JUnitTests {
	
	
	Inspector inspector = new Inspector();
	Object A = new ClassA();
	
	
	@Test
	public void testClass() {
		Class ObjClass = A.getClass();
		assertEquals(inspector.getClass(A), ObjClass);
		System.out.println(ObjClass + " " + inspector.getClass(A));
		
		
	}
	
	@Test
	public void testSuperClass(){
		Class SuperObjClass = A.getClass().getSuperclass();
		assertEquals(inspector.getSClass(A.getClass()),SuperObjClass);
		System.out.println(SuperObjClass + " " + inspector.getSClass(A.getClass()));
	}
	
	@Test
	public void testModifierMethod(){
		Class ObjClass = A.getClass();
		Method[] method = ObjClass.getDeclaredMethods();
		assertEquals(inspector.printModifier(method[0]), "public");
		
		
		
	}
	
	@Test
	public void testModifierField(){
       Class ObjClass = A.getClass();
	   Field[] field = ObjClass.getDeclaredFields();
	   assertEquals(inspector.printModifier(field[0]), "private");
	}
	
	@Test
	public void testMethodLength(){
		 Class ObjClass = A.getClass();
		 Method array[] = ObjClass.getDeclaredMethods();
		 assertEquals(array.length, inspector.getMethodArray(ObjClass).length);
		
	}
	
	@Test
	public void testConstructors(){
		 Class ObjClass = A.getClass();
		 Constructor array[] = ObjClass.getDeclaredConstructors();
		 assertEquals(array.length, inspector.getConstructor(ObjClass).length);
	}
	
	@Test
	public void testInterfaces(){
		Class ObjClass = A.getClass();
		Class array[] = ObjClass.getInterfaces();
		assertEquals(array.length, inspector.getInterfaces(ObjClass).length);
	}
	
	@Test
	public void testFields(){
		Class ObjClass = A.getClass();
		Field array[] = ObjClass.getDeclaredFields();
		assertEquals(array.length, inspector.getFieldArray(ObjClass).length);
	}
}
