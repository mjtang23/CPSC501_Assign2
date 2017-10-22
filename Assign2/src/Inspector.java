import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;


public class Inspector {
	
	public void inspect(Object obj, boolean recursive){
		
		Vector objectsToInspect = new Vector();
		Class ObjClass = obj.getClass();
		Class superClass = ObjClass.getSuperclass();
		Class interfaces[] = ObjClass.getInterfaces();
		Method mArray[]= ObjClass.getDeclaredMethods();
		Field fields[] = ObjClass.getDeclaredFields();
		
		ObjectClass(ObjClass, recursive);
		ObjectClass(superClass);
		interfaceCheck(interfaces);
		methods(mArray, fields);   
			    
			   }
		
			
	
	
	public void ObjectClass(Class objClass, boolean recursive){
		System.out.println("inside inspector: " + objClass + " (recursive = "+recursive+")");
	}
	
	public void ObjectClass(Class objClass){
		System.out.println("Super Class: " + objClass);
	}
	
	public void interfaceCheck(Class inter[]){
		int a = 0;
		System.out.print("Implemented interfaces: " );
		if(inter.length == 0){
			System.out.println("None");
		}else{
		   while(inter.length > (a+1)){
		      System.out.print(inter[a]+ "," + " " );
		      a++;
		   }
		   System.out.println(inter[a]);
		}
	}
	
	public void methods(Method mArray[], Field fields[]){
		int i = 0;

		System.out.print("Methods \n" );
		if(mArray.length == 0){
			System.out.println("None");
		}else{
			
			   while(mArray.length > i){
			      System.out.println("Method Name: "+ mArray[i].getName());
			      Class mExceptions[] = mArray[i].getExceptionTypes();
			      Class params[] = mArray[i].getParameterTypes();
			      for(int x =0; x < mExceptions.length; x++){
			    	  System.out.println("\t Exception: "+ mExceptions[x]);
			      }
			      System.out.print("Parameters:");
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
				      System.out.println(params[y] + ")");
			      }
			      System.out.println("Return type: " + mArray[i].getReturnType());
			      int modCode = mArray[i].getModifiers();
			      String modifier = Modifier.toString(modCode);
			      System.out.println("Modifier: " + modifier);
			      
			      System.out.println("Fields: ");
			      for(int z =0; z < fields.length; z++){
			    	  System.out.println("\tName: " + fields[z].getName());
			    	  System.out.println("\tType: " + fields[z].getType());
			    	  modCode = fields[z].getModifiers();
			    	  modifier = Modifier.toString(modCode);
			    	  System.out.println("\tModifier: " + modifier + "\n");
			      }
			      i++;
			   }
	    }
	}
	

}
