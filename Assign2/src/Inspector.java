import java.util.Vector;

public class Inspector {
	
	public void inspect(Object obj, boolean recursive){
		
		Vector objectsToInspect = new Vector();
		Class ObjClass = obj.getClass();
		Class superClass = ObjClass.getSuperclass();
		Class interfaces[] = ObjClass.getInterfaces();
		int i = 0;
		
		System.out.println("inside inspector: " + ObjClass + " (recursive = "+recursive+")");
		System.out.println("Super Class: " + superClass);
		System.out.print("Implemented interfaces: " );
		if(interfaces.length == 0){
			System.out.println("None");
		}
		else{
		   while(interfaces.length > (i+1)){
		      System.out.print(interfaces[i]+ "," + " " );
		      i++;
		   }
		   System.out.println(interfaces[i]);
		}
	}
	
	
	

}
