import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;


public class Inspector {
	//Main running code for assignment 2
	public void inspect(Object obj, boolean recursive){
		
		Class ObjClass = getClass(obj);
		Field fields[] = ObjClass.getDeclaredFields();
		Constructor constructors[] = ObjClass.getDeclaredConstructors();
		
		   printObjectClass(ObjClass, recursive);
		   printSuperClass(ObjClass, recursive);
		   printInterface(ObjClass, recursive);
		   printMethods(ObjClass, recursive);   
		   printFields(ObjClass, recursive);
		   printConstructors(ObjClass);
		  

		
			    
	}
	//The main inspector class that takes the input from the TestDriver
	public void printObjectClass(Class objClass, boolean recurse){
		
		System.out.println("inside inspector: " + objClass + " (recursive = "+recurse+")");
	}
    // Returns the class that we explore further into
	protected Class getClass(Object obj){
		if(obj.getClass().isArray()){
			Class temp = obj.getClass();
			while(temp.getName().charAt(0) == '['){
			   temp = temp.getComponentType();
			  
			}
			return temp;
			
		}else{
		return obj.getClass();
		}
	}
	
	//prints the super class and iterates till it hits a null value
	public void printSuperClass(Class objClass, Boolean recurse){
		System.out.println("Super Class: " + getSClass(objClass));
		SClassLoop(objClass.getSuperclass());	
		
		
	}
	
	//gets the super class name and returns as a string
	protected String getSClass(Class objClass){
		return objClass.getSuperclass().getName();
	}
	
	//iterates through the class's super classes till it has reached the end of the hierarchy
	protected void SClassLoop(Class superClass){
		if(superClass.getSuperclass() != null){
			System.out.println("\tSuper Class: " + getSClass(superClass));
			SClassLoop(superClass.getSuperclass());
		}else{
			return;
		}
	}
	
	//prints the interfaces associated with class
	public void printInterface(Class objClass, Boolean recurse){
		int a = 0;
		Class interfaces[] = getInterfaces(objClass);
		System.out.print("Implemented interfaces: " );
		if(interfaces.length == 0){
			System.out.println("None");
		}else{
		   while(interfaces.length > (a+1)){
		      System.out.print(interfaces[a].getSimpleName()+ "," + " " );
		      a++;
		   }
		   System.out.println(interfaces[a].getClass());
		   if(getSClass(objClass) != null){
			   printInterface(objClass.getSuperclass(), recurse);
		   }
		}
	}
	
	//returns an array with interfaces associated with class
	protected Class[]getInterfaces(Class obj){
		return obj.getInterfaces();
	}
	
	//prints the method associated with the class
	public void printMethods(Class objClass, Boolean recurse){
		int i = 0;
        Method [] mArray = getMethodArray(objClass);
		System.out.println("----Methods---- \n" );
		if(mArray.length == 0){
			System.out.println("None");
		}else{
			getMethods(mArray, objClass, recurse);
		}
	    
	}
	
	//returns the array of methods of all visibility
	protected Method[] getMethodArray(Class objClass){
		Method mArray[]= objClass.getDeclaredMethods();
		return mArray;
	}
	
	//returns the methods associated with the class. Including parent methods
	//associated with it
	public void getMethods(Method mArray[], Class obj, Boolean recurse){
		int i = 0;
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
		      System.out.println("Method Modifier: " + printModifier(mArray[i])+ "\n");
		      i++;
		      
		}
		if(obj.getSuperclass()!= null){
	    	  getMethods(getMethodArray(obj.getSuperclass()), obj.getSuperclass(), recurse);
		}
	}
	
	//gets the fields associated with the class
	public void printFields(Class obj, Boolean recurse){
		  Field [] fields = getFieldArray(obj);
	      System.out.println("Fields: ");
	      if(fields.length == 0){
	    	  System.out.println("No fields in this class");
	      }else{
	    	  getFields(fields, obj, recurse);
	      }
	      
	}
	
	//gets fields associated from class and its parents 
	public void getFields(Field [] fields,Class obj, Boolean recurse){
		for(int z =0; z < fields.length; z++){
	    	  System.out.println("\tName: " + fields[z].getName());
	    	  if(fields[z].getType().isArray()){
			    	 System.out.println( "\tType: " + fields[z].getClass().getComponentType());
			      }else{
			    	 System.out.println("\tType: " + fields[z].getType());
			      }

	    	  System.out.println("\tField Modifier: " + printModifier(fields[z]) + "\n");
	    	  if(printModifier(fields[z]) == "public"){
	    		  System.out.println(fields[z].getClass().getSuperclass());
	    		  
	    	  }
	      }
		if(obj.getSuperclass() != null){
			Field [] superFields = getFieldArray(obj.getSuperclass());
			getFields(superFields, obj.getSuperclass(), recurse);
		}
		
	}
	//returns an array with fields of all visibility. 
	protected Field[] getFieldArray(Class objClass){
		Field fArray[]= objClass.getDeclaredFields();
		return fArray;
	}
	
	//prints out all the constructors associated with the class
	public void printConstructors(Class objClass){
		Constructor construct[] = getConstructor(objClass);
		System.out.println("Constructors:");
	      for(int x =0; x < construct.length; x++){
	    	  System.out.println(" " + construct[x]);
	      }
	}
	
	//returns all constructors associated with the class
	protected Constructor[] getConstructor(Class objClass){
		return objClass.getDeclaredConstructors();
	}
	
	//returns modifiers for object type Field
	public String printModifier(Field fMod){
		int modCode = fMod.getModifiers();
  	    String modifier = Modifier.toString(modCode);
		return modifier;
	}
	
	//returns modifiers for object type Method
    public String printModifier(Method mMod){
	   int modCode = mMod.getModifiers();
	   String modifier = Modifier.toString(modCode);
	   return modifier;
    }
    

}
