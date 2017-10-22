import java.lang.reflect.Constructor;
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
		Constructor constructors[] = ObjClass.getDeclaredConstructors();
		
		System.out.println("inside inspector: " + ObjClass + " (recursive = "+recursive+")");
		ObjectClass(superClass);
		interfaceCheck(interfaces);
		methods(mArray, fields, constructors);   
		printFields(fields);
		printConstructors(constructors);
			    
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
	
	public void methods(Method mArray[], Field fields[], Constructor construct[]){
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
			      System.out.println("Method Modifier: " + printModifier(mArray[i]));
			      i++;
			   }
	    }
	}
	
	public void printFields(Field fields[]){
	      System.out.println("Fields: ");
	      for(int z =0; z < fields.length; z++){
	    	  System.out.println("\tName: " + fields[z].getName());
	    	  System.out.println("\tType: " + fields[z].getType());
	    	  System.out.println("\tField Modifier: " + printModifier(fields[z]) + "\n");
	      }
	}
	
	public void printConstructors(Constructor construct[]){
		System.out.println("Constructors:");
	      for(int x =0; x < construct.length; x++){
	    	  System.out.println(" " + construct[x]);
	      }
	}
	
	public String printModifier(Field fMod){
		int modCode = fMod.getModifiers();
  	    String modifier = Modifier.toString(modCode);
		return modifier;
	}
	
    public String printModifier(Method mMod){
	   int modCode = mMod.getModifiers();
	   String modifier = Modifier.toString(modCode);
	   return modifier;
    }
    

}
