import java.lang.reflect.Method;
import java.util.*;


public class Inspector {
	
	public void inspect(Object obj, boolean recursive){
		
		Vector objectsToInspect = new Vector();
		Class ObjClass = obj.getClass();
		Class superClass = ObjClass.getSuperclass();
		Class interfaces[] = ObjClass.getInterfaces();
		Method mArray[]= ObjClass.getDeclaredMethods();
		int i = 0;
		
		System.out.println("inside inspector: " + ObjClass + " (recursive = "+recursive+")");
		System.out.println("Super Class: " + superClass);
		System.out.print("Implemented interfaces: " );
		if(interfaces.length == 0){
			System.out.println("None");
		}else{
		   while(interfaces.length > (i+1)){
		      System.out.print(interfaces[i]+ "," + " " );
		      i++;
		   }
		   System.out.println(interfaces[i]);
		}
		System.out.print("Methods: \n" );
		if(mArray.length == 0){
			System.out.println("None");
		}else{
			
			   while(mArray.length > i){
//				  System.out.print("Name: ");
			      System.out.println("Method Name: "+ mArray[i].getName());
			      Class mExceptions[] = mArray[i].getExceptionTypes();
			      Class params[] = mArray[i].getParameterTypes();
			      for(int x =0; x < mExceptions.length; x++){
			    	  System.out.println("\t Exception: "+ mExceptions[x]);
			      }
			      System.out.print("\t Parameters:");
			      if(params.length == 0){
			    	  System.out.println(" None required");
			      }
			      else{
			    	  System.out.print("(");
			    	  int y = 0;
				      while((y+1) < params.length){
				    	  System.out.print(params[y]+ " ,");
				    	  y++;
				      }
				      System.out.println(params[y] + ")\n");
			      }
			      
			     i++;
			   }
		
			}
	}
	
	
	

}
